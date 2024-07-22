package org.example.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.example.model.ContactData;
import org.example.model.Contacts;
import org.example.model.GroupData;
import org.example.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateContactTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.getDbHelper().getGroups().size() == 0){
            app.getNavigationHelper().goToGroupPage();
            app.getGroupHelper().createGroup(new GroupData().withGroupName("test1"));
        }
        app.getNavigationHelper().goToHomePage();
    }

    @DataProvider
    public Iterator<Object[]> validContactsFromXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))){
            String xml = "";
            String line = reader.readLine();
            while (line != null){
                xml += line;
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.addPermission(AnyTypePermission.ANY);
            xStream.processAnnotations(ContactData.class);
            List<ContactData> groups = (List<ContactData>) xStream.fromXML(xml);
            return groups.stream().map(g -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))){
            String json = "";
            String line = reader.readLine();
            while (line != null){
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> groups = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
            return groups.stream().map(g -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validContactsFromJson")
    public void testContactCreation(ContactData contact) {
        Groups groups = app.getDbHelper().getGroups();
        Contacts before = app.getDbHelper().getContacts();

        app.getNavigationHelper().goToHomePage();
        File photo = new File("src/test/resources/gorilla.jpg");
        contact = contact.withPhoto(photo);
        contact = contact.inGroup(groups.iterator().next());

        app.getNavigationHelper().goToContactPage();
        app.getContactHelper().createContact(contact, true);
        assertThat(app.getContactHelper().getContactCount(), equalTo(before.size() + 1));

        Contacts after = app.getDbHelper().getContacts();
        System.out.println(after);
        System.out.println("=================================");
        System.out.println(before.withAdded(contact.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt())));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt()))));
    }
}
