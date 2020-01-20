package com.rakuten.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.training.dal.ProductDAO;
import com.rakuten.training.dal.ReviewDAO;
import com.rakuten.training.domain.Product;
import com.rakuten.training.domain.Review;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

  ReviewDAO dao;
  @Autowired ProductDAO pdao;

  @Autowired
  public void setDao(ReviewDAO dao) {
    this.dao = dao;
  }

  @Override
  public int addNewReview(Review toBeAdded, int productId) {
    /*Product p = pdao.findById(id);
    if (p != null) {
      toBeAdded.setProduct(p);
      Review added = dao.save(toBeAdded);
      return added.getId();
    } else throw new NullPointerException();*/
	  
	  Product product = pdao.findById(productId);
	    if (product == null) {
	      throw new NoSuchProductException();
	    }
	    toBeAdded.setProduct(product);
	    Review added = dao.save(toBeAdded);
	    return added.getId();
	  
  }

  @Override
  public void removeReview(int id) {
    Review existing = dao.findById(id);

    if (existing != null) {
      dao.deleteById(id);
    } else throw new NullPointerException();
  }

  @Override
  public List<Review> findReviewByProdId(int pid) {
    return dao.findReviewByProdId(pid);
  }

  @Override
  public Review findById(int id) {
    return dao.findById(id);
  }

  @Override
  public List<Review> findAll() { // TODO Auto-generated method stub
    return dao.findAll();
  }
}
