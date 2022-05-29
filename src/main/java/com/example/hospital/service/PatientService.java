package com.example.hospital.service;

import com.example.hospital.entity.Patient;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.hospital.vo.Result;
import com.example.hospital.vo.params.PatientHistoryParam;
import com.example.hospital.vo.params.PatientParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2022-05-24
 */
public interface PatientService extends IService<Patient> {
    String getPatientName(Long id);

    Result findPatients();

    Result insertPatient(PatientParam patientParam);

    Result deletePatientbyId(Long patientId);

    Result findPatientbyId(Long patientId);

    Result updateHistory(PatientHistoryParam patientHistoryParam);

    Result updatePatient(Long patientId, PatientParam patientParam);

    Result queryPatients(String query);
}
