package zip;

import cn.hutool.core.text.TextSimilarity;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.FileHeader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @Auther: Jax
 * @Date: 2018/6/28 10:03
 * @Description:zip文件解压缩工具类
 */
@Slf4j
public class UnZipUtils {


    public static void main(String[] args) throws IOException {
        UnZipUtils z = new UnZipUtils();
        File pwdFile = new File("E:\\zip\\pwd.txt");
        File dir = new File("E:\\ct");
        String dest = "E:\\unzip\\";
        Collection<File> zips = FileUtils.listFiles(dir, null, false);
        Map<String, String> passwards = Maps.newHashMap();
        try {
            List<String> lines = FileUtils.readLines(pwdFile);
            lines.stream().forEach(line -> {
                if (!StringUtils.isBlank(line)) {
                    String[] text = line.split("@");
                    if (text.length == 2) {
                        passwards.put(text[0].trim(), text[1].trim());
                    } else {
                        log.info("{} error", line);
                    }

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        Set<String> keyset = passwards.keySet();
        zips.stream().parallel().forEach(zip -> {
            String zipfileName = zip.getName().trim();
            if(zipfileName.indexOf(".zip")<= -1){
                return;
            }
            zipfileName = zipfileName.replace(".zip", "");
            if (new File(dest+zipfileName).exists()) {
                return;
            }
            zipfileName = zipfileName.replace(".pdf", "");
//                log.info("{} not eq pwd",name);
            AtomicInteger i = new AtomicInteger(0);
            for (String pwdKey : keyset) {
                i.getAndIncrement();
                double degree = TextSimilarity.similar(pwdKey, zipfileName);
                if (degree == 0.0d) {
                    continue;
                }
                try {
                    z.unZip(zip, dest, passwards.get(pwdKey));
                    break;
                } catch (Exception e) {
                    continue;
                }
            }
        });
    }

    /**
     * @param zipFile  文件
     * @param dest     解压路径
     * @param password 解压文件密码(可以为空)
     */
    public void unZip(File zipFile, String dest, String password) {
        try {

            // 首先创建ZipFile指向磁盘上的.zip文件
            ZipFile zFile = new ZipFile(zipFile);


            zFile.setFileNameCharset("UTF-8");

            // 解压目录
            File destDir = new File(dest);
            if (!destDir.exists()) {
                // 目标目录不存在时，创建该文件夹
                destDir.mkdirs();
            }
            if (zFile.isEncrypted()) {

                // 设置密码
                zFile.setPassword(password.toCharArray());


            }

            // 将文件抽出到解压目录(解压)
            zFile.extractAll(dest);


            List<net.lingala.zip4j.model.FileHeader> headerList = zFile.getFileHeaders();


            List<File> extractedFileList = new ArrayList<File>();


            for (FileHeader fileHeader : headerList) {


                if (!fileHeader.isDirectory()) {


                    extractedFileList.add(new File(destDir, fileHeader.getFileName()));


                }


            }


            File[] extractedFiles = new File[extractedFileList.size()];


            extractedFileList.toArray(extractedFiles);


            for (File f : extractedFileList) {
                log.info(f.getAbsolutePath() + "文件解压成功!");
            }


        } catch (Exception e) {
            throw new RuntimeException("error" + e.getMessage());
//            e.printStackTrace();
        }
    }
}