package org.example.tests;

import org.example.model.ContactData;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTest extends TestBase{

    @Test
    public void testContactAddresses(){
        app.getNavigationHelper().goToHomePage();
        ContactData contact = app.getContactHelper().getAllContacts().iterator().next();
        ContactData contactInfoFromEditForm = app.getContactHelper().infoFromEditForm(contact);

        assertThat(contact.getAllAddresses(), equalTo(contactInfoFromEditForm.getAddress()));
    }
}
