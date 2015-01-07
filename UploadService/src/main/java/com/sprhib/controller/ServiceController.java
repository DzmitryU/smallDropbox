package com.sprhib.controller;

import com.sprhib.model.FileMeta;
import com.sprhib.service.FileService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class ServiceController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public @ResponseBody
    LinkedList<FileMeta> upload(HttpServletRequest request) {
        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
        }
        LinkedList<FileMeta> result = new LinkedList<FileMeta>();
        ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
        try {
            List<FileItem> items = uploadHandler.parseRequest(request);
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    FileMeta fileMeta = new FileMeta();
                    fileMeta.setName(item.getName());
                    fileMeta.setSize(item.getSize() / 1024 + " Kb");
                    fileMeta.setDate(new Date());
                    FileCopyUtils.copy(item.getInputStream(), new FileOutputStream("G:/temp/files/" + item.getName()));
                    fileService.addFile(fileMeta);
                    result.add(fileMeta);
                }
            }
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
