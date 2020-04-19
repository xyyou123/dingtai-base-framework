javascript:(function(){
    var objs = document.getElementsByTagName("img");
    var list = new Array();

    for(var i = 0; i < objs.length; i++) {
       list[i] = objs[i].src;
    }

    for(var i = 0;i < objs.length; i++) {
        objs[i].onclick = function() {
            window.look.openImage(this.src, list);
        }
    }
})()