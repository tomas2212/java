<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>


<f:message key="carPark.cars" var="title" />
<s:layout-render name="/layout.jsp" title="${title}" 
                 mainMenu="${actionBean.mainMenu}" subMenu="${actionBean.subMenu}">

    <s:layout-component name="content">
        <table>
            <thead>
                <tr>
                    <td><s:label name="car.model" /></td>
                    <td><s:label name="car.brand" /></td>
                    <td><s:label name="car.spz" /></td>
                    <td><s:label name="car.creationYear" /></td>
                    <td><s:label name="car.companyLevel" /></td>
                    <td><s:label name="car.available" /></td>
                    <td><s:label name="car.delete" /></td>
                </tr>
            </thead>
            <c:forEach items="${actionBean.allCars}" var="car">
                <tr>

                    <td>
                        <s:link beanclass="cz.muni.fi.pa165.vozovypark.web.CarParkAdminActionBean" event="editCar">
                            <s:param name="car.id" value="${car.id}" />
                            <c:out value="${car.model}" />
                        </s:link>
                    </td>
                    <td> 
                        <s:link beanclass="cz.muni.fi.pa165.vozovypark.web.CarParkAdminActionBean" event="editCar">
                            <s:param name="car.id" value="${car.id}" />
                            <c:out value="${car.brand}" />
                        </s:link>

                    </td>
                    <td> 
                        <s:link beanclass="cz.muni.fi.pa165.vozovypark.web.CarParkAdminActionBean" event="editCar">
                            <s:param name="car.id" value="${car.id}" />
                            <c:out value="${car.spz}" />
                        </s:link>
                    </td>
                    <td>        
                        <s:link beanclass="cz.muni.fi.pa165.vozovypark.web.CarParkAdminActionBean" event="editCar">
                            <s:param name="car.id" value="${car.id}" />
                            <s:format formatPattern="dd.MM.YYYY" value="${car.creationYear}" />                            
                        </s:link>     
                    </td>
                    <td> 
                        <c:out value="${car.companyLevel.name}" />
                    </td>
                    <td class="available"><img align="center" src="${pageContext.request.contextPath}${(car.available)?'/images/available.png' :  '/images/unavailable.png'}"  /></td>
                    <td> <s:link beanclass="cz.muni.fi.pa165.vozovypark.web.CarParkAdminActionBean" event="deleteCar">
                            <s:param name="car.id" value="${car.id}" />
                            <img align="center" src="${pageContext.request.contextPath}/images/delete.png"  />
                        </s:link>
                    </td>
                </tr>
            </c:forEach>  
        </table>
    </s:layout-component>
</s:layout-render>
