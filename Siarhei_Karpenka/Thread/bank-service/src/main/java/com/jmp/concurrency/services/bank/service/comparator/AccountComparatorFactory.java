package com.jmp.concurrency.services.bank.service.comparator;

import java.util.Comparator;

import com.jmp.concurrency.services.bank.service.dto.Account;
import com.jmp.concurrency.services.bank.service.util.AccountFieldParameter;

public class AccountComparatorFactory {

	public static Comparator<Account> createComparator(AccountFieldParameter sortParameter) {
		switch (sortParameter) {
			case ID: {
				return new IdAccountComparator();
			}
			case NAME: {
				return new NameAccountComparator();
			}
			case AMOUNT: {
				return new AmountAccountComparator();
			}
			case CURRENCY: {
				return new CurrencyAccountComparator();
			}
			default: {
				return new IdAccountComparator();
			}
		}
	}
}