package com.jmp.concurrency.services.bank.service.comparator;

import java.util.Comparator;

import com.jmp.concurrency.services.bank.service.dto.Account;

public class NameAccountComparator implements Comparator<Account> {

	@Override
	public int compare(Account o1, Account o2) {
		int result = o1.getPerson().getSurname().compareTo(o2.getPerson().getSurname());
		if (result == 0) {
			result = o1.getPerson().getName().compareTo(o2.getPerson().getName());
		}
		return result;
	}
}