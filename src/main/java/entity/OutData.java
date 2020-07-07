package entity;

import enums.MddEnum;
import enums.TipsEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
public class OutData<T> extends BaseEntity {
	/**应答消息类型：0代表成功，非0代表有错误*/
	private Integer msgType;
	/**其它数据预留字段*/
	private Object otherData;
	/**返回的业务数据*/
	private T data;
	/**签名方式*/
	private String signType;
	/**时间戳*/
	private long time;
	/**应答连接状态*/
	private Boolean status;
	/**服务器返回信息*/
	private String msg;
	/**签名*/
	private String sign;

	public OutData(boolean status, int msgType, String msg, T data, Object otherData, boolean transfer){
		this.status = status;
		this.msgType = msgType;
		this.msg = msg;
		this.data = data;
		this.otherData = otherData;
		this.signType = "md5";
		this.time = System.currentTimeMillis();
//		signMD5();
	}
	public OutData(boolean status, int msgType, String msg, T data, Object otherData){
		this(status,msgType,msg,data,otherData,true);
	}
	public OutData(boolean status, int msgType, String msg, T data){
		this(status, msgType, msg, data, null);
	}
	public OutData(boolean status, int msgType, String msg){
		this(status, msgType, msg, null);
	}
	public OutData(int msgType, String msg, T data){
		this(true, msgType, msg, data);
	}
	public OutData(int msgType, String msg){
		this(true, msgType, msg, null);
	}
	public static <T> OutData<T> success() {
		return success(null);
	}

	public static <T> OutData<T> success(T data) {
		return success(data, null);
	}

	public static <T> OutData<T> success(T data, Object otherData) {
		OutData<T> result = new OutData<>();
		result.setStatus(true).setMsgType(TipsEnum.SUCCESS.getValue()).setMsg(TipsEnum.SUCCESS.getName()).setData(data).setOtherData(otherData)
				.setSignType("md5").setTime(System.currentTimeMillis());
		return result;
	}

	public static <T> OutData<T> failed(MddEnum mddEnum) {
		return failed(mddEnum.getValue(),mddEnum.getName());
	}
	public static <T> OutData<T> failed(int msgType,String message) {
		return failed(null,null,msgType, message);
	}

	public static <T> OutData<T> failed(T data) {
		return failed(data,null, -1,null);
	}

	public static <T> OutData<T> failed(T data, Object otherData,int msgType,String message) {
		OutData<T> result = new OutData<>();
		return result.setStatus(false).setMsgType(msgType).setData(data).setOtherData(otherData).setMsg(message).setSignType("md5").setTime(System.currentTimeMillis());
	}
	public static <T> OutData<T> exception() {
		return failed(TipsEnum.SYSTEM_BUSY.getValue(),TipsEnum.SYSTEM_BUSY.getName());
	}

}
