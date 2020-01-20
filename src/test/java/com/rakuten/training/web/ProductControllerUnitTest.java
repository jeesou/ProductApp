package com.rakuten.training.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.hamcrest.CoreMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakuten.training.domain.Product;
import com.rakuten.training.service.ProductService;
// import com.rakuten.training.service.ReviewService;

@RunWith(SpringRunner.class)
@WebMvcTest({ProductController.class})
public class ProductControllerUnitTest {

  @Autowired MockMvc mockMvc;

  @MockBean ProductService service;
  //  @MockBean ReviewService s;

  @Test
  public void getProductById_Returns_OK_With_Correct_Product_If_Found() throws Exception {
    // Arrange
    Product found = new Product("test", 123.0f, 100);
    int id = 1;
    found.setId(id);
    Mockito.when(service.findById(id)).thenReturn(found);
    // Act & Assert
    mockMvc
        .perform(MockMvcRequestBuilders.get("/products/{id}", id))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(id)));
  }

  @Test
  public void getProductById_Returns_Not_Found() throws Exception {
    // Arrange
    Product found = new Product("test", 123.0f, 100);
    int id = 1;
    found.setId(id);
    Mockito.when(service.findById(id)).thenReturn(found);
    // Act & Assert
    mockMvc
        .perform(MockMvcRequestBuilders.get("/products/{id}", id + 1))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  public void addProduct_Returns_Created_If_Condition_Satisfied() throws Exception {
    // Arrange
    Product toBeAdded = new Product("test", 140.0f, 200);
    int id = 1;
    toBeAdded.setId(id);
    Mockito.when(service.addNewProduct(Mockito.any(Product.class))).thenReturn(id);
    ObjectMapper mapper = new ObjectMapper();
    // Act & Assert
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(toBeAdded)))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.header().string("location", "/products/" + id));

    Mockito.verify(service, Mockito.times(1)).addNewProduct(Mockito.any(Product.class));
  }

  @Test
  public void addProduct_Returns_Bad_Request() throws Exception {
    // Arrange
    Product toBeAdded = new Product("test", 140.0f, 200);
    int id = 1;
    toBeAdded.setId(id);
    Mockito.when(service.addNewProduct(Mockito.any(Product.class)))
        .thenThrow(new IllegalArgumentException());
    ObjectMapper mapper = new ObjectMapper();
    // Act & Assert
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(toBeAdded)))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());

    Mockito.verify(service, Mockito.times(1)).addNewProduct(Mockito.any(Product.class));
  }

  @Test
  public void deleteProduct_Returns_No_Content() throws Exception {
    Product toBeAdded = new Product("test", 140.0f, 200);
    int id = 1;
    toBeAdded.setId(id);
    Mockito.doNothing().when(service).removeProduct(id);
    mockMvc
        .perform(MockMvcRequestBuilders.delete("/products/{prodid}", id))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
  }
  
  @Test
  public void deleteProduct_Returns_Not_Found() throws Exception {
    Product toBeAdded = new Product("test", 140.0f, 200);
    int id = 1;
    toBeAdded.setId(id);
    Mockito.doNothing().when(service).removeProduct(id);
    mockMvc
        .perform(MockMvcRequestBuilders.delete("/products/{prodid}", id+1))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }
  
}
