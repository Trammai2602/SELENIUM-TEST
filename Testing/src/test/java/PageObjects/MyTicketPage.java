package PageObjects;

import Constant.Constant;
import org.openqa.selenium.*;

import java.util.List;

public class MyTicketPage extends GeneralPage{
    private final By mytable = By.xpath("//table[@class='MyTable']");

    public void cancelTicket(int ticketNumber) {
        // Xác định phần tử tr (row) chứa thông tin vé cần hủy dựa trên số thứ tự của vé
        WebElement ticketRow = Constant.WEBDRIVER.findElement(By.xpath("//tr[./td[contains(text(), '" + ticketNumber + "')]]"));

        // Click vào nút "Cancel" của vé
        WebElement cancelButton = ticketRow.findElement(By.xpath(".//input[@value='Cancel']"));
        cancelButton.click();
    }
    public void cancelTicketByDepartArrive(String departStation, String arriveStation) {
        String xpathExpression = String.format("//td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td/input[@value='Delete']", departStation, arriveStation);
        WebElement cancelButton = Constant.WEBDRIVER.findElement(By.xpath(xpathExpression));
        cancelButton.click();
    }

    public boolean isTicketCancelled(String departStation, String arriveStation) {
        String xpathExpression = String.format("//td[text()='%s']/following-sibling::td[text()='%s']", departStation, arriveStation);
        return Constant.WEBDRIVER.findElements(By.xpath(xpathExpression)).size() > 0;
    }
    public void scrollDownToMyTicketButton() {
        WebElement myTableElement = Constant.WEBDRIVER.findElement(mytable);

        // Scroll to the "My Ticket" button
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", myTableElement);
    }

}
