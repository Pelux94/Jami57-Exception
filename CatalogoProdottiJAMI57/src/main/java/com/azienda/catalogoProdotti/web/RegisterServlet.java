package com.azienda.catalogoProdotti.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.azienda.catalogoProdotti.businessLogic.BusinessLogic;
import com.azienda.catalogoProdotti.exception.UserAlreadyExistsException;
import com.azienda.catalogoProdotti.exception.UserUsernameAndPasswordAreEmptyException;
import com.azienda.catalogoProdotti.model.User;
import com.azienda.catalogoProdotti.utility.Costanti;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.getRequestDispatcher("view/Register.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute(Costanti.MESSAGGIO_ESITO, "Internal Server Error");
			request.getRequestDispatcher("view/Esito.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter(Costanti.INPUT_USERNAME);
		String password = request.getParameter(Costanti.INPUT_PASSWORD);
		BusinessLogic bl = (BusinessLogic) getServletContext().getAttribute(Costanti.BUSINESS_LOGIC);
		User u = new User(username,password);
		
		try {
			bl.registerUser(u);
			request.setAttribute(Costanti.MESSAGGIO_ESITO, "Successful registration. Login into your new account");
			request.getRequestDispatcher("view/Login.jsp").forward(request, response);
		} catch (UserUsernameAndPasswordAreEmptyException e) {
			e.printStackTrace();
			request.setAttribute(Costanti.MESSAGGIO_ESITO, e.getMessage());
			request.getRequestDispatcher("view/Register.jsp").forward(request, response);
		} catch (UserAlreadyExistsException e) {
			e.printStackTrace();
			request.setAttribute(Costanti.MESSAGGIO_ESITO, e.getMessage());
			request.getRequestDispatcher("view/Register.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute(Costanti.MESSAGGIO_ESITO, "Internal Server Error");
			request.getRequestDispatcher("view/Esito.jsp").forward(request, response);
		}
	}

}
