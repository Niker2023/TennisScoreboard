package com.project.dao;

import com.project.entity.Players;
import com.project.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class PlayerDao {

    private static final PlayerDao INSTANCE = new PlayerDao();

    private PlayerDao() {}

    public static PlayerDao getInstance() {
        return INSTANCE;
    }

    public void save(Players player) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(player);
        session.getTransaction().commit();
    }
}
