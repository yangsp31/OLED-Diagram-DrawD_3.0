package fileUtil;

import DTO.searchMaterialDTO;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;

public class readUtil {
    public searchMaterialDTO readFile(File file) throws Exception {
        try (FileReader fr = new FileReader(file)) {
            Gson gson = new Gson();

            return gson.fromJson(fr, searchMaterialDTO.class);
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
