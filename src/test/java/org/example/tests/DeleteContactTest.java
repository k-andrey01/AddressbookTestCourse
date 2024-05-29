package org.example.tests;

import org.example.model.ContactData;
import org.junit.Test;
import org.testng.Assert;

import java.util.List;

public class DeleteContactTest extends TestBase{

    @Test
    public void testDeleteGroup(){
        app.getNavigationHelper().goToHomePage();

        if (!app.getContactHelper().isWhereAContact()){
            app.getContactHelper().createContact(new ContactData("user1", "test", "user", "Казань Гвардейская 14 127", "кre@we.re", "89324511903", "group3"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();

        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().closeAlertDeletion();

        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
