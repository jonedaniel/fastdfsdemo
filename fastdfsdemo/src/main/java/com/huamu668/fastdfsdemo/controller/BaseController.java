package com.huamu668.fastdfsdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class BaseController {
    @Value("${server.context-path}")
    String ctx;

    @ModelAttribute
    public void InitModel(Model model) {
        model.addAttribute("ctx", ctx);
    }
}
