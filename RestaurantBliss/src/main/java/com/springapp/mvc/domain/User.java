package com.springapp.mvc.domain;

/**
 * Created by Евгения on 08.05.2015.
 */

import java.util.Collection;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "user")
public class User{
    @Id
    @Column(name = "u_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic(optional = false)
    @Column(name = "u_login")
    private String uLogin;
    @Basic(optional = false)
    @Column(name = "u_password")
    private String uPassword;
    @Column(name = "enabled")
    private Short enabled;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uId")
    private Collection<Authority> authorityCollection;
    @JoinColumn(name = "c_id", referencedColumnName = "c_id")
    @ManyToOne(optional = false)
    private Client cId;

    public User() {
        this.enabled=1;
    }

    public User(Integer id) {
        this.setId(id);
    }

    public User(String uLogin, String uPassword, Client cId, Short enabled) {
        this.uLogin = uLogin;
        this.uPassword = uPassword;
        this.cId=cId;
        this.enabled=enabled;

    }

    public String getULogin() {
        return uLogin;
    }

    public void setULogin(String uLogin) {
        this.uLogin = uLogin;
    }

    public String getUPassword() {
        return uPassword;
    }

    public void setUPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public Client getCId() {
        return cId;
    }

    public void setCId(Client cId) {
        this.cId = cId;
    }

    public Short getEnabled() {
        return enabled;
    }

    public void setEnabled(Short enabled) {
        this.enabled = enabled;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.uLogin);
        hash = 23 * hash + Objects.hashCode(this.uPassword);
        hash = 23 * hash + Objects.hashCode(this.cId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.uLogin, other.uLogin)) {
            return false;
        }
        if (!Objects.equals(this.uPassword, other.uPassword)) {
            return false;
        }
        if (!Objects.equals(this.cId, other.cId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "uLogin=" + uLogin + ", uPassword=" + uPassword + ", cId=" + cId + '}';
    }


}
