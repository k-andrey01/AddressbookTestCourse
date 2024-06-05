package org.example.tests;

import org.example.model.GroupData;
import org.example.model.Groups;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class GroupModificationTest extends TestBase {

    @Before
    public void ensurePreconditions() {
        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isWhereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData().withGroupName("test1"));
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.getGroupHelper().getAllGroups();

        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId())
                .withGroupName("group3").withHeader("mod").withFooter("modr");
        app.getGroupHelper().modifyGroup(group);
        assertThat(app.getGroupHelper().getGroupCount(), equalTo(before.size()));

        Groups after = app.getGroupHelper().getAllGroups();
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));

    }
}