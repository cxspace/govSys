<%--
  Created by IntelliJ IDEA.
  User: cx
  Date: 2016/09/29
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

  response.sendRedirect(basePath+"sys/login_toLoginUI.action");
%>