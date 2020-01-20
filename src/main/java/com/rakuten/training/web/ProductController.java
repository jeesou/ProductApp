package com.rakuten.training.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rakuten.training.domain.Product;
import com.rakuten.training.domain.Review;
import com.rakuten.training.service.ProductService;
import com.rakuten.training.service.ReviewService;

@RestController
public class ProductController {

  @Autowired ProductService service;
//  @Autowired ReviewService s;

  // @RequestMapping(method =RequestMethod.GET,value = "/products")
  @GetMapping("/products")
  public List<Product> getAllProducts() {
    return service.findAll(); // ByDefault returns a Jason array
  }

  @GetMapping("/products/{prodid}") // uri path template
  public ResponseEntity<Product> getProductById(@PathVariable("prodid") int id) {
    Product p = service.findById(id);
    if (p != null) {
      return new ResponseEntity<Product>(p, HttpStatus.OK);
    } else {
      return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/products")
  public ResponseEntity<Product> addProduct(@RequestBody Product toBeAdded) {
    try {
      int id = service.addNewProduct(toBeAdded);
      HttpHeaders headers = new HttpHeaders();
      headers.setLocation(URI.create("/products/" + id));
      return new ResponseEntity<Product>(headers, HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/products/{prodid}")
  public ResponseEntity<Product> deleteProduct(@PathVariable("prodid") int toBeDeleted) {
    try {
      service.removeProduct(toBeDeleted);
      return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    } catch (NullPointerException e) {
      return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
    } catch (IllegalStateException e) {
      return new ResponseEntity<Product>(HttpStatus.CONFLICT);
    }
  }

  /*@GetMapping("/products/{prodid}/reviews")
  public List<Review> getReviews(@PathVariable("prodid") int pid)
  {
   return s.findReviewByProdId(pid);
  }

  @PostMapping("/products/{prodid}/reviews")
  public ResponseEntity<Review> insertReview(@RequestBody Review toBeAdded, @PathVariable("prodid") int pid)
  {
   try {
   int id=s.addNewReview(toBeAdded,pid);
  // HttpHeaders header = new HttpHeaders();
      //header.setLocation(URI.create("/products/{prodid}/reviews" + id));
   return new ResponseEntity<Review>(HttpStatus.CREATED);
  } catch (NullPointerException e) {
   return new ResponseEntity<Review>(HttpStatus.NOT_FOUND);
  }
  }*/

}
