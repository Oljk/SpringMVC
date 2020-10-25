package springmvc.model.entities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springmvc.model.dao.DaoConnection;
import springmvc.model.dao.UserDAO;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class User {
    @Id
    private int user_id;
    private String name;
    private String surname = "default";
    private String phone_number;
    private String email;
    private boolean isAdmin;

    @NotNull(message="is required")
    private String login;

    @Size(min = 6, max = 15, message="min = 6, max = 15")
    private String password;


    public User(int user_id, String name, String surname, String phone_number, String email, boolean isAdmin, String login, String password) {
        this.user_id = user_id;
        this.name = name;
        this.surname = surname;
        this.phone_number = phone_number;
        this.email = email;
        this.isAdmin = isAdmin;
        this.login = login;
      //  this.password = passwordEncoder().encode(password);
        this.password = password;
    }


    public User() {
    }

    public boolean checkUser() {
        if (this.name == null || "".equals(this.name) /*||  this.surname == null ||  "".equals(this.surname) */ || this.login == null) {
            return false;
        }
        return this.checkLogin();
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
     //   this.password =  passwordEncoder().encode(password);
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return user_id == user.user_id &&
                isAdmin == user.isAdmin &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(phone_number, user.phone_number) &&
                Objects.equals(email, user.email) &&
                Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, name, surname, phone_number, email, isAdmin, login);
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", email='" + email + '\'' +
                ", isAdmin=" + isAdmin +
                ", login=" + login +
                '}';
    }


    public boolean checkLogin() {
        return !this.login.isEmpty() && !this.password.isEmpty();
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
