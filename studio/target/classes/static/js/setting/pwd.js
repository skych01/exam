$(function () {
    $(".navbar-nav li.active").removeClass("active");
    $(".navbar-nav li a[href$='setting/info']").parent().addClass("active");
    $("#content").find(".container .list-group a[href$='pwd']").addClass("active");

    //数据校验
    jQuery.validator.addMethod("affirm", function (value) {
        var now = $("#now").val();
        return value===now;
    });
    //表单校验
    $("form").validate({
        rules: {
            old: {
                required: true
            }, nickname: {
                required: true
            }, now_affirm: {
                required: true
            }
        },
        messages: {
            old: {
                required: "不能为空！"
            }, nickname: {
                required: "不能为空！"
            }, now_affirm: {
                required: "不能为空！"
            }
        }
    });

    $("#loading").showLoading();

    $("#btnSave").click(function () {
        if( $("form").valid()) {
            $.post("/setting/updatePassword", {
                old:$("#old").val(),
                now:$("#now").val()
            }, function (res) {
                if (res.success) {
                    Messenger().post({message: "已保存", showCloseButton: true});
                    $("form").find("input").val("");
                }else{
                    Messenger().post({message: res.message, showCloseButton: true,type:"error"});
                }

            }, "json");
        }
    })
})