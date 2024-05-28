package org.example.tests;

import org.example.model.ContactData;
import org.junit.Test;

public class DeleteContactTest extends TestBase{

    @Test
    public void testDeleteGroup(){
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isWhereAContact()){
            app.getContactHelper().createContact(new ContactData("user1", "test", "user", "Казань Гвардейская 14 127", "кre@we.re", "89324511903", "group3"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().closeAlertDeletion();
    }
}
