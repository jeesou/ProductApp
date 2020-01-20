package com.rakuten.training.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.training.domain.Product;

@Repository
@Transactional
public class ProductDAOJpaImpl implements ProductDAO{

	@Autowired
	EntityManager em;
  @Override
  public Product save(Product toBeSaved) {
	  
	  em.persist(toBeSaved);
	    return toBeSaved;
  }

  @Override
  public Product findById(int id) {
    return em.find(Product.class, id);
  }

  @Override
  public List<Product> findAll() {
	  
	  Query q=em.createQuery("select p from Product as p");
	  List<Product> all=q.getResultList(); 
	  
    return all;
  }

  @Override
  public void deleteById(int id) {
    //  Product p=em.find(Product.class, id);
    //  em.remove(p);

    /*Query q =em.createQuery("delete from Product as p where p.id=:idparam");
    q.setParameter("idparam", id);
    q.executeUpdate();*/

    /*Product p=em.find(Product.class,id);
    em.remove(p);*/

    Product p = em.getReference(Product.class, id);  //for avoiding lazy loading
	  em.remove(p);
  }
}
