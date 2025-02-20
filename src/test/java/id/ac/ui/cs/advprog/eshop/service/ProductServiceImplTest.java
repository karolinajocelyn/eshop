package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
        product.setProductId("123");
        product.setProductName("Test Product");
        product.setProductQuantity(10);
    }

    @Test
    void testCreateProduct() {
        when(productRepository.create(product)).thenReturn(product);

        Product created = productService.create(product);

        verify(productRepository).create(product);
        assertNotNull(created);
        assertEquals(product.getProductId(), created.getProductId());
        assertEquals(product.getProductName(), created.getProductName());
        assertEquals(product.getProductQuantity(), created.getProductQuantity());
    }

    @Test
    void testFindAllProducts() {
        Product product2 = new Product();
        product2.setProductId("456");
        product2.setProductName("Another Product");
        product2.setProductQuantity(20);

        Iterator<Product> iterator = Arrays.asList(product, product2).iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> products = productService.findAll();

        verify(productRepository).findAll();
        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("123", products.get(0).getProductId());
        assertEquals("456", products.get(1).getProductId());
    }

    @Test
    void testFindProductById_Found() {
        when(productRepository.findById("123")).thenReturn(product);

        Product found = productService.findById("123");

        verify(productRepository).findById("123");
        assertNotNull(found);
        assertEquals(product.getProductId(), found.getProductId());
    }

    @Test
    void testFindProductById_NotFound() {
        when(productRepository.findById("999")).thenReturn(null);

        Product found = productService.findById("999");

        verify(productRepository).findById("999");
        assertNull(found);
    }

    @Test
    void testEditProduct_Success() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId("123");
        updatedProduct.setProductName("Updated Product");
        updatedProduct.setProductQuantity(50);

        when(productRepository.findById("123")).thenReturn(product);

        productService.edit(updatedProduct);

        verify(productRepository).findById("123");
        verify(productRepository).edit(product);

        assertEquals("Updated Product", product.getProductName());
        assertEquals(50, product.getProductQuantity());
    }

    @Test
    void testEditProduct_NotFound() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId("999");
        updatedProduct.setProductName("Nonexistent Product");
        updatedProduct.setProductQuantity(50);

        when(productRepository.findById("999")).thenReturn(null);

        productService.edit(updatedProduct);

        verify(productRepository).findById("999");
        verify(productRepository, never()).edit(any());
    }

    @Test
    void testDeleteProduct() {
        productService.delete("123");

        verify(productRepository).delete("123");
    }
}
