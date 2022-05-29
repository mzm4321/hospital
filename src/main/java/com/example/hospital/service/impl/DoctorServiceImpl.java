package com.example.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.hospital.entity.Doctor;
import com.example.hospital.mapper.DoctorMapper;
import com.example.hospital.service.DepartmentService;
import com.example.hospital.service.DoctorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hospital.service.MedicalService;
import com.example.hospital.vo.DoctorSimpleVo;
import com.example.hospital.vo.DoctorVo;
import com.example.hospital.vo.Result;
import com.example.hospital.vo.params.DoctorParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor> implements DoctorService {
    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private DepartmentService departmentService;
    @Lazy
    @Autowired
    private MedicalService medicalService;
    @Override
    public Result findDoctors() {
        LambdaQueryWrapper<Doctor> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByDesc(Doctor::getDoctorAge);
        List<Doctor> doctors = doctorMapper.selectList(lambdaQueryWrapper);
        return Result.success(copySimpleList(doctors));
    };

    public Result findDoctorsbyDeparId(Long departId){
        if(departId==0){
            return findDoctors();
        }else{
            LambdaQueryWrapper<Doctor> lambdaQueryWrapper=new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Doctor::getDepartId,departId);
            lambdaQueryWrapper.orderByDesc(Doctor::getDoctorAge);
            List<Doctor> doctors = doctorMapper.selectList(lambdaQueryWrapper);
            return Result.success(copySimpleList(doctors));
        }

    }

    @Override
    public String getDoctorName(Long id) {
        LambdaQueryWrapper<Doctor> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Doctor::getDoctorId,id);
        return doctorMapper.selectOne(lambdaQueryWrapper).getDoctorName();
    }

    @Override
    public Result insertDoctor(DoctorParam doctorParam) {
        Doctor doctor=new Doctor();
        doctor.setDoctorDetail(doctorParam.getDoctorDetail());
        doctor.setDoctorAge(doctorParam.getDoctorAge());
        doctor.setDoctorImg(doctorParam.getDoctorImg());
        doctor.setDoctorName(doctorParam.getDoctorName());
        doctor.setDoctorSex(doctorParam.getDoctorSex());
        doctor.setHistory(doctorParam.getHistory());
        doctor.setDepartId(doctorParam.getDepartId());
        doctor.setTitle(doctorParam.getTitle());
        String[] paramHospitalTime=doctorParam.getHospitalTime();
        StringBuffer sb=new StringBuffer();
        if(paramHospitalTime!=null&&paramHospitalTime.length>=1){
            for (int i = 0; i < paramHospitalTime.length-1; i++) {
                sb.append(paramHospitalTime[i]);
                sb.append(',');
            }
            sb.append(paramHospitalTime[paramHospitalTime.length-1]);
        }
        doctor.setHospitalTime(sb.toString());
        Integer isStop;
        if(doctorParam.getStop()==false){
            isStop=0;
        }else {
            isStop=1;
        }
        doctor.setStop(isStop);
        doctorMapper.insert(doctor);
        return Result.success(null);
    }


    @Override
    public Result deleteDoctorbyId(Long doctorId) {
        doctorMapper.deleteById(doctorId);
        medicalService.deleteGuahaobyDoctorId(doctorId);
        return Result.success(null);
    }

    @Override
    public Result findDoctor(Long dotocrId) {
        LambdaQueryWrapper<Doctor> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Doctor::getDoctorId,dotocrId);
        return Result.success(copy(doctorMapper.selectOne(lambdaQueryWrapper)));
    }

    @Override
    public Result updateDoctorbyId(Long doctorId, DoctorParam doctorParam) {
        Doctor doctor = new Doctor();
        doctor.setDoctorId(doctorId);
        doctor.setDoctorSex(doctorParam.getDoctorSex());
        doctor.setDoctorName(doctorParam.getDoctorName());
        doctor.setDoctorImg(doctorParam.getDoctorImg());
        doctor.setDoctorAge(doctorParam.getDoctorAge());
        doctor.setTitle(doctorParam.getTitle());
        doctor.setDepartId(doctorParam.getDepartId());
        System.out.println(doctorParam.getStop());
        Integer isStop=doctorParam.getStop()?1:0;
        doctor.setStop(isStop);
        doctor.setDoctorDetail(doctorParam.getDoctorDetail());
        doctor.setHistory(doctorParam.getHistory());
        String[] paramHospitalTime=doctorParam.getHospitalTime();
        StringBuffer sb=new StringBuffer();
        if(paramHospitalTime!=null&&paramHospitalTime.length>=1){
            for (int i = 0; i < paramHospitalTime.length-1; i++) {
                sb.append(paramHospitalTime[i]);
                sb.append(',');
            }
            sb.append(paramHospitalTime[paramHospitalTime.length-1]);
        }
        doctor.setHospitalTime(sb.toString());
        doctorMapper.updateById(doctor);
        return Result.success(null);
    }

    public DoctorSimpleVo copySimple(Doctor doctor){
        DoctorSimpleVo doctorSimpleVo = new DoctorSimpleVo();
        doctorSimpleVo.setDepartName(departmentService.getById(doctor.getDepartId()).getDepartName());
        doctorSimpleVo.setDoctorName(doctor.getDoctorName());
        doctorSimpleVo.setDoctorId(doctor.getDoctorId());
        doctorSimpleVo.setDoctorAge(doctor.getDoctorAge());
        doctorSimpleVo.setDoctorSex(doctor.getDoctorSex());
        doctorSimpleVo.setTitle(doctor.getTitle());
        doctorSimpleVo.setDoctorImg(doctor.getDoctorImg());
        return doctorSimpleVo;
    }

    public List<DoctorSimpleVo> copySimpleList(List<Doctor> doctors){
        ArrayList<DoctorSimpleVo> doctorSimpleVos = new ArrayList<>();
        for (int i = 0; i < doctors.size(); i++) {
            doctorSimpleVos.add(copySimple(doctors.get(i)));
        }
        return doctorSimpleVos;
    }

    public DoctorVo copy(Doctor doctor){
        DoctorVo doctorVo=new DoctorVo();
        doctorVo.setDepartId(doctor.getDepartId());
        doctorVo.setDoctorName(doctor.getDoctorName());
        doctorVo.setDoctorDetail(doctor.getDoctorDetail());
        doctorVo.setDoctorId(doctor.getDoctorId());
        doctorVo.setDoctorAge(doctor.getDoctorAge());
        doctorVo.setDoctorSex(doctor.getDoctorSex());
        doctorVo.setDoctorImg(doctor.getDoctorImg());
        doctorVo.setHistory(doctor.getHistory());
        String time=doctor.getHospitalTime();
        String[] hostpitalTime=time.split(",");
        doctorVo.setHospitalTime(hostpitalTime);
        System.out.println(doctor.getStop());
        if(doctor.getStop()==0){
            doctorVo.setStop(false);
        }else{
            doctorVo.setStop(true);
        }
        doctorVo.setTitle(doctor.getTitle());
        return doctorVo;
    }

    public List<DoctorVo> copyList(List<Doctor> doctors){
        ArrayList<DoctorVo> doctorVos = new ArrayList<>();
        for (int i = 0; i < doctors.size(); i++) {
            doctorVos.add(copy(doctors.get(i)));
        }
        return doctorVos;
    }
}
