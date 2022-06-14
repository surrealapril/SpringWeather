package com.weather.myapp.controller;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.weather.myapp.service.MyWeatherService;

import api.ApiExplorer;
import model.MyWeatherDTO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MyWeatherController {

	private static final Logger logger = LoggerFactory.getLogger(MyWeatherController.class);
	@Autowired
	MyWeatherService serv;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws IOException {
		logger.info("Welcome home! The client locale is {}.", locale);

		ApiExplorer ap = new ApiExplorer();
		List<MyWeatherDTO> list = new ArrayList<>();
		list = ap.getList();
		
		int val = serv.insertWeather(list);
		System.out.println("insert 결과 : " + val);
		return "home";
	}

}
