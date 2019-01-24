package com.labs.vsu.labProj;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labs.vsu.labProj.entity.*;
import com.labs.vsu.labProj.repository.ListProductRepository;
import com.labs.vsu.labProj.repository.ShoppingListRepository;
import com.labs.vsu.labProj.repository.UserRepository;
import com.labs.vsu.labProj.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Table;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LabProjApplicationTests {
	Logger logger = LoggerFactory.getLogger(LabProjApplicationTests.class);

	@Autowired
	PurchaseService purchaseService;
	@Autowired
	ShoppingListService shoppingListService;
	@Autowired
	ListProductService listProductService;
	@Autowired
	UserService userService;
	@Autowired
	ProductDescriptionService productDescriptionService;


	@Test
	public void contextLoads() {

//		purchaseService.save(new Purchase("somefffffname", "some vcvcv address"));
//		purchaseService.deleteByPurchaseAddress("some address");
//
//		logger.info("ok");


	}

	@Test
	public void addList(){
//		User user = new User("Ivan", "Ivaniov", "ivanedtr@mail.ru", "somePAssword123");
//		userService.save(user);
//		Purchase purchaseMagnit = new Purchase("oLeghgentrhzkaa", "Dodyjftelei, 1");
//		purchaseService.save(purchaseMagnit);
////
//		ProductDescription productDescription = new ProductDescription("fh Vkfgufksnoteevo", purchaseService.findByPurchaseName("oLeghgentrhzkaa"));
//		productDescriptionService.save(productDescription);
////
////		logger.info("product descr: " + productDescriptionService.findByProductName("Moloko Vkusnoteevo").toString());
//		ShoppingList shoppingList = new ShoppingList(user);
//		shoppingListService.save(shoppingList);
////
//		ListProduct list = new ListProduct(1, shoppingList, productDescription);
//		listProductService.save(list);

//		listProductService.deleteByListIdAndProductId(151,150);

//		ListProduct found = listProductService.findByListIdAndProductId(1231, 654);
//		ListProduct found = listProductService.findByListIdAndProductId(shoppingList.getListId(), productDescription.getProductId());
//		logger.info("found: " + found);

//		Purchase purchase = purchaseService.findByPurchaseId(101);
//		productDescriptionService.save(new ProductDescription("testNAkkkme", purchase));

//		ProductDescription newProd = productDescriptionService.findByProductId(124);
//		ProductDescription another = productDescriptionService.findByProductId(153);
//		List<ProductDescription> productDescriptions = new ArrayList<>();
//		productDescriptions.add(newProd); productDescriptions.add(another);
//		Purchase purchase = new Purchase(1232123, "serfhwrh", "dgthrthrs", productDescriptions);

//		User user1 = new User(12127121, "fgdgh", "rgwerg", "wdvkjhkdbsf@efg", "dfsrg");
//		ShoppingList list = new ShoppingList(23456, user1);
//		userService.save(user1);
//		shoppingListService.save(list);

//		User user = userService.findByUserId(81999907746);
//		userService.deleteById(152);

	}

}
