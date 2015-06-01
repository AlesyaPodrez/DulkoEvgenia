package com.springapp.mvc.repository;

/**
 * Created by Евгения on 24.05.2015.
 */

import com.springapp.mvc.domain.Authority;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Евгения on 08.05.2015.
 */
@Repository
@Transactional
public class AuthorityRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void addAuthority(Authority authority){
        this.sessionFactory.getCurrentSession().save(authority);
    }

    public List<Authority> listAll(){
        return this.sessionFactory.getCurrentSession().createQuery("from Authority").list();
    }

    public void removeAuthority(Integer iD){
        Authority authority = (Authority)this.sessionFactory.getCurrentSession().load(Authority.class, iD);
        if (null!=authority){
            this.sessionFactory.getCurrentSession().delete(authority);
        }
    }

    public Authority findAuthority(Integer iD) {
        Authority authority = (Authority) this.sessionFactory.getCurrentSession().get(Authority.class, iD);
        return authority;
    }

    public void updateAuthority(Authority authority){
        this.sessionFactory.getCurrentSession().saveOrUpdate(authority);
    }

}

