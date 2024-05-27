package org.example.tests;

import org.example.model.ContactData;
import org.junit.Test;

public class ContactModificationTest extends TestBase{

    @Test
    public void contactModificationTest(){
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Mod", "", "Mod", "", "", ""));
        app.getContactHelper().submitContactModification();
    }
}
