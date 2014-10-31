package com.jmp.concurrency.services.bank.service.predicate;

import com.jmp.concurrency.services.bank.service.dto.Account;

public interface AccountPredicate {
    
    public boolean apply(Account account);
    
}