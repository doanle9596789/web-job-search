package com.example.casestudymodule4.service.impl;

import com.example.casestudymodule4.model.Company;
import com.example.casestudymodule4.repository.ICompanyRepository;
import com.example.casestudymodule4.service.ext.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    private ICompanyRepository companyRepository;

    @Override
    public Page<Company> findAll(String name, Pageable pageable) {
        return companyRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public Company findOne(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Company company) {
        companyRepository.save(company);
    }

    @Override
    public void delete(Long id) {
        companyRepository.deleteById(id);
    }


    @Override
    public Page<Company> findAllCompany(Pageable pageable) {
        return companyRepository.findAllCompany(pageable);
    }

    @Override
    public Integer companyCount()
    {
        return companyRepository.companyCount();
    }
}
