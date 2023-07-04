package com.example.casestudymodule4.service.impl;

import com.example.casestudymodule4.model.ProgramingLanguage;
import com.example.casestudymodule4.repository.IProgramingLanguageRepository;
import com.example.casestudymodule4.service.ext.IProgramingLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProgramingLanguageServiceImpl implements IProgramingLanguageService {
    @Autowired
    private IProgramingLanguageRepository programingLanguageRepository;

    @Override
    public Page<ProgramingLanguage> findAll(String name, Pageable pageable) {
        return programingLanguageRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public ProgramingLanguage findOne(Long id) {
        return programingLanguageRepository.findById(id).orElse(null);
    }

    @Override
    public void save(ProgramingLanguage programingLanguage) {
        programingLanguageRepository.save(programingLanguage);
    }

    @Override
    public void delete(Long id) {
        programingLanguageRepository.deleteById(id);
    }

    @Override
    public Iterable<ProgramingLanguage> findAll() {
        return programingLanguageRepository.findAll();
    }
}
