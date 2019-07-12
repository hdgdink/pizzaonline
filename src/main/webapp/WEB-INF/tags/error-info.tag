<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ attribute name="error" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${not empty error}">
  <div class="modal fade" id="myModal">
    <div class="modal-dialog" role="document">
      <div class="modal-content">

        <div class="modal-header">
          <h4 class="modal-title">
            <fmt:message key="error.error"/>
          </h4>
        </div>

        <div class="modal-body">
          <p>
            <fmt:message key="${error}"/>
          </p>
        </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">
            <fmt:message key="default.ok"/> <c:remove var="error" />
          </button>
        </div>

      </div>
    </div>
  </div>
</c:if>

<script>
    $('#myModal').modal();
</script>