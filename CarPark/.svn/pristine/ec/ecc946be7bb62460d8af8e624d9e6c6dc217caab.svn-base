<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>

<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
<script>
    $(function() {
        $( "#datepicker" ).datepicker();
    });
</script>
<script>
    $(function() {
        $( "#datepicker2" ).datepicker();
    });
</script>

<table>
    <tr>
                    <th><s:label for="datepicker" name="start.date"/></th>
                    <td><s:text name="resDTO.dateFrom" id="datepicker" /></p></td>
                </tr>
                <br/>
                <tr>
                    <th><s:label for="datepicker2" name="end.date"/></th>
                    <td><s:text name="resDTO.dateTo" id="datepicker2" /></p></td>
                </tr>
         
                <br/>
                <tr>
                    <th><s:label for="b3" name="car"/></th>
                    <td><s:select name="resDTO.car">
                             <s:options-collection value="id" label="spz" collection="${actionBean.cars}" />
                             </s:select></td>
                </tr>
                <br/>
                <tr>
                    <th><s:label for="b4" name="employee"/></th>
                    <td><s:select name="resDTO.employee">
                            <s:options-collection value="id" label="name" collection="${actionBean.employees}" />
                            </s:select>
                    </td>
                </tr>
</table>