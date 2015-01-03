package com.sprhib.controller;

import java.util.List;
import java.util.LinkedList;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sprhib.model.FileMeta;
import org.springframework.util.FileCopyUtils;
import java.io.IOException;
import java.io.FileOutputStream;

@Controller
@RequestMapping(value="/controller")
public class HomeController {

    LinkedList<FileMeta> files = new LinkedList<FileMeta>();
    FileMeta fileMeta = null;

    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public @ResponseBody
    LinkedList<FileMeta> upload(HttpServletRequest request, HttpServletResponse response) {
        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
        }
        ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
        try {
            List<FileItem> items = uploadHandler.parseRequest(request);
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    fileMeta = new FileMeta();
                    fileMeta.setFileName(item.getName());
                    fileMeta.setFileSize(item.getSize()/1024+" Kb");
                    fileMeta.setFileType(item.getContentType());
                    FileCopyUtils.copy(item.getInputStream(), new FileOutputStream("G:/temp/files/" + item.getName()));
                    files.add(fileMeta);
                }
            }
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // result will be like this
        // [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
        return files;
    }
}
