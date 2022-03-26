package com.example.javapatternmirea2.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class UserDao {
    private final DataSource dataSource;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDao(DataSource dataSource, PasswordEncoder passwordEncoder) {
        this.dataSource = dataSource;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveNewUser(String username, String password) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO users (username, password, enabled) VALUES (?, ?, ?)");
            statement.setString(1, username);
            statement.setString(2, passwordEncoder.encode(password));
            statement.setBoolean(3, true);
            statement.execute();

            statement = connection
                    .prepareStatement("INSERT INTO authorities (username, authority) VALUES (?, ?)");
            statement.setString(1, username);
            statement.setString(2, "ROLE_USER");
            statement.execute();
            connection.commit();
        }
        catch (SQLException e) {
            throw new InvalidUsernameException();
        }
    }
}
