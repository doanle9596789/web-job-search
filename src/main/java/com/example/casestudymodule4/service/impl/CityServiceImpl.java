package com.example.casestudymodule4.service.impl;

import com.example.casestudymodule4.model.City;
import com.example.casestudymodule4.repository.ICityRepository;
import com.example.casestudymodule4.service.ext.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements ICityService {
    @Autowired
    private ICityRepository cityRepository;

    @Override
    public Page<City> findAll(String name, Pageable pageable) {
        return cityRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public City findOne(Long id) {
        return cityRepository.findById(id).orElse(null);
    }

    @Override
    public void save(City city) {
        cityRepository.save(city);
    }

    @Override
    public void delete(Long id) {
        cityRepository.deleteById(id);
    }


    @Override
    public Iterable<String> findAllCitiesBySearchName(String cityName) {
        return cityRepository.findAllCitiesBySearchName(cityName);
    }

    @Override
    public Integer branchCount() {
        return cityRepository.branchCount();
    }
}
