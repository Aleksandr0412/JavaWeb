<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Таблица чисел</title>
</head>
<body>
<table>
    <%
        int number = Integer.parseInt(request.getParameter("number"));
        out.print("<tr>");
        for (int i = 1; i <= number; i++) {
            for (int j = 1; j <= number; j++) {
                out.print("<td>" + i + "-" + j + "</td>");
            }
            out.print("</tr>");
        }
        out.println();
    %>
</table>
</body>
</html>