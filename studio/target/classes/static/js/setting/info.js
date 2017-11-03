$(function () {
    $(".navbar-nav li.active").removeClass("active");
    $(".navbar-nav li a[href$='setting/info']").parent().addClass("active");
    $("#content").find(".container .list-group a[href$='info']").addClass("active");


    //表单校验
    $("form").validate({
        rules: {
            email: {
                required: true,
                email:true
            },nickname:{
                required: true,
            }
        },
        messages: {
            email: {
                required: "该字段不能为空",
                email:"请输入正确格式的电子邮箱"
            },nickname:{
                required: "该字段不能为空",
            }
        }
    })
    $("#loading").showLoading();
    $("#btnSave").click(function () {
        if( $("form").valid()) {
            $.post("/setting/updateInfo", {
                userId: id,
                email: $("#email").val(),
                nickname: $("#nickname").val(),
                mobile: $("#mobile").val()
            }, function (res) {
                if (res.success) {
                    Messenger().post({message: "已保存", showCloseButton: true});
                }
            }, "json");
        }
    })
})