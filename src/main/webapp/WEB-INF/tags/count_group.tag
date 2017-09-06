<%@tag description="group of input buttons" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


</p>
<form class="form-inline">
    <div class="form-group">
        <div class="input-group">
            <span class="btn btn-primary" id="minus">-</span>
            <input type="number" class="form-control" id="input" value="0">
            <span class="btn btn-primary" id="plus">+</span>
        </div>
    </div>
</form>
<p>

    <script>
        $(document).ready(function () {
            $('#minus').click(function () {
                var $input = $(this).parent().find('input');
                var count = parseInt($input.val()) - 1;
                count = count < 1 ? 0 : count;
                $input.val(count);
                $input.change();
                return false;
            });
            $('#plus').click(function () {
                var $input = $(this).parent().find('input');
                $input.val(parseInt($input.val()) + 1);
                $input.change();
                return false;
            });
        });
    </script>