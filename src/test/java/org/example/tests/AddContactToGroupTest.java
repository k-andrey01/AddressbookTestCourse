package org.example.tests;

import org.example.model.ContactData;
import org.example.model.Contacts;
import org.example.model.GroupData;
import org.example.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.getDbHelper().getContacts().size() == 0){
            app.getNavigationHelper().goToHomePage();
            app.getContactHelper().createContact(new ContactData().withFirstName("test1"), true);
        }
        if (app.getDbHelper().getGroups().size() == 0){
            app.getNavigationHelper().goToGroupPage();
            app.getGroupHelper().createGroup(new GroupData().withGroupName("test11we"));
        }
    }

    @Test
    public void testAddingContactToGroup(){
        Contacts before = app.getDbHelper().getContacts();
        Groups groups = app.getDbHelper().getGroups();

        ContactData addingToGroupContact = before.iterator().next();
        GroupData groupToAdding = groups.iterator().next();
        ContactData contact = addingToGroupContact.inGroup(groupToAdding);
        app.getContactHelper().addContactToGroup(addingToGroupContact, groupToAdding);
        Contacts after = app.getDbHelper().getContacts();
        assertThat(after.size(), equalTo(before.size()));
//        System.out.println(before.without(addingToGroupContact).withAdded(contact));
//        System.out.println("==================");
//        System.out.println(after);

        assertThat(after, equalTo(before.without(addingToGroupContact).withAdded(contact)));
    }
}
