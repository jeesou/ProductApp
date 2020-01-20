package com.rakuten.training.ui;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rakuten.training.domain.Product;
import com.rakuten.training.service.ProductService;

@Component
public class ProductConsoleUI {

  ProductService service; // = new ProductServiceImpl();

  @Autowired
  public void setService(ProductService service) {
    this.service = service;
  }

  Scanner sc = new Scanner(System.in);

  public void createProductWithUI() {
    System.out.println("Enter Name: ");
    String name = sc.nextLine();
    System.out.println("Enter Price: ");
    float price = sc.nextFloat();
    System.out.println("Enter QoH: ");
    int qoh = sc.nextInt();

    Product p = new Product(name, price, qoh);
    int id = service.addNewProduct(p);
    System.out.println("Create Product with Id: " + id);
  }

  public void deleteProductWithId() {
    System.out.println("Enter the id to be deleted : ");
    int id = sc.nextInt();
    service.removeProduct(id);
    System.out.println("Product deleted with Id "+id);
  }
}
