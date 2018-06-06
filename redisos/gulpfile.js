/**
 * Created by jack on 2016/12/20.
 */
var gulp=require("gulp");
var uglify=require('gulp-uglify');
var minifycss=require('gulp-minify-css');
var htmlmin=require('gulp-htmlmin');
//var vermap=require("./vermap.js");
var revHash=require('rev-hash');
var minifyimage=require('gulp-imagemin');
var clean=require('gulp-clean');
var fs=require("fs");
var through=require("through2");
var gutil=require('gulp-util');
var file=require('vinyl-file');

//把vermap.js 合并到gulpfile.js里
var vermap=(function(){
    function getManifestFile(opts,cb){
        file.read(opts.path,opts).then(function(file){
            cb(null,file);
        },function(err){
            if(err.code=='ENOENT'){
                cb(null,new gutil.File(opts));
            }
        });
    }

    /**
     * @param opts Object {path:"把js和css的对应版本号信息存储到的js文件,可以为string或Array",webModule:"部署目录,例如 proj_a 为空表示部署到根目录"}
     * @param saveObj Object 执行完毕后回在saveObj上挂一个属性verMap 来存储 整站js和css 的hash
     * */
    var verMap=function(opts,saveObj){
        opts=opts || {};
        opts.path=opts.path || 'rev-map.js';
        var pathMap={};
        return through.obj(function(file,enc,cb){
            if(file.isNull()){
                cb();
                return;
            }

            if(file.isStream()){
                cb(new Error('Call PluginError'));
                return;
            }
            //console.log("base",file.base);
            //console.log("cwd",file.cwd);
            //console.log("path",file.path);
            if(!opts.base){
                opts.base=file.base;
            }
            if(!opts.cwd){
                opts.cwd=file.cwd;
            }
            var oldPath=file.path;
            var key=oldPath.replace(file.base,"").replace(/\\/g,"/");
            if(key.charAt(0)!="/"){
                key="/"+key;
            }
            if(opts.webModule){
                key="/"+opts.webModule+key;
            }
            pathMap[key]=revHash(file.contents);
            cb();
        },function(cb){
            if(Object.keys(pathMap).length===0){
                cb();
                return;
            }
            saveObj.verMap=pathMap;
            var that=this;
            //使用 opts.path 的复制品 不影响 原始值的使用
            var paths=typeof opts.path=="string" ? [opts.path] : opts.path.concat();
            var opt={};
            opt.base=opts.base;
            opt.cwd=opts.cwd;
            opt.path=opts.base+paths.pop();

            function callback(err,manifestFile){
                if(err){
                    cb(err);
                    return;
                }
                var oldManifest="";
                if(!manifestFile.isNull()){
                    oldManifest=manifestFile.contents.toString();
                }
                var s="window.verMap="+JSON.stringify(pathMap)+";\r\n"+oldManifest;
                manifestFile.contents=new Buffer(s);
                that.push(manifestFile);
                if(paths.length>0){
                    opt.path=opts.base+paths.pop();
                    getManifestFile(opt,callback);
                }else{
                    cb();
                }
            }

            getManifestFile(opt,callback);
        });
    };

    var test2=function(s){
        //不做任何操作 仅仅是看一下 文件的信息
        return through.obj(function(file,enc,cb){
            console.log(s+",path:"+file.path);
            console.log(s+",base:"+file.base);
            console.log(s+",cwd:"+file.cwd);
            cb(null,file);
        },function(cb){
            //console.log(s,"22222222222222",arguments);
            cb();
        });
    };
    return {test2:test2,verMap:verMap};
})();

var config={
    src:"src/main/resources/static_src", //源根路径
    dest:"src/main/resources/static", //目标根路径
    verMapFiles:["js/libs/require.js"],  //用来保存verMap对象的js文件 可以为数组 如:["a.js","b.js"]
    webModule:""   //如果项目部署不在根目录 请在此处填写部署路径 如 path1
};
//测试 查看 路径信息
gulp.task("test",function(){
    return gulp.src([config.src+'/**/*',"!"+config.src+'/about/*'])
        .pipe(vermap.test2("a"));
});
//构建门户网站 并压缩图片
/*gulp.task("build:compress:img",function(){
	gulp.start(["html","images"]);
});*/
//构建门户网站 并copy图片
gulp.task("default",function(){
    gulp.start(["html","copyimg"]);
});
gulp.task("copyimg",function(){
    return gulp.src([config.src+"/**/*.jpg",config.src+"/**/*.png",config.src+"/**/*.gif"])
        .pipe(gulp.dest(config.dest));
});
gulp.task("copyjsmin",function(){
    return gulp.src([config.src+"/**/*.min.js"]).pipe(gulp.dest(config.dest));
});
gulp.task("other",["copyjsmin"],function(){
    return gulp.src([config.src+"/**/*","!"+config.src+"/**/*.scss","!"+config.src+"/**/*.map","!"+config.src+"/**/*.js","!"+config.src+"/**/*.jpg","!"+config.src+"/**/*.png","!"+config.src+"/**/*.gif","!"+config.src+"/**/*.css","!"+config.src+"/**/*.html"])
        .pipe(gulp.dest(config.dest));
});
gulp.task("images",function(){
    return gulp.src([config.src+"/**/*.jpg",config.src+"/**/*.png",config.src+"/**/*.gif"])
        .pipe(minifyimage())
        .pipe(gulp.dest(config.dest));
});
//在 配置的js文件里 增加 verMap变量 操作的是压缩后的文件
gulp.task("version",["style","script","other"],function(){
    return gulp.src([config.dest+"/**/*.js",config.dest+"/**/*.css","!"+config.dest+"/behavior/**/*","!"+config.dest+"/components/**/*"])
        .pipe(vermap.verMap({path:config.verMapFiles,webModule:config.webModule},config))
        .pipe(gulp.dest(config.dest))
});
// 压缩 js 文件
gulp.task("script",function(){
    return gulp.src([config.src+"/**/*.js",'!'+config.src+'/**/*.min.js'])
        .pipe(uglify())
        .pipe(gulp.dest(config.dest));
});
gulp.task("style",function(){
    return gulp.src([config.src+"/**/*.css"])
        .pipe(minifycss())
        .pipe(gulp.dest(config.dest));
});
//在html中 给js文件增加版本号
gulp.task("html",["version"],function(){
    // 在version 执行完毕 后会在config上挂一个属性 叫verMap 就是每个文件对应的hash值
    var verMapFiles=config.verMapFiles.map(function(item){
        if(config.webModule){
            return "/"+config.webModule+"/"+item;
        }else{
            return "/"+item;
        }
    });
    //用来记录写有verMap变量的js文件的revHash
    var verMapFileObj={};

    function getVer(src,file){
        var base=file.base;
        var htmlpath=file.path;
        var path;
        if(src.charAt(0)==="/"){
            path=src;
        }
        if(src.indexOf("http")===0){
            return "";
        }else{
            htmlpath=htmlpath.replace(base,"").replace(/\\/g,"/");
            if(htmlpath.charAt(0)!=="/"){
                htmlpath="/"+htmlpath;
            }
            if(config.webModule){
                htmlpath="/"+config.webModule+htmlpath;
            }

            var arr=htmlpath.split("/");

            if(arr[arr.length-1]!==""){
                arr.pop();
            }
            if(src.charAt(1)!=="."){
                path=arr.join("/")+"/"+src;
            }else{
                if(src.substr(0,2)==="./"){
                    path=arr.join("/")+src.substr(1);
                }else{
                    while(src.substr(0,3)==="../"){
                        arr.pop();
                        src=src.substr(3);
                    }
                    path=arr.join("/")+"/"+src;
                }
            }
        }

        var ver;
        var idx=verMapFiles.indexOf(path);
        if(idx!== -1){
            //写有verMap变量的js
            ver=verMapFileObj[path];
            if(!ver){
                var jsFilePath=config.dest+"/"+config.verMapFiles[idx];
                ver=revHash(new Buffer(fs.readFileSync(jsFilePath,{encodeing:'utf8'})));
                verMapFileObj[path]=ver;
            }
        }else{
            //一般js
            ver=config.verMap[path];
        }
        return ver;
    }

    function replaceStr(file){
        var rtStr=file.contents.toString("utf8");
        var reg=/src="([^"]+\.js)"/g;
        rtStr=rtStr.replace(reg,function(old,$1){
            var ver=getVer($1,file);
            if(ver){
                return 'src="'+$1+'?u='+ver+'"';
            }else{
                return old;
            }
        });
        var reg2=/src='([^']+\.js)'/g;
        rtStr=rtStr.replace(reg2,function(old,$1){
            var ver=getVer($1,file);
            if(ver){
                return "src='"+$1+"?u="+ver+"'";
            }else{
                return old;
            }
        });
        return rtStr;
    }

    return gulp.src([config.src+"/**/*.html"])
        .pipe(through.obj(function(file,enc,cb){
            if(!file.isNull()){
                var s=replaceStr(file);
                file.contents=new Buffer(s);
                cb(null,file);
            }else{
                cb(null,file);
            }
        })).pipe(htmlmin({collapseWhitespace: false})).pipe(gulp.dest(config.dest));
});
gulp.task("clean",function(){
    return gulp.src(config.dest+"/*")
        .pipe(clean());
});