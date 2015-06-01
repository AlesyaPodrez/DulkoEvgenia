package com.springapp.mvc.repository;

import com.springapp.mvc.domain.Client;
import com.springapp.mvc.domain.OrderDish;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Евгения on 08.05.2015.
 */
@Repository
@Transactional
public class OrderDishRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public void addOrderDish(OrderDish orderDish){
        this.sessionFactory.getCurrentSession().save(orderDish);
    }

    public List<OrderDish> listAll(){
        return this.sessionFactory.getCurrentSession().createQuery("from OrderDish ").list();
    }

    public void removeOrderDish(Integer iD){
        OrderDish orderDish = (OrderDish)this.sessionFactory.getCurrentSession().load(OrderDish.class, iD);
        if (null!=orderDish){
            this.sessionFactory.getCurrentSession().delete(orderDish);
        }
    }

    public OrderDish findOrderDish(Integer iD) {
        OrderDish orderDish = (OrderDish) this.sessionFactory.getCurrentSession().get(OrderDish.class, iD);
        return orderDish;
    }

    public void updateOrderDish(OrderDish orderDish){
        this.sessionFactory.getCurrentSession().saveOrUpdate(orderDish);
    }
}
