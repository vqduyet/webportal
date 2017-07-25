package org.packt.personal.web.portal.controller;

import org.packt.personal.web.portal.model.form.Home;
import org.packt.personal.web.portal.model.form.PostMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Date;
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
        return initPost();
    }

    private List<PostMessage> initPost() {
        List<PostMessage> posts = new ArrayList<>();
        PostMessage post = new PostMessage();
        post.setSubject("Welcome!");
        post.setDatePosted(new Date());
        post.setPostedMsg("Hello visitor, feel free to post on my portal!");
        posts.add(post);
        return posts;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getIndex(Model model,
                           @ModelAttribute("posts")List<PostMessage> posts,
                           @ModelAttribute("postForm")PostMessage postForm){
        Home home = new Home();
        home.setMessage(initMessage());
        home.setQuote(initQuote());
        model.addAttribute("home", home);
        if(posts == null) posts = newPosts();
        if(validatePost(postForm)){
            postForm.setDatePosted(new Date());
            posts.add(postForm);
        }
        model.addAttribute("posts", posts);
        return "index";
    }

    @RequestMapping(value = "/index_redirect", method = RequestMethod.GET)
    public RedirectView updateView(){
        return new RedirectView("/webportal/pwp/index_update.html");
    }

    private boolean validatePost(PostMessage post) {
        try {
            if(post.getSubject().trim().length() == 0 || post.getPostedMsg().trim().length() == 0){
                return false;
            }
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    private String initQuote() {
        String quote = "Twenty years from now you will be more disappointed by the things ... -Mark Twain";
        return quote;
    }

    private String initMessage() {
        String message = "Having a positive outlook on life is a crucial part of finding inspiration. In the paragraph above, ...";
        return message;
    }

}
