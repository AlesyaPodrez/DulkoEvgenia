package com.springapp.mvc.repository;

import com.springapp.mvc.domain.MyTable;
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
public class MyTableRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public void addTable(MyTable table){
        this.sessionFactory.getCurrentSession().save(table);
    }

    public List<MyTable> listAll(){
        return this.sessionFactory.getCurrentSession().createQuery("from MyTable ").list();
    }

    public void removeTable(Integer iD){
        MyTable table = (MyTable)this.sessionFactory.getCurrentSession().load(MyTable.class, iD);
        if (null!=table){
            this.sessionFactory.getCurrentSession().delete(table);
        }
    }

    public MyTable findTable(Integer iD) {
        MyTable table = (MyTable) this.sessionFactory.getCurrentSession().get(MyTable.class, iD);
        return table;
    }

    public void updateTable(MyTable table){
        this.sessionFactory.getCurrentSession().saveOrUpdate(table);
    }

}
