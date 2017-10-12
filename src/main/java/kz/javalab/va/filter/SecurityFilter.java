package kz.javalab.va.filter;

import kz.javalab.va.action.admin.CreateEntityAdmin;
import kz.javalab.va.entity.user.Role;
import kz.javalab.va.entity.user.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HdgDink} on 01.10.2017.
 */
public class SecurityFilter implements Filter {
    private Map<String, EnumSet<Role>> actions = new HashMap<String, EnumSet<Role>>();

    /**
     * Creates a map of URLs and users who have rights to access it.
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        EnumSet<Role> all = EnumSet.of(Role.UNREGISTERED_USER, Role.CLIENT, Role.ADMIN);
        EnumSet<Role> authorized = EnumSet.of(Role.CLIENT, Role.ADMIN);
        EnumSet<Role> client = EnumSet.of(Role.CLIENT);
        EnumSet<Role> unreg = EnumSet.of(Role.UNREGISTERED_USER);
        EnumSet<Role> admin = EnumSet.of(Role.ADMIN);
        actions.put("GET/subs", unreg);
        actions.put("GET/pizza", unreg);
        actions.put("GET/beverage", unreg);
        actions.put("GET/subs_loged", authorized);
        actions.put("GET/pizza_loged", authorized);
        actions.put("GET/beverage_loged", authorized);
        actions.put("GET/error", all);
        actions.put("GET/locale", all);
        actions.put("GET/registered", authorized);
        actions.put("GET/logout", authorized);
        actions.put("GET/cabinet", authorized);
        actions.put("GET/add_to_orderlist", authorized);
        actions.put("GET/del_from_orderlist", authorized);
        actions.put("GET/products", admin);
        actions.put("GET/orders", admin);
        actions.put("GET/order_details", admin);
        actions.put("GET/sizes", admin);
        actions.put("GET/types", admin);

        actions.put("POST/register", unreg);
        actions.put("POST/login", unreg);
        actions.put("POST/chekout_order", authorized);
        actions.put("POST/info_update", client);
        actions.put("POST/pass_update", client);
        actions.put("POST/create_product", admin);
        actions.put("POST/create_user", admin);
    }

    /**
     * Checks current request URL. If User has rights to access this URL filter
     * pass request to next filter, otherwise User will be forwarded to the
     * 'Error' page.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        EnumSet<Role> allowedRoles = actions.get(httpRequest.getMethod()
                + httpRequest.getPathInfo());
        System.out.println(httpRequest.getMethod() + httpRequest.getPathInfo());
        User currentUser = (User) session.getAttribute("user");
        Role currentUserRole;
        if (currentUser == null) {
            currentUserRole = Role.UNREGISTERED_USER;
        } else {
            currentUserRole = currentUser.getRole();
        }
        if (allowedRoles == null) {
            chain.doFilter(request, response);
            return;
        }
        if (!allowedRoles.contains(currentUserRole)) {
            session.setAttribute("error", "error.accessDenied");
            request.getRequestDispatcher("error").forward(request, response);
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
