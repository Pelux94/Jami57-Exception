package com.azienda.catalogoProdotti.web;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.azienda.catalogoProdotti.businessLogic.BusinessLogic;
import com.azienda.catalogoProdotti.exception.ItemSearchMatchException;
import com.azienda.catalogoProdotti.model.Item;
import com.azienda.catalogoProdotti.utility.BlobConverter;
import com.azienda.catalogoProdotti.utility.Costanti;


@WebServlet("/ItemSearchServlet")
public class ItemSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.getRequestDispatcher("view/ItemSearch.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute(Costanti.MESSAGGIO_ESITO, "Internal Server Error");
			request.getRequestDispatcher("view/Esito.jsp").forward(request, response);
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessLogic bl = (BusinessLogic)request.getServletContext().getAttribute(Costanti.BUSINESS_LOGIC);
		String itemName = request.getParameter(Costanti.ITEM_NAME).trim();
		Float itemPrice;
		
		try {
			itemPrice = Float.parseFloat(request.getParameter(Costanti.ITEM_PRICE));
		} catch (Exception e1) {
			e1.printStackTrace();
			itemPrice = null;
		}
		
		String itemNameLike = "%" + itemName + "%";
		
		try {
			List<Item> itemList = bl.getItems(itemNameLike, itemPrice);
			BlobConverter.addImage(request, itemList);
			request.setAttribute(Costanti.ITEM_LIST, itemList);
			request.getRequestDispatcher("view/ItemSearch.jsp").forward(request, response);
		} catch (ItemSearchMatchException e) {
			e.printStackTrace();
			request.setAttribute(Costanti.MESSAGGIO_ESITO, e.getMessage());
			request.getRequestDispatcher("view/ItemSearch.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute(Costanti.MESSAGGIO_ESITO, "Internal Server Error");
			request.getRequestDispatcher("view/Esito.jsp").forward(request, response);
		}
	}

}
