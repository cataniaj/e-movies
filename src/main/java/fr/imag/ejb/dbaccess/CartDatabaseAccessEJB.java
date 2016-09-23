/*
 *	Author:      Jérémy Leyvraz
 *	Date:        23 sept. 2016
 */

package fr.imag.ejb.dbaccess;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Singleton
@Local
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class CartDatabaseAccessEJB {

	@Inject EntityManager em;
	
	
	
	
	
	
}
