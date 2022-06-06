package com.example.hospital.controller;


import com.example.hospital.entity.Medicine;
import com.example.hospital.service.MedicineService;
import com.example.hospital.service.impl.MedicineServiceImpl;
import com.example.hospital.vo.Result;
import com.example.hospital.vo.params.MedicineParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2022-05-30
 */
@RestController
@RequestMapping("/medicine")
public class MedicineController {
    @Autowired
    private MedicineService medicineService;
    @GetMapping
    public Result medicine(){
        return medicineService.findMedicines();
    }

    @PostMapping
    public Result addMedicine(@RequestBody MedicineParam medicineParam){
        return medicineService.addMedicine(medicineParam);
    }

    @GetMapping("{medicineId}")
    public Result medicine(@PathVariable Long medicineId){
        return medicineService.findMedicinebyId(medicineId);
    }

    @PutMapping("{medicineId}")
    public Result updateMedicine(@RequestBody MedicineParam medicineParam,@PathVariable Long medicineId){
        return medicineService.updateMedicine(medicineId,medicineParam);
    }

    @DeleteMapping("{medicineId}")
    public Result deleteMedicine(@PathVariable Long medicineId){
        return medicineService.deleteMedicine(medicineId);
    }

    @GetMapping("/query/{query}")
    public Result queryMedicine(@PathVariable String query){
        return medicineService.queryMedicine(query);
    }
}
