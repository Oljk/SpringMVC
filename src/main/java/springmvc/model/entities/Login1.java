package springmvc.model.entities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;


public class Login1 {

    @NotNull(message="is required")
    private String login;

    @Size(min = 6, max = 15, message="min = 6, max = 15")
    private String password;

    public Login1(){}

    public Login1(String login, String password) {
        this.login = login;
        this.password = passwordEncoder().encode(password);
    }

    public Login1(User user) {
        login = user.getLogin();
        password = user.getPassword();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = passwordEncoder().encode(password);
    }



    public boolean checkLogin() {
        return !this.login.isEmpty() && !this.password.isEmpty();
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
