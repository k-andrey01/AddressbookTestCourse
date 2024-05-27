package org.example.appmanager;

import org.example.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper extends HelperBase{
    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void fillContactForm(ContactData contactData){
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getEmail());
        type(By.name("mobile"), contactData.getMobilePhone());
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void deleteSelectedContacts(){
        click(By.cssSelector(".left:nth-child(8) > input"));
    }

    public void closeAlertDeletion(){
        driver.switchTo().alert().accept();
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }
}
