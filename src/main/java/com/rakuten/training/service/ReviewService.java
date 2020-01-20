package com.rakuten.training.service;

import java.util.List;

import com.rakuten.training.domain.Review;


public interface ReviewService {
	
	int addNewReview(Review toBeAdded,int id);
	void removeReview(int id);
	List<Review> findReviewByProdId(int id);
	List<Review> findAll();
	Review findById(int id);
	
}
