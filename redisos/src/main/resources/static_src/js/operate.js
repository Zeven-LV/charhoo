
define(['jquery'],function($) {
    function Page(){
        var that=this;
        this.init=function(){
           that.bindEvent();
        };

        this.bindEvent = function () {
            $("#submit").click(function () {
                $("#result").text("操作结果");
                var dateType = $("#data-type").val();
                var data = {};
                data.action = $("#action").val();
                data.key = $("#key").val();
                data.value = $("#value").val();
                $.ajax({
                    url: "base/"+dateType,
                    type: "GET",
                    data: data,
                    dataType: "text",
                    success: function (rData) {
                        console.log(rData);
                        $("#result").text(rData);
                    },
                    error: function () {
                        $("#result").text("操作失败");
                    }
                });
            });
        }
    }
    return new Page();
});
