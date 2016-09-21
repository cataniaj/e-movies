package fr.imag.producers;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 * This class is used to provide the entity manager
 * as a dependency in the application through the CDI 
 * @author le06
 *
 */
public class EntityManagerProducer {
	@Produces
	@PersistenceContext
	private	EntityManager	em;
	
	@PostConstruct
	public void addManyFilm(){
		System.out.println("------------------------ ok -------------------");
	}
}
