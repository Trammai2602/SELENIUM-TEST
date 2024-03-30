package PageObjects;

import Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

public class BookTicketPage extends GeneralPage{
    private final By selectDDate=By.xpath("//select[@name='Date']");
    private final By selectDFrom=By.xpath("//select[@name='DepartStation']");
    private final By selectArriveAt=By.xpath("//select[@name='ArriveStation']");
    private final By selectSeatType=By.xpath("//select[@name='SeatType']");
    private final By selectTicketAmount=By.xpath("//select[@name='TicketAmount']");
    private final By btnBookTicket=By.xpath("//input[@value='Book ticket']");
    private final By lblBookTicketSuccessMsg=By.xpath("//h1[contains(text(), 'Ticket booked successfully!')]");

    //    protected WebElement getselectDDate(){
//        return Constant.WEBDRIVER.findElement(selectDDate);
//    }
    protected WebElement getselectDFrom(){
        return Constant.WEBDRIVER.findElement(selectDFrom);
    }
    protected WebElement getselectArriveAt(){
        return Constant.WEBDRIVER.findElement(selectArriveAt);
    }
//    protected WebElement getselectSeatType(){
//        return Constant.WEBDRIVER.findElement(selectSeatType);
//    }
//    protected WebElement getselectTicketAmount(){
//        return Constant.WEBDRIVER.findElement(selectTicketAmount);
//    }

    public String getDepartValue(){
        return this.getselectDFrom().getText();
    }
    public String getArriveValue(){
        return this.getselectArriveAt().getText();
    }
    public String getSelectDepartFrom() {
        Select departFromDropdown = new Select(Constant.WEBDRIVER.findElement(selectDFrom));
        return departFromDropdown.getFirstSelectedOption().getText();
    }

    // Method to get the selected value of "Arrive at" dropdown
    public String getSelectArriveAt() {
        Select arriveAtDropdown = new Select(Constant.WEBDRIVER.findElement(selectArriveAt));
        return arriveAtDropdown.getFirstSelectedOption().getText();
    }
    protected WebElement getBtnBookTicket(){
        return Constant.WEBDRIVER.findElement(btnBookTicket);
    }
    protected WebElement getlblBookTicketSuccessMsg(){
        return Constant.WEBDRIVER.findElement(lblBookTicketSuccessMsg);}
    private void selectOptionInDropdown(By dropdownLocator, String option) {
        Select dropdown = new Select(Constant.WEBDRIVER.findElement(dropdownLocator));
        dropdown.selectByVisibleText(option);
    }
    public void selectDepartDate( int daysToAdd) {
        System.out.println("Calling selectDepartDateFromList method...");
        // Lấy ngày hiện tại
        LocalDate today = LocalDate.now();

        // Thêm số ngày cần thiết
        LocalDate desiredDate = today.plusDays(daysToAdd);
        if (daysToAdd >= 3 && daysToAdd <= 30) {

            String formattedDate = desiredDate.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            selectDepartDate(formattedDate);
            System.out.println("selectDepartDateFromList method executed successfully.");
        } else {
            System.out.println("We only have tickets for 3-30 days ahead.");
            System.out.println("Please come to the station to buy a ticket if you need to depart within 2 days.");
        }
    }
    public String getBookTicketSuccessMess(){
        return getlblBookTicketSuccessMsg().getText();
    }
    public HomePage BookTicket( String departFrom, String arriveAt, String seatType, String ticketAmount){
        //Submit login credentials
//        selectOptionInDropdown(selectDDate, departDate);
        selectOptionInDropdown(selectDFrom, departFrom);
        selectOptionInDropdown(selectArriveAt, arriveAt);
        selectOptionInDropdown(selectSeatType, seatType);
        selectOptionInDropdown(selectTicketAmount, ticketAmount);
        // Scroll to the book ticket button
        WebElement bookTicketButton = Constant.WEBDRIVER.findElement(btnBookTicket);
        Actions actions = new Actions(Constant.WEBDRIVER);
        actions.moveToElement(bookTicketButton).perform();
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", bookTicketButton);

        // Click on the book ticket button
        bookTicketButton.click();
        //Land on Home Page
        return new HomePage();
    }
    public void scrollDownToBookTicketButton() {
        WebElement bookTicketButton = Constant.WEBDRIVER.findElement(btnBookTicket);

        // Scroll to the "Book Ticket" button
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", bookTicketButton);
    }


    public void selectDepartDate(String departDate) {
        Select dropdown = new Select(Constant.WEBDRIVER.findElement(selectDDate));
        dropdown.selectByVisibleText(departDate);
    }
}
