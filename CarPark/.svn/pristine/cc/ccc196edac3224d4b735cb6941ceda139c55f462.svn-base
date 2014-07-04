<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>


<f:message key="addCar" var="title" />
<s:layout-render name="/layout.jsp" title="${title}" 
                 mainMenu="${actionBean.mainMenu}" subMenu="${actionBean.subMenu}">

    <s:layout-component name="content">

        <s:form beanclass="cz.muni.fi.pa165.vozovypark.web.CarParkAdminActionBean">
            <s:hidden name="car.id"/>
          
                <%@include file="form.jsp"%>
                <s:submit name="create">Create</s:submit>
                <s:submit name="storno">Cancel</s:submit>
                
        </s:form>
    </s:layout-component>
</s:layout-render>
