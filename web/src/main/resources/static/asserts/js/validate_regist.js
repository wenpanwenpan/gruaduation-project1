//验证姓名：只能为中文
function validateName(name){
    var str = name.value   //判断是否为全中文
    var reg = /^[u4E00-u9FA5]+$/;
    if(name.value == ""){
        name.style.borderColor = "red";
        name.focus();
        return false;
    }else{
        if(reg.test(str)){
            name.style.borderColor = "red";
            name.focus();
            return false;
        }else{
            name.style.borderColor = "green";
        }
        return true;
    }
}

//验证登录账号
function validate_account(account) {

    var str = name.value;         //取得登录账号
    var reg = /^[\u4E00-\u9FA5]|[\uFE30-\uFFA0]/gi;
    if(!reg.exec(str)){             //不包含中文
        account.style.borderColor = "red";
        account.focus();
        return false;
    }else{
        account.style.borderColor = "green";
        return true;
    }
}

function validate_tel(tel) {
    var str = tel.value;
    var reg0 = /^13\d{5,9}$/;
    var reg1 = /^153\d{4,8}$/;
    var reg2 = /^159\d{4,8}$/;
    var reg3 = /^0\d{10,11}$/;

    var my = false;
    if (reg0.test(str))my=true;
    if (reg1.test(str))my=true;
    if (reg2.test(str))my=true;
    if (reg3.test(str))my=true;
    if(my){
        tel.style.borderColor="green";
        return ture;
    }else{
        tel.style.borderColor="red";
        tel.focus();
        return false;
    }
}

function validate_zipcode(zipcode) {

    var str = zipcode.value;
    if(!(/^\d{6}\b/.test(str))){
        zipcode.style.borderColor="red";
        zipcode.focus();
        return false;
    }else{
        zipcode.style.borderColor="green";
        return true;
    }
}

//验证评论不能为空
function validate_content(content) {

    var str = content.value;         //取得登录账号
    if(str == null || str == ""){             //不包含中文
        content.style.borderColor = "red";
        content.focus();
        return false;
    }else{
        content.style.borderColor = "green";
        return true;
    }
}