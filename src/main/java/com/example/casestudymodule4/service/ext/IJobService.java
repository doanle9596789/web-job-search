package com.example.casestudymodule4.service.ext;

import com.example.casestudymodule4.model.Job;
import com.example.casestudymodule4.service.core.ICoreService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IJobService extends ICoreService<Job>
{

    Page<Job> findJobsByQLOrLCOrPLanguage(Long programmingLanguageId,
                                          String qualificationName,
                                          String cityName, Pageable pageable);

}
