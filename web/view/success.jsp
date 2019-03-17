<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/10/23
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>提交成功</title>
</head>
<body>
<h3>自动签到提交成功！</h3>
<%=
session.getAttribute(request.getRemoteAddr())
%>
</body>
</html>
