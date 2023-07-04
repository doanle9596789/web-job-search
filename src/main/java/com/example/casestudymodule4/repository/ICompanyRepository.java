package com.example.casestudymodule4.repository;

import com.example.casestudymodule4.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompanyRepository extends JpaRepository<Company,Long>
{
    Page<Company> findAllByNameContaining(String name, Pageable pageable);


    @Query("select company from Company company")
    Page<Company> findAllCompany(Pageable pageable);

    @Query("select count(distinct(company.name)) from Company  company")
    Integer companyCount();
}
