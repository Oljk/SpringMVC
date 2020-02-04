package springmvc.model.entities;

import java.util.ArrayList;
import java.util.Objects;

public class Order {
    private int order_id;
    private int user_id;
    private String adress;
    private double summ;
    private String comments;
    private boolean isDone;
    private ArrayList<Book> books;

    public Order(int order_id, int user_id, String adress, double summ, String comments, boolean isDone, ArrayList<Book> books) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.adress = adress;
        this.summ = summ;
        this.comments = comments;
        this.isDone = isDone;
        this.books = books;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public double getSumm() {
        return summ;
    }

    public void setSumm(double summ) {
        this.summ = summ;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return order_id == order.order_id &&
                user_id == order.user_id &&
                Double.compare(order.summ, summ) == 0 &&
                isDone == order.isDone &&
                Objects.equals(adress, order.adress) &&
                Objects.equals(comments, order.comments) &&
                Objects.equals(books, order.books);
    }

    @Override
    public int hashCode() {

        return Objects.hash(order_id, user_id, adress, summ, comments, isDone, books);
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", user_id=" + user_id +
                ", adress='" + adress + '\'' +
                ", summ=" + summ +
                ", comments='" + comments + '\'' +
                ", isDone=" + isDone +
                ", books=" + books +
                '}';
    }
}
