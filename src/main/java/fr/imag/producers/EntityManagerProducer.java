package fr.imag.producers;

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
}
