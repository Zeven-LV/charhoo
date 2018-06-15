
define(['jquery'],function($) {
    function Page(){
        var that=this;
        this.init=function(){
           that.bindEvent();
        };

        this.bindEvent = function () {
            $(".menu-item").mouseenter(function () {
                $(this).addClass("on");
            }).mouseleave(function () {
                $(this).removeClass("on");
            }).click(function () {
                $(this).siblings().removeClass("select");
                $(this).addClass("select");
                var model = $(this).prop("id");
                var src = model+".html";
                $(".center-iframe").prop("src",src);
            });
        }
    }
    return new Page();
});
