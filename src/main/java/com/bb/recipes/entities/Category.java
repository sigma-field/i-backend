package com.bb.recipes.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    private String image;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Collection<Recipe> recipes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category() {
        super();
    }

    public Category(String name, String image) {
        this.name = name;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Category [name=" + name + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Transient
    public String getImagePath() {
        if (image == null || id == null) {
            return null;
        }
        return "/images/category/" + id + "/" + image;
    }

    public Category(String name, String image, Collection<Recipe> recipes) {
        this.name = name;
        this.image = image;
        this.recipes = recipes;
    }

}