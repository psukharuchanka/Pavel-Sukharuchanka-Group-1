package com.jmp.concurrency.services.bank.service.dao;

import com.jmp.concurrency.services.bank.service.dto.AccountMap;
import com.jmp.concurrency.services.bank.service.exception.DataManagerException;

public interface DataManager {
    
    public void write(AccountMap accountMap) throws DataManagerException;
    
    public AccountMap read() throws DataManagerException;
    
}