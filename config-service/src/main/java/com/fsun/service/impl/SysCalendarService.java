package com.fsun.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fsun.common.utils.DateUtil;
import com.fsun.common.utils.HttpPostUtil;
import com.fsun.domain.common.PageModel;
import com.fsun.domain.condition.SysCalendarCondition;
import com.fsun.domain.model.SysCalendar;
import com.fsun.domain.model.SysUser;
import com.fsun.mapper.SysCalendarMapper;
import com.fsun.service.SysCalendarApi;

@Service
public class SysCalendarService implements SysCalendarApi {
	
	@Value("${calendarurl}")
    private String calendarurl;
	
	@Autowired
	private SysCalendarMapper sysCalendarMapper;

	@Override
	public SysCalendar load(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysCalendar> list(SysCalendarCondition condition) {		
		return sysCalendarMapper.selectList(condition);
	}

	@Override
	public PageModel findPage(SysCalendarCondition condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save(SysCalendar domain, SysUser currentUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> synCalendar(String beginYear) {		
		List<String> synData = new ArrayList<>();
		int realBeginYear = 0;
		int newYear = DateUtil.getNowYear();
		if(beginYear == null || "".equals(beginYear)){
			realBeginYear = newYear;
		}else{
			realBeginYear = Integer.parseInt(beginYear);
		}
		for (int i = realBeginYear; i <= newYear + 1; i++) {
			List<String> resultData = this.synCurrYearCalendar(i + "");
			synData.addAll(resultData);
		}
		return synData;
	}
	
	
	
	/**************************     私有方法       *****************************/
	
	/**
	 * 同步当前年份下的日历
	 * @param currYear
	 * @return
	 */
	private List<String> synCurrYearCalendar(String currYear) {
		List<String> resultList = new ArrayList<>();
		for (int i = 1; i < 13; i++) {			 
			String currYM = i<10 ? (currYear + "0" + i) : (currYear + i);
			String url = calendarurl + "?d="+ currYM +"&back=json&info=1";
			String resultData = HttpPostUtil.get(url);			
			resultList.add(resultData);
			//延迟5秒
			try {
				Thread.currentThread().sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			//保存日历到数据库
			JSONObject monthJO = JSONObject.parseObject(resultData);
			JSONObject daysJO = (JSONObject) monthJO.get(currYM);
			//keySet()方法获取key的Set集合
			Set<String> keySet = daysJO.keySet();
			//对Set集合遍历
			for (String key : keySet) {			   
				this.synDbCalendar(daysJO.getJSONObject(key));			    
			}	
		}
		return resultList;
	}
	
	/**
	 * 同步数据库日历信息
	 * @param dayJO
	 */
	private void synDbCalendar(JSONObject dayJO){
		SysCalendar sysCalendar = new SysCalendar();
		sysCalendar.setId(dayJO.getString("day"));
		sysCalendar.setStatus(dayJO.getShort("status"));
		sysCalendar.setType(dayJO.getShort("type"));
		sysCalendar.setTypeName(dayJO.getString("typename"));			
		sysCalendar.setDay(dayJO.getString("day"));
		sysCalendar.setUnixTime(dayJO.getInteger("unixtime"));
		sysCalendar.setYearName(dayJO.getString("yearname"));
		sysCalendar.setLunarCalendarCn(dayJO.getString("nonglicn"));
		sysCalendar.setLunarCalendarEn(dayJO.getString("nongli"));
		sysCalendar.setZodiacCn(dayJO.getString("shengxiao"));
		sysCalendar.setSolarTerm(dayJO.getString("jieqi"));
		sysCalendar.setWeekCn(dayJO.getString("weekcn"));
		sysCalendar.setWeekFigures(dayJO.getString("week2"));
		sysCalendar.setWeekShortEn(dayJO.getString("week1"));
		sysCalendar.setWeekFullEn(dayJO.getString("week3"));
		sysCalendar.setDaySort(dayJO.getInteger("daynum"));
		sysCalendar.setWeekSort(dayJO.getInteger("weeknum"));
		sysCalendar.setAvoid(dayJO.getString("avoid"));
		sysCalendar.setSuit(dayJO.getString("suit"));
		//初始化特例日期
		this.initSpecialDay(sysCalendar);
		SysCalendar orgCalendar = sysCalendarMapper.selectByPrimaryKey(sysCalendar.getId());
		if(orgCalendar != null){
			sysCalendarMapper.updateByPrimaryKey(sysCalendar);
		}else{
			sysCalendarMapper.insertSelective(sysCalendar);
		}
	}

	/**
	 * 初始化特例日期(编码 10元旦 、20春节、 30清明节、 40劳动节、 50端午节、 60中秋节、 70国庆节 、 80调休、 100 调班   110 重合节日)
	 * @param sysCalendar
	 * @param dayJO
	 */
	private void initSpecialDay(SysCalendar sysCalendar){
		Short specialDayCode = null;
		String content = null;
		String day = sysCalendar.getDay();
		String weekFigures = sysCalendar.getWeekFigures();
		String lunarCalendarCn = sysCalendar.getLunarCalendarCn();
		int type = sysCalendar.getType();			
		if(type==2){
			//节日
			if(day.endsWith("0101")){
				specialDayCode = 10;
				content = "元旦";
				String specialDayContent = specialDayCoincide(lunarCalendarCn, content);
				if(specialDayContent != null){
					specialDayCode = 110;
					content = specialDayContent;
				}
			}else if(day.endsWith("0404")){
				specialDayCode = 30;
				content = "清明节";
				String specialDayContent = specialDayCoincide(lunarCalendarCn, content);
				if(specialDayContent != null){
					specialDayCode = 110;
					content = specialDayContent;
				}
			}else if(day.endsWith("0501")){
				specialDayCode = 40;
				content = "劳动节";
				String specialDayContent = specialDayCoincide(lunarCalendarCn, content);
				if(specialDayContent != null){
					specialDayCode = 110;
					content = specialDayContent;
				}
			}else if(day.endsWith("1001")){
				specialDayCode = 70;
				content = "国庆节";
				String specialDayContent = specialDayCoincide(lunarCalendarCn, content);
				if(specialDayContent != null){
					specialDayCode = 110;
					content = specialDayContent;
				}
			}else {
				if("正月初一".equals(lunarCalendarCn)){
					specialDayCode = 20;
					content = "春节";
				}
				if("五月初五".equals(lunarCalendarCn)){
					specialDayCode = 20;
					content = "端午节";
				}
				if("八月十五".equals(lunarCalendarCn)){
					specialDayCode = 20;
					content = "中秋节";
				}				
			}
		}else if(type==1){
			//假日
			if("1,2,3,4,5".contains(weekFigures)){
				specialDayCode = 80;
				content = "调休";
			}
		}else{
			//工作日
			if("6,7".contains(weekFigures)){
				specialDayCode = 100;
				content = "调班";
			}
		}
		sysCalendar.setSpecialDayCode(specialDayCode);
		sysCalendar.setContent(content);
	}
	
	/**
	 * 特例日是否存在重合
	 * @return
	 */
	private String specialDayCoincide(String lunarCalendarCn, String currSpecialDay){
		String specialDay = currSpecialDay.replace("节", "");
		if("正月初一".equals(lunarCalendarCn)){
			return "春节&" + specialDay;
		}
		if("五月初五".equals(lunarCalendarCn)){
			return "端午&" + specialDay;
		}
		if("八月十五".equals(lunarCalendarCn)){
			return "中秋&" + specialDay;
		}
		return null;
	}
}
