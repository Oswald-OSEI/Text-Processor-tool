package textprocessor.textprocessor;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextProcessor {

    // Function to find a pattern within a string
    public List<String> searchWord(String regex, String matchString) {
        Pattern pattern = Pattern.compile(regex);
        Matcher search = pattern.matcher(matchString);
        int count = 0;

        List<String> results = new ArrayList<>();
        while (search.find()) {
            count++;
        }

        results.add(String.valueOf(count));
        if (count == 0) {
            String action = "No occurrence of " + "'" + regex + "'" + " found";
            results.add(action);
        } else {
            String action = "You searched for " + "'" + regex + "'" + "   |   Search Results: " + String.valueOf(count) + " occurrence(s)";
            results.add(action);
            results.add(matchString);
            results.add(regex);
        }

        return results;
    }

    public List<String> replaceWord(String regex, String matchString, String replacement) {
        int count = 0;
        Pattern pattern = Pattern.compile(regex);
        Matcher input = pattern.matcher(matchString);
        while (input.find()) {
            count++;
        }

        String output = matchString.replaceAll(regex, replacement);
        List<String> results = new ArrayList<>();
        results.add(String.valueOf(count));
        String action = "You have replaced " + "'" + regex + "'" + " of " + String.valueOf(count) + " occurrences with " + "'" + replacement + "'";
        results.add(action);
        results.add(output);
        results.add(replacement);

        return results;
    }
}
