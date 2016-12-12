package com.cv.peseer.model;

public class WordWeight  implements Comparable<WordWeight>{
	public String word;
	public double weight;
	
	public WordWeight(String word, double weight)
	{
		this.weight = weight;
		this.word = word;
	}

	@Override
	public int compareTo(WordWeight o) {
		if (weight>o.weight){
			return 1;
		}else if (weight<o.weight){
			return -1;
		}else{
			return 0;
		}
	}
}
