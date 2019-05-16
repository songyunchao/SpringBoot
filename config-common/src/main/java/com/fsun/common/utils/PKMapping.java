package com.fsun.common.utils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


/**
 * PKMapping
 * @ClassName: PKMapping 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author fsun 
 * @date 2019年5月16日 下午3:51:44 
 *
 */
public class PKMapping {

  public static int r_role_menu = 2000;
  public static int r_role_power = 2001;
  public static int r_role_user = 2002;
  public static int service_registry = 2003;
  public static int sys_dictionary = 2004;
  public static int sys_log = 2005;
  public static int sys_menu = 2006;
  public static int sys_power = 2007;
  public static int sys_role = 2008;
  public static int sys_settings = 2009;
  public static int sys_user = 2010;
 
  /**
   *
   */
  static SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
  static ThreadLocalRandom r=ThreadLocalRandom.current();
  final static String ZERO = "000000";
  static int size=0;
  static HashSet<String> set=new HashSet<String>();

  /**
   * @param code
   * @return
   * @throws
   * @Title: GUUID
   * @Description: 生成表的PK
   */

	public static String GUUID(int code) {
		if (code < 1) {
			return null;
		}

		int hashCodeV = UUID.randomUUID().toString().hashCode();

		if (hashCodeV < 0) {// 负转正
			hashCodeV = -hashCodeV;
		}
		String subHashcodeV = ZERO + hashCodeV;
		subHashcodeV = subHashcodeV.substring(subHashcodeV.length() - 6, subHashcodeV.length());
		Date d = new Date();
		String s = sdf.format(d);
		// System.out.println(s + code + subHashcodeV);
		set.add(s + code + subHashcodeV);
		if (set.size() == size || set.size() % 500000 == 0) {// 出现数据重复或者set容器达500000，时间加1毫秒，set清空
			long addS = Long.parseLong(s) + 2;
			String newtest = String.valueOf(addS) + code + subHashcodeV;
			set.clear();
			// System.out.println("set内存已清空++++++++++++++++++++++++++++++++++++++++++++++++++++++"+set.size());
			set.add(newtest);
			size = set.size();
			return newtest;
		} else {

			size = set.size();
		}
		// System.out.println("hashset的长度"+set.size());
		return s + code + subHashcodeV;
	}
}
