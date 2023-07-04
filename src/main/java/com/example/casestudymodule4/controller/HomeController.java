package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.model.Company;
import com.example.casestudymodule4.model.Job;
import com.example.casestudymodule4.model.ProgramingLanguage;
import com.example.casestudymodule4.service.ext.ICityService;
import com.example.casestudymodule4.service.ext.ICompanyService;
import com.example.casestudymodule4.service.ext.IJobService;
import com.example.casestudymodule4.service.ext.IProgramingLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/all/homes")
public class HomeController
{
    @Autowired
    private ICityService cityService;


    @Autowired
    private ICompanyService companyService;


    @Autowired
    private IProgramingLanguageService programingLanguageService;

    @Autowired
    private IJobService jobService;


    @GetMapping("/cityCount")
    public ResponseEntity<Integer> branchCount()
    {
        return new ResponseEntity<>(cityService.branchCount(), HttpStatus.OK);
    }
    @GetMapping("/companyCount")
    public ResponseEntity<Integer> cityCount()
    {
        return new ResponseEntity<>(companyService.companyCount(), HttpStatus.OK);
    }

    @GetMapping("/companies")
    public ResponseEntity<Page<Company>> companyList(@RequestParam(value = "page", required = false) int page)
    {
        Page<Company> companies = companyService.findAllCompany(PageRequest.of(page, 6));

        if (companies.getTotalPages() == 0)
        {
            return new ResponseEntity<>(companies, HttpStatus.NO_CONTENT);
        }
        else
        {
            return new ResponseEntity<>(companies, HttpStatus.OK);
        }
    }
    @GetMapping("/programmingLanguage")
    public ResponseEntity<List<ProgramingLanguage>> programmingLanguageList()
    {
        List<ProgramingLanguage> programingLanguages = (List<ProgramingLanguage>) programingLanguageService.findAll();

        if (programingLanguages.size() == 0)
        {
            return new ResponseEntity<>(programingLanguages, HttpStatus.NO_CONTENT);
        }
        else
        {
            return new ResponseEntity<>(programingLanguages, HttpStatus.OK);
        }
    }
    @GetMapping("/citiesSearchByName")
    public ResponseEntity<List<String>> cityListByNameSearch(@RequestParam("searchLocation") String cityName)
    {
        List<String> cities = (List<String>) cityService.findAllCitiesBySearchName(cityName);

        if (cities.size() == 0)
        {
            return new ResponseEntity<>(cities, HttpStatus.NO_CONTENT);
        }
        else
        {
            return new ResponseEntity<>(cities, HttpStatus.OK);
        }
    }

//    @Autowired
//    private IJobService jobService;

    @GetMapping("/searchingJob")
    public ResponseEntity<Page<Job>> searchJob(@RequestParam(defaultValue = "0", value = "page") int page, @RequestParam(value = "searchLocationByJob", required = false) String cityName,
                                               @RequestParam(value = "programmingLanguageJob", required = false) Long programmingLanguage,
                                               @RequestParam(value = "qualificationName", required = false) String qualificationName) {
        Page<Job> jobs;
        jobs= jobService.findJobsByQLOrLCOrPLanguage(programmingLanguage, qualificationName, cityName,PageRequest.of(page,6));

        if (jobs.getTotalPages() == 0) {
            return new ResponseEntity<>(jobs, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(jobs, HttpStatus.OK);
        }
    }@GetMapping("/detailCompany/{id}")
    public ResponseEntity<Company>getOne(@PathVariable Long id){
        Company company=companyService.findOne(id);
        return new ResponseEntity<>(company,HttpStatus.OK);
}


//    @GetMapping("/searchingJob")
//    public ResponseEntity<Page<Job>> searchJob(@RequestParam(defaultValue = "0", value = "page") int page, @RequestParam(value = "searchLocationByJob", required = false) String cityName,
//                                               @RequestParam(value = "programmingLanguageJob", required = false) Long programmingLanguage,
//                                               @RequestParam(value = "qualificationName", required = false) String qualificationName) {
//        Page<Job> jobs;
//        jobs= jobService.findJobsByQLOrLCOrPLanguage(programmingLanguage, qualificationName, cityName,PageRequest.of(page,6));
//
//        if (jobs.getTotalPages() == 0) {
//            return new ResponseEntity<>(jobs, HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(jobs, HttpStatus.OK);
//        }
//    }
}
