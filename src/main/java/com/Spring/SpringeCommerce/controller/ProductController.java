package com.Spring.SpringeCommerce.controller;

import com.Spring.SpringeCommerce.exceptions.ResourceNotFoundException;
import com.Spring.SpringeCommerce.interfaces.IProductService;
import com.Spring.SpringeCommerce.model.Product;
import com.Spring.SpringeCommerce.requests.AddProductRequest;
import com.Spring.SpringeCommerce.requests.ProductUpdateRequest;
import com.Spring.SpringeCommerce.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(new ApiResponse("Found!", products));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId){
        try{
            Product product = productService.getProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Found!", product));
        }  catch (ResourceNotFoundException e){
            return ResponseEntity
                    .status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/add-product")
    public ResponseEntity<ApiResponse> addNewProduct(@RequestBody AddProductRequest product){
        try {
            Product newProduct = productService.addProduct(product);
            return ResponseEntity.ok(new ApiResponse("Product added successful!", product));
        } catch (Exception e){
            return ResponseEntity
                    .status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/product/{productId}/update")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long productId, @RequestBody ProductUpdateRequest product){
        try {
            Product updatedProduct = productService.updateProduct(product, productId);
            return ResponseEntity.ok(new ApiResponse("Product updated successful!", product));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/product/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId){
        try {
            productService.deleteProduct(productId);
            return ResponseEntity.ok(new ApiResponse("Deleted successfully!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/by-brand-and-name")
    public ResponseEntity<ApiResponse> getProductsByBrandAndName(@RequestParam String brandName, @RequestParam String productName){
       try {
           List<Product> products = productService.getProductsByBrandAndName(brandName, productName);
           if (products.isEmpty()){
               return ResponseEntity
                       .status(NOT_FOUND)
                       .body(new ApiResponse("No product found!", null));
           }

           return ResponseEntity.ok(new ApiResponse("Products found!", products));
       } catch (Exception e) {
           return ResponseEntity
                   .status(INTERNAL_SERVER_ERROR)
                   .body(new ApiResponse(e.getMessage(), null));
       }
    }

    @GetMapping("/by-category-and-brand")
    public ResponseEntity<ApiResponse> getProductsByCategoryAndBrand(@RequestParam String categoryName, @RequestParam String brandName){
       try {
           List<Product> products = productService.getProductsByCategoryAndBrand(categoryName, brandName);
           if (products.isEmpty()){
               return ResponseEntity
                       .status(NOT_FOUND)
                       .body(new ApiResponse("No product found!", null));
           }

           return ResponseEntity.ok(new ApiResponse("Products found!", products));
       } catch (Exception e) {
           return ResponseEntity
                   .status(INTERNAL_SERVER_ERROR)
                   .body(new ApiResponse(e.getMessage(), null));
       }
    }

    @GetMapping("/{productName}")
    public ResponseEntity<ApiResponse> getProductsByName(@PathVariable String productName){
        try {
            List<Product> products = productService.getProductsByName(productName);
            if (products.isEmpty()){
                return ResponseEntity
                        .status(NOT_FOUND)
                        .body(new ApiResponse("No product found!", null));
            }

            return ResponseEntity.ok(new ApiResponse("Products found!", products));
        } catch (Exception e) {
            return ResponseEntity
                    .status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/by-brand")
    public ResponseEntity<ApiResponse> getProductsByBrand(@RequestParam String brandName){
        try {
            List<Product> products = productService.getProductsByBrand(brandName);
            if (products.isEmpty()){
                return ResponseEntity
                        .status(NOT_FOUND)
                        .body(new ApiResponse("No product found!", null));
            }

            return ResponseEntity.ok(new ApiResponse("Products found!", products));
        } catch (Exception e) {
            return ResponseEntity
                    .status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{category}/all")
    public ResponseEntity<ApiResponse> findProductsByCategory(@PathVariable String category){
        try {
            List<Product> products = productService.getProductsByCategory(category);
            if (products.isEmpty()){
                return ResponseEntity
                        .status(NOT_FOUND)
                        .body(new ApiResponse("No products found!", null));
            }

            return ResponseEntity.ok(new ApiResponse("Products found!", products));
        } catch (Exception e) {
            return ResponseEntity
                    .status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

}


