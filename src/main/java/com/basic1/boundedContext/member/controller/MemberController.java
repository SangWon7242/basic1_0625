package com.basic1.boundedContext.member.controller;

import com.basic1.base.rq.Rq;
import com.basic1.base.rsData.RsData;
import com.basic1.boundedContext.member.entity.Member;
import com.basic1.boundedContext.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@AllArgsConstructor
@Controller
public class MemberController {
  private final MemberService memberService;
  private final Rq rq;

  @GetMapping("/member/login")
  @ResponseBody
  public RsData login(String username, String password, HttpServletRequest req, HttpServletResponse resp) {
    if (username.trim().isEmpty()) {
      return RsData.of("F-3", "username(을)를 입력해주세요.");
    }

    if (password.trim().isEmpty()) {
      return RsData.of("F-4", "password(을)를 입력해주세요.");
    }

    RsData rsData = memberService.tryLogin(username, password);

    if (rsData.isSuccess()) {
      Member member = (Member) rsData.getData();
      rq.setSession("loginedMemberId", member.getId());
    }

    return rsData;
  }

  @GetMapping("/member/logout")
  @ResponseBody
  public RsData logout(HttpServletRequest req, HttpServletResponse resp) {
    boolean cookieRemoved = rq.removeSession("loginedMemberId");

    if(!cookieRemoved) {
      return RsData.of("S-2", "이미 로그아웃 상태입니다.");
    }

    return RsData.of("S-1", "로그아웃 되었습니다.");
  }

  @GetMapping("/member/me")
  @ResponseBody
  public RsData showMe(HttpServletRequest req, HttpServletResponse resp) {
    long loginedMemberId = rq.getSessionAsLong("loginedMemberId", 0);

    boolean isLogined = loginedMemberId > 0; // 0보다 크다는 의미는 로그인이 되었다는 의미

    if (!isLogined) {
      return RsData.of("F-1", "로그인 후 이용해주세요.");
    }

    Member member = memberService.findById(loginedMemberId);

    return RsData.of("S-1", "당신의 username(은)는 %s 입니다.".formatted(member.getUsername()));
  }
}
