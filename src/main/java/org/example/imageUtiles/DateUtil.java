package org.example.imageUtiles;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class DateUtil {

    /**
     * 默认的日期格式
     */
	public static String DEFAULT_YEAR_FORMAT = "yyyy";
	public static String DEFAULT_MONTH_FORMAT = "MM";
	public static String DEFAULT_DAY_FORMAT = "dd";
	public static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	public static String DEFAULT_YEAR_MONTH_FORMAT = "yyyy-MM";
	public static String DEFAULT_DATE_TIME_FORMAT ="yyyy-MM-dd HH:mm:ss";
	public static String PAYMENT_DATE_TIME_FORMAT ="yyyyMMddHHmmss";
	public static String PAYMENT_SHOT_DATE_TIME_FORMAT ="yyyyMMdd";
    public static String DEFAULT_DATE_FORMAT_1 = "yyyy年MM月dd日";
    public static String DATE_FOTMAT_SP = "yyyy/MM/dd";

    /**
	 * 取得当前日期
	 * @return Date 当前日期 包含日期
	 */
	public static Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * 返回日期是否在时间范围内
	 */
	public static boolean betweenAndStartEndDate(Date date, Date startDate,
												 Date endDate) {
		boolean isAfter = !date.before(startDate);
		boolean isBefore = !date.after(endDate);
		return isAfter && isBefore;
	}

    /**
	 * 取得当前日期
	 * @return Date 当前日期
	 */
	public static Date getCurrentDateNoTime() {
		return convertString2Date(getCurrentStringDate());
	}
	public static Date getDateAfterDays(Date date,int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, day);
		return cal.getTime();
	}
	public static Date getDateByDateAndDay(Date date,int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_YEAR, day);
		return cal.getTime();
	}
	public static Date getDateAfterMonths(Date date,int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, day);
		return cal.getTime();
	}
	public static Date getDateAfterMonthsFirstDay(Date date,int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, day);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}
	
	public static Date getDateAfterMinutes(Date date, int minute) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minute);
		return cal.getTime();
	}
	
	public static boolean isSameYearMonth(Date date1,Date date2) {
		if (date1 == null) date1 = new Date();
		if (date2 == null) date2 = new Date();
		String s1 = convertDate2String(date1, DEFAULT_YEAR_MONTH_FORMAT);
		String s2 = convertDate2String(date2, DEFAULT_YEAR_MONTH_FORMAT);
		return s1.equals(s2);
	}

	public static boolean isSameYearMonthDay(Date date1,Date date2) {
		if (date1 == null) date1 = new Date();
		if (date2 == null) date2 = new Date();
		String s1 = convertDate2String(date1, DEFAULT_DATE_FORMAT);
		String s2 = convertDate2String(date2, DEFAULT_DATE_FORMAT);
		return s1.equals(s2);
	}

	public static long getDayTime() {
		return 24*60*60*1000L ;
	}

	public static String getYear() {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_YEAR_FORMAT);
		return sdf.format(new Date());
	}
	public static String getDay() {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DAY_FORMAT);
		return sdf.format(new Date());
	}
    public static String getYear(Date date) {
    	SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_YEAR_FORMAT);
    	return sdf.format(date);
    }
    public static String getMonth(Date date) {
    	SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_MONTH_FORMAT);
    	return sdf.format(date);
    }
    /**
	 * 
	 * @param date
	 * @param num number of day
	 * @return
	 */
	public static Date getNextDatebyNumber(Date date, int num) {
		return new Date(date.getTime()+num*getDayTime()) ;
	}

    /**
	 * 返回当前日期对应的默认格式的字符串
	 * @return String 当前日期对应的字符串
	 */
	public static String getCurrentStringDate() {
		return convertDate2String(getCurrentDate(), DEFAULT_DATE_FORMAT);
	}


    /**
	 * 返回当前日期对应的指定格式的字符串
	 * @param dateFormat - 日期格式
	 * @return String 当前日期对应的字符串
	 */
    public static String getCurrentStringDate(String dateFormat) {
        return convertDate2String(getCurrentDate(), dateFormat);
    }
    
    public static String getCurrentStringTime() {
        return convertDate2String(getCurrentDate(), DEFAULT_DATE_TIME_FORMAT);
    }
    
    public static String getStringDateWithTime(Date date) {
        return convertDate2String(date, DEFAULT_DATE_TIME_FORMAT);
    }

    /**
	 * 将日期转换成指定格式的字符串
	 * @param date - 要转换的日期
	 * @param dateFormat - 日期格式
	 * @return String 日期对应的字符串
	 */
	public static String convertDate2String(Date date, String dateFormat) {
		SimpleDateFormat sdf = null;
		if (dateFormat != null && !dateFormat.equals("")) {
			try {
				sdf = new SimpleDateFormat(dateFormat);
			} catch (Exception e) {
				e.printStackTrace();
				sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
			}
		} else {
			sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		}
		return sdf.format(date);
	}

    /**
	 * 将日期转换成指定格式的字符串
	 * @param date - 要转换的日期
	 * @return String 日期对应的字符串
	 */
	public static String convertDate2String(Date date) {
		if(date==null) return "";
		SimpleDateFormat sdf = null;
		sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		return sdf.format(date);
	}

    /**
	 * 将字符串转换成日期
	 * @param stringDate - 要转换的字符串格式的日期
	 * @return Date 字符串对应的日期
	 */
	public static Date convertString2Date(String stringDate) {
		return convertString2Date(stringDate, DEFAULT_DATE_FORMAT);
	}

	public static String convertString2YearMonth(String stringDate) {
		if (Objects.isNull(stringDate)) {
			return stringDate;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM");
		String yearMonth = null;
		try {
			if('/'==stringDate.charAt(4)){
				yearMonth = format2.format(format.parse(stringDate));
			}else{
				yearMonth = format2.format(format2.parse(stringDate));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return yearMonth;
	}




    /**
	 * 将字符串转换成日期
	 * @param stringDate - 要转换的字符串格式的日期
	 * @param dateFormat - 要转换的字符串对应的日期格式
	 * @return Date 字符串对应的日期
	 */
	public static Date convertString2Date(String stringDate,
										  String dateFormat) {
		SimpleDateFormat sdf = null;
		if (dateFormat != null && !dateFormat.equals("")) {
			try {
				sdf = new SimpleDateFormat(dateFormat);
			} catch (Exception e) {
				e.printStackTrace();
				sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
			}
		} else {
			sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		}
		try {
			return sdf.parse(stringDate);
		} catch (ParseException pe) {
			pe.printStackTrace();
			return null;
		}
	}

    /**
	 * 将一种格式的日期字符串转换成默认格式的日期字符串
	 * @param oldFormat - 要格式化的日期的格式
	 * @return String 格式化后的日期字符串
	 */
	public static String formatStringDate(String oldStringDate,
										  String oldFormat) {
		return convertDate2String(convertString2Date(oldStringDate, oldFormat),
				DEFAULT_DATE_FORMAT);
	}

    /**
	 * 将一种格式的日期字符串转换成另一种格式的日期字符串
	 * @param oldFormat - 要格式化的日期的格式
	 * @param newFormat - 格式化后的日期的格式
	 * @return String 格式化后的日期字符串
	 */
    public static String formatStringDate(String oldStringDate, 
                                          String oldFormat, String newFormat) {
        return convertDate2String(convertString2Date(oldStringDate, oldFormat), 
                                  newFormat);
    }
    //获取当月,返回格式 yyyy-MM
    public static String getCurretnYearMonth() {
    	String current = getCurrentStringDate();
    	return current.substring(0,7);
    }

    //获取指定日期中月份的第一天,返回格式 yyyy-MM-dd
    public static String getMonthFirstDay() {
    	String current = getCurrentStringDate();
    	return current.substring(0,7)+"-01";
    }
    
    
    //获取指定日期中月份的第一天,返回格式 yyyy-MM-dd
    public static String getMonthFirstDay(Date date) {
    	String dateStr =  convertDate2String(date, DEFAULT_DATE_FORMAT);
    	return dateStr.substring(0,7)+"-01";
    }
   
    

    //获取月第一天,返回格式 yyyy-MM-dd
    public static Date getMonthFirstDayByDate(Date date) {
    	if (date == null) {
    		return null;
    	}
    	 Calendar cal = Calendar.getInstance();
         cal.setTime(date);
         cal.set(Calendar.DAY_OF_MONTH, 1);    
         return cal.getTime();
    }
  //获取当月最后一天,返回格式 yyyy-MM-dd
    public static Date getMonthLastDayByDate(Date date) {
    	Date lDate = DateUtil.getDateAfterMonths(date,1);//上个月
    	lDate = DateUtil.getMonthFirstDayByDate(lDate); //取第一天
    	lDate = DateUtil.getDateAfterDays(lDate, -1);
    	return lDate;
    }
 
	/** 
	 * 
	 * 如monthString 2008-01 ，则返回 2008-02.
	 * @return
	 */
	public static String getNextMonthString(String monthString) {
		int year = getYearByMonthString(monthString);
		int month = getMonthByMonthString(monthString);
		if (month==12) {
			return String.valueOf(year+1)+"-"+"01";
		} else if( (month+1) <=9) {
			return String.valueOf(year)+"-0"+String.valueOf(month+1);
		} else {
			return String.valueOf(year)+"-"+String.valueOf(month+1);
		}
	}
	/**
	 *
	 * @param monthString 2007-03 OR 2007-12
	 * @return
	 */
	public static String getLastMonthString(String monthString) {
		int year = getYearByMonthString(monthString);
		int month = getMonthByMonthString(monthString);
		if (month==1) {
			return String.valueOf(year-1)+"-"+"12";
		} else if (month <=10) {
			return String.valueOf(year)+"-0"+String.valueOf(month-1);
		} else {
			return String.valueOf(year)+"-"+String.valueOf(month-1);
		}
	}
	
	/** 
	 * 
	 */
	public static String getYearLastMonth(Date date) {
		int year = getYearByMonthString(DateUtil.convertDate2String(date, DEFAULT_YEAR_MONTH_FORMAT));
		return String.valueOf(year)+"-"+"12";
	}
	
	/**
	 *
	 * @param monthString 2007-03 OR 2007-12
	 * @return
	 */
	public static Date getStartDateByMonthString(String monthString) {
		int year = getYearByMonthString(monthString);
		int month = getMonthByMonthString(monthString);
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(year, month-1, 1) ;
		return cal.getTime();
	}


	/**
	 *
	 * @param monthString 2007-03 OR 2007-12
	 * @return
	 */
	public static int getYearByMonthString(String monthString) {
		if (monthString == null || monthString.length() <7) return 0;
		return Integer.parseInt(monthString.substring(0,4)) ;
	}
	public static int getDaysBetween(String dateString1,String dateString2) {
		Date date1=convertString2Date(dateString1);
		Date date2=convertString2Date(dateString2);
		if(date1==null)date1=getCurrentDateNoTime();
		if(date2==null)date2=getCurrentDateNoTime();
		return (int) ((date2.getTime()- date1.getTime()) / (1000 * 60 * 60 * 24));
	}
	public static int getDaysBetween(Date date1,Date date2) {
		if(date1==null)date1=getCurrentDateNoTime();
		if(date2==null)date2=getCurrentDateNoTime();
		String dateString1=convertDate2String(date1);
		String dateString2=convertDate2String(date2);
		return getDaysBetween(dateString1,dateString2);
	}

	/**
	 * 返回月的天数
	 * @param date
	 * @return
	 */
	public static int getMonthDays(Date date) {
		if (date == null) {
			return 0;
		}
		return getDaysBetween(DateUtil.getDateAfterMonthsFirstDay(date, 0) ,DateUtil.getDateAfterMonthsFirstDay(date, 1));
	}

	/**
	 *
	 * @param monthString 2007-03 OR 2007-12
	 * @return
	 */
	public static int getMonthByMonthString(String monthString) {
		if (monthString == null || monthString.length() <7) return 0;
		return Integer.parseInt(monthString.substring(5,7)) ;
	}

	public static int getMonthsBetween(String fromMonth, String toMonth) {
		if (Objects.isNull(fromMonth)) {
			fromMonth = DateUtil.getCurretnYearMonth();
		}
		if (Objects.isNull(toMonth)) {
			toMonth = DateUtil.getCurretnYearMonth();
		}
		return 1+( Integer.parseInt(toMonth.substring(0,4))*12+Integer.parseInt(toMonth.substring(5)))
				-(Integer.parseInt(fromMonth.substring(0,4))*12+Integer.parseInt(fromMonth.substring(5)));
	}

	public static Date convertStringToDateForAll(String time) {
		String separator_yM = "";
		String separator_Md = "";
		String separator_dH = "";
		String yyyy = "";
		String MM = "";
		String dd = "";
		String HH = "";
		Pattern pattern = Pattern
				.compile("(\\d{4})([^\\d]?)(\\d{1,2})([^\\d]?)(\\d{1,2})([^\\d]?)(\\d*)$");
		Matcher matcher = pattern.matcher(time);
		if (matcher.find()) {
			// System.out.println(matcher.group(0));
			yyyy = matcher.group(1);
			separator_yM = matcher.group(2);
			MM = matcher.group(3);
			separator_Md = matcher.group(4);
			dd = matcher.group(5);
			separator_dH = matcher.group(6);
			HH = matcher.group(7);
			// System.out.println(yyyy + separator_yM + MM + separator_Md + dd + separator_dH + HH);
		}

		if(yyyy == null || yyyy.equals("")
				|| MM == null || MM.equals("")
				|| dd == null || dd.equals("")) {
			return null;
		}

		String formateStr = "yyyy" + separator_yM + repeat("M", MM.length()) + separator_Md + repeat("d", dd.length()) + separator_dH + repeat("H", HH.length());
		//System.out.println("formateStr->" + formateStr);

		SimpleDateFormat formate = new SimpleDateFormat(formateStr);
		formate.setLenient(false);
		Date result = null;
		try {
			result = formate.parse(time);
		} catch (ParseException e) {
		}
		return result;

	}
	public static String repeat(String s,int counts) {
		int len = s.length();
		StringBuilder builder = new StringBuilder(len * counts);
		for(int i=0; i<counts; i++){
			builder.append(s);
		}
		return builder.toString();
	}

	public static int getDayByDate(Date date) {
		String s = convertDate2String(date);
		return Integer.parseInt(s.substring(8));
	}

	public static final Map<Integer, Character> charMap = new HashMap<Integer, Character>();
	public static final Pattern p = Pattern.compile("^(\\d+)\\D*(\\d*)\\D*(\\d*)\\D*(\\d*)\\D*(\\d*)\\D*(\\d*)");

	static {
		charMap.put(1, 'y');
		charMap.put(2, 'M');
		charMap.put(3, 'd');
		charMap.put(4, 'H');
		charMap.put(5, 'm');
		charMap.put(6, 's');
	}

	/**
	 * 任意格式转换
	 * @param dateString
	 * @return
	 */
	public static Date convertString2DateAnyFmt(String dateString) {
		dateString = dateString.trim().replaceAll("[a-zA-Z]"," ");
		if(Pattern.matches("^[-+]?\\d{13}$",dateString)) {//支持13位时间戳
			return new Date(Long.parseLong(dateString));
		}
		Matcher m = p.matcher(dateString);
		StringBuilder sb = new StringBuilder(dateString);
		if (m.find(0)) {//从被匹配的字符串中，充index = 0的下表开始查找能够匹配pattern的子字符串。m.matches()的意思是尝试将整个区域与模式匹配，不一样。
			int count = m.groupCount();
			for (int i = 1; i <= count; i++) {
				for (Map.Entry<Integer, Character> entry : charMap.entrySet()) {
					if (entry.getKey() == i) {
						sb.replace(m.start(i), m.end(i), replaceEachChar(m.group(i), entry.getValue()));
					}
				}
			}
		} else {
			System.out.println("错误的日期格式");
			return null;
		}
		String format = sb.toString();
		SimpleDateFormat sf = new SimpleDateFormat(format);
		try {
			return sf.parse(dateString);
		} catch (ParseException e) {
			System.out.println("日期字符串转Date出错");
			e.printStackTrace();
			return null;
		}
	}
	public static String replaceEachChar(String s, Character c) {
		StringBuilder sb = new StringBuilder("");
		for (Character c1 : s.toCharArray()) {
			if (c1 != ' ') {
				sb.append(String.valueOf(c));
			}
		}
		return sb.toString();
	}

	public static String convertSecent(Integer takeUpTime) {
		if (takeUpTime == null) {
			return "";
		}
		if ( takeUpTime < 60) {
			return " 1 分钟内";
		} else if ( takeUpTime < 60*60) {
			return (takeUpTime / 60) + "分钟";
		} else if ( takeUpTime < 24*60*60) {
			Integer h = (takeUpTime / (60*60));
			return  h + "小时" + (takeUpTime - h*60*60 )/60+"分钟";
		} else {
			Integer d = (takeUpTime / (24*60*60));
			return  d + "天" + (takeUpTime - d*24*60*60 )/(60*60)+"小时";
		}
	}

	public static String convertDate2StringRefNow(Date date) { // 只到分钟
		if (date == null) {
			return null;
		}
		Date now = getCurrentDate();
		if (convertDate2String(now).equals(convertDate2String(date))) {
			if (date.getHours() != now.getHours()) {
				return (now.getHours() - date.getHours())+" 小时前";
			} else if (date.getMinutes() != now.getMinutes()) {
				return (now.getMinutes() - date.getMinutes())+" 分钟前";
			} else {
				int n = (now.getSeconds()- date.getSeconds());
				if (n == 0) {
					n = 1;
				}
				return n+" 秒前";
			}
		} else if (convertDate2String(now).equals(convertDate2String(getDateAfterDays(date,1)))) {// 昨天
			return "昨天 "+convertDate2String(date,"HH:mm") ;

		} else if (date.getYear() == now.getYear())  { // 不是今年
			return convertDate2String(date,"MM月dd日 HH:mm");
		} else {
			return convertDate2String(date,"yyyy年MM月dd日 HH:mm");
		}
	}

	/**
	 * 设置课程时长格式
	 * @param seconds 时长， 单位：秒
	 * @return 格式化为时分秒
	 * @author yqcao
	 * @date 2020/5/22
	 */
	public static String lessonNumFormat(Integer seconds) {

		if(Objects.isNull(seconds)){
			return null;
		}
		if (seconds<60) {
			return seconds+"秒";
		}else if (seconds>60&&seconds<3600) {
			int m = seconds/60;
			int s = seconds%60;
			return m+"分"+s+"秒";
		}else {
			int h = seconds/3600;
			int m = (seconds%3600)/60;
			int s = (seconds%3600)%60;
			return h+"小时"+m+"分"+s+"秒";
		}

	}

	/**
	 * 日期加一天
	 * @param date 日期
	 * @return date 日期+1天
	 * @author yqcao
	 * @date 2020/5/29
	 */
	public static String endDateAddone(String date) {

		if(Objects.isNull(date)) {
			return null;
		}
		Date endDateFormat = convertString2Date(date, DEFAULT_DATE_FORMAT);
		endDateFormat = getDateAfterDays(endDateFormat, 1);
		date = convertDate2String(endDateFormat);
		return date;
	}
	
    public static Date getDayEnd(Date date) {  
    	if (Objects.isNull(date)) {
    		return null;
    	}
        Calendar calendar = Calendar.getInstance(); 
        calendar.setTime(date); 
        calendar.set(Calendar.HOUR_OF_DAY, 23);   
        calendar.set(Calendar.MINUTE, 59);  
        calendar.set(Calendar.SECOND, 59);  
        return calendar.getTime();  
    }
    
	/**
	 * 获取当年的第一天
	 */
	public static Date getCurrYearFirst() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearFirstDay(currentYear);
	}

	/**
	 * 获取当年的最后一天
	 */
	public static Date getCurrYearLast() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearLastDay(currentYear);
	}

	/**
	 * 获取某年第一天日期
	 */
	public static Date getYearFirstDay(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}

	/**
	 * 获取某年最后一天日期
	 */
	public static Date getYearLastDay(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();
		return currYearLast;
	}
	
	/**
	 * 获取某年第一天日期
	 */
	public static Date getYearFirstDay(String year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, Integer.parseInt(year));
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}

	/**
	 * 获取某年最后一天日期
	 */
	public static Date getYearLastDay(String year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, Integer.parseInt(year));
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();
		return currYearLast;
	}
	

	/**
	 * 获取指定日期的季度第一天和最后一天
	 * 输入 "2021-03"
	 * 返回: [2021-01-01 , 2021-04-01]  
	 */

	public static Date[] getSeasonRangeByDate(Date date) {
		int quarter = getQuarterOfYear(date);
		String ds = DateUtil.getYear(date);
		String nds = DateUtil.getYear(DateUtil.getDateAfterMonths(date, 12));
		Date sd = null;
		Date ed = null;
		if (quarter == 1) {
			sd = DateUtil.convertString2Date(ds + "-01-01");
			ed = DateUtil.convertString2Date(ds + "-04-01");
		} else if (quarter == 2) {
			sd = DateUtil.convertString2Date(ds + "-04-01");
			ed = DateUtil.convertString2Date(ds + "-07-01");
		} else if (quarter == 3) {
			sd = DateUtil.convertString2Date(ds + "-07-01");
			ed = DateUtil.convertString2Date(ds + "-10-01");
		} else if (quarter == 4) {
			sd = DateUtil.convertString2Date(ds + "-10-01");
			ed = DateUtil.convertString2Date(nds + "-01-01");
		}
		Date[] s = new Date[] { sd, ed };
		return s;
	}
	
	/**
	 * 返回指定日期的季度
	 */
	public static int getQuarterOfYear(Date date) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    return calendar.get(Calendar.MONTH) / 3 + 1;
	}
	
	/**
	 * 获取某年第一天日期
	 */
	public static Date getYearFirstDay(Date d) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, Integer.parseInt(DateUtil.getYear(d)));
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}

	/**
	 * 获取某年最后一天日期
	 */
	public static Date getYearLastDay(Date d) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, Integer.parseInt(DateUtil.getYear(d)));
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();
		return currYearLast;
	}
	
	/**
	 * 将日期转换成中文年月日
	 * 如：2020-02-15 转换成  2020年2月15日
	 * @param date
	 * @return
	 */
	public static String getDateInstanceToString(Date date) {
		DateFormat df2 =  DateFormat.getDateInstance(DateFormat.LONG, Locale.CHINA);
		return df2.format(date);
	}
	
  	public static String jsDateBetweenTimes( Date fromDate, Date toDate ) {
  		if (fromDate == null || toDate == null ) {
  			return " null 日期， 不计算!";
  		}
  		return convertSecent( (int) ((toDate.getTime() - fromDate.getTime())/1000));
  	}    
    
	public static int getSecondBetween(Date fromDate,Date toDate) {
	    if (fromDate == null || toDate == null) {
	    	return 0;
	    }
	    return (int) ((toDate.getTime()- fromDate.getTime()) /  1000 );
	}
	
	public static boolean DateIsCompareToBig(Date beginTime, Date endTime) {
		if (beginTime.compareTo(endTime) == 1) {
			return true;
		}
		return false;
	}
	
	public static boolean dateAEqualOrBigThanDateB(Date a, Date b) {
		if (a.compareTo(b) >= 0) {
			return true;
		}
		return false;
	}
	

	public static Date convertUnixDate(Long unixDate) {
		return new Date(unixDate);
	}

	public static void main(String[] args) {
//		Date d = convertStringToDateForAll("2017/10/08 00:00:00" );
//		System.out.println(DateUtil.getMonthDays(getCurrentDate()));
		
		Date d = DateUtil.convertString2Date("2020-11-01");
		Date[] ds = getSeasonRangeByDate(d);
		for (Date date : ds) {
			System.out.println(DateUtil.convertDate2String(date));
		}
				
		
	}

	/**
	 * 包含指定月份
	 * @param specMonth = 1,2,3,4,
	 * @param month
	 * @return
	 */
	public static boolean containsMonth(String specMonth, String month) {
		if (specMonth == null ) {
			return false;
		}
		String spec = ","+specMonth+",";
		int m = DateUtil.getMonthByMonthString(month);
		return spec.contains(","+m+",");
	}
	
	public static int dateCompare(Date date1, Date date2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateFirst = dateFormat.format(date1);
        String dateLast = dateFormat.format(date2);
        int dateFirstIntVal = Integer.parseInt(dateFirst);
        int dateLastIntVal = Integer.parseInt(dateLast);
        if (dateFirstIntVal > dateLastIntVal) {
            return 1;
        } else if (dateFirstIntVal < dateLastIntVal) {
            return -1;
        }
        return 0;
    }
	
	// 2021-12 --> 2021-12-01
	public static String getMonthFirstDayByPeriodMonth(String periodMonth) {
		return periodMonth+"-01";
	}

	// 2021-12 --> 2021-12-31
	public static String getMonthLastDayByPeriodMonth(String periodMonth) {
		Date de = DateUtil.getMonthLastDayByDate(DateUtil.convertString2Date(periodMonth, DateUtil.DEFAULT_YEAR_MONTH_FORMAT));
		return DateUtil.convertDate2String(de);
	}
}

