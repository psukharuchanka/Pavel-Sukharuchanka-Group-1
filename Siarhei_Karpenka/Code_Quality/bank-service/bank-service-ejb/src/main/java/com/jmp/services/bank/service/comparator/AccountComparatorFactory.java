package com.jmp.services.bank.service.comparator;

import java.util.Comparator;

import com.jmp.services.bank.service.persistance.dto.Account;
import com.jmp.services.bank.service.util.AccountSortParam;

public class AccountComparatorFactory {

	public static Comparator<Account> createComparator(AccountSortParam sortParameter) {
		switch (sortParameter) {
			case ID: {
				return new IdAccountComparator();
			}
			case NAME: {
				return new NameAccountComparator();
			}
			case SURNAME: {
				return new SurnameAccountComparator();
			}
			default: {
				return new IdAccountComparator();
			}
		}
	}
}