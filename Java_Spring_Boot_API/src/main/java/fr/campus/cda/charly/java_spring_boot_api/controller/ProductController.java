package fr.campus.cda.charly.java_spring_boot_api.controller;

import fr.campus.cda.charly.java_spring_boot_api.dto.ProductEnterDTO;
import fr.campus.cda.charly.java_spring_boot_api.dto.ProductExitDTO;
import fr.campus.cda.charly.java_spring_boot_api.entity.Product;
import fr.campus.cda.charly.java_spring_boot_api.repository.ProductRepository;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class ProductController {
    private static Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductRepository productRepository;

//    @PostMapping("/products")
//    public ResponseEntity<ProductExitDTO> addProduct(@RequestBody ProductEnterDTO params) {
//        try {
//            Product addProduct = new Product(params.name(), params.price());
//            productRepository.save(addProduct);
//            ProductExitDTO productExitDTO = new ProductExitDTO(addProduct.getName(), addProduct.getPrice(), addProduct.getId());
//            LOGGER.info("Le produit a bien été ajouté : {}", productExitDTO);
//            return new ResponseEntity<>(productExitDTO, HttpStatus.CREATED);
//        } catch (Exception e) {
//            LOGGER.error("Erreur lors de l'ajout du produit : {}", e.getMessage());
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }

    @PostMapping("/products")
    public ResponseEntity<Map<String, Object>> addingProduct(@Valid @RequestBody ProductEnterDTO params, BindingResult bindingResult) {
        Map<String, Object> responseBody = new HashMap<>();
        if (bindingResult.hasErrors()) {
            if(LOGGER.isWarnEnabled()){
                LOGGER.warn("Your given datas are wrong {}", bindingResult.getAllErrors());
            }
            responseBody.put("Success", false);
            responseBody.put("Message", "Incorrect data given ");
            responseBody.put("error", bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage));
            return ResponseEntity.badRequest().body(responseBody);
        }
        Product addProduct = new Product(params.name(), params.price());
        try{
            productRepository.save(addProduct);
        }catch(Exception e){
            LOGGER.error("Erreur lors de l'ajout du produit : {}", e.getMessage());
        }


        LOGGER.info("Le produit a bien été ajouté : {}", addProduct.getName());
        responseBody.put("Success", true);
        responseBody.put("Message", "Your product have been added succesfully ! ");
        responseBody.put("data", new ProductExitDTO(addProduct.getName(), addProduct.getPrice(), addProduct.getId()));
        return ResponseEntity.ok(responseBody);
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
