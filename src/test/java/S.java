public class S extends BaseEntity {
    private String sactionUuid;
    private Integer count = 1;
    public String getSactionUuid() {
        return sactionUuid;
    }
    public void setSactionUuid(String sactionUuid) {
        this.sactionUuid = sactionUuid;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    public void addCount() {
        this.count ++;
    }
}