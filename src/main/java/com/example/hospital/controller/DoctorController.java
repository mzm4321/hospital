package com.example.hospital.controller;


import com.example.hospital.service.DoctorService;
import com.example.hospital.service.impl.DoctorServiceImpl;
import com.example.hospital.vo.Result;
import com.example.hospital.vo.params.DoctorParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author author
 * @since 2022-05-24
 */
@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;


    @GetMapping("/depart/{departId}")
    public Result findDoctorsbyDeparId(@PathVariable Long departId) {
        System.out.println(departId);
        return doctorService.findDoctorsbyDeparId(departId);
    }
    @GetMapping("/nostop/depart/{departId}")
    public Result findNoStopDoctorsbyDeparId(@PathVariable Long departId) {
        System.out.println(departId);
        return doctorService.findNoStopDoctorsbyDeparId(departId);
    }


    @GetMapping("{dotocrId}")
    public Result findDoctor(@PathVariable Long dotocrId) {
        return doctorService.findDoctor(dotocrId);
    }

    @GetMapping
    public Result doctor() {
        return doctorService.findDoctors();
    }

    @PostMapping
    public Result insertDoctor(@RequestBody DoctorParam doctorParam){
        return doctorService.insertDoctor(doctorParam);
    }

    @PutMapping("/update/{doctorId}")
    public Result updateDoctorbyId(@PathVariable Long doctorId,@RequestBody DoctorParam doctorParam) {
        System.out.println(doctorParam);
        System.out.println("--------------"+doctorParam.getStop());
        System.out.println(doctorParam.getHospitalTime());
        return doctorService.updateDoctorbyId(doctorId,doctorParam);
    }

    @DeleteMapping("{doctorId}")
    public Result deleteDoctorbyId(@PathVariable Long doctorId){
        System.out.println(doctorId);
        return doctorService.deleteDoctorbyId(doctorId);
    }

}
