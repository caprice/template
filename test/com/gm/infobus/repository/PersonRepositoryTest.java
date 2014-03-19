package com.gm.infobus.repository;

import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gm.infobus.entity.Person;


public class PersonRepositoryTest extends BaseConfigurtionTest {
	  @Autowired  
	  private PersonRepository personRepository;
	  @Test
	  public void testPerson(){
	    	UUID uuid=UUID.randomUUID();
	    	Person person=new Person();
	    	person.setId(uuid);
	    	person.setName("Otávio Santana");
	    	person.setYear(25);
	    	personRepository.save(person);
	    	Person otavio=personRepository.findOne(uuid);
	    	System.out.println(otavio.getName());
	  }
}
