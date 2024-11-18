package com.project.dao;

import com.project.entity.Players;
import com.project.util.HibernateUtil;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

@Slf4j
@NoArgsConstructor
public class PlayerDao {

    private static final PlayerDao INSTANCE = new PlayerDao();

    public static PlayerDao getInstance() {
        return INSTANCE;
    }

    public void save(Players player) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(player);
        log.info("save player : {}", player);
        session.getTransaction().commit();
    }
}
