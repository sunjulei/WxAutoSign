<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/30 0030
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>自动签到</title>
</head>
<body>
<%

%>
<h3>注意：请不要重复提交，也不要提交无用参数,感谢配合！</h3>
<form method="get" action="autosign" onsubmit="return toVal()">
    CookiePHPSESSID：<input type="text" name="cookieid" id="cookieid"><br/><br/>
    位置：<input placeholder="广州市天河区荷光路43号" type="text" name="local" id="local"><br/><br/>
    纬度：<input placeholder="113.379565" type="text" name="signx" id="signx" onkeyup="this.value=this.value.replace(/[^0-9/.]/g,'')"><br/><br/>
    经度：<input placeholder="22.137759" type="text" name="signy" id="signy" onkeyup="this.value=this.value.replace(/[^0-9/.]/g,'')"><br/><br/>
    <input type="submit" value="提交">
    <script type="text/javascript">
        function toVal() {
            var cookieid = document.getElementById("cookieid").value;
            var local = document.getElementById("local").value;
            var signx = document.getElementById("signx").value;
            var signy = document.getElementById("signy").value;
            if (cookieid.length == 26 && local != "" && signx != "" && signy != "") {
                alert("提交成功！");
                return true;
            }
            else {
                alert("请检查填写是否有误");
                return false;
            }
        }

    </script>
</form>
<br/>
<h3>
    1、CookiePHPSESSID获取方式：<br>
    <a href="http://sunlee.top:8080/packet.apk">http://sunlee.top:8080/packet.apk</a><br/>
    2、安装上述链接的安装包<br/>
    3、点击带数字的三角图标，选择微信<br/>
    4、切换到微信，点击一次 “我要签到”界面<br/>
    5、返回该软件，点击红色方块停止，<br/>
    6、点击下方的列表，可看到很多个微信的数据包,依次点开<br/>
    7、找到一个没有乱码的界面，然后找到 CookiePHPSESSID,复制后面的字符(如下图)<br/>
    <img src="view/eg.png" alt="">
</h3>


</body>

</html>
