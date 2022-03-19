import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        File file = new File("src\\main\\java\\data.json");
        System.out.println(jsonToList(readString(file)));
    }

    public static String readString(File file) {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = bis.read(); i != -1; i = bis.read()) {
                stringBuilder.append((char) i);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static List<Employee> jsonToList(String json) {
        try {
            Gson gson = new GsonBuilder().create();
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(json);
            List<Employee> list = new ArrayList<>();
            for (Object object : array) {
                list.add(gson.fromJson(object.toString(), Employee.class));
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}