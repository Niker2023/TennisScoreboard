package com.project.dao;

import com.project.entity.Matches;
import com.project.util.HibernateUtil;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

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
        session.getTransaction().commit();
    }
}
