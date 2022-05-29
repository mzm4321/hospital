package com.example.hospital.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientVo {
    private Long patientId;

    private String patientName;

    private String patientSex;

    private Integer patientAge;

    private String history;
}
