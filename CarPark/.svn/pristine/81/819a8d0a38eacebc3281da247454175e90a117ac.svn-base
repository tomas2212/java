<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>


<script>
    $(function() {
        $( "#datepicker" ).datepicker({dateFormat: "dd.MM.yyyy"});
    });
</script>
<script>
    $(function() {
        $( "#datepicker2" ).datepicker({dateFormat: "dd.MM.yyyy"});
    });
</script>

<table>
    <tr>
                    <th><s:label for="datepicker" name="start.date"/></th>
                    <td><s:text name="resDTO.dateFrom" formatType="datetime" formatPattern="dd.MM.yyyy" id="datepicker" /></p></td>
                </tr>
            
                <tr>
                    <th><s:label for="datepicker2" name="end.date"/></th>
                    <td><s:text  formatType="datetime" formatPattern="dd.MM.yyyy" name="resDTO.dateTo" id="datepicker2" /></p></td>
                </tr>
         
                <tr>
                    <th><s:label for="b3" name="car"/></th>
                    <td><s:select name="resDTO.car">
                             <s:options-collection value="id" label="spz" collection="${actionBean.cars}" />
                             </s:select></td>
                </tr>
               
                <tr>
                    <th><s:label for="b4" name="employee"/></th>
                    <td><s:select name="resDTO.employee">
                            <s:options-collection value="id" label="name" collection="${actionBean.employees}" />
                            </s:select>
                    </td>
                </tr>
</table>