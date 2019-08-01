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

        <div>
            <%
                if (request.getAttribute("Book") != null) {
                    out.println("<div class=\"w3-panel w3-green w3-display-container w3-card-4 w3-round\">\n" +
                                " <h5>Book '" + request.getAttribute("Book") + "' added!</h5>\n" + "</div>");
                }
            %>
            <div class="w3-card-4">
                <div class="w3-container w3-light-blue">
                    <h2>Add book</h2>
                </div>

                <form method="post" method="post" class="w3-selection w3-light-grey w3-padding">
                    <label>Title:
                        <input type="text" name="title" class="w3-input w3-border w3-round-large"><br />
                    </label>
                    <label>Writer:
                        <input type="text" name="writer" class="w3-input w3-border w3-round-large"><br />
                    </label>
                    <label>Genre:
                        <select name="genre" class="w3-select w3-border w3-round-large">
                            <option>--выберите жанр--</option>
                            <option>CLASSIC</option>
                            <option>CRIME</option>
                            <option>FANTASY</option>
                            <option>HISTORICAL</option>
                            <option>HORROR</option>
                            <option>THRILLER</option>
                            <option>WESTERN</option>
                        </select><br />
                    </label><br />
                    <label>ISBN:
                        <input type="text" name="isbn" class="w3-input w3-border w3-round-large"><br />
                    </label>
                    <label>Publication date:
                        <input type="date" name="date" class="w3-input w3-border w3-round-large"><br />
                    </label>
                    <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Add book</button>
                    <button type="reset" class="w3-btn w3-green w3-round-large w3-margin-bottom">Reset data</button>
                </form>
            </div>
        </div>

        <div>
            <button class="w3-btn w3-round-large" onclick="location.href='/jlibrary/'">Back to main</button>
        </div>
    </body>
</html>