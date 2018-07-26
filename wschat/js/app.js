(function (global,factory) {

    factory(global)

})(typeof(window) !== "undefined" ? window : this,function (window) {

    let JCoding={}
    let api={}

    JCoding.api=api

    JCoding.api.IP="localhost"
    JCoding.api.PORT="9090"

    getIp=obj=>{
        if (obj){
            console.log(this.$$)
            return this.JCoding.api.IP
        }
        return obj.ip==undefined?this.JCoding.IP:obj.ip;
    }

    getPort=obj=> {
        if (obj==undefined){
            return this.JCoding.api.PORT
        }
        return obj.port==undefined?this.JCoding.api.PORT:obj.port;
    }

    JCoding.api.wsUrl=obj=>{// /websockts/chatHadler
        return "ws://"+this.getIp(obj)+":"+this.getPort(obj)+obj.url;
    }

    JCoding.api.httpUrl=obj=>{
        return "http://"+this.JCoding.api.getIp(obj)+":"+this.JCoding.api.getPort(obj)+obj.url;
    }

    JCoding.WebSocket=function(obj){
        let socket=new WebSocket(obj.url)
        $$.log(socket)
        socket.onopen=obj.onOpen;
        socket.onclose=obj.onClose;
        socket.onmessage=obj.onReceive;
        socket.onerror=obj.onError;
        return function (txt) {
            socket.send(txt);
        }
    }

    JCoding.log=function(txt){
        console.log(txt)
    }

    window.$$ = window.JCoding=JCoding;
})