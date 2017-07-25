package org.packt.personal.web.portal.controller;

import org.packt.personal.web.portal.model.form.PostMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

/**
 * Created by duyet.vu on 7/18/2017.
 */
@Controller
@RequestMapping("pwp")
@SessionAttributes("posts")
public class IndexController {

    @ModelAttribute("posts")
    public List<PostMessage> newPosts(){
        
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getIndex(Model model){
        model.addAttribute("greetings", "Welcome Page");
        return "index";
    }

}
