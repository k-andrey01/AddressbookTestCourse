package org.example.tests;

import org.example.model.ContactData;
import org.example.model.Contacts;
import org.example.model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class ContactModificationTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().goToHomePage();
        if (app.getDbHelper().getContacts().size() == 0){
            app.getContactHelper().createContact(new ContactData().withFirstName("test1"), true);
        }
    }

    @Test
    public void contactModificationTest(){
        Contacts before = app.getDbHelper().getContacts();

        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Mod");
        File photo = new File("src/test/resources/gorilla.jpg");
        contact = contact.withPhoto(photo);
        app.getContactHelper().modifyContact(contact);
        assertThat(app.getContactHelper().getContactCount(), equalTo(before.size()));

        Contacts after = app.getDbHelper().getContacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
