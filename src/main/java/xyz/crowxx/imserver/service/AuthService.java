package xyz.crowxx.imserver.service;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import xyz.crowxx.imserver.core.exception.ForbiddenException;
import xyz.crowxx.imserver.core.exception.NotFoundException;
import xyz.crowxx.imserver.model.Account;
import xyz.crowxx.imserver.model.Token;
import xyz.crowxx.imserver.repository.AccountRepository;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {
    @Resource
    AccountRepository accountRepository;
    @Resource
    TokenService tokenService;
    public Account signUp(String phone,String password){
        Account account = new Account();
        account.setPhone(phone);
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        account.setPassword(md5Password);
        account.setName("用户+"+phone);
        return accountRepository.save(account);
    }

    public Account findAccountByPhone(String phone){
        return accountRepository.findAccountByPhone(phone);
    }

    public Token signIn(String phone, String password) {
        Account account = accountRepository.findAccountByPhone(phone);
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!account.getPassword().equals(md5Password)) {
            return null;
        }

        Token token = new Token();
        token.setUserid(account.getId());
        String tokenVa = UUID.randomUUID().toString();
        token.setToken(tokenVa);
        tokenService.addToken(token);
        return token;
    }

    public void updatePWD(Long uid,String oldPassword, String newPassword) {
        Optional<Account> byId = accountRepository.findById(uid);
        if (byId.isEmpty()){
            throw new NotFoundException();
        }
        Account account = byId.get();
        String md5DigestAsHex = DigestUtils.md5DigestAsHex(oldPassword.getBytes());
        if (!account.getPassword().equals(md5DigestAsHex)) {
            throw new ForbiddenException();
        }
        String newPasswordMD5 = DigestUtils.md5DigestAsHex(newPassword.getBytes());
        account.setPassword(newPasswordMD5);
        accountRepository.save(account);
    }
}
