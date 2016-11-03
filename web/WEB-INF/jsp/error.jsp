<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: cx
  Date: 2016/10/01
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统异常信息</title>
</head>
<body>
     <s:if test="exception.errorMsg != '' && exception.errorMsg != null">
         <s:property value="exception.errorMsg"/>
     </s:if>
     <s:else>
         操作失败！<s:property value="exception.message"/>
     </s:else>
</body>
</html>
