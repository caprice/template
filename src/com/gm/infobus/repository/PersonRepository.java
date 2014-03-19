package com.gm.infobus.repository;

import java.util.UUID;

import org.easycassandra.persistence.cassandra.spring.CassandraRepository;
import org.easycassandra.persistence.cassandra.spring.CassandraTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gm.infobus.entity.Person;

@Repository("personRepository")
public class PersonRepository extends CassandraRepository<Person, UUID> {

	@Autowired
	private CassandraTemplate cassandraTemplate;

	@Override
	protected CassandraTemplate getCassandraTemplate() {
		return cassandraTemplate;
	}

}