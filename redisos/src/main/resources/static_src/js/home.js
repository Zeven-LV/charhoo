
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
                var model = $(this).text();

                $(".right-content").text(model);
            });
        }
    }
    return new Page();
});
