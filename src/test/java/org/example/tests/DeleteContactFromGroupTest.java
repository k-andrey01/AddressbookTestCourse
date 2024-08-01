package org.example.tests;

import org.example.model.ContactData;
import org.example.model.Contacts;
import org.example.model.GroupData;
import org.example.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroupTest extends TestBase {

    private GroupData groupForContactDeleting;

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

        Groups groups = app.getDbHelper().getGroups();
        groupForContactDeleting = groups.iterator().next();
        Contacts contactsInGroup = app.getDbHelper().getContactsViaGroup(groupForContactDeleting.getId());

        if (contactsInGroup.size() == 0){
            Contacts contacts = app.getDbHelper().getContacts();
            ContactData addingToGroupContact = contacts.iterator().next();
            app.getContactHelper().addContactToGroup(addingToGroupContact, groupForContactDeleting);
        }
    }

    @Test
    public void testDeletingContactFromGroup(){
        Contacts before = app.getDbHelper().getContactsViaGroup(groupForContactDeleting.getId());

        ContactData contactForDeletionFromGroup = before.iterator().next();
        ContactData contact = contactForDeletionFromGroup.delFromGroup(groupForContactDeleting);
        app.getContactHelper().delContactFromGroup(contactForDeletionFromGroup, groupForContactDeleting);

        Contacts after = app.getDbHelper().getContactsViaGroup(groupForContactDeleting.getId());
        assertThat(after.size(), equalTo(before.size() - 1));

        assertThat(after, equalTo(before.without(contactForDeletionFromGroup)));

    }
}
