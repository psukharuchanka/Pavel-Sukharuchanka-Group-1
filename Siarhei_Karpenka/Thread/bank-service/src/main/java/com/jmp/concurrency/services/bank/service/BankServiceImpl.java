package com.jmp.concurrency.services.bank.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.jmp.concurrency.services.bank.service.comparator.AccountComparatorFactory;
import com.jmp.concurrency.services.bank.service.dao.DataManager;
import com.jmp.concurrency.services.bank.service.dao.JAXBDataManager;
import com.jmp.concurrency.services.bank.service.dto.Account;
import com.jmp.concurrency.services.bank.service.dto.AccountMap;
import com.jmp.concurrency.services.bank.service.dto.Currency;
import com.jmp.concurrency.services.bank.service.dto.Person;
import com.jmp.concurrency.services.bank.service.exception.AccountException;
import com.jmp.concurrency.services.bank.service.exception.DataManagerException;
import com.jmp.concurrency.services.bank.service.filter.AccountPredicateFilter;
import com.jmp.concurrency.services.bank.service.predicate.AmountAccountPredicate;
import com.jmp.concurrency.services.bank.service.predicate.CurrencyAccountPredicate;
import com.jmp.concurrency.services.bank.service.predicate.NameAccountPredicate;
import com.jmp.concurrency.services.bank.service.util.AccountFieldParameter;
import com.jmp.concurrency.services.bank.service.util.AppProperties;
import com.jmp.concurrency.services.bank.service.util.PropertiesConfig;
import com.jmp.concurrency.services.bank.service.util.SortType;

public enum BankServiceImpl implements BankService {
	
	INSTANCE;

	private static final Logger logger = Logger.getLogger(BankServiceImpl.class);
	
	private static final DataManager dataManager = new JAXBDataManager();
	
    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();
	
	@Override
	public Account getAccount(final int id) throws AccountException, DataManagerException {
		logger.debug("Getting account object by Id : " + id);
		AccountMap accounts;
		try {
			accounts = (AccountMap) executorService.submit(new Callable<AccountMap>() {
				@Override
				public AccountMap call() throws Exception {
					return dataManager.read();
				}
			}).get();
		} catch (InterruptedException e) {
			logger.error(e);
			throw new DataManagerException(e);
		} catch (ExecutionException e) {
			logger.error(e);
			throw new DataManagerException(e);
		}
		if (accounts.getAccountMap().get(id) == null) {
			throw new AccountException("Account (id = " + id + ") does not exist");
		}
		return accounts.getAccountMap().get(id);
	}

	@Override
	public Account createAccount(final Person person, final BigDecimal amount, final Currency currency) throws AccountException, DataManagerException {
		logger.debug("Creation account for person : " + person);
		if(!isValidAccount(person, amount, currency)) {
			throw new AccountException("Invalid data for account : " + person + " " + amount + " " + currency);
		}
		try {
			return (Account) executorService.submit(new Callable<Account>() {
				@Override
				public Account call() throws Exception {
					AccountMap accountMap = dataManager.read();
					Map<Integer, Account> accounts = accountMap.getAccountMap();
					Account account = new Account(person, currency, amount);
					account.setId(new Integer(accounts.size() + 1));
					accounts.put(account.getId(), account);
					accountMap.setAccountMap(accounts);
					dataManager.write(accountMap);
					return account;
				}
			}).get();
		} catch (InterruptedException e) {
			logger.error(e);
			throw new DataManagerException(e);
		} catch (ExecutionException e) {
			logger.error(e);
			throw new DataManagerException(e);
		}
	}
	
	private boolean isValidAccount(Person person, BigDecimal amount, Currency currency) {
		return isValidPerson(person) && isValidAmount(amount) && isValidCurrency(currency);
	}

	private boolean isValidCurrency(Currency currency) {
		return currency != null;
	}

	private boolean isValidAmount(BigDecimal amount) {
		return amount.compareTo(new BigDecimal("0.00")) >= 0;
	}

	private boolean isValidPerson(Person person) {
		return person != null && !person.getName().isEmpty() && !person.getSurname().isEmpty();
	}
	
	@Override
	public List<Account> filterByName(String name) throws DataManagerException {
		logger.debug("Filtering accounts by name : " + name);
		AccountMap accountMap;
		try {
			accountMap = (AccountMap) executorService.submit(new Callable<AccountMap>() {
				@Override
				public AccountMap call() throws Exception {
					return dataManager.read();
				}
			}).get();
		} catch (InterruptedException e) {
			logger.error(e);
			throw new DataManagerException(e);
		} catch (ExecutionException e) {
			logger.error(e);
			throw new DataManagerException(e);
		}
		return AccountPredicateFilter.filter(accountMap.getAccountMap().values(), new NameAccountPredicate(name));
	}
	
	@Override
	public List<Account> filterByCurrency(Currency currency) throws DataManagerException {
		logger.debug("Filtering accounts by currency : " + currency);
		AccountMap accountMap = null;
		try {
			accountMap = (AccountMap) executorService.submit(new Callable<AccountMap>() {
				@Override
				public AccountMap call() throws Exception {
					return dataManager.read();
				}
			}).get();
		} catch (InterruptedException e) {
			logger.error(e);
			throw new DataManagerException(e);
		} catch (ExecutionException e) {
			logger.error(e);
			throw new DataManagerException(e);
		}
		return AccountPredicateFilter.filter(accountMap.getAccountMap().values(), new CurrencyAccountPredicate(currency));
	}

	@Override
	public List<Account> filterByAmount(Currency currency, Integer minValue, Integer maxValue) throws DataManagerException  {
		logger.debug("Filtering accounts by amount : min - " + minValue + " max - " + maxValue);
		AccountMap accountMap;
		try {
			accountMap = (AccountMap) executorService.submit(new Callable<AccountMap>() {
				@Override
				public AccountMap call() throws Exception {
					return dataManager.read();
				}
			}).get();
		} catch (InterruptedException e) {
			logger.error(e);
			throw new DataManagerException(e);
		} catch (ExecutionException e) {
			logger.error(e);
			throw new DataManagerException(e);
		}
		return AccountPredicateFilter.filter(accountMap.getAccountMap().values(), 
				new AmountAccountPredicate(currency, new BigDecimal(minValue), new BigDecimal(maxValue)));
	}

	@Override
	public List<Account> sort(AccountFieldParameter parameter, SortType type) throws DataManagerException {
		logger.debug("Sorting accounts by parameter : " + parameter);
		AccountMap accountMap;
		try {
			accountMap = (AccountMap) executorService.submit(new Callable<AccountMap>() {
				@Override
				public AccountMap call() throws Exception {
					return dataManager.read();
				}
			}).get();
		} catch (InterruptedException e) {
			logger.error(e);
			throw new DataManagerException(e);
		} catch (ExecutionException e) {
			logger.error(e);
			throw new DataManagerException(e);
		}
		List<Account> accounts = new ArrayList<Account>(accountMap.getAccountMap().values());
		Collections.sort(accounts, AccountComparatorFactory.createComparator(parameter));
		if (SortType.DESCENDING == type) {
			Collections.reverse(accounts);
		}
		return accounts;
	}

	@Override
	public Account exchangeCurrency(final int id, final Currency currencyToExchange) throws DataManagerException, AccountException  {
		logger.debug("Currency exchange for account : " + id);
		try {
			return (Account) executorService.submit(new Callable<Account>() {
				@Override
				public Account call() throws Exception {
					AccountMap accountMap = dataManager.read();
					Account account = accountMap.getAccountMap().get(id);
					String exchangeProperty = AppProperties.EXCHANGE_PREFIX_PROPERTY;
					exchangeProperty += account.getCurrency().toString().toLowerCase() + "." + currencyToExchange.toString().toLowerCase();
					BigDecimal exchangeRate = new BigDecimal(Double.valueOf(PropertiesConfig.getProperty(exchangeProperty)));
					account.setCurrency(currencyToExchange);
					account.setAmount(account.getAmount().multiply(exchangeRate));
					accountMap.getAccountMap().put(account.getId(), account);
					dataManager.write(accountMap);
					return account;
				}
			}).get();
		} catch (InterruptedException e) {
			logger.error(e);
			throw new DataManagerException(e);
		} catch (ExecutionException e) {
			logger.error(e);
			throw new DataManagerException(e);
		}
	}

	@Override
	public List<Account> search(String name) throws DataManagerException {
		try {
			return filterByName(name);
		} catch (DataManagerException e) {
			logger.error(e);
			throw new DataManagerException(e);
		}
	}
}