package com.example.hospital.controller;


import com.example.hospital.service.MedicalService;
import com.example.hospital.vo.Result;
import com.example.hospital.vo.params.GuahaoParam;
import com.example.hospital.vo.params.MedicalParam;
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
@RequestMapping("/medical")
public class MedicalController {
    @Autowired
    MedicalService medicalService;

    @GetMapping
    public Result getGuahao(){
        return medicalService.getGuahao();
    }

    @GetMapping("/{medicalId}")
    public Result getGuahaobyId(@PathVariable Long medicalId){
        return medicalService.getGuahaobyId(medicalId);
    }

    @PutMapping("/{medicalId}")
    public Result updateGuahao(@RequestBody MedicalParam medicalParam){
        return medicalService.updatePrescription(medicalParam);
    }

    @PostMapping
    public Result insertGuahao(@RequestBody GuahaoParam guahaoParam){
        System.out.println(guahaoParam);
        return medicalService.insertGuahao(guahaoParam);
    }
    @DeleteMapping("/{medicalId}")
    public Result deleteGuahao(@PathVariable Long medicalId){
        return medicalService.deleteGuahaobyId(medicalId);
    }

}
