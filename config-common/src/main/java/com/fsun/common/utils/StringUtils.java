package com.fsun.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang3.ObjectUtils;

public class StringUtils {
	
	/**
     * The empty String {@code ""}.
     * @since 2.0
     */
    public static final String EMPTY = "";
    
	/**
	 * 获取唯一Id
	 *
	 * @return
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.toUpperCase().replace("-", "");
	}

	/**
	 * 检查指定的字符串是否为空。
	 * <ul>
	 * <li>SysUtils.isEmpty(null) = true</li>
	 * <li>SysUtils.isEmpty("") = true</li>
	 * <li>SysUtils.isEmpty("   ") = true</li>
	 * <li>SysUtils.isEmpty("abc") = false</li>
	 * </ul>
	 *
	 * @param value
	 *            待检查的字符串
	 * @return true/false
	 */
	public static boolean isEmpty(String value) {
		int strLen = 0;
		if (value == null || (strLen = value.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(value.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	public static String generateVarcharPrimaryKey() {
		SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmmss");
		String key = time.format(new Date()) + UUID.randomUUID().toString().split("-")[4];
		return key;
	}

	
	public static String md5Encode(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		byte[] byteArray = null;
		try {
			byteArray = inStr.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = md5Bytes[i] & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	/**
	 * 手机号验证
	 */
	public static boolean validatePhone(String phone) throws PatternSyntaxException {
		return isChinaPhoneLegal(phone) || isHKPhoneLegal(phone);
	}

	/**
	 * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数 此方法中前三位格式有： 13+任意数 15+除4的任意数 18+除1和4的任意数
	 * 17+除9的任意数 147
	 */
	public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
		String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * 香港手机号码8位数，5|6|8|9开头+7位任意数
	 */
	public static boolean isHKPhoneLegal(String str) throws PatternSyntaxException {
		String regExp = "^(5|6|8|9)\\d{7}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * 校验银行账号 
      	校验过程： 
      1、从卡号最后一位数字开始，逆向将奇数位(1、3、5等等)相加。 
      2、从卡号最后一位数字开始，逆向将偶数位数字，先乘以2（如果乘积为两位数，将个位十位数字相加，即将其减去9），再求和。 
      3、将奇数位总和加上偶数位总和，结果应该可以被10整除。        
	 * @param bankaccount
	 * @return
	 */
	public static boolean isBankAccount(String bankaccount) {
		if (bankaccount.length() < 15 || bankaccount.length() > 19) {
			return false;
		}
		char bit = getBankCardCheckCode(bankaccount.substring(0, bankaccount.length() - 1));
		if (bit == 'N') {
			return false;
		}
		return bankaccount.charAt(bankaccount.length() - 1) == bit;  		
	}

	/** 
	* 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位 
	* @param nonCheckCodeBankCard 
	* @return 
	*/  
	private static char getBankCardCheckCode(String nonCheckCodeBankCard){  
		if (nonCheckCodeBankCard == null || nonCheckCodeBankCard.trim().length() == 0
				|| !nonCheckCodeBankCard.matches("\\d+")) {
			// 如果传的不是数据返回N
			return 'N';
		}
		char[] chs = nonCheckCodeBankCard.trim().toCharArray();
		int luhmSum = 0;
		for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
			int k = chs[i] - '0';
			if (j % 2 == 0) {
				k *= 2;
				k = k / 10 + k % 10;
			}
			luhmSum += k;
		}
		return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
	}
	
	/**
	 * 字符串转集合
	 * @param str
	 * @return
	 */
	public static List<String> split(String str) {
		List<String> result = new ArrayList<String>();
		if (!isEmpty(str)) {
			String[] temp = str.split(",");
			for (String string : temp) {
				if (!isEmpty(string)) {
					result.add(string);
				}
			}
		}
		return result;
	}
	
	/**
	 * 集合转字符串
	 * @param coll
	 * @param delim
	 * @return
	 */
	public static String collectionToDelimitedString(Collection<?> coll, String delim) {
		return collectionToDelimitedString(coll, delim, "", "");
	}

	/**
	 * 集合转字符串
	 * @param coll
	 * @return
	 */
	public static String collectionToCommaDelimitedString(Collection<?> coll) {
		return collectionToDelimitedString(coll, ",");
	}

	/**
	 * 集合转字符串
	 * @param coll
	 * @param delim
	 * @param prefix
	 * @param suffix
	 * @return
	 */
	public static String collectionToDelimitedString(Collection<?> coll, String delim, String prefix, String suffix) {
		if (coll==null || coll.size()==0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Iterator<?> it = coll.iterator();
		while (it.hasNext()) {
			sb.append(prefix).append(it.next()).append(suffix);
			if (it.hasNext()) {
				sb.append(delim);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 集合转字符串
	 * @param iterable
	 * @param separator
	 * @return
	 */
	public static String join(final Iterable<?> iterable, final String separator) {
        if (iterable == null) {
            return null;
        }
        return join(iterable.iterator(), separator);
    }
	
	/**
	 * 集合转字符串
	 * @param iterator
	 * @param separator
	 * @return
	 */
	public static String join(final Iterator<?> iterator, final String separator) {

        // handle null, zero and one elements before building a buffer
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return EMPTY;
        }
        final Object first = iterator.next();	
        if (!iterator.hasNext()) {
            @SuppressWarnings( "deprecation" ) // ObjectUtils.toString(Object) has been deprecated in 3.2
            final String result = ObjectUtils.toString(first);
            return result;
        }

        // two or more elements
        final StringBuilder buf = new StringBuilder(256); // Java default is 16, probably too small
        if (first != null) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            if (separator != null) {
                buf.append(separator);
            }
            final Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }

	
}
