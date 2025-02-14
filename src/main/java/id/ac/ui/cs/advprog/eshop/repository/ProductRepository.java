package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product findById(String productId) {
        return productData.stream()
                .filter(product -> product.getProductId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public void edit(Product editedProduct) {
        for (int i = 0; i < productData.size(); i++) {
            Product product = productData.get(i);
            if (product.getProductId().equals(editedProduct.getProductId())) {
                productData.set(i, editedProduct);
                break;
            }
        }
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public void delete(String id) {
        for (int i = 0; i < productData.size(); i++) {
            if (productData.get(i).getProductId().equals(id)) {
                productData.remove(i);
                break;
            }
        }
    }
}