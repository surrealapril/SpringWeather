package com.weather.myapp.controller;

import java.sql.Connection;
import java.text.DateFormat;

import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Handles requests for the application home page.
 */
@Controller
public class MyWeatherController {
	
	private static final Logger logger = LoggerFactory.getLogger(MyWeatherController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@Inject
	private DataSource dataSource;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		try {
			Connection conn = (Connection) dataSource.getConnection();
			System.out.println("성공 : " + conn);
			
			} catch (Exception ex){
				System.out.println("실패..!");
				ex.printStackTrace();
			}
		
		return "home";
	}
	
	
}
