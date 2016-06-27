package com.yixin.js.common.utils;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	
    public static Date strToDate(String str, String formatStr)throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.parse(str);
    }

    public static String dateToStr(Date date,String formatStr)throws Exception{
        String returnStr = "";
        if(null!=date){
            SimpleDateFormat sdf =   new SimpleDateFormat(formatStr);
            returnStr = sdf.format(date);
        }
        return returnStr;
    }

    /**
     * 获取起始日期过后的几天
     * @param nowDate 起始日期
     * @param y 天数
     * @throws ParseException
     */
    public static String getNextDate(String nowDate,long y)throws ParseException  {
        SimpleDateFormat   sdf=new   SimpleDateFormat("yyyy-MM-dd");
        Calendar   cal=Calendar.getInstance();
        //如果不给定起算时间则从今天算起
        if(StringUtils.isEmpty(nowDate)){
            nowDate = sdf.format(new Date());
        }
        cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(nowDate));
        cal.add(Calendar.DAY_OF_YEAR,Long.valueOf(y).intValue());
        return sdf.format(cal.getTime());
    }

    /**
     * 获取周 信息
     * @param date 日期
     * @return 周
     */
    @SuppressWarnings("unused")
    public static String getWeek(Date date){
        String   weekStr;
        Calendar   calendar   =   Calendar.getInstance();
        calendar.setTime(date);
        int   week   =   calendar.get(Calendar.DAY_OF_WEEK)-1;
        weekStr = String.valueOf(week);
        return weekStr;
    }

    /**
     * 判断是否是 周六日
     * @param date 日期
     * @return boolean
     */
    @SuppressWarnings("unused")
    public static boolean isWeek(Date date){
        boolean   isWeek   =   false;
        Calendar   calendar   =   Calendar.getInstance();
        calendar.setTime(date);
        int   week   =   calendar.get(Calendar.DAY_OF_WEEK)-1;
        if(week == 0 || week == 6){
            isWeek = true;
        }
        return isWeek;
    }

    /**
     * 日期之差 天数
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return 天数
     */
    public static long getDates(String beginDate,String endDate){
        Date date1;
        Date date2;
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            date1 = df.parse(beginDate);
            date2 = df.parse(endDate);
            long days = date2.getTime()-date1.getTime();
            days = days/1000/60/60/24;
            return days;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 日期之差 天数
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return long 天数
     */
    public static long getDates(Date beginDate,Date endDate){
        try {
            String beginDateStr=dateToStr(beginDate,"yyyy-MM-dd");
            String endDateStr=dateToStr(endDate,"yyyy-MM-dd");
            return getDates(beginDateStr,endDateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 根据开始日期，结束日期计算出这段日期内的工作日天数（适用周六日休息）
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return int 工作日天数
     * @throws ParseException
     */
    @SuppressWarnings("unused")
    public static int get_work_day(String beginDate,String endDate) throws ParseException{
        int workDay=0;
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        try {
            start.setTime(format.parse(beginDate));
            end.setTime(format.parse(endDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(start.after(end)){
            return -1;
        }
        else{
            while(start.before(end))
            {
                if(start.get(Calendar.DAY_OF_WEEK)!=1&&start.get(Calendar.DAY_OF_WEEK)!=7){
                    workDay++;
                }
                start.add(Calendar.DAY_OF_MONTH,1);
            }
        }
        if(end.get(Calendar.DAY_OF_WEEK)!=1&& end.get(Calendar.DAY_OF_WEEK)!=7){
            workDay++;
        }
        System.out.println(workDay);
        return workDay;
    }

    /**
     * 根据开始日期，结束日期计算出这段日期内的休息天数（适用周六日休息）
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return int 假期
     * @throws ParseException
     */
    @SuppressWarnings("unused")
    public static int get_play_day(String beginDate,String endDate) throws ParseException{
        int playDay=0;
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        try {
            start.setTime(format.parse(beginDate));
            end.setTime(format.parse(endDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(start.after(end)){
            return -1;
        }
        else{
            while(start.before(end)){
                if(start.get(Calendar.DAY_OF_WEEK)==1||start.get(Calendar.DAY_OF_WEEK)==7){
                    playDay++;
                }
                start.add(Calendar.DAY_OF_MONTH,1);
            }
        }
        if(end.get(Calendar.DAY_OF_WEEK)==1||end.get(Calendar.DAY_OF_WEEK)==7){
            playDay++;
        }
        System.out.println(playDay);
        return playDay;
    }

    /**
     * 比较两个时间大小
     * @param beginDate 第一个日期
     * @param endDate 第二个日期
     * @return int -1 晚，1：早；0：时间相等
     */
    @SuppressWarnings("unused")
    public static int compare_date(String beginDate, String endDate) {
        return compare_date(beginDate, endDate,"yyyy-MM-dd");
    }
    /**
     * 比较两个时间大小
     * @param beginDate 第一个日期
     * @param endDate 第二个日期
     * @return int -1 晚，1：早；0：同一天
     */
    public static int compare_date(String beginDate, String endDate,String formatStr) {
        SimpleDateFormat format=new SimpleDateFormat(formatStr);
        try {
            Date firstDate = format.parse(beginDate);
            Date secondDate = format.parse(endDate);
            return compareDate(firstDate, secondDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    } 
    /**
     * 比较日期大小
     * @param firstDate 第一个日期
     * @param secondDate 第二个日期
     * @return int -1 晚，1：早；0：同一天
     */
    public static int compareDate(Date firstDate, Date secondDate) {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.setTime(firstDate);
        end.setTime(secondDate);
        if(start.after(end)){
            return -1;
        }else if(start.before(end)){
            return 1;
        }else{
            return 0;
        }
    }

    /**
     * 获取系统时间 yyyy-MM-dd
     */
    public static String getCurrentDate(){
        Date data=new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        return df.format(data);
    }

    /**
     * 获取系统时间 yyyy年MM月dd日
     */
    public static String getCurrentDateOne(){
        Date data=new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy年MM月dd日");
        return df.format(data);
    }

    /**
     * 当月第一天
     */
    public static String getFirstDay(){
        // 获取当月第一天和最后一天
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 获取前月的第一天
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return format.format(cale.getTime());

    }

    /**
     * 当月最后一天
     */
    @SuppressWarnings("unused")
    public static String getLastDay() {
        // 获取当前年份、月份、日期
        Calendar cale = Calendar.getInstance();
        // 获取当月第一天和最后一天
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 获取前月的最后一天
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return format.format(cale.getTime());
    }

    /**
     * 验证时间是否有效
     * @param dateStr 时间字符串
     * @param formatStr 时间格式化字符串样式
     */
    public static Boolean validateDateFormYear(String dateStr, String formatStr){
        boolean flag = true;
        try {
            Date date = strToDate(dateStr, formatStr);
            String getFormatDateStr = dateToStr(date, formatStr);
            if(!getFormatDateStr.equals(dateStr)){
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    @SuppressWarnings("unused")
    public static Boolean validateDateFormYear(String dateStr){
        boolean flag = true;
        if(dateStr.indexOf("-")>0){
            flag = validateDateFormYear(dateStr, "yyyy-MM-dd");
        }else if(dateStr.indexOf("/")>0){
            flag = validateDateFormYear(dateStr, "yyyy/MM/dd");
        }
        return flag;
    }

    public static String dateParse(String date) throws ParseException{
        String strDate[] = date.split("-");
        return strDate[0]+"年"+strDate[1]+"月"+strDate[2]+"日";
    }
//    public static void main(String[] args) {
//    	System.out.println(compare_date("2015-01-25 19:26", "2015-01-25 19:25:09", "yyyy-MM-dd HH:mm"));
//    	/*Date startDate = new Date();
//    	Date endDate = new Date();
//    	System.out.println(getBetweenSecondNumber(startDate, endDate));*/
//	}
    
    /**
     * 获取2个时间相隔几秒
     */
    public static int getBetweenSecondNumber(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return -1;
        }

        if (startDate.after(endDate)) {
            Date tmp = endDate;
            endDate = startDate;
            startDate = tmp;
        }
        long timeNumber = -1L;
        long TIME = 1000L;
        try {
            timeNumber = (endDate.getTime() - startDate.getTime()) / TIME;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return (int) timeNumber;
    }
}
