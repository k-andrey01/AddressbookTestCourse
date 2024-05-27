package org.example.tests;

import org.junit.Test;

public class DeleteContactTest extends TestBase{

    @Test
    public void testDeleteGroup(){
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().closeAlertDeletion();
    }
}
