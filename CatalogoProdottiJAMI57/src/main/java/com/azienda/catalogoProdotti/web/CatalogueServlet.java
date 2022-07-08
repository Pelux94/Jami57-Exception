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


@WebServlet("/CatalogueServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,maxFileSize = 1024 * 1024 * 10,maxRequestSize = 1024 * 1024 * 10 * 5)
public class CatalogueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	private void addImage(HttpServletRequest request, List<Item> itemList) throws Exception {
		String uploadPath = getServletContext().getRealPath("") + File.separator + "upload";
		File uploadDir = new File(uploadPath);
		String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		Map<Integer, String> imageMap = new HashMap<Integer,String>();
		for(Item x : itemList) {
			String filePath = uploadDir + File.separator + x.getId() + "_" + x.getImageName();
			if(x.getImage() != null) {
				BlobConverter.saveFile(x.getImage(), filePath);
				String imgPath = basePath + File.separator + "upload" + File.separator + x.getId() + "_" + x.getImageName();
				imageMap.put(x.getId(), imgPath);
			} else {
				String imgPath = basePath + File.separator + "img" + File.separator + "theoffice.gif";
				imageMap.put(x.getId(), imgPath);
			}
		}
		request.setAttribute("costanteMappa", imageMap);
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.getRequestDispatcher("view/Catalogue.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute(Costanti.MESSAGGIO_ESITO, "Internal Server Error");
			request.getRequestDispatcher("view/Esito2.jsp").forward(request, response);
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
			addImage(request, itemList);
			request.setAttribute(Costanti.ITEM_LIST, itemList);
			request.getRequestDispatcher("view/Catalogue.jsp").forward(request, response);
		} catch (ItemSearchMatchException e) {
			e.printStackTrace();
			request.setAttribute(Costanti.MESSAGGIO_ESITO, e.getMessage());
			request.getRequestDispatcher("view/Catalogue.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute(Costanti.MESSAGGIO_ESITO, "Internal Server Error");
			request.getRequestDispatcher("view/Esito2.jsp").forward(request, response);
		}
	}

}
