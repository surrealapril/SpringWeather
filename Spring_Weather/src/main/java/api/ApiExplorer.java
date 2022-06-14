package api;

import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.MyWeatherDTO;

import java.io.BufferedReader;
import java.io.IOException;

public class ApiExplorer {
	private static final Logger logger = LoggerFactory.getLogger(ApiExplorer.class);

	public String requestUrl() {
		// config.properties api.key 불러오기
		// 컨테이너 생성
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		// 환경변수 관리 객체 생성
		ConfigurableEnvironment env = ctx.getEnvironment();
		// 프로퍼티 관리 객체 생성
		MutablePropertySources prop = env.getPropertySources();
		// 프로퍼티 관리 객체에 프로퍼티 파일 추가
		try {
			prop.addLast(new ResourcePropertySource("classpath:properties/config.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 프로퍼티 정보 얻기
		String apiKey = env.getProperty("api.key");
		logger.info("apiExplorer apikey : " + apiKey);

		try {
			String sUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";
			String pageNo = "1";
			String numOfRows = "1000";
			String dataType = "JSON";
			String baseDate = "20220614";
			String baseTime = "0500";
			String nx = "55";
			String ny = "127";

			StringBuilder urlBuilder = new StringBuilder(
					"http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"); /* URL */
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "="
					+ URLEncoder.encode(apiKey, "UTF-8")); /* Service Key */
			urlBuilder.append(
					"&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8")); /* 페이지번호 */
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
					+ URLEncoder.encode(numOfRows, "UTF-8")); /* 한 페이지 결과 수 */
			urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "="
					+ URLEncoder.encode(dataType, "UTF-8")); /* 요청자료형식(XML/JSON) Default: XML */
			urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "="
					+ URLEncoder.encode(baseDate, "UTF-8")); /* ‘21년 6월 28일 발표 */
			urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "="
					+ URLEncoder.encode(baseTime, "UTF-8")); /* 06시 발표(정시단위) */
			urlBuilder.append(
					"&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); /* 예보지점의 X 좌표값 */
			urlBuilder.append(
					"&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); /* 예보지점의 Y 좌표값 */
			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			// System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd;
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			conn.disconnect();
			logger.info(sb.toString());

			String result = sb.toString();

			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<MyWeatherDTO> getList() {
		ApiExplorer apEx = new ApiExplorer();
		String result = apEx.requestUrl();
		System.out.println("result: " + result);
		Gson gson = new GsonBuilder().setLenient().create();

		JsonParser jsonParser = new JsonParser();
		JsonObject jsonObject = jsonParser.parse(result).getAsJsonObject();
		JsonObject response = jsonObject.getAsJsonObject("response");
		JsonObject body = response.getAsJsonObject("body");
		JsonObject items = body.getAsJsonObject("items");
		JsonArray item = items.getAsJsonArray("item");
		
		
		Map<String, List<JsonObject>> map = new HashMap<>();
		// map 전처리
		for (Object object : item) {
			JsonObject objCate = (JsonObject) object;
			String key = objCate.get("category").getAsString();
			if (key.equals("REH") || key.equals("TMP")) {
				if (map.containsKey(key)) {
					List<JsonObject> list = map.get(key);
					list.add(objCate);
				} else {
					List<JsonObject> jsonlist = new ArrayList<>();
					jsonlist.add(objCate);
					map.put(key, jsonlist);
				}
			}
			//System.out.println("map : " + map.toString());
		}
		
		Iterator<Map.Entry<String, List<JsonObject>>> entries = map.entrySet().iterator();
		while(entries.hasNext()) {
			Map.Entry<String, List<JsonObject>> entry = entries.next();
			System.out.println("Key : "+ entry.getKey() + " , Value = " + entry.getValue());
		}
		
		List<MyWeatherDTO> list = new ArrayList<>();

		List<JsonObject> rehList = map.get("REH");
		List<JsonObject> tmpList = map.get("TMP");
		for (JsonObject tmp : tmpList) {
			for (JsonObject reh : rehList) {
				String fcstDate = reh.get("fcstDate").getAsString();
				String fcstTime = reh.get("fcstTime").getAsString().substring(0,2);
				String rehVal = reh.get("fcstValue").getAsString();
				if (fcstDate.equals(tmp.get("fcstDate").getAsString()) || fcstTime.equals(tmp.get("fcstTime").getAsString())) {
					String bDate = tmp.get("baseDate").getAsString();
					String bTime = tmp.get("baseTime").getAsString().substring(0,2);
					String tmpVal = tmp.get("fcstValue").getAsString();
					MyWeatherDTO dto = new MyWeatherDTO(bDate, bTime, fcstDate, fcstTime, rehVal, tmpVal);
					//System.out.println(dto.toString());
					list.add(dto);
				}
			}

		}
		
		return list;

	}
}