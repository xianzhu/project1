package com.cv.kdata.response;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.cv.kdata.xsbresponse.EntMatch;

public class XsbListMatchingResponse extends ResponseObject{
	@JSONField(ordinal = 3) // 新三板公司对标列表, 取前十
	public List<EntMatch> xsb_matching_list;
	@JSONField(ordinal = 4) // 上市公司对标列表, 取前十
	public List<EntMatch> list_matching_list;
}
