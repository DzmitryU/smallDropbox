<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>Small Dropbox</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.min.css"/>
    <link rel="stylesheet" href="/resources/css/main.css"/>
    <script src="/resources/js/jquery-2.1.1.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">

    <div id="app">

        <ul class="nav nav-tabs nav-justified">
            <li role="presentation" class="active">
                <a href="http://localhost:8080/download">Download</a></li>
            <li role="presentation">
                <a href="http://localhost:8080/upload">Upload</a></li>
        </ul>


        <!-- Tab panes -->
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="download">
                <h1 class="text-center">Download your files</h1>
                <table class="table table-striped table-hover table-bordered">
                    <thead>
                    <tr>
                        <td>UID</td>
                        <td>Name</td>
                        <td>Date</td>
                        <td>Size</td>
                        <td></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="file" items="${files}">
                        <tr id="${file.id}">
                            <td>${file.id}</td>
                            <td>${file.name}</td>
                            <td>${file.date}</td>
                            <td>${file.size}</td>
                            <td>
                                <a href="/download/${file.id}" onclick="removeFile('${file.id}')"
                                   type="button" class="btn btn-sm btn-default" download>Download</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script>
    function removeFile(id) {
        $('#' + id).remove();
    };
</script>
</body>
</html>