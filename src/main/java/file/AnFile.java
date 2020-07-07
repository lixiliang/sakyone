package file;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AnFile {
    static Map<String, Integer> map = Maps.newHashMap();

    public static void main(String[] args) {
        String filePath = "D:/1.txt";
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (StringUtils.isNotBlank(line)) {
                    String[] stra = line.split(",");
                    String value = stra[1].replace("count", "");
                    Integer v = Integer.parseInt(value);
                    if (map.containsKey(stra[0])) {
                        Integer ww = map.get(stra[0]);
                        map.put(stra[0], v + ww);
                    }
                    map.put(stra[0], v);
                }
            }

            List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    //按照value值，重小到大排序
//                return o1.getValue() - o2.getValue();

                    //按照value值，从大到小排序
                return o2.getValue() - o1.getValue();
                }
            });

            //注意这里遍历的是list，也就是我们将map.Entry放进了list，排序后的集合
            for (Map.Entry s : list) {
                System.out.println(s.getKey() + ": " + s.getValue());
            }

         /*   Iterator iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String key = (String) entry.getKey();
                Integer val = (Integer) entry.getValue();
                System.out.println(key + ": " + val);
            }*/

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
