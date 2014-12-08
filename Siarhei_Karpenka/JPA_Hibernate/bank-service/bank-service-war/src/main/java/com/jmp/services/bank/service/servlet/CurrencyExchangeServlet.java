package com.jmp.services.bank.service.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jmp.services.bank.service.persistance.eao.AccountManagerLocal;
import com.jmp.services.bank.service.persistance.eao.CurrencyManagerLocal;

@WebServlet(description = "Currency Exchange Servlet", urlPatterns = {"/exchange"})
public class CurrencyExchangeServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    
    @EJB
    AccountManagerLocal accountManagerLocal;
    
    @EJB
    CurrencyManagerLocal currencyManagerLocal;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String accountId = request.getParameter("accountId");
    	String billId = request.getParameter("billId");
        String currencyId = request.getParameter("currencyId");
        request.setAttribute("account", accountManagerLocal.exchange(Integer.valueOf(accountId), Integer.valueOf(billId), Integer.valueOf(currencyId)));
        request.setAttribute("currencies", currencyManagerLocal.getAllCurrencies());
        request.getRequestDispatcher("WEB-INF/jsp/account-view.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       this.doGet(request, response);
    }
}
