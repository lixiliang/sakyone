import cn.hutool.core.lang.ClassScanner;
import cn.hutool.core.lang.Filter;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.core.ResolvableType;
import utill.HttpResponse;
import utill.HttpUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

@Slf4j
public class Test03 {


    @Test
    public void test3() throws ParseException, IOException {
        String path = "e://param2.data";
//        String url = "http://172.16.32.91:7799/missionApi/external/mission/award/grant";
        String url = "http://localhost:7799/missionApi/external/mission/award/grant";
        List<String> lines = FileUtils.readLines(new File(path), "UTF-8");
        for (String line : lines) {
            HttpResponse rs = HttpUtils.jsonPost(url, JSON.parseObject(line));
            log.info("rs:{}", rs);
        }
    }
    @Test
    public void analyze() {
        String[][] projects = new String[][]{
//                {"mdd_api", "E:\\git\\gitlab\\Mdd_AppApi\\target\\mdd-api.jar"}
//                {"mdd_BizManager", "E:\\git\\gitlab\\Mdd_BizManager\\target\\Mdd_BizManager.jar"}
//                , {"mdd-PayManager", "E:\\git\\gitlab\\Mdd_PayManager\\target\\Mdd_PayManager.jar"}
//                , {"Mdd_MpManager", "E:\\git\\gitlab\\Mdd_MpManager\\target\\Mdd_MpManager.jar"}
//                {"mdd_manager", "E:\\git\\gitlab\\Mdd_WebManage\\classes\\artifacts\\Mdd_manager_jar\\Mdd_manager.jar"}
//             {"mdd_mission_Api", "E:\\git\\gitlab\\mdd-mission-api\\target\\mdd-mission-api.jar"}
                {"mdd_Activity", "E:\\git\\gitlab\\Mdd_Activity\\target\\mdd-activity-api.jar"}
//                              , {"mdd_mini_api", "E:\\git\\gitlab\\Mdd_MiniProgramApi\\target\\mdd-mini-api.jar"}
//                              , {"mdd-pay-biz", "E:\\git\\gitlab\\Mdd_PayBizApi\\target\\mdd-pay-biz.jar"}
//                              , {"mdd_pay_api", "E:\\git\\gitlab\\Mdd_PayApi\\target\\mdd-pay-api.jar"}
//                              , {"mdd-search-api", "E:\\git\\gitlab\\Mdd_SearchApi\\target\\mdd-search-api.jar"}
//                              , {"mdd-search-index", "E:\\git\\gitlab\\Mdd_SearchIndex\\target\\mdd-search-index.jar"}
//                              , {"mdd-userlive-api", "E:\\git\\gitlab\\Mdd_UserLiveApi\\target\\mdd-userlive-api.jar"}
//                              , {"mdd-ad-api", "E:\\git\\gitlab\\mdd-ad-api\\target\\mdd-ad-api.jar"}
//                              , {"mdd-task", "E:\\git\\gitlab\\Mdd_Task\\target\\mdd-task.jar"}
//                              , {"mdd-mp-api", "E:\\git\\gitlab\\Mdd_MpApi\\target\\mdd-mp-api.jar"}
//                              , {"mdd-mp-sync", "E:\\git\\gitlab\\Mdd_MpSync\\target\\mdd-mp-sync.jar"}
//                              , {"mdd_transcode_task", "E:\\git\\gitlab\\mdd_transcode_task\\target\\mdd_transcode_task.jar"}
//                              , {"mdd-ad-api", "E:\\git\\gitlab\\mdd-ad-api\\target\\mdd-ad-api.jar"}*/
        };
        for (int i = 0; i < projects.length; i++) {
            projects[i][0] = StringUtils.replaceAll(projects[i][0], "-", "_");
            loadClass(projects[i][1]);
        }

    }

    public void loadClass(String jar) {
        Filter<Class<?>> classFilter = (c) -> {
            String name = c.getName().toLowerCase();
            if (name.endsWith("controller")
                    || name.endsWith("service")
                    || name.endsWith("dao")) {
                return false;
            }
            return true;
        };
        Set<Class<?>> classSet = ClassScanner.scanPackage("code", classFilter);
        classSet.stream().forEach(c-> System.out.println(c) );
        log.info("");
//        File file = new File(jar);
//        ClassLoader classLoader = JarClassLoader.loadJarToSystemClassLoader(file);
//        Class aClass = null;
//        try {
//            aClass = classLoader.loadClass("com.pureShare.entity.member.dto.MemberAppleOrderDto");
//            System.out.println(aClass.hashCode());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void t4() throws ParseException, IOException {
        checkConstructor(OceanParamVO.class);
    }

    public void checkConstructor(Class<?> clz) {
        Constructor<?>[] constructors = clz.getConstructors();
//                    log.info("method:{} , param-{} type: {}",method.getName(),j,type);
//                    System.out.println("参数类型：" + clz.getName() +"type:"+clz.getTypeName());
        boolean noArgs = false;
        for (Constructor<?> con : constructors) {
            int pcount = con.getParameterCount();
            if (pcount == 0) {
                noArgs = true;
                break;
            }
        }
        if (!noArgs &&!clz.isInterface() && !clz.isEnum()) {
            log.info("无参构造行数：{},", clz);
        }
    }

    public void parseMethod(Class<?> clazz) {
        //获取本类的所有方法，存放入数组
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            log.info("方法名：" + method.getName());
            //获取本方法所有参数类型，存入数组
            Class<?>[] getTypeParameters = method.getParameterTypes();
            if (getTypeParameters.length == 0) {
                log.info("此方法无参数");
            }
            for (int i = 0; i < getTypeParameters.length; i++) {
                ResolvableType rt = ResolvableType.forMethodParameter(method, i);
                log.info("method:{}, type: {}", method.getName(), rt.getType());
                //获取参数的泛型
                ResolvableType[] generics = rt.getGenerics();
                if (generics.length > 0) {
                    for (int j = 0; j < generics.length; j++) {
                        Type type = generics[j].getType();
                        log.info("method:{} , param-{} type: {}", method.getName(), j, type);
                        Class<?> clz = generics[j].resolve();
                        checkConstructor(clz);
                    }
                } else {
                    //无泛型
                    checkConstructor(rt.resolve());
                }

            }

            /*for (Class<?> class1 : getTypeParameters) {
                String parameterName = class1.getName();
                ResolvableType t = ResolvableType.forClass(class1);
                Type type = t.getGeneric(0).getType();
                Class<?> clz = t.getGeneric(0).resolve();
                Constructor<?>[] constructors = type.getClass().getConstructors();
                System.out.println("参数类型：" + parameterName +"type:"+clz.getTypeName());
                for (Constructor< ? > con : constructors) {
                    log.info("name:{},参数个数：{}",con.getName(),con.getParameterCount());
                }

             *//*   Type sType = class1.getGenericSuperclass();
                if(sType instanceof ParameterizedType){
                    Type type = ((ParameterizedType)class1.getGenericSuperclass()).getActualTypeArguments()[0];
                    System.out.println("参数类型：" + parameterName +"type:"+type.getTypeName());

                }*//*

            }*/
        }
    }

}
