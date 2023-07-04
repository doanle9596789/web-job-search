//package com.example.casestudymodule4.controller;
//
//import com.example.casestudymodule4.model.Job;
//import com.example.casestudymodule4.service.ext.IJobService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.FileCopyUtils;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@CrossOrigin("*")
//@RequestMapping("/api/user")
//public class JobController {
//
//    @Value("${upload.path1}")
//    private String linkCv;
//
//    @Value("${display.path1}")
//    private String displayLink1;
//    @Autowired
//    private IJobService jobService;
//

//
//    @GetMapping("/listJob")
//    public ResponseEntity<Page<Job>> listJob(@PageableDefault Pageable pageable,Optional<String >name){
//        Page<Job>jobs;
//        if (name.isPresent()){
//            jobs=jobService.findAll(name.get(),pageable);
//        }else {
//            jobs=jobService.findAll("",pageable);
//        }
//        return new ResponseEntity<>(jobs,HttpStatus.OK);
//    }
//
//    @PostMapping("/createJob")
//    public ResponseEntity<Void> createJob(@RequestBody Job job) {
//        jobService.save(job);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//    @DeleteMapping ("/delete/{id}")
//    public ResponseEntity<Void>deleteJob(@PathVariable(value = "id") Long id){
//        jobService.delete(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//    @PutMapping("/update/{id}")
//    public ResponseEntity<Void>updateJob(@PathVariable(value = "id") Long id){
//        Job job=jobService.findOne(id);
//        jobService.save(job);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//    @PostMapping(value = "/upload")
//    public ResponseEntity<Void> createUpload(@RequestPart(value = "file", required = false) MultipartFile file,
//                                             @RequestPart("job") Job job) {
//        if (file != null) {
//            String fileName = file.getOriginalFilename();
//            try {
//                FileCopyUtils.copy(file.getBytes(), new File(linkCv + fileName));
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//            job.setUploadCV(displayLink1 + fileName);
//        } else {
//            job.setUploadCV(displayLink1 + "cv.jpg");
//        }
//        jobService.save(job);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//    @GetMapping("/searchByCareer")
//    public ResponseEntity<Page<Job>> searchJobByCategory(@RequestBody ProgramingLanguage programingLanguage, @PageableDefault Pageable pageable) {
//        Page<Job> jobs;
//        String careerName = programingLanguage.getName();
//        jobs = jobService.findJobByCategory(careerName, pageable);
//
//
//        if (jobs.getTotalPages() == 0) {
//            return new ResponseEntity<>(jobs, HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(jobs, HttpStatus.OK);
//        }
//    }
//
//    @GetMapping("/searchByCity")
//    public ResponseEntity<Page<Job>> searchJobByCity(@RequestBody City city, @PageableDefault Pageable pageable) {
//        Page<Job> jobs;
//        String cityName = city.getName();
//        jobs = jobService.findJobByCategory(cityName, pageable);
//
//
//        if (jobs.getTotalPages() == 0) {
//            return new ResponseEntity<>(jobs, HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(jobs, HttpStatus.OK);
//        }
//    }
//
//    @GetMapping("/searchByType")
//    public ResponseEntity<Page<Job>> searchJobByType(@RequestBody Job job, @PageableDefault Pageable pageable) {
//        Page<Job> jobs;
//        String type = job.getContent();
//        jobs = jobService.findJobByCategory(type, pageable);
//
//
//        if (jobs.getTotalPages() == 0) {
//            return new ResponseEntity<>(jobs, HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(jobs, HttpStatus.OK);
//        }
//    }
//
//    @GetMapping("/searchJobByNSEC")
//    public ResponseEntity<Page<Job>> searchJobByNSEC(@RequestParam(value = "name") String name,
//                                                     @RequestParam(value = "salary") Double salary, @RequestParam(value = "experience") Integer exp,
//                                                     @RequestParam(value = "cityName") String cityName, @PageableDefault Pageable pageable) {
//        Page<Job> jobs;
//        jobs = jobService.findJobByNSEC(name, salary, exp, cityName, pageable);
//
//        if (jobs.getTotalPages() == 0) {
//            return new ResponseEntity<>(jobs, HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(jobs, HttpStatus.OK);
//        }
//    }
//
//    @GetMapping("/searchJobByCaMs")
//    public ResponseEntity<Page<Job>> searchJobByCaMs(@RequestParam(value = "careerName") String careerName,
//                                                     @RequestParam(value = "salary") Double salary, @PageableDefault Pageable pageable) {
//        Page<Job> jobByCaMs;
//        jobByCaMs = jobService.findJobByCaMs(careerName, salary, pageable);
//
//        if (jobByCaMs.getTotalPages() == 0) {
//            return new ResponseEntity<>(jobByCaMs, HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(jobByCaMs, HttpStatus.OK);
//        }
//    }
//
//    @GetMapping("/listJob")
//    public ResponseEntity<Page<Job>> listJob(@PageableDefault Pageable pageable, Optional<String> name) {
//        Page<Job> jobs;
//        if (name.isPresent()) {
//            jobs = jobService.findAll(name.get(), pageable);
//        } else {
//            jobs = jobService.findAll("", pageable);
//        }
//        return new ResponseEntity<>(jobs, HttpStatus.OK);
//    }
//
//    @PostMapping("/createJob")
//    public ResponseEntity<Void> createJob(@RequestBody Job job) {
//        jobService.save(job);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteJob(@PathVariable(value = "id") Long id) {
//        jobService.delete(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<Void> updateJob(@PathVariable(value = "id") Long id) {
//        Job job = jobService.findOne(id);
//        jobService.save(job);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//}
package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.model.Job;
import com.example.casestudymodule4.service.ext.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class JobController {
    @Autowired
    private IJobService jobService;


    @GetMapping("/listJob")
    public ResponseEntity<Page<Job>> listJob(@PageableDefault Pageable pageable,Optional<String >name){
        Page<Job>jobs;
        if (name.isPresent()){
            jobs=jobService.findAll(name.get(),pageable);
        }else {
            jobs=jobService.findAll("",pageable);
        }
        return new ResponseEntity<>(jobs,HttpStatus.OK);
    }

    @PostMapping("/createJob")
    public ResponseEntity<Void> createJob(@RequestBody Job job) {
        jobService.save(job);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<Void>deleteJob(@PathVariable(value = "id") Long id){
        jobService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Void>updateJob(@PathVariable(value = "id") Long id){
        Job job=jobService.findOne(id);
        jobService.save(job);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
