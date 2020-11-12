package uz.appexpertserver.peyload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqPayment {

    private Integer payType;
    private double amount;
    private UUID project;
    private String payDate;

}
