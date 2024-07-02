package com.basic1.controller;

import com.basic1.boundedContext.member.entity.Member;
import com.basic1.boundedContext.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;

// @Controller
// 개발자가 스프링부트에 말한다.
// HomeController는 컨트롤러이다.
@Controller
public class HomeController {
  /*
  // 필드 주입
  @Autowired
  private MemberService memberService;
   */
  private final MemberService memberService;

  private int count;
  private List<Person> people;

  // @Autowired 생략 가능(생성자 주입)
  public HomeController(MemberService memberService) {
    count = -1;
    people = new ArrayList<>();

    this.memberService = memberService;
  }

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

  @GetMapping("/home/increase")
  @ResponseBody
  public int showIncrease() {
    count++;
    return count;
  }

  @GetMapping("/home/plus")
  @ResponseBody
  // @RequestParam의 의미
  // 개발자가 스프링부트에게 말한다.
  // int a는 쿼리스트링에서 a파라미터의 값을 얻은 후 정수화 한 값이어야 한다.
  // @RequestParam 생략 가능
  public int showPlus(@RequestParam(defaultValue = "0") int a, @RequestParam(defaultValue = "0") int b) {
    return a + b;
  }

  @GetMapping("/home/returnBoolean")
  @ResponseBody
  public boolean showReturnBoolean() {
    return true;
  }

  @GetMapping("/home/returnDouble")
  @ResponseBody
  public Double showReturnDouble() {
    return Math.PI;
  }

  @GetMapping("/home/returnIntArray")
  @ResponseBody
  public int[] showReturnIntArray() {
    int[] arr = new int[]{10, 20, 30};

    return arr;
  }

  @GetMapping("/home/returnIntList")
  @ResponseBody
  public List<Integer> showReturnIntList() {
    List<Integer> list = new ArrayList<>() {{
      add(10);
      add(20);
      add(30);
    }};

    /*
    List<Integer> list2 = new ArrayList<>();
    list2.add(10);
    list2.add(20);
    list2.add(30);
    */

    return list;
  }

  @GetMapping("/home/returnMap")
  @ResponseBody
  public Map<String, Object> showReturnMap() {
    Map<String, Object> map = new LinkedHashMap<>() {{
      put("id", 1);
      put("speed", 200);
      put("name", "아반떼");
      put("carNo", new ArrayList<>() {{
        add(2);
        add(3);
        add(4);
      }});
    }};

    return map;
  }

  @GetMapping("/home/returnCar")
  @ResponseBody
  public Car showReturnCar() {
    Car car = new Car(1, 200, "아반떼", new ArrayList<>() {{
      add(2);
      add(3);
      add(4);
    }});

    return car;
  }

  @GetMapping("/home/returnCarV2")
  @ResponseBody
  public Car2 showReturnCar2() {
    Car2 car = new Car2(1, 200, "아반떼", new ArrayList<>() {{
      add(2);
      add(3);
      add(4);
    }});

    return car;
  }

  @GetMapping("/home/returnCarMapList")
  @ResponseBody
  public List<Map<String, Object>> showReturnCarMapList() {
    Map<String, Object> carMap1 = new LinkedHashMap<>() {{
      put("id", 1);
      put("speed", 200);
      put("name", "아반떼");
      put("carNo", new ArrayList<>() {{
        add(2);
        add(3);
        add(4);
      }});
    }};

    Map<String, Object> carMap2 = new LinkedHashMap<>() {{
      put("id", 2);
      put("speed", 400);
      put("name", "제네시스");
      put("carNo", new ArrayList<>() {{
        add(5);
        add(6);
        add(7);
      }});
    }};

    List<Map<String, Object>> list = new ArrayList<>();
    list.add(carMap1);
    list.add(carMap2);

    return list;
  }

  @GetMapping("/home/returnCarList")
  @ResponseBody
  public List<Car2> showReturnCarList() {
    Car2 car1 = new Car2(1, 200, "아반떼", new ArrayList<>() {{
      add(2);
      add(3);
      add(4);
    }});

    Car2 car2 = new Car2(2, 400, "제네시스", new ArrayList<>() {{
      add(4);
      add(5);
      add(6);
    }});

    List<Car2> list = new ArrayList<>();
    list.add(car1);
    list.add(car2);

    return list;
  }

  @GetMapping("/home/addPerson")
  @ResponseBody
  public String addPerson(String name, int age) {
    Person p = new Person(name, age);

    System.out.println(p);

    people.add(p);

    return "%d번 사람이 추가되었습니다.".formatted(p.getId());
  }

  @GetMapping("/home/showPeople")
  @ResponseBody
  public List<Person> showPeople() {
    return people;
  }

  @GetMapping("/home/removePerson")
  @ResponseBody
  public String removePerson(int id) {
    // person -> person.getId() == id
    // 위 함수가 참인 엘리먼트(요소)가 존재하면, 해당 요소를 삭제
    // removed 변수에는 삭제주행 여부가 저장된다.
    // 조건에 맞는 걸 찾았고, 삭제까지 되었다면 true, 아니면 false
    boolean removed = people.removeIf(person -> person.getId() == id);

    if (!removed) {
      return "%d번 사람이 존재하지 않습니다.".formatted(id);
    }

    return "%d번 사람이 삭제되었습니다.".formatted(id);
  }

  @GetMapping("/home/modifyPerson")
  @ResponseBody
  public String modifyPerson(int id, String name, int age) {
    Person found = people
        .stream()
        .filter(p -> p.getId() == id) // 해당 녀석이 참인것만 필터링
        .findFirst() // 필터링 결과가 하나만 남는데, 그 하나 남은걸 가져온다.
        .orElse(null); // 없으면 null을 넣어줘라

    if (found == null) {
      return "%d번 사람이 존재하지 않습니다.".formatted(id);
    }

    found.setName(name);
    found.setAge(age);

    return "%d번 사람이 수정되었습니다.".formatted(id);
  }

  @GetMapping("/home/cookie/increase")
  @ResponseBody
  public int showCookieIncrease(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    int countInCookie = 0;

    if (req.getCookies() != null) {
      countInCookie = Arrays.stream(req.getCookies())
          .filter(cookie -> cookie.getName().equals("count"))
          .map(cookie -> cookie.getValue())
          .mapToInt(Integer::parseInt)
          .findFirst()
          .orElse(0);
    }

    int newCountCookie = countInCookie + 1;

    resp.addCookie(new Cookie("count", countInCookie + ""));

    return newCountCookie;
  }

  @GetMapping("/home/reqAndResp")
  @ResponseBody
  public void showReqAndResp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    int age = Integer.parseInt(req.getParameter("age"));

    resp.getWriter().append("Hello, you are %d years old".formatted(age));
  }

  @GetMapping("/home/user1")
  @ResponseBody
  public Member showUser1() {
   return memberService.findByUsername("user1");
  }
}

class Car {
  private final int id;
  private final int speed;
  private final String name;
  private final List<Integer> carNo;

  public Car(int id, int speed, String name, List<Integer> carNo) {
    this.id = id;
    this.speed = speed;
    this.name = name;
    this.carNo = carNo;
  }

  public int getId() {
    return id;
  }

  public int getSpeed() {
    return speed;
  }

  public String getName() {
    return name;
  }

  public List<Integer> getCarNo() {
    return carNo;
  }
}

@AllArgsConstructor
@Getter
class Car2 {
  private final int id;
  private final int speed;
  @Setter
  private String name;
  @Setter
  private List<Integer> carNo;
}

@AllArgsConstructor
@Getter
@ToString
class Person {
  private static int lastId;
  private final int id;
  @Setter
  private String name;
  @Setter
  private int age;

  static {
    lastId = 0;
  }

  public Person(String name, int age) {
    this(++lastId, name, age);
  }
}