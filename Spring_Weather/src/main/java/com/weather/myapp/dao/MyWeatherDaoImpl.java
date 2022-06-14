package com.weather.myapp.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.MyWeatherDTO;

@Repository
public class MyWeatherDaoImpl implements MyWeatherDao {

	@Autowired
	private SqlSession sqlSession;
	
	String ns = "weather.";

	@Override
	public int insertWeather(List<MyWeatherDTO> list) {
		int val = 0;
		
		try {
			val = sqlSession.insert(this.ns + "insert", list);
			System.out.println("insert success");
		} catch (Exception e) {
			System.out.println("insert error");
			e.printStackTrace();
		}
		
		return val;
	}
}
