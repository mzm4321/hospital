package com.example.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.hospital.entity.Patient;
import com.example.hospital.mapper.PatientMapper;
import com.example.hospital.service.MedicalService;
import com.example.hospital.service.PatientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hospital.vo.PatientVo;
import com.example.hospital.vo.Result;
import com.example.hospital.vo.params.PatientHistoryParam;
import com.example.hospital.vo.params.PatientParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author author
 * @since 2022-05-24
 */
@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient> implements PatientService {
    @Autowired
    private PatientMapper patientMapper;
    @Lazy
    @Autowired
    private MedicalService medicalService;
    @Override
    public String getPatientName(Long id) {
        LambdaQueryWrapper<Patient> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Patient::getPatientId, id);
        return patientMapper.selectOne(lambdaQueryWrapper).getPatientName();
    }

    @Override
    public Result findPatients() {
        LambdaQueryWrapper<Patient> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        return Result.success(copyList(patientMapper.selectList(lambdaQueryWrapper)));
    }

    @Override
    public Result insertPatient(PatientParam patientParam) {
        Patient patient = new Patient();
        patient.setPatientAge(patientParam.getPatientAge());
        patient.setPatientName(patientParam.getPatientName());
        patient.setPatientSex(patientParam.getPatientSex());
        patient.setHistory(patientParam.getHistory());
        patientMapper.insert(patient);
        return Result.success(null);
    }

    @Override
    public Result deletePatientbyId(Long patientId) {
        LambdaQueryWrapper<Patient> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Patient::getPatientId, patientId);
        patientMapper.deleteById(patientId);
        medicalService.deleteGuahaobyPatientId(patientId);
        return Result.success(null);
    }

    @Override
    public Result findPatientbyId(Long patientId) {
        LambdaQueryWrapper<Patient> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Patient::getPatientId,patientId);
        return Result.success(copy(patientMapper.selectOne(lambdaQueryWrapper)));
    }

    @Override
    public Result updateHistory(PatientHistoryParam patientHistoryParam) {
        LambdaQueryWrapper<Patient> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Patient::getPatientId,patientHistoryParam.getPatientId());
        Patient patient = patientMapper.selectOne(lambdaQueryWrapper);
        patient.setHistory(patientHistoryParam.getHistory());
        patientMapper.updateById(patient);
        return Result.success(null);
    }

    @Override
    public Result updatePatient(Long patientId, PatientParam patientParam) {
        Patient patient=new Patient();
        patient.setPatientId(patientId);
        patient.setPatientName(patientParam.getPatientName());
        patient.setPatientAge(patientParam.getPatientAge());
        patient.setPatientSex(patientParam.getPatientSex());
        patient.setHistory(patientParam.getHistory());
        patientMapper.updateById(patient);
        return Result.success(null);
    }

    @Override
    public Result queryPatients(String query) {
        LambdaQueryWrapper<Patient> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Patient::getPatientName,query);
        return Result.success(copyList(patientMapper.selectList(lambdaQueryWrapper)));
    }

    private PatientVo copy(Patient patient) {
        PatientVo patientVo = new PatientVo();
        BeanUtils.copyProperties(patient, patientVo);
        return patientVo;
    }

    private List<PatientVo> copyList(List<Patient> patients){
        ArrayList<PatientVo> patientVos = new ArrayList<>();
        for (int i = 0; i < patients.size(); i++) {
            patientVos.add(copy(patients.get(i)));
        }
        return patientVos;
    }


}
