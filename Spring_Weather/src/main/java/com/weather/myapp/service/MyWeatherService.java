package com.weather.myapp.service;

import java.util.List;

import model.MyWeatherDTO;

public interface MyWeatherService {
	
	public int insertWeather(List<MyWeatherDTO> list);

}
