package com.springapp.mvc.repository;

import com.springapp.mvc.domain.DishType;
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
public class DishTypeRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void addDishType(DishType dishType){
        this.sessionFactory.getCurrentSession().save(dishType);
    }

    public List<DishType> listAll(){
        return this.sessionFactory.getCurrentSession().createQuery("from DishType").list();
    }

    public void removeDishType(Integer iD){
        DishType dishType  = (DishType)this.sessionFactory.getCurrentSession().load(DishType.class, iD);
        if (null!=dishType){
            this.sessionFactory.getCurrentSession().delete(dishType);
        }
    }

    public DishType findDishType(Integer iD) {
        List<DishType> dishTypes = (List<DishType>) this.sessionFactory.getCurrentSession().createQuery("from DishType dt where dt.id="+iD).list();
        return dishTypes.get(0);
    }

    public DishType findDishTypeByName(String typeName) {
        DishType dishType = (DishType) this.sessionFactory.getCurrentSession().createQuery("from DishType dt where dt.dtName="+typeName);
        return dishType;
    }

    public void updateDishType(DishType dishType){
        this.sessionFactory.getCurrentSession().saveOrUpdate(dishType);
    }
}
