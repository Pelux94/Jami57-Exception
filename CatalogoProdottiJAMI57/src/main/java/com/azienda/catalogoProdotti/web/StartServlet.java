package com.azienda.catalogoProdotti.web;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.azienda.catalogoProdotti.businessLogic.BusinessLogic;
import com.azienda.catalogoProdotti.dao.ItemDao;
import com.azienda.catalogoProdotti.dao.ProfileDao;
import com.azienda.catalogoProdotti.dao.ShoppingCartDao;
import com.azienda.catalogoProdotti.dao.UserDao;
import com.azienda.catalogoProdotti.utility.Costanti;


@WebServlet(value="/StartServlet", loadOnStartup=1)
public class StartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException{
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogo_prodotti");
		EntityManager em = emf.createEntityManager();
		UserDao uDao = new UserDao(em);
		ItemDao iDao = new ItemDao(em);
		ProfileDao pDao = new ProfileDao(em);
		ShoppingCartDao scDao = new ShoppingCartDao(em);
		
		try {
			BusinessLogic bl = new BusinessLogic(em, pDao, uDao, iDao, scDao);
			getServletContext().setAttribute(Costanti.BUSINESS_LOGIC, bl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
     

}
