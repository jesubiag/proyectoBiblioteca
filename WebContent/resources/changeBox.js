$(function() {
    $('.select1').change(function() {
        $('#changeBox').toggle($(this).val() > 1);
    });
});