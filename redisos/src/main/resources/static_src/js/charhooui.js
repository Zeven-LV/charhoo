/**
 *
 */
(function ($) {
    window.charhoo = $.charhooui = {
        version: 'V0.0.1',
        run: function (plugin, args, ext) {

            console.log(plugin);
            if (args.length >= 1 && typeof args[0] == 'string') return;
            //只要第一个参数不是string类型,都执行组件的实例化工作
            var options = args.length > 0 ? args[0] : null;
            var p = $.extend({css:{"color":"red"}},options);
            console.log(p);
            var out_div = document.createElement("div");
            out_div.width=p.width+"px";
            out_div.height=p.height+"px";
            out_div.style.color = "blue";
            out_div.style.border = "1px solid #DDD";
            var main_table = document.createElement("table");
            p.title?main_table.createCaption().innerHTML=p.title:null;
            var thead=main_table.createTHead();
            var tr=thead.insertRow(0);
            for(var colNo in p.columns){
                var col = p.columns[colNo];
                tr.insertCell().innerHTML=col.display;
            }
            $.ajax({
                url: p.url,
                data:{},
                type: "GET",
                dataType: "json",
                success: function (rData) {
                    console.log(rData);
                    var tbody=main_table.createTBody();
                    for(var rowNo in rData){
                        var rowData = rData[rowNo];
                        //connect = rowData.connect
                        var tr=tbody.insertRow(rowNo);
                        for(var colNo in p.columns){
                            var col = p.columns[colNo];
                            var innerhtml = "";
                            if(col.render){
                                innerhtml = col.render.call();
                            }else{
                                innerhtml = rowData[col.name];
                            }
                            tr.insertCell().innerHTML=innerhtml;
                        }
                    }
                },
                error: function () {
                    alert("error");
                }

            });
            out_div.append(main_table);
            return $(this).css({"color":p.css.color,}).html(out_div);
        }
    }
})(jQuery);


(function ($){
    $.fn.charhooGrid= function (options)
    {
        console.log("charhooGrid");
        return $.charhooui.run.call(this, "charhooGrid", arguments);
    };

})(jQuery);