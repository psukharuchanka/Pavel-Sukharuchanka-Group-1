package com.jmp.services.bank.service.persistance.eao;

import java.util.List;

import javax.ejb.Local;

import com.jmp.services.bank.service.persistance.dto.Account;
import com.jmp.services.bank.service.util.AccountSortParam;
import com.jmp.services.bank.service.util.SortType;

@Local
public interface AccountManagerLocal {

    int addAccount(Account account);
    
    Account getAccount(int id);
	
    void deleteAccount(int id);

    List<Account> getAllAccounts();
    
    List<Account> sort(SortType type, AccountSortParam param);
    
    Account exchange(int accountId, int billId, int currencyId);

}
