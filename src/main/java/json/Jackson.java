package json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author lixiliang
 * @describe
 * @date 2021/11/2
 */
@Slf4j
public class Jackson {
    public static void main(String[] args) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        BssMsg msg = new BssMsg();
        msg.setIn(1);
        msg.setLn(2L);
        msg.setName("name1");
        msg.setUuid("id1");
        String jstr = mapper.writeValueAsString(msg);
        log.info("js:{}", jstr);

        try {
            BssMsg m = mapper.readValue(jstr,BssMsg.class);
            log.info("obj:{}",m);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
