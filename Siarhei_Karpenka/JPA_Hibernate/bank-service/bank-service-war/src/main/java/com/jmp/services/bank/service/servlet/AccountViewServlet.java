package com.jmp.services.bank.service.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jmp.services.bank.service.persistance.dto.Account;
import com.jmp.services.bank.service.persistance.dto.Currency;
import com.jmp.services.bank.service.persistance.eao.AccountManagerLocal;
import com.jmp.services.bank.service.persistance.eao.CurrencyManagerLocal;

@WebServlet(description = "Add Account Servlet", urlPatterns = {"/account-view"})
public class AccountViewServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    
    @EJB
    AccountManagerLocal accountManagerLocal;
    
    @EJB
    CurrencyManagerLocal currencyManagerLocal;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountId = request.getParameter("accountId");
        Account account = accountManagerLocal.getAccount(Integer.valueOf(accountId));
        List<Currency> currencies = currencyManagerLocal.getAllCurrencies();
        request.setAttribute("account", account);
        request.setAttribute("currencies", currencies);
        request.getRequestDispatcher("WEB-INF/jsp/account-view.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       this.doGet(request, response);
    }
}
