package com.jmp.services.bank.service.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

import javax.ejb.EJB;
import javax.jms.JMSException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jmp.services.bank.service.jms.publisher.BankServicePublisher;
import com.jmp.services.bank.service.persistance.dto.Account;
import com.jmp.services.bank.service.persistance.dto.Bill;
import com.jmp.services.bank.service.persistance.dto.Currency;
import com.jmp.services.bank.service.persistance.dto.Person;
import com.jmp.services.bank.service.persistance.eao.AccountManagerLocal;
import com.jmp.services.bank.service.persistance.eao.CurrencyManagerLocal;
import com.jmp.services.bank.service.util.AccountSortParam;
import com.jmp.services.bank.service.util.SortType;

@Controller
public class BankServiceController {
	
	private static final Logger logger = Logger.getLogger(BankServiceController.class);

	@EJB(mappedName="java:global/bank-service-ear/bank-service-ejb-1.0-SNAPSHOT/AccountManagerBean!com.jmp.services.bank.service.persistance.eao.AccountManagerLocal")
    private AccountManagerLocal accountManagerLocal;
	
	@EJB(mappedName="java:global/bank-service-ear/bank-service-ejb-1.0-SNAPSHOT/CurrencyManagerBean!com.jmp.services.bank.service.persistance.eao.CurrencyManagerLocal")
    private CurrencyManagerLocal currencyManagerLocal;
	
	private BankServicePublisher client = new BankServicePublisher();

	@RequestMapping(value = "/")
	public ModelAndView getIndexPage() throws JMSException, NamingException {
		client.sendAsync("Used / pattern, now=" + System.currentTimeMillis());
		client.stop();
 		ModelAndView model = new ModelAndView("home");
 		model.addObject("accounts", accountManagerLocal.getAllAccounts());
		return model;
 	} 
	
	@RequestMapping(value = "/home")
	public ModelAndView getHomePage() throws JMSException, NamingException {
		client.sendAsync("Used /home pattern, now=" + System.currentTimeMillis());
		client.stop();
 		ModelAndView model = new ModelAndView("home");
 		model.addObject("accounts", accountManagerLocal.getAllAccounts());
		return model;
 	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteAccount(@RequestParam(value = "accountId", required = true) int accountId) throws JMSException, NamingException {
		client.sendAsync("Used /delete pattern, now=" + System.currentTimeMillis());
		client.stop();
		ModelAndView model = new ModelAndView("home");
        accountManagerLocal.deleteAccount(accountId);
 		model.addObject("accounts", accountManagerLocal.getAllAccounts());
		return model;
 	}
	
	@RequestMapping(value = "/exchange", method = RequestMethod.POST)
    public ModelAndView exchangeCurrency(
    		@RequestParam(value = "accountId", required = true) int accountId,
    		@RequestParam(value = "billId", required = true) int billId,
    		@RequestParam(value = "currencyId", required = true) int currencyId) throws JMSException, NamingException {
		client.sendAsync("Used /exchange pattern, now=" + System.currentTimeMillis());
		client.stop();
		ModelAndView model = new ModelAndView("account-view");
        accountManagerLocal.deleteAccount(accountId);
 		model.addObject("account", accountManagerLocal.exchange(accountId, billId, currencyId));
		model.addObject("currencies", currencyManagerLocal.getAllCurrencies());
 		return model;
 	}
	
	@RequestMapping(value = "/account", method = RequestMethod.GET)
    public ModelAndView exchangeCurrency(@RequestParam(value = "accountId", required = true) int accountId) throws JMSException, NamingException {
		client.sendAsync("Used /account pattern, now=" + System.currentTimeMillis());
		client.stop();
		ModelAndView model = new ModelAndView("account-view");
 		model.addObject("account", accountManagerLocal.getAccount(accountId));
		model.addObject("currencies", currencyManagerLocal.getAllCurrencies());
 		return model;
 	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView exchangeCurrency(
    		@RequestParam(value = "name", required = true) String name,
    		@RequestParam(value = "surname", required = true) String surname,
    		@RequestParam(value = "currency", required = true) String currency,
    		@RequestParam(value = "amount", required = true) String amount) throws JMSException, NamingException {
		client.sendAsync("Used /create pattern, now=" + System.currentTimeMillis());
		client.stop();
		ModelAndView model = new ModelAndView("home");
 		Person person = new Person(name, surname);
        Currency billCurrency = currencyManagerLocal.getCurrency(currency);
        Bill bill = new Bill(billCurrency, new BigDecimal(amount));
        Account account = new Account(person, new HashSet<Bill>(Arrays.asList(bill)));
        accountManagerLocal.addAccount(account);
 		model.addObject("accounts", accountManagerLocal.getAllAccounts());
 		return model;
 	}
	
	@RequestMapping(value = "/create-account", method = RequestMethod.GET)
    public ModelAndView getCreateAccountView() throws JMSException, NamingException {
		client.sendAsync("Used /create-account pattern, now=" + System.currentTimeMillis());
		client.stop();
		ModelAndView model = new ModelAndView("create-account");
 		model.addObject("currencies", currencyManagerLocal.getAllCurrencies());
 		return model;
 	}
	
	@RequestMapping(value = "/sort", method = RequestMethod.GET)
    public ModelAndView sortAccounts(@RequestParam(value = "param", required = true) String param, @RequestParam(value = "type", required = true) String type) throws JMSException, NamingException {
		client.sendAsync("Used /sort pattern, now=" + System.currentTimeMillis());
		client.stop();
		ModelAndView model = new ModelAndView("home");
 		AccountSortParam sortParam = AccountSortParam.valueOf(param.toUpperCase());
        SortType sortType = SortType.valueOf(type.toUpperCase());
 		model.addObject("accounts", accountManagerLocal.sort(sortType, sortParam));
 		return model;
 	}

}
