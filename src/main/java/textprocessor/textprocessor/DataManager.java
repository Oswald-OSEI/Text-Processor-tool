package textprocessor.textprocessor;

import java.util.LinkedList;
import java.util.List;

public class DataManager {

    private LinkedList<List<String>> resultList;

    public DataManager() {
        resultList = new LinkedList<>();
    }

    // Store results from TextProcessor
    public void storeResult(List<String> result) {
        resultList.addFirst(result);
    }

    public List<String> accessElement(int index) {
        if (index < 0 || index >= resultList.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return resultList.get(index);
    }

    public void deleteElement(int index) {
        if (index < 0 || index >= resultList.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        resultList.remove(index);
    }

    public LinkedList<List<String>> getResultList() {
        return resultList;
    }
}
