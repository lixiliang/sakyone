package utill;

public class ConfigSms {
	public final static String SMS_SIGN="【埋堆堆】";
	public final static String SMS_APPID="1400046565";
	public final static String SMS_APPKEY="77b97f74a357ead52f1be93ff64c6dd8";
	public final static String SMS_URL="https://yun.tim.qq.com/v5/tlssmssvr/sendsms";
	
	/*public static void update(List<ConfigEntryInfo> list) {
		if(null==list){
			return;
		}
		String num,val;
		for (ConfigEntryInfo configEntryInfo : list) {
			num = configEntryInfo.getNum().toUpperCase();
			val = configEntryInfo.getValue();
			System.out.println(num+":"+val);
			if(num.equals("SMS_SIGN")){
				SMS_SIGN = val;
			}else if(num.equals("SMS_APPID")){
				SMS_APPID = val;
			}else if(num.equals("SMS_URL")){
				SMS_URL = val;
			}else if(num.equals("SMS_APPKEY")){
				SMS_APPKEY = val;
			}
		}
	}*/
}
