package org.example.repository;

import org.example.entity.Category;

public class CategoryRepository extends RepositoryManager<Category, Long>{

    public CategoryRepository() {
        super(Category.class);
    }


}
