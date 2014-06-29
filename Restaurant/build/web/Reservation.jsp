<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.Format"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Collection"%>
<%@page import="cz.muni.fi.pb138.restaurant.Reservation"%>
<%@page import="cz.muni.fi.pb138.restaurant.User"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cz.muni.fi.pb138.restaurant.Table"%>
<%@page import="java.util.List"%>
<%@page import="cz.muni.fi.pb138.restaurant.TableManager"%>
<%@page import="cz.muni.fi.pb138.restaurant.UserManager"%>
<%@page import="cz.muni.fi.pb138.restaurant.Manager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
    <head profile="http://gmpg.org/xfn/11">
        <title>Corporation</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <meta http-equiv="imagetoolbar" content="no" />
        <link rel="stylesheet" href="styles/layout.css" type="text/css" />
        <link rel="stylesheet" href="styles/bookingtable.css" type="text/css"/>
    </head>
    <body id="top">
        <% String name = (String) session.getAttribute("name");
                    String date = (String) request.getAttribute("date");
                    if (date == null) {
                        Date d = new Date();
                        Format formatter = formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String dat = formatter.format(d);
                        date = dat;
                    }
                    if (name == null || "".equals(name)) {%>
        <div class="corner">
            <p><a class="login" href="Registration.jsp">Log in</a> </p>
        </div>
        <% } else {%>
        <p class="login"> Hello <%=session.getAttribute("name")%>! </p>
        <p><a class="logout" href="${pageContext.request.contextPath}/LoginServlet?logout=true">Log out</a></p>
        <% }%>
        <div class="wrapper col1">
            <div id="head">
                <h1><a href="#">The IT Restaurant</a></h1>
                <p>School project</p>
                <div id="topnav">
                    <ul>
                        <li><a  href="index.jsp">Home</a></li>
                        <% if (name != null && !"".equals(name)) {%>
                        <li><a class ="active" href="Reservation.jsp">Reservation</a></li>
                        <li><a href="${pageContext.request.contextPath}/MyProfile">My Profile</a></li>
                        <% }%>
                    </ul>
                </div>
            </div>
        </div>
        <div class="wrapper col4">
            <div id="container">
                <div id="content">
                    <% if (request.getAttribute("error") != null) {%>
                    <font COLOR="red"> <h2> error: ${error}  </h2></font>
                    <% }%>
                    <% if (request.getAttribute("success") != null) {%>
                    <font COLOR="green"> <h2> ${success}  </h2></font>
                   
                    <% }%>
                    <div style="height:300px">
                        <table name="weekdays"><tr>
                                <td class="monday">Monday 08:00 - 22:00</td>
                                <td class="tuesday">Tuesday 08:00 - 22:00</td>
                                <td class="wednesday">Wednesday 08:00 - 22:00</td>
                                <td class="thursday">Thursday 08:00 - 22:00</td>
                                <td class="friday">Friday 08:00 - 22:00</td>
                                <td class="saturday">Saturday 08:00 - 22:00</td>
                                <td class="sunday">Sunday 08:00 - 22:00</td>
                            </tr>
                        </table>
                        <h1> Selected day : <strong>  <%= date%> </strong> </h1>

                        <div class="bookingtable">
                            <table>
                                <tr>
                                    <td>Table ID (Seats)</td>
                                    <td>00:00</td><td>01:00</td><td>02:00</td><td>03:00</td>
                                    <td>04:00</td><td>05:00</td><td>06:00</td><td>07:00</td><td>08:00</td><td>09:00</td>
                                    <td>10:00</td><td>11:00</td><td>12:00</td><td>13:00</td><td>14:00</td><td>15:00</td>
                                    <td>16:00</td><td>17:00</td><td>18:00</td><td>19:00</td><td>20:00</td>
                                    <td>21:00</td><td>22:00</td><td>23:00</td>
                                </tr>
                                <%
                                            Manager manager = new Manager();
                                            UserManager um = manager.getUm();
                                            TableManager tm = manager.getTm();
                                            ArrayList<Table> tables = (ArrayList<Table>) tm.allTables();
                                            User user = new User(true);
                                            String bgColor;
                                            String value;
                                            int[] times = new int[24];

                                            for (int i = 0; i < 24; i++) {
                                            }
                                %> 
                                <% for (int i = 0; i < tables.size(); i++) {%>
                                <tr>
                                    <% if (tables.get(i).isVip()) {
                                             bgColor = "orange";
                                         } else {
                                             bgColor = "white";
                                         }
                                    %>
                                    <td style="background-color:<%= bgColor%>"><%= tables.get(i).getTableId()%> ( <%= tables.get(i).getPlaces()%> )</td>
                                    <% for (int j = 0; j < 24; j++) {
                                             if (j < 8 || j > 21) {
                                                 bgColor = "lightgrey";
                                                 value = "x";
                                                 times[j] = 0;
                                             } else {
                                                 bgColor = "lightgreen";
                                                 String mow = (String) session.getAttribute("email");
                                                 Collection<Table> free = tm.freeTables(um.findUser(mow), date, j*60, 60);
                                                 if (free.contains(tables.get(i))) {
                                                     value = "o";
                                                     times[j] = 1;
                                                 } else {
                                                     bgColor = "red";
                                                     value = "o";
                                                     times[j] = 0;
                                                 }
                                             }

                                    %> <td style="background-color:<%= bgColor%>"><%= value%></td>
                                    <% }%>
                                </tr>
                                <% }%>

                            </table>
                            <table>
                                <tr>
                                    <td class="close" style="background-color:lightgrey;">x</td><td>Close</td>
                                    <td class="open" style="background-color:lightgreen;"></td><td>Open</td>
                                    <td class="booked" style="background-color:red;">o</td><td>Booked</td>
                                    <td class="vip" style="background-color:orange;"></td><td>VIP</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="reservation">
                        <br/><br/><br/> <br/> <br/> <br/> <br/> <br/><br/>
                        <form action="${pageContext.request.contextPath}/ReservationServlet" method="post" >
                            Day :<select name="day">

                                <% List<String> d = new ArrayList<String>();
                                            for (int i = 1; i <= 31; i++) {
                                                if (i < 10) {
                                                    d.add("0" + Integer.toString(i));
                                                } else {
                                                    d.add(Integer.toString(i));
                                                }
                                            }%>

                                <%if (d.isEmpty()) {%>
                                <option value="none"> -- </option>
                                <% }%>

                                <% for (int i = 0; i < d.size(); i++) {%>
                                <option value=<%= d.get(i)%>><%= d.get(i)%></option>
                                <% }%>
                            </select>

                            Month :<select name="month">

                                <% List<String> m = new ArrayList<String>();
                                            for (int i = 1; i <= 12; i++) {
                                                if (i < 10) {
                                                    m.add("0" + Integer.toString(i));
                                                } else {
                                                    m.add(Integer.toString(i));
                                                }
                                            }%>

                                <%if (m.isEmpty()) {%>
                                <option value="none"> -- </option>
                                <% }%>

                                <% for (int i = 0; i < m.size(); i++) {%>
                                <option value=<%= m.get(i)%>><%= m.get(i)%></option>
                                <% }%>
                            </select>
                            <input type="Submit" value="Check the day" />
                        </form> <br/>
                        <form action="${pageContext.request.contextPath}/ReservationServlet?book=true&date=<%= date%>" method="POST" >

                            Reserve for date <strong>  <%= date%> </strong> : <br/> <br/>
                            <!-- Firstname : <input type="text" name="firstname" value="" /> <br/> <br/>
                             Surname : <input type="text" name="surname" value="" /> <br/> <br/> -->

                            Table :
                            <select name="table">
                                <% for (int i = 0; i < tables.size(); i++) {%>
                                <option value=<%= tables.get(i).getTableId()%>><%= tables.get(i).getTableId()%></option>
                                <% }%>
                            </select>

                            Hour (for 60 minutes) :
                            <select name="time_hour">

                                <% List<String> s = new ArrayList<String>();
                                            for (int i = 8; i < times.length - 2; i++) {

                                                s.add(Integer.toString(i));

                                            }%>

                                <%if (s.isEmpty()) {%>
                                <option value="none"> -- </option>
                                <% }%>

                                <% for (int i = 0; i < s.size(); i++) {%>
                                <option value=<%= s.get(i)%>><%= s.get(i)%></option>
                                <% }%>
                            </select> 
                            <select name="time_minute">

                                <option value="0">00</option>

                            </select> <br/>

                            <input type="Submit" name="book" value="Book"/>
                        </form>
                    </div>
                </div>

                <div class="clear"></div>
            </div>
        </div>

        <div class="wrapper col5">
            <div id="footer">
                <!-- End Contact Form -->
                <div id="compdetails">
                    <div id="officialdetails">
                        <h2>IT Restaurant</h2>
                        <ul>
                            <li>For the school project</li>
                            <li class="last">PB138</li>
                        </ul>
                    </div>
                    <div id="contactdetails">
                        <h2>Our Contact Details !</h2>
                        <ul>
                            <li>IT Restaurant</li>
                            <li>Masaryk University</li>
                            <li>Brno</li>

                            <li>Email: </li>
                            <li class="last"> </li>
                        </ul>
                    </div>
                    <div class="clear"></div>
                </div>
                <!-- End Company Details -->
                <div id="copyright">
                    <p class="fl_left">Copyright &copy; 2010 - All Rights Reserved - <a href="#">Domain Name</a></p>
                    <p class="fl_right">Template by <a href="http://www.os-templates.com/" title="Open Source Templates">OS Templates</a></p>
                    <br class="clear" />
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </body>
</html>