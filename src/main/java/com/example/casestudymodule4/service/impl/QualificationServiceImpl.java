package com.example.casestudymodule4.service.impl;

import com.example.casestudymodule4.model.Qualification;
import com.example.casestudymodule4.repository.IQualificationRepository;
import com.example.casestudymodule4.service.ext.IQualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QualificationServiceImpl implements IQualificationService {
    @Autowired
    private IQualificationRepository qualificationRepository;

    @Override
    public Page<Qualification> findAll(String name, Pageable pageable) {
        return qualificationRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public Qualification findOne(Long id) {
        return qualificationRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Qualification qualification) {
        qualificationRepository.save(qualification);
    }

    @Override
    public void delete(Long id) {
        qualificationRepository.deleteById(id);
    }
}
