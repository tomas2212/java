<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>


 
<html>
  <head>
    <title>Login</title>
  </head>
 
  <body>
    <h1>Login</h1>
 
    <c:if test="${not empty param.login_error}">
      <font color="red">
      <f:message key="login.error"/><br/><br/>
      
      </font>
    </c:if>
 
    <form name="f" action="<c:url value='j_spring_security_check'/>" method="POST">
      <table>
        <tr><td><f:message key="companyAdmin.login"/> :</td><td><input type='text' name='j_username' value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/></td></tr>
        <tr><td><f:message key="companyAdmin.password"/> :</td><td><input type='password' name='j_password'></td></tr>
       
 
        <tr><td colspan='2'><input name="submit" type="submit" value="<f:message key="login.submit"/>"></td></tr>
   
      </table>
    </form>
  </body>
</html>