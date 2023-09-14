package com.trishul.model.account;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public abstract  class Account {
    private String id;
    private String email;
    private String userName;
    private String password;
    private LocalDateTime lastAccessed;
    private Contact contact;

}
