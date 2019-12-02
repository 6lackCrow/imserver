package xyz.crowxx.imserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.crowxx.imserver.model.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findAccountByPhone(String phone);

    List<Account> findAccountsByNameLike(String s);
}
