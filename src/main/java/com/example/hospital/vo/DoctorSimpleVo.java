package com.example.hospital.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorSimpleVo {
    private Long doctorId;

    private String doctorName;

    private Integer doctorAge;

    private String title;

    private String doctorSex;

    private String departName;

    private String doctorImg;
}
