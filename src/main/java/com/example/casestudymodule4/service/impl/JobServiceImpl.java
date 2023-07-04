package com.example.casestudymodule4.service.impl;

import com.example.casestudymodule4.model.Job;
import com.example.casestudymodule4.repository.IJobRepository;
import com.example.casestudymodule4.service.ext.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobServiceImpl implements IJobService {
    @Autowired
    private IJobRepository jobRepository;

    @Override
    public Page<Job> findAll(String name, Pageable pageable) {
        return jobRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public Job findOne(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Job job) {
        jobRepository.save(job);
    }

    @Override
    public void delete(Long id) {
        jobRepository.deleteById(id);
    }


    @Override
    public Page<Job> findJobsByQLOrLCOrPLanguage(Long programmingLanguageId, String qualificationId, String cityId,Pageable pageable)
    {
        return jobRepository.findJobsByQLOrLCOrPLanguage(programmingLanguageId, qualificationId, cityId,pageable);

    }

}
