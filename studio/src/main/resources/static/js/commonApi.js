// 对日期 填充0操作，如4.2 显示为04.02
function getzf(num) {
    if (parseInt(num) < 10) {
        num = '0' + num;
    }
    return num;
}

// 拼接日期 以 年月日
function getMyDate(str) {
    var oDate = new Date(str),
        oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth() + 1,
        oDay = oDate.getDate(),
        oHour = oDate.getHours(),
        oMin = oDate.getMinutes(),
        oSen = oDate.getSeconds(),
        oTime = oYear + '-' + getzf(oMonth) + '-' + getzf(oDay)
    return oTime;
};
function setPage(parameter, url, res,fn) {

    var options = {
        bootstrapMajorVersion: 3, //对应的bootstrap版本
        currentPage: res.number + 1, //
        size: "normal",
        numberOfPages: 1,
        totalPages: res.totalPages,
        itemTexts: function (type, page, current) {//设置显示的样式，默认是箭头
            switch (type) {
                case "first":
                    return "首页";
                case "prev":
                    return "上一页";
                case "next":
                    return "下一页";
                case "last":
                    return "末页";
                case "page":
                    return page;
            }
        },
        //点击事件
        onPageClicked: function (event, originalEvent, type, page) {
            parameter.pageNo = page;
            $.get(url, parameter, function (res) {
                    fn(res.content.content, $("#showList"));
                },
                "json");
        }
    };
    if (res.totalPages > 1) {
        this.bootstrapPaginator(options);
    } else {
        this.html("");
    }

}

/**
 * 以tr的形式 渲染
 * @returns {Row} 行
 * @constructor Row
 */
function Row() {
    var tr = $("<tr></tr>");
    var count = arguments.length;
    var tds = [];
    for (var i = 0; i < count; i++) {
        tds[i] = $("<td  style='line-height:160% ;overflow: hidden;word-break:break-all;'></td>").append(arguments[i]);
        tr.append(tds[i]);
    }
    /**
     * 获取当前行
     * @returns tr
     */
    this.getRow = function () {
        return tr;
    }
    /**
     * 获取 长度
     * @returns length
     */
    this.getLength = function () {
        return tds.length;
    }
    /**
     * 获取指定索引的列
     * @param index
     * @returns td
     */
    this.getColumn = function (index) {
        return tds[index - 1];
    }
    /**
     * 追加数据至 末尾
     */
    this.appendColumn = function () {
        var localCount = arguments.length;
        for (var i = 0; i < localCount; i++) {
            tds[count + i] = $("<td  style='line-height:160% ;overflow: hidden;word-break:break-all;'></td>").append(arguments[i]);
            tr.append(tds[count + i]);
        }

    }
    /**
     * 添加数据至指定索引
     * @param index
     * @param column
     */
    this.addColumn = function (index, column) {
        if (arguments.length < 2) {
            console.log("参数数量有误！！！！");
            return;
        }
        var length = tds.length;
        for (var i = length - 1; index <= i + 1; i--) {
            tds[i + 1] = tds[i]
        }
        tds[index - 1] = $("<td  style='line-height:160% ;overflow: hidden;word-break:break-all;'></td>").append(column);
        for (var i = 0; i < tds.length; i++) {
            tr.append(tds[i]);
        }
    }
    return this;
}

/**
 *
 * @param dropInput
 * @constructor EditableSelect
 */
function EditableSelect(dropInput) {

    var groupList = $('<ul class="list-group hide drop_list"> </ul>');
    dropInput.bind("blur", function () {
        setTimeout(function () {
            groupList.addClass("hide");
        }, 300);
    });
    dropInput.bind("focus", function () {
        var length = dropInput.val().length;
        if (0 !== length && groupList.html() != "") {
            groupList.removeClass("hide");
        }
    });
    /**
     * 传递 数据，需要的属性，展示下拉框
     * @param data 下拉框的数据 必填
     * 以下参数 针对json格式数据 对应json的属性
     * @param valueProperties   下拉框显示
     * @param titleProperties   下拉框title
     * @param inputProperties   input回显
     */
    this.setDate = function (data, valueProperties, titleProperties, inputProperties) {
        groupList.text("");
        groupList.addClass("hide");
        var pamLength = arguments.length;
        if (data.length > 0) {
            $.each(data, function (index, val) {
                if (pamLength == 1) {
                    var item = $('<li class="puCodeList form-control "' +
                        '>' + this + '</li>');
                    item.click(this, selectedItem);
                } else {
                    var item = $('<li class="puCodeList form-control "' +
                        ' 策略模式='+eval("this." + titleProperties)+'>' + eval("this." + valueProperties) + '</li>');
                    item.click(eval("this." + inputProperties), selectedItem);
                }
                item.hover(function () {
                    $(this).addClass("drop_item_Selected");
                }, function () {
                    $(this).removeClass("drop_item_Selected");
                });
                groupList.append(item[0])
            })
            $(groupList).removeClass("hide");
        }
    };
    //下拉框被选中
    var selectedItem = function (data) {
        var value = data.data;
        dropInput.val(value);
        $(groupList).addClass("hide");
        $(groupList).html("");
    };
    /**
     *清除下拉框数据
     */
    this.cleanData = function () {
        setTimeout(function () {
            groupList.text("");
            $(groupList).addClass("hide");
        }, 300);
    }
    dropInput.after(groupList);
}

function hoverTip(tipMsg) {
    console.log(tipMsg);
    $(this).hover(function (e) {
        var tip = $("<div id='hoverTip' style='position:absolute;background-color: white;border:1px solid #000000 '></div>");
        tip.append(tipMsg);
        $("body").append(tip);
        tip.css({
            "top": (e.pageY + 10) + "px",
            "left": (e.pageX + 15) + "px"
        }).show("fast");
    }, function () {
        $("#hoverTip").remove();
    }).mousemove(function (e) {
        $("#hoverTip").css({
            "top": (e.pageY + 10) + "px",
            "left": (e.pageX + 15) + "px"
        }).show("fast");
    })
}


function showLoading() {
    var loadingDom = $(this);
    loadingDom.ajaxStart(function () {
        init();
        function init() {
            var scrollTop = $(window).scrollTop();
            var scrollLeft = $(window).scrollLeft();
            loadingDom.css({
                "left": $(window).width() / 2 + scrollLeft,
                "top": $(window).height() / 2 + scrollTop
            });
            loadingDom.show();
        }
        $(window).scroll(
            function () {
                init(loadingDom);
            }
        )
    });
    $(this).ajaxStop(function () {
        $(this).hide();
        $(window).unbind('scroll');
    });
}

$.fn.setPage = setPage;
$.fn.hoverTip = hoverTip;
$.fn.showLoading = showLoading;