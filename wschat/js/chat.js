
$("#chat_content").scrollTop($("#chat_content")[0].scrollHeight);

let wsOpen=function(evt){
    $$.log("onOpen")
}

let wsClose=function(evt){
    $$.log(evt);
};

let wsReceive=function (evt) {
    let data=JSON.parse(evt.data);
    let code=data.type;
    $$.log(data.content)
    if (code==$$.status.MESSAGE_SEND_SELF_INFO){//用户登录之后获取自己的信息
        $$.wsUserId=data.content;
        $$.log("online-self "+$$.wsUserId)
    }
    if (code==$$.status.MESSAGE_SEND_ONLINE_LIST){//用上线获取在线列表
        $$.onLists=$$.onLists.concat(data.content);
        $$.log("lists +"+$$.onLists)
        renderOnLineList();
    }
    if (code==$$.status.MESSAGE_SEND_OFFLINE_NOTIFICATION){//新用户上线
        $$.onLists.splice(-1,data.content);
        renderOnLineList();
    }
    if (code==$$.status.MESSAGE_SEND_CHAT){//聊天信息
        renderChatContent(
            {
                id: "bubble",
                info: evt.data,
                component: $("#chat_content")
            })
        $("#chat_content").scrollTop($("#chat_content")[0].scrollHeight);
    }

}

let wsConnectError=function (evt) {
    console.log(evt);
}

function renderOnLineList() {
    $$.log("renderOnLineList "+$$.onLists)
    let component=$("#chat_right");
    component.empty();
    $$.onLists.forEach(function (item) {
        let tmp=$("#onlist_tmp").clone();
        tmp.attr("id",item);
        tmp.css("display","block")
        tmp.find(".online_item").text(item)
        if (item==$$.wsUserId){
            tmp.css("background","red");
        }
        component.append(tmp)
    })

}

function renderChatContent(obj) {
    let tmp=$("#bubble_tmp").clone();
    tmp.attr("id",obj.id);
    tmp.css("display","block")
    tmp.find(".article").text(obj.info)
    obj.component.append(tmp)
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