package com.example.smartcity.JobOffer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobServiceImplementation {
    private final JobRepository jobRepository;

    public void createJobOffer(JobOffer jobOfferRequest,Long userId) {
        JobOffer newJobOffer = new JobOffer();

        newJobOffer.setAvailable(true);
        newJobOffer.setDescription(jobOfferRequest.getDescription());
        newJobOffer.setDate(new Date(System.currentTimeMillis()));
        newJobOffer.setEmployerId(userId);
        newJobOffer.setCandidates(new HashSet<>());

        jobRepository.save(newJobOffer);

    }

    public List<JobOffer> getAllJobOffersAvailable() {
        return jobRepository.findAll().stream().filter(JobOffer::isAvailable).toList();
    }

    public List<JobOffer> getAllJobOffers() {
        return jobRepository.findAll();
    }

    public Optional<JobOffer> getJobOfferById(Long id) throws Exception {
        return Optional.ofNullable(jobRepository.findById(id).orElseThrow(() -> new Exception("Job offer with id:" + id + "not found.")));
    }

    public void deleteJobOfferById(Long id) throws Exception {
        jobRepository.delete(jobRepository.findById(id).orElseThrow(() -> new Exception("Job offer with id:" + id + "not found.")));
    }


}
