package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import edu.smu.tspell.wordnet.*;

public class DictionaryMain
{
	public static void main(String[] args)
	{
		BufferedReader br = null;
		Writer writer = null;

		try {
			 
		    writer = new BufferedWriter(new OutputStreamWriter(
			          new FileOutputStream("res/top20k_meaning.txt"), "utf-8"));
		
			String sCurrentLine;
 			br = new BufferedReader(new FileReader("res/top20k.txt"));
			
			while ((sCurrentLine = br.readLine()) != null) {
				getMeaning(sCurrentLine.toLowerCase(), writer);
			}
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
 
	}
	
	
	public static String getMeaningWithSentence(Synset[] synsets, int i){
		String meaningWithSentence = synsets[i].getDefinition();
		String UsageExample[] = synsets[i].getUsageExamples();
		if(UsageExample.length>0){
			meaningWithSentence += "&&" + UsageExample[0];
		}
		return meaningWithSentence;
	}
	
	public static void getMeaning(String wordForm, Writer writer) throws IOException
	{
			WordNetDatabase database = WordNetDatabase.getFileInstance();
			Synset[] synsets = database.getSynsets(wordForm);

			int noun_count = 0;
			int verb_count = 0;
			int adverb_count = 0;
			int adjective_count = 0;
			int adjective_satellite = 0;
			int rem = 0;
			
			if (synsets.length > 0)
			{
				writer.write(wordForm + "\n");
				
				for (int i = 0; i < synsets.length; i++)
				{
					
					if(synsets[i].getType() == SynsetType.NOUN)
						noun_count++;
					else if(synsets[i].getType() == SynsetType.VERB)
						verb_count++;
					else if(synsets[i].getType() == SynsetType.ADJECTIVE)
						adjective_count++;
					else if(synsets[i].getType() == SynsetType.ADVERB)
						adverb_count++;
					else if(synsets[i].getType() == SynsetType.ADJECTIVE_SATELLITE)
						adjective_satellite++;
					else
						rem++;
				
					writer.write(getMeaningWithSentence(synsets, i) + "\n");
				}
				writer.write(noun_count + " " + verb_count + " " + adjective_count + " " + adverb_count + " " + adjective_satellite + " " + rem + "\n#\n");
			}
		}
		
	

}