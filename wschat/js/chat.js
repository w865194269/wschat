
$("#chat_content").scrollTop($("#chat_content")[0].scrollHeight);

let wsOpen=function(evt){
    $$.log("onOpen")
}

let wsClose=function(evt){
    $$.log(evt);
};

let wsReceive=function (evt) {
    $$.log(evt.data);

    appendChat(
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
    url:$$.api.wsUrl({url:"/websockts/chatHadler"}),
    onOpen:wsOpen,
    onClose:wsClose,
    onReceive:wsReceive,
    onError:wsConnectError
};

let wsSend=$$.WebSocket(ws);

function appendChat(obj) {
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
        wsSend($("#chat_info").val());
        appendChat(
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