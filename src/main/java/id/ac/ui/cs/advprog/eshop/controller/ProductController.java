package id.ac.ui.cs.advprog.eshop.controller;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        model.addAttribute("product", new Product());
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product) {
        service.create(product);
        return "redirect:/product/list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productList";
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(@PathVariable String id, Model model) {
        Product existingProduct = service.findById(id);
        if (existingProduct == null) {
            return "redirect:/product/list";
        }
        model.addAttribute("product", existingProduct);
        return "editProduct";
    }

    @PostMapping("/edit")
    public String editProductPost(@RequestParam("id") String id, @ModelAttribute Product editedProduct) {
        service.update(id, editedProduct);
        return "redirect:/product/list";
    }

    @PostMapping("/delete/{deletedProductId}")
    public String deleteProduct(@PathVariable String deletedProductId) {
        service.delete(deletedProductId);
        return "redirect:/product/list";
    }
}

