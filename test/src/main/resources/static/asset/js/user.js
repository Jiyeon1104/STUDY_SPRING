/*
* user module
 */

console.log("User Module...");
let userService= (function(){

    function add(user, callback, error){
        console.log("add user.........");
        $.ajax({
            url: "/test/add",
            type: "post",
            data: JSON.stringify(user), // JSON 형식으로 바꿔서 보내주기, url에 보낼 데이터
            contentType: "application/json", // 전달 해야 할 데이터의 타입
            success: function(result){
                if(callback){
                    callback(result);
                }
            },
            error: function(xhr, status, er){
                if(error){
                    error(er);
                }
            }

        })
    }

    return {add: add}
})();