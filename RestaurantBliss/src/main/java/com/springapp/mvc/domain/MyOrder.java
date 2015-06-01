package com.springapp.mvc.domain;

import javax.persistence.*;

/**
 * Created by Евгения on 08.05.2015.
 */
import java.sql.Time;
import java.util.Date;
import java.util.Collection;
import java.util.Objects;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "myorder")
public class MyOrder{
    @Id
    @Column(name = "o_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "o_delivery_time")
    private String oDeliveryTime;
    @Basic(optional = false)
    @Column(name = "o_delivery_date")
    private String oDeliveryDate;
    @JoinColumn(name = "c_id", referencedColumnName = "c_id")
    @ManyToOne(optional = false)
    private Client cId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "myOrder", fetch = FetchType.EAGER)
    private Collection<OrderDish> orderDishCollection;

    public MyOrder() {
    }

    public MyOrder(int id){
        this.setId(id);
    }

    public MyOrder(String oDeliveryDate, String oDeliveryTime, Client cId) {
        this.oDeliveryDate = oDeliveryDate;
        this.oDeliveryTime = oDeliveryTime;
        this.cId=cId;
    }

    public String getODeliveryTime() {
        return oDeliveryTime;
    }

    public void setODeliveryTime(String oDeliveryTime) {
        this.oDeliveryTime = oDeliveryTime;
    }

    public String getODeliveryDate() {
        return oDeliveryDate;
    }

    public void setODeliveryDate(String oDeliveryDate) {
        this.oDeliveryDate = oDeliveryDate;
    }

    public Client getCId() {
        return cId;
    }

    public void setCId(Client cId) {
        this.cId = cId;
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
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.oDeliveryTime);
        hash = 47 * hash + Objects.hashCode(this.oDeliveryDate);
        hash = 47 * hash + Objects.hashCode(this.cId);
        hash = 47 * hash + Objects.hashCode(this.orderDishCollection);
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
        final MyOrder other = (MyOrder) obj;
        if (!Objects.equals(this.oDeliveryTime, other.oDeliveryTime)) {
            return false;
        }
        if (!Objects.equals(this.oDeliveryDate, other.oDeliveryDate)) {
            return false;
        }
        if (!Objects.equals(this.cId, other.cId)) {
            return false;
        }
        if (!Objects.equals(this.orderDishCollection, other.orderDishCollection)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MyOrder{" + "oDeliveryTime=" + oDeliveryTime + ", oDeliveryDate=" + oDeliveryDate + ", cId=" + cId  + '}';
    }

}
