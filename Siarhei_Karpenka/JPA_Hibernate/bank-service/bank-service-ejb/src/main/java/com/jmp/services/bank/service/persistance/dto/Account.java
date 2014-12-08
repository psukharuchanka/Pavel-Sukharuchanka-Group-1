package com.jmp.services.bank.service.persistance.dto;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "ACCOUNTS")
@DynamicUpdate
public class Account implements Serializable {
    
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACCOUNT_ID")
	private int id;

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PERSON_ID")
    private Person person;

	@OneToMany(fetch=FetchType.EAGER)
	@JoinTable(name="ACCOUNTS_BILLS", 
			joinColumns={ @JoinColumn(name="ACCOUNT_ID", referencedColumnName="ACCOUNT_ID") },
			inverseJoinColumns={ @JoinColumn(name="BILL_ID", referencedColumnName="BILL_ID", unique=true) }
	)
    private Set<Bill> bills;

    public Account() {
    }
    
	public Account(Person person, Set<Bill> bills) {
		super();
		this.person = person;
		this.bills = bills;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Set<Bill> getBills() {
		return bills;
	}

	public void setBills(Set<Bill> bills) {
		this.bills = bills;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bills == null) ? 0 : bills.hashCode());
		result = prime * result + id;
		result = prime * result + ((person == null) ? 0 : person.hashCode());
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
		Account other = (Account) obj;
		if (bills == null) {
			if (other.bills != null)
				return false;
		} else if (!bills.equals(other.bills))
			return false;
		if (id != other.id)
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", person=" + person + ", bills=" + bills
				+ "]";
	}
}
