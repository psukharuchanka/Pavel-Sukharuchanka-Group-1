package com.jmp.services.bank.service.persistance.eao;

import java.util.List;

import javax.ejb.Remote;

import com.jmp.services.bank.service.persistance.dto.Currency;

@Remote
public interface CurrencyManagerRemote {

    int addCurrency(Currency currency);
    
    Currency getCurrency(int id);
    
    Currency getCurrency(String name);
	
    void deleteCurrency(int id);

    List<Currency> getAllCurrencies();

}

