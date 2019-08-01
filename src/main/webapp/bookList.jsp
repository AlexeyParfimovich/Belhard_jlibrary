<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Books list</title>
        <link rel="stylesheet" href="w3.css">
    </head>

    <body class="w3-light-grey">
        <div class="w3-container w3-blue-grey w3-opacity w3-right-align">
            <h1>Books service app</h1>
        </div>

        <div class="w3-container w3-margin-bottom w3-padding">
            <div class="w3-card-4">
                <div class="w3-container w3-light-blue">
                    <h2>Books</h2>
                </div>
                <%
                    List<String> books = (List<String>) request.getAttribute("Books");

                    if (books != null && !books.isEmpty()) {
                        out.println("<ui class=\"w3-ul\">");
                        for (String s : books) {
                            out.println("<li class=\"w3-hover-sand\">" + s + "</li>");
                        }
                        out.println("</ui>");
                    } else out.println("<div class=\"w3-panel w3-red w3-display-container w3-card-4 w3-round\">\n" +
                                        " <h5>There are no books yet!</h5>\n" + "</div>");
                %>
            </div>
        </div>

        <div>
            <button class="w3-btn w3-round-large" onclick="location.href='/jlibrary/'">Back to main</button>
        </div>
    </body>
</html>