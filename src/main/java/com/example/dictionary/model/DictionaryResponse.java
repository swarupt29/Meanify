package com.example.dictionary.model;

import java.util.List;

public class DictionaryResponse {
	private String word;
	private List<Meaning> meanings;

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public List<Meaning> getMeanings() {
		return meanings;
	}

	public void setMeanings(List<Meaning> meanings) {
		this.meanings = meanings;
	}
}
