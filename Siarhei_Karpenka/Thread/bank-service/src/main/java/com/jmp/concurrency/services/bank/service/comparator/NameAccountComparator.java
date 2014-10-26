package com.jmp.concurrency.services.bank.service.comparator;

import java.util.Comparator;

import com.jmp.concurrency.services.bank.service.dto.Account;

public class NameAccountComparator implements Comparator<Account> {

	@Override
	public int compare(Account o1, Account o2) {
		String name1 = o1.getPerson().getSurname() + o1.getPerson().getName();
		String name2 = o2.getPerson().getSurname() + o2.getPerson().getName();
		return name1.toUpperCase().compareTo(name2.toUpperCase());
	}
}