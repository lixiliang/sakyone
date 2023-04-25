package pic;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import org.apache.commons.lang3.StringUtils;
import utill.HttpResponse;
import utill.HttpUtils;

import java.io.File;
import java.io.IOException;

public class GPSInfo {
    /**
     * 图片信息获取metadata元数据信息
     *
     * @param fileName 需要解析的文件
     * @return
     */
    public ImageInfoBean parseImgInfo(String fileName) {
        File file = new File(fileName);
        ImageInfoBean imgInfoBean = null;
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            imgInfoBean = printImageTags(file, metadata);
        } catch (ImageProcessingException e) {
            System.err.println("error 1a: " + e);
        } catch (IOException e) {
            System.err.println("error 1b: " + e);
        }
        return imgInfoBean;
    }

    /**
     * 读取metadata里面的信息
     *
     * @param sourceFile 源文件
     * @param metadata   metadata元数据信息
     * @return
     */
    private ImageInfoBean printImageTags(File sourceFile, Metadata metadata) {
        ImageInfoBean imgInfoBean = new ImageInfoBean();
        imgInfoBean.setImgName(sourceFile.getName());
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                String tagName = tag.getTagName();
                String desc = tag.getDescription();
                if (tagName.equals("GPS Latitude")) {
                    //纬度
                    imgInfoBean.setLatitude(pointToLatlong(desc));
                } else if (tagName.equals("GPS Longitude")) {
                    //经度
                    imgInfoBean.setLongitude(pointToLatlong(desc));
                }
            }
            for (String error : directory.getErrors()) {
                System.err.println("ERROR: " + error);
            }
        }
        return imgInfoBean;
    }

    /**
     * 经纬度转换  度分秒转换
     *
     * @param point 坐标点
     * @return
     */
    public String pointToLatlong(String point) {
        Double du = Double.parseDouble(point.substring(0, point.indexOf("°")).trim());
        Double fen = Double.parseDouble(point.substring(point.indexOf("°") + 1, point.indexOf("'")).trim());
        Double miao = Double.parseDouble(point.substring(point.indexOf("'") + 1, point.indexOf("\"")).trim());
        Double duStr = du + fen / 60 + miao / 60 / 60;
        return duStr.toString();
    }


    //location 格式： lat<纬度>,lng<经度>
    public static void baiduMap(String location) {
        //调用百度地图api 到百度地图开放平台注册得到账号与key
        String ak = "XfDC4bnnTYClfmUlVbE3pOAVqPqPNn0V";
        String url = "https://api.map.baidu.com/reverse_geocoding/v3/?ak=" + ak + "&output=json&coordtype=wgs84ll&location=${location}";
        url = url.replace("${location}", location);
        HttpResponse rs = HttpUtils.get(url);
        System.out.println(rs);
    }

    public static void main(String[] args) {
        ImageInfoBean imgInfo = new GPSInfo().parseImgInfo("C:\\Users\\zh\\Desktop\\8.jpg");
        System.out.println(imgInfo.toString());
        if (!StringUtils.isBlank(imgInfo.getLatitude()) || !StringUtils.isBlank(imgInfo.getLongitude())) {
            String location = imgInfo.getLatitude() + "," + imgInfo.getLongitude();
            baiduMap(location);
        }
    }


}