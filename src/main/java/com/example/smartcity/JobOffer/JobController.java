package com.example.smartcity.JobOffer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/job")

public class JobController {
    //service injection
    private final JobServiceImplementation jobServiceImplementation;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/")
    public List<JobOffer> getAllJobOffers() {
        return jobServiceImplementation.getAllJobOffers();
    }

    @GetMapping("/allOffersAvailable")
    public List<JobOffer> getAllJobOffersAvailable() {
        return jobServiceImplementation.getAllJobOffersAvailable();
    }

    @GetMapping("/allOffers")
    public Optional<JobOffer> getJobOfferById(@PathVariable(name = "id") Long id) {
        try {
            return jobServiceImplementation.getJobOfferById(id);
        } catch (Exception e) {
            System.out.println("Job offer with id " + id + " not found.");
            return null;
        }
    }

    @PostMapping("/")
    public ResponseEntity<String> postJobOffer(@RequestBody JobOffer jobOffer, @RequestParam Long userId) {
        jobServiceImplementation.createJobOffer(jobOffer, userId);
        return new ResponseEntity<>("Success.", HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<String> putJobOffer() {
        //todo
        return new ResponseEntity<>("Success.", HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<String> deleteJobOffer(@PathVariable(name = "id") Long id) throws Exception {
        try {
            jobServiceImplementation.deleteJobOfferById(id);
            return new ResponseEntity<>("Success.", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Job offer with id " + id + " not found.");
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }

    }

    //todo
    //add method that deletes user from the joboffer that he is apllying
    //add method that ensures the job is not available, because someone took it


}
