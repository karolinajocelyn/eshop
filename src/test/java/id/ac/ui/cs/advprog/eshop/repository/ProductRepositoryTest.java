package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    private ProductRepository productRepository;
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();

        product1 = new Product();
        product1.setProductId("1");
        product1.setProductName("Product One");
        product1.setProductQuantity(10);

        product2 = new Product();
        product2.setProductId("2");
        product2.setProductName("Product Two");
        product2.setProductQuantity(20);

        productRepository.create(product1);
        productRepository.create(product2);
    }

    @Test
    void testCreateProduct() {
        Product product3 = new Product();
        product3.setProductId("3");
        product3.setProductName("Product Three");
        product3.setProductQuantity(30);

        Product createdProduct = productRepository.create(product3);

        assertNotNull(createdProduct);
        assertEquals("3", createdProduct.getProductId());
        assertEquals("Product Three", createdProduct.getProductName());
        assertEquals(30, createdProduct.getProductQuantity());
    }

    @Test
    void testFindById_ProductExists() {
        Product foundProduct = productRepository.findById("1");

        assertNotNull(foundProduct);
        assertEquals("1", foundProduct.getProductId());
    }

    @Test
    void testFindById_ProductDoesNotExist() {
        Product foundProduct = productRepository.findById("999");
        assertNull(foundProduct);
    }

    @Test
    void testFindAll_ProductsExist() {
        Iterator<Product> iterator = productRepository.findAll();
        assertTrue(iterator.hasNext());

        Product firstProduct = iterator.next();
        assertEquals("1", firstProduct.getProductId());

        Product secondProduct = iterator.next();
        assertEquals("2", secondProduct.getProductId());

        assertFalse(iterator.hasNext());
    }

    @Test
    void testFindAll_NoProducts() {
        productRepository.delete("1");
        productRepository.delete("2");

        Iterator<Product> iterator = productRepository.findAll();
        assertFalse(iterator.hasNext());
    }

    @Test
    void testEdit_ProductExists() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId("1");
        updatedProduct.setProductName("Updated Product");
        updatedProduct.setProductQuantity(50);

        productRepository.edit(updatedProduct);

        Product foundProduct = productRepository.findById("1");
        assertNotNull(foundProduct);
        assertEquals("Updated Product", foundProduct.getProductName());
        assertEquals(50, foundProduct.getProductQuantity());
    }

    @Test
    void testEdit_ProductDoesNotExist() {
        Product nonExistentProduct = new Product();
        nonExistentProduct.setProductId("999");
        nonExistentProduct.setProductName("Nonexistent Product");
        nonExistentProduct.setProductQuantity(50);

        // Simpan jumlah produk sebelum edit
        int beforeSize = countProducts();

        productRepository.edit(nonExistentProduct);

        // Pastikan jumlah produk tetap sama setelah mencoba mengedit produk yang tidak ada
        int afterSize = countProducts();
        assertEquals(beforeSize, afterSize);
    }

    @Test
    void testDelete_ProductExists() {
        productRepository.delete("1");

        assertNull(productRepository.findById("1"));
        assertNotNull(productRepository.findById("2"));
    }

    @Test
    void testDelete_ProductDoesNotExist() {
        int beforeSize = countProducts();

        productRepository.delete("999");

        int afterSize = countProducts();
        assertEquals(beforeSize, afterSize);
    }

    @Test
    void testDelete_LastProduct() {
        productRepository.delete("1");
        productRepository.delete("2");

        Iterator<Product> iterator = productRepository.findAll();
        assertFalse(iterator.hasNext());
    }

    private int countProducts() {
        Iterator<Product> iterator = productRepository.findAll();
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        return count;
    }
}
