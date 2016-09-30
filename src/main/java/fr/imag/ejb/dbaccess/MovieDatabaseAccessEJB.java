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

import fr.imag.entities.Cart;
import fr.imag.entities.Movie;
import fr.imag.entities.MovieList;
import fr.imag.entities.Product;

/**
 * L'EJB qui gère les accès à la table Movie
 * @author Jerem
 *
 */
@Singleton
@Local
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class MovieDatabaseAccessEJB {
		@Inject EntityManager em;

		/**
		 * Recherche tous les films de la base
		 * @return La liste des films présents dans la base
		 */
		public synchronized List<Movie> findAllFilmInOurDataBase(){
	    	Query query = em.createQuery("SELECT m FROM Movie m");
	    	return (List<Movie>) query.getResultList();
		}

		/**
		 * Efface tous les films
		 */
		public synchronized void removeAllMovies(){
	    	Query query = em.createQuery("SELECT o FROM Movie o ");
	    	List<Movie> allMovie = query.getResultList();
	    	for(Movie m : allMovie){
	    		em.remove(m);
	    	}
		}
		
		/**
		 * Charge une liste de film dans la table
		 * @param movieList La liste des films a charger
		 */
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
		
		/**
		 * Renvoie la liste de tous les films
		 * @return
		 */
	    public synchronized MovieList allMovies(){
	    	Query query = em.createQuery("SELECT o FROM Movie o ");
	    	List<Movie> allMovie = query.getResultList();
	    	MovieList movies = new MovieList(allMovie);
	    	return movies;
	    }
	    
	    /**
	     * Efface la table Movie
	     */
	    public synchronized void clean(){
	    	em.createNativeQuery("TRUNCATE Table Product").executeUpdate();
	    }
	    
	    /**
	     * Mise a jour d'un produit
	     * @param idProduct Le produit concerne
	     * @param newStock Le nouveau stock
	     * @param newPrice Le nouveau prix
	     */
	    public synchronized void updateProduct(String idProduct, String newStock, String newPrice){
	    	Product p = (Product)em.find(Product.class, Integer.parseInt(idProduct));
	    	p.setStock(Integer.parseInt(newStock));
	    	p.setPrice(Integer.parseInt(newPrice));
	    }
	    
	    /**
	     * Recherche si un film est present dans la base
	     * @param idTMDB L'ID TMDB du film concerne
	     * @return true ou false
	     */
	    public synchronized boolean containsMovie(int idTMDB){
	    	MovieList movieList = allMovies();
	    	for(Movie m : movieList){
	    		if(m.getIdTMDB() == idTMDB){
	    			return true;
	    		}
	    	}
	    	return false;
	    }
	    
	    /**
	     * Renvoie le stock d'un produit
	     * @param idProduct Le produit concerne
	     * @return Le stock du produit
	     */
	    public int getStock(int idProduct){
			Query query = em.createQuery("SELECT m FROM Movie m "+
					"WHERE m.idProduct=:param");
			query.setParameter("param", idProduct);
			Movie m = (Movie) query.getSingleResult();
			return m.getStock();
	    }
}

