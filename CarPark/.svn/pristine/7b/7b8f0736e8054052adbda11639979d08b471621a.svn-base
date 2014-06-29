<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>


<f:message key="carPark.release" var="title" />
<s:layout-render name="/layout.jsp" title="${title}" 
                 mainMenu="${actionBean.mainMenu}" subMenu="${actionBean.subMenu}">
    
    <s:layout-component name="content">
        <table>
            <thead>
                <tr>
                    <td>
                        <s:label name="car" />
                    </td>
                    <td>
                        <s:label name="employee" />
                    </td>
                    <td>
                       <s:label name="release" />
                    </td>
                </tr>
            </thead>
            <c:forEach items="${actionBean.carsToRelease}" var="reservation">
            <tr>
                
                <td>
                   <c:out value="${reservation.car.model}" />
                </td>
                 <td>
                   <c:out value="${reservation.employee.name}" />
                </td>
                <td><s:link event="releaseCar" beanclass="cz.muni.fi.pa165.vozovypark.web.CarParkAdminActionBean">
                        <s:param name="carId" value="${reservaticar.car.id}" />
                        <s:label name="release"/>
                    </s:link></td>
            </tr>
            </c:forEach>
        </table>
    </s:layout-component>
</s:layout-render>
