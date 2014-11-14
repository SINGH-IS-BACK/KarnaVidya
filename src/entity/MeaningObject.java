package entity;

import java.util.ArrayList;
import java.util.Set;

public class MeaningObject {

	String word;
	String word_tamil;
	Set<String> synonym_en;
	String Part_of_speech;	//noun, verb, adjective, adverb, adjective_satellite
	ArrayList<String> meaning_en;
	ArrayList<String> meaning_ta;
	
	public String getWord_tamil() {
		return word_tamil;
	}
	public void setWord_tamil(String word_tamil) {
		this.word_tamil = word_tamil;
	}
	public Set<String> getSynonym_en() {
		return synonym_en;
	}
	public void setSynonym_en(Set<String> synonym_en) {
		this.synonym_en = synonym_en;
	}
	
	public ArrayList<String> getMeaning_en() {
		return meaning_en;
	}
	public void setMeaning_en(ArrayList<String> meaning_en) {
		this.meaning_en = meaning_en;
	}
	public ArrayList<String> getMeaning_ta() {
		return meaning_ta;
	}
	public void setMeaning_ta(ArrayList<String> meaning_ta) {
		this.meaning_ta = meaning_ta;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	
	public String getPart_of_speech() {
		return Part_of_speech;
	}
	public void setPart_of_speech(String part_of_speech) {
		Part_of_speech = part_of_speech;
	}
	
	
}
