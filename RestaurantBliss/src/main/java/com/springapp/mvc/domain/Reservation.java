package com.springapp.mvc.domain;

/**
 * Created by Евгения on 08.05.2015.
 */
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @Column(name = "r_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "r_date")
    //@Temporal(TemporalType.DATE)
    private String rDate;
    @Basic(optional = false)
    @Column(name = "r_time")
    //@Temporal(TemporalType.TIME)
    private String rTime;
    @JoinColumn(name = "t_id", referencedColumnName = "t_id")
    @ManyToOne(optional = false)
    private MyTable tId;
    @JoinColumn(name = "c_id", referencedColumnName = "c_id")
    @ManyToOne(optional = false)
    private Client cId;

    public Reservation() {
    }

    public Reservation(int id){
        this.setId(id);
    }

    public Reservation(String rDate, String rTime, Client cId, MyTable tId) {
        this.rDate = rDate;
        this.rTime = rTime;
        this.cId=cId;
        this.tId=tId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRDate() {
        return rDate;
    }

    public void setRDate(String rDate) {
        this.rDate = rDate;
    }

    public String getRTime() {
        return rTime;
    }

    public void setRTime(String rTime) {
        this.rTime = rTime;
    }

    public MyTable getTId() {
        return tId;
    }

    public void setTId(MyTable tId) {
        this.tId = tId;
    }

    public Client getCId() {
        return cId;
    }

    public void setCId(Client cId) {
        this.cId = cId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.rDate);
        hash = 71 * hash + Objects.hashCode(this.rTime);
        hash = 71 * hash + Objects.hashCode(this.tId);
        hash = 71 * hash + Objects.hashCode(this.cId);
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
        final Reservation other = (Reservation) obj;
        if (!Objects.equals(this.rDate, other.rDate)) {
            return false;
        }
        if (!Objects.equals(this.rTime, other.rTime)) {
            return false;
        }
        if (!Objects.equals(this.tId, other.tId)) {
            return false;
        }
        if (!Objects.equals(this.cId, other.cId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reservation{" + "rDate=" + rDate + ", rTime=" + rTime + ", tId=" + tId.getId() + ", cId=" + cId.getId() + '}';
    }


}
