package com.jmp.concurrency.services.bank.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.log4j.Logger;

import com.jmp.concurrency.services.bank.service.client.BankServiceClient;
import com.jmp.concurrency.services.bank.service.dto.Account;
import com.jmp.concurrency.services.bank.service.dto.Person;
import com.jmp.concurrency.services.bank.service.exception.AccountException;
import com.jmp.concurrency.services.bank.service.exception.DataManagerException;
import com.jmp.concurrency.services.bank.service.util.AccountFieldParameter;

public class App {
	
	private static final Logger logger = Logger.getLogger(App.class);
	
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
	private static final BankServiceClient bankServiceClient = new BankServiceClient(BankServiceImpl.INSTANCE, null);

    public static void main(String[] args) throws AccountException, NumberFormatException, DataManagerException {
    	logger.info("Initializing bank service");
        try {
	        String line = null;
	        while (!"exit".equals(line)) {
	        	logger.info("BANK SERVICE");
		    	logger.info("create - create account");
		    	logger.info("exchange - currency exchange");
		    	logger.info("sort - sort accounts");
		    	logger.info("search - search account by id");
		    	logger.info("filter - filter accounts by currency");
		    	logger.info("exit - exit bank service");
	            line = reader.readLine();
	            if (line != null && !line.isEmpty()) {
	                processCommand(line);
	            }
	        }
        } catch(IOException e) {
        	logger.error(e);
        }
    }

    private static void processCommand(String command) throws IOException, AccountException, NumberFormatException, DataManagerException  {
    	if (command.equals("create")) {
        	logger.info("Please, write person's data");
        	logger.info("Name : ");
        	String name = reader.readLine();
        	logger.info("Surname : ");
        	String surname = reader.readLine();
        	logger.info("Currency : ");
        	String currency = reader.readLine();
        	logger.info("Amount : ");
        	String amount = reader.readLine();
        	Person person = new Person(name, surname);
        	bankServiceClient.createAccount(person, amount, currency);
        	logger.info("Account created : " + bankServiceClient.getCurrentAccount());
        } else if (command.equals("exchange")) {
        	logger.info("Please, write currency to exchange");
        	logger.info("Currency : ");
        	String currency = reader.readLine();
            bankServiceClient.exchangeCurrency(currency);
            logger.info("Account : " + bankServiceClient.getCurrentAccount());
        } else if (command.equals("filter")) {
        	logger.info("Please, write one of parameters to filter (name, amount, currency)");
        	String parameter = reader.readLine();
        	AccountFieldParameter accountParameter = AccountFieldParameter.valueOf(parameter.toUpperCase());
        	List<Account> filteredAccounts = null;
        	switch (accountParameter) {
				case NAME: {
					logger.info("Please, write name to filter : ");
					String name = reader.readLine();
					filteredAccounts = bankServiceClient.filterByName(name);
					break;
				}
				case AMOUNT: {
					logger.info("Please, write min amount value : ");
					String minValue = reader.readLine();
					logger.info("Please, write max amount value : ");
					String maxValue = reader.readLine();
					filteredAccounts = bankServiceClient.filterByAmount(Integer.valueOf(minValue), Integer.valueOf(maxValue));
					break;
				}
				case CURRENCY: {
					logger.info("Please, write currency value : ");
					String currency = reader.readLine();
					filteredAccounts = bankServiceClient.filterByCurrency(currency);
					break;
				}
				default: {
					logger.info("Wrong filter parameter");
					return;
				}
			}
            logger.info("Filtered accounts info : ");
            for (Account account : filteredAccounts) {
				logger.info(account);
			}
        } else if (command.equals("sort")) {
        	logger.info("Please, write one of sort parameters (id, name, amount, currency)");
        	String sortParameter = reader.readLine();
        	logger.info("Please, write one of sort types (ascending, descending)");
        	String sortType = reader.readLine();
        	List<Account> sortedAccounts = bankServiceClient.sort(sortParameter, sortType);
            logger.info("Sorted accounts info : ");
            for (Account account : sortedAccounts) {
				logger.info(account);
			}
        } else if (command.equals("search")) {
        	logger.info("Please, write account name to search : ");
        	String accountName = reader.readLine();
        	bankServiceClient.search(accountName);
        }
    }
}
