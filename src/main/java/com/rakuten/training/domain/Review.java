package com.rakuten.training.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Review {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;

  String author;
  String content;
  float stars;

  @ManyToOne  //Tells hibernate the type of relationship
  @JoinColumn(name="product_id")
  @JsonIgnore
  Product product;

  public Review() {}

  public Review(String author, String content, float stars) {
    super();
    this.author = author;
    this.content = content;
    this.stars = stars;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public float getStars() {
    return stars;
  }

  public void setStars(float stars) {
    this.stars = stars;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }
}
