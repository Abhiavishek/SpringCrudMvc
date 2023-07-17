package org.jsp.springmvccrud.controller;

import org.jsp.springmvccrud.dao.UserDao;
import org.jsp.springmvccrud.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @Autowired
	private UserDao dao;
    
    @RequestMapping("/open")
    public String openView(String view) {
    	return view;
    }
    @PostMapping(value="/register")
    public ModelAndView saveUser(@ModelAttribute User u, ModelAndView view) {
    	u = dao.saveUser(u);
    	view.addObject("msg", "user saved with Id : "+ u.getId());
    	view.setViewName("print");
    	return view;
    }
    
    @RequestMapping(value="/edit")
    public ModelAndView edit(@RequestParam int id, ModelAndView view) {
    	User u = dao.findUserById(id);
    	if(u!=null) {
    		view.addObject("u",u);
    		view.setViewName("update");
    		return view;
    	}
    	view.addObject("msg", "The Id is invalid");
    	view.setViewName("print");
    	return view;
    }

    @RequestMapping(value="/update")
    public ModelAndView update(@ModelAttribute User u, ModelAndView view) {
    	dao.updateUser(u);
    	view.addObject("msg", "user updated");
    	view.setViewName("print");
    	return view;
    }
    
    @RequestMapping("/verify-phone")
    public ModelAndView verifyUser(@RequestParam long phone, String password, ModelAndView view) {
    	
    	User u = dao.verifyUser(phone, password);
    	if(u!=null) {
    		view.addObject("u",u);
    		view.setViewName("view");
    		return view;
    	}
    	else {
    		view.addObject("msg", "Invalid Phone Number and Password");
    		view.setViewName("print");
    		return view;
    	}
    	
    }
    
    @RequestMapping("/verify-email")
    public ModelAndView verifyUser(@RequestParam String email, String password, ModelAndView view) {
    	
    	User u = dao.verifyUser(email, password);
    	if(u!=null) {
    		view.addObject("u",u);
    		view.setViewName("view");
    		return view;
    	}
    	else {
    		view.addObject("msg", "Invalid Email and Password");
    		view.setViewName("print");
    		return view;
    	}
    }
    
    @RequestMapping("/verify-id")
    public ModelAndView verifyUser(@RequestParam int id, String password, ModelAndView view) {
    	
    	User u = dao.verifyUser(id, password);
    	if(u!=null) {
    		view.addObject("u",u);
    		view.setViewName("view");
    		return view;
    	}
    	else {
    		view.addObject("msg", "Invalid Id and Password");
    		view.setViewName("print");
    		return view;
    	}
    }
    
    @RequestMapping("/delete")
    public ModelAndView DeleteUser(@RequestParam int id, ModelAndView view) {
    	
    	    boolean deleted = dao.deleteUser(id);
    		view.setViewName("print");
    		if(deleted) {
    			view.addObject("msg", "user deleted");
    			return view;
    		}
    	
    	else {
    		view.addObject("msg", "Cannot deleted as the Id is invalid");
    		return view;
    	}
    }
}
