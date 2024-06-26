package org.example.tests;

import org.example.model.ContactData;
import org.example.model.Contacts;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateContactTest extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToHomePage();
        Contacts before = app.getContactHelper().getAllContacts();

        File photo = new File("src/test/resources/gorilla.jpg");
        ContactData contact = new ContactData().withFirstName("user1").withLastName("user")
                .withHomePhone("8 932 045 21 32").withMobilePhone("8-922-960-02-38").withWorkPhone("8(800)5553535")
                .withEmail("ankaz@mail.ru").withEmail2("ya@stud.kpfu.ru").withEmail3("what@pi.ka")
                .withAddress("Казань Гвардейская 14 127").withPhoto(photo);
        app.getNavigationHelper().goToContactPage();
        app.getContactHelper().createContact(contact, true);
        assertThat(app.getContactHelper().getContactCount(), equalTo(before.size() + 1));

        Contacts after = app.getContactHelper().getAllContacts();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt()))));
    }
}
