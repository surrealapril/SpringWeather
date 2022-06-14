package com.weather.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weather.myapp.dao.MyWeatherDao;

import model.MyWeatherDTO;

@Service
public class MyWeatherServImpl implements MyWeatherService {

	@Autowired
	private MyWeatherDao dao;

	@Override
	public int insertWeather(List<MyWeatherDTO> list) {		
		return this.dao.insertWeather(list);
	}
}
