package com.basic1.base.rq;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public class Rq {
  private final HttpServletRequest req;
  private final HttpServletResponse resp;

  private String getCookie(String name, String defaultValue) {
    if (req.getCookies() == null) return defaultValue;

    return Arrays.stream(req.getCookies())
        .filter(cookie -> cookie.getName().equals(name))
        .map(Cookie::getValue)
        .findFirst()
        .orElse(defaultValue);
  }

  public long getCookieAsLong(String name, long defaultValue) {
    String value = getCookie(name, null);

    if (value == null) {
      return defaultValue;
    }

    try {
      return Long.parseLong(value);
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  public void setCookie(String name, long value) {
    setCookie(name, value + "");
  }

  public void setCookie(String name, String value) {
    resp.addCookie(new Cookie(name, value));
  }

  public boolean removeCookie(String name) {
    if (req.getCookies() != null) {
      Arrays.stream(req.getCookies())
          .filter(cookie -> cookie.getName().equals(name))
          .forEach(cookie -> {
            cookie.setMaxAge(0); // 쿠키의 수명을 0으로 줄인다.
            resp.addCookie(cookie); // 줄인 녀석을 쿠키에 넣어준다.
          });

      return Arrays.stream(req.getCookies())
          .filter(cookie -> cookie.getName().equals(name))
          .count() > 0;
      // 쿠키를 가져왔는데 그 개수가 0보다 크다? true를 반환
      // true를 반환했다는 의미는 로그아웃이 되어 있지 않다.
    }

    return false;
  }
}