package org.example.genarators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import org.example.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;
    @Parameter(names = "-f", description = "Target file")
    public String file;
    @Parameter(names = "-d", description = "Data Format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        } else if (format.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)){
            writer.write(json);
        }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactData.class);
        String xml = xStream.toXML(contacts);
        try (Writer writer = new FileWriter(file)){
            writer.write(xml);
        }
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            for (ContactData contact : contacts) {
                writer.write(String.format("%s;%s;%s\n", contact.getFirstName(), contact.getMiddleName(), contact.getLastName(),
                        contact.getAddress(), contact.getAddress2(), contact.getEmail(), contact.getEmail2(), contact.getEmail3(),
                        contact.getMobilePhone(), contact.getWorkPhone(), contact.getHomePhone()));
            }
        }
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> groups = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++){
            groups.add(new ContactData().withFirstName(String.format("Name%s", i)).withMiddleName(String.format("MiddleName%s", i))
                    .withLastName(String.format("Surname%s", i)).withAddress(String.format("Address%s", i))
                    .withAddress2(String.format("Address2%s", i)).withEmail(String.format("mail%s@mail.ru", i))
                    .withEmail2(String.format("mail2%s@mail.ru", i)).withEmail3(String.format("mail3%s@mail.ru", i))
                    .withMobilePhone(String.format("phone%s", i)).withWorkPhone(String.format("work%s", i))
                    .withHomePhone(String.format("home%s", i)));
        }
        return groups;
    }
}
