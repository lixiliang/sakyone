import lombok.Data;
import lombok.NonNull;

@Data
public class OceanParamVO {

    @NonNull
    private String authCode;
    @NonNull
    private Long advertiserID;
    private String startDate;
    private String endDate;
    private Boolean isNewAuthCode;
    //1为T+1任务 2为
    private Integer oceanTaskType;

}
