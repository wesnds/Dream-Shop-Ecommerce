package com.Spring.SpringeCommerce.interfaces;

import com.Spring.SpringeCommerce.requests.AddProductRequest;
import com.Spring.SpringeCommerce.requests.ProductUpdateRequest;
import com.Spring.SpringeCommerce.model.Product;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest product);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategoryName(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String productName);
    List<Product> getProductsByBrandAndName(String brand, String productName);
    void deleteProduct(Long id);
    Product updateProduct(ProductUpdateRequest product, Long productId);
    Long countProductsByBrandAndName(String brand, String productName);
}
