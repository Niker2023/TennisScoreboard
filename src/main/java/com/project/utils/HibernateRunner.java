
package com.project.utils;

import com.project.entity.Players;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateRunner {

    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            Players player = Players.builder()
                    .player("Maradonna")
                    .build();

            session.persist(player);

            session.getTransaction().commit();
            System.out.println("OK");
        }
    }
}
