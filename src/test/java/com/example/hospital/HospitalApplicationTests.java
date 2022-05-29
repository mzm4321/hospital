package com.example.hospital;

import com.example.hospital.entity.Medical;
import com.example.hospital.mapper.MedicalMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HospitalApplicationTests {
    @Autowired
    MedicalMapper medicalMapper;
    @Test
    void contextLoads() {
        for(int i=0;i<10;i++){
            Medical medical = new Medical();
            medical.setPrescription("nihao");
            medical.setDate("星期二");
            medical.setDoctorId(1024L);
            medical.setPatientId(1024L);
            medicalMapper.insert(medical);
        }
    }

}
