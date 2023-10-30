import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WordDictionary {

    private String word;
    private String definition;

    //Here I have created a List of objects of type WordDictionary
    List<WordDictionary> dictionary = new ArrayList<>();

    //Here I am creating a parameterized constructor
    public WordDictionary(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }


    /*
     * Here I am using the stream API to filter through the input dictionaries and map the
     * element that matches the word and re-initialize the definition and assign it to an Optional of type WordDictionary
     * If the wordDictionary is no match found, I am adding it as a new record to the List<WordDictionary>
     */
    public void insertWord(String word, String definition) {
        Optional<WordDictionary> wordDictionary = dictionary.stream().filter(w -> w.word.equals(word)).map(wrd -> {
            wrd.definition = definition;
            return wrd;
        }).findFirst();

        if(wordDictionary.isEmpty()){
            dictionary.add(new WordDictionary(word, definition));
        }
    }

    /*
     * Here I am using the stream API to filter through the input dictionaries and find if there
     * is any word in the dictionary that is equal to the input word.
     * Return the definition from the filtered input
     */
    public String findDefinition(String word) {
        return dictionary.stream().filter(w -> w.word.equals(word)).map(d -> d.definition).toString();

    }

    /*
     * Here I am using the stream API to filter through the input dictionaries and find if there
     * is any word in the dictionary that contains the input word.
     * Then return the list if all the possible definitions as a List
     */
    public List<String> partialSearch(String partialWord) {
        return dictionary.stream().filter(w -> w.word.contains(partialWord)).map(d -> d.definition).collect(Collectors.toList());
    }

    /*
     * Here I am using the for loop to lookup the index of the dictionary word that matches the input word
     * and removing that index from the dictionary
     */
    public void remove(String word) {
        for (int i = 0; i < dictionary.size(); i++) {
            if (dictionary.get(i).equals(word)) {
                dictionary.remove(i);
            }
        }
    }

    public static void main(String[] args) {

        WordDictionary wordDictionary = new WordDictionary("", "");

        //Invoking insert method
        wordDictionary.insertWord("Java", "Is a object oriented programming language");
        wordDictionary.insertWord("Java", "Is a great programming language");
        wordDictionary.insertWord("React", "Is a open sourced front end framework developed on JS");
        wordDictionary.insertWord("MySQL", "Is an Oracle-backed open source relational database management system (RDBMS)");

        //Invoking findDefinition method
        wordDictionary.findDefinition("MySQL");

        //Invoking partialSearch method
        wordDictionary.partialSearch("Re");

        //Invoking remove method
        wordDictionary.remove("React");
    }
}