package customEvent;

import DTO.inputDTO;
import DTO.material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class searchMaterialManager {
    private static searchMaterialManager instance = new searchMaterialManager();
    private Map<Class<?>, List<Object>> listeners = new HashMap<>();
    private inputDTO inputDTO;

    private searchMaterialManager() {}

    public static searchMaterialManager getInstance() {
        if(instance == null) {
            instance = new searchMaterialManager();
        }

        return instance;
    }

    public void setSearchMaterial(inputDTO inputDTO) {
        this.inputDTO = inputDTO;

        notifyListeners(searchMaterialListener.class, inputDTO);
    }

    public <T> void addListener(Class<T> listenerType, T listener) {
        listeners.computeIfAbsent(listenerType, k -> new ArrayList<>()).add(listener);
    }

    public <T> void notifyListeners(Class<T> listenerType, inputDTO inputDTO) {
        List<Object> listenerList = listeners.get(listenerType);
        if (listenerList != null) {
            for (Object listener : listenerList) {
                if(listenerType == searchMaterialListener.class) {
                    ((searchMaterialListener) listener).searchMaterial(inputDTO);
                }
            }
        }
    }
}
