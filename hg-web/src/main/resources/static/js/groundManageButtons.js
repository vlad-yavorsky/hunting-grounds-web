$(function () {
    $('.removeGround').click(function () {
        var id = $(this).data("ground-id");
        var name = $(this).data("ground-name");
        $('.modal-title').text('Remove confirmation');
        $('.modal-body').html('Are you sure want to remove ground <strong>' + name + '</strong>?');
        $('#removeFormGroundId').val(id);
    });
});
