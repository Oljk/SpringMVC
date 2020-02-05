package springmvc.model.entities;

import org.springframework.context.annotation.Bean;

import java.util.Objects;


public class Author {
    private int author_id;
    private String name;
    private String surname;
    private String description;

    public Author() {

    }

    public Author(int author_id, String name, String surname, String description) {
        this.author_id = author_id;
        this.name = name;
        this.surname = surname;
        this.description = description;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return author_id == author.author_id &&
                Objects.equals(name, author.name) &&
                Objects.equals(surname, author.surname) &&
                Objects.equals(description, author.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(author_id, name, surname, description);
    }

    @Override
    public String toString() {
        return "Author{" +
                "author_id=" + author_id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
