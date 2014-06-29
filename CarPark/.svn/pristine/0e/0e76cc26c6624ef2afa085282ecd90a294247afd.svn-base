<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>


<f:message key="companyAdmin.companyLevels" var="title" />
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
                <td>Level-value</td>
                <td>edit</td>
                <td><s:label name="delete" /></td>
            </tr>
            </thead>
            <c:forEach items="${actionBean.allCompanyLevels}" var="acl">
                <tr>
                    <td>${acl.id}</td>
                    <td><c:out value="${acl.name}" />
                    <td><c:out value="${acl.levelValue}" />
                    <td><s:link beanclass="cz.muni.fi.pa165.vozovypark.web.CompanyAdminActionBean" event="editCl"><s:param name="companyLevel.id" value="${acl.id}"/><s:label name="companyAdmin.edit" /></s:link> </td>
                    <td><s:link beanclass="cz.muni.fi.pa165.vozovypark.web.CompanyAdminActionBean" event="deleteCl"><s:param name="companyLevel.id" value="${acl.id}"/><s:label name="companyAdmin.delete" /></s:link></td>
                </tr>
            </c:forEach>            
        </table>
    </s:layout-component>
</s:layout-render>