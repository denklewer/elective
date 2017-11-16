//package epam_team1.web.controllers;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//@Controller
//@RequestMapping("/login")
//public class LoginController {
//
//    @RequestMapping(method = RequestMethod.GET)
//    public String loginPage(Model model){
//        return "login";
//    }
//
//
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login(Model model) {
//        return "login";
//    }
//
//
//    @RequestMapping(value = "/login", params = {"failed"}, method = RequestMethod.GET)
//    public String loginerror(Model model) {
//        model.addAttribute("error", "true");
//        return "login";
//
//    }
//
//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public String logout(Model model) {
//        return "login";
//    }
//}
