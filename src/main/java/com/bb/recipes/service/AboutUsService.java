package com.bb.recipes.service;

import java.util.List;

import com.bb.recipes.entities.AboutUs;

public interface AboutUsService {
    public List<AboutUs> findAll();

    public void save(AboutUs aboutUs);

    public void deleteAll();
}