package kz.javalab.va.action.admin;


import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionFactory;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.FoodDao;
import kz.javalab.va.dao.impl.TypeDao;
import kz.javalab.va.dao.impl.UserDao;
import kz.javalab.va.entity.Food;
import kz.javalab.va.entity.user.Role;
import kz.javalab.va.entity.user.User;
import kz.javalab.va.util.AttributeSetter;
import kz.javalab.va.util.validator.FieldsValidator;
import kz.javalab.va.util.validator.ValidationException;
import org.apache.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by HdgDink} on 11.10.2017.
 */
public class CreateEntityAdmin implements Action {
    private static final Logger LOGGER = Logger.getLogger(CreateEntityAdmin.class);
    private static final ActionResult USER_ADMIN_PAGE = new ActionResult(ActionResult.METHOD.REDIRECT, "cabinet");
    private static final ActionResult PRODUCTS_ADMIN_PAGE = new ActionResult(ActionResult.METHOD.REDIRECT, "products");
    private static final String ERROR_MESSAGE_ATTRIBUTE = "RegisterErrorMessageKey";
    private static final String USER_EXIST = "user_exist";
    private HttpSession session;
    private String entityName;
    private ActionResult result;
    private HttpServletRequest req;

    public CreateEntityAdmin(String entityName) {
        this.entityName = entityName;
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        req = request;
        session = request.getSession();
        if (entityName.equals("user")) createUser();
        if (entityName.equals("product")) createProduct();
        return result;
    }

    private void createProduct() throws ActionException {
        Integer type = Integer.parseInt(req.getParameter("type_id"));
        String nameRu = req.getParameter("nameRu");
        String nameEn = req.getParameter("nameEn");
        String discriptionRu = req.getParameter("composRu");
        String dicriptionEn = req.getParameter("composEn");
        Integer price = Integer.parseInt(req.getParameter("price"));
        String img = req.getParameter("imgPath");
        Boolean active = Boolean.parseBoolean(req.getParameter("active")) ;
        System.out.println("active Value For CREATE: " + active);

        Food food = new Food();
        food.setPrice(price);
        food.setImg(img);
        food.setDiscriptionEn(dicriptionEn);
        food.setDiscriptionRu(discriptionRu);
        food.setNameEn(nameEn);
        food.setNameRu(nameRu);
        food.setTypeId(type);
        food.setActive(active);

        try {
            FoodDao foodDao = new FoodDao();
            final Food finalFood = food;
            foodDao.create(finalFood);
            AttributeSetter setter = new AttributeSetter();
            setter.setAttributes(session);
        } catch (Exception e) {
            LOGGER.error("Error at create Food()");
            throw new ActionException("Error at createFood()", e);
        }
        result = PRODUCTS_ADMIN_PAGE;

    }


    private void createUser() throws ActionException {
        String userName = req.getParameter("user_name");
        String email = req.getParameter("email");
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        Integer balance = Integer.parseInt(req.getParameter("balance"));
        String password = req.getParameter("password");
        Role role = Role.valueOf(req.getParameter("role"));
        boolean userNameValid = false;

        try {
            userNameValid = FieldsValidator.userNameCheck(userName);
        } catch (ValidationException e) {
            e.printStackTrace();
        }

        if (!userNameValid) {
            session.setAttribute(ERROR_MESSAGE_ATTRIBUTE, USER_EXIST);
            LOGGER.error("Username already busy");
            result = USER_ADMIN_PAGE;
        } else {
            User user = new User();
            user.setFirstname(firstName);
            user.setLastname(lastName);
            user.setEmail(email);
            user.setUsername(userName);
            user.setPassword(password);
            user.setBalance(balance);
            user.setRole(role);
            try {
                UserDao userDao = new UserDao();
                final User finalUser = user;
                userDao.create(finalUser);
            } catch (Exception e) {
                LOGGER.error("Error at createUser()");
                throw new ActionException("Error at createUser()", e);
            }
            result = USER_ADMIN_PAGE;
        }
    }
}
