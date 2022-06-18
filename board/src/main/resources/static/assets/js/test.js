/*
test module
 */

console.log("test module...");

let testService = (function() {

    function test1(callback, error) {
        $.ajax({
            url: "/reply/test",
            type: "get",
            success: function (result) {
                if (callback) {
                    callback(result);
                }
            }, error: function (xhr, status, er) {
                if (error) {
                    error(er);
                }
            }
        })
    }

    function test2(name, callback, error){
        console.log(name);

        $.ajax({
            url: "/reply/test2/" + name,
            type: "get",
            success: function(result){
                if (callback){
                    callback(result);
                }
            }, error: function(xhr, status, er){
                if (error){
                    error(er);
                }
            }
        })
    }

    function test3(callback, error) {
        $.ajax({
            url: "/reply/test3",
            type: "get",
            dataType: "json",
            success: function(result){
                if (callback) {
                    callback(result);
                }
                }, error: function(xhr, status, er) {
                if (error) {
                    error(er);
                }
            }
        })
    }

    function test4(fruit, callback, error){
        $.ajax({
            url: "/reply/test4",
            type: "post",
            data: JSON.stringify(fruit), // 보내는 값
            contentType: "application/json", // 보내는 데이터 타입
            dataType: "json", // 받는 데이터 타입, 받는 애
            success: function(result){
                if (callback) {
                    callback(result);
                }
            }, error: function(xhr, status, er) {
                if (error) {
                    error(er);
                }
            }
        })
    }

    function test5(list, callback){
        $.ajax({
            url: "/reply/test5/" + list.price +"/" + list.name + "/" + list.store,
            type: "get",
            dataType: "json",
            success: function(result) {
                if (callback) {
                    callback(result);
                }
            }
        })
    }

    return {test1:test1, test2:test2, test3:test3, test4: test4, test5: test5}
})();
