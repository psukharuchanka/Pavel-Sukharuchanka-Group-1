package com.jmp.concurrency.services.bank.service.client;

import java.math.BigDecimal;
import java.util.List;

import com.jmp.concurrency.services.bank.service.BankService;
import com.jmp.concurrency.services.bank.service.dto.Account;
import com.jmp.concurrency.services.bank.service.dto.Currency;
import com.jmp.concurrency.services.bank.service.dto.Person;
import com.jmp.concurrency.services.bank.service.exception.AccountException;
import com.jmp.concurrency.services.bank.service.exception.DataManagerException;
import com.jmp.concurrency.services.bank.service.util.AccountFieldParameter;
import com.jmp.concurrency.services.bank.service.util.SortType;

public class BankServiceClient {
	
	private BankService bankService = null;
	
	private Account currentAccount = null;

	public BankServiceClient(BankService bankService, Account currentAccount) {
		super();
		this.bankService = bankService;
		this.currentAccount = currentAccount;
	}

	public Account getCurrentAccount() {
		return currentAccount;
	}

	public void setCurrentAccount(Account currentAccount) {
		this.currentAccount = currentAccount;
	}
	
	public Account createAccount(Person person, String amount, String currency) throws NumberFormatException, DataManagerException, AccountException  {
		Account account = bankService.createAccount(person, new BigDecimal(Long.valueOf(amount)), Currency.valueOf(currency.toUpperCase()));
		setCurrentAccount(account);
		return getCurrentAccount();
	}
	
	public Account getAccount(String id) throws DataManagerException, AccountException  {
		int accountId = Integer.parseInt(id);
		Account account = bankService.getAccount(accountId);
		setCurrentAccount(account);
		return getCurrentAccount();
	}
	
	public Account exchangeCurrency(String currency)  throws DataManagerException, AccountException  {
		Currency currencyToExchange = Currency.valueOf(currency.toUpperCase());
		Account account = bankService.exchangeCurrency(getCurrentAccount().getId(), currencyToExchange);
		setCurrentAccount(account);
		return getCurrentAccount();
	}

	public List<Account> filterByName(String name)  throws DataManagerException  {
		return bankService.filterByName(name);
	}
	
	public List<Account> filterByAmount(Integer minValue, Integer maxValue) throws DataManagerException {
		return bankService.filterByAmount(minValue, maxValue);
	}
	
	public List<Account> filterByCurrency(String currency) throws DataManagerException  {
		return bankService.filterByCurrency(Currency.valueOf(currency.toUpperCase()));
	}
	
	public List<Account> sort(String sortParameter, String sortType) throws DataManagerException {
		AccountFieldParameter parameter = AccountFieldParameter.valueOf(sortParameter.toUpperCase());
		SortType type = SortType.valueOf(sortType.toUpperCase());
		List<Account> sortedAccounts = bankService.sort(parameter, type);
		return sortedAccounts;
	}
	
	public List<Account> search(String name) throws DataManagerException  {
		return bankService.search(name);
	}
}
