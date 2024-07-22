package org.example.tests;

import org.example.model.ContactData;
import org.example.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class DeleteContactTest extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {
        if (app.getDbHelper().getContacts().size() == 0){
            app.getNavigationHelper().goToHomePage();
            app.getContactHelper().createContact(new ContactData().withFirstName("test1"), true);
        }
    }

    @Test
    public void testDeleteGroup() {
        Contacts before = app.getDbHelper().getContacts();

        ContactData deletedContact = before.iterator().next();
        app.getContactHelper().deleteContact(deletedContact);
        assertThat(app.getContactHelper().getContactCount(), equalTo(before.size() - 1));

        Contacts after = app.getDbHelper().getContacts();
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
