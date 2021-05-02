package com.bb.recipes.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uploadTime;
    private String title;
    private String origin;
    private Integer preparationTime;
    private Integer cookingTime;
    private Integer totalTime;
    private Integer servings;
    private String level;
    private String image;
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private Collection<Comment> comments;
    @Lob
    @Column(length = 10000)
    private String ingredients;
    @Lob
    @Column(length = 10000)
    private String instructions;
    @ManyToOne
    private Category category;

    public Recipe() {
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Integer getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(Integer preparationTime) {
        this.preparationTime = preparationTime;
    }

    public Integer getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(Integer cookingTime) {
        this.cookingTime = cookingTime;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Recipe [cookingTime=" + cookingTime + ", level=" + level + ", origin=" + origin + ", preparationTime="
                + preparationTime + ", servings=" + servings + ", title=" + title + ", totalTime=" + totalTime + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Recipe(String title, String origin, Integer preparationTime, Integer cookingTime, Integer totalTime,
            Integer servings, String level, Category category) {
        this.title = title;
        this.origin = origin;
        this.preparationTime = preparationTime;
        this.cookingTime = cookingTime;
        this.totalTime = totalTime;
        this.servings = servings;
        this.level = level;

        this.category = category;
    }

    @Transient
    public String getImagePath() {
        if (image == null || id == null) {
            return null;
        }
        return "/images/recipe/" + id + "/" + image;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

}