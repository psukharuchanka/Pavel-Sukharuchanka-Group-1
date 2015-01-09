package com.jmp.services.bank.service.persistance.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "CURRENCY_RATES", 
		uniqueConstraints={@UniqueConstraint(columnNames = {"CURRENCY_INIT_ID", "CURRENCY_EXCH_ID"})})
@DynamicUpdate
public class CurrencyRate  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CURRENCY_RATE_ID")
	private int id;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CURRENCY_INIT_ID")
    private Currency initialCurrency;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CURRENCY_EXCH_ID")
    private Currency exchangeCurrency;
	
	@Column(name = "RATE", columnDefinition="decimal(50,10) default '0.00'")
    private BigDecimal rate;
	
    public CurrencyRate() {
		super();
	}

	public CurrencyRate(Currency initialCurrency, Currency exchangeCurrency, BigDecimal rate) {
		super();
		this.initialCurrency = initialCurrency;
		this.exchangeCurrency = exchangeCurrency;
		this.rate = rate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Currency getInitialCurrency() {
		return initialCurrency;
	}

	public void setInitialCurrency(Currency initialCurrency) {
		this.initialCurrency = initialCurrency;
	}

	public Currency getExchangeCurrency() {
		return exchangeCurrency;
	}

	public void setExchangeCurrency(Currency exchangeCurrency) {
		this.exchangeCurrency = exchangeCurrency;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((exchangeCurrency == null) ? 0 : exchangeCurrency.hashCode());
		result = prime * result
				+ ((initialCurrency == null) ? 0 : initialCurrency.hashCode());
		result = prime * result + ((rate == null) ? 0 : rate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CurrencyRate other = (CurrencyRate) obj;
		if (exchangeCurrency == null) {
			if (other.exchangeCurrency != null)
				return false;
		} else if (!exchangeCurrency.equals(other.exchangeCurrency))
			return false;
		if (initialCurrency == null) {
			if (other.initialCurrency != null)
				return false;
		} else if (!initialCurrency.equals(other.initialCurrency))
			return false;
		if (rate == null) {
			if (other.rate != null)
				return false;
		} else if (!rate.equals(other.rate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CurrencyRate [id=" + id + ", initialCurrency="
				+ initialCurrency + ", exchangeCurrency=" + exchangeCurrency
				+ ", rate=" + rate + "]";
	}
	
}
