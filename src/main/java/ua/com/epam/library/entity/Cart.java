package ua.com.epam.library.entity;

public class Cart extends Entity<Cart> implements Identifiable {

    private long bookId;
    private long userId;

    private int quantity;
    private int total;

    private String title;
    private String photoName;

    private String authorFirstName;
    private String authorLastName;

    public Cart() {
    }

    public Cart(long bookId, long userId, int quantity, int total, String title, String photoName, String authorFirstName, String authorLastName) {
        this.bookId = bookId;
        this.userId = userId;
        this.quantity = quantity;
        this.total = total;
        this.title = title;
        this.photoName = photoName;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
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
}