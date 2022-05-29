package com.example.hospital.controller;


import com.example.hospital.service.PatientService;
import com.example.hospital.vo.Result;
import com.example.hospital.vo.params.DoctorParam;
import com.example.hospital.vo.params.MedicalParam;
import com.example.hospital.vo.params.PatientHistoryParam;
import com.example.hospital.vo.params.PatientParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2022-05-24
 */
@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    PatientService patientService;
    @GetMapping
    public Result patient() {
        return patientService.findPatients();
    }

    @GetMapping("{patientId}")
    public Result patient(@PathVariable Long patientId) {
        return patientService.findPatientbyId(patientId);
    }

    @PutMapping("history/{patientId}")
    public Result updateHistory(@RequestBody PatientHistoryParam patientHistoryParam){
        return patientService.updateHistory(patientHistoryParam);
    }

    @GetMapping("query/{query}")
    public Result patient(@PathVariable String query) {
        return patientService.queryPatients(query);
    }

    @PostMapping
    public Result insertPatient(@RequestBody PatientParam patientParam){
        System.out.println(patientParam);
        return patientService.insertPatient(patientParam);
    }

    @PutMapping("{patientId}")
    public Result updatePatient(@PathVariable Long patientId,@RequestBody PatientParam patientParam){
        return patientService.updatePatient(patientId,patientParam);
    }

    @DeleteMapping("{patientId}")
    public Result deleteDoctorbyId(@PathVariable Long patientId){
        return patientService.deletePatientbyId(patientId);
    }
}
