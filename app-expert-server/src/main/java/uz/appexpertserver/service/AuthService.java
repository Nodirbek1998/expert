package uz.appexpertserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.appexpertserver.entity.User;
import uz.appexpertserver.entity.enums.PersonType;
import uz.appexpertserver.entity.enums.RoleName;
import uz.appexpertserver.peyload.ApiResponse;
import uz.appexpertserver.peyload.ReqUser;
import uz.appexpertserver.repository.DistrictRepository;
import uz.appexpertserver.repository.RoleRepository;
import uz.appexpertserver.repository.UserRepository;

import java.util.Collections;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    RoleRepository roleRepository;

    public ApiResponse register(ReqUser reqUser){
        if (!userRepository.existsByPhoneNumberEqualsIgnoreCase(reqUser.getPhoneNumber())){
            if (!userRepository.existsByEmailEqualsIgnoreCase(reqUser.getEmail())){
                if (!userRepository.existsByTin(reqUser.getTin())){
                    User user = new User(
                            reqUser.getFirstName(),
                            reqUser.getLastName(),
                            reqUser.getMiddleName(),
                            reqUser.getPhoneNumber(),
                            passwordEncoder.encode(reqUser.getPassword()),
                            reqUser.getTin(),
                            reqUser.getEmail(),
                            PersonType.PHYSICAL ,
                            Collections.singleton(roleRepository.findByRoleName(RoleName.USER_ROLE))
                    );
                    userRepository.save(user);
                    return new ApiResponse(true,"Saqlandi");
                }
                return new ApiResponse(false,"Tin mavjud");
            }
            return new ApiResponse(false,"email mavjud");
        }
        return new ApiResponse(false,"tel nomer mavjud");
    }
}
