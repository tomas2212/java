<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:message key="employeeSubMenu.employees" var="title" />
<s:layout-render name="/layout.jsp" title="${title}" 
                 mainMenu="${actionBean.mainMenu}" subMenu="${actionBean.subMenu}">

    <s:layout-component name="content">

        <s:form beanclass="cz.muni.fi.pa165.vozovypark.web.CompanyAdminActionBean">
            <s:hidden name="employee.id"/>
            
                <%@include file="form.jsp"%>
                <s:submit name="update">Uložit</s:submit>
                <s:submit name="storno">Storno</s:submit>
                
        </s:form>
    </s:layout-component>
</s:layout-render>
