package com.db.awmd.service;

import com.db.awmd.domain.Account;

public interface NotificationService {

  void notifyAboutTransfer(Account account, String transferDescription);
}
