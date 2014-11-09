package com.jmp.concurrency.services.bank.service.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jmp.concurrency.services.bank.service.dto.Account;
import com.jmp.concurrency.services.bank.service.dto.AccountMap;
import com.jmp.concurrency.services.bank.service.dto.Currency;
import com.jmp.concurrency.services.bank.service.dto.Person;
import com.jmp.concurrency.services.bank.service.exception.DataManagerException;
import com.jmp.concurrency.services.bank.service.util.AppProperties;
import com.jmp.concurrency.services.bank.service.util.PropertiesConfig;

public class JAXBDataManagerTest {
	
	private static final Logger logger = Logger.getLogger(JAXBDataManagerTest.class);
	
    private static DataManager dataManager;

    @BeforeClass
    public static void before() {
        dataManager = new JAXBDataManager();
    }

    @Test
    public void testWriteData() throws DataManagerException {
    	Map<Integer, Account> accounts = new HashMap<Integer, Account>();
    	accounts.put(1, new Account(new Person("Siarhei", "Karpenka"), Currency.BYR, new BigDecimal(10700000)));
    	accounts.put(2, new Account(new Person("Siarhei", "Ivashkou"), Currency.RUB, new BigDecimal(41000)));
    	accounts.put(3, new Account(new Person("Ivan", "Sialitski"), Currency.USD, new BigDecimal(1000)));
    	AccountMap accountMap = new AccountMap();
    	accountMap.setAccountMap(accounts);
    	dataManager.write(accountMap);
    }

    @Test
	public void testReadData() throws DataManagerException {
		AccountMap accountMap = dataManager.read();
		Map<Integer, Account> accounts = accountMap.getAccountMap();
		assertNotNull("Can not recover data ", accounts.get(1));
		assertEquals("Incorrect data ", 
				new Account(new Person("Siarhei", "Karpenka"), Currency.BYR, new BigDecimal(10700000)),
				accounts.get(1));
		assertNotNull("Can not recover data ", accounts.get(2));
		assertEquals("Incorrect data ", 
				new Account(new Person("Siarhei", "Ivashkou"), Currency.RUB, new BigDecimal(41000)),
				accounts.get(2));
		assertNotNull("Can not recover data ", accounts.get(3));
		assertEquals("Incorrect data ",
				new Account(new Person("Ivan", "Sialitski"), Currency.USD, new BigDecimal(1000)),
				accounts.get(3));
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
