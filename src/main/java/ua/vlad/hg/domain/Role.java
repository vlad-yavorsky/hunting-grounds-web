package ua.vlad.hg.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Role {

    public enum Type implements GrantedAuthority {
        ADMIN,
        MODERATOR,
        USER;

        @Override
        public String getAuthority() {
            return name();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_seq")
    @SequenceGenerator(name = "role_id_seq", sequenceName = "role_id_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 20)
    private String name;
}