package com.bb.recipes.service;

import java.util.List;

import com.bb.recipes.entities.AboutUs;
import com.bb.recipes.repo.AboutUsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AboutUsServiceImpl implements AboutUsService {
    private AboutUsRepository aboutUsRepository;

    @Autowired
    public AboutUsServiceImpl(AboutUsRepository aboutUsRepository) {
        this.aboutUsRepository = aboutUsRepository;
    }

    @Override
    public void deleteAll() {
        aboutUsRepository.deleteAll();

    }

    @Override
    public List<AboutUs> findAll() {
        return aboutUsRepository.findAll();
    }

    @Override
    public void save(AboutUs aboutUs) {
        aboutUsRepository.save(aboutUs);
    }

}