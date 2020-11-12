package uz.appexpertserver.peyload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqProjectChat {
    private UUID project;
    private UUID attachment;
    private String request;
    private String response;
    private UUID byClient;
    private boolean responded;


}
