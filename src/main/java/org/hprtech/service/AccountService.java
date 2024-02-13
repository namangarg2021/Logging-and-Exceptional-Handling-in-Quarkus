package org.hprtech.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.hprtech.dto.Account;
import org.hprtech.resource.Resource;
import org.jboss.logging.Logger;

@ApplicationScoped
public class AccountService {
    public static final Logger LOG = Logger.getLogger(AccountService.class);

    public boolean isAccountAlreadyExist(Account account) {
        LOG.info("Entering in AccountService::isAccountAlreadyExist()");
        LOG.debug("AccountService::isAccountAlreadyExist() Account "+account);

        Account account1 = getAccountByAccNumber(account.getAccountNumber());
        LOG.debug("AccountService::isAccountAlreadyExist() Account1 "+account1);

        if(account1 != null) {
            LOG.info("Returning from AccountService::isAccountAlreadyExist()");
            return true;
        } else {
            LOG.info("Returning from AccountService::isAccountAlreadyExist()");
            return openNewAccount(account);
        }
    }

    private boolean openNewAccount(Account account) {
        LOG.info("Entering in AccountService::openNewAccount()");
        LOG.debug("AccountService::openNewAccount() Account "+account);
        LOG.info("Returning from AccountService::openNewAccount()");
        return true;
    }

    private Account getAccountByAccNumber(long accountNumber) {
        LOG.info("Entering in AccountService::getAccountByAccNumber()");
        LOG.debug("AccountService::getAccountByAccNumber() accountNumber "+accountNumber);

        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setName("Rakesh");
        LOG.debug("AccountService::getAccountByAccNumber() account "+account);

        LOG.info("Returning from AccountService::getAccountByAccNumber()");
        return  account;
    }
}
