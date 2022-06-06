package com.example.hospital.service;

import com.example.hospital.entity.Doctor;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.hospital.vo.Result;
import com.example.hospital.vo.params.DoctorParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2022-05-24
 */
public interface DoctorService extends IService<Doctor> {
    Result findDoctors();
    Result findDoctorsbyDeparId(Long id);

    String getDoctorName(Long id);

    Result insertDoctor(DoctorParam doctorParam);

    Result deleteDoctorbyId(Long doctorId);

    Result findDoctor(Long dotocrId);

    Result updateDoctorbyId(Long doctorId, DoctorParam doctorParam);

    Result findNoStopDoctorsbyDeparId(Long departId);
}
