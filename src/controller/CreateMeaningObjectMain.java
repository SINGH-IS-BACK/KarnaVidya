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
			          new FileOutputStream("5000_meaningObject.txt"), "utf-8"));
			String sCurrentLine;
 
			br_en = new BufferedReader(new FileReader("5000_meaning.txt"));

			while ((sCurrentLine = br_en.readLine()) != null) {
				
				MeaningObject meaningObject = new MeaningObject();
				ArrayList<String> meaning_en = new ArrayList<String>();
				ArrayList<String> meaning_ta = new ArrayList<String>();
			
				
				meaningObject.setWord(sCurrentLine);
				//meaningObject.setWord_tamil(br_ta.readLine());
				meaningObject.setWord_tamil("");
				meaningObject.setSynonym_en(getSynonym(sCurrentLine));
				
				while (!((sCurrentLine = br_en.readLine())).contains("#"))
				{
					System.out.println(sCurrentLine);
					meaning_en.add(sCurrentLine);
					//meaning_ta.add(br_ta.readLine());
				}
				//br_ta.readLine();
				meaningObject.setPart_of_speech(meaning_en.remove(meaning_en.size()-1));
				meaningObject.setMeaning_en(meaning_en);
				
				//meaning_ta.remove(meaning_ta.size()-1);
				meaningObject.setMeaning_ta(meaning_ta);
				
				Gson gson = new Gson();
				System.out.println(gson.toJson(meaningObject).toString());
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