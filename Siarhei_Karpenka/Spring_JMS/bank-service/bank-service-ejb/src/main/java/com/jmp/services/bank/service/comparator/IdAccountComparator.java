package com.jmp.services.bank.service.comparator;

import java.util.Comparator;

import com.jmp.services.bank.service.persistance.dto.Account;

public class IdAccountComparator implements Comparator<Account> {

	public int compare(Account o1, Account o2) {
		return o1.getId() == o2.getId() ? 0 : o1.getId() > o2.getId() ? 1 : -1;
	}
}