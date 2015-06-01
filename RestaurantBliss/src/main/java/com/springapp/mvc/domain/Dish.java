package com.springapp.mvc.domain;

/**
 * Created by Евгения on 08.05.2015.
 */

import java.util.Collection;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "dish")
public class Dish{
    @Id
    @Column(name = "d_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "d_name")
    private String dName;
    @Basic(optional = false)
    @Column(name = "d_composition")
    private String dComposition;
    @Basic(optional = false)
    @Column(name = "d_mass")
    private String dMass;
    @Basic(optional = false)
    @Column(name = "d_price")
    private int dPrice;
    @Basic(optional = false)
    @Column(name = "d_picture")
    private String dPicture;
    @JoinColumn(name = "dt_id", referencedColumnName = "dt_id")
    @ManyToOne(optional = false)
    private DishType dtId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dish", fetch = FetchType.LAZY)
    private Collection<OrderDish> orderDishCollection;

    public Dish() {
    }

    public Dish(int id){
        this.setId(id);
    }

    public Dish(String dName, String dComposition, String dMass, int dPrice, DishType dtId, String dPicture) {
        this.dName = dName;
        this.dComposition = dComposition;
        this.dMass = dMass;
        this.dPrice = dPrice;
        this.dtId = dtId;
        this.dPicture = dPicture;
    }

    public Dish(Integer id, String dName, String dComposition, String dMass, int dPrice, DishType dtId, String dPicture) {
        this.id=id;
        this.dName = dName;
        this.dComposition = dComposition;
        this.dMass = dMass;
        this.dPrice = dPrice;
        this.dtId = dtId;
        this.dPicture = dPicture;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDName() {
        return dName;
    }

    public void setDName(String dName) {
        this.dName = dName;
    }

    public String getDComposition() {
        return dComposition;
    }

    public void setDComposition(String dComposition) {
        this.dComposition = dComposition;
    }

    public String getDMass() {
        return dMass;
    }

    public void setDMass(String dMass) {
        this.dMass = dMass;
    }

    public int getDPrice() {
        return dPrice;
    }

    public void setDPrice(int dPrice) {
        this.dPrice = dPrice;
    }

    public DishType getDtId() {
        return dtId;
    }

    public void setDtId(DishType dtId) {
        this.dtId = dtId;
    }

    public String getDPicture() {
        return dPicture;
    }

    public void setDPicture(String dPicture) {
        this.dPicture = dPicture;
    }

    @XmlTransient
    public Collection<OrderDish> getOrderDishCollection() {
        return orderDishCollection;
    }

    public void setOrderDishCollection(Collection<OrderDish> orderDishCollection) {
        this.orderDishCollection = orderDishCollection;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.dName);
        hash = 23 * hash + Objects.hashCode(this.dComposition);
        hash = 23 * hash + Objects.hashCode(this.dMass);
        hash = 23 * hash + this.dPrice;
        hash = 23 * hash + Objects.hashCode(this.dtId);
        hash = 23 * hash + Objects.hashCode(this.orderDishCollection);
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
        final Dish other = (Dish) obj;
        if (!Objects.equals(this.dName, other.dName)) {
            return false;
        }
        if (!Objects.equals(this.dComposition, other.dComposition)) {
            return false;
        }
        if (!Objects.equals(this.dMass, other.dMass)) {
            return false;
        }
        if (this.dPrice != other.dPrice) {
            return false;
        }
        if (!Objects.equals(this.dtId, other.dtId)) {
            return false;
        }
        if (!Objects.equals(this.orderDishCollection, other.orderDishCollection)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dish{" + "dName=" + dName + ", dComposition=" + dComposition + ", dMass=" + dMass + ", dPrice=" + dPrice + ", dtId=" + dtId + '}';
    }



}