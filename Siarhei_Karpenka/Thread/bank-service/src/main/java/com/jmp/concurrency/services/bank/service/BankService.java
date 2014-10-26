package com.jmp.concurrency.services.bank.service;

import java.math.BigDecimal;
import java.util.List;

import com.jmp.concurrency.services.bank.service.dto.Account;
import com.jmp.concurrency.services.bank.service.dto.Currency;
import com.jmp.concurrency.services.bank.service.dto.Person;
import com.jmp.concurrency.services.bank.service.exception.AccountException;
import com.jmp.concurrency.services.bank.service.exception.DataManagerException;
import com.jmp.concurrency.services.bank.service.util.AccountFieldParameter;
import com.jmp.concurrency.services.bank.service.util.SortType;

public interface BankService {

	Account getAccount(int id) throws DataManagerException, AccountException;
	
	Account createAccount(Person person, BigDecimal amount, Currency currency) throws DataManagerException, AccountException;
	
	List<Account> filterByCurrency(Currency currency) throws DataManagerException;
	
	List<Account> filterByName(String name) throws DataManagerException;
	
	List<Account> filterByAmount(Integer minValue, Integer maxValue) throws DataManagerException;
	
	List<Account> search(String name) throws DataManagerException;
	
	List<Account> sort(AccountFieldParameter parameter, SortType type) throws DataManagerException;
	
	Account exchangeCurrency(int id, Currency currencyToExchange) throws DataManagerException, AccountException;
	
}