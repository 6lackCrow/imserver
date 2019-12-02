package xyz.crowxx.imserver.repository;

import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.crowxx.imserver.model.FriendRelational;

import java.util.List;

public interface FriendRelationalRepository extends JpaRepository<FriendRelational,Long> {

    FriendRelational findFriendRelationalsByAidEqualsAndAidtoEquals(Long aid,Long toaid);


    List<FriendRelational> findFriendRelationalsByAidEquals(Pageable pageable, Long aid);
}
