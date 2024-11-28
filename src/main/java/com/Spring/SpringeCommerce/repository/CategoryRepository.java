package com.Spring.SpringeCommerce.repository;

import com.Spring.SpringeCommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
