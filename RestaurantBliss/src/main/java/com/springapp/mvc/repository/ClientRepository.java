package com.springapp.mvc.repository;

import com.springapp.mvc.domain.Client;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
@Transactional
public class ClientRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void addClient(Client client){
        this.sessionFactory.getCurrentSession().save(client);
    }

    public List<Client> listAll(){
        List<Client> clients = this.sessionFactory.getCurrentSession().createQuery("from Client").list();
        return clients;
    }

    public void removeClient(Integer iD){
        Client client = (Client)this.sessionFactory.getCurrentSession().load(Client.class, iD);
        if (null!=client){
            System.out.println(client.getCName());
            this.sessionFactory.getCurrentSession().delete(client);
        }
    }

    public Client findClient(Integer iD) {
        Client client = (Client) this.sessionFactory.getCurrentSession().get(Client.class, iD);
            return client;
    }

    public void updateClient(Client client){
        this.sessionFactory.getCurrentSession().saveOrUpdate(client);
    }

}
