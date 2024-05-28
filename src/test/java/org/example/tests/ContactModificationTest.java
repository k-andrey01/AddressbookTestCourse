package org.example.tests;

import org.example.model.ContactData;
import org.example.model.GroupData;
import org.junit.Test;

public class ContactModificationTest extends TestBase{

    @Test
    public void contactModificationTest(){
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isWhereAContact()){
            app.getContactHelper().createContact(new ContactData("user1", "test", "user", "Казань Гвардейская 14 127", "кre@we.re", "89324511903", "group3"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Mod", "Mod", "Mod", "Казань Гвардейская 14 127", "fd@g.m", "89033010903", null), false);
        app.getContactHelper().submitContactModification();
    }
}
