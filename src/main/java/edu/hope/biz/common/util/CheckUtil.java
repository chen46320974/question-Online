package edu.hope.biz.common.util;


import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtil {

    /**
     * 校验手机号格式是否合格
     * @param phone
     * @return
     */
    public static Boolean checkPhone(String phone){
        Pattern p = Pattern.compile("^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$");
        return  p.matcher(phone).matches();
    }


    /**
     * 校验银行卡卡号(比较算出的校验位和卡号里的校验位)
     * @param cardId
     * @return
     */
//    public static boolean checkBankCard(String cardId) {
//        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
//        return cardId.charAt(cardId.length() - 1) == bit;
//    }
//
//    /**
//     * 用不含校验位的银行卡卡号，采用 Luhm 校验算法获得校验位(卡号最后一位为校验位)
//     * @param nonCheckCodeCardId
//     * @return
//     */
//    private static char getBankCardCheckCode(String nonCheckCodeCardId) {
//        if(nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
//                || !nonCheckCodeCardId.matches("\\d+")) {
//            throw new IllegalArgumentException("Bank card code must be number!");
//        }
//        char[] chs = nonCheckCodeCardId.trim().toCharArray();
//        int luhmSum = 0;
//        for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
//            int k = chs[i] - '0';
//            if(j % 2 == 0) {
//                k *= 2;
//                k = k / 10 + k % 10;
//            }
//            luhmSum += k;
//        }
//        return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');
//    }

    /**
    * 校验银行卡号
    * @param: cardNumber
    * @author: zhaoMaoJie
    * @Date: 12:05 2019/11/28 0028
     */
    public static boolean checkBankCard(String cardNumber){
        String nonCheckCodeCardId = cardNumber.substring(0,cardNumber.length() - 1);
        if(nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            return false;
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if(j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        char bit = (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');
        return cardNumber.charAt(cardNumber.length() - 1) == bit;
    }

    /**
     * 校验身份证号码
     * @param idNumber
     * @return
     */
    public static boolean validateIdNumber(String idNumber) {
        //
        Pattern p1 = Pattern.compile("(\\d{17}[0-9xX]|\\d{14}[0-9xX])");
        Matcher matcher = p1.matcher(idNumber);
        if (matcher.matches()) {
            Pattern p2 = Pattern.compile("\\d{6}(\\d{8}).*"); // 用于提取出生日字符串
            Pattern p3 = Pattern.compile("(\\d{4})(\\d{2})(\\d{2})");// 用于将生日字符串进行分解为年月日
            Matcher matcher2 = p2.matcher(idNumber);
            boolean flag = matcher2.find();
            if (flag) {
                String date = matcher2.group(1);
                Matcher matcher3 = p3.matcher(date);
                if (matcher3.find()) {
                    String year = matcher3.group(1);
                    int month = Integer.parseInt(matcher3.group(2));
                    int day = Integer.parseInt(matcher3.group(3));
                    if (month < 1 || month > 12) {
                        return false;
                    }
                    if (day > getDays(year, month) || day < 1) {
                        return false;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取某个月份对应天数
     * @param year
     * @param month
     * @return
     */
    private static int getDays(String year, int month) {
        int days = 0;
        switch (month) {
            case 1:
                days = 31;
                break;
            case 3:
                days = 31;
                break;
            case 5:
                days = 31;
                break;
            case 7:
                days = 31;
                break;
            case 8:
                days = 31;
                break;
            case 10:
                days = 31;
                break;
            case 12:
                days = 31;
                break;
            case 2:
                if (isLeapYear(year))
                    days = 29;
                else
                    days = 28;
                break;
            default:
                days = 30;
        }
        return days;
    }

    /**
     * 检验是否为闰年
     * @param year
     * @return
     */
    private static boolean isLeapYear(String year) {
        Long yearL = Long.parseLong(year);
        if ((yearL % 4 == 0) && (yearL % 100 != 0) || (yearL % 400 == 0)) {
            return true;
        }
        return false;
    }

    /**
     * 判断时间是否为当前月
     * @param param
     * @return
     */
    public static boolean isThisMonth(Date param){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        String date = df.format(param);
        String MM = date.substring(4, 6);//截取系统月份
        String mm = df.format(new Date()).substring(4, 6);;//要判断的月份
        if(MM.equals(mm)){
            return true;
        }else{
            return false;
        }
    }

    /**
    * 校验车牌号
    * @param: plateNumber
    * @author: zhaoMaoJie
    * @Date: 18:41 2019/11/27 0027
     */
    public static boolean checkPlateNumber(String plateNumber){
        if(StringUtils.isEmpty(plateNumber)){
            return false;
        }
        String carnumRegex = "([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}(([0-9]{5}[DF])|([DF]([A-HJ-NP-Z0-9])[0-9]{4})))|([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂学警港澳]{1})";

        return plateNumber.matches(carnumRegex);
    }
}
