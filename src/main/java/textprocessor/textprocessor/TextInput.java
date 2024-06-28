package textprocessor.textprocessor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;

public class TextInput {

    // Linked list to store arrays from searchWord and replaceWord functions
    private LinkedList<List<String>> resultList;
    public TextInput() {
        resultList = new LinkedList<>();
    }

    // Function to find a pattern within a string
    // Takes in 2 inputs: the pattern and the match string to search from
    public List<String> searchWord(String regex, String matchString) {
        Pattern pattern = Pattern.compile(regex);
        Matcher search = pattern.matcher(matchString);
        int count = 0;

        // This array stores the outputs (string) of the search
        List<String> results = new ArrayList<>();
        // Loop runs when find() returns true
        while (search.find()) {
            // Pass the found string into the ArrayList
            count++;
        }
        results.add(String.valueOf(count));
        if (count==0) {
            String action = "No occurence of " + regex + " found";
            results.add(action);
        }

        else{
            String action = "You searched for "+ regex + "   |   Search Results : " + String.valueOf(count)+ " occurrence(s)";
            results.add(action);
            results.add(matchString);
            results.add(regex);
            resultList.addFirst(results);}
        // Append the results to the linked list (addFirst to make it the head)


        return results;
    }

    public List<String> replaceWord(String regex, String matchString, String replacement) {
        // Count the number of replacements to be made
        int count = 0;
        Pattern pattern = Pattern.compile(regex);
        Matcher input = pattern.matcher(matchString);
        while (input.find()) {
            count++;
        }

        // Replace the word
        String output = matchString.replaceAll(regex, replacement);
        List<String> results = new ArrayList<>();
        results.add(String.valueOf(count));
        String action = "You have replaced "+ regex + " "+"of "+ String.valueOf(count)+ " occurrences" + " with " + replacement;
        results.add(action);
        results.add(output);
        results.add(replacement);

        // Append the results to the linked list (addFirst to make it the head)
        resultList.addFirst(results);

        return results;
    }

    // Function to access elements of the linked list
    public List<String> accessElement(int index) {
        if (index < 0 || index >= resultList.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return resultList.get(index);
    }

    // Function to delete elements from the linked list
    public void deleteElement(int index) {
        if (index < 0 || index >= resultList.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        resultList.remove(index);
    }

    // Getter for resultList
    public LinkedList<List<String>> getResultList() {
        return resultList;
    }
}