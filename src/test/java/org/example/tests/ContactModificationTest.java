package org.example.tests;

import org.example.model.ContactData;
import org.example.model.GroupData;
import org.junit.Test;
import org.testng.Assert;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase{

    @Test
    public void contactModificationTest(){
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isWhereAContact()){
            app.getContactHelper().createContact(new ContactData("user1", "test", "user", "Казань Гвардейская 14 127", "кre@we.re", "89324511903", "group3"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();

        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().initContactModification();

        ContactData contact = new ContactData("Mod", "Mod", "Mod", "Казань Гвардейская 14 127", "fd@g.m", "89033010903", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();

        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
