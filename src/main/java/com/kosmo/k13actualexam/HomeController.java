package com.kosmo.k13actualexam;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import geolocation.MyFacilityDTO;
import geolocation.SearchRadiusImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	//1.내 위치값 알아내기
	@RequestMapping(value="/GeoLocation/01GeoLocation.do", method=RequestMethod.GET)
	public String getFunc1() {
		return "01GeoLocation/01GeoLocation";
	}
	
	//2. 구글맵 연동
	@RequestMapping(value="/GeoLocation/02GoogleMap.do", method=RequestMethod.GET)
	public String geoFunc2() {
		return "01GeoLocation/02GoogleMap";
	}
	
	//3. 구글맵에 내 위치 출력하기
	@RequestMapping(value="/GeoLocation/03MyLocation.do", method=RequestMethod.GET)
	public String geoFunc3() {
		return "01GeoLocation/03MyLocation";
	}
	//마이바티스를 사용하기 위한 자동 의존 주입
	@Autowired
	private SqlSession sqlSession;
	
	//내 위치기반 시설물 반경검색
	@RequestMapping(value="/GeoLocation/04SearchRadius.do", method=RequestMethod.GET)
	public String geoFunc4(Model model, HttpServletRequest req) {
		
		//폼값받기(반경, 위도, 경도)
		int distance = (req.getParameter("distance")==null) ?
				0 : Integer.parseInt(req.getParameter("distance"));
		double latTxt = (req.getParameter("latTxt")==null) ?
				0 : Double.parseDouble(req.getParameter("latTxt"));
		double lngTxt = (req.getParameter("lngTxt")==null) ?
				0 : Double.parseDouble(req.getParameter("lngTxt"));
		
		ArrayList<MyFacilityDTO> searchLists = null;
		if(distance!=0) {
			searchLists = sqlSession.getMapper(SearchRadiusImpl.class)
					.searchRadius(distance, latTxt, lngTxt);
		}
		
		model.addAttribute("searchLists", searchLists);
		
		return "01GeoLocation/04SearchRadius";
	}
	
}
