/**
 * Created by a88u on 2016/8/7.
 */



//$('a.media').attr('href',path);

$(function() {
    var pdfDir="files/PDF/";
    var file = getUrlQueryStr('path',location.href);
    var path=pdfDir+file;

    $('a.media').attr('href',path);
    $('a.media').media({width:'100%', height:'100%'});
    //$('a.media').media();
});









