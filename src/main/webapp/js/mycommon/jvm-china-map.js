/**
 *
 */


var timer;

var staticContext;
var animatedContext;

var staticChinaCanvas;
var animatedChinaCanvas;

var jvmChinaMapObj;

// 动态星星
var total = 19; // 每条信息显示占用刷新次数
var starShow = 4; // 星星闪烁占用刷新次数 4
var meteorShow = 6; // 流星占用刷新次数  6

// 静态星星
var linecount = 8; // 静态星星光芒数
var minStarLight = 3; // 星星光芒最小值
var starLightStep = 7; // 星星光芒范围

var staticIndex=0;

// 流星对话框
var dialogForm=[
    {"slng":125.8,"slat":36,"elng":125.8,"elat":36,"type":"start"}, // "top":/ 0
    {"slng":92,"slat":50,"elng":92,"elat":50,"type":"start"}, // "top":    1
    {"slng":74,"slat":48,"elng":74,"elat":48,"type":"end"}, // "btmL":   2
    {"slng":76,"slat":27,"elng":76,"elat":27,"type":"end"},//          3
    {"slng":128,"slat":25,"elng":128,"elat":25,"type":"start"},//          4
    {"slng":126,"slat":31,"elng":126,"elat":31,"type":"start"} // "btmR":    5

];

//var slng = 91.9, slat = 48.6;
//var elng = 100, elat = 41.8;

function chinaMapInit() {
    $(window).resize(canvasResize);

    animatedCanvas = $(config.cAnimateConvas)[0];
    staticCanvas = $(config.cStaticConvas)[0];

    staticContext = staticCanvas.getContext('2d');
    animatedContext = animatedCanvas.getContext('2d');

    jvmChinaMapObj = $(config.cMap).vectorMap('get', 'mapObject');
}


function chinaMapShow() {
    timer = setInterval(drawStars, chinaLoop);
}

function drawStars() {
    var canvas = $(config.cAnimateConvas)[0];
    clearCanvas(animatedContext, canvas.width, canvas.height);

    if (chinaMap.size() > 0) {
        var infoObj = chinaMap.getByIndex(0);
        infoObj.update();
        infoObj.draw(canvas);
    }
}

function canvasResize() {
    clearInterval(timer);
    setChinaCanvasSize();
    //reLoadMapData(staticIndex);

    timer = setInterval(drawStars, chinaLoop);
}

function setChinaCanvasSize() {
    var divEle = document.getElementById(config.cMainDiv);

    var width;
    var height;

    if (divEle.currentStyle) {
        width = divEle.currentStyle.width;
        height = divEle.currentStyle.height;
    } else {
        width = window.getComputedStyle(divEle, null).width;
        height = window.getComputedStyle(divEle, null).height;
    }

    $(config.cStaticConvas).attr("height", height);
    $(config.cStaticConvas).attr("width", width);

    $(config.cAnimateConvas).attr("height", height);
    $(config.cAnimateConvas).attr("width", width);
}

var infoObj = function (id, lat, lng, msg) {
    var nowSec=new Date();
    this.id = id+"_"+nowSec;
    this.lat = lat;
    this.lng = lng;
    this.msg = msg;

    this.timer = 0;
    this.meteorTimer = 0;
    this.starTimer = 0;
    this.getPointXY();
    this.getPointPos();
};

infoObj.prototype.getPointPos=function(){
    if(this.lat>37){
        if(this.lng>=120){
            this.pos=0;
        }else if(this.lng>90&&this.lng<120){
            this.pos=1;
        }else{
            this.pos=2;
        }
    }else{
        if(this.lng>=118){
            this.pos=5;
        }else if(this.lng>106&&this.lng<118){
            this.pos=4;
        }else{
            this.pos=3;
        }
    }
};

infoObj.prototype.getPointXY = function () {
    var point = jvmChinaMapObj.latLngToPoint(this.lat, this.lng);

    this.x = Math.round(point.x);
    this.y = Math.round(point.y);
};

infoObj.prototype.add = function () {
    if (isNaN(this.x) || isNaN(this.y)) {
        console.log("isNaN:" + this.x + ", " + this.y + ", " + this.lat + ", " + this.lng);
    } else {
        chinaMap.put(this.id, this);
    }
};

infoObj.prototype.remove = function () {
    chinaMap.remove(this.id);
};

infoObj.prototype.update = function () {
    this.timer = this.timer + 1;
    this.getPointXY();
};

infoObj.prototype.draw = function () {
//	animatedContext.clearRect(0,0,animatedCanvas.width,animatedCanvas.height);
//console.log("draw");
    if (this.starTimer <= starShow) {
        this.drawAnimatedStar(); // draw star and msg text
    } else if (this.timer <= total) {
        this.drawMeteor(this.x, this.y); // draw msg only
    }
    if (this.timer > total) {
        this.remove();
        this.drawStatic(); // draw staticCav
    }
};

// 显示动态星星
infoObj.prototype.drawAnimatedStar = function () {
    this.starTimer++;
//	animatedContext.fillStyle="rgba(142,234,249,.1)"; // point
    if (this.starTimer % 2 == 0) {
        animatedContext.fillStyle = 'rgba(255,255,255,.27)'; // point
        animatedContext.strokeStyle = 'rgba(142,234,249,.1)'; // line

        var star = new starObj(this.x, this.y, true);
        star.drawAnimated(animatedContext);
    }
};

// 显示流星效果
infoObj.prototype.drawMeteor = function (x, y) {
    this.meteorTimer++;
    var spoint = jvmChinaMapObj.latLngToPoint(dialogForm[this.pos].slat, dialogForm[this.pos].slng);
    var sx = Math.round(spoint.x);
    var sy = Math.round(spoint.y);

    if (isNaN(sx) || isNaN(sy)) {
        console.log("isNaN:" + sx + ", " + sy + ", " + dialogForm[this.pos].slat + ", " + dialogForm[this.pos].slng);
    }

    var epoint = jvmChinaMapObj.latLngToPoint(dialogForm[this.pos].elat, dialogForm[this.pos].elng);
    var ex = Math.round(epoint.x);
    var ey = Math.round(epoint.y);

    if (isNaN(ex) || isNaN(ey)) {
        console.log("isNaN:" + ex + ", " + ey + ", " + dialogForm[this.pos].elat + ", " + dialogForm[this.pos].elng);
    }

    var targetX = 0.5 * (ex + sx), targetY = 0.5 * (ey + sy);

    if (this.meteorTimer < meteorShow) { // 画流星
        drawMeteor(animatedContext, x, y, targetX, targetY, this.meteorTimer);
    }
    else { // 显示文字
        var lh = 22;
        var rw = 40;
        writeTextOnCanvas(animatedContext, lh, rw, sx, sy,dialogForm[this.pos].type, this.msg);
    }
};

// 绘制流星方法
function drawMeteor(context, sx, sy, ex, ey, time) {
    var lenX = (ex - sx) / meteorShow;
    var lenY = (ey - sy) / meteorShow;
    var tx = lenX * time + sx, ty = lenY * time + sy;

    var mygradient = context.createLinearGradient(sx, sy, tx, ty);
    mygradient.addColorStop(0, "rgba(255,255,255,.1)"); // .01
    mygradient.addColorStop(.5, "rgba(255,255,255,.5)"); // .3
    mygradient.addColorStop(1, "rgba(255,255,255,1)");

    // 画星星点
    context.beginPath();
    context.fillStyle = "rgba(255,255,255,1)";
    context.arc(sx, sy, 1, 0, Math.PI * 2);
    context.fill();
    context.closePath();

    // 画流星尾巴
    context.beginPath();
    context.strokeStyle = mygradient;
    context.moveTo(sx, sy);
    context.lineTo(tx, ty);
    context.stroke();
    context.closePath();
}

// 显示静态星星
infoObj.prototype.drawStatic = function () {
    staticContext.fillStyle = 'rgba(142,234,249,1)'; // point
    staticContext.strokeStyle = "rgba(142,234,249,.06)"; // line

    var star = new starObj(this.x, this.y, false);
    star.drawStatic(staticContext);
    staticIndex=this.id;
};

// 星星对象
var starObj = function (x, y, isAnimate) {
    this.x = x;
    this.y = y;
    // / console.log(this.x+", "+this.y+", "+this.lat+", "+this.lng);
    this.lines = [];
    if (isAnimate) {
        var pos = [
            {'x': 0, 'y': 20}, {'x': 10, 'y': 10}, {'x': 20, 'y': 0}, {'x': 10, 'y': -10},
            {'x': 0, 'y': -20}, {'x': -10, 'y': -10}, {'x': -20, 'y': 0}, {'x': -10, 'y': 10}
        ];

        for (var i = 0; i < pos.length; i++) {
            this.lines.push({
                x: pos[i].x * 0.5,
                y: pos[i].y * 0.5,
                size: Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2))
            });
        }
    } else {
        for (var i = 0; i < linecount; i++) {
//		var r=Math.random();
            this.lines.push({
                alpha: Math.random() * Math.PI * 2,
                size: Math.random() * starLightStep + minStarLight,
//			delta:r>.5? 1:-1,
//			gamma:r>.5? r*Math.PI/6:-r*Math.PI/6
            });
        }
    }
};

starObj.prototype.drawStatic = function (context) {
    context.fillRect(this.x, this.y, 1, 1);
    var sx = this.x + 0.5;
    var sy = this.y + 0.5;

    this.lines.forEach(function (line) {
            var x = sx + Math.round(Math.cos(line.alpha) * line.size) + 0.5;
            var y = sy + Math.round(Math.sin(line.alpha) * line.size) + 0.5;

//		 var mygradient=staticContext.createRadialGradient(sx,sy,0,x,y,line.size);
//		 mygradient.addColorStop("0",'rgba(142,234,249,.2)');
//		 mygradient.addColorStop(".2",'rgba(142,234,249,.15)');
//		 mygradient.addColorStop("1","rgba(142,234,249,.01)");
//		 staticContext.fillStyle=mygradient;

            context.beginPath(),
                context.moveTo(sx, sy),
                context.lineTo(x, y),

                context.closePath(),
                context.stroke();
        }
    );

};

starObj.prototype.drawAnimated = function (context) {
    context.fillRect(this.x, this.y, 1, 1);
    var sx = this.x;
    var sy = this.y;

    this.lines.forEach(function (line) {
            var x = sx + line.x;
            var y = sy + line.y;

            var mygradient = staticContext.createRadialGradient(sx, sy, 0, x, y, line.size);
            mygradient.addColorStop("0", 'rgba(142,234,249,.4)');
            mygradient.addColorStop(".2", 'rgba(142,234,249,.3)');
            mygradient.addColorStop("1", "rgba(142,234,249,.02)");
            staticContext.fillStyle = mygradient;

            context.beginPath(),
                context.moveTo(sx, sy),
                context.lineTo(x, y),

                context.closePath(),
                context.stroke();
        }
    );

};

// canvas显示文字
function writeTextOnCanvas(ctx, lh, rw, startx, starty,type, text) {
    var lineheight = lh;

    ctx.font = "12px 微软雅黑";
	//ctx.fillStyle = "rgba(0,0,0,1)";
    ctx.fillStyle = "rgba(255,255,255,1)";

    function getTrueLength(str) {//获取字符串的真实长度（字节长度）
        var len = str.length, truelen = 0;
        for (var x = 0; x < len; x++) {
            if (str.charCodeAt(x) > 128) {
                truelen += 2;
            } else {
                truelen += 1;
            }
        }
        return truelen;
    }

    function cutString(str, leng) {//按字节长度截取字符串，返回substr截取位置
        var len = str.length, tlen = len, nlen = 0;
        for (var x = 0; x < len; x++) {
            if (str.charCodeAt(x) > 128) {
                if (nlen + 2 < leng) {
                    nlen += 2;
                } else {
                    tlen = x;
                    break;
                }
            } else {
                if (nlen + 1 < leng) {
                    nlen += 1;
                } else {
                    tlen = x;
                    break;
                }
            }
        }
        return tlen;
    }

    var textStartx=startx;
    if(type=="end"){
        textStartx=40;
        console.log(textStartx,',  ',startx);
    }
    for (var i = 1; getTrueLength(text) > 0; i++) {
        var tl = cutString(text, rw);
        ctx.textAlign = 'left';

        //ctx.fillText(text.substr(0, tl).replace(/^\s+|\s+$/, ""), startx, i * lineheight + starty);

        ctx.fillText(text.substr(0, tl).replace(/^\s+|\s+$/, ""), textStartx, i * lineheight + starty,rw*12);
        text = text.substr(tl);
    }
}

// canvas resize时，重绘地图上的静态点
function reShowStaticStars(lat,lng){
    var point = jvmChinaMapObj.latLngToPoint(lat, lng);

    this.x = Math.round(point.x);
    this.y = Math.round(point.y);

    staticContext.fillStyle = 'rgba(142,234,249,1)'; // point
    staticContext.strokeStyle = "rgba(142,234,249,.06)"; // line

    var star=new starObj(x,y,false);
    star.drawStatic(staticContext);
}






///////////////////////////////////////////////////////////////////////////////////////////////
function readStars() {
    var num = Math.random() * 2;
//	timer++;
//	if(timer%100!=0)
//		return;
    if (chinaMap.size() < 3) {
        for (var i = 0; i < num; i++) {
            var lng = Math.random() * 10 + 104.07;
            var lat = Math.random() * 20 + 30.67;
            var id = randomString(8);
//		var msg=randomString(Math.floor(Math.random()*100+50));
            var msg = "在现实生活中，一些领导干部法治意识比较淡薄，有的存在有法不依、执法不严、甚至徇私枉法等问题，影响了党和国家的形象和威信，损害了政治、经济、文化、社会、生态文明领域的正常秩序。";

//		var r=Math.random();
//		if(r<.5){
//			msg="在现实生活中在CSS2中，border-color也开始接受transparent作为参数值，《Open eBook(tm) Publication Structure 1.0.1》[OEB101]延伸到color也接受transparent作为参数值。IE6不支持border的颜色为transparent，边框色会显示为黑色，一些领导45678uiouydsdfghjkl干部法治意识比较淡薄，有的存在有法不依、执法不严、甚afdfsfdsfdfds在现实生活中，一些领导干部法治意afdfe43980jiioiuoiuoioi有法不依、执法不严、甚f";
//		}

//		var lng=104.07;
//		var lat=30.67;

//		var r=Math.floor(Math.random()*CityIDs.length);
//		var city=CitiesID[r];
//		
//		var id=city.id;
//		var lat=city.lat+Math.random()*0.1;
//		var lng=city.lng+Math.random()*0.1;
//固定经纬度，为调试 
//		lng=104.07;
//		lat=30.67;

//		console.log(id+", "+lat+", "+lng);

            var info = new infoObj(id, lat, lng, msg);
            info.add();
        }
    }
}


//////////////////////////////////////////////////////////////
function randomReadStars() {
    var num = Math.random() * 5;
    if (chinaMap.size() < 3) {
        for (var i = 0; i < num; i++) {
            var r = Math.floor(Math.random() * citiesInfo.length);
            while (r >= citiesInfo.length) {
                r = Math.floor(Math.random() * citiesInfo.length);
            }
            var city = citiesInfo[r];

            var id = city.id;
            var rx = Math.random() > .5 ? 1 : -1;
            var ry = Math.random() > .5 ? 1 : -1;
            var lat = city.lat + Math.random() * 0.01 * rx;
            var lng = city.lng + Math.random() * 0.01 * ry;

//			var lat=city.lat;
//			var lng=city.lng;


            // 固定经纬度，调试用
//			lng=104.07;
//			lat=30.67;


//			var msg=city.name+": "+randomString(Math.round(Math.random()*70));
            var msg = city.name + "在现实生活中，一些领导干部法治意识比较淡薄，有的存在有法不依、执法不严、甚至徇私枉法等问题，影响了党和国家的形象和威信，损害了政治、经济、文化、社会、生态文明领域的正常秩序。";

            var ra = Math.random();
            if (ra < .2) {
                msg = city.name + "在现实生活中在CSS2中，border-color也开始接受transparent作为参数值，《Open eBook(tm) Publication Structure 1.0.1》[OEB101]延伸到color也接受transparent作为参数值。IE6不支持border的颜色为transparent，边框色会显示为黑色，一些领导45678uiouydsdfghjkl干部法治意识比较淡薄，有的存在有法不依、执法不严、甚afdfsfdsfdfds在现实生活中，一些领导干部法治意afdfe43980jiioiuoiuoioi有法不依、执法不严、甚f";
            } else if (ra < .4) {
                msg = city.name + "border-color transparent ，《Open eBook(tm) Publication Structure 1.0.1》[OEB101]延伸到color也接受transparent作为参数值。IE6不支持border的颜色为transparent，边框色会显示为黑色，一些领导45678uiouydsdfghjkl干部法治意识比较淡薄，有的存在有法不依、执法不严、甚afdfsfdsfdfds在现实生活中，一些领导干部法治意afdfe43980jiioiuoiuoioi有法不依、执法不严、甚f";
            } else if (ra < .6) {
                msg = city.name + ':input type="button" name="start" value="start" onclick="startShow();"' + 'liujincai.innerHTML=liujincai.innerHTML + " " + (intvalue ++).toString();';
            }


            var info = new infoObj(id, lat, lng, msg);
            info.add();
        }
    }
}

//function randomChinaMapShow() {
//    timer = setInterval(randomChinaMapLoop, chinaLoop);
//}
//
//
//function randomChinaMapLoop() {
//    randomReadStars();
////	console.log("mapsize="+chinaMap.size());
//    drawStars();
//}





