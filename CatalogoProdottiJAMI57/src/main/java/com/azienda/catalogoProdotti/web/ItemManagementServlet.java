package com.azienda.catalogoProdotti.web;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.azienda.catalogoProdotti.businessLogic.BusinessLogic;
import com.azienda.catalogoProdotti.exception.ItemAlreadyExistsException;
import com.azienda.catalogoProdotti.exception.ItemFieldsAreEmptyException;
import com.azienda.catalogoProdotti.exception.ItemIDNotFoundException;
import com.azienda.catalogoProdotti.model.Item;
import com.azienda.catalogoProdotti.utility.BlobConverter;
import com.azienda.catalogoProdotti.utility.Costanti;


@WebServlet("/ItemManagementServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,maxFileSize = 1024 * 1024 * 10,maxRequestSize = 1024 * 1024 * 10 * 5)
public class ItemManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private void uploadImage(HttpServletRequest request, Item item) throws Exception {
		String uploadPath = getServletContext().getRealPath("") + File.separator + "upload";
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		String filePath = null;
		for ( Part part : request.getParts() ) {
			String fileName = part.getSubmittedFileName();
			if ( fileName!=null && !fileName.isEmpty() ) {
				filePath = uploadPath + File.separator + fileName;
				part.write(filePath);
				Blob img = BlobConverter.generateBlob(filePath);
				item.setImage(img);
				item.setImageName(fileName);
			}
		}

	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.getRequestDispatcher("view/ItemManagement.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute(Costanti.MESSAGGIO_ESITO, "Internal Server Error");
			request.getRequestDispatcher("view/Esito.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessLogic bl = (BusinessLogic)request.getServletContext().getAttribute(Costanti.BUSINESS_LOGIC);
		String operation = request.getParameter(Costanti.OPERATION_TYPE);
		String itemName;
		Float itemPrice;
		Integer itemSupply;
		Item item;
		
		try {
			itemName = request.getParameter(Costanti.ITEM_NAME);
			itemPrice = Float.parseFloat(request.getParameter(Costanti.ITEM_PRICE));
			itemSupply = Integer.parseInt(request.getParameter(Costanti.ITEM_SUPPLY));		
			item = new Item(itemName, itemPrice, itemSupply);
		} catch (Exception e1) {
			e1.printStackTrace();
			itemName = null;
			itemPrice = null;
			itemSupply = null;
			item = null;
		}

		try {
			if(operation.equals(Costanti.ADD_ITEM)) {
				try {
					uploadImage(request, item);
					bl.addItem(item);
					request.setAttribute(Costanti.MESSAGGIO_ESITO, "Product successfully added to the shop");
					request.getRequestDispatcher("view/ItemManagement.jsp").forward(request, response);
				} catch (ItemFieldsAreEmptyException | ItemAlreadyExistsException e) {
					e.printStackTrace();
					request.setAttribute(Costanti.MESSAGGIO_ESITO, e.getMessage());
					request.getRequestDispatcher("view/AddItem.jsp").forward(request, response);
				}
			} else if(operation.equals(Costanti.UPDATE_ITEM)) {
				try {
					Integer itemID = Integer.parseInt(request.getParameter(Costanti.ITEM_ID));
					bl.updateItem(itemName, itemPrice, itemSupply, itemID);
					request.setAttribute(Costanti.MESSAGGIO_ESITO, "Product successfully updated");
					request.getRequestDispatcher("view/ItemManagement.jsp").forward(request, response);
				} catch (ItemFieldsAreEmptyException | ItemIDNotFoundException e) {
					e.printStackTrace();
					request.setAttribute(Costanti.MESSAGGIO_ESITO, e.getMessage());
					request.getRequestDispatcher("view/UpdateItem.jsp").forward(request, response);
				} catch (NoResultException e) {
					e.printStackTrace();
					request.setAttribute(Costanti.MESSAGGIO_ESITO, "ID not found");
					request.getRequestDispatcher("view/UpdateItem.jsp").forward(request, response);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					request.setAttribute(Costanti.MESSAGGIO_ESITO, "One or more fields are empty or not valid");
					request.getRequestDispatcher("view/UpdateItem.jsp").forward(request, response);
				}
			} else if(operation.equals(Costanti.DELETE_ITEM)) {
				try {
					Integer itemID = Integer.parseInt(request.getParameter(Costanti.ITEM_ID));
					bl.deleteItem(itemID);
					request.setAttribute(Costanti.MESSAGGIO_ESITO, "Product successfully removed from the shop");
					request.getRequestDispatcher("view/ItemManagement.jsp").forward(request, response);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					request.setAttribute(Costanti.MESSAGGIO_ESITO, "ID field is empty");
					request.getRequestDispatcher("view/DeleteItem.jsp").forward(request, response);
				} catch (NoResultException e) {
					e.printStackTrace();
					request.setAttribute(Costanti.MESSAGGIO_ESITO, "ID not found");
					request.getRequestDispatcher("view/DeleteItem.jsp").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute(Costanti.MESSAGGIO_ESITO, "The selected product is in a cart and can't be removed");
					request.getRequestDispatcher("view/DeleteItem.jsp").forward(request, response);			
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute(Costanti.MESSAGGIO_ESITO, "Internal Server Error");
			request.getRequestDispatcher("view/Esito.jsp").forward(request, response);			
		}
	}

}
