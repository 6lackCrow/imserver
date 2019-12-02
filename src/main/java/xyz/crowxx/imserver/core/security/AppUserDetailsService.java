package xyz.crowxx.imserver.core.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.crowxx.imserver.model.Account;
import xyz.crowxx.imserver.service.AccountService;

import javax.annotation.Resource;

@Service
public class AppUserDetailsService implements UserDetailsService {


    @Resource
    AccountService accountService;

    /**
     *
     * @param username 泛指能代表 user 的东西  比如 uid  username  token
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //
        Account account = accountService.findAccountById(Long.valueOf(username));

        if(account == null){
            throw new UsernameNotFoundException(username);
        }

        return new AppUserDetails(account);
    }



}


