package org.example.dao.impl;

import org.example.HibernateUtil;
import org.example.dao.Dao;
import org.example.models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;

public class UserDaoHql implements Dao<User> {
    Transaction transaction = null;

    @Override
    public Optional<User> findById(long id) {
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            user = session.createQuery("from User u where u.id = :param", User.class)
                    .setParameter("param", id).getSingleResult();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return Optional.of(user);
    }

    @Override
    public List<User> findAll(int from, int pageSize) {
        if (from < 1) from = 1;
        if (pageSize < 1) pageSize = 10;
        List<User> users = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            users = session.createQuery("from User u", User.class)
                    .setFirstResult(from - 1)
                    .setMaxResults(pageSize).list();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public Long save(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return (Long) session.save(user);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            int i = session.createQuery("update User set name = :nameParam, email = :emailParam" +
                            ", dateOfBirth = :dateOfBirthParam, grade = :gradeParam, isAlive = :isAliveParam" +
                            " where id = :idParam").setParameter("nameParam", user.getName())
                    .setParameter("emailParam", user.getEmail())
                    .setParameter("dateOfBirthParam", user.getDateOfBirth())
                    .setParameter("gradeParam", user.getGrade())
                    .setParameter("isAliveParam", user.getIsAlive())
                    .setParameter("idParam", user.getId())
                    .executeUpdate();
            System.out.println(i);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.delete(user);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}