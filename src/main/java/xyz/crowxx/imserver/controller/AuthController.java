package xyz.crowxx.imserver.controller;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.crowxx.imserver.core.exception.NotFoundException;
import xyz.crowxx.imserver.core.security.AppUserDetails;
import xyz.crowxx.imserver.model.Account;
import xyz.crowxx.imserver.model.Token;
import xyz.crowxx.imserver.service.AuthService;
import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Resource
    AuthService authService;
    /*注册*/
    @PostMapping("/signup")
    public Token signUp(@Valid @RequestBody Para signUpPara){
        Account signUp = authService.signUp(signUpPara.getPhone(), signUpPara.getPassword());
        return authService.signIn(signUp.getPhone(),signUp.getPassword());
    }

    /*登录*/
    @PostMapping("/signin")
    public Token signIn(@Valid @RequestBody Para signInPara){
        Token signInToken = authService.signIn(signInPara.getPhone(),signInPara.getPassword());
        if (signInToken == null){
            throw new NotFoundException();
        }
        return signInToken;
    }
    /*修改密码*/
    @PostMapping("/update_password")
    public void updatePassword(@AuthenticationPrincipal AppUserDetails appUserDetails,
                               @Valid @RequestBody UpdatePwd updatePwd
    ){
        authService.updatePWD(appUserDetails.account.getId(),updatePwd.getOldPassword(),updatePwd.getNewPassword());
    }
}
@Data
class Para{
    @NotBlank(message = "手机号不能为空")
    String phone;
    @NotBlank(message = "密码不能为空")
    String password;
}
@Getter
@Setter
class UpdatePwd{
    @NotBlank()
    String oldPassword;
    @NotBlank()
    String newPassword;
}
