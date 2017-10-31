<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ attribute name="info" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${not empty info}">
    <div class="modal fade" id="myModal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title"><fmt:message key="default.message"/></h4>
                </div>
                <div class="modal-body">
                    <p><fmt:message key="${info}"/></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message
                            key="default.ok"/></button>
                    <c:remove var="changePassSuccess" scope="session"/>
                    <c:remove var="succesfullyOrdered" scope="session"/>
                </div>
            </div>
        </div>
    </div>
</c:if>

<script>
    $('#myModal').modal();
</script>