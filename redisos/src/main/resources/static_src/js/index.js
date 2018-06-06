/**
 * Created by jack on 2018/5/25.
 */
define(['jquery','tools','vue','maputil','base64'],function($,tools,Vue,maputil,base64) {
    function Page(){
        var that=this;
        var ev=tools.events();
        this.init=function(){
            var obj={
                userId:'wang',
                tokenId:'12345678',
                mobileNo:'18601946029'
            };
            try{
                var s=window.daka.getMobileApp();
                obj=JSON.parse(base64.utf8to16(base64.base64decode(s)));
            }catch(e){}
            tools.session.set("userid",obj.userId);
            tools.session.set("token",obj.tokenId);
            var data={
                loginName:obj.mobileNo
            };
            tools.ajax({
                url:proj.prefix+"/login/daka",
                data:data,
                success:function(rData){
                    if(rData.status==1){
                        $(".main-content:first").removeAttr("style");
                        that.afterLogin();
                    }else{
                        tools.showMsg(rData.errMsg);
                        location.href='about.html?v='+window.jsVer;
                    }
                },
                error:function(err){
                    console.log(err.status);
                }
            });
        };
        this.afterLogin=function(){
            this.initMap();
            this.initModel();
            this.bindEvent();
            this.getDataList();
            this.getTree();
            this.onHashChange();
        }
        this.getTree=function(){
            function setParent(parent,child){
                child.parent=parent;
                if(child.children && child.children.length>0){
                    child.children.forEach(function(item){
                        setParent(child,item);
                    });
                }
            }
            tools.ajax({
                url:proj.prefix+"/dept/tree",
                success:function(rData){
                    if(rData.status==1){
                        rData.data.checked=true;
                        rData.data.children.forEach(function(item){
                            setParent(rData.data,item);
                        });
                        that.filterApp.topNode=rData.data;
                        that.filterApp.item=rData.data;
                    }
                }
            });
        };
        this.getDataList=function(params){
            tools.ajax({
                url:proj.prefix+"/v/allVehicle",
                data:params,
                success:function(rData){
                    if(rData.status=="1"){
                        var list=rData.data.map(function(item){
                            var p={};
                            var lng=item.lng/600000;
                            var lat=item.lat/600000;
                            var arr=maputil.wgs84ToBd(lng,lat);
                            p.lng=arr[0];
                            p.lat=arr[1];
                            item.p=p;
                            if(item.status=="online"){
                                item.continuedTxt="已在线"+item.continued;
                                item.locationTxt="当前位置:"+item.location;
                            }else if(item.status=="offline"){
                                item.continuedTxt="已离线"+item.continued;
                                item.locationTxt="最后位置:"+item.location;
                            }else{
                                item.continuedTxt="已持续"+item.continued;
                                item.locationTxt="报警位置:"+item.location;
                            }
                            return item;
                        });
                        that.orgList=JSON.parse(JSON.stringify(list));
                        that.renderList(list);
                        that.renderMarker(list);
                        /*setTimeout(function(){
                            that.getDataList();
                        },30*60*1000);*/
                    }
                },
                error:function(){
                    /*setTimeout(function(){
                        that.getDataList();
                    },30*60*1000);*/
                }
            });
        };
        this.renderList=function(list){
            this.listApp.list=list;
        };
        this.renderMarker=function(list){
            this.clearOldMarkers();

            if(!that.allMarkers){
                that.allMarkers={};
            }
            var urls={online:"images/index/online-ico.png",offline:"images/index/offline-ico.png",except:"images/index/warn-ico.png"};
            var points=[];
            list.forEach(function(item){
                var url=urls[item.status];
                if(!url){
                    url="images/index/warn-ico.png";
                }
                var icon=new BMap.Icon(url,new BMap.Size(25,25));
                icon.setImageSize(new BMap.Size(25,25));
                icon.imageOffset=new BMap.Size(0,0);
                var opts={icon:icon};
                var p=item.p;
                var pt=new BMap.Point(p.lng,p.lat);
                points.push(pt);
                var marker=new BMap.Marker(pt,opts);
                that.map.addOverlay(marker);
                marker.data=item;
                that.allMarkers[item.vid]=marker;
                marker.addEventListener("click",function(){
                    that.markerOnClick(this);
                });
            });
            //设置最佳视野
            that.map.setViewport(points);
        };
        this.markerOnClick=function(marker){
            for(var p in that.allMarkers){
                var one=that.allMarkers[p];
                if(one.getLabel() && one.getLabel().getMap()){
                    that.map.removeOverlay(one.getLabel());
                    break;
                }
            }
            if(!marker.getLabel()){
                var label=new BMap.Label(marker.data.vno);
                var color;
                if(marker.data.status=="online"){
                    color="#3385ff";
                }else if(marker.data.status=="offline"){
                    color="#999";
                }else{
                    color="#ff000e";
                }
                label.setStyle({
                    borderRadius:"3px",
                    color:color,
                    border:"1px solid #efefef",
                    padding:"0 5px",
                    width:"64px",
                    height:"26px",
                    lineHeight:"26px",
                    textAlign:"center"
                });
                label.setOffset(new BMap.Size(-28,-28));
                marker.setLabel(label);
            }
            that.detailApp.item=marker.data;
        }
        this.clearOldMarkers=function(){
            if(!that.allMarkers){
                return;
            }
            for(var p in that.allMarkers){
                var m=that.allMarkers[p];
                that.map.removeOverlay(m);
            }
            this.detailApp.item={};
        };
        this.initModel=function(){
            this.listApp=new Vue({
                el:"#veh-list",
                data:{
                    list:[]
                },
                methods:{},
                computed:{
                    isEmpty:function(){
                        if(!this.list){
                            return true;
                        }else if(this.list.length==0){
                            return true;
                        }else{
                            return false;
                        }
                    }
                },
                components:{
                    'my-row':{
                        props:['item'],
                        template:'<li class="vehicle-item" @click="itemOnClick">\
                            <div class="veh-vno">{{item.vno}}</div>\
                            <div class="veh-line"><span :class="statusClass"></span> <span>{{item.continuedTxt}}</span></div>\
                            <div class="veh-line"><span>{{item.locationTxt}}</span></div>\
                        </li>',
                        methods:{
                            itemOnClick:function(){
                                $(".tab-header-item:first").trigger(ev.click);
                                var p=this.item.p;
                                var vid=this.item.vid;
                                var marker=that.allMarkers[vid];
                                setTimeout(function(){
                                    that.map.panTo(new BMap.Point(p.lng,p.lat));
                                    that.markerOnClick(marker);
                                },500);
                            }
                        },
                        computed:{
                            statusClass:function(){
                                var arr=['status'];
                                if(this.item.status=='online'){
                                    arr.push('online');
                                }else if(this.item.status=='offline'){
                                    arr.push('offline');
                                }else{
                                    arr.push('except');
                                }
                                return arr.join(" ");
                            }
                        }
                    }
                }
            });
            this.detailApp=new Vue({
                el:"#veh-detail",
                data:{
                    item:{}
                },
                methods:{
                    goTrack:function(){
                        that.showTrackWin(this.item);
                    },
                    closeMe:function(){
                        this.item={};
                    }
                },
                computed:{
                    isShow:function(){
                        var n=0;
                        for(var p in this.item){
                            n++;
                        }
                        if(n>0){
                            return "show";
                        }else{
                            return "";
                        }
                    },
                    statusClass:function(){
                        var arr=['status'];
                        if(this.item.status=='online'){
                            arr.push('online');
                        }else if(this.item.status=='offline'){
                            arr.push('offline');
                        }else{
                            arr.push('except');
                        }
                        return arr.join(" ");
                    }
                }
            });
            this.filterApp=new Vue({
                el:"#filter-div",
                data:{
                    isHome:true,
                    vno:"",
                    topNode:{},
                    item:{}
                },
                components:{
                    'group-node':{
                        props:['item'],
                        template:'<div class="form-item group">\
                            <label :class="labelClass" @click="labelClick">{{item.name}}</label>\
                            <a v-if="hasChild" @click="goNextLevel" class="next-level">下级</a>\
                        </div>',
                        methods:{
                            labelClick:function(){
                                var thisItem=this.item;
                                that.filterApp.item.children.forEach(function(item){
                                    if(!item.hasOwnProperty("checked")){
                                        Vue.set(item,"checked",false);
                                    }
                                    if(item==thisItem){
                                        item.checked=!item.checked;
                                    }else{
                                        item.checked=false;
                                    }
                                });
                            },
                            goNextLevel:function(e){
                                var thisItem=this.item;
                                that.filterApp.item.children.forEach(function(item){
                                    if(!item.hasOwnProperty("checked")){
                                        Vue.set(item,"checked",false);
                                    }
                                    if(item==thisItem){
                                        item.checked=true;
                                    }else{
                                        item.checked=false;
                                    }
                                });
                                that.filterApp.item=this.item;
                            }
                        },
                        computed:{
                            labelClass:function(){
                                var arr=['node-name'];
                                if(this.item.checked){
                                    arr.push('checked');
                                }
                                return arr.join(" ");
                            },
                            hasChild:function(){
                                return this.item.children && this.item.children.length>0;
                            }
                        }
                    }
                },
                methods:{
                    selectNode:function(){
                        this.isHome=false;
                    }
                },
                computed:{
                    nodeNames:function(){
                        var arr=[];
                        var currNode=this.topNode;
                        while(currNode.checked){
                            arr.push(currNode);
                            if(currNode.children && currNode.children.length>0){
                                var list=currNode.children;
                                var find=false;
                                for(var i=0;i<list.length;i++){
                                    if(list[i].checked){
                                        currNode=list[i];
                                        find=true;
                                        break;
                                    }
                                }
                                if(!find){
                                    break;
                                }
                            }else{
                                break;
                            }
                        }
                        return arr.map(function(item){
                            return item.name;
                        }).join(">");
                    },
                    nodeIds:function(){
                        var arr=[];
                        var currNode=this.topNode;
                        while(currNode.checked){
                            arr.push(currNode);
                            if(currNode.children && currNode.children.length>0){
                                var list=currNode.children;
                                var find=false;
                                for(var i=0;i<list.length;i++){
                                    if(list[i].checked){
                                        currNode=list[i];
                                        find=true;
                                        break;
                                    }
                                }
                                if(!find){
                                    break;
                                }
                            }else{
                                break;
                            }
                        }
                        /*return arr.map(function(item){
                            return item.id;
                        }).join(",");
                        */
                        return arr[arr.length-1].id;
                    },
                    hasChild:function(){
                        return this.item && this.item.children && this.item.children.length>0;
                    }
                }
            });
            this.filterApp.$nextTick(function(){
                //点击确定
                $("#ok-btn").bind(ev.click,function(){
                    that.filterOK();
                });
                //点击返回
                $("#back-btn").bind(ev.click,function(){
                    that.filterGoBack();
                });
            });
        };
        this.hideFilter=function(ok){
            if(ok){
                //验证
                var params={};
                params.vno=this.filterApp.vno;
                params.deptId=this.filterApp.nodeIds;
                that.getDataList(params);
                $("#filter-div").fadeOut();
            }else{
                //取消的操作
                $("#filter-div").fadeOut();
            }

        };
        this.showFilter=function(){
            $("#filter-div").fadeIn();
            that.filterApp.isHome=true;
        };
        this.filterOK=function(){
            if(!this.filterApp.isHome){
                this.filterApp.isHome=true;
            }else{
                this.hideFilter(true);
            }
        };
        this.filterGoBack=function(){
            var checkedItem=that.filterApp.item.children && that.filterApp.item.children.filter(function(item){
                return item.checked;
            });
            if(checkedItem.length>0){
                //取消勾选的项目
                checkedItem[0].checked=false;
            }else{
                if(that.filterApp.item.parent){
                    that.filterApp.item=that.filterApp.item.parent;
                }else{
                    if(!that.filterApp.isHome){
                        that.filterApp.isHome=true;
                    }else{
                        that.hideFilter(false);
                    }
                }
            }
        };
        this.showTrackWin=function(data){
            //location.hash="/track";
            tools.setSessionObj("zhiyun-track-page",{vno:data.vno,vid:data.vid});
            location.href="track/track.html?v="+window.jsVer;
        };
        this.onHashChange=function(){
            /*var data={vno:1111};
            var path=location.hash.substr(1);
            console.log(path);
            if(path=="/track"){
                var url="track/track.html?v="+window.jsVer+"&vno="+data.vno+"&r="+Math.random();
                $("#track-frame").attr("src",url);
                $("#track-div").fadeIn();
            }else{
                $("#track-frame").attr("src","about:blank");
                $("#track-div").fadeOut();
            }*/
        };
        this.bindEvent=function(){
            $(window).bind("hashchange",function(){
                that.onHashChange();
            });
            //切换tab页签
            $(".tab-header-item").bind(ev.click,function(){
                if(!$(this).hasClass("active")){
                    $(this).addClass("active").siblings().removeClass("active");
                    var i=$(this).index();
                    $(".main-item:eq("+ i +")").addClass("active").siblings().removeClass("active");
                    if(i==0){
                        $("#search-ico").hide();
                    }else{
                        $("#search-ico").show();
                    }
                }
            });
            $("#map-loc").bind(ev.click,function(){
                var points=[];
                for(var p in that.allMarkers){
                    var m=that.allMarkers[p];
                    points.push(m.getPosition());
                }
                if(points.length>1){
                    that.map.setViewport(points);
                }
            });
            //点击放大镜
            $("#search-ico").bind(ev.click,function(){
                that.showFilter();
            });
            //点击验
            $(".validate-btn").bind(ev.click,function(){
                location.href="validate.html?v="+window.jsVer;
            });
            //放大
            $("#zoomin").bind(ev.click,function(){
                that.map.zoomIn();
            });
            //缩小
            $("#zoomout").bind(ev.click,function(){
                that.map.zoomOut();
            });
            //切换地图模式
            $("#map-s-s").bind(ev.click,function(){
                if($("#switcher-div").is(":hidden")){
                    $("#switcher-div").fadeIn();
                }else{
                    $("#switcher-div").fadeOut();
                }
            });

            //卫星地图切换
            $("#satellite").bind(ev.click,function(){
                if ($(this).hasClass("active")) {
                    $(this).removeClass("active");
                    that.map.setMapType(BMAP_NORMAL_MAP);
                }else{
                    that.map.setMapType(BMAP_SATELLITE_MAP);
                    $(this).addClass("active");
                }
                $("#switcher-div").fadeOut();
            });
            //交通状况切换
            $("#normal-map").bind(ev.click,function(){
                if ($(this).hasClass("active")) {
                    that.map.removeTileLayer(this.traffic);
                    $(this).removeClass("active");
                } else {
                    // 创建交通流量图层实例
                    this.traffic = new BMap.TrafficLayer();
                    that.map.addTileLayer(this.traffic);
                    $(this).addClass("active");
                }
                $("#switcher-div").fadeOut();
            });
        };
        this.initMap=function(){
            var map = new BMap.Map("map-div");    // 创建Map实例
            map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);  // 初始化地图,设置中心点坐标和地图级别
            //map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
            map.setCurrentCity("北京");          // 设置地图显示的城市 此项是必须设置的
            map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
            this.map=map;
        };
    }
    return new Page();
});