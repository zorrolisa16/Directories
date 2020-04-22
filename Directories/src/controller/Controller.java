package controller;

import model.dao.*;
import model.entity.*;
import model.exception.DAOException;
import model.exception.ControllerException;

import java.util.Date;
import java.util.List;

/**
 * @author Polina Astashko
 */
public class Controller {

    public List<Director> getDirectors() throws ControllerException {
        List<Director> directors;
        try {
            DirectorDAO tmp = new DirectorDAO();
            directors = tmp.readDirectors();
            System.out.println("read all directors");
        } catch (DAOException e) {
            throw new ControllerException("Failed to get directors", e);
        }
        return directors;
    }

    public List<Film> getFilms() throws ControllerException {
        List<Film> films;
        try {
            FilmDAO tmp = new FilmDAO();
            films = tmp.readFilms();
            System.out.println("read all films");
        } catch (DAOException e) {
            throw new ControllerException("Failed to get films", e);
        }
        return films;
    }

    public void insertDirector(String name) throws ControllerException {
        try {
            DirectorDAO tmp = new DirectorDAO();
            tmp.insertDirector(name);
            System.out.println("insert director result");
        } catch (DAOException e) {
            throw new ControllerException("Failed to insert director", e);
        }
    }

    public void insertFilm(String name, String genres, Date date, float rating, String director_name) throws ControllerException {
        try {
            FilmDAO tmp = new FilmDAO();
            tmp.insertFilm(name, genres, date, rating, director_name);
            System.out.println("insert film result");
        } catch (DAOException e) {
            throw new ControllerException("Failed to insert film", e);
        }
    }

    public void updateDirector(String old_name, String new_name) throws ControllerException {
        try {
            DirectorDAO tmp = new DirectorDAO();
            tmp.updateDirector(old_name, new_name);
            System.out.println("update director result");
        } catch (DAOException e) {
            throw new ControllerException("Failed to update director", e);
        }
    }

    public void updateFilm(String old_name, String new_name) throws ControllerException {
        try {
            FilmDAO tmp = new FilmDAO();
            tmp.updateFilm(old_name, new_name);
            System.out.println("update director result");
        } catch (DAOException e) {
            throw new ControllerException("Failed to update film", e);
        }
    }

    public void deleteDirector(String name) throws ControllerException {
        try {
            DirectorDAO tmp = new DirectorDAO();
            tmp.deleteDirector(name);
            System.out.println("delete director result");
        } catch (DAOException e) {
            throw new ControllerException("Failed to delete director", e);
        }
    }

    public void deleteFilm(String name) throws ControllerException {
        try {
            FilmDAO tmp = new FilmDAO();
            tmp.deleteFilm(name);
            System.out.println("delete film result");
        } catch (DAOException e) {
            throw new ControllerException("Failed to delete film", e);
        }
    }

    public static <T extends Iterable<E>, E> void showResults(T list) {
        for (E element : list) {
            System.out.println(element);
        }
    }

}
