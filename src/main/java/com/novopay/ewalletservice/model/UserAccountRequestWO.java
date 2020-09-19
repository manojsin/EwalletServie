package com.novopay.ewalletservice.model;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class UserAccountRequestWO {
    private Long id;
    private String userName;
    private String email;
    private Date dateCreated;
    private BigDecimal balance;
    public UserAccountRequestWO() {
    }
    public UserAccountRequestWO(UserAccountBuilder builder) {
        this.id = builder.id;
        this.userName = builder.userName;
        this.email = builder.email;
        this. dateCreated = builder.dateCreated;
        this.balance = builder.balance;
    }
    @Data
    public static class UserAccountBuilder {
        private Long id;
        private String userName;
        private String email;
        private Date dateCreated;
        private BigDecimal balance;
    }
}
