<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>

<script>
    var dateFormat = "M d yy";
    if(navigator.language == "sk"){
        dateFormat = "dd.mm.yy";
    }
    $(function() {
        $( "#datepicker" ).datepicker({dateFormat: dateFormat});
    });
    $(function() {
        $( "#datepicker2" ).datepicker({dateFormat: dateFormat});
    });
</script>



<table>
    <tr>
                    <th><s:label for="datepicker" name="reservation.start"/></th>
                    <td><s:text formatType="date" formatPattern="M d yy" name="resDTO.dateFrom" id="datepicker" /></p></td>
                </tr>
                <br/>
                <tr>
                    <th><s:label for="datepicker2" name="reservation.end"/></th>
                    <td><s:text formatType="date" formatPattern="M d yy" name="resDTO.dateTo" id="datepicker2" /></p></td>
                </tr>
         
                <br/>
                <tr>
                    <th><s:label for="b3" name="reservation.car"/></th>
                    <td><s:select name="resDTO.car">
                             <s:options-collection value="id" label="spz" collection="${actionBean.cars}" />
                             </s:select></td>
                </tr>
                <br/>
</table>