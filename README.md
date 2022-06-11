# Spring_Weather ( 22.06.07 ~ )
### 공공데이터 기상청 날씨 조회 API 를 이용한 온/습도 크롤링
#### Front-end: HTML5, CSS3, Javascript, Toast UI, Highchart, 기상청 단기예보 조회서비스 API
#### Back-end: Spring Framework 5.2.9 , JDK 1.8, Apache Tomcat 8.5, Maven 3.5.1, MyBatis 3.5.3
#### Database: MySQL

- 22.06.07 
  - 프로젝트 생성 및 초기 커밋
  - nav, grid (임시데이터) 완성
- 22.06.08 
  - Highchart (임시데이터) 완성
  - View 완성
- 22.06.09
  - Mybatis 연동
    - 문제 : driverClassName 라이브러리 이름 바뀜 -> `jdbc.driverClassName = com.mysql.cj.jdbc.Driver`
- 22.06.10 ~ 06.11
  - serviceKey 외부 파일로 관리하는 것에 대한 고민
    - 문제
      - apiKey가 null 로 log 찍힘 -> `properties/config.properties`처럼 properties의 상위 폴더 경로를 적어주니 해결
      - logger로는 apiKey를 제대로 읽어오는데 sample코드를 사용하면 `SERVICE_KEY_IS_NOT_REGISTERED_ERROR` -> 인코딩 키가 아닌 디코딩 키 사용하니 해결.