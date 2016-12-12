package com.cv.peseer.xsbresponse;

import com.alibaba.fastjson.annotation.JSONField;

public class EntMatch {
	@JSONField(ordinal = 1)
	public String stock_code;
	@JSONField(ordinal = 2)
	public String company_name;
	@JSONField(ordinal = 3)
	public String is_state_hold;
	@JSONField(ordinal = 4)
	public Double total_asset;
	@JSONField(ordinal = 5)
	public Double opt_income; // 营业收入(元)
	@JSONField(ordinal = 6)
	public Double opt_profit; // 营业利润(元)
	@JSONField(ordinal = 7)
	public Double opt_profit_on_income; // 营业利润／营业总收入(%)
	@JSONField(ordinal = 8)
	public Double liab_ratio; // 资产负债率(%)
	@JSONField(ordinal = 9)
	public Double gross_profit_margin; // 销售毛利率(%)
}
