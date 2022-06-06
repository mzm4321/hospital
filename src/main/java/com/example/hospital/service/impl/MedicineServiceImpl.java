package com.example.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.hospital.entity.Medicine;
import com.example.hospital.mapper.MedicineMapper;
import com.example.hospital.service.MedicineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hospital.vo.MedicineVo;
import com.example.hospital.vo.Result;
import com.example.hospital.vo.params.MedicineParam;
import org.springframework.beans.BeanUtils;
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
 * @since 2022-05-30
 */
@Service
public class MedicineServiceImpl extends ServiceImpl<MedicineMapper, Medicine> implements MedicineService {
    @Autowired
    private MedicineMapper medicineMapper;
    @Override
    public Result findMedicines() {
        return Result.success(copyList(medicineMapper.selectList(null)));
    }

    @Override
    public Result addMedicine(MedicineParam medicineParam) {
        LambdaQueryWrapper<Medicine> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Medicine::getMedicineName,medicineParam.getMedicineName());
        Medicine medicine = medicineMapper.selectOne(lambdaQueryWrapper);
        if(medicine==null){
            medicine=new Medicine();
            medicine.setMedicineName(medicineParam.getMedicineName());
            medicine.setMedicineNum(medicineParam.getMedicineNum());
            medicineMapper.insert(medicine);
        }else{
            medicine.setMedicineNum(medicine.getMedicineNum()+medicineParam.getMedicineNum());
            medicineMapper.updateById(medicine);
        }
        return Result.success(null);
    }

    @Override
    public Result findMedicinebyId(Long medicineId) {
        LambdaQueryWrapper<Medicine> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Medicine::getMedicineId,medicineId);
        return Result.success(copy(medicineMapper.selectOne(lambdaQueryWrapper)));
    }

    @Override
    public Result updateMedicine(Long medicineId, MedicineParam medicineParam) {
        Medicine medicine = new Medicine();
        medicine.setMedicineId(medicineId);
        medicine.setMedicineName(medicineParam.getMedicineName());
        medicine.setMedicineNum(medicineParam.getMedicineNum());
        medicineMapper.updateById(medicine);
        return Result.success(null);
    }

    @Override
    public Result deleteMedicine(Long medicineId) {
        medicineMapper.deleteById(medicineId);
        return Result.success(null);
    }

    @Override
    public Result queryMedicine(String query) {
        LambdaQueryWrapper<Medicine> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Medicine::getMedicineName,query);
        return Result.success(copyList(medicineMapper.selectList(lambdaQueryWrapper)));
    }

    public MedicineVo copy(Medicine medicine){
        MedicineVo medicineVo = new MedicineVo();
        BeanUtils.copyProperties(medicine,medicineVo);
        return medicineVo;
    }

    public List<MedicineVo> copyList(List<Medicine> medicineList){
        List<MedicineVo> medicineVos = new ArrayList<>();
        for (int i = 0; i < medicineList.size(); i++) {
            medicineVos.add(copy(medicineList.get(i)));
        }
        return medicineVos;
    }
}
