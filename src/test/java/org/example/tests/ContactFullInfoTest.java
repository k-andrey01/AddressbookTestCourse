package org.example.tests;

import org.example.model.ContactData;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactFullInfoTest extends TestBase{

    @Test
    public void testContactFullInfo() {
        app.getNavigationHelper().goToHomePage();
        ContactData contact = app.getContactHelper().getAllContacts().iterator().next();
        ContactData contactInfoFromFullForm = app.getContactHelper().infoFromFullForm(contact);
        ContactData contactInfoFromEditForm = app.getContactHelper().infoFromEditForm(contact);

        assertThat(mergeInfo(contactInfoFromEditForm), equalTo(cleanInfo(contactInfoFromFullForm.getFullInfo())));
    }

    private String mergeInfo(ContactData contact) {
        return Arrays.asList(contact.getFirstName(), contact.getLastName(),
                        contact.getAddress(), contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(),
                        contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter(s -> !s.equals(" "))
                .map(Object::toString)
                .collect(Collectors.joining(" "));
    }

    private String cleanInfo(String info){
        return info.replaceAll("H: ", "").replaceAll("M: ", "")
                .replaceAll("W: ", "").replaceAll("\n", " ").replaceAll(" {2}", " ");
    }
}
