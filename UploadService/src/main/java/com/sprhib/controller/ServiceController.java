package com.sprhib.controller;

import com.sprhib.model.FileMeta;
import com.sprhib.service.FileService;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class ServiceController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/")
    public String indexPage() {
        return "redirect:/upload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public ModelAndView mainPage() {
        return new ModelAndView("home");
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public
    @ResponseBody
    LinkedList<FileMeta> upload(HttpServletRequest request, HttpServletResponse response) {
        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
        }

        LinkedList<FileMeta> result = new LinkedList<FileMeta>();
        ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
        try {
            List<MultipartFile> items = ((DefaultMultipartHttpServletRequest) request).getFiles("files[]");
            for (MultipartFile item : items) {
                FileMeta fileMeta = new FileMeta();
                String fileName = ((CommonsMultipartFile) item).getFileItem().getName();
                String extension = "." + FilenameUtils.getExtension(fileName);
                fileMeta.setName(fileName);
                fileMeta.setSize(item.getSize() / 1024 + " Kb");
                fileMeta.setDate(new Date());
                FileCopyUtils.copy(item.getInputStream(), new FileOutputStream("G:/temp/files/" + fileMeta.getId() + extension));
                fileService.addFile(fileMeta);
                result.add(fileMeta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
