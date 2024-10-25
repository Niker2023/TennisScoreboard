
package com.project;

import com.project.entity.Matches;
import com.project.entity.Players;
import com.project.entity.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {

        String sql = "select * from PLAYERS where player";

        Players players1 = Players.builder()
                .player("Maradonna")
                .build();

        Players players2 = Players.builder()
                .player("Mary")
                .build();

        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
             var session = sessionFactory.openSession()) {

            session.beginTransaction();

            session.persist(players1);
            session.persist(players2);

            session.getTransaction().commit();
        }

        var matches1 = Matches.builder()
                .player1(players1)
                .player2(players2)
                .winner(players1)
                .build();

        log.info(matches1.toString());
        System.out.println(matches1.toString());

        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

//            session.refresh(players1);
//            session.refresh(players2);


            session.persist(matches1);

            session.getTransaction().commit();
        }
    }
}
