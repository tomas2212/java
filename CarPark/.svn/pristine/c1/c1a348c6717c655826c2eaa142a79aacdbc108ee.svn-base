<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>


<f:message key="companyAdmin.employees" var="title" />
<s:layout-render name="/layout.jsp" title="${title}" 
                 mainMenu="${actionBean.mainMenu}" subMenu="${actionBean.subMenu}">

    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.vozovypark.web.CompanyAdminActionBean" var="actionBean"/>
        <br/>
        <table>
            <thead>
                <tr>
                    <td>ID</td>
                    <td>Name</td>
                    <td>Position</td>
                    <td>Approve</td>
                    <td>Address</td>
                    <td>Company Level</td>
                    <td><s:label name="companyAdmin.edit" /></td>
                    <td><s:label name="companyAdmin.delete" /></td>
                </tr>
            </thead>
            <c:forEach items="${actionBean.allEmployees}" var="ae">
                <tr>
                    <td>${ae.id}</td>
                    <td><c:out value="${ae.name}" />
                    <td><c:out value="${ae.position}" />
                    <td><c:out value="${ae.approve}" />
                    <td><c:out value="${ae.address}" />
                    <td><c:out value="${ae.companyLevel.name}" />
                    <td><s:link beanclass="cz.muni.fi.pa165.vozovypark.web.CompanyAdminActionBean" event="editEmployee"><s:param name="employee.id" value="${ae.id}"/><s:label name="companyAdmin.edit" /></s:link> </td>
                    <td><s:link beanclass="cz.muni.fi.pa165.vozovypark.web.CompanyAdminActionBean" event="deleteEmployee"><s:param name="employee.id" value="${ae.id}"/><s:label name="companyAdmin.delete" /></s:link></td>
                    </tr>
            </c:forEach>  
        </table>
    </s:layout-component>
</s:layout-render>
