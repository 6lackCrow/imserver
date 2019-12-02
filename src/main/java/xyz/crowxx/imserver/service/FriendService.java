package xyz.crowxx.imserver.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.crowxx.imserver.model.Account;
import xyz.crowxx.imserver.model.FriendRelational;
import xyz.crowxx.imserver.repository.AccountRepository;
import xyz.crowxx.imserver.repository.FriendRelationalRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service
public class FriendService {
    @Resource
    FriendRelationalRepository friendRelationalRepository;
    @Resource
    AccountService accountService;
    @Resource
    AccountRepository accountRepository;
    public List<Account> findAll(Pageable pageable, Long id) {
        List<FriendRelational> relationals = friendRelationalRepository.findFriendRelationalsByAidEquals(pageable, id);
        List<Long> toaidList = new ArrayList<>();
        for (FriendRelational relational : relationals) {
            Long aidto = relational.getAidto();
            toaidList.add(aidto);
        }
        List<Account> res = accountRepository.findAllById(toaidList);
        return res;
    }
    public Account findFriend(Long uid) {
        Account account = accountService.findAccountById(uid);
        return account;
    }
    public void deleteRelational(Long curId, Long uid) {
        FriendRelational r1 = friendRelationalRepository.findFriendRelationalsByAidEqualsAndAidtoEquals(curId, uid);
        FriendRelational r2 = friendRelationalRepository.findFriendRelationalsByAidEqualsAndAidtoEquals(uid, curId);
        friendRelationalRepository.delete(r1);
        friendRelationalRepository.delete(r2);
    }

}
