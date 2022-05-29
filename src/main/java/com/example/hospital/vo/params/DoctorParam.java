package com.example.hospital.vo.params;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorParam {
    String doctorName;
    Integer doctorAge;
    String history;
    String title;
    Boolean stop;
    String[] hospitalTime;
    String doctorDetail;
    String doctorSex;
    String doctorImg;
    Long departId;
}
