$(function () {
    $('.removeGround').click(function () {
        const id = $(this).data("ground-id");
        const name = $(this).data("ground-name");
        $('.modal-title').text('Remove confirmation');
        $('.modal-body').html('Are you sure want to remove ground <strong>' + name + '</strong>?');
        $('#removeFormGroundId').val(id);
    });
});
