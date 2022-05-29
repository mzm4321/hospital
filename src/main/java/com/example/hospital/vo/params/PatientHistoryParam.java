package com.example.hospital.vo.params;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class PatientHistoryParam {
    Long patientId;
    String history;
}
