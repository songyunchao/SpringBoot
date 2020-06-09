package com.fsun.domain.model;

public class SysCalendar {
    /**
     * 
     * 表字段 : sys_calendar.id
     */
    private String id;

    /**
     * 状态
     * 表字段 : sys_calendar.status
     */
    private Short status;

    /**
     * 日历类型(0:工作，1: 假日, 2:节日)
     * 表字段 : sys_calendar.type
     */
    private Short type;

    /**
     * 日历类型名称(0:工作日，1: 假日, 2:节日)
     * 表字段 : sys_calendar.type_name
     */
    private String typeName;

    /**
     * 例外日编码
     * 表字段 : sys_calendar.special_day_code
     */
    private Short specialDayCode;

    /**
     * 例外日名称
     * 表字段 : sys_calendar.content
     */
    private String content;

    /**
     * 当前日期(YYYYMMDD)
     * 表字段 : sys_calendar.day
     */
    private String day;

    /**
     * 时间戳
     * 表字段 : sys_calendar.unix_time
     */
    private Integer unixTime;

    /**
     * 干支纪年
     * 表字段 : sys_calendar.year_name
     */
    private String yearName;

    /**
     * 农历中文日期
     * 表字段 : sys_calendar.lunar_calendar_cn
     */
    private String lunarCalendarCn;

    /**
     * 农历日期
     * 表字段 : sys_calendar.lunar calendar_en
     */
    private String lunarCalendarEn;

    /**
     * 生肖
     * 表字段 : sys_calendar.zodiac_cn
     */
    private String zodiacCn;

    /**
     * 十二节气
     * 表字段 : sys_calendar.solar_term
     */
    private String solarTerm;

    /**
     * 周的中文
     * 表字段 : sys_calendar.week_cn
     */
    private String weekCn;

    /**
     * 周的阿拉伯数字
     * 表字段 : sys_calendar.week_figures
     */
    private String weekFigures;

    /**
     * 周的英文简称
     * 表字段 : sys_calendar.week_short_en
     */
    private String weekShortEn;

    /**
     * 周的英文全称
     * 表字段 : sys_calendar.week_full_en
     */
    private String weekFullEn;

    /**
     * 一年中的第几天
     * 表字段 : sys_calendar.day_sort
     */
    private Integer daySort;

    /**
     * 一年中的第几周
     * 表字段 : sys_calendar.week_sort
     */
    private Integer weekSort;

    /**
     * 忌
     * 表字段 : sys_calendar.avoid
     */
    private String avoid;

    /**
     * 宜
     * 表字段 : sys_calendar.suit
     */
    private String suit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public Short getSpecialDayCode() {
        return specialDayCode;
    }

    public void setSpecialDayCode(Short specialDayCode) {
        this.specialDayCode = specialDayCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day == null ? null : day.trim();
    }

    public Integer getUnixTime() {
        return unixTime;
    }

    public void setUnixTime(Integer unixTime) {
        this.unixTime = unixTime;
    }

    public String getYearName() {
        return yearName;
    }

    public void setYearName(String yearName) {
        this.yearName = yearName == null ? null : yearName.trim();
    }

    public String getLunarCalendarCn() {
        return lunarCalendarCn;
    }

    public void setLunarCalendarCn(String lunarCalendarCn) {
        this.lunarCalendarCn = lunarCalendarCn == null ? null : lunarCalendarCn.trim();
    }

    public String getLunarCalendarEn() {
        return lunarCalendarEn;
    }

    public void setLunarCalendarEn(String lunarCalendarEn) {
        this.lunarCalendarEn = lunarCalendarEn == null ? null : lunarCalendarEn.trim();
    }

    public String getZodiacCn() {
        return zodiacCn;
    }

    public void setZodiacCn(String zodiacCn) {
        this.zodiacCn = zodiacCn == null ? null : zodiacCn.trim();
    }

    public String getSolarTerm() {
        return solarTerm;
    }

    public void setSolarTerm(String solarTerm) {
        this.solarTerm = solarTerm == null ? null : solarTerm.trim();
    }

    public String getWeekCn() {
        return weekCn;
    }

    public void setWeekCn(String weekCn) {
        this.weekCn = weekCn == null ? null : weekCn.trim();
    }

    public String getWeekFigures() {
        return weekFigures;
    }

    public void setWeekFigures(String weekFigures) {
        this.weekFigures = weekFigures == null ? null : weekFigures.trim();
    }

    public String getWeekShortEn() {
        return weekShortEn;
    }

    public void setWeekShortEn(String weekShortEn) {
        this.weekShortEn = weekShortEn == null ? null : weekShortEn.trim();
    }

    public String getWeekFullEn() {
        return weekFullEn;
    }

    public void setWeekFullEn(String weekFullEn) {
        this.weekFullEn = weekFullEn == null ? null : weekFullEn.trim();
    }

    public Integer getDaySort() {
        return daySort;
    }

    public void setDaySort(Integer daySort) {
        this.daySort = daySort;
    }

    public Integer getWeekSort() {
        return weekSort;
    }

    public void setWeekSort(Integer weekSort) {
        this.weekSort = weekSort;
    }

    public String getAvoid() {
        return avoid;
    }

    public void setAvoid(String avoid) {
        this.avoid = avoid == null ? null : avoid.trim();
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit == null ? null : suit.trim();
    }
}