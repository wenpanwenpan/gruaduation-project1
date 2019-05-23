//解析分页条,购物页面的分页条
function parsingPaginatioBar() {
    //得到当前页码并且转换为整型
    var currentNum = parseInt($("#currentPage").attr("name"));
    //得到总页数
    var allPages = parseInt($("#allPages").attr("name"));

    //将上一页的<li>标签加入到<ul>分页条中
    var prePage = $("<li></li>").addClass("page-item").append($("<a></a>").
    attr("href","/shopping/goshoppingbypage?pageNo=" +  (currentNum - 1) ).addClass("page-link").append("上一页"));
    //将下一页的<li>标签加入到<ul>分页条中
    var nextPage = $("<li></li>").addClass("page-item").append($("<a></a>").
    attr("href","/shopping/goshoppingbypage?pageNo=" + (currentNum + 1) ).addClass("page-link").append("下一页"));

    //禁用上一页或下一页
    if(currentNum == 1){
        prePage.addClass("disabled");
    }
    if(currentNum == allPages){
        nextPage.addClass("disabled");
    }

    //将上一页加入到分页条中
    $(".pagination").append(prePage);

    //遍历取出每一个连续显示的页码，构建分页条
    $(".myPaging").each(function (index, item) {
        var numLi = $("<li></li>").append($("<a></a>").attr("href","/shopping/goshoppingbypage?pageNo=" + item.id).
        append(item.id).addClass("page-link")).addClass("page-item");

        //设置选中页数高亮显示
        if(item.id == currentNum.toString()){
            numLi.addClass("active");
        }
        //将生成的<li></li>标签添加到ul分页条中
        $(".pagination").append(numLi);
    });

    $(".pagination").append(nextPage);
}
//========================================================================================================
//解析分页条，管理所有商品界面的分页条
function parsingPaginatioBarForManageAllCommodity() {
    //得到当前页码并且转换为整型
    var currentNum = parseInt($("#currentPage").attr("name"));
    //得到总页数
    var allPages = parseInt($("#allPages").attr("name"));

    //将上一页的<li>标签加入到<ul>分页条中
    var prePage = $("<li></li>").addClass("page-item").append($("<a></a>").
    attr("href","/admin/manageuser/manageUserCommoditiesPre?pageNo=" +  (currentNum - 1) ).addClass("page-link").append("上一页"));
    //将下一页的<li>标签加入到<ul>分页条中
    var nextPage = $("<li></li>").addClass("page-item").append($("<a></a>").
    attr("href","/admin/manageuser/manageUserCommoditiesPre?pageNo=" + (currentNum + 1) ).addClass("page-link").append("下一页"));

    //禁用上一页或下一页
    if(currentNum == 1){
        prePage.addClass("disabled");
    }
    if(currentNum == allPages){
        nextPage.addClass("disabled");
    }

    //将上一页加入到分页条中
    $(".pagination").append(prePage);

    //遍历取出每一个连续显示的页码，构建分页条
    $(".myPaging").each(function (index, item) {
        var numLi = $("<li></li>").append($("<a></a>").attr("href","/admin/manageuser/manageUserCommoditiesPre?pageNo=" + item.id).
        append(item.id).addClass("page-link")).addClass("page-item");

        //设置选中页数高亮显示
        if(item.id == currentNum.toString()){
            numLi.addClass("active");
        }
        //将生成的<li></li>标签添加到ul分页条中
        $(".pagination").append(numLi);
    });

    $(".pagination").append(nextPage);
}

//========================================================================================================
//解析分页条，管理所有用户界面的分页条
function parsingPaginatioBarForManageAllUser() {
    //得到当前页码并且转换为整型
    var currentNum = parseInt($("#currentPage").attr("name"));
    //得到总页数
    var allPages = parseInt($("#allPages").attr("name"));

    //将上一页的<li>标签加入到<ul>分页条中
    var prePage = $("<li></li>").addClass("page-item").append($("<a></a>").
    attr("href","/admin/manageUser/viewAllUser?pageNo=" +  (currentNum - 1) ).addClass("page-link").append("上一页"));
    //将下一页的<li>标签加入到<ul>分页条中
    var nextPage = $("<li></li>").addClass("page-item").append($("<a></a>").
    attr("href","/admin/manageUser/viewAllUser?pageNo=" + (currentNum + 1) ).addClass("page-link").append("下一页"));

    //禁用上一页或下一页
    if(currentNum == 1){
        prePage.addClass("disabled");
    }
    if(currentNum == allPages){
        nextPage.addClass("disabled");
    }

    //将上一页加入到分页条中
    $(".pagination").append(prePage);

    //遍历取出每一个连续显示的页码，构建分页条
    $(".myPaging").each(function (index, item) {
        var numLi = $("<li></li>").append($("<a></a>").attr("href","/admin/manageUser/viewAllUser?pageNo=" + item.id).
        append(item.id).addClass("page-link")).addClass("page-item");

        //设置选中页数高亮显示
        if(item.id == currentNum.toString()){
            numLi.addClass("active");
        }
        //将生成的<li></li>标签添加到ul分页条中
        $(".pagination").append(numLi);
    });

    $(".pagination").append(nextPage);
}

//========================================================================================================
//解析分页条，查看留言的分页条
function parsingPaginatioBarForViewContent() {
    //得到当前页码并且转换为整型
    var currentNum = parseInt($("#currentPage").attr("name"));
    //得到总页数
    var allPages = parseInt($("#allPages").attr("name"));

    //将上一页的<li>标签加入到<ul>分页条中
    var prePage = $("<li></li>").addClass("page-item").append($("<a></a>").
    attr("href","/user/viewContent?pageNo=" +  (currentNum - 1) ).addClass("page-link").append("上一页"));
    //将下一页的<li>标签加入到<ul>分页条中
    var nextPage = $("<li></li>").addClass("page-item").append($("<a></a>").
    attr("href","/user/viewContent?pageNo=" + (currentNum + 1) ).addClass("page-link").append("下一页"));

    //禁用上一页或下一页
    if(currentNum == 1){
        prePage.addClass("disabled");
    }
    if(currentNum == allPages){
        nextPage.addClass("disabled");
    }

    //将上一页加入到分页条中
    $(".pagination").append(prePage);

    //遍历取出每一个连续显示的页码，构建分页条
    $(".myPaging").each(function (index, item) {
        var numLi = $("<li></li>").append($("<a></a>").attr("href","/user/viewContent?pageNo=" + item.id).
        append(item.id).addClass("page-link")).addClass("page-item");

        //设置选中页数高亮显示
        if(item.id == currentNum.toString()){
            numLi.addClass("active");
        }
        //将生成的<li></li>标签添加到ul分页条中
        $(".pagination").append(numLi);
    });

    $(".pagination").append(nextPage);
}

//========================================================================================================

//点击跳转到我的订单，因为两个地方都要用到该方法，所以将它抽取出来
function toMyOrder() {
    var checkboxpid = "||";
    var commodityCount = "||";
    //将所有的pid进行拼串
    $.each($(".check_item:checked"),function (i,item) {
        if(checkboxpid == "||"){			//保证拼接后的pid串是  1--3--5这种形式
            checkboxpid = item.id;
        }else{
            checkboxpid = checkboxpid + "--" + item.id;
        }
    });
    //将所有的购买次数进行拼串
    $.each($(".check_item:checked"),function (i,item) {
        if(commodityCount == "||"){			//保证拼接后的pid串是  1--3--5这种形式
            commodityCount = item.name;
        }else{
            commodityCount = commodityCount + "__" + item.name;
        }
    });
    window.location.href = "/usr/ajax/toMyOrder/" + checkboxpid + "/" + commodityCount;
}

//========================================================================================================
//设置全选状态
function checkAll() {
    //设置全选
    $("#check_all").click(function () {
        $(".check_item").prop("checked",$(this).prop("checked"));
    });

    //为check_item绑定点击事件，所以只能用on()方法绑定
    $(document).on("click",".check_item",function(){
        //alert($(".check_item:checked").length);
        var flag = $(".check_item:checked").length == $(".check_item").length;
        $("#check_all").prop("checked",flag)
    });
}

//========================================================================================================
//用户搜索商品
function searchCommodity() {
    //搜索商品
    $('#seachCommodity').blur(function () {

        var key = $(this).val();
        $.ajax({
            url : "/usr/ajax/seachCommodity", //路径
            type : "POST",            //提交方式
            data : {
                "key" : key
            },                        //数据，这里使用的是Json格式进行传输
            success : function(result) {//返回数据根据结果进行相应的处理
                if ( result == "true" ) {
                    if(key != null && "" != key){
                        window.location.href = "/usr/ajax/seachCommodity/" +key;
                    }
                } else {
                    alert("没有该商品");
                }
            }
        });
    });
}

//========================================================================================================
//管理员搜索商品
function adminSearchCommodity() {
    //搜索商品
    $('#seachCommodity').blur(function () {

        var key = $(this).val();
        $.ajax({
            url : "/amin/ajax/seachCommodity", //路径
            type : "POST",            //提交方式
            data : {
                "key" : key
            },                        //数据，这里使用的是Json格式进行传输
            success : function(result) {//返回数据根据结果进行相应的处理
                if ( result == "true" ) {
                    if(key != null && "" != key){
                        window.location.href = "/admin/ajax/seachCommodity/" +key;
                    }
                } else {
                    alert("没有该商品");
                }
            }
        });
    });
};

//========================================================================================================
//管理员搜索用户信息
function adminSearchUser() {

    $('#seachCommodity').blur(function () {

        var keyword = $(this).val();
        $.ajax({
            url : "/admin/ajax/seachUser", //路径
            type : "POST",            //提交方式
            data : {
                "keyword" : keyword
            },                        //数据，这里使用的是Json格式进行传输
            success : function(result) {//返回数据根据结果进行相应的处理
                if ( result == "true" ) {
                    if(keyword != null && "" != keyword){
                        window.location.href = "/admin/ajax/searchUser/" + keyword;
                    }
                } else {
                    alert("没有该用户");
                }
            }
        });

    });
}











