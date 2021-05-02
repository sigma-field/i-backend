package com.bb.recipes.config;

import com.bb.recipes.entities.Category;
import com.bb.recipes.entities.Comment;
import com.bb.recipes.entities.Recipe;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Recipe.class);
        config.exposeIdsFor(Category.class);
        config.exposeIdsFor(Comment.class);
        config.setMaxPageSize(100);
    }
}
