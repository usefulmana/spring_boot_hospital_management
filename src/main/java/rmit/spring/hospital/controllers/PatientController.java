package rmit.spring.hospital.controllers;


import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.spring.hospital.Repositories.PatientRepository;
import rmit.spring.hospital.exceptions.ResourceNotFoundException;
import rmit.spring.hospital.models.Patient;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
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
        patient.setFirstName(patientDetails.getFirstName());
        patient.setLastName(patientDetails.getLastName());
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
