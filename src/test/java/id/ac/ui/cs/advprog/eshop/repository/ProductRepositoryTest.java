package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product foundProduct = productRepository.findById(product.getProductId());
        assertNotNull(foundProduct);
        assertEquals(product.getProductId(), foundProduct.getProductId());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditSuccessful() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product editedProduct = new Product();
        editedProduct.setProductId(product.getProductId());
        editedProduct.setProductName("Sampo Cap Pacil");
        editedProduct.setProductQuantity(200);
        productRepository.edit(editedProduct);

        Product foundProduct = productRepository.findById(product.getProductId());
        assertNotNull(foundProduct);
        assertEquals("Sampo Cap Pacil", foundProduct.getProductName());
        assertEquals(200, foundProduct.getProductQuantity());
    }

    @Test
    void testDeleteSuccessful() {
        Product product = new Product();
        product.setProductName("Makan");
        product.setProductQuantity(10);
        productRepository.create(product);

        productRepository.delete(product.getProductId());

        Product foundProduct = productRepository.findById(product.getProductId());
        assertNull(foundProduct);
    }
}