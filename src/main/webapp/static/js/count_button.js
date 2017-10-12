jQuery(document).ready(function ($) {
    $('.quont-minus').click(function () {
        var $input = $(this).parent().find('input');
        var val = +$input[0].defaultValue;
        var count = parseInt($input.val()) - 1;
        count = count < val ? val : count;
        $input.val(count);
        $input.change();
        return false;
    });

    $('.quont-plus').click(function () {
        var $input = $(this).parent().find('input');
        var val = +$input[0].defaultValue;
        $input.val(parseInt($input.val()) + 1);
        $input.change();
                return false;
    });
});


function checkCount() {
    if ($('.form-control').val() != '0') {
        $('.add').removeAttr('disabled');
    }
    else
        $('.add').attr('disabled', 'disable');
}
