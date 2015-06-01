package com.springapp.mvc.repository;

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
public class UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void addUser(User user){
        this.sessionFactory.getCurrentSession().save(user);
    }

    public List<User> listAll(){
        return this.sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    public void removeUser(Integer iD){
        User user = (User)this.sessionFactory.getCurrentSession().load(User.class, iD);
        if (null!=user){
            this.sessionFactory.getCurrentSession().delete(user);
        }
    }

    public User findUser(Integer iD) {
        User user = (User) this.sessionFactory.getCurrentSession().get(User.class, iD);
        return user;
    }

    public User findUserByLogin(String login) {
        AtomicReference<String> HQLQuery = new AtomicReference<String>("from User u where u.uLogin ='"+login+"'");
        try {
            User user = (User) this.sessionFactory.getCurrentSession().createQuery(HQLQuery.get()).list().get(0);
            return user;
        } catch (IndexOutOfBoundsException ex){
            return null;
        }
    }

    public void updateUser(User user){
        this.sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

}
