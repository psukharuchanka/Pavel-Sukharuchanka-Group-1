package com.jmp.concurrency.services.bank.service.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "currency")
@XmlAccessorType (XmlAccessType.FIELD)
public enum Currency {
	
    USD("USD"), RUB("RUB"), BYR("BYR");
    
    String name = null;

    private Currency(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}
