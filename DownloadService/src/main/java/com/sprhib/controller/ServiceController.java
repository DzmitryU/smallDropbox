package com.sprhib.controller;

import com.sprhib.model.FileMeta;
import com.sprhib.service.FileService;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class ServiceController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/")
    public String indexPage() {
        return "redirect:/download";
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ModelAndView mainPage() {
        ModelAndView modelAndView = new ModelAndView("home");
        List<FileMeta> files = fileService.getFiles();
        //Collections.sort(files);
        modelAndView.addObject("files", files);
        return modelAndView;
    }

    @RequestMapping(value = "/download/{file_id}", method = RequestMethod.GET)
    public void getFile(@PathVariable("file_id") String fileId, HttpServletResponse response) {
        try {
            FileMeta fileMeta = fileService.getFile(fileId);
            if (fileMeta == null) {
                response.setStatus(404);
            }
            String extension = "." + FilenameUtils.getExtension(fileMeta.getName());
            // get your file as InputStream
            String pathName = "G:/temp/files/" + fileMeta.getId() + extension;
            InputStream is = new FileInputStream(pathName);
            // copy it to response's OutputStream
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
            File file = new File("G:\\temp\\files\\" + fileMeta.getId() + extension);
            is.close();
            fileService.deleteFile(fileMeta.getId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
