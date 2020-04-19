package com.db.awmd.repository;

import com.db.awmd.domain.Account;
import com.db.awmd.domain.TransferMoneyUtil;
import com.db.awmd.exception.DuplicateAccountIdException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.text.html.parser.Entity;

import org.springframework.stereotype.Repository;

@Repository
public class AccountsRepositoryInMemory implements AccountsRepository {

	private final Map<String, Account> accounts = new ConcurrentHashMap<>();

	@Override
	public void createAccount(Account account, boolean isUpdate) throws DuplicateAccountIdException {
		Account previousAccount = accounts.putIfAbsent(account.getAccountId(), account);
		if (previousAccount != null) {
			throw new DuplicateAccountIdException("Account id " + account.getAccountId() + " already exists!");
		}

	}

	@Override
	public Account getAccount(String accountId) {
		return accounts.get(accountId);
	}

	@Override
	public void clearAccounts() {
		accounts.clear();
	}

	@Override
	public boolean updateBalance(String accountId, BigDecimal ammount) {
		boolean flag = false;
		for (Map.Entry<String, Account> map : accounts.entrySet()) {
			if (map.getKey().equals(accountId)) {
				Account replaceAcc = map.getValue();
				replaceAcc.setBalance(ammount);
				accounts.replace(map.getKey(), replaceAcc);
				flag = true;
			}
		}

		return flag;
	}
}
