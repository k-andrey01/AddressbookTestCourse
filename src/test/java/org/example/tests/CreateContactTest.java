package org.example.tests;

import org.example.model.ContactData;
import org.junit.Test;
import org.testng.Assert;

import java.util.Comparator;
import java.util.List;

public class CreateContactTest extends TestBase{

    @Test
    public void testContactCreation(){
        app.getNavigationHelper().goToHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();

        app.getNavigationHelper().goToContactPage();
        ContactData contact = new ContactData("user1", "test", "user", "Казань Гвардейская 14 127", "кre@we.re", "89324511903", "group3");
        app.getContactHelper().createContact(contact, true);

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        Comparator<? super ContactData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
        before.add(contact);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
