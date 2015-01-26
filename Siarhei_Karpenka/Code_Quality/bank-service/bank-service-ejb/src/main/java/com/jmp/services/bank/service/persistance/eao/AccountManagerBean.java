package com.jmp.services.bank.service.persistance.eao;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.jmp.services.bank.service.comparator.AccountComparatorFactory;
import com.jmp.services.bank.service.persistance.dto.Account;
import com.jmp.services.bank.service.persistance.dto.Bill;
import com.jmp.services.bank.service.persistance.dto.Currency;
import com.jmp.services.bank.service.util.AccountSortParam;
import com.jmp.services.bank.service.util.SortType;

@Stateless
@DeclareRoles("bean")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AccountManagerBean implements AccountManagerRemote, AccountManagerLocal {
	
	@PersistenceContext(name = "records.unit")
    private EntityManager em;

    @RolesAllowed("bean")
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int addAccount(Account account) {
    	em.persist(account.getPerson());
    	for (Bill bill : account.getBills()) {
    		em.persist(bill);
    	}
		em.persist(account);
		return account.getId();
	}

    @RolesAllowed("bean")
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Account getAccount(int id) {
		return em.find(Account.class, id);
	}

    @RolesAllowed("bean")
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteAccount(int id) {
    	Account account = getAccount(id);
    	em.remove(account.getPerson());
    	for (Bill bill : account.getBills()) {
    		em.remove(bill);
    	}
		em.remove(account);
	}

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<Account> getAllAccounts() {
		Query query = em.createQuery("FROM Account");
        return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<Account> sort(SortType type, AccountSortParam param) {
		List<Account> accounts = getAllAccounts();
		Comparator<Account> comparator = AccountComparatorFactory.createComparator(param);
		Collections.sort(accounts, comparator);
		if(SortType.DESC == type) {
			Collections.reverse(accounts);
		}
        return accounts;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Account exchange(int accountId, int billId, int currencyId) {
		Bill bill = em.find(Bill.class, billId);
		Currency currency = em.find(Currency.class, currencyId);
		Query query = em.createQuery("SELECT cr.rate FROM CurrencyRate AS cr WHERE cr.initialCurrency.id = ?1 and cr.exchangeCurrency.id = ?2");
		query.setParameter(1, bill.getCurrency().getId());
		query.setParameter(2, currencyId);
		BigDecimal rate = (BigDecimal) query.getSingleResult();
		bill.setCurrency(currency);
		bill.setAmount(bill.getAmount().multiply(rate));
		em.merge(bill);
		return getAccount(accountId);
	}
}
