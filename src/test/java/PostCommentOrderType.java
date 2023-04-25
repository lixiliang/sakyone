import java.util.LinkedHashMap;
import java.util.Map;

public enum PostCommentOrderType implements BaseEnum {
	/**数据库保存的是，枚举顺序整型，并非设置的值，不要轻易改变枚举顺序*/
	NEWWST("最新",0),
	LONGEST("最早",1),
	LIKE_NUM("点赞数",2);
	private String name;
	private Integer value;

	private PostCommentOrderType(String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	public static String getName(int value) {
		for (PostCommentOrderType c : PostCommentOrderType.values()) {
			if (c.getValue() == value) {
				return c.getName();
			}
		}
		return null;
	}
	public static PostCommentOrderType getEnum(int value) {
		for (PostCommentOrderType c : PostCommentOrderType.values()) {
			if (c.getValue() == value) {
				return c;
			}
		}
		return null;
	}
	public static PostCommentOrderType getEnum(String name) {
		for (PostCommentOrderType c : PostCommentOrderType.values()) {
			if (c.getName().equals(name)) {
				return c;
			}
		}
		return null;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}

	public Map<Integer, String> toMap() {
		LinkedHashMap<Integer, String> map = new LinkedHashMap<Integer, String>();
		for (PostCommentOrderType c : PostCommentOrderType.values()) {
			map.put(c.getValue(),c.getName()); 
		}
		return map; 
	}

	public String toName() {
		return this.name == null ? this.name() : this.name;
	}
	public Integer toValue() {
		return this.value == null ? this.ordinal() : this.value;
	}
}
