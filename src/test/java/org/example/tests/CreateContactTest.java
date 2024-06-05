package org.example.tests;

import org.example.model.ContactData;
import org.example.model.Contacts;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateContactTest extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToHomePage();
        Contacts before = app.getContactHelper().getAllContacts();

        ContactData contact = new ContactData().withFirstName("user1").withLastName("user").withGroup("group1");
        app.getNavigationHelper().goToContactPage();
        app.getContactHelper().createContact(contact, true);
        assertThat(app.getContactHelper().getContactCount(), equalTo(before.size() + 1));

        Contacts after = app.getContactHelper().getAllContacts();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt()))));
    }
}
