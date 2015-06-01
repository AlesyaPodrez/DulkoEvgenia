package com.springapp.mvc.domain;

/**
 * Created by Евгения on 08.05.2015.
 */
import java.util.Collection;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "order_dish")
public class OrderDish{
    @Id
    @Column(name = "od_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "o_num_of_dish")
    private int oNumOfDish;
    @JoinColumn(name = "o_id", referencedColumnName = "o_id")
    @ManyToOne(optional = false)
    private MyOrder myOrder;
    @JoinColumn(name = "d_id", referencedColumnName = "d_id")
    @ManyToOne(optional = false)
    private Dish dish;

    public OrderDish() {
    }

    public OrderDish(int oNumOfDish, MyOrder order, Dish dish) {
        this.oNumOfDish = oNumOfDish;
        this.myOrder=order;
        this.dish=dish;

    }

    public int getONumOfDish() {
        return oNumOfDish;
    }

    public void setONumOfDish(int oNumOfDish) {
        this.oNumOfDish = oNumOfDish;
    }

    public MyOrder getMyOrder() {
        return myOrder;
    }

    public void setMyOrder(MyOrder myOrder) {
        this.myOrder = myOrder;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.oNumOfDish;
        hash = 71 * hash + Objects.hashCode(this.myOrder);
        hash = 71 * hash + Objects.hashCode(this.dish);
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
        final OrderDish other = (OrderDish) obj;
        if (this.oNumOfDish != other.oNumOfDish) {
            return false;
        }
        if (!Objects.equals(this.myOrder, other.myOrder)) {
            return false;
        }
        if (!Objects.equals(this.dish, other.dish)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OrderDish{" + "oNumOfDish=" + oNumOfDish + ", myOrder=" + myOrder + ", dish=" + dish + '}';
    }



}
