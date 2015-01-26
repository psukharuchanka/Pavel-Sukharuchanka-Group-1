package com.jmp.services.bank.service.persistance.eao;

import java.util.List;

import javax.ejb.Local;

import com.jmp.services.bank.service.persistance.dto.Currency;

@Local
public interface CurrencyManagerLocal {

    int addCurrency(Currency Currency);
    
    Currency getCurrency(int id);
    
    Currency getCurrency(String name);
	
    void deleteCurrency(int id);

    List<Currency> getAllCurrencies();

}
