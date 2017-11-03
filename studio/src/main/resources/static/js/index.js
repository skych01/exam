var publicData = {
    pageSize: 10
};
$(function () {
    $("#loading").showLoading();
    init();
    $("#showModelAdd").click(function () {
        fontChange();
        var myModal = $("#addQuestions");
        myModal.find("textarea").val('');
        myModal.modal('show');
        var btn = $("#btnSave");
        btn.unbind();
        btn.click(addQuestion);
    });
    $("#find_name").keydown(function (event) {
        if (event.keyCode === 13) {
            init();
            return false;
        }
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
}

var init = function () {
    var ajaxUrl = "/exam/getQuestion";
    var parameter = {pageNo: 1, pageSize: publicData.pageSize};
    var findName = $("#find_name").val();
    if (findName.length !== 0) {
        parameter.name = findName
    }
    $.get(ajaxUrl, parameter,
        function (res) {
            loadData(res.content.content, $("#showList"));
            $("#bp-element").setPage(parameter, ajaxUrl, res.content, loadData)
        },
        "json");
};

var loadData = function (data, dom) {
    // 每次加载 清空dom 数据
    dom.html("");
    if (data === null) {
        dom.append("<tr><td colspan='6'>没有更多数据了..</td></tr>");
    }
    if (data.length > 0) {
        $.each(data, function (index) {
            var details = $("<a class=\"btn btn-info btn-xs\"  " +
                "href='exam/answer?questionId=" + this.questionId + "'" + " role=\"button\">" +
                "<span class=\"glyphicon glyphicon-align-justify\" aria-hidden=\"true\"></span>&nbsp;答案管理</a>");
            var edit = $('<a class=\'btn btn-info btn-xs\' role=\'button\'>' +
                '<span class=\'glyphicon glyphicon-pencil\' aria-hidden=\'true\'>' +
                '</span>&nbsp;编&nbsp;辑</a>').click(this, openEditModel);
            var del1 = $("<a class=\"btn btn-danger btn-xs\"  role=\"button\"><" +
                "span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\">" +
                "</span>&nbsp;删&nbsp;除</a>").click(this.questionId, del);

            var operation = $("<div></div>").append(details).append(" ").append(edit).append(" ").append(del1);
            var tr = new Row(index + 1, this.questionName, this.answerName, getMyDate(this.updateTime), operation);
            dom.append(tr.getRow());
        });
    }
};

function openEditModel(data) {
    var question = data.data;
    var myModal = $("#addQuestions");
    myModal.find("textarea").val(question.questionName);
    fontChange();
    myModal.modal('show');
    var btn = $("#btnSave");
    btn.unbind();
    btn.click(question.questionId, editQuestion);
}
function addQuestion() {
    if ($("form").valid()) {
        var myModal = $("#addQuestions");
        var name = myModal.find("textarea").val();
        $.post("/exam/addAnswer", {name: name}, function () {
            Messenger().post({message: "已保存", showCloseButton: true});
            init();
        }, "json");
    }

}
function editQuestion(data) {
    if ($("form").valid()) {
        var questionId = data.data;
        var myModal = $("#addQuestions");
        var name = myModal.find("textarea").val();
        $.post("/exam/editQuestion", {questionName: name, questionId: questionId}, function () {
            Messenger().post({message: "已保存", showCloseButton: true});
            init();
        }, "json");
    }
}
function del(data) {
    var questionId = data.data;
    if (confirm("确实要删除吗？")) {
        $.post("/exam/delQuestion", {
                questionId: questionId
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
