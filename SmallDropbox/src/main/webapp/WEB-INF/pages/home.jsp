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
                    <c:forEach var="file" items="${files}">
                        <tr>
                            <td>${file.id}</td>
                            <td>${file.name}</td>
                            <td>${file.date}</td>
                            <td>${file.size}</td>
                            <td>
                                <button type="button" class="btn btn-sm btn-default">Download</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div role="tabpanel" class="tab-pane" id="upload">
                <h1 class="text-center">Upload your files</h1>

                <div class="progress">
                    <div class="progress-bar progress-bar-striped active" id="upload-progress" role="progressbar"
                         aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
                </div>
                <input id="file-upload" type="file" name="files[]" data-url="/upload" multiple/>
            </div>
        </div>

    </div>
</div>

<script src="/resources/js/jquery.ui.widget.js"></script>
<script src="/resources/js/jquery.iframe-transport.js"></script>
<script src="/resources/js/jquery.fileupload.js"></script>

<script>
    $(function () {
        $('#file-upload').fileupload({
            dataType: 'json',
            done: function (e, data) {
                $.each(data.result.files, function (index, file) {
                });
            },
            progressall: function (e, data) {
                var progress = parseInt(data.loaded / data.total * 100, 10);
                $('#upload-progress').css('width', progress + '%').html(progress + '%');
            }
        })
    });
</script>

</body>
</html>