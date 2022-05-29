package com.example.hospital.vo.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientParam {

    private String patientName;

    private String patientSex;

    private Integer patientAge;

    private String history;

}
