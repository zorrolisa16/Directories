package model.dao;

import model.entity.Film;
import model.exception.DAOException;
import model.exception.DBConnectionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Polina Astashko
 */
public class FilmDAO extends DAO {

    private static final String SELECT_ALL_FILMS_SQL = "select f.*, d.name from film f join director d on f.director_id=d.id";
    private static final String INSERT_FILM_SQL = "insert into film (name, genres, release_date, rating, director_id) values(?, ?, ?, ?, (select id from director where name=?))";
    private static final String UPDATE_FILM_SQL = "update film set name = ? where name = ?";
    private static final String DELETE_FILM_SQL = "delete from film where name = ?";

    public FilmDAO() throws DAOException {
        super();
    }

    public List<Film> readFilms() throws DAOException {
        List<Film> films = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getDBConnector().getConnection();
            PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_FILMS_SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String genres = rs.getString(3);
                Date release_date = rs.getDate(4);
                double rating = rs.getFloat(5);
                int director_id = rs.getInt(6);
                String director_name = rs.getString(7);
                Film film = new Film(id, name, genres, release_date, rating, director_id);
                film.setDirectorName(director_name);
                films.add(film);
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
        return films;
    }

    public void insertFilm(String name, String genres, Date date, float rating, String director_name) throws DAOException {
        Connection connection = null;
        try {
            connection = getDBConnector().getConnection();
            PreparedStatement stmt = connection.prepareStatement(INSERT_FILM_SQL);
            stmt.setString(1, name);
            stmt.setString(2, genres);
            stmt.setDate(3, new java.sql.Date(date.getTime()));
            stmt.setFloat(4, rating);
            stmt.setString(5, director_name);
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

    public void updateFilm(String old_name, String new_name) throws DAOException {
        Connection connection = null;
        try {
            connection = getDBConnector().getConnection();
            PreparedStatement stmt = connection.prepareStatement(UPDATE_FILM_SQL);
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

    public void deleteFilm(String name) throws DAOException {
        Connection connection = null;
        try {
            connection = getDBConnector().getConnection();
            PreparedStatement stmt = connection.prepareStatement(DELETE_FILM_SQL);
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
