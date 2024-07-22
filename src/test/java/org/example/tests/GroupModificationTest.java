package org.example.tests;

import org.example.model.GroupData;
import org.example.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class GroupModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().goToGroupPage();
        if (app.getDbHelper().getGroups().size() == 0){
            app.getGroupHelper().createGroup(new GroupData().withGroupName("test1"));
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.getDbHelper().getGroups();

        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId())
                .withGroupName("group3").withHeader("mod").withFooter("modr");
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().modifyGroup(group);
        assertThat(app.getGroupHelper().getGroupCount(), equalTo(before.size()));

        Groups after = app.getDbHelper().getGroups();
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));

        verifyGroupListInUI();
    }
}