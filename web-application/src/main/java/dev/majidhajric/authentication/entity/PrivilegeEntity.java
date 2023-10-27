package dev.majidhajric.authentication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PrivilegeEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long id;

    @ToString.Include
    @Column(unique = true, nullable = false)
    private String authority;

    @ManyToMany(mappedBy = "privileges")
    private Set<RoleEntity> roleEntities = new HashSet<>();
}
