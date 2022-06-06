package com.example.hospital.service;

import com.example.hospital.entity.Medicine;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.hospital.vo.Result;
import com.example.hospital.vo.params.MedicineParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2022-05-30
 */
public interface MedicineService extends IService<Medicine> {

    Result findMedicines();

    Result addMedicine(MedicineParam medicineParam);

    Result findMedicinebyId(Long medicineId);

    Result updateMedicine(Long medicineId, MedicineParam medicineParam);

    Result deleteMedicine(Long medicineId);

    Result queryMedicine(String query);
}
