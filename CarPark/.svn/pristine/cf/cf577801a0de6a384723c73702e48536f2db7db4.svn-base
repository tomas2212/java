<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>


<f:message key="companyAdmin.addCompanyLevel" var="title" />
<s:layout-render name="/layout.jsp" title="${title}" 
                 mainMenu="${actionBean.mainMenu}" subMenu="${actionBean.subMenu}">

    <s:layout-component name="content">
        <s:form beanclass="cz.muni.fi.pa165.vozovypark.web.CompanyAdminActionBean" >
            <s:hidden name="companyLevel.id"/>
            <fieldset><legend>New Company Level</legend>
                <br/>
                    <tr>
                        <th><s:label for="b1" name="company.level.name"/></th>
                        <td><s:text id="b1" name="companyLevel.name"/></td>
                        <s:submit name="createButtonCl" />
                    </tr>
            </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>
