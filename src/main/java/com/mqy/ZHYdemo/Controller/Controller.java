package com.mqy.ZHYdemo.Controller;

import com.mqy.ZHYdemo.Domain.Account;
import com.mqy.ZHYdemo.Domain.PatentQueryParams;
import com.mqy.ZHYdemo.Domain.ResponseBody;
import com.mqy.ZHYdemo.Service.AuthService;
import com.mqy.ZHYdemo.Service.SearchService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class Controller {
    @Resource
    SearchService search;

    @Resource
    AuthService authService;

    @PostMapping("/search")
    public ResponseBody search (@RequestBody PatentQueryParams query) {
        return search.search(query);
    }

    @PostMapping("/auth")
    public ResponseBody getToken (@RequestBody Account account) {
        // 账号密码放在了请求体中，后续考虑加密
        String token = authService.auth(account);
        if (token == null || "".equals(token)) {
            return new ResponseBody(400, "无权限", null);
        }
        return new ResponseBody(200, "成功", token);
    }



}
