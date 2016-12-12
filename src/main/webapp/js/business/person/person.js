/**
 * Created by a88u on 2016/10/12.
 */
var v_peopleModel=new Vue({
    el:"#v-peopleModel",
    data:{
        result:[],
        isEnd:false,
        resultPage:0,
        key:"",
        doSearch:false,

        changeList:[],
        changeList_2:[]
    },
    ready:function(){

    },
    methods:{
        goto:function(url,id){
            savecurrentSearchPage("key",this.key);
            gotoClickPage(url,id);
        },
        openNews:function(url){
            sendMonitor({url:url});
        },
        pageControlFilter:function(value){
            //console.log(value,", ",this.resultPage,",",this.isEnd);
            if(value==0){
                return this.resultPage!=0;
            }else{
                return !this.isEnd;
            }
        },
        changeResultPage:function(value){
            if(value==0){ // 上一页
                this.resultPage--;
                getSubResultPage(this.key,this.resultPage);
            }else{ // 下一页
                this.resultPage++;
                getSubResultPage(this.key,this.resultPage);
            }
        }
    },
    filters:{
        currentPageFilter:function(value){
            var p=this.resultPage+1;
            return "第"+p+"页";
        },
        isResultEmpty:function(value){
            //console.log(this.isEnd);
            if(this.doSearch){
                return false;
            }
            return this.isEnd;
        },
        checkEmptyFilter:function(value){
            var result=true;
            if(this.doSearch){
                return false;
            }
            if(value&&value.length>0){
                result=false;
            }
            return result;
        },
        checkNotEmptyFilter:function(value){
            var result=false;
            if(this.doSearch){
                return true;
            }
            if(value&&value.length>0){
                result=true;
            }
            return result;
        },
        getShortStrFilter:function(value){
            var result=value;
            if(value&&value.length>100){
                result=result.substr(0,100)+"...";
            }
            return result;
        }
    }
});

var key=getUrlQueryStr("key",location.href);
if(typeof key == 'undefined'){
    key="";
}

if(key!="") {
    $("#top-search").val(key);
    v_peopleModel.$data.key = key;
    getSubResultPage(key, 0);
}else{
    getPersonInfo();
}

function getSubResultPage(key,page){
    if(key == null || key == '' || key == undefined)return;
    v_peopleModel.$data.doSearch=true;
    var turl=commonUrls.personSearchUrl;

    $.ajax({
        url: turl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            key:key,
            from:page*10
        },
        dataType: "json",
        success: function (res) {
            if(res.status=='failure'){
                //goToLoginout();
                console.log("failure",res.message);
                goToNotlogon();
            }else if(res.status=='success') {
                //console.log("send ajax success");
                $('#result_table').DataTable().destroy();
                v_peopleModel.$data.result=res.userList;
                if(res.userList.length==0){
                   v_peopleModel.$data.isEnd=true;
                }else{
                    v_peopleModel.$data.isEnd=false;
                }
                
                v_peopleModel.$nextTick(function () {
                    bindDataTable("result_table", -1, "投资者查询结果");
                });
            }
        },
        fail: function (status) {
            console.error("error. status=", status);
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

function getPersonInfo(){
    // changeList
    $.ajax({
        url: commonUrls.personUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
        },
        dataType: "json",
        success: function (res) {
            if(res.status=='failure'){
                //goToLoginout();
                console.log("failure",res.message);
                goToNotlogon();
            }else if(res.status=='success') {
                var response = res;
                console.log("response", response);
                $("#orgTop_table").DataTable().destroy();
                $("#angle_table").DataTable().destroy();

                v_peopleModel.$data.changeList=response.orglList;
                v_peopleModel.$data.changeList_2=response.angelList;

                v_peopleModel.$nextTick(function () {
                    bindDataTable("orgTop_table",10,"机构榜单");
                    bindDataTable("angle_table",10,"天使榜单");
                });

                //console.log(v_peopleModel.$data.changeList.length,', ',v_peopleModel.$data.changeList_2.length);
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

function bindDataTable(domId,perLength,exportTitle){
    var dom='#'+domId;

    var tableOptions={
        searching:false,
        ordering:false,
        language:{
            url:"data/dataTables_cn.json"
        },
        headerCallback: function( thead, data, start, end, display ) {
            $(thead).find("th").eq(0).removeClass("sorting_asc");
        },
        dom:'<"html5buttons"B>tp',
        buttons: [
            {extend: 'copy',text:"复制"},
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
        tableOptions.pageLength=perLength; // 设置每页显示项数
    }else{
        tableOptions.paginate=false; // 不分页
    }
    //for(var i=0;i<extendButtons.length;i++) {
    //    tableOptions.buttons.unshift(extendButtons[i]);
    //}
    //console.log(tableOptions);
    //console.log(domId,", ",exportTitle);
    $(dom).DataTable(tableOptions);
}

function searchBarPress(event,value){
    var event = event || window.event; // 为了兼容firefox没有全局event对象
    if (event.keyCode == 13) { // 回车搜索
        //var curKey=window.location.search;
        //
        //var newstr=replaceUrlQueryStr("key",curKey,value);
        //window.location.search=newstr;
        var key=value;
        console.log("doPersonSearch:",key);
        //$("#top-search").val(key);
        v_peopleModel.$data.key=key;
        v_peopleModel.$data.resultPage=0;
        getSubResultPage(key,0);
        //getPersonInfo();
    }
}