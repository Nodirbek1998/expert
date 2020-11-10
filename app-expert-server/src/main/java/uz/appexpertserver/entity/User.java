package uz.appexpertserver.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.appexpertserver.entity.enums.PersonType;
import uz.appexpertserver.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class User extends AbsEntity implements UserDetails {

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    private String middleName;

    @Column(unique = true, nullable = false )
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @Column(length = 9, unique = true, nullable = false)
    private String tin;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne(optional = false)
    private District district;

    @Enumerated(EnumType.STRING)
    private PersonType personType;

    @ManyToMany
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
