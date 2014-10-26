package com.jmp.concurrency.services.bank.service.predicate;

import java.math.BigDecimal;

import com.jmp.concurrency.services.bank.service.dto.Account;


public class AmountAccountPredicate implements AccountPredicate {
	
	private BigDecimal minValue = null;
	
	private BigDecimal maxValue = null;
    
	public AmountAccountPredicate(BigDecimal minValue, BigDecimal maxValue) {
		super();
		this.minValue = minValue;
		this.maxValue = maxValue;
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
		return account.getAmount().compareTo(minValue) >= 0 
				&& account.getAmount().compareTo(maxValue) <= 0 ;
	}
}