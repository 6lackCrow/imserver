package xyz.crowxx.imserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.crowxx.imserver.model.AddFriendRequest;

import java.util.List;

public interface AddFriendRequestRepository extends JpaRepository<AddFriendRequest,Long> {
    List<AddFriendRequest> findAddFriendRequestsByToaidEquals(Long toaid);
}