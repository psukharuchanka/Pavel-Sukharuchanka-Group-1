package com.jmp.concurrency.services.bank.service.predicate;

import com.jmp.concurrency.services.bank.service.dto.Account;


public class NameAccountPredicate implements AccountPredicate {
	
	private String siteSearch = null;
    
	public NameAccountPredicate(String siteSearch) {
		super();
		this.siteSearch = siteSearch;
	}

	public String getSiteSearch() {
		return siteSearch;
	}

	public void setSiteSearch(String siteSearch) {
		this.siteSearch = siteSearch;
	}

	@Override
	public boolean apply(Account account) {
		String fullName = account.getPerson().getName() + " " + account.getPerson().getSurname();
		return fullName.contains(siteSearch);
	}
}