/**
 * index
 */
define(['jquery'],function($) {
    function Page(){
        var that=this;
        this.init=function(){
            that.bindEvent();
        };

        this.bindEvent = function () {
            $("#goin-system").click(function () {
                window.location = "home.html"
            });
        }
    }
    return new Page();
});