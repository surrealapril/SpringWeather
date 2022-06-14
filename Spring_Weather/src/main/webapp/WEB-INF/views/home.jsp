<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/highchart.css">
<!-- grid ui -->
<link rel="stylesheet"
	href="https://uicdn.toast.com/grid/latest/tui-grid.css" />
<script src="https://uicdn.toast.com/grid/latest/tui-grid.js"></script>
<!-- chart ui -->
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>

<script src="${pageContext.request.contextPath}/resources/js/grid.js" defer></script>	
<script src="${pageContext.request.contextPath}/resources/js/chart.js" defer></script>
		

</head>
<body>
<div class="wrapper">
<div class="nav-grid">
시작일 선택 : <input type="date" name="beginDate" value="beginDate">
종료일 선택: <input type="date" name="endDate" value="endDate">
데이터 선택: <select name="dataPick" >
	<option value="tempHumi">온/습도</option>
</select>
<input type="button" value="새로고침"/>
<br>

<div class="" id="grid"></div>
</div>

<figure class="highcharts-figure">
  <div id="container"></div>
 </figure>
 
</div>


</body>
</html>
