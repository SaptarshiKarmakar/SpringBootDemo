package com.demo15.util;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeDto {
    private long id;
    @NotBlank
    @Size(min = 3, message = "At least 3 character Required")
    private String name;
    @Email
    private String email;
    @Size(min = 10, max = 10, message = "Should be 10 digits")
    private String number;
    private String dept;
}
