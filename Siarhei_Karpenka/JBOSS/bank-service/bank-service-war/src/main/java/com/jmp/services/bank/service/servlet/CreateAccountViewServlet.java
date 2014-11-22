package com.jmp.services.bank.service.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jmp.services.bank.service.persistance.eao.CurrencyManagerLocal;

@WebServlet(description = "Add Account Servlet", urlPatterns = {"/create-account-view"})
public class CreateAccountViewServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    
    @EJB
    CurrencyManagerLocal currencyManagerLocal;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("currencies", currencyManagerLocal.getAllCurrencies());
        request.getRequestDispatcher("WEB-INF/jsp/create-account.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       this.doGet(request, response);
    }
}
