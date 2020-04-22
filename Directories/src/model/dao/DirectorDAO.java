package model.dao;

import model.entity.Director;
import model.exception.DAOException;
import model.exception.DBConnectionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Polina Astashko
 */
public class DirectorDAO extends DAO {

    private static final String INSERT_DIRECTOR_SQL = "insert into director (name) values(?)";
    private static final String SELECT_ALL_DIRECTORS_SQL = "select * from director";
    private static final String UPDATE_DIRECTOR_SQL = "update director set name = ? where name = ?";
    private static final String DELETE_DIRECTOR_SQL = "delete from director where name = ?";

    public DirectorDAO() throws DAOException {
        super();
    }

    public List<Director> readDirectors() throws DAOException {
        List<Director> directors = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getDBConnector().getConnection();
            PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_DIRECTORS_SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Director director = new Director(id, name);
                directors.add(director);
            }
        } catch (SQLException | DBConnectionException e) {
        } finally {
            if (connection != null) {
                try {
                    getDBConnector().releaseConnection(connection);
                } catch (DBConnectionException e) {
                    throw new DAOException("Failed to return connection to db connector ", e);
                }
            }
        }
        return directors;
    }

    public void insertDirector(String name) throws DAOException {
        Connection connection = null;
        try {
            connection = getDBConnector().getConnection();
            PreparedStatement stmt = connection.prepareStatement(INSERT_DIRECTOR_SQL);
            stmt.setString(1, name);
            stmt.execute();
        } catch (SQLException | DBConnectionException e) {
        } finally {
            if (connection != null) {
                try {
                    getDBConnector().releaseConnection(connection);
                } catch (DBConnectionException e) {
                    throw new DAOException("Failed to return connection to db connector ", e);
                }
            }
        }
    }

    public void updateDirector(String old_name, String new_name) throws DAOException {
        Connection connection = null;
        try {
            connection = getDBConnector().getConnection();
            PreparedStatement stmt = connection.prepareStatement(UPDATE_DIRECTOR_SQL);
            stmt.setString(1, old_name);
            stmt.setString(2, new_name);
            stmt.execute();
        } catch (SQLException | DBConnectionException e) {
        } finally {
            if (connection != null) {
                try {
                    getDBConnector().releaseConnection(connection);
                } catch (DBConnectionException e) {
                    throw new DAOException("Failed to return connection to db connector ", e);
                }
            }
        }
    }

    public void deleteDirector(String name) throws DAOException {
        Connection connection = null;
        try {
            connection = getDBConnector().getConnection();
            PreparedStatement stmt = connection.prepareStatement(DELETE_DIRECTOR_SQL);
            stmt.setString(1, name);
            stmt.execute();
        } catch (SQLException | DBConnectionException e) {
        } finally {
            if (connection != null) {
                try {
                    getDBConnector().releaseConnection(connection);
                } catch (DBConnectionException e) {
                    throw new DAOException("Failed to return connection to db connector ", e);
                }
            }
        }
    }

}
