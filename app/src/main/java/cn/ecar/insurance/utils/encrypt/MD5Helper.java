package cn.ecar.insurance.utils.encrypt;


import com.orhanobut.logger.Logger;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;


public class MD5Helper {

    // 十六进制下数字到字符的映射数组
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f"};

    /**
     * 方法描述： 执行加密操作
     *
     * @param info
     * @return
     */
    public static String sign(String info, String charset) {
        return encodeByMD5(info, charset);
    }

    public static String signAndBase64(String info, String charset) {

        return new String(Base64.encodeBase64(encodeByMD5_16bit(info, charset)));
    }

    /**
     * 方法描述： 对字符串进行MD5加密
     *
     * @param info
     * @return
     */
    private static String encodeByMD5(String info, String charset) {
        if (info == null) {
            return null;
        }
        try {
            // 创建具有指定算法名称的信息摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
            md.update(info.getBytes(charset));
            byte[] b = md.digest();

            // 将得到的字节数组变成字符串返回
            info = byteArrayToHexString(b);

        } catch (Exception ex) {
            Logger.e("Enry by MD5 Failure!!!", ex);
        }
        return info.toUpperCase();
    }

    /**
     * 方法描述： 对字符串进行MD5加密
     *
     * @param info
     * @return
     */
    private static byte[] encodeByMD5_16bit(String info, String charset) {
        if (info == null) {
            return null;
        }
        byte[] b = null;
        try {
            // 创建具有指定算法名称的信息摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
            md.update(info.getBytes(charset));
            b = md.digest();

        } catch (Exception ex) {
            Logger.e("Enry by MD5 Failure!!!", ex);
        }
        return b;
    }

    /**
     * 转换字节数组为十六进制字符串
     *
     * @param b 字节数组
     * @return 十六进制字符串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * 将一个字节转化成十六进制形式的字符串
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * 方法描述： 转换为十六进制
     *
     * @param b
     * @return
     */
    public static String toHex(byte[] b) {
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(Character.forDigit((b[i] & 0x06) >> 4, 16));
            sb.append(Character.forDigit(b[i] & 0x0f, 16));
        }
        return sb.toString();
    }

    /**
     * 方法描述： 带参签名
     *
     * @param params
     * @param appSecret
     * @param charset
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getSign(Map<String, String> params, String appSecret, String charset)
            throws UnsupportedEncodingException {

        String sign = "";
        if (params == null) {
            return sign;
        }

        Object[] paramNames = params.keySet().toArray();
        Arrays.sort(paramNames);

        StringBuilder message = new StringBuilder();
        for (Object s : paramNames) {
            if (s == null) {
                continue;
            }
            String value = params.get(s) == null ? "" : params.get(s);


            message.append(s).append("=").append(value).append("&");
        }
        sign = message.toString().substring(0, message.toString().length() - 1);
        Logger.i("签名串：" + sign + "&" + appSecret);

        return sign(sign + "&" + appSecret, charset);
    }

    /**
     * 方法描述： 带参签名
     *
     * @param params
     * @param appSecret
     * @param charset
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getSignEncode(Map<String, String> params, String appSecret, String charset)
            throws UnsupportedEncodingException {

        String sign = "";
        if (params == null) {
            return sign;
        }

        Object[] paramNames = params.keySet().toArray();
        Arrays.sort(paramNames);

        StringBuilder message = new StringBuilder();
        for (Object s : paramNames) {
            if (s == null) {
                continue;
            }
            String value = params.get(s) == null ? "" : params.get(s);
            message.append(s).append("=").append(URLEncoder.encode(value, charset)).append("&");
        }
        sign = message.toString().substring(0, message.toString().length() - 1);
        Logger.i("签名串：" + sign + "&" + appSecret);
        return sign(sign + "&" + appSecret, charset);
    }

    /**
     * 方法描述： 带参签名
     *
     * @param params
     * @param appSecret
     * @param charset
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getSignByObject(Map<String, Object> params, String appSecret, String charset)
            throws UnsupportedEncodingException {

        String sign = "";
        if (params == null) {
            return sign;
        }

        Object[] paramNames = params.keySet().toArray();
        Arrays.sort(paramNames);

        StringBuilder message = new StringBuilder();
        for (Object s : paramNames) {
            if (s == null) {
                continue;
            }
            String value = params.get(s) == null ? "" : (String) params.get(s);
            message.append(s).append("=").append(URLEncoder.encode(value, charset)).append("&");
        }
        sign = message.toString().substring(0, message.toString().length() - 1);
        return sign(sign + "&" + appSecret, charset);
    }

    /**
     * 方法描述： 带参签名
     *
     * @param params
     * @param appSecret
     * @param charset
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getBesttoneSign(Map<String, String> params, String appSecret, String charset)
            throws UnsupportedEncodingException {

        String signData = "";
        if (params == null) {
            return signData;
        }

        StringBuilder message = new StringBuilder();
        for (String s : params.values()) {
            message.append(s);
        }
        signData = message.toString().trim() + appSecret.trim();
        Logger.i("签名串：" + signData);
        return sign(signData, charset);
    }


//    public static void main(String[] args) {
//
//        System.out.println(encodeByMD5("bigdataforinterface", "UTF-8"));
//
//        //System.out.println(signAndBase64("<order></order>123456", "UTF-8"));
//        /*Map<String,String> map = new HashMap<String, String>();
//        map.put("version", "1.0");
//		map.put("appId", "1");
//		map.put("timestamp", "20151222");
//		map.put("nonce", "896589");
//		map.put("phoneNumber", "13818175906");
//		try {
//			System.out.println(getSignEncode(map, "123456", "UTF-8"));
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}*/
//    }
}
