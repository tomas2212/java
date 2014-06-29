<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>


<f:message key="companyAdmin.employees" var="title" />
<s:layout-render name="/layout.jsp" title="${title}" 
                 mainMenu="${actionBean.mainMenu}" subMenu="${actionBean.subMenu}">
    
    <s:layout-component name="content">
     <table>
             <thead>
                    <tr>
                        <td>Name</td>
                        <td>Position</td>
                        <td>Approve</td>
                        <td>Address</td>
                        
                    </tr>
                </thead>
            <c:forEach items="${actionBean.allEmployees}" var="employee">
              <tr>
                    
                    <td>
                        <s:link beanclass="cz.muni.fi.pa165.vozovypark.web.CompanyAdminActionBean" event="editEmployee">
                    <s:param name="employee.id" value="${employee.id}" />
                        <c:out value="${employee.name}" />
                        </s:link>
                    </td>
                    <td> <c:out value="${employee.position}" /></td>
                    <td> <c:out value="${employee.approve}" /></td>
                    <td> <c:out value="${employee.address}" /></td>
                    
                </tr>
                 
            </c:forEach>  
        </table>
    </s:layout-component>
</s:layout-render>
