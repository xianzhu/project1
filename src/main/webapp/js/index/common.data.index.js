/**
 * Created by a88u on 2016/12/9.
 */


//var isTest=false; // 正式版
var isTest=true; // 测试版

var hostUrl="http://116.62.52.41:18081/";

var indexCommonUrls_test={
    newsUrl:hostUrl+"elasticsearch/top",
    loginUrl:hostUrl+"login"
};

var indexCommonUrls_real={
    newsUrl:"elasticsearch/top",
    loginUrl:"login"
};

var indexCommonUrls=indexCommonUrls_real;
if(isTest){
    indexCommonUrls=indexCommonUrls_test;
}
