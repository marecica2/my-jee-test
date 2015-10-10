package org.bmsource.controller;

import javax.inject.Inject;

import org.bmsource.dao.UserDao;
import org.bmsource.model.a.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@PreAuthorize("permitAll")
@Controller
public class UserController {

	@Inject
	Validator validator;

	@Inject
	UserDao userDao;

	@Inject
	UserSession session;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration(ModelMap model) {
		User user = new User();
		model.addAttribute(user);
		return new ModelAndView("registration", model);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(ModelMap model) {
		User user = new User();
		model.addAttribute(user);
		return new ModelAndView("login", model);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginPost(@ModelAttribute("user") User user, BindingResult result, ModelMap model) {
		User usr = userDao.getByLogin(user.getLogin());
		if (usr == null) {
			result.addError(new FieldError("user", "login", "User does not exists"));
		} else if (!usr.getPassword().equals(user.getPassword())) {
			result.addError(new FieldError("user", "login", "Incorrect password"));
		} else {
			session.setUser(usr);
			return new ModelAndView("redirect:/", model);
		}
		return new ModelAndView("login", model);
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView add(@ModelAttribute("user") User user, BindingResult result, ModelMap model) {
		validator.validate(user, result);

		if (result.hasErrors()) {
			return new ModelAndView("registration", model);
		}
		userDao.create(user);
		return new ModelAndView("redirect:/", model);
	}

}
