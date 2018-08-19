
define(['jquery','charhooui'],function($,charhooui) {
    function Page(){
        var that=this;
        this.init=function(){
           that.bindEvent();
           that.getCurrentConnect();
           that.initGrid();
        };

        this.bindEvent = function () {
            $("#do-connect").click(function () {
                $.ajax({
                    url: "",
                    type: "POST",
                    data: {},
                    dataType: "json",
                    success: function(rData){
                        alert("success");
                    },
                    error: function () {
                        alert("error");
                    }
                });
            });
        };

        this.initGrid = function () {
            var columns = [];
            columns.push({display:"连接", name:"connect", width:"80%"});
            columns.push({display:"操作", name:"action", width:"20%", render:function () {
                    return "<span style='color:red' >修改</span>";
                }});
            var option = {
                columns: columns,
                width:100,
                height:200,
                url:"connect/getHistory"
            };
            $("#history-connect-context").charhooGrid(option);
        };

        this.getCurrentConnect = function () {
            $.ajax({
                url: "connect/getCurrentConnection",
                type: "GET",
                data: {},
                dataType: "json",
                success: function(rData){
                    if(rData.status == 1){
                        $("#current-connect-span").text(rData.connect);
                    }
                },
                error: function () {
                    alert("error");
                }
            });
        }

    }
    return new Page();
});
