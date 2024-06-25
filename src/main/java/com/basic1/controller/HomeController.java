package com.basic1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// @Controller
// 개발자가 스프링부트에 말한다.
// HomeController는 컨트롤러이다.
@Controller
public class HomeController {
  // @GetMapping("/home/main") 의 의미
  // 개발자가 스프링부트한테 말한다.
  // 만약에 /home/main 요청이 들어오면 아래 메서드를 실행시켜줘
  
  @GetMapping("/home/main")
  
  // @ResponseBody의 의미
  // 아래 메서드를 실행한 후 그 리턴값을 응답으로 삼아줘 body에 출력해줘
  @ResponseBody
  public String showHome() {
    return "안녕하세요";
  }

  @GetMapping("/home/main2")
  @ResponseBody
  public String showHome2() {
    return "환영합니다.";
  }

  @GetMapping("/home/main3")
  @ResponseBody
  public String showHome3() {
    return "스프링부트는 획기적입니다.";
  }
}
