package com.project.dao;

import com.project.entity.Players;
import com.project.util.HibernateUtil;
import jakarta.persistence.PersistenceException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Query;

@Slf4j
@NoArgsConstructor
public class PlayerDao {

    public void save(Players player) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(player);
        log.info("save player : {}", player);
        session.getTransaction().commit();
    }


    public Players getPlayerById(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Players player = session.get(Players.class, id);
        session.getTransaction().commit();
        return player;
    }


    public Players getPlayerByName(String name) throws PersistenceException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Players> query = session.createQuery("from Players where name = :name", Players.class);
        Players player = query.setParameter("name", name).getSingleResult();
        session.getTransaction().commit();
        return player;
    }
}
