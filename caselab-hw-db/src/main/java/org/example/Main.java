package org.example;

import java.time.LocalDate;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("John", "john@domain.com", 1, LocalDate.of(1999, 1, 28), true);
        User user2 = new User("Susan", "susan@domain.com", 8, LocalDate.of(1978, 7, 14), true);
        UserDao dao = new UserDao("jdbc:postgresql://localhost:5432/users_db", "postgres", "root");
        long u1 = dao.save(user1);
        user1.setId(u1);
        long u2 = dao.save(user2);
        dao.delete(user1);
        assert dao.findById(u1).isEmpty();
        user2.setId(u2);
        user2.setName("Kek");
        dao.update(user2);
        assert dao.findById(user2.getId()).get().getName().equals("Kek");
        System.out.println(Arrays.toString(dao.findAll(1, 5).toArray()));
        System.out.println(Arrays.toString(dao.findAll(2, 3).toArray()));
    }
}