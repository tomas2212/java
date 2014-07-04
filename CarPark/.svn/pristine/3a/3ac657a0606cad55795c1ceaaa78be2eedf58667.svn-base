<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:message key="companyAdmin.editCl" var="title" />
<s:layout-render name="/layout.jsp" title="${title}" 
                 mainMenu="${actionBean.mainMenu}" subMenu="${actionBean.subMenu}">

    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.vozovypark.web.CompanyAdminActionBean" var="actionBean"/>
        <s:form beanclass="cz.muni.fi.pa165.vozovypark.web.CompanyAdminActionBean">
            <s:hidden name="companyLevel.id"/>
            <s:errors/>
            <fieldset><legend><s:label name="companyAdmin.changeAttributes" /></legend>
                <table>
                    <tr>
                        <th><s:label for="b2" name="name"/></th>
                        <td><s:text id="b2" name="companyLevel.name"/></td>   
                    </tr>
                    <tr>
                        <th><s:label for="b2" name="levelValue"/></th>
                        <td><s:text id="b2" name="companyLevel.levelValue"/></td>
                    </tr>
                </table>             
                <s:submit name="saveButtonCl"/>
                <s:submit name="cancelButtonCl"/>
            </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>