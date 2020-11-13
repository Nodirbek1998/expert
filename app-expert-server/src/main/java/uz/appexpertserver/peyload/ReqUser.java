package uz.appexpertserver.peyload;

import lombok.Data;
import uz.appexpertserver.entity.District;
import uz.appexpertserver.entity.Role;
import uz.appexpertserver.entity.enums.PersonType;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class ReqUser {
    @Pattern(regexp = "^[a-zA-Z]{3,20}",message = "3 tadan ko`p faqat harflarda kiriting")
    private String firstName, lastName;
    private String middleName;

    @Pattern(regexp = "^[+][9][9][8][0-9]{9}$",message = "Phone number must be 13 digits.")
    private String phoneNumber;

    @Pattern(regexp = "^(?:(?=.*?\\p{N})(?=.*?[\\p{S}\\p{P}])(?=.*?\\p{Lu})(?=.*?\\p{Ll}))[^\\p{C}]{4,16}$",message = "Uzunligi 8-16 oralig`ida.Parol kamida bitta katta harf, son va belgi bo`lishi shart.")
    private String password;
    @Pattern(regexp = "^[0-9]{9}$", message = "TIN must be 9 digits.")
    private String tin;
    @Email(message = "Email emasku bu oka")
    private String email;
    @NotNull
    private PersonType personType;


}
