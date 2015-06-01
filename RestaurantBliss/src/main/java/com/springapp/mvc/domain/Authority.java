package com.springapp.mvc.domain;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Евгения on 24.05.2015.
 */
@Entity
@Table(name = "authority")
@XmlRootElement
public class Authority{
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "au_id")
    private Integer auId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "authority")
    private String authority;
    @JoinColumn(name = "u_id", referencedColumnName = "u_id")
    @ManyToOne(optional = false)
    private User uId;

    public Authority() {
    }

    public Authority(Integer auId) {
        this.auId = auId;
    }

    public Authority(String authority, User uId) {
        this.authority = authority;
        this.uId=uId;
    }

    public Integer getAuId() {
        return auId;
    }

    public void setAuId(Integer auId) {
        this.auId = auId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public User getUId() {
        return uId;
    }

    public void setUId(User uId) {
        this.uId = uId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Authority)) return false;

        Authority authority1 = (Authority) o;

        if (!auId.equals(authority1.auId)) return false;
        if (!authority.equals(authority1.authority)) return false;
        if (!uId.equals(authority1.uId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = auId.hashCode();
        result = 31 * result + authority.hashCode();
        result = 31 * result + uId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "auId=" + auId +
                ", authority='" + authority + '\'' +
                ", uId=" + uId +
                '}';
    }
}