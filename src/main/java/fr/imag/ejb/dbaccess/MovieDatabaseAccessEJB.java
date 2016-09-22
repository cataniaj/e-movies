/*
 *	Author:      Jérémy Leyvraz
 *	Date:        22 sept. 2016
 */

package fr.imag.ejb.dbaccess;

import java.util.List;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import fr.imag.entities.Movie;

@Singleton
@Local
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class MovieDatabaseAccessEJB {
		@Inject EntityManager em;

		public synchronized List<Movie> findAllFilmInOurDataBase(){
	    	Query query = em.createQuery("SELECT m FROM Movie m");
	    	return (List<Movie>) query.getResultList();
		}
	}

