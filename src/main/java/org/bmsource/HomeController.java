package org.bmsource;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController
{
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(ModelMap model)
    {
        List<String> col = Arrays.asList(new String[] { "111", "222", "333" });
        model.put("list", col);
        return new ModelAndView("index", model);
    }
}
