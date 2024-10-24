
package com.project;

import com.project.entity.Matches;
import com.project.entity.Players;
import com.project.entity.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class HibernateRunner {

    public static void main(String[] args) {

        Players players1 = Players.builder()
                .player("Maradonna")
                .build();

        Players players2 = Players.builder()
                .player("Mary")
                .build();

        var matches1 = Matches.builder()
                .player1(players1)
                .player2(players2)
                .winner(players1)
                .build();

        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
             var session = sessionFactory.openSession()) {

            session.beginTransaction();


            session.save(players1);
            session.save(players2);

//            session.flush();

            session.persist(matches1);
//            var players = session.get(Players.class, 1);

            session.getTransaction().commit();
//            System.out.println(players);
        }
    }
}
