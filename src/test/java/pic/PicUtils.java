package pic;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author lixiliang
 * @describe
 * @date 2022/8/22
 */
public class PicUtils {
    public static HashMap<String, Object> readPicInfo(String file_path) {
        HashMap<String, Object> map = new HashMap<String,Object>();
        Tag tag = null;
        File jpegFile = new File(file_path);
        Metadata metadata;
        try {
            metadata = JpegMetadataReader.readMetadata(jpegFile);
            Iterator<Directory> it = metadata.getDirectories().iterator();
            while (it.hasNext()) {
                Directory exif = it.next();
                Iterator<Tag> tags = exif.getTags().iterator();
                while (tags.hasNext()) {
                    tag = tags.next();
                    System.out.println(tag.getTagName()+"--"+tag.getDescription());
                }
            }
        } catch (JpegProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
    public static void main(String[] args) {
        //传入照片的绝对路径
        readPicInfo("C:\\Users\\zh\\Desktop\\7.jpg");
    }
    /***
     * 经纬度坐标格式转换
     * @param Gps
     */
    public double latitude_and_longitude_convert_to_decimal_system(String Gps) {
        String a = Gps.split("°")[0].replace(" ", "");
        String b = Gps.split("°")[1].split("'")[0].replace(" ", "");
        String c = Gps.split("°")[1].split("'")[1].replace(" ", "").replace("\"", "");
        double gps_dou = Double.parseDouble(a)+Double.parseDouble(b)/60 + Double.parseDouble(c)/60/60;
        return gps_dou;
    }
}
