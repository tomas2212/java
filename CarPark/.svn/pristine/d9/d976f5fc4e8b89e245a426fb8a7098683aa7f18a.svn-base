<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<table>
    <tr>
        <th><s:label for="employeeName" name="companyAdmin.name"/></th>
        <td><s:text id="employeeName" name="employee.name"/></td>
    </tr>
    <tr>
        <th><s:label for="employeePosition" name="companyAdmin.position"/></th>
        <td><s:text id="employeePosition" name="employee.position" /></td>
    </tr>
    <tr>
        <th><s:label for="employeeLogin" name="companyAdmin.login"/></th>
        <td><s:text id="employeeLogin" name="employee.login" /></td>
    </tr>
    <tr>
        <th><s:label for="password" name="companyAdmin.password"/></th>
        <td><s:text id="password" name="password" /></td>
    </tr>
    <tr>
        <th><s:label name="companyAdmin.manager"/></th>
        <td><s:checkbox name="isManager" /></td>
    </tr>
    <tr>
        <th><s:label name="companyAdmin.carAdmin"/></th>
        <td><s:checkbox name="isCarAdmin" /></td>
    </tr>
    <tr>
        <th><s:label name="companyAdmin.sysAdmin"/></th>
        <td><s:checkbox name="isSysAdmin" /></td>
    </tr>
    <tr>
        <th><s:label for="employeeAddress" name="companyAdmin.address"/></th>
        <td><s:text id="employeeAddress" name="employee.address"/></td>
    </tr>
    <tr>
        <th><s:label for="employeeCL" name="companyAdmin.companyLevel"/></th>
        <td><s:select id="employeeCL" name="employee.companyLevel">
                <s:options-collection value="id" label="name" collection="${actionBean.allCompanyLevels}" />
            </s:select>
        </td>
    </tr>
</table>