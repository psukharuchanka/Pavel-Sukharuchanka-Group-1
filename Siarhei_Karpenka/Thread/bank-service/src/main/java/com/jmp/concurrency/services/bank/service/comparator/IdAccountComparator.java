package com.jmp.concurrency.services.bank.service.comparator;

import java.util.Comparator;

import com.jmp.concurrency.services.bank.service.dto.Account;

public class IdAccountComparator implements Comparator<Account> {

	@Override
	public int compare(Account o1, Account o2) {
		return o1.getId().compareTo(o2.getId());
	}
}