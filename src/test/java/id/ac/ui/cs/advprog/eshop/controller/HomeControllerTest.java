package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeControllerTest {

    private HomePageController homePageController;

    @BeforeEach
    void setUp() {
        homePageController = new HomePageController();
    }

    @Test
    void testHome() {
        String viewName = homePageController.homePage();
        assertEquals("HomePage", viewName);
    }
}