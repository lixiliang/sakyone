package file;

import com.google.common.collect.Maps;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class CvsFile {

    public static void main(String[] args) {
        StringBuilder lines = new StringBuilder();
        String f1 = "E:\\tmp\\wx.txt";
        String f2 = "E:\\tmp\\202009-pay.txt";
        Map<String,String> map1 = Maps.newHashMap();
        Map<String,String> map2 = Maps.newHashMap();
        try {
            FileReader fileReader = new FileReader(f1);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] cols = line.split("`");
                map1.put(cols[3].replace("\t",""),line);
            }

            FileReader fileReader2 = new FileReader(f2);
            BufferedReader bufferedReader2 = new BufferedReader(fileReader2);
            String line2 = null;
            while ((line2 = bufferedReader2.readLine()) != null) {
                String[] cols = line2.split(",");
                map2.put(cols[2],line2);
            }

            map1.entrySet().forEach((e)-> {
                if(!map2.containsKey(e.getKey())){
                    System.out.println("not in mdd:"+e.getValue());
                }
            });

            map2.entrySet().forEach((e)-> {
                if(!map1.containsKey(e.getKey())){
                    System.out.println("not in wx:"+e.getValue());
                }
            });

        } catch (Exception e) {
            System.out.println("error,filepath:" + f1);
            e.printStackTrace();
        }
    }


    public static void writeText(String filePath, String content) {
        try {
            FileUtils.write(new File(filePath),content,"UTF-8");
        } catch (IOException e) {
           e.printStackTrace();
        } finally {
        }
    }
}
