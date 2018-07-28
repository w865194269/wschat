
$("#chat_content").scrollTop($("#chat_content")[0].scrollHeight);

let wsOpen=function(evt){
    $$.log("onOpen")
}

let wsClose=function(evt){
    $$.log(evt);
};

let wsReceive=function (evt) {
    $$.log(evt.data);

    renderChatContent(
            {
                id: "bubble",
                info: evt.data,
                component: $("#chat_content")
            })
    $("#chat_content").scrollTop($("#chat_content")[0].scrollHeight);
}

let wsConnectError=function (evt) {
    console.log(evt);
}

let ws={
    url:$$.api.wsUrl({url:"/chatHadler"}),
    // url:$$.api.wsUrl({url:"/websockts/chatHadler"}),
    onOpen:wsOpen,
    onClose:wsClose,
    onReceive:wsReceive,
    onError:wsConnectError
};

let wsSend=$$.WebSocket(ws);

function renderChatContent(obj) {
    let tmp=$("#bubble_tmp").clone();
    tmp.attr("id",obj.id);
    tmp.css("display","block")
    tmp.find(".article").text(obj.info)
    obj.component.append(tmp)
}

$("#chat_send").on("click",function (evt) {

    let infos=$("#chat_info");
    if(infos.val().length>0){
        console.log($("#chat_info").val());
        //发送信息
        let info={}
        info.type=2;
        info.content=$("#chat_info").val();
        wsSend(JSON.stringify(info));
        //聊天内容记录框记录添加
        renderChatContent(
            {
                id:"bubble",
                info:infos.val(),
                component:$("#chat_content")
            }
        );
        infos.val("")
        $("#chat_content").scrollTop($("#chat_content")[0].scrollHeight);
    }
})