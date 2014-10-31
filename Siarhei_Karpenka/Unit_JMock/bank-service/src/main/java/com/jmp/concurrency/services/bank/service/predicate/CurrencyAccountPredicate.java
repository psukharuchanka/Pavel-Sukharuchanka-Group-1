package com.jmp.concurrency.services.bank.service.predicate;

import com.jmp.concurrency.services.bank.service.dto.Account;
import com.jmp.concurrency.services.bank.service.dto.Currency;


public class CurrencyAccountPredicate implements AccountPredicate {
	
	private Currency currency = null;

	public CurrencyAccountPredicate(Currency currency) {
		super();
		this.currency = currency;
	}

	@Override
	public boolean apply(Account account) {
		return account.getCurrency() == currency;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
}