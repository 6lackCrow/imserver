package xyz.crowxx.imserver.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import xyz.crowxx.imserver.core.exception.ForbiddenException;
import xyz.crowxx.imserver.core.security.AppUserDetails;
import xyz.crowxx.imserver.model.Account;
import xyz.crowxx.imserver.service.AccountService;
import javax.annotation.Resource;

@RestController
@RequestMapping("mine/")
public class MineController {
    @Resource
    AccountService accountService;
    @GetMapping("/{id}")
    public Account findCurrentAccount(@PathVariable Long id, @AuthenticationPrincipal AppUserDetails appUserDetails){
        System.out.println(appUserDetails);
        if (!id.equals(appUserDetails.account.getId())){
            throw new ForbiddenException();
        }
        return accountService.findAccountById(id);
    }

    @PutMapping("/{uid}")
    Account updateAccount(@PathVariable Long uid,
                          @AuthenticationPrincipal AppUserDetails appUserDetails,
                          @RequestBody Account account
    ){
        if (!uid.equals(appUserDetails.account.getId())){
            throw new ForbiddenException();
        }
        return accountService.updateAccount(uid,account);
    }
}
