package com.springapp.mvc.repository;

import com.springapp.mvc.domain.Dish;
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
public class DishRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void addDish(Dish dish){
        this.sessionFactory.getCurrentSession().save(dish);
    }

    public List<Dish> listAll(){
        return this.sessionFactory.getCurrentSession().createQuery("from Dish").list();
    }

    public void removeDish(Integer iD){
        Dish dish = (Dish)this.sessionFactory.getCurrentSession().load(Dish.class, iD);
        if (null!=dish){
            this.sessionFactory.getCurrentSession().delete(dish);
        }
    }

    public Dish findDish(Integer iD) {
        Dish dish = (Dish) this.sessionFactory.getCurrentSession().get(Dish.class, iD);
        return dish;
    }

    public List<Dish> findDishByName(String name) {
        String HQLQuery ="from Dish d where d.dName like '%"+name+"%'";
        return this.sessionFactory.getCurrentSession().createQuery(HQLQuery).list();
    }

    public void updateDish(Dish dish){
        this.sessionFactory.getCurrentSession().saveOrUpdate(dish);
    }

}
