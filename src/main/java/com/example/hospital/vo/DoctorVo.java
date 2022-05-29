package com.example.hospital.vo;

import lombok.Data;

@Data
public class DoctorVo {
    private Long doctorId;

    private String doctorName;

    private Integer doctorAge;

    private String history;

    private String title;

    private boolean stop;

    private String[] hospitalTime;

    private String doctorDetail;

    private String doctorSex;

    private String doctorImg;

    private Long departId;
}
