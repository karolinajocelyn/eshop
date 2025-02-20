package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductName("Test Product");
        product.setProductQuantity(100);
    }

    @Test
    void testCreateProduct() {
        when(productRepository.create(product)).thenReturn(product);
        Product created = productService.create(product);
        verify(productRepository).create(product);
        assertNotNull(created);
    }

    @Test
    void testFindAllProducts() {
        Product product2 = new Product();
        product2.setProductName("Test Product 2");
        product2.setProductQuantity(200);

        Iterator<Product> iterator = Arrays.asList(product, product2).iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> products = productService.findAll();
        verify(productRepository).findAll();
        assertEquals(2, products.size());
    }

    @Test
    void testEditProduct() {
        product.setProductName("Updated Name");
        product.setProductQuantity(200);
        when(productRepository.findById(product.getProductId())).thenReturn(product);

        productService.edit(product);

        verify(productRepository).edit(product);
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).delete(product.getProductId());
        productService.delete(product.getProductId());
        verify(productRepository).delete(product.getProductId());
    }
}
