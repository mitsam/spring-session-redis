package com.lazyint.demo.spring_session_redis.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lazyint.demo.spring_session_redis.security.SecurityUtils;

@RestController
@RequestMapping("/api")
public class HomeController {
    @GetMapping("/resource1")
    public Map<String, String> getResource(HttpSession session) {
	Map<String, String> resource = new HashMap<String, String>();
	resource.put("resource", "here is some resource");
	System.out.println("session.getId()" + session.getId());
	session.setAttribute("abc", resource);
	return resource;
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/resource2")
    public Map<String, String> getResource2ndTime(HttpSession session) {
	Map<String, String> resource = (HashMap<String, String>) session.getAttribute("abc");
	System.out.println("resource... " + resource);
	System.out.println("session.getId()... " + session.getId());
	System.out.println("user... " + SecurityUtils.getCurrentUserLogin());
	System.out.println("user isAuthenticated... " + SecurityUtils.isAuthenticated());
	System.out.println("user isCurrentUserInRole... " + SecurityUtils.isCurrentUserInRole("ROLE_USER"));
	System.out.println("session in min... " + session.getMaxInactiveInterval() / 60);

	return resource;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(HttpSession session) {
	session.invalidate();
    }
}
