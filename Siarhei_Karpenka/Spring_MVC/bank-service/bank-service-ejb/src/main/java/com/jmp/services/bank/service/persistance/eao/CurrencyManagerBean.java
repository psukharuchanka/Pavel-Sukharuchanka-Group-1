package com.jmp.services.bank.service.persistance.eao;

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

import com.jmp.services.bank.service.persistance.dto.Currency;

@DeclareRoles("bean")
@Stateless(mappedName="BankService/CurrencyManagerBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CurrencyManagerBean implements CurrencyManagerRemote, CurrencyManagerLocal {
	
	@PersistenceContext(name = "records.unit")
    private EntityManager em;

    @RolesAllowed("bean")
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int addCurrency(Currency Currency) {
		em.persist(Currency);
		return Currency.getId();
	}

    @RolesAllowed("bean")
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Currency getCurrency(int id) {
		return em.find(Currency.class, id);
	}
    
    @RolesAllowed("bean")
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Currency getCurrency(String name) {
    	Query query = em.createQuery("FROM Currency AS currency WHERE currency.name = ?1");
		query.setParameter(1, name);
		return (Currency) query.getSingleResult();
	}

    @RolesAllowed("bean")
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteCurrency(int id) {
		Query query = em.createQuery("DELETE FROM Currency AS currency WHERE currency.id = ?1");
		query.setParameter(0, id);
        query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<Currency> getAllCurrencies() {
		Query query = em.createQuery("FROM Currency");
        return query.getResultList();
	}
}
