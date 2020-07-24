<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>타이틀</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<style>
#map{
	width: 800px; height: 600px;
}
</style>
<script>
function initMap() {
	var uluru = {lat:37.480316, lng:126.882771};
	var map = new google.maps.Map(document.getElementById('map'),{
		zoom : 17,
		center : uluru
	});
	var marker = new google.maps.Marker({
		position : uluru,
		map : map
	});
}

window.onload = function(){
	initMap();
}
</script>

</head>
<body>
	<h2>Google Map</h2>
	<div id="map"></div>
	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDhkjldDelM4TtHiCs3KzAB6ZXc3e3yMo4"></script>
</body>
</html>
