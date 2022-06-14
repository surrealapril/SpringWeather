package com.weather.myapp.dao;

import java.util.List;

import model.MyWeatherDTO;

public interface MyWeatherDao {
	
	int insertWeather(List<MyWeatherDTO> list);

}
