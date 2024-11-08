package com.project.dao;

import com.project.entity.Players;
import com.project.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class PlayerDao {

    private static final PlayerDao INSTANCE = new PlayerDao();
//    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private PlayerDao() {}

    public static PlayerDao getInstance() {
        return INSTANCE;
    }

    public List<Players> findAll() {
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            return session.createQuery("from Players", Players.class).list();
        }
    }
}
