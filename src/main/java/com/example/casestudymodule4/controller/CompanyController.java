package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.model.Company;
import com.example.casestudymodule4.service.ext.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/company")
public class CompanyController {
    @Value("${upload.path}")
    private String link;

    @Value("${display.path}")
    private String displayLink;
    @Autowired
   private ICompanyService companyService;
    @GetMapping("/listCompany")
    public ResponseEntity<Page<Company>>listCompany(@PageableDefault Pageable pageable, Optional<String> name){
        Page<Company>companies;
        if (name.isPresent()){
           companies=companyService.findAll(name.get(),pageable);
        }else {
            companies=companyService.findAll("",pageable);
        }
        return new ResponseEntity<>(companies,HttpStatus.OK);
    }
    @PostMapping ("/createCompany")
    public ResponseEntity<Void>createCompany(@RequestBody Company company){
        companyService.save(company);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/deleteCompany/{id}")
    public ResponseEntity<Void>deleteCompany(@PathVariable Long id){
        companyService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/updateCompany/{id}")
    public ResponseEntity<Company>updateCompany(@PathVariable Long id){
        Company company1= (companyService.findOne(id));
      if (company1==null){
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }else {
          company1.setId(company1.getId());
      }
        companyService.save(company1);
        return new ResponseEntity<>(company1,HttpStatus.OK);
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<Void> createUpload(@RequestPart(value = "file", required = false) MultipartFile file,
                                             @RequestPart("company") Company company) {
        if (file != null) {
            String fileName = file.getOriginalFilename();
            try {
                FileCopyUtils.copy(file.getBytes(), new File(link + fileName));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            company.setAvatar(displayLink + fileName);
        } else {
            company.setAvatar(displayLink + "avatar.jpg");
        }
        companyService.save(company);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/detailCompany/{id}")
    public ResponseEntity<Company>showDetail(@PathVariable Long id){
        Company company=companyService.findOne(id);
        return new ResponseEntity<>(company,HttpStatus.OK);
    }


}
