package model.entity;

import java.util.Date;

/**
 * @author Polina Astashko
 */
public class Film {

    private int id;
    private String name;
    private String genres;
    private Date date;
    private double rating;
    private int director_id;
    private String director_name;

    public Film(int id, String name, String genres, Date date, double rating, int director_id) {
        setId(id);
        setTitle(name);
        setGenres(genres);
        setDate(date);
        setRating(rating);
        setDirectorId(director_id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return name;
    }

    public void setTitle(String name) {
        this.name = name;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getDirectorId() {
        return director_id;
    }

    public void setDirectorId(int director_id) {
        this.director_id = director_id;
    }

    public void setDirectorName(String director_name) {
        this.director_name = director_name;
    }

    @Override
    public String toString() {
        return String.format("name: %s; genres: %s; date: %s; rating: %.1f; name of director: %s", name, genres, date, rating,
                director_name);
    }

}
