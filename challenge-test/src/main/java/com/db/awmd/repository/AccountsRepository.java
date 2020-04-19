package com.db.awmd.repository;

import java.math.BigDecimal;

import com.db.awmd.domain.Account;
import com.db.awmd.domain.TransferMoneyUtil;
import com.db.awmd.exception.DuplicateAccountIdException;

public interface AccountsRepository {

  void createAccount(Account account, boolean isUpdate) throws DuplicateAccountIdException;

  Account getAccount(String accountId);

  void clearAccounts();
  
  public boolean updateBalance(String accountId, BigDecimal ammount);
}
