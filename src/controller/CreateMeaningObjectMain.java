package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

import com.google.gson.Gson;
import edu.smu.tspell.wordnet.*;
import entity.MeaningObject;

public class CreateMeaningObjectMain
{
	public static void main(String[] args)
	{
		BufferedReader br_en = null;
		Writer writer = null;
		try {
			 
		    writer = new BufferedWriter(new OutputStreamWriter(
			          new FileOutputStream("res/top20k_meaningObject.txt"), "utf-8"));
			String sCurrentLine;
 
			br_en = new BufferedReader(new FileReader("res/top20k_meaning.txt"));

			while ((sCurrentLine = br_en.readLine()) != null) {
				
				MeaningObject meaningObject = new MeaningObject();
				LinkedHashMap<String, String> meaning_en = new LinkedHashMap<String, String>();
				LinkedHashMap<String, String> meaning_ta = new LinkedHashMap<String, String>();
			
				
				meaningObject.setWord(sCurrentLine);
				//meaningObject.setWord_tamil(br_ta.readLine());
				meaningObject.setWord_tamil("");
				meaningObject.setSynonym_en(getSynonym(sCurrentLine));
				
				ArrayList<String> meaning_en_arraylist = new ArrayList<String>();
				//ArrayList<String> meaning_ta_arraylist = new ArrayList<String>();
				while (!((sCurrentLine = br_en.readLine())).contains("#"))
				{
					meaning_en_arraylist.add(sCurrentLine);
					//meaning_ta_arraylist.add(sCurrentLine);
				}
				
				meaning_en = getMeaningLinkedMap(meaning_en_arraylist);
				//meaning_ta = getMeaningLinkedMap(meaning_ta_arraylist);
				
				meaningObject.setPart_of_speech(meaning_en_arraylist.get(meaning_en_arraylist.size()-1));
				//br_ta.readLine();
				meaningObject.setMeaning_en(meaning_en);
				meaningObject.setMeaning_ta(meaning_ta);
				
				Gson gson = new Gson();
				//System.out.println(gson.toJson(meaningObject).toString());
			    writer.write(gson.toJson(meaningObject).toString());
			    writer.write("\n");
				
			}
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br_en != null)br_en.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
 
	}
	
	private static LinkedHashMap<String, String> getMeaningLinkedMap(
			ArrayList<String> meaning_en_arraylist) {
		LinkedHashMap<String, String> meaningLinkedMap = new LinkedHashMap<String, String>();
		for(int i = 0 ; i< meaning_en_arraylist.size()-1;i++){
			String meaning_sentence[] = meaning_en_arraylist.get(i).split("&&");
			if(meaning_sentence.length>1)
				meaningLinkedMap.put(meaning_sentence[0],meaning_sentence[1]);
			else
				meaningLinkedMap.put(meaning_sentence[0],"");
		}
		return meaningLinkedMap;
	}

	public static Set<String> getSynonym (String word){
		Set<String> synonyms = new HashSet<String>();
		WordNetDatabase database = WordNetDatabase.getFileInstance();
		Synset[] synsets = database.getSynsets(word);
		for(int i = 0; i < synsets.length ; i++){
			String[] wordForms = synsets[i].getWordForms();
			for(int j = 0 ; j < wordForms.length ; j++){
				synonyms.add(wordForms[j]);
			}
		}
		return synonyms;
	}
}