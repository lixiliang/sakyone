import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class JsonUtil {

    public static <T> T jsonToBean(Class <T> clazz ,String json) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(json, clazz);
    }

    public static <T> ArrayList<T> jsonToList(Class <T> clazz , String json) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        ArrayList<T> list = new ArrayList<T>();
        try {
            JSONArray array = new JSONArray(json);
            for(int i = 0;i<array.length();i++){
                list.add(gson.fromJson(array.getJSONObject(i).toString(), clazz));
            }
        } catch (JSONException e) {}
        return list;
    }

    public static String beanToJson(Object object){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(object);
    }

}