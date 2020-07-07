package file;

import java.io.File;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) throws IOException {
        String filePath = "c:\\data\\tvbcserver\\mdd\\newMddManage\\file\\2019-07-17 00:00:00至2019-07-24 16:00:00流水对账数据.xlsx";
        File file = new File(filePath);
        if(!file.exists()){
            file.createNewFile();
        }
    }
}
