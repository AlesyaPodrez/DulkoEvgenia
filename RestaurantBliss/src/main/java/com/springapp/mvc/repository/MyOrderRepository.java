package com.springapp.mvc.repository;

import com.springapp.mvc.domain.MyOrder;
import com.springapp.mvc.domain.User;
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
public class MyOrderRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public void addOrder(MyOrder order){
        this.sessionFactory.getCurrentSession().save(order);
    }

    public List<MyOrder> listAll(){
        return this.sessionFactory.getCurrentSession().createQuery("from MyOrder ").list();
    }

    public void removeOrder(Integer iD){
        MyOrder order = (MyOrder)this.sessionFactory.getCurrentSession().load(MyOrder.class, iD);
        if (null!=order){
            this.sessionFactory.getCurrentSession().delete(order);
        }
    }

    public MyOrder findOrder(Integer iD) {
        MyOrder order = (MyOrder) this.sessionFactory.getCurrentSession().get(MyOrder.class, iD);
        return order;
    }

    public List<MyOrder> findOrderByClient(Integer iD) {
        AtomicReference<String> HQLQuery = new AtomicReference<String>("from MyOrder where cId like "+iD);
        List<MyOrder> orders = this.sessionFactory.getCurrentSession().createQuery(HQLQuery.get()).list();
        return orders;
    }

    public void updateOrder(MyOrder order){
        this.sessionFactory.getCurrentSession().saveOrUpdate(order);
    }
}
