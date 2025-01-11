package com.example.dictionary.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.example.dictionary.model.Definition;
import com.example.dictionary.model.DefinitionModel;
import com.example.dictionary.model.DictionaryResponse;
import com.example.dictionary.model.Meaning;

@Controller
public class DictionaryController {

    // Handles requests to the root URL and displays the home page
    @GetMapping("/")
    public String index() {
        return "index"; // Renders the index.html file
    }

    // Handles requests to fetch the definition of a word
    @GetMapping("/result")
    public String getWordDefinition(@RequestParam("word") String word, Model model) {
        // Base URL for the dictionary API with the word parameter
        String apiUrl = "https://api.dictionaryapi.dev/api/v2/entries/en/" + word;

        try {
            // Create a RestTemplate instance to make API calls
            RestTemplate restTemplate = new RestTemplate();

            // Fetch the dictionary response as an array of DictionaryResponse objects
            DictionaryResponse[] response = restTemplate.getForObject(apiUrl, DictionaryResponse[].class);

            // List to store the extracted definitions
            List<DefinitionModel> definitions = new ArrayList<>();

            if (response != null && response.length > 0) {
                DictionaryResponse wordData = response[0]; // Get the first response object

                // Add the word to the model for display on the result page
                model.addAttribute("word", wordData.getWord());

                // Extract meanings and definitions from the API response
                if (wordData.getMeanings() != null) {
                    for (Meaning meaning : wordData.getMeanings()) {
                        for (Definition definition : meaning.getDefinitions()) {
                            // Create a DefinitionModel object for each definition
                            DefinitionModel definitionModel = new DefinitionModel();
                            definitionModel.setPartOfSpeech(meaning.getPartOfSpeech());
                            definitionModel.setDefinition(definition.getDefinition());
                            definitions.add(definitionModel); // Add to the list
                        }
                    }
                }
            } else {
                // Handle the case where no definitions are found
                definitions.add(new DefinitionModel("N/A", "No definitions found for the word."));
            }

            // Add the list of definitions to the model for display
            model.addAttribute("definitions", definitions);
        } catch (Exception e) {
            // Handle exceptions, such as network errors or invalid responses
            model.addAttribute("word", word);
            model.addAttribute("definitions", List.of(
                new DefinitionModel("N/A", "Unable to fetch the definition.")
            ));
        }

        return "result"; // Renders the result.html file
    }
}
