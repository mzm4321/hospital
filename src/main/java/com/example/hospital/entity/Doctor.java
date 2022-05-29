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
@TableName("doctor")
public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "doctor_id", type = IdType.ASSIGN_ID)
    private Long doctorId;

    private String doctorName;

    private Integer doctorAge;

    private String history;

    private String title;

    private Integer stop;

    private String hospitalTime;

    private String doctorDetail;

    private String doctorSex;

    private String doctorImg;

    private Long departId;

}
