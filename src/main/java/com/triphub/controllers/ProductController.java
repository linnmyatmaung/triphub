package com.triphub.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.triphub.model.Product;
import com.triphub.services.ProductRepository;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repo;

    public ProductController(ProductRepository repo) {
        this.repo = repo;
    }

    @GetMapping({"", "/"})
    public String getProduct(Model model) {
        List<Product> products = repo.findAll(Sort.by(Sort.DEFAULT_DIRECTION.DESC, "id"));
        model.addAttribute("message", "Products");
        model.addAttribute("products", products);
        return "products/index";
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "products/add";
    }

    @PostMapping("/add")
    public String userSubmit(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        repo.save(product);
        redirectAttributes.addFlashAttribute("successMessage", "Product added successfully!");
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Product> product = repo.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "products/edit";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Product not found");
            return "redirect:/products";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        Optional<Product> existingProduct = repo.findById(id);
        if (existingProduct.isPresent()) {
            Product updatedProduct = existingProduct.get();
            updatedProduct.setName(product.getName());
            updatedProduct.setPrice(product.getPrice());
            updatedProduct.setDescription(product.getDescription());
            repo.save(updatedProduct);
            redirectAttributes.addFlashAttribute("successMessage", "Product updated successfully!");
            return "redirect:/products";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Product not found");
            return "redirect:/products";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Optional<Product> product = repo.findById(id);
        if (product.isPresent()) {
            repo.delete(product.get());
            redirectAttributes.addFlashAttribute("successMessage", "Product deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Product not found");
        }
        return "redirect:/products";
    }
}
