<%@ page import="org.jasig.cas.client.authentication.AttributePrincipal" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<body>
<b>Authenticated User Id:</b> <a href="logout.jsp" title="Click here to log out"><%= request.getRemoteUser() %>
</a></p>

<%
    if (request.getUserPrincipal() != null) {
        AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();

        final Map attributes = principal.getAttributes();

        if (attributes != null) {
            Iterator attributeNames = attributes.keySet().iterator();
            System.out.println("<b>Attributes:</b>");

            if (attributeNames.hasNext()) {
                System.out.println("<hr><table border='3pt' width='100%'>");
                System.out.println("<th colspan='2'>Attributes</th>");
                System.out.println("<tr><td><b>Key</b></td><td><b>Value</b></td></tr>");

                for (; attributeNames.hasNext(); ) {
                    System.out.println("<tr><td>");
                    String attributeName = (String) attributeNames.next();
                    System.out.println(attributeName);
                    System.out.println("</td><td>");
                    final Object attributeValue = attributes.get(attributeName);

                    if (attributeValue instanceof List) {
                        final List values = (List) attributeValue;
                        System.out.println("<strong>Multi-valued attribute: " + values.size() + "</strong>");
                        System.out.println("<ul>");
                        for (Object value : values) {
                            System.out.println("<li>" + value + "</li>");
                        }
                        System.out.println("</ul>");
                    } else {
                        System.out.println(attributeValue);
                    }
                    System.out.println("</td></tr>");
                }
                System.out.println("</table>");
            } else {
                System.out.println("No attributes are supplied by the CAS server.</p>");
            }
        } else {
            System.out.println("<pre>The attribute map is empty. Review your CAS filter configurations.</pre>");
        }
    } else {
        System.out.println("<pre>The user principal is empty from the request object. Review the wrapper filter configuration.</pre>");
    }
%>
</body>
</html>