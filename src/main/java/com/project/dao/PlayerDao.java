package com.project.dao;

import com.project.entity.Players;
import com.project.exception.HibernateException;
import com.project.util.HibernateUtil;
import jakarta.persistence.PersistenceException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Slf4j
@NoArgsConstructor
public class PlayerDao {

    public void save(Players player) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.persist(player);
            log.info("save player : {}", player);
            transaction.commit();
        } catch (PersistenceException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateException("Can`t save player : " + player);
        }
    }

    public Players getPlayerById(int id) {
        Transaction transaction = null;
        Players player;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            player = session.get(Players.class, id);
            transaction.commit();
        } catch (PersistenceException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateException("Can`t get player : " + id);
        }
        return player;
    }

    public Players getPlayerByName(String name) {
        Transaction transaction = null;
        Players player;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query<Players> query = session.createQuery("from Players where name = :name", Players.class);
            player = query.setParameter("name", name).getSingleResult();
            transaction.commit();
        } catch (PersistenceException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateException("Can`t get player : " + name);
        }
        return player;
    }
}
