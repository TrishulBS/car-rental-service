package com.trishul.model.account;

import com.trishul.model.common.Address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contact {
    private String phone;
    private String email;
    private Address address;
    private PersonalInfo personalInfo;
}
