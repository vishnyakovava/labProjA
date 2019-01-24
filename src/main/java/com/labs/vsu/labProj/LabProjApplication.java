package com.labs.vsu.labProj;

import com.labs.vsu.labProj.entity.User;
import com.labs.vsu.labProj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class LabProjApplication implements CommandLineRunner{

	@Autowired
	UserRepository repository;
//	@Autowired
//	ShoppingListRepository shoppingListRepository;

	public static void main(String[] args) {
		SpringApplication.run(LabProjApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//repository.deleteAll();
		String information = "";
		for(User user: repository.findAll()){
			 information+="User(" + user.getUserId()
					 + "): first name - " + user.getUserFirstName()
					 + ", last name - " + user.getUserLastName()
					 + ", email - " + user.getUserEmail();
		}
	}
}
