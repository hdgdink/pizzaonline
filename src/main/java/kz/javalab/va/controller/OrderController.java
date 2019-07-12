package kz.javalab.va.controller;

import kz.javalab.va.entity.user.User;
import kz.javalab.va.service.order.CheckoutService;
import kz.javalab.va.service.order.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/")
public class OrderController {
    private Logger logger = Logger.getRootLogger();
    private static final String REF_ATT = "referer";
    private final static String USER_ATT = "user";

    @Autowired
    private OrderService orderService = new OrderService();
    @Autowired
    private CheckoutService checkoutService = new CheckoutService();
    @Autowired
    private HttpServletRequest request;


    @RequestMapping(value = "add_to_orderlist")
    public String addToOrderList(@RequestParam("count") int count, @RequestParam("food") int foodId,
                                 @RequestParam("size") int sizeVal, HttpSession session) {
        User loggedUser = (User) session.getAttribute(USER_ATT);
        String referer = request.getHeader(REF_ATT);

        if (loggedUser != null && count > 0) {
            orderService.addToList(count, sizeVal, foodId, session, loggedUser);
        }

        return "redirect:/" + referer.substring(referer.lastIndexOf("/") + 1, referer.length());
    }


    @RequestMapping(value = "del_from_orderlist")
    public String removeEntry(HttpSession session,
                              @RequestParam("order_detail_id") int orderDetId,
                              @RequestParam("order_detail_final_price") int productFinalPrice) {
        String referer = request.getHeader(REF_ATT);

        orderService.removeEntryFromOrderList(orderDetId, productFinalPrice, session);
        return "redirect:/" + referer.substring(referer.lastIndexOf("/") + 1, referer.length());
    }


    @RequestMapping(value = "chekout_order", method = RequestMethod.POST)
    public ModelAndView checkOut(HttpSession session,
                                 @RequestParam("address") String address,
                                 @RequestParam("phone") String phone) {
        User user = (User) session.getAttribute(USER_ATT);
        ModelAndView modelAndView = new ModelAndView();
        String referer = request.getHeader(REF_ATT);
        String view = "redirect:/" + referer.substring(referer.lastIndexOf("/") + 1, referer.length());

        if (user != null) {
            checkoutService.checkOutOrder(session, phone, address);
        } else {
            modelAndView.addObject("error", "account.notFound");
        }

        modelAndView.setViewName(view);
        return modelAndView;
    }
}