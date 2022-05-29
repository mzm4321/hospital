package com.example.hospital.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
@TableName("patient")
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "patient_id", type = IdType.ASSIGN_ID)
    private Long patientId;

    private String patientName;

    private String patientSex;

    private Integer patientAge;

    private String history;


}
