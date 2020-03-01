package springmvc.model.entities;

import java.util.Objects;

public class Item {
    private int item_id;
    private int parent_id;
    private String name;
    private Number type;
    private String description;

    public Item(int item_id, int parent_id, String name, Number type, String description) {
        this.item_id = item_id;
        this.parent_id = parent_id;
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public Item(int item_id, String name, Number type, String description) {
        this.item_id = item_id;
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public Item() {

    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getType() {
        return type;
    }

    public void setType(Number type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return item_id == item.item_id &&
                parent_id == item.parent_id &&
                Objects.equals(name, item.name) &&
                Objects.equals(type, item.type) &&
                Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item_id, parent_id, name, type, description);
    }

    @Override
    public String toString() {
        return "Item{" +
                "item_id=" + item_id +
                ", parent_id=" + parent_id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                '}';
    }

}
