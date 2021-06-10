function usr(){
    var user = document.getElementById("user");
    var label = document.getElementById("lbUsr");
    if(user.value == ""){
        label.innerHTML = "Hãy nhập tài khoản";
    }
    else if(user.value.length > 0){
        label.innerHTML = "";
    }
}
function passw(){
    var pass = document.getElementById("psw");
    var label = document.getElementById("lbPsw");
    if(pass.value == "")   
        label.innerHTML = "Hãy nhập mật khẩu";
    else 
        if(pass.value.length > 0) label.innerHTML = "";
}
