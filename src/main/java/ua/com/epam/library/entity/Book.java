package ua.com.epam.library.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book extends Entity<Book> implements Identifiable {

    private String title;
    private String published;
    private int quantity;
    private String description;
    private String photoName;

    private long genreId;
    private String genreName;

    private long authorsId;
    private String authorFirstName;
    private String authorLastName;

    private long publishingHouseId;
    private String publishingHouseName;

    List<Author> authors = new ArrayList<>();

    public Book() {
    }
    public Book(String title,
                String published,
                int quantity,
                String description,
                String photoName,
                long genreId,
                String genreName,
                long authorsId,
                String authorFirstName,
                String authorLastName,
                long publishingHouseId,
                String publishingHouseName,
                List<Author> authors) {
        this.title = title;
        this.published = published;
        this.quantity = quantity;
        this.description = description;
        this.photoName = photoName;
        this.genreId = genreId;
        this.genreName = genreName;
        this.authorsId = authorsId;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.publishingHouseId = publishingHouseId;
        this.publishingHouseName = publishingHouseName;
        this.authors = authors;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public long getGenreId() {
        return genreId;
    }

    public void setGenreId(long genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public long getAuthorsId() {
        return authorsId;
    }

    public void setAuthorsId(long authorsId) {
        this.authorsId = authorsId;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public long getPublishingHouseId() {
        return publishingHouseId;
    }

    public void setPublishingHouseId(long publishingHouseId) {
        this.publishingHouseId = publishingHouseId;
    }

    public String getPublishingHouseName() {
        return publishingHouseName;
    }

    public void setPublishingHouseName(String publishingHouseName) {
        this.publishingHouseName = publishingHouseName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;

        if (!Objects.equals(title, book.title)) return false;
        if (!Objects.equals(published, book.published)) return false;
        if (!Objects.equals(genreName, book.genreName)) return false;
        if (!Objects.equals(authorFirstName, book.authorFirstName))
            return false;
        if (!Objects.equals(authorLastName, book.authorLastName))
            return false;
        return Objects.equals(publishingHouseName, book.publishingHouseName);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (published != null ? published.hashCode() : 0);
        result = 31 * result + (genreName != null ? genreName.hashCode() : 0);
        result = 31 * result + (authorFirstName != null ? authorFirstName.hashCode() : 0);
        result = 31 * result + (authorLastName != null ? authorLastName.hashCode() : 0);
        result = 31 * result + (publishingHouseName != null ? publishingHouseName.hashCode() : 0);
        return result;
    }
}
