<%--
  Created by IntelliJ IDEA.
  User: xhj224
  Date: 2016/12/16
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>员工列表</title>
    <link href="${base }/style/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>员工信息</h1>
<hr/>

<table class='register'>
    <tr>
        <th>序号</th>
        <th>姓名</th>
        <th>职称</th>
        <th>薪资</th>
        <th>入职时间</th>
        <th>操作</th>
    </tr>
    <c:if test="${employees!=null && fn:length(employees)>0}" var="verifyEmp">
        <c:forEach items="${employees}" var="emp" varStatus="status">
            <tr>
                    <%--<td><input type="checkbox" name="deleteEmp" value="${emp.id}" title=""></td>--%>
                <td>${status.count}</td>
                <td><a href="${base}/emp/${emp.id}/updateEmp.do">${emp.name}</a></td>
                <td>${emp.title}</td>
                <td>${emp.salary}</td>
                <td><fmt:formatDate value="${emp.hiredate}"/></td>
                <td><a href="javascript:void(0);" onclick="deleteEmpById('${emp.id}')">删除</a></td>
            </tr>
        </c:forEach>
    </c:if>
    <c:if test="${!verifyEmp}">
        <tr>
            <th colspan='6'>暂无员工信息...</th>
        </tr>
    </c:if>
</table>
<input type="button" value="返回" onclick="history.go(-1);">
</body>
<script>
    function AjaxFunction() {
        var xmlHttp;  // 缓存XHR对象便于 Ajax 使用
        try {
            xmlHttp = new XMLHttpRequest(); // Opera 8.0+, Firefox, Safari
        } catch (e) {
            // Internet Explorer Browsers
            try {
                xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
            } catch (e) {
                try {
                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                } catch (e) {
                    // 错误处理
                    alert("Your browser broke!");
                }
            }
        }
        return xmlHttp;
    }

    function deleteEmpById(empId) {
        var xmlHttp = AjaxFunction();
        var url = "${base}/emp/" + empId + "/deleteEmpForAjax.do";
        xmlHttp.open("GET", url, true);
        xmlHttp.send();
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                // 获取响应中的文本
                var result = xmlHttp.responseText;
                if (result == 1) {
                    // table.deleteRow(rowsNumber(empId));
                    location.reload();
                }
            }
        }
    }
</script>
</html>
