$(function () {
    $("#loading").showLoading();
    init();
    $("#showModelAdd").click(function () {
        var myModal = $("#addAnswer");
        myModal.find("textarea").val('');
        myModal.modal('show');
        var btn = $("#btnSave");
        btn.unbind();
        btn.click(addAnswer);
        fontChange();
    });

    //监听input改变
    $("#name").on("input ", fontChange);

    //表单校验
    $("form").validate({
        rules: {
            name: {
                required: true,
                maxlength: 100
            }
        },
        messages: {
            name: {
                required: "题目不能为空",
                maxlength: "长度不能大于100"
            }
        }
    })
});

var fontChange = function () {
    var length = $("#name").val().length;
    var fontSize = $("#fontSize");

    fontSize.text(length);
    if (length > 100) {
        fontSize.parent().removeClass("lengthCommon");
        fontSize.parent().addClass("lengthOut");
    } else {
        fontSize.parent().removeClass("lengthOut");
        fontSize.parent().addClass("lengthCommon");
    }
};

var init = function () {
    var ajaxUrl = "/exam/getAnswer";
    var parameter = {answerId: id};
    $.get(ajaxUrl, parameter,
        function (res) {
            loadData(res.content, $("#showList"));
            $("#bp-element").setPage(parameter, ajaxUrl, res.content, loadData)
        },
        "json");
};

var loadData = function (data, dom) {
    // 每次加载 清空dom 数据
    dom.html("");
    if (!data) {
        dom.append("<tr><td colspan='5'>没有更多数据了..</td></tr>");
        return false;
    } else {
        if (data.length > 0) {
            $.each(data, function (index) {
                var edit = $('<a class=\'btn btn-info btn-xs\' role=\'button\'>' +
                    '<span class=\'glyphicon glyphicon-pencil\' aria-hidden=\'true\'>' +
                    '</span>&nbsp;编&nbsp;辑</a>').click(this, openEditModel);
                var del1 = $("<a class=\"btn btn-danger btn-xs\"  role=\"button\"><" +
                    "span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\">" +
                    "</span>&nbsp;删&nbsp;除</a>").click(this.questionId, del);

                var operation = $("<div></div>").append(edit).append(" ").append(del1);
                var tr = new Row(index + 1, this.answerName, eval(this.correct)?"是":"否", getMyDate(this.updateTime), operation);
                dom.append(tr.getRow());
                console.log(eval(this.correct));
            });
        }else {
            dom.append("<tr><td colspan='5'>没有更多数据了..</td></tr>");
            return false;
        }
    }
};

function openEditModel(data) {
    var answer = data.data;
    var myModal = $("#addAnswer");
    myModal.find("textarea").val(answer.answerName);
    console.log(answer.correct);
    myModal.find("select").val(answer.correct+"");
    fontChange();
    myModal.modal('show');
    var btn = $("#btnSave");
    btn.unbind();
    btn.click(answer.answerId, editQuestion);
}
function addAnswer() {
    if ($("form").valid()) {
        var myModal = $("#addAnswer");
        var param = {
                questionId: id,
                answerName: myModal.find("textarea").val(),
                correct: myModal.find("select").val()
            };
        $.post("/exam/addAnswer", param, function () {
            Messenger().post({message: "已保存", showCloseButton: true});
            init();
        }, "json");
    }

}
function editQuestion(data) {
    if ($("form").valid()) {
        var myModal = $("#addAnswer");
        var answerId = data.data;
        var param = {
            questionId: id,
            answerName: myModal.find("textarea").val(),
            correct: myModal.find("select").val(),
            answerId:answerId
        };
        $.post("/exam/editAnswer",param, function () {
            Messenger().post({message: "已保存", showCloseButton: true});
            init();
        }, "json");
    }
}
function del(data) {
    var answerId = data.data;
    if (confirm("确实要删除吗？")) {
        $.post("/exam/delAnswer", {
                answerId: answerId
            },
            function (res) {
                if (res.success) {
                    Messenger().post({message: "已删除", showCloseButton: true});
                    init();
                } else {
                    Messenger().post({message: "正在使用中无法删除", showCloseButton: true, type: "error"});
                }
            },
            "json");
    }

}
