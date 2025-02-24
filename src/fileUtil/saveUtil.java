package fileUtil;

import DTO.material;
import DTO.searchMaterialDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class saveUtil {
    public boolean saveDiagram(ArrayList<material> materials, String fileName) throws IOException {
        boolean created;
        String filePath = System.getProperty("user.dir") + "/save";       // The file in the physical location where the program runs is based on
        int count = 1;
        File directory = new File(filePath);
        File saveFile;
        Gson gson = new GsonBuilder().
                registerTypeAdapter(Color.class, new colorAdapter()).
                setPrettyPrinting().
                create();

        if(!directory.exists()){
            created = directory.mkdirs();

            if(!created){
                throw new IOException("directory create fail");
            }
        }

        saveFile = new File(filePath + File.separator + fileName + ".json");

        while(saveFile.exists()){
            saveFile = new File(filePath + File.separator + fileName + "(" + count + ")" + ".json");

            count++;
        }

        try(FileWriter fw = new FileWriter(saveFile)){
            gson.toJson(materials, fw);

            return true;
        }
        catch(IOException e){
            throw new IOException(e + "save fail");
        }
    }

    public boolean saveMaterial(searchMaterialDTO searchMaterialDTO) throws IOException {
        boolean created;
        String filePath = System.getProperty("user.dir") + "/materialList/" + searchMaterialDTO.getPurpose();     // The file in the physical location where the program runs is based on
        int count = 1;
        File directory = new File(filePath);
        File saveFile;
        Gson gson = new GsonBuilder().
                setPrettyPrinting().
                create();

        if(!directory.exists()){
            created = directory.mkdirs();

            if(!created){
                throw new IOException("directory create fail");
            }
        }

        saveFile = new File(filePath + File.separator + searchMaterialDTO.getName() + ".json");

        while(saveFile.exists()){
            saveFile = new File(filePath + File.separator + searchMaterialDTO.getName() + "(" + count + ")" + ".json");

            count++;
        }

        try(FileWriter fw = new FileWriter(saveFile)){
            gson.toJson(searchMaterialDTO, fw);

            return true;
        }
        catch(IOException e){
            throw new IOException("fail");
        }
    }
}
