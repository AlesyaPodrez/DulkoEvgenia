package com.springapp.mvc.domain;

/**
 * Created by Евгения on 08.05.2015.
 */
import java.util.Collection;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "mytable")
public class MyTable {
    @Id
    @Column(name = "t_id")
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
    @Column(name = "t_num_of_seats")
    private int tNumOfSeats;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tId")
    private Collection<Reservation> reservationCollection;

    public MyTable() {
    }

    public MyTable(int id){
        this.setId(id);
    }

    public MyTable(int id, int tNumOfSeats) {
        this.setId(id);
        this.tNumOfSeats = tNumOfSeats;
    }

    public int getTNumOfSeats() {
        return tNumOfSeats;
    }

    public void setTNumOfSeats(int tNumOfSeats) {
        this.tNumOfSeats = tNumOfSeats;
    }

    @XmlTransient
    public Collection<Reservation> getReservationCollection() {
        return reservationCollection;
    }

    public void setReservationCollection(Collection<Reservation> reservationCollection) {
        this.reservationCollection = reservationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.tNumOfSeats;
        hash = 47 * hash + Objects.hashCode(this.reservationCollection);
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
        final MyTable other = (MyTable) obj;
        if (this.tNumOfSeats != other.tNumOfSeats) {
            return false;
        }
        if (!Objects.equals(this.reservationCollection, other.reservationCollection)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MyTable{" + "tNumOfSeats=" + tNumOfSeats + '}';
    }

}
