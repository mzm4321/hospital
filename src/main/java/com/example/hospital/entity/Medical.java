package com.example.hospital.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2022-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("medical")
public class Medical implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "medical_id", type = IdType.ASSIGN_ID)
    private Long medicalId;

    private Long patientId;

    private Long doctorId;

    private String date;

    private String prescription;


}
