package com.jmp.services.bank.service.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jmp.services.bank.service.persistance.dto.Account;
import com.jmp.services.bank.service.persistance.dto.Bill;
import com.jmp.services.bank.service.persistance.dto.Currency;
import com.jmp.services.bank.service.persistance.dto.Person;
import com.jmp.services.bank.service.persistance.eao.AccountManagerLocal;
import com.jmp.services.bank.service.persistance.eao.CurrencyManagerLocal;

@WebServlet(description = "Add Account Servlet", urlPatterns = {"/create-account"})
public class CreateAccountServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    
    @EJB
    AccountManagerLocal accountManagerLocal;
    
    @EJB
    CurrencyManagerLocal currencyManagerLocal;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String currencyName = request.getParameter("currency");
        String amount = request.getParameter("amount");
        Person person = new Person(name, surname);
        Currency currency = currencyManagerLocal.getCurrency(currencyName);
        Bill bill = new Bill(currency, new BigDecimal(amount));
        Account account = new Account(person, new HashSet<Bill>(Arrays.asList(bill)));
        accountManagerLocal.addAccount(account);
        request.getRequestDispatcher("/accounts").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       this.doGet(request, response);
    }
}
