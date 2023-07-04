package com.example.casestudymodule4.repository;

import com.example.casestudymodule4.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityRepository extends JpaRepository<City,Long> {
    Page<City> findAllByNameContaining(String name, Pageable pageable);

    @Query("select count(distinct(city.name)) from City city")
    Integer branchCount();

    @Query("select distinct(city.name) from City city where lower(city.name) like concat('%',:cityName, '%') or upper(city.name) like concat('%',:cityName, '%') ")
    Iterable<String> findAllCitiesBySearchName(@Param("cityName") String name);

}
