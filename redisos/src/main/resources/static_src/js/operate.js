
define(['jquery'],function($) {
    function Page(){
        var that=this;
        this.init=function(){
           that.bindEvent();
        };

        this.bindEvent = function () {
            $("#put").click(function () {
                $("#result").text("操作结果");
                var redisType = $("#redis-type").val();
                that.put(redisType);
            });
            $("#delete").click(function () {
                $("#result").text("操作结果");
                var redisType = $("#redis-type").val();
                that.delete(redisType);
            });
            $("#get").click(function () {
                $("#result").text("操作结果");
                var redisType = $("#redis-type").val();
                that.get(redisType);
            });
        }

        this.put = function (redisType) {
            var data = {};
            data.key = $("#key").val();
            data.value = $("#value").val();
            $.ajax({
                url: "base/"+redisType,
                type: "PUT",
                data: data,
                dataType: "text",
                success: function (rData) {
                    $("#result").text(rData);
                },
                error: function () {
                    $("#result").text("操作失败");
                }
            });
        };

        this.delete = function (redisType) {
            var data = {};
            data.key = $("#key").val();
            data._method = "DELETE"
            $.ajax({
                url: "base/"+redisType,
                type: "POST",
                data: data,
                dataType: "text",
                success: function (rData) {
                    $("#result").text(rData);
                },
                error: function () {
                    $("#result").text("操作失败");
                }
            });
        };

        this.get = function (redisType) {
            var data = {};
            data.key = $("#key").val();
            $.ajax({
                url: "base/"+redisType,
                type: "GET",
                data: data,
                dataType: "text",
                success: function (rData) {
                    $("#result").text(rData);
                },
                error: function () {
                    $("#result").text("操作失败");
                }
            });
        };
    }
    return new Page();
});
