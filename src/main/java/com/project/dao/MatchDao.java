package com.project.dao;

import com.project.entity.Players;
import com.project.entity.Matches;
import com.project.exception.DaoException;
import com.project.util.HibernateUtil;
import jakarta.persistence.PersistenceException;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@NoArgsConstructor
public class MatchDao {

    public void save(Matches match) {
        Transaction tx = null;

        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            session.persist(match);
            log.info("save match : {}", match);
            tx.commit();
        } catch (PersistenceException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new DaoException("Не удалось сохранить матч: " + match.toString());
        }
    }

    public List<Matches> getMatchesByPlayer(Players player, Integer currentPage, Integer limitPerPage) {
        List<Matches> matchesList = new ArrayList<>();
        Transaction tx = null;

        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query<Matches> query = session.createQuery("from Matches where player1 = :player or player2 = :player", Matches.class);
            query.setFirstResult((currentPage - 1) * limitPerPage);
            query.setMaxResults(limitPerPage);
            matchesList = query.setParameter("player", player).getResultList();
            tx.commit();
        } catch (PersistenceException e) {
            if (tx != null) {
                tx.rollback();
            }
            log.error("getMatchesByPlayer : {}", e.getMessage());
        }

        return matchesList;
    }

    public List<Matches> getMatches(Integer currentPage, Integer limitPerPage) {
        List<Matches> matchesList = new ArrayList<>();
        Transaction tx = null;

        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query<Matches> query = session.createQuery("from Matches", Matches.class);
            query.setFirstResult((currentPage - 1) * limitPerPage);
            query.setMaxResults(limitPerPage);
            matchesList = query.getResultList();
            tx.commit();
        } catch (PersistenceException e) {
            if (tx != null) {
                tx.rollback();
            }
            log.error("getMatches : {}", e.getMessage());
        }

        return matchesList;
    }

    public int getMatchesCount() {
        Transaction tx = null;
        Long count = 0L;

        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query<Long> query = session.createQuery("select count(*) from Matches", Long.class);
            count = query.getSingleResult();
            tx.commit();
        } catch (PersistenceException e) {
            if (tx != null) {
                tx.rollback();
            }
            log.error("getMatchesCount : {}", e.getMessage());
        }
        return Math.toIntExact(count);
    }

    public int getMatchesCountByPlayer(Players player) {
        Transaction tx = null;
        Long count = 0L;

        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query<Long> query = session.createQuery("select count(*) from Matches where player1 = :player or player2 = :player", Long.class);
            count = query.setParameter("player", player).getSingleResult();
            tx.commit();
        } catch (PersistenceException e) {
            if (tx != null) {
                tx.rollback();
            }
            log.error("getMatchesCountByPlayer : {}", e.getMessage());
        }

        return Math.toIntExact(count);
    }
}