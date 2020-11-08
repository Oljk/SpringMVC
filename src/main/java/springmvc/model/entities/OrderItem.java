package springmvc.model.entities;

public class OrderItem {
    int orderId = 0;
    Book book;
    int amount;

    public OrderItem() {
    }

    public OrderItem(Book book) {
        super();
        this.book = book;
        this.amount = 1;
    }

    public OrderItem(Book book, int amount) {
        this.book = book;
        this.amount = amount;
    }

    public OrderItem(int orderId, Book book, int amount) {
        this.orderId = orderId;
        this.book = book;
        this.amount = amount;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int orderItemId() {
        return book.getItem().getItem_id();
    }

}
