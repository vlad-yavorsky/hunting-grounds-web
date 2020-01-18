package ua.vlad.hg.web.dto;

import ua.vlad.hg.core.util.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String email;
    private String username;
    private String password;
    private Date created;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private Set<Role> role;

}
