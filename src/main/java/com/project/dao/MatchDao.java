package com.project.dao;

import com.project.entity.Matches;
import com.project.entity.Players;
import com.project.util.HibernateUtil;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.Query;

import java.util.List;

@Slf4j
@NoArgsConstructor
public class MatchDao {


    public static final MatchDao INSTANCE = new MatchDao();


    public static MatchDao getInstance() {
        return INSTANCE;
    }


    public void save(Matches match) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(match);
        log.info("save match : {}", match);
        session.getTransaction().commit();
    }


    public List<Matches> getMatches() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Matches> query = session.createQuery("from Matches", Matches.class);
        List<Matches> matches = query.getResultList();
        session.getTransaction().commit();
        return matches;
    }


    public List<Matches> getMatchesByPlayer(Players player) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Matches> query = session.createQuery("from Matches where player1 = :player or player2 = :player", Matches.class);
        List<Matches> matchesList = query.setParameter("player", player).getResultList();
        session.getTransaction().commit();
        return matchesList;
    }


    public List<Matches> getMatchesForPagination(Integer page, Integer limit) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Matches> query = session.createQuery("from Matches", Matches.class);
        query.setFirstResult((page - 1) * limit);
        query.setMaxResults(limit);
        List<Matches> matchesList = query.getResultList();
        session.getTransaction().commit();
        return matchesList;
    }


    public Long getMatchesCount() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Long> query = session.createQuery("select count(*) from Matches", Long.class);
        Long count = query.getSingleResult();
        session.getTransaction().commit();
        return count;
    }
}
