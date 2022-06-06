package com.example.hospital.service;

import com.example.hospital.entity.Medical;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.hospital.vo.Result;
import com.example.hospital.vo.params.GuahaoParam;
import com.example.hospital.vo.params.MedicalParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2022-05-24
 */
public interface MedicalService extends IService<Medical> {

    Result getGuahao();

    Result getGuahaobyId(Long id);
    Result updatePrescription(MedicalParam medicalParam);

    Result deleteGuahaobyId(Long medicalId);

    Result deleteGuahaobyDoctorId(Long doctorId);

    void deleteGuahaobyPatientId(Long patientId);

    Result insertGuahao(GuahaoParam guahaoParam);
}
