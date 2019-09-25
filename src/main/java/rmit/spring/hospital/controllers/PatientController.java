package rmit.spring.hospital.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.spring.hospital.repositories.PatientRepository;
import rmit.spring.hospital.exceptions.ResourceNotFoundException;
import rmit.spring.hospital.models.Patient;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/patients")
    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    @GetMapping("/patients/{id}")
    public Patient getAPatientById(@Valid @PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find any patient with id = ", id));
    }
    @GetMapping("/patients/byName/{name}")
    public List<Patient> getPatientsByName(@Valid @PathVariable(value = "name") String name){
        if (name.equalsIgnoreCase("all")){
            return patientRepository.findAll();
        }
        return patientRepository.findByPatientNameIgnoreCaseContaining(name);
    }
    @PostMapping("/patients")
    public Patient addPatient(@RequestBody Patient patient){
        return patientRepository.save(patient);
    }

    @PutMapping("/patients/{id}")
    public Patient updatePatientInfo(@PathVariable(value = "id") Long id, @Valid @RequestBody Patient patientDetails)
            throws ResourceNotFoundException{
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find any patient with id = ", id));

        patient.setAge(patientDetails.getAge());
        patient.setDob(patientDetails.getDob());
        patient.setPatientName(patientDetails.getPatientName());
        patient.setInsuranceNumber(patientDetails.getInsuranceNumber());
        patient.setNationalIDNumber(patientDetails.getNationalIDNumber());
        patient.setSex(patientDetails.getSex());

        return patientRepository.save(patient);
    }

    @DeleteMapping("/patients/{id}")
    public @ResponseBody ResponseEntity<?> deleteAPatient(@PathVariable(value = "id") Long id) throws ResourceNotFoundException{
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find any patient with id = ", id));
        patientRepository.delete(patient);
        HashMap<String, String> res = new HashMap<>();
        res.put("message", String.format("Successfully deleted patient with id = %s", id));
        return ResponseEntity.ok(res);
    }
}
