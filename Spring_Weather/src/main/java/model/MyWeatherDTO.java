package model;

public class MyWeatherDTO {

	private int seq;
	private String insertDate; //DB넣는 날짜
	private String baseDate; // 발표일자
	private String baseTime; // 발표시각
	private String fcstDate; // 예측일자
	private String fcstTime; // 예측시간
	private String rehVal; // 습도값
	private String tmpVal; // 기온값
	private int nx; // 예보지점 X좌표
	private int ny; // 예보지점 Y좌표

	public MyWeatherDTO() {
	}
	
	

	public MyWeatherDTO(String baseDate, String baseTime, String fcstDate, String fcstTime, String rehVal,
			String tmpVal) {
		super();
		this.baseDate = baseDate;
		this.baseTime = baseTime;
		this.fcstDate = fcstDate;
		this.fcstTime = fcstTime;
		this.rehVal = rehVal;
		this.tmpVal = tmpVal;
	}


	public MyWeatherDTO(int seq, String insertDate, String baseDate, String baseTime, String fcstDate, String fcstTime,
			String rehVal, String tmpVal) {
		super();
		this.seq = seq;
		this.insertDate = insertDate;
		this.baseDate = baseDate;
		this.baseTime = baseTime;
		this.fcstDate = fcstDate;
		this.fcstTime = fcstTime;
		this.rehVal = rehVal;
		this.tmpVal = tmpVal;
	}


	public MyWeatherDTO(int seq, String insertDate, String baseDate, String baseTime, String fcstDate, String fcstTime,
			String rehVal, String tmpVal, int nx, int ny) {
		super();
		this.seq = seq;
		this.insertDate = insertDate;
		this.baseDate = baseDate;
		this.baseTime = baseTime;
		this.fcstDate = fcstDate;
		this.fcstTime = fcstTime;
		this.rehVal = rehVal;
		this.tmpVal = tmpVal;
		this.nx = nx;
		this.ny = ny;
	}




	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	public String getRehVal() {
		return rehVal;
	}

	public void setRehVal(String rehVal) {
		this.rehVal = rehVal;
	}

	public String getTmpVal() {
		return tmpVal;
	}

	public void setTmpVal(String tmpVal) {
		this.tmpVal = tmpVal;
	}

	public String getBaseDate() {
		return baseDate;
	}

	public void setBaseDate(String baseDate) {
		this.baseDate = baseDate;
	}

	public String getBaseTime() {
		return baseTime;
	}

	public void setBaseTime(String baseTime) {
		this.baseTime = baseTime;
	}

	public String getFcstDate() {
		return fcstDate;
	}

	public void setFcstDate(String fcstDate) {
		this.fcstDate = fcstDate;
	}

	public String getFcstTime() {
		return fcstTime;
	}

	public void setFcstTime(String fcstTime) {
		this.fcstTime = fcstTime;
	}

	public int getNx() {
		return nx;
	}

	public void setNx(int nx) {
		this.nx = nx;
	}

	public int getNy() {
		return ny;
	}

	public void setNy(int ny) {
		this.ny = ny;
	}

	@Override
	public String toString() {
		return "MyWeatherDTO [seq=" + seq + ", insertDate=" + insertDate + ", baseDate=" + baseDate + ", baseTime="
				+ baseTime + ", fcstDate=" + fcstDate + ", fcstTime=" + fcstTime + ", rehVal=" + rehVal + ", tmpVal="
				+ tmpVal + ", nx=" + nx + ", ny=" + ny + "]";
	}

	
	
	
}