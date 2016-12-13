package com.cv.peseer.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

public class WordSegmentUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(WordSegmentUtil.class);

	private static final String ltp_url = "http://10.27.70.43:12345/ltp";
	private static final String b = "b";
	private static final String ws = "ws";

	public static List<NameValuePair> getPara(String text, String format, String func) {
		List<NameValuePair> res = new ArrayList<NameValuePair>();
		res.add(new BasicNameValuePair("s", text));
		res.add(new BasicNameValuePair("x", format));
		res.add(new BasicNameValuePair("t", func));

		return res;
	}

	/**
	 * <?xml version="1.0" encoding="utf-8" ?>
	 * <xml4nlp> <note sent="y" word="y" pos="n" ne="n" parser="n" wsd="n" srl=
	 * "n" /> <doc> <para id="0"> <sent id="0" cont="财富管理"> <word id="0" cont=
	 * "财富" /> <word id="1" cont="管理" /> </sent> </para> </doc> </xml4nlp>
	 *
	 * @param xml
	 * @return
	 * @throws SAXException
	 * @throws UnsupportedEncodingException
	 * @throws DocumentException
	 */
	private static List<String> parseXml(String xml)
			throws SAXException, UnsupportedEncodingException, DocumentException {

		List<String> wordList = new ArrayList<>();
		SAXReader saxReader = new SAXReader();

		Document document = saxReader.read(new ByteArrayInputStream(xml.getBytes("UTF-8")));
		Element root = document.getRootElement();// xml4nlp
		Iterator sentIterator = root.element("doc").element("para").elementIterator();
		while (sentIterator.hasNext()) {
			Element sent = (Element) sentIterator.next();
			Iterator wordIterator = sent.elementIterator();
			while (wordIterator.hasNext()) {
				String content = ((Element) wordIterator.next()).attributeValue("cont");
				if (content.length()<2)
					continue;
				wordList.add(content);
			}
		}

		return wordList;
	}

		public static List<String> getWordSegment(String key) {
		List<String> wordList = null;
		CloseableHttpResponse resp = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost hp = new HttpPost(ltp_url);

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params = getPara(key, b, ws);

			hp.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
			resp = httpclient.execute(hp);

			HttpEntity ent = resp.getEntity();
			String result = EntityUtils.toString(ent);
			result = new String(result.getBytes("ISO-8859-1"), "UTF-8");
			EntityUtils.consume(ent);
			wordList = parseXml(result);
		} catch (IOException | SAXException | DocumentException e) {
			LOGGER.error("", e);
		} finally {
			try {
				httpclient.close();

				if (resp != null) {
					resp.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return wordList;
	}

	public static void main(String[] args) throws IOException, SAXException, DocumentException {
		//System.out.println(new Date());
		long start = System.currentTimeMillis();
		//for (int index=0; index<1000; index++) {
			List<String> aaa = getWordSegment("任志强是个好人");
			for (String item:aaa){
				System.out.println(item);
			}
		//}
		long end = System.currentTimeMillis();
		System.out.println("cost time:"+(end-start));
//		aaa.forEach(o ->System.out.println(o));
//		System.out.println(new Date()+"\t"+aaa.size());
//		StringBuffer sb = new StringBuffer();
//		BufferedReader br = new BufferedReader(new FileReader(new File("e:/ltpClient.txt")));
//		String line = null;
//		while ((line = br.readLine()) != null) {
//			sb.append(line).append("\n");
//		}
//		br.close();
//		List<String> wordList = parseXml(sb.toString());
//		for (String word : wordList) {
//			System.out.println(word);
//		}
	}
}
