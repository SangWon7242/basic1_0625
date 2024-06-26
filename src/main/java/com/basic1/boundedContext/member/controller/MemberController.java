package com.basic1.boundedContext.member.controller;

import com.basic1.base.rsData.RsData;
import com.basic1.boundedContext.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {
  /*
  // 필드 주입
  @Autowired
  private MemberService memberService;
   */
  private final MemberService memberService;

  // 생성자 주입
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping("/member/login")
  @ResponseBody
  public RsData login(String username, String password) {
    if(username.trim().isEmpty()) {
      return RsData.of("F-3", "username(을)를 입력해주세요.");
    }

    if(password.trim().isEmpty()) {
      return RsData.of("F-4", "password(을)를 입력해주세요.");
    }

    return memberService.tryLogin(username, password);
  }
}
