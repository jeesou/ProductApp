package com.rakuten.training.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.Mockito;

import com.rakuten.training.dal.ProductDAO;
import com.rakuten.training.domain.Product;

public class ProductServiceImplTest {

  @Test
  public void addNewProduct_Returns_Valid_Id_When_ProductValue_GTEQ_MinValue() {
	  //Arrange
	  ProductServiceImpl service=new ProductServiceImpl();
	  Product toBeAdded=new Product("test", 10000, 1);//notice 10000*1>=10000
	  ProductDAO mockDAO=Mockito.mock(ProductDAO.class);
	  Product saved=new Product("test", 10000, 1);
	  saved.setId(1);
	  Mockito.when(mockDAO.save(toBeAdded)).thenReturn(saved);
	  service.setDao(mockDAO);
	  //Act
	  int id=service.addNewProduct(toBeAdded);
	  //Assert
	  assertTrue(id>0);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void addNewProduct_Returns_Valid_Id_When_ProductValue_LT_MinValue()
  {
	  //Arrange
	  ProductServiceImpl service=new ProductServiceImpl();
	  Product toBeAdded=new Product("test", 9999, 1);//notice 9999*1>=9999
	  //Act
	  service.addNewProduct(toBeAdded);
	  //Assert
  }
  
  @Test
  public void removeProduct_Actually_Deletes()
  {
	  ProductDAO mockDAO = Mockito.mock(ProductDAO.class);
	  ProductServiceImpl service = new ProductServiceImpl();
	  Product deleted = new Product("test", 200, 1);
	  int deletionId = 1;
	  deleted.setId(deletionId);
	  
	  service.setDao(mockDAO);
	  Mockito.when(mockDAO.findById(deletionId)).thenReturn(deleted);
	  //Act
	  service.removeProduct(deletionId);
	  Mockito.verify(mockDAO, Mockito.times(1)).deleteById(deletionId);
  }
  
@Test(expected=IllegalStateException.class)
public void removeProduct_Dosent_Deletes()
{
	//Arrange
	  ProductDAO mockDAO = Mockito.mock(ProductDAO.class);
	  ProductServiceImpl service = new ProductServiceImpl();
	  Product deleted = new Product("test", 1000000000, 1);
	  int deletionId = 1;
	  deleted.setId(deletionId);
	  
	  service.setDao(mockDAO);
	  Mockito.when(mockDAO.findById(deletionId)).thenReturn(deleted);
	  //Act
	  service.removeProduct(deletionId);
//	  Mockito.verify(mockDAO, Mockito.times(1)).deleteById(deletionId);
}

@Test
public void findById()
{
	ProductDAO mockDAO = Mockito.mock(ProductDAO.class);
	  ProductServiceImpl service = new ProductServiceImpl();
	  Product toBeFound = new Product("test", 200, 1);
	  int findId = 1;
	  toBeFound.setId(findId);
	  
	  service.setDao(mockDAO);
	  Mockito.when(mockDAO.findById(findId)).thenReturn(toBeFound);
	  
	  Product p=service.findById(findId);
	  
	  assertTrue(toBeFound.equals(p));
}
  
}
