package com.jmp.concurrency.services.bank.service.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.jmp.concurrency.services.bank.service.dto.Account;
import com.jmp.concurrency.services.bank.service.predicate.AccountPredicate;

public class AccountPredicateFilter {

	public static List<Account> filter(Collection<Account> target, AccountPredicate predicate) {
		List<Account> result = new ArrayList<Account>();
		for (Account element : target) {
			if (predicate.apply(element)) {
				result.add(element);
			}
		}
		return result;
	}

	public static Account select(Collection<Account> target, AccountPredicate predicate) {
		Account result = null;
		for (Account element : target) {
			if (!predicate.apply(element)) {
				continue;
			}
			result = element;
			break;
		}
		return result;
	}

	public static Account select(Collection<Account> target, AccountPredicate predicate, Account defaultValue) {
		Account result = defaultValue;
		for (Account element : target) {
			if (!predicate.apply(element)) {
				continue;
			}
			result = element;
			break;
		}
		return result;
	}
}