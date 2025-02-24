package customEvent;

import DTO.material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaterialManager {
    private static MaterialManager instance = new MaterialManager();
    private material selectedMaterial;
    private Map<Class<?>, List<Object>> listeners = new HashMap<>();

    private MaterialManager() {}

    public static MaterialManager getInstance() {
        if (instance == null) {
            instance = new MaterialManager();
        }

        return instance;
    }

    public void setSelectedMaterial(material selectedMaterial) {
        this.selectedMaterial = selectedMaterial;
        notifyListeners(editMaterialSelectionListener.class, selectedMaterial);
    }

    public <T> void addListener(Class<T> listenerType, T listener) {
        listeners.computeIfAbsent(listenerType, k -> new ArrayList<>()).add(listener);
    }

    public <T> void notifyListeners(Class<T> listenerType, material material) {
        List<Object> listenerList = listeners.get(listenerType);
        if (listenerList != null) {
            for (Object listener : listenerList) {
                if(listenerType == editMaterialSelectionListener.class) {
                    ((editMaterialSelectionListener) listener).materialSelect(material);
                }
            }
        }
    }
}
