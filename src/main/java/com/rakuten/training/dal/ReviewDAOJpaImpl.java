package com.rakuten.training.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.training.domain.Product;
import com.rakuten.training.domain.Review;

@Repository
@Transactional
public class ReviewDAOJpaImpl implements ReviewDAO {

  @Autowired EntityManager em;

  @Override
  public Review findById(int id) {
    return em.find(Review.class, id);
  }

  @Override
  public Review save(Review toBeSaved) {
    // Product p = em.find(Product.class, productId);

    // if(p!=null) {
    // toBeSaved.setProduct(p);
    em.persist(toBeSaved);
    return toBeSaved;
    //  }
    // else
    //	throw new NullPointerException();

  }

  @Override
  public void deleteById(int id) {
    Review r = em.find(Review.class, id);
    em.remove(r);
  }

  @Override
  public List<Review> findReviewByProdId(int productId) {
    /*Product p = em.find(Product.class, pid);
    if (p != null) {
      Query q = em.createQuery("select r from Review r where r.product.id=:idparam");
      q.setParameter("idparam", pid);
      List<Review> all = q.getResultList();
      return all;
    } else throw new NullPointerException();*/
	  TypedQuery<Review> q = em.createQuery("select r from Review r where r.product.id=:id", Review.class) ;
	    q.setParameter("id", productId);
	    return q.getResultList();
  }

  @Override
  public List<Review> findAll() {

    Query q = em.createQuery("select r from Review as r");
    List<Review> all = q.getResultList();
    return all;
  }
}
