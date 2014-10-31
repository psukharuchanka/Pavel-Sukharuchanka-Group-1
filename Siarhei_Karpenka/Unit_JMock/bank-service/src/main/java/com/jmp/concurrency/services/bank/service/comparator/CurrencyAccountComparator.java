package com.jmp.concurrency.services.bank.service.comparator;

import java.util.Comparator;

import com.jmp.concurrency.services.bank.service.dto.Account;

public class CurrencyAccountComparator implements Comparator<Account> {

	@Override
	public int compare(Account o1, Account o2) {
		String currency1 = o1.getCurrency().toString().toUpperCase();
		String currency2 = o2.getCurrency().toString().toUpperCase();
		return currency1.compareTo(currency2);
	}
}