package com.novopay.ewalletservice.model;
import lombok.Data;
@Data
public class UserAccountRequestWO {
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;

}
