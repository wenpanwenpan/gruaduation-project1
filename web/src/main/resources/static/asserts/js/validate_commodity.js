function validatePrice(price) {
    var ino = price.nextElementSibling;
    if (!(/^\d+(\.\d+)?$/.test(price.value))) {
        price.style.borderColor = "red";
        //ino.style.backgroundColor = "red";
        //ino.innerHTML = "×";
        //ino.innerHTML="格式错误";
        price.focus();
        return false;
    } else {
        price.style.borderColor = "green";
        //ino.style.backgroundColor = "green";
        //ino.innerHTML = "√";
        //ino.innerHTML="格式正确";
        return true;
    }
}

function validateAmount(amount){
    if(!(/^\d+$/.test(amount.value))){
        amount.style.borderColor = "red";
        amount.focus();
        return false;
    }else{
        amount.style.borderColor = "green";
        return true;
    }
}

function validateName(name){
    if(name.value == ""){
        name.style.borderColor = "red";
        name.focus();
        return false;
    }else{
        name.style.borderColor = "green";
        return true;
    }
}
