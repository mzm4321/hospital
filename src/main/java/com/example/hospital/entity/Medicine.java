package com.example.hospital.entity;

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
 * @since 2022-05-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("medicine")
public class Medicine implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "medicine_id", type = IdType.ASSIGN_ID)
    private Long medicineId;

    private String medicineName;

    private Integer medicineNum;


}
