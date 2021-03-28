package springmvc.model.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * session_id = для заказов-корзин, isVirtual для таких заказов тру
 * если заказ в базе хранится, то там идет айдишник
 * пока заказ не подтвердили, заказ висит в корзине по сешн айди,
 * после подтверждения создается обьект в БД  и дальше заказ по ордер айди формируется
 */

public class Order {
    private int order_id;
    private int user_id;
    private String adress;
    private double summ;
    private String comments;
    private boolean isDone;
    private Map<Integer, OrderItem> books;
    private String sessionId;
    private boolean isVirtual = false;


    public Order() {
        books = new HashMap<>();
    }

    public Order(String sessionId) {
        this.sessionId = sessionId;
        isVirtual = true;
    }

    public Order(int order_id, int user_id, String adress, String comments, boolean isDone, List<OrderItem> items) {
        books = new HashMap<>();
        this.order_id = order_id;
        this.user_id = user_id;
        this.adress = adress;
        this.comments = comments;
        this.isDone = isDone;
        for (OrderItem item: items) {
            books.put(item.orderItemId(), item);
        }
        checkSum();
    }

    public Order(int order_id, int user_id, String adress, double summ, String comments, boolean isDone) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.adress = adress;
        this.summ = summ;
        this.comments = comments;
        this.isDone = isDone;
    }

    public Order(int order_id, int user_id, String adress, double summ, String comments, boolean isDone, Map<Integer, OrderItem> books, String sessionId) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.adress = adress;
        this.summ = summ;
        this.comments = comments;
        this.isDone = isDone;
        this.books = books;
        this.sessionId = sessionId;
    }

    public Order(int order_id, int user_id, String adress, Map<Integer, OrderItem> books, String sessionId, boolean isVirtual) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.adress = adress;
        this.books = books;
        this.sessionId = sessionId;
        this.isVirtual = isVirtual;
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

    public Map<Integer, OrderItem> getOrderItems() {
        return books;
    }

    public void setBooks(Map<Integer, OrderItem> books) {
        this.books = books;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public boolean isCartOrder() {
        return isVirtual;
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

    /**
     * Сумма не пересчитывается для выполненых заказов
     */
    public void checkSum() {
        if (!isDone) {
            summ = 0;
            for (OrderItem item : books.values()) {
                summ += item.getBook().getPrice() * item.getAmount();
            }
        }
    }

    public void addOrderItem(OrderItem item) {
        books.put(item.orderItemId(), item);
        checkSum();
    }

    public void removeOrderItem(OrderItem item) {
        books.remove(item.orderItemId());
        checkSum();
    }

    public void addOrderItems(List<OrderItem> items) {
        for (OrderItem item: items) {
            books.put(item.orderItemId(), item);
        }
    }
}
