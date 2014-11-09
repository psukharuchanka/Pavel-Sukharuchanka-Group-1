package com.jmp.concurrency.services.bank.service.predicate;

import java.math.BigDecimal;

import com.jmp.concurrency.services.bank.service.dto.Account;
import com.jmp.concurrency.services.bank.service.dto.Currency;


public class AmountAccountPredicate implements AccountPredicate {
	
	private BigDecimal minValue = null;
	
	private BigDecimal maxValue = null;
	
	private Currency currency = null;
    
	public AmountAccountPredicate(Currency currency, BigDecimal minValue, BigDecimal maxValue) {
		super();
		this.currency = currency;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
	public BigDecimal getMinValue() {
		return minValue;
	}

	public void setMinValue(BigDecimal minValue) {
		this.minValue = minValue;
	}

	public BigDecimal getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(BigDecimal maxValue) {
		this.maxValue = maxValue;
	}

	@Override
	public boolean apply(Account account) {
		return account.getCurrency() == currency 
				&& account.getAmount().compareTo(minValue) >= 0 
				&& account.getAmount().compareTo(maxValue) <= 0;
	}



}