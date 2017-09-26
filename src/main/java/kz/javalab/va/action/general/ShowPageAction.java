package kz.javalab.va.action.general;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.entity.user.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowPageAction implements Action {
    private ActionResult result;
    User user;
    String view;

    public ShowPageAction(String view) {
         if (user!=null)
           view = view + "_loged";
         else view = view + "_unreg";
        result = new ActionResult(ActionResult.METHOD.FORWARD, view);
    //    this.view = view;
    }


    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
       // String username = String.valueOf(session.getAttribute("username"));
      //  if (username != "")
     //       view = view + "_loged";
      //  else view = view + "_unreg";
      //  result = new ActionResult(ActionResult.METHOD.FORWARD, view);
        return result;
    }
}
