<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<script>
    $(function() {
         $( "#carYear" ).spinner({min:1900, max:3000});
    });
</script>
       
<s:errors/>
<table>
    <tr>
        <th><s:label for="carModel" name="car.model"/></th>
        <td><s:text id="carModel" name="car.model"/></td>
    </tr>
    <tr>
        <th><s:label for="carBrand" name="car.brand"/></th>
        <td><s:text id="carBrand" name="car.brand" /></td>
    </tr>
    <tr>
        <th><s:label for="carSPZ" name="car.spz"/></th>
        <td><s:text id="carSPZ" name="car.spz"/></td>
    </tr>
    <tr>
        <th><s:label for="carYear" name="car.creationYear"/></th>
        <td><s:text id="carYear" name="car.creationYear" formatType="date" formatPattern="yyyy" /></td>
    </tr>
    <tr>
        <th><s:label for="carCL" name="car.companyLevel"/></th>
        <td><s:select id="carCL" name="car.companyLevel">
                <s:options-collection value="id" label="name" collection="${actionBean.allCompanyLevels}" />
            </s:select>
        </td>
    </tr>
    <tr>
        <th><s:label for="carAvail" name="car.available"/></th>
        <td><s:checkbox name="car.available" /></td>
    </tr>
</table>