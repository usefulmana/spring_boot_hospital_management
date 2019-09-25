package rmit.spring.hospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.spring.hospital.exceptions.ResourceNotFoundException;
import rmit.spring.hospital.models.Patient;
import rmit.spring.hospital.models.Visit;
import rmit.spring.hospital.repositories.PatientRepository;
import rmit.spring.hospital.repositories.VisitRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class VisitController {

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/visits/{id}")
    public Visit getVisitById(@Valid @PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        return visitRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Unable to find a visit with id = ", id));
    }

    @GetMapping("/visits/patient/{p_id}")
    public List<Visit> getAllVisitsByAPatient(@Valid @PathVariable(name = "p_id") Long id){
        return visitRepository.getAllByPatient_IdOrderByDateCreatedDesc(id);
    }

    @GetMapping("/visits/patient/latest/{p_id}")
    public Visit getLatestVisitOfAPatient(@Valid @PathVariable(name = "p_id") Long id){
        return visitRepository.findTopByPatientIdOrderByDateCreatedDesc(id);
    }

    @PostMapping("/visits/patient/{p_id}")
    public Visit createAVisit(@Valid @PathVariable(name = "p_id") Long p_id, @RequestBody Visit visit) throws
            ResourceNotFoundException{
        Patient patient = patientRepository.findById(p_id)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find any patient with id = ", p_id));

        visit.setPatient(patient);
        return visitRepository.save(visit);
    }

    @PutMapping("/visits/{id}")
    public Visit updateAVisit(@Valid @PathVariable(name = "id") Long id, @RequestBody Visit updatedVisit) throws
            ResourceNotFoundException{
        Visit visit = visitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find any visit with id = ", id));

        visit.setProblems(updatedVisit.getProblems());
        return visitRepository.save(visit);
    }

    @DeleteMapping("/visits/{id}")
    public ResponseEntity<?> deleteAVisit(@Valid @PathVariable(name = "id") Long id) throws
            ResourceNotFoundException{
        Visit visit = visitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find any visit with id = ", id));
        visitRepository.delete(visit);

        HashMap<String, String> res = new HashMap<>();
        res.put("message", String.format("Successfully deleted a visit with id = %s", id));
        return ResponseEntity.ok(res);
    }


}
