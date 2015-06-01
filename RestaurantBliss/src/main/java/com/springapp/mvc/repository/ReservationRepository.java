package com.springapp.mvc.repository;

import com.springapp.mvc.domain.Reservation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Евгения on 08.05.2015.
 */
@Repository
@Transactional
public class ReservationRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public void addReservation(Reservation reservation){
        this.sessionFactory.getCurrentSession().save(reservation);
    }

    public List<Reservation> listAll(){
        return this.sessionFactory.getCurrentSession().createQuery("from Reservation").list();
    }

    public void removeReservation(Integer iD){
        Reservation reservation = (Reservation)this.sessionFactory.getCurrentSession().get(Reservation.class, iD);
        if (null!=reservation){
            this.sessionFactory.getCurrentSession().delete(reservation);
        }
    }

    public Reservation findReservation(Integer iD) {
        Reservation reservation = (Reservation) this.sessionFactory.getCurrentSession().get(Reservation.class, iD);
        return reservation;
    }

    public List<Reservation> findReservationByDate(String date, String checkIn, String checkOut) {
        AtomicReference<String> HQLQuery = new AtomicReference<String>("from Reservation r where r.rDate ='" + date + "' and (r.rTime BETWEEN '" + checkIn + "' AND '" + checkOut + "')");
        return this.sessionFactory.getCurrentSession().createQuery(HQLQuery.get()).list();
    }

    public List<Reservation> findReservationByClient(Integer iD) {
        AtomicReference<String> HQLQuery = new AtomicReference<String>("from Reservation r where r.cId="+iD);
        return this.sessionFactory.getCurrentSession().createQuery(HQLQuery.get()).list();
    }

    public void updateDish(Reservation dish){
        this.sessionFactory.getCurrentSession().saveOrUpdate(dish);
    }
}
