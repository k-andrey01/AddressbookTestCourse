package org.example.tests;

import org.example.model.ContactData;
import org.example.model.Contacts;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class ContactModificationTest extends TestBase{

    @Before
    public void ensurePreconditions() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isWhereAContact()) {
            app.getContactHelper().createContact(new ContactData().withFirstName("test1"), true);
        }
    }

    @Test
    public void contactModificationTest(){
        Contacts before = app.getContactHelper().getAllContacts();

        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Mod");
        app.getContactHelper().modifyContact(contact);
        assertThat(app.getContactHelper().getContactCount(), equalTo(before.size()));

        Contacts after = app.getContactHelper().getAllContacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
