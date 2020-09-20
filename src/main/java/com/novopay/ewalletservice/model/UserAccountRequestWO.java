package com.novopay.ewalletservice.model;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserAccountRequestWO {
    @NotNull(message = "firstName can't be null!")
    @NotEmpty(message = "firstName can't be Empty!")
    @Size(min = 3,max = 30)
    private String firstName;
    @NotNull(message = "lastName can't be null!")
    @NotEmpty(message = "lastName can't be Empty!")
    @Size(min = 3,max = 30)
    private String lastName;
    @NotNull(message = "Email can't be null!")
    @NotEmpty(message = "Email can't be Empty!")
    @Email(message = "Email is not valid")
    private String email;
    @NotNull(message = "mobileNumber can't be null!")
    @NotEmpty(message = "mobileNumber can't be Empty!")
    @Size(max = 10,min = 10)
    private String mobileNumber;

}
