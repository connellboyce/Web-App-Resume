package com.connellboyce.webportfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {
    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/contact")
    public String getContact() {
        return "contact";
    }

    @GetMapping("/projects")
    public String getProjects() {
        return "projects";
    }

    @GetMapping("/skills")
    public String getSkills() {
        return "skills";
    }

    @GetMapping("/resume")
    public String getResume() {
        return "resume";
    }

    @GetMapping("/tools")
    public String getTools() {
        return "tools";
    }
}
