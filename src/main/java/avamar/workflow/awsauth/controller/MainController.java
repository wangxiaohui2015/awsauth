package avamar.workflow.awsauth.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import avamar.workflow.awsauth.module.SecretCode;
import avamar.workflow.awsauth.utils.KeysUtils;
import avamar.workflow.awsauth.utils.LogUtil;
import avamar.workflow.awsauth.utils.SecurityUtils;

/**
 * Main controller.
 * 
 * @author sunny
 */
@Controller
public class MainController {

	private static final Logger LOGGER = LogUtil.getMainLogger((MainController.class));

	@RequestMapping(value = "/login", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView login(HttpServletRequest request) {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		ModelAndView modAndView = new ModelAndView();
		if (SecurityUtils.checkUser(userName, password)) {
			List<SecretCode> secretCodes = KeysUtils.getAllSecretCode();
			modAndView.addObject("secretCodes", secretCodes);
			modAndView.setViewName("main");
			request.getSession().setAttribute("user", userName);
			LOGGER.info("Succeed to login with user: " + userName + ", password: " + password);
		} else {
			modAndView.addObject("loginResult", "-1");
			modAndView.addObject("userName", userName);
			modAndView.addObject("password", password);
			modAndView.setViewName("login");
			LOGGER.info("Failed to login with user: " + userName + ", password: " + password);
		}
		return modAndView;
	}
}
