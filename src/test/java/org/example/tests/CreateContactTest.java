package org.example.tests;

import org.example.model.ContactData;
import org.junit.Test;

public class CreateContactTest extends TestBase{

    @Test
    public void testContactCreation(){
        app.getNavigationHelper().goToContactPage();
        app.getContactHelper().fillContactForm(new ContactData("user1", "test", "user", "Казань Гвардейская 14 127", "кre@we.re", "89324511903"));
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();
    }
}
