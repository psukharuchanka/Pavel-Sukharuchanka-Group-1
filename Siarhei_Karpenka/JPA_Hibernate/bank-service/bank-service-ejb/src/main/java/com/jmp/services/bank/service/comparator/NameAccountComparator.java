package com.jmp.services.bank.service.comparator;

import java.util.Comparator;

import com.jmp.services.bank.service.persistance.dto.Account;

public class NameAccountComparator implements Comparator<Account> {

	public int compare(Account o1, Account o2) {
		return o1.getPerson().getName().compareTo(o2.getPerson().getName());
	}
}