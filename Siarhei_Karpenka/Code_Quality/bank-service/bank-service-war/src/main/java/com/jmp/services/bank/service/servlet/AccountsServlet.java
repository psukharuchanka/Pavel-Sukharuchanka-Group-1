package com.jmp.services.bank.service.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jmp.services.bank.service.persistance.eao.AccountManagerLocal;

@WebServlet(description = "Input Account Servlet", urlPatterns = {"/accounts"})
public class AccountsServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    
    @EJB
    AccountManagerLocal accountManagerLocal;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setAttribute("accounts", accountManagerLocal.getAllAccounts());
        request.getRequestDispatcher("WEB-INF/jsp/home.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
