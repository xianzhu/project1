/**
 * Created by a88u on 2017/7/6.
 */
var testData=[
    {
        subList:[
            {
            title:"title 1-1",
                domId:"#accordion1-1",
                did:"accordion1-1",
            desc:"desc 1-1"
            },
            {
                title:"title 1-2",
                domId:"#accordion1-2",
                did:"accordion1-2",
                desc:"desc 1-2"
            },
            {
                title:"title 1-3",
                domId:"#accordion1-3",
                did:"accordion1-3",
                desc:"desc 1-3"
            }
        ]
    },
    {
        subList:[
            {
            title:"title 2-1",
                domId:"#accordion2-1",
                did:"accordion2-1",
            desc:"desc 2-1"
        },
            {
                title:"title 2-2",
                domId:"#accordion2-2",
                did:"accordion2-2",
                desc:"desc 2-2"
            },
            {
                title:"title 2-3",
                domId:"#accordion2-3",
                did:"accordion2-3",
                desc:"desc 2-3"
            },
            {
                title:"title 2-4",
                domId:"#accordion2-4",
                did:"accordion2-4",
                desc:"desc 2-4"
            }
        ]
    },
    {
        subList:[
            {
            title:"title 3-1",
                domId:"#accordion3-1",
                did:"accordion3-1",
            desc:"desc 3-1"
        },
            {
                title:"title 3-2",
                domId:"#accordion3-2",
                did:"accordion3-2",
                desc:"desc 3-2"
            },
            {
                title:"title 3-3",
                domId:"#accordion3-3",
                did:"accordion3-3",
                desc:"desc 3-3"
            }
        ]
    },
    {
        subList:[
            {
            title:"title 4-1",
                domId:"#accordion4-1",
                did:"accordion4-1",
            desc:"desc 4-1"
        },
            {
                title:"title 4-2",
                domId:"#accordion4-2",
                did:"accordion4-2",
                desc:"desc 4-2"
            }
        ]
    }
];


var index=0;
var srcData=[];
var v_containerModule=new Vue({
    el:"#containerModule",
    data:{
        allList:[]
    },
    methods:{
        changeData:function () {
            index=(index+1)%4;
            this.$data.allList=testData[index].subList;
            console.log(this.$data.allList);
            this.$nextTick(function () {
                bindCalendarItem();
            });
        }
    }
});


function bindCalendarItem(){
    var d = document,
        accordionToggles = d.querySelectorAll('.js-accordionTrigger'),
        setAria,
        setAccordionAria,
        switchAccordion,
        touchSupported = ('ontouchstart' in window),
        pointerSupported = ('pointerdown' in window);
console.log(accordionToggles);
    skipClickDelay = function(e){
        e.preventDefault();
        e.target.click();
    }

    setAriaAttr = function(el, ariaType, newProperty){
        el.setAttribute(ariaType, newProperty);
    };
    setAccordionAria = function(el1, el2, expanded){
        switch(expanded) {
            case "true":
                setAriaAttr(el1, 'aria-expanded', 'true');
                setAriaAttr(el2, 'aria-hidden', 'false');
                break;
            case "false":
                setAriaAttr(el1, 'aria-expanded', 'false');
                setAriaAttr(el2, 'aria-hidden', 'true');
                break;
            default:
                break;
        }
    };
//function
    switchAccordion = function(e) {
        console.log("triggered");
        e.preventDefault();
        closeAllAccordion(e);
        var thisAnswer = e.target.parentNode.nextElementSibling;
        var thisQuestion = e.target;
        if(thisAnswer.classList.contains('is-collapsed')) {
            setAccordionAria(thisQuestion, thisAnswer, 'true');
        } else {
            setAccordionAria(thisQuestion, thisAnswer, 'false');
        }
        thisQuestion.classList.toggle('is-collapsed');
        thisQuestion.classList.toggle('is-expanded');
        thisAnswer.classList.toggle('is-collapsed');
        thisAnswer.classList.toggle('is-expanded');

        thisAnswer.classList.toggle('animateIn');
    };

    closeAllAccordion=function(e){
        var parent=e.target.parentNode.parentNode.parentNode;
        var dtTargets=parent.querySelectorAll('dt a');
        for(var i=0,len=dtTargets.length;i<len;i++){
            var titem=dtTargets[i];
            if(titem!=e.target){
                var thisAnswer = titem.parentNode.nextElementSibling;
                var thisQuestion = titem;
                if(thisAnswer.classList.contains('is-collapsed')) {
                    setAccordionAria(thisQuestion, thisAnswer, 'true');
                } else {
                    setAccordionAria(thisQuestion, thisAnswer, 'false');
                }
                thisQuestion.classList.add('is-collapsed');
                thisQuestion.classList.remove('is-expanded');
                thisAnswer.classList.add('is-collapsed');
                thisAnswer.classList.remove('is-expanded');
                thisAnswer.classList.remove('animateIn');
            }
        }
    };
    for (var i=0,len=accordionToggles.length; i<len; i++) {
        if(touchSupported) {
            accordionToggles[i].addEventListener('touchstart', skipClickDelay, false);
        }
        if(pointerSupported){
            accordionToggles[i].addEventListener('pointerdown', skipClickDelay, false);
        }
        accordionToggles[i].addEventListener('click', switchAccordion, false);
    }
}

