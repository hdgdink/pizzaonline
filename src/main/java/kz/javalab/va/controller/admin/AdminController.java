package kz.javalab.va.controller.admin;

import kz.javalab.va.entity.order.Status;
import kz.javalab.va.service.admin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
@RequestMapping("/")
public class AdminController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private AdminProductsService productsService;
    @Autowired
    private AdminSizesService sizesService;
    @Autowired
    private AdminAccountsService accountsService;
    @Autowired
    private AdminOrderDetService orderDetService;
    @Autowired
    private AdminOrderService orderService;
    @Autowired
    private AdminTypesService typesService;


    @RequestMapping(value = "/admin_cabinet")
    public String showAdminCab(Model model) {
        model.addAttribute("user_list", accountsService.getUserList());
        model.addAttribute("roles", accountsService.getRoles());

        return "admin_cabinet";
    }


    @RequestMapping("/admin_products")
    public String showAdminProd(Model model) {
        model.addAttribute("products_list", productsService.getProductList());
        model.addAttribute("typeList", typesService.getTypes());
        return "admin_products";
    }


    @RequestMapping("/admin_orders")
    public String showAdminOrders(Model model) {
        model.addAttribute("orderList", orderService.getOrders());
        model.addAttribute("statusList", Arrays.asList(Status.values()));
        return "admin_orders";
    }


    @RequestMapping("/admin_order_details")
    public String showAdminOrderDet(Model model) {
        model.addAttribute("allOrderDetails", orderDetService.getAllDetails());
        return "admin_order_details";
    }


    @RequestMapping("/admin_types")
    public String showAdminTypes(Model model) {
        model.addAttribute("typeList", typesService.getTypes());
        return "admin_types";
    }


    @RequestMapping("/admin_sizes")
    public String showAdminSizes(Model model) {
        model.addAttribute("allSizesAdmin", sizesService.getSizes());

        return "admin_sizes";
    }
}