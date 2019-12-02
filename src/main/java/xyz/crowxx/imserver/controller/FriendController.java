package xyz.crowxx.imserver.controller;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import xyz.crowxx.imserver.core.security.AppUserDetails;
import xyz.crowxx.imserver.model.Account;
import xyz.crowxx.imserver.service.FriendService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/friend")
public class FriendController {

    @Resource
    FriendService friendService;

    @GetMapping("/")
    List<Account> index(@AuthenticationPrincipal AppUserDetails appUserDetails,
                        @RequestParam(defaultValue = "0") Integer page,
                        @RequestParam(defaultValue = "10") Integer size){

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);

        return friendService.findAll(pageable, appUserDetails.account.getId());
    }

    @DeleteMapping("/{uid}")
    void delete(@AuthenticationPrincipal AppUserDetails appUserDetails,@PathVariable Long uid){
        Long curId = appUserDetails.account.getId();
        friendService.deleteRelational(curId,uid);
    }

    @GetMapping("/{uid}")
    Account get(@PathVariable Long uid){
        return friendService.findFriend(uid);
    }

}

