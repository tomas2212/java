<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-definition>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>${title}</title>
            <script src="http://code.jquery.com/jquery-1.8.2.js"></script>
            <script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
            
            <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css" />
        </head>
        <body>
            <div class="wrapper">
                <div class="header">
                    <h1>Car Park</h1>
                    <div style="text-align: right; color: black; font-size: 14px;">
                        <a href="<c:url value="/logout" />" > Logout</a>
                    </div>
                </div>
                <div class="mainMenu">
                    <ul>
                        <c:forEach items="${mainMenu.menuItems}" var="mainMenuItem">
                            <li>
                                <s:link href="${mainMenuItem.url}" class="${(mainMenuItem.active)? 'active': ''}">
                                    <f:message key="${mainMenuItem.name}"/>
                                </s:link>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="subMenu">
                    <ul>
                        <c:forEach items="${subMenu.menuItems}" var="subMenuItem">
                            <li>
                                <s:link href="${subMenuItem.url}" class="${(subMenuItem.active)? 'active': ''}">
                                    <f:message key="${subMenuItem.name}"/>
                                </s:link></li>
                            </c:forEach>
                    </ul>

                </div>
                <div class="content">
                    <h2>${title}</h2>
                    <s:layout-component name="content"/>
                </div>
                <div class="footer">© 2012</div>
            </div>
        </body>
    </html>
</s:layout-definition>
