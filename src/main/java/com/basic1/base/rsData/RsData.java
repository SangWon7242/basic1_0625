package com.basic1.base.rsData;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RsData {
  private final String resultCode;
  private final String msg;
  private final Object data;
  private final int age = 10;

  public static RsData of(String resultCode, String msg) {
    return new RsData(resultCode, msg, null);
  }

  public static RsData of(String resultCode, String msg, Object data) {
    return new RsData(resultCode, msg, data);
  }

  public boolean isSuccess() {
    return resultCode.startsWith("S-");
  }
  
  /*
  // is 접두어가 빠지고 해당 메서드의 결과가 JSON 화 되서 출력
  // get도 마찬가지이다.
  public boolean isAbc() {
    return false;
  }

  public int getAbc() {
    return 10;
  }  
  */
}
