package com.sprhib.controller;

import com.sprhib.model.FileMeta;
import com.sprhib.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private FileService fileService;

    private ArrayList<String> uploadServices = new ArrayList<String>() {{
        add("http://localhost:8081/upload");
        add("http://localhost:8082/upload");
    }};

    private ArrayList<String> downloadServices = new ArrayList<String>() {{
        add("http://localhost:8083/download");
        add("http://localhost:8084/download");
    }};

	@RequestMapping(value="/")
	public String mainPage() {
        String server = downloadServices.remove(downloadServices.size() - 1);
        downloadServices.add(0, server);
        return "redirect:" + server;
	}

    @RequestMapping(value="/download", method = RequestMethod.GET)
    public String download(HttpServletRequest request, HttpServletResponse response) {
        String server = downloadServices.remove(downloadServices.size() - 1);
        downloadServices.add(0, server);
        return "redirect:" + server;
    }

    @RequestMapping(value="/upload", method = RequestMethod.GET)
    public String upload(HttpServletRequest request, HttpServletResponse response) {
        String server = uploadServices.remove(uploadServices.size() - 1);
        uploadServices.add(0, server);
        return "redirect:" + server;
    }
}
