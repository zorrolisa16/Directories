package model;

import model.exception.DBConnectionException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Polina Astashko
 */
public class ConnectionPool {

    private static ConnectionPool instance;

    private static final String DB_PROPERTIES = "database.properties";
    private final int initConnectionsCount = 5;
    private static String driver;
    private static String DB_URL;
    private static String user;
    private static String password;
    private BlockingQueue<Connection> connections;

    ConnectionPool() throws DBConnectionException {
        if (instance != null) {
            return;
        }
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(DB_PROPERTIES));
            System.out.println("Properties file loaded");
        } catch (IOException e) {
            throw new DBConnectionException("properties file not loaded");
        }
        driver = "com.mysql.jdbc.Driver";
        DB_URL = properties.getProperty("url");
        user = properties.getProperty("user");
        password = properties.getProperty("password");

        try {
            Class.forName(driver);
            System.out.println("Driver loaded");
        } catch (ClassNotFoundException e) {
            throw new DBConnectionException("Error loading driver!", e);
        }

        connections = new ArrayBlockingQueue<>(initConnectionsCount);
        try {
            for (int i = 0; i < initConnectionsCount; i++) {
                Connection connection = DriverManager.getConnection(DB_URL, user, password);
                if (connection == null) {
                    throw new DBConnectionException("Driver type is not correct in URL " + DB_URL + ".");
                }
                connections.add(connection);
                System.out.println("Connection " + i + " established");
            }
        } catch (SQLException e) {
            throw new DBConnectionException("Failed to establish connection", e);
        }
        System.out.println("DB pool of connections inited");
    }

    public static synchronized ConnectionPool getInstance() throws DBConnectionException {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public synchronized void deinitDBConnector() throws DBConnectionException {
        try {
            while (connections.size() > 0) {
                connections.take().close();
            }
        } catch (SQLException e) {
            throw new DBConnectionException("Could not close database connection ", e);
        } catch (InterruptedException e) {
            throw new DBConnectionException("Problem with concurrent queue", e);
        }
        System.out.println("DB pool of connections deinited");
    }

    public synchronized Connection getConnection() throws DBConnectionException {
        try {
            System.out.println("got connection from the pool");
            return connections.take();
        } catch (InterruptedException e) {
            throw new DBConnectionException("Failed to get connection from pool", e);
        }
    }

    public synchronized void releaseConnection(Connection connection) throws DBConnectionException {
        try {
            if (connection.isClosed()) {
                System.out.println("connection was closed");
                System.out.println("created new connection");
                Connection newConnection = DriverManager.getConnection(DB_URL, user, password);
                connections.add(newConnection);
            } else {
                connections.add(connection);
                System.out.println("returned connection to the pool");
            }
        } catch (SQLException e) {
            throw new DBConnectionException("Failed to establish connection", e);
        }
    }

}
