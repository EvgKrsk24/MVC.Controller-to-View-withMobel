package ru.spring.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller // сканируется @ComponentScan из SpringConfig
@RequestMapping("/first") // задает нв путь для нижних классов
public class FirstController {

    @GetMapping("/hello") //хотим принимать Get запросы. localhost:8080/first/hello
    // чз аннотацию считываем параметры из запроса
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname", required = false) String surname,
                            Model model) { // request содержит все сведения о посту зап от пользователя
        // required = false, если в запросе не передаются парметры то их знач null


        //System.out.println("Hello, " + name + " " + surname); перемести в модель
        model.addAttribute("message", "Hello, " + name + " " + surname);


        return "first/hello"; // возвращаем страницу hello из папки first
    }
    // ниже аналогично но чз HttpServletRequest (с помощью объекта) считываем параметры из запроса
//    public String helloPage(HttpServletRequest request) { // request содержит все сведения о посту зап от пользователя
//String name = request.getParameter("name");// получаем параметры из запроса
//String surname = request.getParameter("surname");
//
//System.out.println("Hello, " + name + " " + surname);
//
//        return "first/hello"; // возвращаем страницу hello из папки first
//    }

    @GetMapping("/goodbye") //хотим принимать Get запросы. localhost:8080/first/goodbye
    public String goodByePage() {
        return "first/goodbye"; // возвращаем страницу goodbye из папки first
    }

//    @GetMapping("/calculator") // не верно
//    public String calculator (@RequestParam(value = "a", required = false) int a,
//                           @RequestParam(value = "b", required = false) int b,
//                              @RequestParam(value = "action", required = false) int action) {
//        System.out.println(action);
//    }
@GetMapping("/calculator")
    public String calculator(HttpServletRequest request, Model modelCalc) {
        String a = request.getParameter("a");
        String b = request.getParameter("b");
        String action = request.getParameter("action");
        int ai= Integer.parseInt(a.trim());
        int bi= Integer.parseInt(b.trim());
        double result = 0;
        if (action.equals("multiplication")){
result=ai*bi;
        } else if (action.equals("addition")) {
            result=ai+bi;
        } else if (action.equals("subtraction")) {
            result=ai-bi;
        } else if (action.equals("division")) {
            result=ai/(double)bi;
        }

        modelCalc.addAttribute("actionCalc",result); // передача результата с помощью модели в представление

        return "first/calculator";
    }

}
