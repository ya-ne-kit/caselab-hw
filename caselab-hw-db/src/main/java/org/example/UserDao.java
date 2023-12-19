package org.example;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class UserDao implements Dao<User> {
    private Connection connection;

    public UserDao(String url, String username, String password) {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findById(long id) {
        User user = null;
        try {
            PreparedStatement statement =
                    connection.prepareStatement("SELECT * FROM Users WHERE id = ?");
            statement.setLong(1, id);
            statement.executeQuery();
            ResultSet set = statement.getResultSet();
            if (set.next()) {
                user = new User(
                        set.getLong("id"),
                        set.getString("name"),
                        set.getString("email"),
                        set.getInt("grade"),
                        set.getDate("date_of_birth").toLocalDate(),
                        set.getBoolean("alive")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(user);
    }

    @Override
    public List<User> findAll(int from, int pageSize) {
        if (from < 1) from = 1;
        if (pageSize < 1) pageSize = 10;
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement statement =
                    connection.prepareStatement("SELECT * FROM public.\"Users\" LIMIT ? OFFSET ?");
            statement.setInt(1, pageSize);
            statement.setInt(2, (from - 1) * pageSize);
            statement.executeQuery();
            ResultSet set = statement.getResultSet();
            while (set.next()) {
                users.add(new User(
                        set.getLong("id"),
                        set.getString("name"),
                        set.getString("email"),
                        set.getInt("grade"),
                        set.getDate("date_of_birth").toLocalDate(),
                        set.getBoolean("alive")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public Long save(User user) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO public.\"Users\" (name, email, grade, date_of_birth, alive) VALUES (?, ?, ?, ?, ?) RETURNING id", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setInt(3, user.getGrade());
            statement.setDate(4, Date.valueOf(user.getDateOfBirth()));
            statement.setBoolean(5, user.getIsAlive());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement("UPDATE public.\"Users\" SET name = ?, email = ?, grade = ?, date_of_birth = ?, alive = ? WHERE id = ?");
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setInt(3, user.getGrade());
            statement.setDate(4, Date.valueOf(user.getDateOfBirth()));
            statement.setBoolean(5, user.getIsAlive());
            statement.setLong(6, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(User user) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement("DELETE FROM public.\"Users\" WHERE id = ?");
            statement.setLong(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}