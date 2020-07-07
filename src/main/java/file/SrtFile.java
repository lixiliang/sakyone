package file;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;

public class SrtFile {

    public static void main(String[] args) {
       /* String  str = "儿子, 我们去别的地方玩";
        str = StringUtils.replaceAll(str, "[\\pP+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]", "");
        System.out.println(str);*/
        File dir = new File("E:\\srt\\魚躍在花見srt");
        Collection<File> files =  FileUtils.listFiles(dir,null,true);
        for (File file:files){
//            System.out.println();
            String filePath = file.getAbsolutePath();
            clean(filePath);
        }
//        String filePath = "E:/srt/夺命真夫20.srt";
    }

    private static void clean(String filePath) {
        StringBuilder lines = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (isCNChar(line)) {
//                    System.out.println("is cn:"+line);
                    line = StringUtils.replaceAll(line, "[\\pP+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]", " ");
//                    System.out.println("new line:"+line);
                }
                lines.append(line).append("\n");
            }
            StringBuilder newFilePath = new StringBuilder();
            int lastIndex = filePath.lastIndexOf("/");
            if(lastIndex == -1){
                lastIndex =  filePath.lastIndexOf("\\");
            }
            int secondIndex = filePath.indexOf("\\",4);
            newFilePath.append(filePath,0,secondIndex).append("\\clean\\")
                    .append(filePath,secondIndex+1,lastIndex+1)
                    .append(filePath.substring(lastIndex + 1));
           /* newFilePath.append(filePath, 0, lastIndex)
                    .append("/clean/")
                    .append(filePath.substring(lastIndex + 1));*/
            String newFilePathStr = newFilePath.toString();
            System.out.println("newFilePath: " + newFilePathStr);
            writeText(newFilePathStr, lines.toString());
        } catch (Exception e) {
            System.out.println("error,filepath:" + filePath);
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

    /**
     * 判断字符串中是否含有中文
     */
    public static boolean isCNChar(String s) {
        boolean booleanValue = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c > 128) {
                booleanValue = true;
                break;
            }
        }
        return booleanValue;
    }
}
