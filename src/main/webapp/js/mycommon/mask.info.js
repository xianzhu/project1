/**
 * Created by a88u on 2016/10/28.
 */
var modal_mask_info = initModelMaskVue();

function initModelMaskVue(){
    Vue.component('modal_mask', {
        template: '#modal-template',
        props: {
            show: {
                type: Boolean,
                required: true,
                twoWay: true
            }
        }
    });
    //console.log("initModelMaskVue");
    v_model_mask_info=new Vue({
        el:"#v-model-mask-info",
        data:{
            showModal:false,
            information:"",
            header:""
        },
        methods:{
            openCalendarUrl:function (url) {
                openUrl(url);
                sendMonitor({url:url});
            }
        }
    });
    //console.log("model: ",v_model_mask_info);
    return v_model_mask_info;
}


function showInfo(header, infomation) {
    console.log(header,', ',infomation);
    modal_mask_info.$data.header = header;
    modal_mask_info.$data.information = infomation;
    modal_mask_info.$data.showModal = true;
}
