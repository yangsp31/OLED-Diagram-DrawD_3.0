package fileUtil;

import DTO.material;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class loadUtil {
    public ArrayList<material> loadFile(File selectedFile) throws Exception {
        Gson gson = new GsonBuilder().
                registerTypeAdapter(Color.class, new colorAdapter()).
                setPrettyPrinting().create();

        try (FileReader fr = new FileReader(selectedFile)) {
            Type listType = new TypeToken<ArrayList<material>>() {}.getType();

            return gson.fromJson(fr, listType);
        }
        catch (Exception e) {
            throw new Exception(e.getMessage() + e);
        }
    }
}
