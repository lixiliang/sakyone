import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PackageScanner {
    private List<Class<?>> classes;
    private String packagePath = "";
 
    /*
     * 无参构造方法，内部调用带参的构造方法。
     *
     * @throw classNotFound
     *
     */
    public PackageScanner() throws ClassNotFoundException {
        this("");
    }
 
    /*
     * 实现，调用fileScanner进行目录扫描和加载
     *
     * @param String 传入需要扫描的包
     *
     * @throw classNotFound
     */
    public PackageScanner(String basePackage) throws ClassNotFoundException {
        packagePath = System.getProperty("user.dir") + "\\src\\";
        String filePath = packagePath + basePackage.replace('.', '\\');
        classes = new ArrayList<Class<?>>();
        fileScanner(new File(filePath));
    }
 
    private void fileScanner(File file) throws ClassNotFoundException {
        if (file.isFile() && file.getName().lastIndexOf(".java") == file.getName().length() - 5) {//5是".java"的长度
            String filePath = file.getAbsolutePath();
            String qualifiedName = filePath.substring(packagePath.length(), filePath.length() - 5).replace('\\', '.');
            System.out.println(qualifiedName);
            classes.add(Class.forName(qualifiedName));
            return;
        } else if (file.isDirectory()) {
            for (File f : file.listFiles())
                fileScanner(f);
        }
    }
 
    /*
     * 得到加载到的类对象的List,返回的是ArrayList
     */
    public List<Class<?>> getClasses() {
        return this.classes;
    }
}