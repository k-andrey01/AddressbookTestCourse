package org.example.tests;

import org.example.model.ContactData;
import org.example.model.GroupData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class HbConnectionTest {

    private SessionFactory sessionFactory;

    @Test
    public void testHbConnection(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Date startDate = Date.valueOf("0001-01-01");
        Date endDate = Date.valueOf(LocalDate.now().plusDays(1));

        // Используем запрос с BETWEEN для проверки диапазона дат
        List<ContactData> result = session.createQuery("from ContactData where deprecated between :startDate and :endDate", ContactData.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .list();
        for (ContactData contact: result){
            System.out.println(contact);
        }
        session.getTransaction().commit();
        session.close();
    }

    @BeforeClass
    protected void setUp() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
