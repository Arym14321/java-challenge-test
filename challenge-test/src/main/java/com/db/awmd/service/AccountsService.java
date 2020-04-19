package com.db.awmd.service;

import com.db.awmd.domain.Account;
import com.db.awmd.domain.TransferMoneyUtil;
import com.db.awmd.repository.AccountsRepository;
import lombok.Getter;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountsService {

  @Getter
  private final AccountsRepository accountsRepository;

  @Autowired
  public AccountsService(AccountsRepository accountsRepository) {
    this.accountsRepository = accountsRepository;
  }

  public void createAccount(Account account) {
    this.accountsRepository.createAccount(account,false);
  }

  public Account getAccount(String accountId) {
    return this.accountsRepository.getAccount(accountId);
  }
  public String transferMoney(TransferMoneyUtil transferMoneyUtil) {
	  // get account details based on fromAccountId
	  Account fromAcc = this.accountsRepository.getAccount(transferMoneyUtil.getAccountFromId());
	  Account toAcc = this.accountsRepository.getAccount(transferMoneyUtil.getAccountToId());
	  // check balance in from account
	  
	 int res =  transferMoneyUtil.getTransferAmmount().compareTo(fromAcc.getBalance());
	 // -1 is less than we perform transfer ammount operation
	 if(res  == -1 || res == 0) {
		 BigDecimal minusFromAccAmt = fromAcc.getBalance().subtract(transferMoneyUtil.getTransferAmmount());
		 BigDecimal addToAccAmt = toAcc.getBalance().add(transferMoneyUtil.getTransferAmmount());
		 boolean accRes = accountsRepository.updateBalance(fromAcc.getAccountId(), minusFromAccAmt);
		 boolean addAmtRes = accountsRepository.updateBalance(toAcc.getAccountId(), addToAccAmt);
		 if(accRes && addAmtRes) {
			 return "Ammount Transfer Success"; 
		 } else {
			 return "Ammount Transfer FAIL";
		 }
	 } else {
		 // res 1 means greater than so transfer ammount is greater than accoutal account ammount
		 return "Account Balance is less than transfer Ammount";
	 }
	  
  }
  
}
