/**
 * Created by a88u on 2016/12/7.
 */

var count=10;

setTimeout(logcount,1000);

function logcount(){
    count--;
    // console.log(count);
    $("#second_count").html(count);
    if(count==0){
        gotoIndex();
    }else{
        setTimeout(logcount,1000);
    }
}

function gotoIndex(){
    window.location.href="index.html";
}