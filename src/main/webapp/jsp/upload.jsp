<%--
  Created by IntelliJ IDEA.
  User: mvcoder
  Date: 2020/4/15
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>上传文件 - Servlet2.5</title>
</head>
<body>
<h2>上传文件 - Servlet3.0</h2>
<form action="/upload/fileSave" method="post" enctype="multipart/form-data">
    <p>
        <label for="files">文件：</label>
        <input type="text" name="text" /><br/>
        <input type="file" name="files" id="files" multiple="multiple"/>
    </p>
    <p>
        <button type="submit">提交</button>
    </p>
</form>
</body>
</html>
