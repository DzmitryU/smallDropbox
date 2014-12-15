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
                <a href="#download" aria-controls="download" role="tab" data-toggle="tab">Download</a></li>
            <li role="presentation">
                <a href="#upload" aria-controls="upload" role="tab" data-toggle="tab">Upload</a></li>
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
                    <tr>
                        <td>1</td>
                        <td>Test 1</td>
                        <td>12.12.14</td>
                        <td>13480</td>
                        <td><button type="button" class="btn btn-sm btn-default">Download</button></td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Test 2</td>
                        <td>12.12.14</td>
                        <td>13480</td>
                        <td><button type="button" class="btn btn-sm btn-default">Download</button></td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>Test 3</td>
                        <td>12.12.14</td>
                        <td>13480</td>
                        <td><button type="button" class="btn btn-sm btn-default">Download</button></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div role="tabpanel" class="tab-pane" id="upload">
                <h1 class="text-center">Upload your files</h1>
            </div>
        </div>

    </div>
</div>

</body>
</html>