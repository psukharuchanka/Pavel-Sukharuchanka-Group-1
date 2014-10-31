package com.jmp.concurrency.services.bank.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jmp.concurrency.services.bank.service.dto.Account;
import com.jmp.concurrency.services.bank.service.dto.Currency;
import com.jmp.concurrency.services.bank.service.dto.Person;
import com.jmp.concurrency.services.bank.service.exception.AccountException;
import com.jmp.concurrency.services.bank.service.exception.DataManagerException;
import com.jmp.concurrency.services.bank.service.util.AccountFieldParameter;
import com.jmp.concurrency.services.bank.service.util.SortType;

public class BankServiceImplMockTest {
	
	private static BankService bankServiceMock;
	
	private static Account account1;
	
	private static Account account2;
	
	private static Account account3;
	
	private static Account account4;
	
	@SuppressWarnings("unchecked")
	@BeforeClass
    public static void initializeDataAndMockRules() throws AccountException, DataManagerException {
		bankServiceMock = mock(BankService.class);
		account1 = new Account(new Person("Siarhei", "Karpenka"), Currency.BYR, new BigDecimal(10700000));
		account1.setId(1);
		account2 = new Account(new Person("Siarhei", "Ivashkou"), Currency.RUB, new BigDecimal(41000));
		account2.setId(2);
		account3 = new Account(new Person("Ivan", "Sialitski"), Currency.USD, new BigDecimal(1000));
    	account3.setId(3);
    	account4 = new Account(new Person("Pavel", "Sukharuchanka"), Currency.USD, new BigDecimal(1000));
    	account4.setId(4);
    	
    	when(bankServiceMock.getAccount(1)).thenReturn(account1);
    	when(bankServiceMock.getAccount(2)).thenReturn(account2);
    	when(bankServiceMock.getAccount(3)).thenReturn(account3);
    	when(bankServiceMock.getAccount(-1)).thenThrow(AccountException.class);
    	
    	when(bankServiceMock.createAccount(new Person("Pavel", "Sukharuchanka"), 
    			new BigDecimal(1000), Currency.USD)).thenReturn(account4);
    	when(bankServiceMock.createAccount(new Person("Siarhei", "Karpenka"),
    			new BigDecimal(-24), Currency.BYR)).thenThrow(AccountException.class);
    	when(bankServiceMock.filterByCurrency(Currency.BYR)).thenReturn(Arrays.asList(account1));
    	when(bankServiceMock.filterByName("Siarhei")).thenReturn(Arrays.asList(account1, account2));
    	when(bankServiceMock.filterByAmount(Currency.USD, 500, 1500)).thenReturn(Arrays.asList(account3, account4));
    	when(bankServiceMock.search("Siarhei")).thenReturn(Arrays.asList(account1, account2));
    	when(bankServiceMock.sort(AccountFieldParameter.ID, SortType.ASCENDING)).thenReturn(Arrays.asList(account1, account2, account3, account4));
    	account2.setCurrency(Currency.USD);
    	account2.setAmount(new BigDecimal(1000));
    	when(bankServiceMock.exchangeCurrency(2, Currency.USD)).thenReturn(account2);
	}
    
    @Test
    public void testGetAccount() throws AccountException, DataManagerException  {
    	assertNotNull("Can get account ", bankServiceMock.getAccount(1));
		assertEquals("Account has incorrect data ", 
				new Account(new Person("Siarhei", "Karpenka"), Currency.BYR, new BigDecimal(10700000)),
				bankServiceMock.getAccount(1));
    }

    @Test(expected = AccountException.class)
    public void testGetAccountWithInvalidId() throws AccountException, DataManagerException {
    	bankServiceMock.getAccount(-1);
    }
    
    @Test
    public void testCreateAccount() throws DataManagerException, AccountException {
    	Account account = bankServiceMock.createAccount(new Person("Pavel", "Sukharuchanka"), new BigDecimal(1000), Currency.USD);
    	assertEquals("Account has incorrect data ", account4, account);
    }
	
    @Test(expected = AccountException.class)
    public void testCreateAccountWithInvalidData() throws DataManagerException, AccountException {
    	bankServiceMock.createAccount(new Person("Siarhei", "Karpenka"), new BigDecimal(-24), Currency.BYR);
    }
    
    @Test
    public void testFilterByCurrency() throws DataManagerException {
    	List<Account> accounts = bankServiceMock.filterByCurrency(Currency.BYR);
    	for (Account account : accounts) {
			assertEquals("Account has wrong currency after filtering", Currency.BYR, account.getCurrency());
		}
    }
    
    @Test
    public void testFilterByName() throws DataManagerException {
    	List<Account> accounts = bankServiceMock.filterByName("Siarhei");
    	for (Account account : accounts) {
			assertEquals("Account has wrong name after filtering", "Siarhei", account.getPerson().getName());
		}
    }
    
    @Test
    public void testFilterByAmount() throws DataManagerException {
    	List<Account> accounts = bankServiceMock.filterByAmount(Currency.USD, 500, 1500);
    	for (Account account : accounts) {
    		assertTrue("Account has wrong currency value", account.getCurrency() == Currency.USD);
			assertTrue("Account has wrong amount value", account.getAmount().compareTo(new BigDecimal(500)) >= 0);
			assertTrue("Account has wrong amount value", account.getAmount().compareTo(new BigDecimal(1500)) <= 0);
		}
    }
    
    @Test
    public void testSearchByName() throws DataManagerException {
    	List<Account> accounts = bankServiceMock.search("Siarhei");
    	for (Account account : accounts) {
			assertTrue("Account has wrong name value after search", 
					(account.getPerson().getName() + account.getPerson().getSurname()).contains("Siarhei"));
		}
    }
	
    @Test
    public void testSortByID() throws DataManagerException {
    	List<Account> accounts = bankServiceMock.sort(AccountFieldParameter.ID, SortType.ASCENDING);
    	Integer expectedId = 1;
    	for (Account account : accounts) {
    		assertEquals("Account has wrong order place", expectedId++, account.getId());
    	}
    }
	
    @Test
    public void testExchangeCurrency() throws DataManagerException, AccountException {
    	Account account = bankServiceMock.exchangeCurrency(2, Currency.USD);
    	assertEquals("Account has wrong amount after currency exchnage", 1000, Math.round(new Double(account.getAmount().toString())));
    }
}
