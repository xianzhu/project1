/**
 * Created by a88u on 2016/10/8.
 */

// 获取url参数
function getUrlQueryStr(sArgName, urlStr) {
    //console.log(urlStr);
    var url=decodeURI(urlStr);
    //console.log(url);
    var args = url.split("?");
    var retval;

    if (args[0] == url) {
        return retval;
    }
    var str = args[1];
    args = str.split("&");
    for (var i = 0; i < args.length; i++) {
        str = args[i];
        var arg = str.split("=");
        if (arg.length <= 1) continue;
        if (arg[0] == sArgName) retval = arg[1];
    }
    return retval;
}

function replaceUrlQueryStr(sArgName,srcStr,newvalue){
    var newstr="";
    console.log(sArgName,srcStr,newvalue);
    var from=srcStr.indexOf(sArgName+"=");
    if(from==-1){ // 没有此参数，直接加上
        if(srcStr.indexOf("?")<0){
            newstr=srcStr + "?" + sArgName + "=" + newvalue;
        }else {
            newstr = srcStr + "&" + sArgName + "=" + newvalue;
        }
    }else {
        var head = srcStr.substr(0, from);
        var next = srcStr.indexOf("&", from);
        var foot = "";
        if (next != -1) {
            foot = srcStr.substring(next, srcStr.length);
        }
        console.log(head, ', ', foot);
        newstr = head + "key=" + newvalue + foot;
    }
    console.log(newstr);
    return newstr;
}

// jquery dataTables表设置
function bindExportedDataTable(domId,perLength,exportTitle,customOptions){
    var dom='#'+domId;
    var tableOptions={
        searching:false,
        ordering:false,
        language:{
            url:"data/dataTables_cn.json"
        },
        headerCallback: function( thead, data, start, end, display ) { // 去掉表头的排序图标---需要考虑所有行
            var theadRows=$(thead).parent().find("tr");
            theadRows.each(function(){
                var self=$(this);
                //console.log(self);
                if(self.find("th").eq(0)) {
                    self.find("th").eq(0).removeClass("sorting_asc");
                }else{
                    console.log("not found");
                }
            });
        },
//            lengthChange:false,
            dom: '<"html5buttons"B>lTfgitp',
        /*
         l:每页显示数量
         B:按钮，button
         f: search工具条
         i:"Showing 1 to 21 of 21 entries"
         p:分页
         t:table
         */
        buttons: [
            {extend: 'copy',text:"复制"},
            //{extend: 'csv'},
            //{extend: 'excel'},
            {extend: 'pdf',title:exportTitle},
            {extend: 'print',title:exportTitle,
                customize: function (win){
                    $(win.document.body).addClass('white-bg');
                    $(win.document.body).css('font-size', '10px');

                    $(win.document.body).find('table')
                        .addClass('compact')
                        .css('font-size', 'inherit');
                }
            }
        ]
    };
    if(perLength>0){
        tableOptions.dom='<"html5buttons"B>tp';
        tableOptions.pageLength=perLength; // 设置每页显示项数
    }else{
        tableOptions.dom='<"html5buttons"B>t';
        tableOptions.paginate=false; // 不分页
    }
    if(typeof customOptions!='undefined'){
        console.log("do custom options");
        tableOptions=joinJsonObject(tableOptions,customOptions);
    }

    //console.log("no pageinate",tableOptions.dom);
//console.log(tableOptions);
    $(dom).DataTable(tableOptions);
}

function bindSimpleDataTable_new(domId,perLength){
    var dom='#'+domId;
    var tableOptions={
        searching:false,
        ordering:false,
        language:{
            url:"data/dataTables_cn.json"
        },
        headerCallback: function( thead, data, start, end, display ) { // 去掉表头的排序图标---需要考虑所有行
            var theadRows=$(thead).parent().find("tr");
            theadRows.each(function(){
                var self=$(this);
                //console.log(self);
                if(self.find("th").eq(0)) {
                    self.find("th").eq(0).removeClass("sorting_asc");
                }else{
                    console.log("not found");
                }
            });
        },
        dom: '<"html5buttons"B>lTfgitp',

        buttons: [
            //'copy','excel','pdf','print'
            {extend: 'copy',text:"复制"},
            {extend: 'csv'},
            {extend: 'excel'},
            {extend: 'pdf',orientation:'landscape',defaultStyle:{font:'msyh'}},
            {extend: 'print',
                customize: function (win){
                    $(win.document.body).addClass('white-bg');
                    $(win.document.body).css('font-size', '10px');

                    $(win.document.body).find('table')
                        .addClass('compact')
                        .css('font-size', 'inherit');
                }
            }
        ]
    };
    if(perLength>0){
        tableOptions.dom='<"html5buttons"B>tp';
        tableOptions.pageLength=perLength; // 设置每页显示项数
    }else{
        tableOptions.dom='<"html5buttons"B>t';
        tableOptions.paginate=false; // 不分页
    }

    $(dom).DataTable(tableOptions);
}

function bindSimpleDataTable(domId,perLength){
    var dom='#'+domId;
    var tableOptions={
        searching:false,
        ordering:false,
        language:{
            url:"data/dataTables_cn.json"
        },
        headerCallback: function( thead, data, start, end, display ) { // 去掉表头的排序图标---需要考虑所有行
            var theadRows=$(thead).parent().find("tr");
            theadRows.each(function(){
                var self=$(this);
                self.find("th").eq(0).removeClass("sorting_asc");
            });
        },
        dom: 'tp',
        buttons: []
    };
    if(perLength>0){
        tableOptions.dom='tp';
        tableOptions.pageLength=perLength; // 设置每页显示项数
    }else{
        tableOptions.dom='t';
        tableOptions.paginate=false; // 不分页
    }

    $(dom).DataTable(tableOptions);
}

function joinJsonObject(jObj1,jObj2){
    var result={};
    for(var item in jObj1){
        result[item]=jObj1[item];
    }
    for(var item in jObj2){
        result[item]=jObj2[item];
    }
    return result;
}

function clone(obj) {
    var o;
    if (typeof obj == "object") {
        if (obj === null) {
            o = null;
        } else {
            if (obj instanceof Array) {
                o = [];
                for (var i = 0, len = obj.length; i < len; i++) {
                    o.push(clone(obj[i]));
                }
            } else {
                o = {};
                for (var k in obj) {
                    o[k] = clone(obj[k]);
                }
            }
        }
    } else {
        o = obj;
    }
    return o;
}

function gotoClickPage(url,id){
    //var score=parseFloat(Math.random()*100).toFixed(0);
    var score=0;
console.log("gotoClickPage: ",url,', ',id);
    if(id&&id!=null&&id!="null"&&id!="") {
        var murl = url + ".html?id=" + id + "&uname=" + v_userModel.$data.uname + "&score=" + score;
        window.location.href = murl;
    }
}

function gotoMenuPage(url,id){
    var score=0;
    var murl = url + ".html?id=" + id + "&uname=" + v_userModel.$data.uname + "&score=" + score;
    window.location.href = murl;
}

function openClickPage(url,id){
    var murl=url+".html?id="+id;
    window.open(murl);
}

function sendMonitor(options){
    var img = new Image(),
        id = 'img' + new Date();
    img.id = id;
    img.onload = img.onerror = img.onabort = function() { window[id] = undefined; };
    window[id] = img;
    img.src = 'testData/loginout.json?url='+options.url; // 此处设置src
}

function gotoSearchPage(url,key){
    var score=parseFloat(Math.random()*100).toFixed(0);
    console.log("goto ",url);
    var murl=url+".html?key="+key+"&uname="+v_userModel.$data.uname+"&score="+score;
    window.location.href=murl;
}

function gotoCompanyPage(url,id,type){
    console.log("goto:",id,', ',type);
    if(id&&id!=""&&id!=null&&id.length>3) {
        var score = parseFloat(Math.random() * 100).toFixed(0);
        var murl = url + ".html?id=" + id + "&comtype=" + type + "&uname="
            + v_userModel.$data.uname + "&score=" + score;
        window.location.href = murl;
    }
}

function gotoCompanyByCode(code,type){
    $.ajax({
        url: commonUrls.companyIdUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            id:code
        },
        dataType: "text",
        success: function (res) {
            var response = res;
            console.log(res);
            //if((typeof response!=undefined)&&response!=""&&response!="null"&&response!=null) {
            if(res){
                console.log("if",res);
                var id=res;
                if(code.substr(0,2)=="80"){ // 新三板
                    gotoCompanyPage("companyBasic",id,1);
                }else{ // A股
                    gotoCompanyPage("companyBasic",id,0);
                }

                //item = {type: type, id: response.stockCode};
            }else{
                console.log("not found:",code);
                ////item={type:type,id:response.uuid};
                //gotoCompanyPage("companyBasic",id,2);
            }
        },
        fail: function (status) {
            console.error("event id=", id, " error. status=", status);
        },
        statusCode: {
            404: function() {
                goTo404();
            },
            500:function(){
                goTo500();
            }
        }
    });
}

function goTo404(){
window.location.href="404.html";
}
function goTo500(){

}
function goToFailure(res){
    if(res.message=="未登录或者登录已经失效"){
        goToNotlogon();
    }
}
function goToNotlogon(){
    window.location.href="notlogon.html";
}

function openFilesOnline(path){
    var pdfUrl=path;

    if(isTest){
        pdfUrl=hostUrl+path;
    }

    console.log(pdfUrl);
    $.ajax({
        url: pdfUrl,              //请求地址
        type: "POST",                            //请求方式
        data: {},
        success: function (res) {
            //console.log(typeof res);
            if(res.status=='failure'){
                //goToLoginout();
                console.log("failure",res.message);
                window.open("404.html");
            }else{
                //console.log(typeof res);
                var isTimeout=res.indexOf('{');
                // timeout 服务器返回的是字符串，而不是json   {"message":"Please relogin","status":"timeout"}
                if(res.indexOf('{')==0){
                    console.log("timeout",res);
                    goToNotlogon();
                }else{
                    console.log("success....");
                    window.open(pdfUrl);
                }
            }
        },
        fail: function (status) {
            console.error("event id=", id, " error. status=", status);
        },
        statusCode: {
            404: function() {
                goTo404();
            },
            500:function(){
                goTo500();
            }
        }
    });
    console.log("open url");
    //window.open("pdfPreview.html?path="+path);
}

function initPopover(){
    var options={
        trigger:"hover focus",
        delay:{
            show:300,
            hide:200
        }
    };
    $("[data-toggle='popover']").popover(options);
}

function gotoCompanybyId(id){
    //var uuid=id;
    console.log(id);
    if(id&&id!="NULL"&&id!="-") {
        //var item;
        $.ajax({
            url: commonUrls.companyIdUrl,              //请求地址
            type: "POST",                            //请求方式
            data: { //请求参数
                id: id
            },
            dataType: "text",
            success: function (res) {
                var response = res;
                console.log(res);
                //if((typeof response!=undefined)&&response!=""&&response!="null"&&response!=null) {
                if (res) {
                    console.log("if", res);
                    if (res.substr(0, 2) == "80") { // 新三板
                        gotoCompanyPage("companyBasic", id, 1);
                    } else { // A股
                        gotoCompanyPage("companyBasic", id, 0);
                    }
                    //item = {type: type, id: response.stockCode};
                } else {
                    console.log("elseif: ");
                    //item={type:type,id:response.uuid};
                    gotoCompanyPage("companyBasic", id, 2);
                }
            },
            fail: function (status) {
                console.error("event id=", id, " error. status=", status);
            },
            statusCode: {
                404: function () {
                    goTo404();
                },
                500: function () {
                    goTo500();
                }
            }
        });
    }
}

function checkNotEmptyList(value){
    var result=false;
    if(value&&value!=null&&value!=""){
        result=true;
    }
    return result;
}


// 退出登录
function loginOut() {
    $.ajax({
        url: commonUrls.loginoutUrl,
        type: "POST",    //请求方式
        data: {},     //请求参数
        dataType: "json",
        success: function (res) {
            console.log("res:", res);
            //var response = res.data;
            //if(res.status=="success") {
            //    window.location.href = "index.html";
            //}else if(res.status=="failure"){
            //    if(res.message=="未登录或者登录已经失效"){
            //        goToNotlogon();
            //    }
            //}
            window.location.href = "index.html";
        },
        fail: function (res) {
            console.error("error. message=", res);
            window.location.href = "index.html";
        }
    });
}


// key-value键值对
function Map() {
    var struct = function(key, value) {
        this.key = key;
        this.value = value;
    };

    var put = function(key, value){
        for (var i = 0; i < this.arr.length; i++) {
            if ( this.arr[i].key === key ) {
                this.arr[i].value = value;
                return;
            }
        }
        this.arr[this.arr.length] = new struct(key, value);
    };

    var get = function(key) {
        for (var i = 0; i < this.arr.length; i++) {
            if ( this.arr[i].key === key ) {
                return this.arr[i].value;
            }
        }
        return null;
    };

    var getByIndex=function(index){
        if(this.arr.length>index)
            return this.arr[index].value;
    };

    var remove = function(key) {
        var v;
        for (var i = 0; i < this.arr.length; i++) {
            v = this.arr.pop();
            if ( v.key === key ) {
                continue;
            }
            this.arr.unshift(v);
        }
    };

    var size = function() {
        return this.arr.length;
    };

    var isEmpty = function() {
        return this.arr.length <= 0;
    };

    var clear=function(){
        this.arr.length=0;
    };

    this.arr = new Array();
    this.get = get;
    this.getByIndex=getByIndex;
    this.put = put;
    this.clear=clear;
    this.remove = remove;
    this.size = size;
    this.isEmpty = isEmpty;
}

function toRateFormat(value,fixed){
    return parseFloat(value*100).toFixed(fixed)+"%";
}

function toDateFormat(value){
    var result="";
    if(value&&value!=null&&value.toLowerCase()!="null"){
        //console.log(value);
        result=value;
    }else{
        result="--";
    }
    return result;
}

function toAmountFormat(value,fixed,unit) {
    var value=(parseFloat(value).toFixed(fixed) + '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
    return unit+value;
}

function checkIfCompanyMenu(url){
    //var result=false;
    if(url.indexOf("company")==0&&url.length>7){
        return true;
    }else{
        return false;
    }
}

function savecurrentSearchPage(key,value){
    var url=window.location.href;
    var newurl=replaceUrlQueryStr(key,url,value);
    var stateObj={foo:"bar"};
    history.pushState(stateObj,"测试页面1",newurl);
}



