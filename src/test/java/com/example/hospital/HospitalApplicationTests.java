package com.example.hospital;

import com.example.hospital.entity.Medical;
import com.example.hospital.mapper.MedicalMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;

@SpringBootTest
class HospitalApplicationTests {
    @Autowired
    MedicalMapper medicalMapper;
    @Autowired
    RedisTemplate<String, String> redisTemplate;
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

    @Test
    void getPassword(){
        final String slat = "123@abc";
        String password="123456";
        password = DigestUtils.md5DigestAsHex((password + slat).getBytes());
        System.out.println(password);
    }

    @Test
    void deleteRedis(){
        redisTemplate.delete("TOKEN_eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NTUzMzE1MTYsInVzZXJJZCI6MSwiaWF0IjoxNjU0NDQyNDgzfQ.CdivM5eoRoCcTd3saM8F8-XosWzP194Cdp9R4yiBxjk");
    }

}
