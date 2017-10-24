<%@tag description="group of input buttons" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p>
<form class="form-inline">
    <div class="form-group">
        <div class="input-group">
            <c:set var="count" value="0" scope="request"/>
            <span class="quont-minus btn btn-primary" id="minus">-</span>
            <input type="text" class="form-control" id="input" value="${count}" name="count"
                   readonly="readonly">
            <span class="quont-plus btn btn-primary" id="plus">+</span>
        </div>
    </div>
</form>
</p>

