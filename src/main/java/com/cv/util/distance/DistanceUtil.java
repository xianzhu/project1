package com.cv.util.distance;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.peseer.cont.RDDWebConst;
import com.cv.peseer.model.WordWeight;


public class DistanceUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(DistanceUtil.class);

	private static final String SERVER_IP = RDDWebConst.TOPICSERVICEIP;
	private static final int PORT_WORD = 5555;
	private static final int PORT_PHRASE_MACRO = 5556;
	private static final int PORT_PHRASE_MICRO = 5558;

	//精准搜索里面查找不到的指定词的情况下, 搜索该服务
	private static final int PORT_4_EXCEPTION_SEARCH = 5559;

	private static final int TIMEOUT = 30000;

	public static String replaceSpecialChars(String extendWords) {
		if (extendWords == null) {
			return null;
		}

		extendWords = extendWords.replace("合伙人_", "");
		extendWords = extendWords.replace("_领", "");
		extendWords = extendWords.replace("_", "");
		extendWords = extendWords.replace("。", "");

		return extendWords;
	}

	private static void mergeResult(HashSet<String> resultSet, List<WordWeight> wordWeightList) {
		for (WordWeight wordWeight : wordWeightList) {
			if (!StringUtils.isEmpty(wordWeight.word)) {
				resultSet.add(replaceSpecialChars(wordWeight.word));
			}
		}
	}

	public static Set<String> getAllRelatedUserOrg(List<String> wordList) {
		HashSet<String> user_organize_entSet = new HashSet<>();
		//System.out.println("begin 1 "+System.currentTimeMillis());
		mergeResult(user_organize_entSet, getWeight(wordList, "macro"));
		//System.out.println("end 1 "+System.currentTimeMillis());
		//System.out.println("begin 2 "+System.currentTimeMillis());
		mergeResult(user_organize_entSet, getWeight(wordList, "micro"));
		//System.out.println("end 2 "+System.currentTimeMillis());

		return user_organize_entSet;
	}

	public static List<WordWeight> getWeight(List<String> wordList, String type) {
		if (null == wordList) {
			throw new InvalidParameterException("wordList can not be empty!!");
		}

		String word = null;
		Integer port = 0;

		if (wordList.size() == 1) {
			word = wordList.get(0);
			port = PORT_WORD;
		} else {
			StringBuffer sb = new StringBuffer();
			for (String w : wordList) {
				sb.append(w).append(" ");
			}
			word = sb.toString().trim();
			port = "macro".equalsIgnoreCase(type) ? PORT_PHRASE_MACRO : PORT_PHRASE_MICRO;
		}

		return doQuery2(word, SERVER_IP, port);
	}

	public static List<WordWeight> getSingleWordWeigt(String word) {
		return doQuery2(word, SERVER_IP, PORT_WORD);
	}

	//在经过搜索 org_user_info,organize_info,org_usr_info_cvs都找不到有用信息的情况下, 调用该服务
	public static List<WordWeight> getExceptionWordWeight(List<String> wordList)
	{
		StringBuffer sb = new StringBuffer();
		for (String w : wordList) {
			sb.append(w).append(" ");
		}
		String word = sb.toString().trim();
		return doQuery2(word, SERVER_IP, PORT_4_EXCEPTION_SEARCH);
	}

	private static List<WordWeight> doQuery2(String word, String host, int port) {
		List<WordWeight> wordWeightList = new ArrayList<>();
		TTransport transport = null;
		try {
			transport = new TSocket(host, port, TIMEOUT);

			// 协议要和服务端一致
			long start = System.currentTimeMillis();
			TProtocol protocol = new TBinaryProtocol(transport);
			Distance.Client client = new Distance.Client(protocol);
			transport.open();
			long end = System.currentTimeMillis();
			System.out.println("cost time:"+(end-start));
			QueryResult result = client.doQuery(word);

			if (result != null) {
				if (result.isSetWordDistList()) {
					for (WordDist wordDist : result.getWordDistList()) {
						WordWeight wordWeight = new WordWeight(wordDist.bestWord, wordDist.bestDist);
						wordWeightList.add(wordWeight);
					}
				}
			}
		} catch (TException e) {
			LOGGER.error("", e);
		} finally {
			if (transport!=null){
				transport.close();
			}
		}
		return wordWeightList;
	}

	public static void main(String[] args) {
//		List<String> aaa = new ArrayList<>();
//		aaa.add("真格");
//		aaa.add("基金");
//		System.out.println(getAllRelatedUserOrg(aaa));

		List<String> aaa = new ArrayList<>();
		aaa.add("人工智能");
		//aaa.add("智能");

		long start = System.currentTimeMillis();
	//	List<WordWeight> exceptionWordList = getExceptionWordWeight(aaa);
		List<WordWeight> exceptionWordList = getExceptionWordWeight(aaa);
		long end = System.currentTimeMillis();
		System.out.println("cost time:"+(end-start));

//		for (WordWeight wordWeight:exceptionWordList){
//			System.out.println(wordWeight.word);
//		}

	}
}
