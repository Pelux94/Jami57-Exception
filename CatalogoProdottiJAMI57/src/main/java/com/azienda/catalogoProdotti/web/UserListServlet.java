package com.azienda.catalogoProdotti.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.azienda.catalogoProdotti.businessLogic.BusinessLogic;
import com.azienda.catalogoProdotti.model.User;
import com.azienda.catalogoProdotti.utility.Costanti;


@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			BusinessLogic bl = (BusinessLogic)getServletContext().getAttribute(Costanti.BUSINESS_LOGIC);
			List<User> userList = bl.getUserList();
			request.setAttribute(Costanti.USER_LIST, userList);
			request.getRequestDispatcher("view/UserList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute(Costanti.MESSAGGIO_ESITO, "Internal Server Error");
			request.getRequestDispatcher("view/Esito.jsp").forward(request, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
