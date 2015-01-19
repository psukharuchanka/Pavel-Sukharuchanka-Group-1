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

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "BILLS")
@DynamicUpdate
public class Bill  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BILL_ID")
	private int id;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CURRENCY_ID")
    private Currency currency;
    
	@Column(name = "AMOUNT", columnDefinition="decimal(50,10) default '0.00'")
    private BigDecimal amount;
	
	public Bill() {
		super();
	}

	public Bill(Currency currency, BigDecimal amount) {
		super();
		this.currency = currency;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result
				+ ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + id;
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
		Bill other = (Bill) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bill [id=" + id + ", currency=" + currency + ", amount="
				+ amount + "]";
	}

}
