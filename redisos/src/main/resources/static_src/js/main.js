
if(!window.jsVer){
    window.jsVer="1.0";
}
window.proj={};
proj.prefix="/redisos";
require.config({
    paths:{
        'jquery':"jquery"
    },
    shim:{

    },
    urlArgs:window.urlArgsFun
});
require([document.body.id],function(page){
    if(page && page.init){
        window.page=page;
        page.init();
    }
});
