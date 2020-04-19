javascript:function filter()
{
    var pList = document.getElementsByTagName('A');
    for(var value of pList){
    	if(value.href == "http://android.myapp.com/myapp/detail.htm?apkName=com.dingtai.guangdianchenzhou") {
           value.style.display="none";
        }
    }

    document.getElementById('appbao-download-banner').style.display="none";
}

filter();