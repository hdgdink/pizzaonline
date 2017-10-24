package kz.javalab.va.action;

public class ActionResult {
    public enum METHOD {
        FORWARD, REDIRECT
    }

    private METHOD method;
    private String view;

    public ActionResult(METHOD method, String view) {
        super();
        this.method = method;
        this.view = view;
    }

    public METHOD getMethod() {
        return method;
    }

    public String getView() {
        return view;
    }

}