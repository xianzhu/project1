package com.cv.kdata.util;

import java.util.HashSet;
import java.util.List;

import com.cv.kdata.model.WordWeight;
import com.cv.util.distance.DistanceUtil;



public class ExtendInfoUtil {
	
	public static HashSet<String> getAllWord(String key) {
		HashSet<String> allWordSet = new HashSet<>();

		List<String> wordList = WordSegmentUtil.getWordSegment(key);
		if(wordList.isEmpty()){
			wordList.add(key);
		}
		
		if (wordList.size() == 1) {
			List<WordWeight> singleWordWeightList = DistanceUtil.getSingleWordWeigt(wordList.get(0));
			singleWordWeightList.forEach(item -> allWordSet.add(item.word));
			return removeUnusedChars(allWordSet);
		}
		List<WordWeight> wordWeightList_macro = DistanceUtil.getWeight(wordList, "macro");
		wordWeightList_macro.forEach(item -> allWordSet.add(item.word));
		List<WordWeight> wordWeightList_micro = DistanceUtil.getWeight(wordList, "micro");
		wordWeightList_micro.forEach(item -> allWordSet.add(item.word));

		return removeUnusedChars(allWordSet);
	}

	private static HashSet<String> removeUnusedChars(HashSet<String> srcWordSets) {
		HashSet<String> resultSet = new HashSet<>();
		for (String word : srcWordSets) {
			if (word.length() < 2) {
				continue;
			}
			word = word.replace("'", "");
			word = word.replace("合伙人_", "");
			word = word.replace("。", "");
			if (word.endsWith("_领")) {
				word = word.replace("_领", "");
			}
			word = word.replace("_", "");
			resultSet.add(word.trim());
		}
		return resultSet;
	}

	public static void main(String[] args) {
		// String string =
		// "计算机软件的开发、设计、制作，网络技术的开发、设计，销售自产产品，并提供相关的技术咨询和技术服务；办公用品、食用农副产品（不含生猪、牛羊肉等家禽产品）、日用品、工艺品（文物除外）、电子产品、食品、服饰与配件、塑料制品、电动自行车及零部件、包装材料、箱包的批发、网上零售、进出口、佣金代理（拍卖除外）；餐饮管理；企业管理咨询，企业营销策划。设计、制作、发布、代理国内外各类广告。（不涉及国营贸易管理商品，涉及配额、许可证管理商品，按照国家有关规定办理）。【依法须经批准的项目，经相关部门批准后方可开展经营活动】
		// ";
		// List<String> wordList = WordSegmentUtil.getWordSegment(string);
		// for (String word:wordList){
		// System.out.print(word+ " ");
		// }
		// String key = "携程 ";
		// String key = "章苏阳";
		// String key = "汤忠一";
		String key = "红杉";

		for (int index = 0; index < 100; index++) {
			HashSet<String> all_word = getAllWord(key);
			System.out.println(all_word.size());
		}

	//	HashSet<String> filter = new HashSet<>();
		// System.out.println(all_word.size());
		// for (String word : all_word) {
		// System.out.println(word+", length:"+word.length());
		// if (word.length()>1){
		// filter.add(word);
		// }
		// }

		// System.out.println(filter.size());

	}

}
