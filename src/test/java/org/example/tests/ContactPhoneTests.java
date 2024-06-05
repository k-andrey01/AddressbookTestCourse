package org.example.tests;

import org.example.model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactPhoneTests extends TestBase {

    @Test
    public void testContactPhones() {
        app.getNavigationHelper().goToHomePage();
        ContactData contact = app.getContactHelper().getAllContacts().iterator().next();
        ContactData contactInfoFromEditForm = app.getContactHelper().infoFromEditForm(contact);
    }
}
