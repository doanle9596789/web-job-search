package com.example.casestudymodule4.repository;

import com.example.casestudymodule4.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IJobRepository extends JpaRepository<Job,Long>
{
    Page<Job> findAllByNameContaining(String name, Pageable pageable);

    @Query("select job from City city inner join city.job job where"+" "+
            "(:qualificationName IS NULL OR job.qualification.name LIKE %:qualificationName%) and"+" "+
            " (:cityName IS NULL OR city.name LIKE %:cityName%) and"+" "+
            "(:programmingLanguageId IS NULL OR job.programingLanguage.id = :programmingLanguageId)")
    Page<Job> findJobsByQLOrLCOrPLanguage(@Param("programmingLanguageId") Long programmingLanguageId,
                                          @Param("qualificationName") String qualificationName,
                                          @Param("cityName") String cityName,Pageable pageable);

}
