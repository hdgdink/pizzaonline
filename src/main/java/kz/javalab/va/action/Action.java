package kz.javalab.va.action;

import javax.servlet.http.HttpServletRequest;

public interface Action {

    ActionResult execute(HttpServletRequest request) throws ActionException;
}
