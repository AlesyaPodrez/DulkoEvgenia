package com.springapp.mvc.domain;

/**
 * Created by Евгения on 08.05.2015.
 */

import java.util.Collection;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Евгения
 */
@Entity
@Table(name = "Client")
@XmlRootElement
public class Client {
    @Id
    @Column(name = "c_id")
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
    @Column(name = "c_surname")
    private String cSurname;
    @Basic(optional = false)
    @Column(name = "c_name")
    public String cName;
    @Column(name = "c_phone")
    private String cPhone;
    @Column(name = "c_adress")
    private String cAdress;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private Collection<User> userCollection;

    public Client() {
    }

    public Client(Integer cId) {
        this.setId(cId);
    }

    public Client(String cSurname, String cName, String cAdress) {
        this.cSurname = cSurname;
        this.cName = cName;
        this.cAdress = cAdress;
    }

    public String getCSurname() {
        return cSurname;
    }

    public void setCSurname(String cSurname) {
        this.cSurname = cSurname;
    }

    public String getCName() {
        return cName;
    }

    public void setCName(String cName) {
        this.cName = cName;
    }

    public String getCPhone() {
        return cPhone;
    }

    public void setCPhone(String cPhone) {
        this.cPhone = cPhone;
    }

    public String getCAdress() {
        return cAdress;
    }

    public void setCAdress(String cAdress) {
        this.cAdress = cAdress;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.cSurname);
        hash = 19 * hash + Objects.hashCode(this.cName);
        hash = 19 * hash + Objects.hashCode(this.cPhone);
        hash = 19 * hash + Objects.hashCode(this.cAdress);
        hash = 19 * hash + Objects.hashCode(this.userCollection);
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
        final Client other = (Client) obj;
        if (!Objects.equals(this.cSurname, other.cSurname)) {
            return false;
        }
        if (!Objects.equals(this.cName, other.cName)) {
            return false;
        }
        if (!Objects.equals(this.cPhone, other.cPhone)) {
            return false;
        }
        if (!Objects.equals(this.cAdress, other.cAdress)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Client{" + "cSurname=" + cSurname + ", cName=" + cName + ", cPhone=" + cPhone + ", cAdress=" + cAdress + '}';
    }



}