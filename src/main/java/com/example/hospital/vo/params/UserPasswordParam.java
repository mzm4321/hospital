package com.example.hospital.vo.params;

import lombok.Data;

@Data
public class UserPasswordParam {
    String oldPassword;
    String newPassword;
}
