package xyz.crowxx.imserver.service;

import org.springframework.stereotype.Service;
import xyz.crowxx.imserver.core.AppBeanUtils;
import xyz.crowxx.imserver.core.exception.NotFoundException;
import xyz.crowxx.imserver.model.Account;
import xyz.crowxx.imserver.repository.AccountRepository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Resource
    AccountRepository accountRepository;
    public Account findAccountById(Long id){
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isEmpty()){
            throw new NotFoundException();
        }
        return accountOptional.get();
    }
    public Account updateAccount(Long id,Account accountPara){
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isEmpty()){
            throw new NotFoundException();
        }
        Account account = accountOptional.get();
        accountPara.setPassword(null);
        AppBeanUtils.copyNotNullProperties(accountPara,account);
        accountRepository.save(account);
        return account;
    }
    public Account addAccount(Account account){
        return accountRepository.save(account);
    }

    public List<Account> findUsersByName(String name) {
        return accountRepository.findAccountsByNameLike("%"+name+"%");
    }
}
