package ua.vlad.hg.core.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.UserDetails;
import ua.vlad.hg.core.util.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table
@EqualsAndHashCode(of = {"id"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name = User.Graph.ROLE, attributeNodes = @NamedAttributeNode(value = "role"))
public class User implements UserDetails {

    public static final class Graph {
        public static final String ROLE = "user.role";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    private Long id;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss") // todo: (001) fix created date
    private Date created;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

    @ElementCollection(targetClass = Role.class)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> role;

    @Override
    public Collection<Role> getAuthorities() {
        return role;
    }

}
