package file;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AwardFile {
    static Map<String, Integer> map = Maps.newHashMap();

    public static void main(String[] args) {
        String filePath = "D:/awardRecord.txt";
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (StringUtils.isNotBlank(line)) {
                    String[] record = line.split(",");
                    String member = StringUtils.substring(record[0],record[0].indexOf(":")+1);
                    String awardType = StringUtils.substring(record[1],record[1].indexOf(":")+1);
                    String transactionId = StringUtils.substring(record[2],record[2].indexOf(":")+1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
