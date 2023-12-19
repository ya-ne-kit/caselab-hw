package org.example.dao.impl;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.example.HibernateUtil;
import org.example.dao.Dao;
import org.example.models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoCriteria implements Dao<User> {
    Transaction transaction = null;

    @Override
    public Optional<User> findById(long id) {
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> critQuery = builder.createQuery(User.class);

            Root<User> root = critQuery.from(User.class);
            critQuery.select(root).where(builder.equal(root.get("id"), id));

            Query<User> query = session.createQuery(critQuery);
            user = query.getSingleResult();

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
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> critQuery = builder.createQuery(User.class);
            critQuery.select(critQuery.from(User.class));

            TypedQuery<User> query = session.createQuery(critQuery);
            query.setFirstResult(from - 1);
            query.setMaxResults(pageSize);
            users = query.getResultList();
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
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaUpdate<User> criteriaUpdate = builder.createCriteriaUpdate(User.class);
            Root<User> root = criteriaUpdate.from(User.class);
            criteriaUpdate.set(root.get("name"), user.getName())
                    .set(root.get("email"), user.getEmail())
                    .set(root.get("grade"), user.getGrade())
                    .set(root.get("isAlive"), user.getIsAlive())
                    .set(root.get("dateOfBirth"), user.getDateOfBirth())
                    .where(builder.equal(criteriaUpdate.getRoot().get("id"), user.getId()));
            session.createQuery(criteriaUpdate).executeUpdate();
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
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaDelete<User> criteriaDelete = builder.createCriteriaDelete(User.class);

            Root<User> root = criteriaDelete.from(User.class);
            criteriaDelete.where(builder.equal(root.get("id"), user.getId()));

            Query query = session.createQuery(criteriaDelete);
            query.executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}