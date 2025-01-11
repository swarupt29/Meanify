package com.example.dictionary.model;

public class DefinitionModel {
	private String partOfSpeech;
	private String definition;

	public DefinitionModel(String partOfSpeech, String definition) {
		this.partOfSpeech = partOfSpeech;
		this.definition = definition;
	}

	public DefinitionModel() {
		// TODO Auto-generated constructor stub
	}

	public String getPartOfSpeech() {
		return partOfSpeech;
	}

	public void setPartOfSpeech(String partOfSpeech) {
		this.partOfSpeech = partOfSpeech;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}
}
