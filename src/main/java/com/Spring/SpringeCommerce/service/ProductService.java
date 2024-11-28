package com.Spring.SpringeCommerce.service;

import com.Spring.SpringeCommerce.requests.AddProductRequest;
import com.Spring.SpringeCommerce.requests.ProductUpdateRequest;
import com.Spring.SpringeCommerce.exceptions.ProductNotFoundException;
import com.Spring.SpringeCommerce.interfaces.IProductService;
import com.Spring.SpringeCommerce.model.Category;
import com.Spring.SpringeCommerce.model.Product;
import com.Spring.SpringeCommerce.repository.CategoryRepository;
import com.Spring.SpringeCommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private Product createProduct(AddProductRequest request, Category category){
       Product newProduct = new Product(
               request.getName(),
               request.getBrand(),
               request.getDescription(),
               request.getPrice(),
               request.getInventory(),
               category
       );

       return newProduct;
    }

    @Override
    public Product addProduct(AddProductRequest request) {
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName())).orElseGet(() -> {
            Category newCategory = new Category(request.getCategory().getName());
            return categoryRepository.save(newCategory);
        });
        request.setCategory(category);

        return productRepository.save(createProduct(request, category));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found!"));
    }

    @Override
    public List<Product> getProductsByCategoryName(String categoryName) {
        return productRepository.findByCategoryName(categoryName);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.findById(id).ifPresentOrElse(productRepository::delete, () ->{
            throw new ProductNotFoundException("Product not found!");
        });
    }

    private Product updateExistingProduct(Product product, ProductUpdateRequest request){
        product.setName(request.getName());
        product.setBrand(request.getBrand());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setInventory(request.getInventory());

        product.setCategory(categoryRepository.findByName(request.getCategory().getName()));

        return product;
    }

    @Override
    public Product updateProduct(ProductUpdateRequest request, Long productId) {
        return productRepository.findById(productId)
                .map(product -> updateExistingProduct(product, request))
                .map(productRepository :: save)
                .orElseThrow(() -> new ProductNotFoundException("Product not found!"));
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }
}
