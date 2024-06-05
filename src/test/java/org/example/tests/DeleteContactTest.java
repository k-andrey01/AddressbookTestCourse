package org.example.tests;

import org.example.model.ContactData;
import org.example.model.Contacts;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class DeleteContactTest extends TestBase {


    @Before
    public void ensurePreconditions() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isWhereAContact()) {
            app.getContactHelper().createContact(new ContactData().withFirstName("test1"), true);
        }
    }

    @Test
    public void testDeleteGroup() {
        Contacts before = app.getContactHelper().getAllContacts();

        ContactData deletedContact = before.iterator().next();
        app.getContactHelper().deleteContact(deletedContact);
        assertThat(app.getContactHelper().getContactCount(), equalTo(before.size() - 1));

        Contacts after = app.getContactHelper().getAllContacts();
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
