package com.jmp.concurrency.services.bank.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jmp.concurrency.services.bank.service.dao.DataManager;
import com.jmp.concurrency.services.bank.service.dao.JAXBDataManager;
import com.jmp.concurrency.services.bank.service.dto.Account;
import com.jmp.concurrency.services.bank.service.dto.AccountMap;
import com.jmp.concurrency.services.bank.service.dto.Currency;
import com.jmp.concurrency.services.bank.service.dto.Person;
import com.jmp.concurrency.services.bank.service.exception.AccountException;
import com.jmp.concurrency.services.bank.service.exception.DataManagerException;
import com.jmp.concurrency.services.bank.service.util.AccountFieldParameter;
import com.jmp.concurrency.services.bank.service.util.AppProperties;
import com.jmp.concurrency.services.bank.service.util.PropertiesConfig;
import com.jmp.concurrency.services.bank.service.util.SortType;

public class BankServiceImplTest {
	
	private static final Logger logger = Logger.getLogger(BankServiceImplTest.class);

	private static final BankService bankServiceInstance = BankServiceImpl.INSTANCE;
	
	@BeforeClass
    public static void initializeDataFile() throws DataManagerException {
		DataManager dataManager = new JAXBDataManager();
		Map<Integer, Account> accounts = new HashMap<Integer, Account>();
		Account account = new Account(new Person("Siarhei", "Karpenka"), Currency.BYR, new BigDecimal(10700000));
		account.setId(1);
    	accounts.put(1, account);
    	account = new Account(new Person("Siarhei", "Ivashkou"), Currency.RUB, new BigDecimal(41000));
    	account.setId(2);
    	accounts.put(2, account);
    	account = new Account(new Person("Ivan", "Sialitski"), Currency.USD, new BigDecimal(1000));
    	account.setId(3);
    	accounts.put(3, account);
    	AccountMap accountMap = new AccountMap();
    	accountMap.setAccountMap(accounts);
    	dataManager.write(accountMap);
    }
    
    @Test
    public void testGetAccount() throws DataManagerException, AccountException {
    	assertNotNull("Can get account ", bankServiceInstance.getAccount(1));
		assertEquals("Account has incorrect data ", 
				new Account(new Person("Siarhei", "Karpenka"), Currency.BYR, new BigDecimal(10700000)),
				bankServiceInstance.getAccount(1));
    }

    @Test(expected = AccountException.class)
    public void testGetAccountWithInvalidId () throws DataManagerException, AccountException {
    	bankServiceInstance.getAccount(-1);
    }
    
    @Test
    public void testCreateAccount() throws DataManagerException, AccountException {
    	Account account = bankServiceInstance.createAccount(new Person("Pavel", "Sukharuchanka"), new BigDecimal(1000), Currency.USD);
    	Account expectedResult = new Account(new Person("Pavel", "Sukharuchanka"), Currency.USD, new BigDecimal(1000));
    	expectedResult.setId(4);
    	assertEquals("Account has incorrect data ", expectedResult, account);
    }
	
    @Test(expected = AccountException.class)
    public void testCreateAccountWithInvalidData() throws DataManagerException, AccountException {
    	bankServiceInstance.createAccount(new Person("Siarhei", "Karpenka"), new BigDecimal(-24), Currency.BYR);
    }
    
    @Test
    public void testFilterByCurrency() throws DataManagerException {
    	List<Account> accounts = bankServiceInstance.filterByCurrency(Currency.BYR);
    	for (Account account : accounts) {
			assertEquals("Account has wrong currency after filtering", Currency.BYR, account.getCurrency());
		}
    }
    
    @Test
    public void testFilterByName() throws DataManagerException {
    	List<Account> accounts = bankServiceInstance.filterByName("Siarhei");
    	for (Account account : accounts) {
			assertEquals("Account has wrong name after filtering", "Siarhei", account.getPerson().getName());
		}
    }
    
    @Test
    public void testFilterByAmount() throws DataManagerException {
    	List<Account> accounts = bankServiceInstance.filterByAmount(Currency.USD, 500, 1500);
    	for (Account account : accounts) {
    		assertTrue("Account has wrong currency value", account.getCurrency() == Currency.USD);
			assertTrue("Account has wrong amount value", account.getAmount().compareTo(new BigDecimal(500)) >= 0);
			assertTrue("Account has wrong amount value", account.getAmount().compareTo(new BigDecimal(1500)) <= 0);
		}
    }
    
    @Test
    public void testSearchByName() throws DataManagerException {
    	List<Account> accounts = bankServiceInstance.search("Siarhei");
    	for (Account account : accounts) {
			assertTrue("Account has wrong name value after search", 
					(account.getPerson().getName() + account.getPerson().getSurname()).contains("Siarhei"));
		}
    }
	
    @Test
    public void testSortByID() throws DataManagerException {
    	List<Account> accounts = bankServiceInstance.sort(AccountFieldParameter.ID, SortType.ASCENDING);
    	Integer expectedId = 1;
    	for (Account account : accounts) {
    		assertEquals("Account has wrong order place", expectedId++, account.getId());
    	}
    }
	
    @Test
    public void testExchangeCurrency() throws DataManagerException, AccountException {
    	Account account = bankServiceInstance.exchangeCurrency(2, Currency.USD);
    	assertEquals("Account has wrong amount after currency exchnage", 1000, Math.round(new Double(account.getAmount().toString())));
    }
    
    @AfterClass
	public static void after() {
		try {
			File testFileData = new File(PropertiesConfig.getProperty(AppProperties.DATA_FILE_PATH_PROPERTY));
			if (testFileData.exists()) {
				testFileData.delete();
			}
		} catch (Exception e) {
			logger.error("Error during file deleting", e);
		}
	}
}
