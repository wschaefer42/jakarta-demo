<%@ page import="java.time.LocalDate" %><%--@elvariable id="message" type="String"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<jsp:useBean id="greeting" class="com.example.frontendweb.Greeter"/>
<h2>Welcome</h2>
<c:if test="message != ''">
    <p>Hello Nobody</p>
</c:if>
<ul>
    <c:forEach items="${greeting.languages}" var="lang">
        <li>${lang}</li>
    </c:forEach>
</ul>
<%
    LocalDate now = LocalDate.now();

%>
<p>I said: ${message}</p>
<p>${greeting.message}</p>
<p>Date: ${greeting.now}</p>
</body>
</html>
