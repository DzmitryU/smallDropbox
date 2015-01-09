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
            <li role="presentation">
                <a href="http://localhost:8080/download">Download</a></li>
            <li role="presentation" class="active">
                <a href="http://localhost:8080/upload">Upload</a>
            </li>
        </ul>


        <!-- Tab panes -->
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="upload">
                <h1 class="text-center">Upload your files</h1>

                <div id="upload-results">
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
            progress: function (e, data) {
                var progress = parseInt(data.loaded / data.total * 100, 10);
                $('#' + data.files[0].uploadID).css('width', progress + '%').html(data.files[0].name + ' ' + progress + '%');
            }
        }).on('fileuploadadd',
                function (e, data) {
                    var result = $('#upload-results');
                    $.each(data.files, function (index, file)
                    {
                        file.uploadID = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
                            var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
                            return v.toString(16);
                        });
                        var progressBar = $("<div></div>");
                        progressBar.addClass("progress-bar progress-bar-striped active");
                        progressBar.attr('role', 'progressbar');
                        progressBar.attr('aria-valuenow', '0');
                        progressBar.attr('aria-valuemin', '0');
                        progressBar.attr('aria-valuemax', '100');
                        progressBar.attr('id', file.uploadID);
                        var progress = $("<div></div>").addClass("progress").append(progressBar);
                        result.append(progress);
                    });
                });
    });
</script>

</body>
</html>