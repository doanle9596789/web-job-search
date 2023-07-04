package com.example.casestudymodule4.service.ext;

import com.example.casestudymodule4.model.Company;
import com.example.casestudymodule4.service.core.ICoreService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICompanyService extends ICoreService<Company>
{


    Page<Company> findAllCompany(Pageable pageable);
    Integer companyCount();
}
