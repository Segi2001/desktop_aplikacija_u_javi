
package rs.ac.bg.fon.ai.projekat_teretana.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Stefan Segrt
 */
public class JsonUtils {
    
   private static final Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

    public static void UpisiUJSONSaNazivomKlase(String putanja, Object object, String nazivKlase, String klasa) throws IOException {
        if (object instanceof List) {
            
            List<?> listaObjekata = (List<?>) object;
            JsonArray jsonArray = new JsonArray();
            for (Object obj : listaObjekata) {
                JsonObject jsonObject = JsonParser.parseString(gson.toJson(obj)).getAsJsonObject();
                jsonObject.addProperty(nazivKlase, klasa);
                jsonArray.add(jsonObject);
            }
            try (FileWriter writer = new FileWriter(putanja)) {
                gson.toJson(jsonArray, writer);
            }
        } else {
           
            JsonObject jsonObject = JsonParser.parseString(gson.toJson(object)).getAsJsonObject();
            jsonObject.addProperty(nazivKlase, klasa);
            try (FileWriter writer = new FileWriter(putanja)) {
                gson.toJson(jsonObject, writer);
            }
        }
    }
    
//    public static void upisiUJSON(String filePath, Object obj) throws IOException {
//        try (FileWriter writer = new FileWriter(filePath)) {
//            gson.toJson(obj, writer);
//        }
//    }
//
//    
//    public static <T> T procitajISJSONA(String filePath, Class<T> clazz) throws IOException {
//        try (FileReader reader = new FileReader(filePath)) {
//            return gson.fromJson(reader, clazz);
//        }
//    }
    
    
}
