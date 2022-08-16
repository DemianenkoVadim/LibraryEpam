package ua.com.epam.library.entity;

import java.util.Objects;

public class Genre extends Entity<Genre> implements Identifiable {

    private String genre;

    public Genre() {
    }

    public Genre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre genre)) return false;

        return Objects.equals(this.genre, genre.genre);
    }

    @Override
    public int hashCode() {
        return genre != null ? genre.hashCode() : 0;
    }
}
