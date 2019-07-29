<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add new book</title>
        <link rel="stylesheet" href="w3.css">
    </head>

    <body class="w3-light-grey">
        <div class="w3-container w3-blue-grey w3-opacity w3-right-align">
            <h1>Books service app</h1>
        </div>

        <div class="w3-container w3-padding">
            <%
                if (request.getAttribute("Book") != null) {
                    out.println("<div class=\"w3-panel w3-green w3-display-container w3-card-4 w3-round\">\n" +
                                " <h5>Book '" + request.getAttribute("Book") + "' added!</h5>\n" + "</div>");
                }
            %>
            <div class="w3-card-4">
                <div class="w3-container w3-center w3-green>
                    <h2>Add book</h2>
                </div>

                <form method="post" class="w3-selection w3-light-grey w3-padding">
                    <label>Title:
                        <input type="text" name="title" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
                    </label>
                    <label>Genre:
                        <input type="text" name="genre" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
                    </label>
                    <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Add book</button>
                </form>
            </div>
        </div>

        <div class="w3-container w3-grey w3-opacity w3-right-align w3-padding>
            <button class="w3-btn w3-round-large" onclick="location.href='/jlibrary/'">Back to main</button>
        </div>
    </body>
</html>