package com.springapp.mvc.domain;

/**
 * Created by Евгения on 08.05.2015.
 */
import java.util.Collection;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "dish_type")
public class DishType{
    @Id
    @Column(name = "dt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic(optional = false)
    @Column(name = "dt_name")
    private String dtName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dtId", fetch = FetchType.EAGER)
    private Collection<Dish> dishCollection;

    public DishType() {
    }

    public DishType(int id){
        this.setId(id);
    }

    public DishType(int id, String dtName) {
        this.setId(id);
        this.dtName = dtName;
    }

    public String getDtName() {
        return dtName;
    }

    public void setDtName(String dtName) {
        this.dtName = dtName;
    }

    @XmlTransient
    public Collection<Dish> getDishCollection() {
        return dishCollection;
    }

    public void setDishCollection(Collection<Dish> dishCollection) {
        this.dishCollection = dishCollection;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.dtName);
        hash = 41 * hash + Objects.hashCode(this.dishCollection);
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
        final DishType other = (DishType) obj;
        if (!Objects.equals(this.dtName, other.dtName)) {
            return false;
        }
        if (!Objects.equals(this.dishCollection, other.dishCollection)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DishType{" + "dtName=" + dtName + '}';
    }


}
