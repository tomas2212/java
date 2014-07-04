<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<table>
    <tr>
        <th><s:label for="employeeName" name="name"/></th>
        <td><s:text id="employeeName" name="employee.name"/></td>
    </tr>
    <tr>
        <th><s:label for="employeePosition" name="position"/></th>
        <td><s:text id="employeePosition" name="employee.position" /></td>
    </tr>
    <tr>
        <th><s:label for="employeeApprov" name="approved"/></th>
        <td><s:checkbox name="employee.approved" /></td>
    </tr>
    <tr>
        <th><s:label for="employeeAddress" name="address"/></th>
        <td><s:text id="employeeAddress" name="employee.address"/></td>
    </tr>
    <tr>
        <th><s:label for="employeeCL" name="companyLevel"/></th>
        <td><s:select id="employeeCL" name="employee.companyLevel">
                <s:options-collection value="id" label="name" collection="${actionBean.allCompanyLevels}" />
            </s:select>
        </td>
    </tr>
</table>