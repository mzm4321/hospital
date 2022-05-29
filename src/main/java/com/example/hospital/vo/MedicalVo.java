package com.example.hospital.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalVo {
    private Long medicalId;
    private String patientName;
    private String doctorName;
    private String date;
    private String prescription;
}
