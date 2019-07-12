package kz.javalab.va.controller;

import kz.javalab.va.entity.user.Role;
import kz.javalab.va.entity.user.User;
import kz.javalab.va.service.general.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/")

public class MainController {
    private final static String USER_ATT = "user";

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AttributeService attSetter;


    @RequestMapping()
    public String start(HttpSession session, Model model) {

        Map<String, List> attributes = attSetter.getAttrList();
        Locale locale = (Locale) session.getAttribute("locale");

        if (locale == null) {
            session.setAttribute("locale", Locale.getDefault());
        }
        session.setAttribute("pizzaList", attributes.get("PizzaList"));
        session.setAttribute("bevList", attributes.get("BevList"));
        session.setAttribute("subList", attributes.get("SubList"));
        session.setAttribute("sizeList", attributes.get("SizeList"));
        model.addAttribute(USER_ATT, new User());

        return "index";
    }


    @RequestMapping(value = "/pizza")
    public String showPizzaUnreg(Model model) {
        model.addAttribute(USER_ATT, new User());
        return "index";
    }


    @RequestMapping(value = "/subs")
    public String showSubsUnreg(Model model) {
        model.addAttribute(USER_ATT, new User());
        return "subs_unreg";
    }


    @RequestMapping(value = "/bev")
    public String showBevUnreg(Model model) {
        model.addAttribute(USER_ATT, new User());
        return "bev_unreg";
    }


    @RequestMapping(value = "/pizza_logged")
    public String showPizzaLogged() {
        return "pizza_logged";
    }


    @RequestMapping(value = "/subs_logged")
    public String showSubsLogged() {
        return "subs_logged";
    }


    @RequestMapping(value = "/bev_logged")
    public String showBevLogged() {
        return "bev_logged";
    }


    @RequestMapping(value = "/locale")
    public String changeLocale(HttpSession session, Locale locale) {
        session.setAttribute("locale", locale);
        String referer = request.getHeader("referer");

        return "redirect:/" + referer.substring(referer.lastIndexOf("/") + 1, referer.length());
    }


    @RequestMapping(value = "/cabinet")
    public String showCabinetPage(HttpSession session) {
        User logUser = (User) session.getAttribute(USER_ATT);

        if (logUser.getRole().equals(Role.ADMIN)) {
            return "redirect:/admin_cabinet";
        } else return "user_cabinet";
    }

}