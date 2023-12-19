package org.example;

import org.example.dao.impl.UserDaoCriteria;
import org.example.models.User;

import java.time.LocalDate;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("John", "john@domain.com", 1, LocalDate.of(1999, 1, 28), true);
        User user2 = new User("Susan", "susan@domain.com", 8, LocalDate.of(1978, 7, 14), true);
        User user3 = new User("Mike", "mike@domain.com", 4, LocalDate.of(1988, 6, 1), true);
        User user4 = new User("Leo", "leo@domain.com", 3, LocalDate.of(1989, 3, 4), true);
        UserDaoCriteria dao = new UserDaoCriteria();
        long u1 = dao.save(user1);
        user1.setId(u1);
        long u2 = dao.save(user2);
        user2.setId(u2);

        dao.save(user3);
        dao.save(user4);

        dao.delete(user1);
        assert dao.findById(u1).isEmpty();

        user2.setName("Kek");
        dao.update(user2);
        System.out.println(dao.findById(user2.getId()).get().getName());
        assert dao.findById(user2.getId()).get().getName().equals("Kek");
        System.out.println(Arrays.toString(dao.findAll(1, 2).toArray()));
        System.out.println(Arrays.toString(dao.findAll(2, 3).toArray()));
    }
}