package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class EditProductFunctionalTest {
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
    void createAndEditProduct_isCorrect(ChromeDriver driver) {
        driver.get(baseUrl + "/product/create");

        WebElement nameInput = driver.findElement(By.name("productName"));
        WebElement quantityInput = driver.findElement(By.name("productQuantity"));
        WebElement submitButton = driver.findElement(By.tagName("button"));

        nameInput.sendKeys("Sampo Cap Ing");
        quantityInput.sendKeys("50");
        submitButton.click();

        driver.get(baseUrl + "/product/list");

        WebElement editLink = driver.findElement(By.linkText("Edit"));
        editLink.click();

        nameInput = driver.findElement(By.name("productName"));
        quantityInput = driver.findElement(By.name("productQuantity"));
        WebElement updateButton = driver.findElement(By.tagName("button"));

        nameInput.clear();
        nameInput.sendKeys("Sampo Cap Sabun");
        quantityInput.clear();
        quantityInput.sendKeys("1000");
        updateButton.click();

        WebElement productTable = driver.findElement(By.tagName("table"));
        String pageSource = productTable.getText();

        assertTrue(pageSource.contains("Sampo Cap Sabun"));
        assertTrue(pageSource.contains("1000"));
    }
}