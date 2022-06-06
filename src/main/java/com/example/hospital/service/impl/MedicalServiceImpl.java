package com.example.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.hospital.entity.Medical;
import com.example.hospital.mapper.MedicalMapper;
import com.example.hospital.service.DoctorService;
import com.example.hospital.service.MedicalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hospital.service.PatientService;
import com.example.hospital.vo.MedicalVo;
import com.example.hospital.vo.Result;
import com.example.hospital.vo.params.GuahaoParam;
import com.example.hospital.vo.params.MedicalParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2022-05-24
 */
@Service
public class MedicalServiceImpl extends ServiceImpl<MedicalMapper, Medical> implements MedicalService {
    @Autowired
    private MedicalMapper medicalMapper;
    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;
    @Override
    public Result getGuahao() {
        List<Medical> medicals = medicalMapper.selectList(new LambdaQueryWrapper<>());
        return Result.success(copyList(medicals));
    }

    @Override
    public Result getGuahaobyId(Long id) {
        LambdaQueryWrapper<Medical> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Medical::getMedicalId,id);
        Medical medical = medicalMapper.selectOne(lambdaQueryWrapper);
        return Result.success(copy(medical));
    }

    @Override
    public Result updatePrescription(MedicalParam medicalParam) {
        LambdaQueryWrapper<Medical> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Medical::getMedicalId,medicalParam.getMedicalId());
        Medical medical = medicalMapper.selectOne(lambdaQueryWrapper);
        System.out.println(medical.getMedicalId());
        medical.setPrescription(medicalParam.getPrescription());
        int i = medicalMapper.updateById(medical);
        return Result.success(copy(medical));
    }

    @Override
    public Result deleteGuahaobyId(Long medicalId) {
        return Result.success(medicalMapper.deleteById(medicalId));
    }

    @Override
    public Result deleteGuahaobyDoctorId(Long doctorId) {
        LambdaQueryWrapper<Medical> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Medical::getDoctorId,doctorId);
        medicalMapper.delete(lambdaQueryWrapper);
        return Result.success(null);
    }

    @Override
    public void deleteGuahaobyPatientId(Long patientId) {
        LambdaQueryWrapper<Medical> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Medical::getPatientId,patientId);
        medicalMapper.delete(lambdaQueryWrapper);
    }

    @Override
    public Result insertGuahao(GuahaoParam guahaoParam) {
        Medical medical = new Medical();
        medical.setPatientId(guahaoParam.getPatientId());
        medical.setDoctorId(guahaoParam.getDoctorId());
        medical.setDate(guahaoParam.getDate());
        medicalMapper.insert(medical);
        return Result.success(null);
    }

    public MedicalVo copy(Medical medical){
        MedicalVo medicalVo=new MedicalVo();
        medicalVo.setMedicalId(medical.getMedicalId());
        medicalVo.setPatientName(patientService.getPatientName(medical.getPatientId()));
        medicalVo.setDoctorName(doctorService.getDoctorName(medical.getDoctorId()));
        medicalVo.setDate(medical.getDate());
        medicalVo.setPrescription(medical.getPrescription());
        return medicalVo;
    }

    public List<MedicalVo> copyList(List<Medical> medicals){
        ArrayList<MedicalVo> medicalVos = new ArrayList<>();
        for (int i = 0; i < medicals.size(); i++) {
            medicalVos.add(copy(medicals.get(i)));
        }
        return medicalVos;
    }
}
