package edu.hope.biz.common.util;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author
 * @date 2019/10/18 14:32
 */
public class PasswordUtil {

    /**
     * 生成密码以及salt
     * @param password
     * @return
     */
    public static  Dict genPassword(String password){
        String md5Password = SecureUtil.md5(password);
        String salt = RandomUtil.randomString(32);
        String finalPassword = SecureUtil.md5(md5Password+salt);

        return Dict.create().set("password",finalPassword).set("salt",salt);
    }

    /**
     * 根据输入的密码 以及盐、加密后的密码验证是否正确
     * @param password
     * @param salt
     * @param dbPassword
     * @return
     */
    public static boolean verifyPassword(String password,String salt,String dbPassword){
        String md5Password = SecureUtil.md5(password);
        String finalPassword = SecureUtil.md5(md5Password+salt);
        return finalPassword.equals(dbPassword);
    }

    public static void main(String[] args) {
        System.out.println(genPassword("123456"));
    }

	private static String timeTicket; 
	private static int currNum;
	/**
	 * 生成流水号
	 * @return
	 */
	public synchronized static String createSerialNum(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String ticket = formatter.format(new Date());
		if (!ticket.equals(timeTicket)) {
			timeTicket = ticket;
			currNum = 0;
		}
		currNum++;
		return String.format("%s%04d", timeTicket, currNum);
	}

}
