/**
 *
 */

var timer;
var animatedContext;
var canvasLoop = 180; // canvas刷新频率
var config = {
    cAnimateConvas: "#star-canvas"
}
var starsMap = new Map();
// 动态星星
var starShow = 30; // 星星闪烁占用刷新次数 4

var MaxX = 1000, MaxY = 1000;

var infoObj = function (id) {
    this.id = id;
    this.starTimer = 0;
    this.angle = 0;
    this.x = Math.round(Math.random() * MaxX);
    this.y = Math.round(Math.random() * MaxY);
};

infoObj.prototype.update = function () {
    this.angle += Math.PI * Math.random() / 90;
};

infoObj.prototype.add = function () {
    starsMap.put(this.id, this);
};
infoObj.prototype.remove = function () {
    starsMap.remove(this.id);
};
infoObj.prototype.draw = function () {
    if (this.starTimer > starShow * Math.random()) {
        this.remove();
    } else {
        this.drawAnimatedStar();
    }
};
// 显示动态星星
infoObj.prototype.drawAnimatedStar = function () {
    this.starTimer++;
    // if(this.starTimer%2==0){
    animatedContext.fillStyle = 'rgba(255,255,255,.27)'; // point
    animatedContext.strokeStyle = 'rgba(142,234,249,.1)'; // line

    var star = new starObj(this.x, this.y, this.angle, this.starTimer);
    star.drawAnimated();
    // }
};

// 星星对象
var starObj = function (x, y, angle, timer) {
    this.x = x;
    this.y = y;
    this.angle = angle;
    this.timer = timer;
    this.lines = [];
    // var pos = [
    //     {'x': 0, 'y': 40}, {'x': 20, 'y': 20}, {'x': 40, 'y': 0}, {'x': 20, 'y': -20},
    //     {'x': 0, 'y': -40}, {'x': -20, 'y': -20}, {'x': -40, 'y': 0}, {'x': -20, 'y': 20}
    // ];
    var pos = [
        {'r': Math.random() * 40, 'deg': angle + Math.PI * 0 / 180},
        {'r': Math.random() * 28, 'deg': angle + Math.PI * 45 / 180},
        {'r': Math.random() * 40, 'deg': angle + Math.PI * 90 / 180},
        {'r': Math.random() * 28, 'deg': angle + Math.PI * 135 / 180},
        {'r': Math.random() * 40, 'deg': angle + Math.PI * 180 / 180},
        {'r': Math.random() * 28, 'deg': angle + Math.PI * 225 / 180},
        {'r': Math.random() * 40, 'deg': angle + Math.PI * 270 / 180},
        {'r': Math.random() * 28, 'deg': angle + Math.PI * 315 / 180}
    ];
    for (var i = 0; i < pos.length; i++) {
        console.log();
        this.lines.push({
            x: pos[i].r * Math.sin(pos[i].deg),
            y: pos[i].r * Math.cos(pos[i].deg),
            size: pos[i].r,
            w:Math.round(Math.random() * 5)
        });
    }
};
starObj.prototype.drawAnimated = function () {
    var sx = this.x;
    var sy = this.y;
    var sr = Math.round(Math.random() * 10);

    var cgradient = animatedContext.createRadialGradient(sx, sy, 1, sx, sy, sr);
    // cgradient.addColorStop("0", 'rgba(142,234,249,.7)');
    // cgradient.addColorStop(".2", 'rgba(142,234,249,.5)');
    // cgradient.addColorStop("1", "rgba(142,234,249,.02)");
    cgradient.addColorStop("0", 'rgba(255,255,255,.7)');
    cgradient.addColorStop(".2", 'rgba(255,255,255,.5)');
    cgradient.addColorStop("1", "rgba(255,255,255,.02)");
    animatedContext.beginPath();
    animatedContext.fillStyle = cgradient;
    animatedContext.arc(this.x, this.y, sr, 0,Math.PI*2,true);
    animatedContext.closePath();
    animatedContext.fill();

    this.lines.forEach(function (line) {
            var x = sx + line.x;
            var y = sy - line.y;

    //         var mygradient = animatedContext.createRadialGradient(sx, sy, 0, x, y, line.size);
            var mygradient = animatedContext.createLinearGradient(sx, sy, x, y);
            // mygradient.addColorStop("0", 'rgba(142,234,249,.7)');
            // mygradient.addColorStop(".2", 'rgba(142,234,249,.4)');
            // mygradient.addColorStop("1", "rgba(142,234,249,.1)");
            mygradient.addColorStop("0", 'rgba(255,255,255,.7)');
            mygradient.addColorStop(".2", 'rgba(255,255,255,.4)');
            mygradient.addColorStop("1", "rgba(255,255,255,.1)");
            animatedContext.fillStyle = mygradient;

            animatedContext.lineWidth = line.w;
            animatedContext.beginPath();
            animatedContext.moveTo(sx, sy);
            animatedContext.lineTo(x, y);

            animatedContext.closePath();
            animatedContext.stroke();
        }
    );

};

// 清空画布
function clearCanvas(context, width, height) {
    context.clearRect(0, 0, width, height);
}

function canvasStarShow() {
    $(window).resize(canvasResize);
    animatedCanvas = $(config.cAnimateConvas)[0];
    resetMaxXY();
    animatedContext = animatedCanvas.getContext('2d');
    var sinfo = new infoObj("s-first");
    sinfo.add();
    timer = setInterval(drawStars, canvasLoop);
}

function drawStars() {
    clearCanvas(animatedContext, MaxX, MaxY);
    var nowSec = "s_" + new Date();

    for (var i = 0; i < starsMap.size(); i++) {
        var infoobj = starsMap.getByIndex(i);
        infoobj.update();
        infoobj.draw(animatedCanvas);
    }
    if (Math.random() > 0.5) {
        var sinfo = new infoObj(nowSec);
        sinfo.add();
    }
}

function canvasResize() {
    clearInterval(timer);
    resetMaxXY();
    timer = setInterval(drawStars, canvasLoop);
}

function resetMaxXY() {
    animatedCanvas.width = $("#star-canvas").width();
    animatedCanvas.height = $("#star-canvas").height();
    MaxX = animatedCanvas.width;
    MaxY = animatedCanvas.height;
}






