package kz.javalab.va.controller;

import kz.javalab.va.entity.user.User;
import kz.javalab.va.service.account.LoginService;
import kz.javalab.va.service.account.SignService;
import kz.javalab.va.service.account.UserUpdateService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/")
public class AccountController {
    private Logger logger = Logger.getRootLogger();
    private final static String ERROR_ATT = "error";
    private final static String USER_ATT = "user";
    private static final String REF_ATT = "referer";

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserUpdateService userUpdateService;
    @Autowired
    private
    SignService signService;
    @Autowired
    private HttpServletRequest request;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute User userModel, HttpSession session) {
        User loggedUser = loginService.getUser(userModel);
        logger.info(loggedUser.toString());

        if (loggedUser.getUsername() == null) {
            logger.debug("Wrong username");
            return setModel(ERROR_ATT, "account.notFound", "index");
        }

        if (!loggedUser.getPassword().equals(userModel.getPassword())) {
            logger.debug("Wrong password");
            return setModel(ERROR_ATT, "account.isBad", "index");
        }

        if (loginService.adminCheck(loggedUser)) {
            logger.info("This is admin");
            session.setAttribute(USER_ATT, loggedUser);
            return setModel(USER_ATT, loggedUser, "redirect:/admin_cabinet");
        }

        if (loginService.banCheck(loggedUser)) {
            logger.info("Your account is banned");
            return setModel(ERROR_ATT, "account.bannedAcc", "index");
        }

        return setLoggedModel(session, loggedUser);
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView signIn(@Valid @ModelAttribute User user,
                               BindingResult bindingResult,
                               @RequestParam("re-password") String rePass,
                               HttpSession session) {

        if (!signService.validateUserName(user)) {
            return setModel(ERROR_ATT, "error.userExist", "index");
        }

        if (!signService.comparePasswords(user, rePass)) {
            return setModel(ERROR_ATT, "error.passwordsNotMatch", "index");
        }

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView();
            populateError("email", bindingResult, modelAndView);
            populateError("password", bindingResult, modelAndView);
            populateError("username", bindingResult, modelAndView);
            return modelAndView;
        }

        User loggedUser = signService.createUser(user);

        return setLoggedModel(session, loggedUser);
    }


    @RequestMapping(value = "/logout")
    public String logout(Model model, HttpSession session) {
        model.addAttribute(USER_ATT, new User());
        session.removeAttribute(ERROR_ATT);
        session.removeAttribute("order_details");
        session.removeAttribute("order");
        session.removeAttribute("quantity");
        session.removeAttribute("size");
        session.removeAttribute("finalPrice");
        session.removeAttribute("allSizesAdmin");
        return "index";
    }


    @RequestMapping(value = "info_update", method = RequestMethod.POST)
    public String updateUserInfo(HttpSession session, @RequestParam("email") String email,
                                 @RequestParam("firstname") String firstName,
                                 @RequestParam("lastname") String lastName) {
        String referer = request.getHeader(REF_ATT);
        userUpdateService.updateInfo(session, email, firstName, lastName);

        return "redirect:/" + referer.substring(referer.lastIndexOf("/") + 1, referer.length());
    }


    @RequestMapping(value = "pass_update", method = RequestMethod.POST)
    public ModelAndView updateUserPass(HttpSession session, @RequestParam("oldPassword") String oldPass,
                                       @RequestParam("newPassword1") String newPass1,
                                       @RequestParam("newPassword2") String newPass2) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute(USER_ATT);
        String referer = request.getHeader(REF_ATT);

        modelAndView.setViewName("redirect:/" + referer.substring(referer.lastIndexOf("/") + 1, referer.length()));

        if (!user.getPassword().equals(oldPass)) {
            logger.debug("Old password value is wrong.");
            modelAndView.addObject(ERROR_ATT, "password.oldPasswordWrong");
            return modelAndView;
        }

        if (newPass1.equals("")) {
            logger.debug("New password value is empty.");
            modelAndView.addObject(ERROR_ATT, "password.passwordEmpty");
            return modelAndView;
        }

        if (!newPass1.equals(newPass2)) {
            logger.debug("Passwords don't match.");
            modelAndView.addObject(ERROR_ATT, "error.passwordsNotMatch");
            return modelAndView;
        }

        userUpdateService.updatePass(session, newPass1);
        logger.info("user pass is updated, new pass is " + newPass1);
        return modelAndView;
    }


    private ModelAndView setLoggedModel(HttpSession session, User user) {
        ModelAndView modelAndView = new ModelAndView();
        Map<String, Object> details = loginService.getOrderDetails(user);

        if (details.size() > 0) {
            session.setAttribute("order", details.get("order"));
            session.setAttribute("order_details", details.get("order_details"));
        }

        session.setAttribute(USER_ATT, user);
        modelAndView.setViewName("redirect:/pizza_logged");
        modelAndView.addObject(USER_ATT, user);
        logger.info("Success login");
        return modelAndView;
    }


    private ModelAndView setModel(String attName, Object attVal, String view) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(attName, attVal);
        modelAndView.setViewName(view);
        return modelAndView;
    }

    private void populateError(String field, BindingResult bindingResult, ModelAndView modelAndView) {
        modelAndView.setViewName("index");

        if (bindingResult.hasFieldErrors(field)) {
            modelAndView.addObject("error", bindingResult.getFieldError(field)
                    .getDefaultMessage());
        }
    }
}