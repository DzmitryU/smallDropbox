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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class LinkController {

    @Autowired
    private FileService fileService;

	@RequestMapping(value="/")
	public ModelAndView mainPage() {
        ModelAndView modelAndView = new ModelAndView("home");
        List<FileMeta> files = fileService.getFiles();
        //Collections.sort(statuses);
        modelAndView.addObject("files", files);
        //modelAndView.addObject("status", new Status());
        return modelAndView;
	}

    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public @ResponseBody
    LinkedList<FileMeta> upload(HttpServletRequest request, HttpServletResponse response) {
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

        // result will be like this
        // [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
        return result;
    }
	
	@RequestMapping(value="/index")
	public ModelAndView indexPage() {
		return new ModelAndView("home");
	}
}
