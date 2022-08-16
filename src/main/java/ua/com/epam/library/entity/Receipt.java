package ua.com.epam.library.entity;

import java.time.LocalDateTime;

public class Receipt extends Entity<Receipt> implements Identifiable {

    private String receiptNumber;

    private Stage stage;
    private Rent rent;
    private LocalDateTime receivingDate;
    private LocalDateTime estimateReturnDate;
    private LocalDateTime realReturnDate;
    private double penalty;

    private long userId;
    private long bookId;
    private long userOrderId;
    private String email;

    private String title;

    private String photoName;
    private String authorFirstName;
    private String authorLastName;
    private Book book;

    public Receipt() {
    }

    public Receipt(String receiptNumber,
                   Stage stage,
                   Rent rent,
                   LocalDateTime receivingDate,
                   LocalDateTime estimateReturnDate,
                   LocalDateTime realReturnDate,
                   double penalty,
                   long userId,
                   long bookId,
                   long userOrderId,
                   String title,
                   String email,
                   String photoName,
                   String authorFirstName,
                   String authorLastName,
                   Book book) {
        this.receiptNumber = receiptNumber;
        this.email = email;
        this.stage = stage;
        this.rent = rent;
        this.receivingDate = receivingDate;
        this.estimateReturnDate = estimateReturnDate;
        this.realReturnDate = realReturnDate;
        this.penalty = penalty;
        this.userId = userId;
        this.bookId = bookId;
        this.userOrderId = userOrderId;
        this.title = title;
        this.photoName = photoName;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.book = book;
    }

    public Stage getStage() {
        return stage;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public LocalDateTime getReceivingDate() {
        return receivingDate;
    }

    public void setReceivingDate(LocalDateTime receivingDate) {
        this.receivingDate = receivingDate;
    }

    public LocalDateTime getEstimateReturnDate() {
        return estimateReturnDate;
    }

    public void setEstimateReturnDate(LocalDateTime estimateReturnDate) {this.estimateReturnDate = estimateReturnDate;}

    public LocalDateTime getRealReturnDate() {
        return realReturnDate;
    }

    public void setRealReturnDate(LocalDateTime realReturnDate) {
        this.realReturnDate = realReturnDate;
    }

    public double getPenalty() {return penalty;}

    public void setPenalty(double penalty) {this.penalty = penalty;}

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getUserOrderId() {
        return userOrderId;
    }

    public void setUserOrderId(long userOrderId) {
        this.userOrderId = userOrderId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
