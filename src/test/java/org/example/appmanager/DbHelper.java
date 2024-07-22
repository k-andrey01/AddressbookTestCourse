package org.example.appmanager;

import org.example.model.ContactData;
import org.example.model.Contacts;
import org.example.model.GroupData;
import org.example.model.Groups;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Groups getGroups(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Date startDate = Date.valueOf("0001-01-01");
        Date endDate = Date.valueOf(LocalDate.now().plusDays(1));

        List<GroupData> result = session.createQuery("from GroupData").list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Contacts getContacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Date startDate = Date.valueOf("0001-01-01");
        Date endDate = Date.valueOf(LocalDate.now().plusDays(1));

        List<ContactData> result = session.createQuery("from ContactData where deprecated not between :startDate and :endDate", ContactData.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .list();

        session.getTransaction().commit();
        session.close();

        return new Contacts(result);
    }
}
