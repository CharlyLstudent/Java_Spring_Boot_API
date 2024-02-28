package fr.campus.cda.charly.java_spring_boot_api.controller;

import fr.campus.cda.charly.java_spring_boot_api.dto.ProductEnterDTO;
import fr.campus.cda.charly.java_spring_boot_api.dto.ProductExitDTO;
import fr.campus.cda.charly.java_spring_boot_api.entity.Product;
import fr.campus.cda.charly.java_spring_boot_api.repository.ProductRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/products")
    public ProductExitDTO addProduct(@RequestBody ProductEnterDTO params) {
        Product addProduct = new Product(params.name(), params.price());
        productRepository.save(addProduct);
        return new ProductExitDTO(addProduct.getName(), addProduct.getPrice(), addProduct.getId());
    }

    @GetMapping("/products")
    public List<ProductExitDTO> getAllProducts() {
        List<Product> productList = StreamSupport
                .stream(productRepository.findAll().spliterator(), false)
                .toList();
        List<ProductExitDTO> productExitDTOs = productList.stream()
                .map(product -> new ProductExitDTO(product.getName(), product.getPrice(), product.getId()))
                .toList();
        return productExitDTOs;
    }

    @GetMapping("products/{id}")
    public ProductExitDTO getProductById(@PathVariable int id) {
        return productRepository.findById(id)
                .map(product -> new ProductExitDTO(product.getName(), product.getPrice(), product.getId()))
                .orElse(null);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductExitDTO> updateProduct(@RequestBody ProductEnterDTO params, @PathVariable int id) {
        return productRepository.findById(id).map(product -> {
            product.setName(params.name());
            product.setPrice(params.price());
            Product updatedProduct = productRepository.save(product);
            return new ResponseEntity<>(new ProductExitDTO(updatedProduct.getName(), updatedProduct.getPrice(), updatedProduct.getId()), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/products/{id}")
    public ProductExitDTO deleteProduct(@PathVariable int id) {
        ProductExitDTO product = productRepository.findById(id).map(product1 -> new ProductExitDTO(product1.getName(), product1.getPrice(), product1.getId())).orElse(null);
        productRepository.deleteById(id);
        return product;
    }
}
