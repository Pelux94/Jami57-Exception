package com.azienda.catalogoProdotti.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.azienda.catalogoProdotti.businessLogic.BusinessLogic;
import com.azienda.catalogoProdotti.exception.ItemAlreadyExistsException;
import com.azienda.catalogoProdotti.exception.ItemIDNotFoundException;
import com.azienda.catalogoProdotti.exception.ItemSupplyQuantityBelowOneException;
import com.azienda.catalogoProdotti.exception.UserDoesNotHaveShoppingCartException;
import com.azienda.catalogoProdotti.model.Item;
import com.azienda.catalogoProdotti.model.User;
import com.azienda.catalogoProdotti.utility.BlobConverter;
import com.azienda.catalogoProdotti.utility.Costanti;
import com.azienda.catalogoProdotti.utility.EmailUtility;


@WebServlet("/ShoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessLogic bl = (BusinessLogic)getServletContext().getAttribute(Costanti.BUSINESS_LOGIC);
		User u = (User) request.getSession().getAttribute(Costanti.USER_INFO);
		
		try {
			List<Item> listItem = bl.getItemsFromCart(u.getShoppingCart().getId());
			BlobConverter.addImage(request, listItem);
			request.setAttribute(Costanti.ITEM_CART, listItem);
			request.getRequestDispatcher("view/ShoppingCart.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute(Costanti.MESSAGGIO_ESITO, "Internal Server Error");
			request.getRequestDispatcher("view/Esito.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessLogic bl = (BusinessLogic)getServletContext().getAttribute(Costanti.BUSINESS_LOGIC);
		User u = (User) request.getSession().getAttribute(Costanti.USER_INFO);
		String operation = request.getParameter(Costanti.OPERATION_TYPE);
		Integer id = Integer.parseInt(request.getParameter(Costanti.ITEM_ID));	
		
		
		try {
			if(operation.equals(Costanti.ADD_ITEM_TO_CART)) {
				bl.addToShoppingCart(id, u);
				request.setAttribute(Costanti.MESSAGGIO_ESITO, "Product added to your cart");
				request.getRequestDispatcher("view/ItemSearch.jsp").forward(request, response);
			} else if(operation.equals(Costanti.REMOVE_ITEM_FROM_SHOPPING_CART)) {
				bl.removeFromShoppingCart(id, u);
				request.setAttribute(Costanti.MESSAGGIO_ESITO, "Item removed from your cart");
				List<Item> listItem = bl.getItemsFromCart(u.getShoppingCart().getId());
				request.setAttribute(Costanti.ITEM_CART, listItem);
				request.getRequestDispatcher("view/ShoppingCart.jsp").forward(request, response);
			} else if(operation.equals(Costanti.BUY_ITEM)) {
				bl.buyItem(id, u);
				try {
					EmailUtility.sendEmail(u.getUsername(), new Item(null, null, null));
				} catch (Exception e) {
					e.printStackTrace();
				}
				request.setAttribute(Costanti.MESSAGGIO_ESITO, "Thank you for your purchase!");
				request.getRequestDispatcher("view/Esito.jsp").forward(request, response);
			}
		} catch (ItemIDNotFoundException | ItemAlreadyExistsException e) {
			e.printStackTrace();
			request.setAttribute(Costanti.MESSAGGIO_ESITO, e.getMessage());
			request.getRequestDispatcher("view/ItemSearch.jsp").forward(request, response);
		} catch (UserDoesNotHaveShoppingCartException e) {
			e.printStackTrace();
			request.setAttribute(Costanti.MESSAGGIO_ESITO, e.getMessage());
			request.getRequestDispatcher("view/Esito.jsp").forward(request, response);
		} catch (ItemSupplyQuantityBelowOneException e) {
			e.printStackTrace();
			request.setAttribute(Costanti.MESSAGGIO_ESITO, e.getMessage());
			request.getRequestDispatcher("view/Esito.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute(Costanti.MESSAGGIO_ESITO, "Internal Server Error");
			request.getRequestDispatcher("view/Esito.jsp").forward(request, response);
		}
	
	}

}
