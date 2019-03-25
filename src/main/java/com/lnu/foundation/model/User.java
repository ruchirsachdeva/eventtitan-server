package com.lnu.foundation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(value = {"users", "organizations"}, ignoreUnknown = true)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String email;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
    private Set<Organization> organizations = new HashSet<>();


    private Double lat;
    @Column(name = "`long`")
    private Double longitude;

    private String firstName;
    private String lastName;
    private String password;
    private String passwordConfirm;

    public void addOrganization(Organization organization) {
        organizations.add(organization);
        organization.setProvider(this);
    }

    public void removeOrganization(Organization organization) {
        organizations.remove(organization);
    }


    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(() -> getRole() != null ? addRolePrefixWithUnderscore() : "ROLE_USER");
    }

    private String addRolePrefixWithUnderscore() {
        return "ROLE_" + getRole().getName().replaceAll(" ", "_").toUpperCase();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }


    public String toString() {
        return "User(userId=" + this.getUserId() + ", username=" + this.getUsername() + ", email=" + this.getEmail() + ", role=" + this.getRole() + ", lat=" + this.getLat() + ", longitude=" + this.getLongitude() + ", firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ", password=" + this.getPassword() + ", passwordConfirm=" + this.getPasswordConfirm() + ")";
    }

    public boolean isProvider() {
        return "provider".equalsIgnoreCase(getRole().getName());
    }
    public boolean isClient() {
        return "client".equalsIgnoreCase(getRole().getName());
    }
}
