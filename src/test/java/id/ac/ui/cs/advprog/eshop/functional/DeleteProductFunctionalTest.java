package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class DeleteProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseURL:http://localhost}")
    private String testBaseUrl;
    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void createAndDeleteProduct_isCorrect(ChromeDriver driver) {
        driver.get(baseUrl + "/product/create");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Create a new product
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("productName")));
        WebElement quantityInput = driver.findElement(By.name("productQuantity"));
        WebElement submitButton = driver.findElement(By.tagName("button"));

        nameInput.sendKeys("Aku suka adpro");
        quantityInput.sendKeys("56");
        submitButton.click();

        driver.get(baseUrl + "/product/list");

        // Find delete button within the correct product row
        WebElement deleteForm = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[contains(text(),'Aku suka adpro')]/following-sibling::td/form")
        ));
        WebElement deleteButton = deleteForm.findElement(By.tagName("button"));

        // Click delete button and wait for the confirmation alert
        deleteButton.click();
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert deleteConfirmationAlert = driver.switchTo().alert();
            deleteConfirmationAlert.accept();
        } catch (NoAlertPresentException e) {
            System.out.println("No confirmation alert appeared, continuing test...");
        }

        // Wait for the row to be removed
        wait.until(ExpectedConditions.invisibilityOf(deleteForm));

        // Verify that the product was deleted
        WebElement productTable = driver.findElement(By.tagName("table"));
        String pageSource = productTable.getText();

        assertFalse(pageSource.contains("Aku suka adpro"), "Product name still appears in the table.");
        assertFalse(pageSource.contains("56"), "Product quantity still appears in the table.");
    }
}
