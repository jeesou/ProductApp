package com.rakuten.training;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.rakuten.training.dal.ProductDAO;

@SpringBootApplication
public class ProductAppApplication {

  public static void main(String[] args) {
    // ApplicationContext springContainer =
    SpringApplication.run(ProductAppApplication.class, args);
    /* ProductConsoleUI ui=springContainer.getBean(ProductConsoleUI.class);
    ui.createProductWithUI();
    ui.deleteProductWithId();*/

    /*ReviewDAO reviewDAO = springContainer.getBean(ReviewDAO.class);
    Review sample = new Review("def", "this is average", 3);
    Review saved = reviewDAO.save(sample,2);
    System.out.println("Created Review with id: " + saved.getId());*/

    //    Product p = productDAO.findById(2);
    //    System.out.println(p.getName());
    //    System.out.println("This product has "+p.getReviews().size()+" reviews");

    //    List<Product> p=productDAO.findAll();
    //    System.out.println("The list of products is : ");
    //    for(Product pro:p)
    //    	System.out.println(pro.getName());

    /*ProductDAO productDAO = springContainer.getBean(ProductDAO.class);
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the id for deletion : ");
    int id = sc.nextInt();
    productDAO.deleteById(id);
    sc.close();*/
  }
}
