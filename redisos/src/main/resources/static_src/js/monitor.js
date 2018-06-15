
define(['jquery'],function($) {
    function Page(){
        var that=this;
        this.init=function(){
            that.clusterNodes();
            that.bindEvent();
        };

        this.clusterNodes = function () {
            $.ajax({
                url: "monitor/clusterNodes",
                type: "GET",
                dataType: "text",
                success: function (rData) {
                    $("#result").text(rData);
                },
                error: function () {
                    $("#result").text("获取集群节点失败");
                }
            });
        };

        this.bindEvent = function () {

        };
    }
    return new Page();
});
