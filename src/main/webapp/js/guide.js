/**
 * Created by a88u on 2017/2/14.
 */

$(function(){
    var AllHet = $(window).height();
    /*var mainHet= $('.floatCtro').height();
    var fixedTop = (AllHet - mainHet)/2
    //  $('div.floatCtro').css({top:fixedTop+'px'});*/
    var curVal=0;
    $('.nav-item').click(function(){
        var ind = $('.nav-item').index(this)+1;
        var topVal = $('#content0'+ind).offset().top;
        var curVal = $('.main-side').scrollTop();
        var posVal=topVal+curVal;
        console.log('#content0'+ind,topVal,curVal,posVal);
        $('.main-side').animate({scrollTop:posVal},1000);
    })
    $('.backTop').click(function(){
        $('.main-side').animate({scrollTop:0},1000)
    })
    $('.img-normal').on('click',function(){//点击图片放大。
    	var width = $(this).attr("width");
    	console.log(width);
    	if(width === "50%") {
    		$(this).attr("width","100%");
    	}else if(width === "100%") {
    		$(this).attr("width","50%");
    	}
    });
    $('.main-side').scroll(scrolls);
    scrolls();
    function scrolls(){
        var f1,f2,f3,f4,f5,f6,f7,f8,f9,f10;
        var f11,f12,f13,f14,f15,f16,f17,f18,f19,f20,f21;
        var bck;
        var fixRight = $('.nav-item');
        var blackTop = $('.backTop')
        var sTop = $('.main-side').scrollTop()+30;
        f1 = $('#content01').offset().top+$('#content01').height();
        f2 = $('#content02').offset().top+$('#content02').height();
        f3 = $('#content03').offset().top+$('#content03').height();
        f4 = $('#content04').offset().top+$('#content04').height();
        f5 = $('#content05').offset().top+$('#content05').height();
        f6 = $('#content06').offset().top+$('#content06').height();
        f7 = $('#content07').offset().top+$('#content07').height();
        f8 = $('#content08').offset().top+$('#content08').height();
        f9 = $('#content09').offset().top+$('#content09').height();
        f10 = $('#content010').offset().top+$('#content010').height();
        f11 = $('#content011').offset().top+$('#content011').height();
        f12 = $('#content012').offset().top+$('#content012').height();
        f13 = $('#content013').offset().top+$('#content013').height();
        f14 = $('#content014').offset().top+$('#content014').height();
        f15 = $('#content015').offset().top+$('#content015').height();
        f16 = $('#content016').offset().top+$('#content016').height();
        f17 = $('#content017').offset().top+$('#content017').height();
        f18 = $('#content018').offset().top+$('#content018').height();
        /*f19 = $('#content019').offset().top+$('#content019').height();
        f20 = $('#content020').offset().top+$('#content020').height();
        f21 = $('#content021').offset().top+$('#content021').height();*/

        //console.log(f1,f2,f3,f4);
        fixRight.removeClass('cur');

        if(f1>=0){
            fixRight.eq(0).addClass('cur');
        }
        else if(f2>=0){

            fixRight.eq(1).addClass('cur');
        }
        else if(f3>=0){
            fixRight.eq(2).addClass('cur');
        }
        else if(f4>=0){
            fixRight.eq(3).addClass('cur');
        }
        else if(f5>=0){
            fixRight.eq(4).addClass('cur');
        }
        else if(f6>=0){
            fixRight.eq(5).addClass('cur');
        }
        else if(f7>=0){
            fixRight.eq(6).addClass('cur');
        }
        else if(f8>=0){
            fixRight.eq(7).addClass('cur');
        }
        else if(f9>=0){
            fixRight.eq(8).addClass('cur');
        }
        else if(f10>=0){
            fixRight.eq(9).addClass('cur');
        }
        else if(f11>=0){
            fixRight.eq(10).addClass('cur');
        }
        else if(f12>=0){
            fixRight.eq(11).addClass('cur');
        }
        else if(f13>=0){
            fixRight.eq(12).addClass('cur');
        }
        else if(f14>=0){
            fixRight.eq(13).addClass('cur');
        }
        else if(f15>=0){
            fixRight.eq(14).addClass('cur');
        }
        else if(f16>=0){
            fixRight.eq(15).addClass('cur');
        }
        else if(f17>=0){
            fixRight.eq(16).addClass('cur');
        }
        else if(f18>=0){
            fixRight.eq(17).addClass('cur');
        }
        else if(f19>=0){
            fixRight.eq(18).addClass('cur');
        }
        else if(f20>=0){
            fixRight.eq(19).addClass('cur');
        }
        else if(f21>=0){
            fixRight.eq(20).addClass('cur');
        }
    }
})

