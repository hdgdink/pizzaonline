package kz.javalab.va.controller.admin;

import kz.javalab.va.entity.Food;
import kz.javalab.va.entity.OrderDetails;
import kz.javalab.va.entity.Size;
import kz.javalab.va.entity.Type;
import kz.javalab.va.entity.order.Order;
import kz.javalab.va.entity.user.User;
import kz.javalab.va.service.admin.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class AdminActionsController {
    private Logger logger = Logger.getRootLogger();
    @Autowired
    private AdminProductsService productsService;
    @Autowired
    private AdminSizesService sizesService;
    @Autowired
    private AdminAccountsService accountsService;
    @Autowired
    private AdminOrderService orderService;
    @Autowired
    private AdminTypesService typesService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "edit_user", method = RequestMethod.POST)
    public String editUser(@ModelAttribute User userModel, Model model) {
        logger.info("Controller " + userModel.toString());
        String referer = request.getHeader("referer");

        if (!accountsService.updateUser(userModel)) {
            model.addAttribute("error", "error.userExist");
        }

        return "redirect:/" + referer.substring(referer.lastIndexOf("/") + 1, referer.length());
    }


    @RequestMapping(value = "create_user", method = RequestMethod.POST)
    public String createUser(@ModelAttribute User userModel, Model model) {
        String referer = request.getHeader("referer");

        if (!accountsService.createUser(userModel)) {
            model.addAttribute("error", "error.userExist");
        }

        return "redirect:/" + referer.substring(referer.lastIndexOf("/") + 1, referer.length());
    }


    @RequestMapping(value = "edit_product", method = RequestMethod.POST)
    public String editProduct(@ModelAttribute Food foodModel) {
        logger.info("Controller " + foodModel.toString());
        String referer = request.getHeader("referer");
        productsService.updateProduct(foodModel);

        return "redirect:/" + referer.substring(referer.lastIndexOf("/") + 1, referer.length());
    }


    @RequestMapping(value = "create_product", method = RequestMethod.POST)
    public String createProduct(@ModelAttribute Food foodModel) {
        logger.info("Controller " + foodModel.toString());
        String referer = request.getHeader("referer");
        productsService.createProduct(foodModel);

        return "redirect:/" + referer.substring(referer.lastIndexOf("/") + 1, referer.length());
    }

    @RequestMapping(value = "edit_order", method = RequestMethod.POST)
    public String editOrder(@ModelAttribute Order orderModel) {
        logger.info("Controller " + orderModel.toString());
        String referer = request.getHeader("referer");
        orderService.updateOrder(orderModel);

        return "redirect:/" + referer.substring(referer.lastIndexOf("/") + 1, referer.length());
    }


    @RequestMapping(value = "create_order", method = RequestMethod.POST)
    public String createOrder(@ModelAttribute Order orderModel) {
        logger.info("Controller " + orderModel.toString());
        orderService.createOrder(orderModel);
        String referer = request.getHeader("referer");

        return "redirect:/" + referer.substring(referer.lastIndexOf("/") + 1, referer.length());
    }

    @RequestMapping(value = "edit_type", method = RequestMethod.POST)
    public String editType(@ModelAttribute Type typeModel) {
        logger.info("Controller " + typeModel.toString());
        String referer = request.getHeader("referer");
        typesService.editSize(typeModel);

        return "redirect:/" + referer.substring(referer.lastIndexOf("/") + 1, referer.length());
    }

    @RequestMapping(value = "create_type", method = RequestMethod.POST)
    public String createType(@ModelAttribute Type typeModel) {
        logger.info("Controller " + typeModel.toString());
        String referer = request.getHeader("referer");
        typesService.createSize(typeModel);

        return "redirect:/" + referer.substring(referer.lastIndexOf("/") + 1, referer.length());
    }

    @RequestMapping(value = "edit_size", method = RequestMethod.POST)
    public String editSize(@ModelAttribute Size sizeModel, HttpSession session) {
        logger.info("Controller " + sizeModel.toString());
        String referer = request.getHeader("referer");
        session.setAttribute("SizeList", sizesService.editSize(sizeModel));

        return "redirect:/" + referer.substring(referer.lastIndexOf("/") + 1, referer.length());
    }

    @RequestMapping(value = "create_size", method = RequestMethod.POST)
    public String createSize(@ModelAttribute Size sizeModel, HttpSession session) {
        logger.info("Controller " + sizeModel.toString());
        String referer = request.getHeader("referer");
        session.setAttribute("SizeList", sizesService.createSize(sizeModel));

        return "redirect:/" + referer.substring(referer.lastIndexOf("/") + 1, referer.length());
    }

    @RequestMapping(value = "edit_order_details", method = RequestMethod.POST)
    public String editOrderDet(@ModelAttribute OrderDetails orderDetModel) {
        logger.info("Controller " + orderDetModel.toString());
        String referer = request.getHeader("referer");

        return "redirect:/" + referer.substring(referer.lastIndexOf("/") + 1, referer.length());
    }


    @RequestMapping(value = "create_order_details", method = RequestMethod.POST)
    public String createOrderDet(@ModelAttribute OrderDetails orderDetModel) {
        logger.info("Controller " + orderDetModel.toString());
        String referer = request.getHeader("referer");

        return "redirect:/" + referer.substring(referer.lastIndexOf("/") + 1, referer.length());
    }

}