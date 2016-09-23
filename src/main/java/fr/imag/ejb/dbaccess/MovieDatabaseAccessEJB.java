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
import fr.imag.entities.MovieList;
import fr.imag.entities.Product;

@Singleton
@Local
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class MovieDatabaseAccessEJB {
		@Inject EntityManager em;

		public synchronized List<Movie> findAllFilmInOurDataBase(){
	    	Query query = em.createQuery("SELECT m FROM Movie m");
	    	return (List<Movie>) query.getResultList();
		}

		public synchronized void removeAllMovies(){
	    	Query query = em.createQuery("SELECT o FROM Movie o ");
	    	List<Movie> allMovie = query.getResultList();
	    	for(Movie m : allMovie){
	    		em.remove(m);
	    	}
		}
		
		public synchronized void chargeDatabase(MovieList movieList){
			for(Movie m : movieList){
		    	Query query = em.createQuery("SELECT o FROM Movie o ");
		    	List<Movie> allMovies = query.getResultList();
		    	boolean find = false;
		    	for(Movie movie : allMovies){
		    		if(m.getIdTMDB() == movie.getIdTMDB()){
		    			find = true;
		    			break;
		    		}
		    	}
		    	if(!find){
					em.persist(m);
		    	}
				find = false;
			}
		}
	    public synchronized MovieList allMovies(){
	    	Query query = em.createQuery("SELECT o FROM Movie o ");
	    	List<Movie> allMovie = query.getResultList();
	    	MovieList movies = new MovieList(allMovie);
	    	return movies;
	    }
	    
	    public synchronized void cleanMovies(){
	    	em.createNativeQuery("TRUNCATE Table Product").executeUpdate();
	    	
	    }
	    
	    public synchronized void updateProduct(String idProduct, String newStock, String newPrice){
	    	Product p = (Product)em.find(Product.class, Integer.parseInt(idProduct));
	    	p.setStock(Integer.parseInt(newStock));
	    	p.setPrice(Integer.parseInt(newPrice));
	    }
	    
	    public synchronized boolean containsMovie(int idTMDB){
	    	MovieList movieList = allMovies();
	    	for(Movie m : movieList){
	    		if(m.getIdTMDB() == idTMDB){
	    			return true;
	    		}
	    	}
	    	return false;
	    }
}

