package kz.javalab.va.action;

public class ActionResult {
    public enum METHOD {
        FORWARD, REDIRECT
    }

    private METHOD method;
    private String view;
    private boolean redirection;

    public ActionResult(METHOD method, String view) {
        super();
        this.method = method;
        this.view = view;
    }

    public ActionResult(String view) {
        this.view = view;
    }

    public METHOD getMethod() {
        return method;
    }


    public void setMethod(METHOD method) {
        this.method = method;
    }

    public String getView() {
        return view;
    }

    /*my*/
    public void setView(String view) {
        this.view = view;
    }

    public ActionResult(String view, boolean redirection) {
        this.view = view;
        this.redirection = redirection;
    }
    public ActionResult(){
        super();
    }

}