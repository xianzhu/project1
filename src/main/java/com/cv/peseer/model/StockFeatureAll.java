package com.cv.peseer.model;

import java.io.Serializable;

public class StockFeatureAll implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String stockCode;

    private String cnName;

    private Long stockType;

    private String csrcIndustry;

    private String emIndustry;

    private Long topicIndustry;

    private String prov;

    private Long isDev;

    private Long chairmanCumCeo;

    private Long isStateHold;

    private Double top1Hold;

    private Double top10Hold;

    private Double shareConc;

    private Double shareBalance;

    private Double personHold;

    private Long ceoEducation;

    private Long ceoAge;

    private Double pe;

    private Double pb;

    private Double ps;

    private Double totalStock;

    private Double marketCap;

    private Double netProfit;

    private Double optProfitGrowth;

    private Double netProfitOnAsset;

    private Double optProfit;

    private Double totalAsset;

    private Double profit;

    private Double profitGrowth;

    private Double totalAssetGrowth;

    private Double holderEquityOnLiab;

    private Double liabRatio;

    private Double optIncome;

    private Double assetTurnover;

    private Double netProfitOnIncome;

    private Double optProfitOnIncome;

    private Double netProfitMargin;

    private Double maFeeOnIncome;

    private Double tradeCycle;

    private Double daysInInventory;

    private Double interestLiabOnCapital;

    private Double currentAssetRatio;

    private Double tangibleAssetRatio;

    private Double roa;

    private Double nonCurrentAssetRatio;

    private Double tangibleAssetOnLiab;

    private Double currentLiabRatio;

    private Double grossProfitMargin;

    private Double optCostOnIncome;

    private Double roic;

    private Double optIncomeGrowth;

    private Double roe;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode == null ? null : stockCode.trim();
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName == null ? null : cnName.trim();
    }

    public Long getStockType() {
        return stockType;
    }

    public void setStockType(Long stockType) {
        this.stockType = stockType;
    }

    public String getCsrcIndustry() {
        return csrcIndustry;
    }

    public void setCsrcIndustry(String csrcIndustry) {
        this.csrcIndustry = csrcIndustry == null ? null : csrcIndustry.trim();
    }

    public String getEmIndustry() {
        return emIndustry;
    }

    public void setEmIndustry(String emIndustry) {
        this.emIndustry = emIndustry == null ? null : emIndustry.trim();
    }

    public Long getTopicIndustry() {
        return topicIndustry;
    }

    public void setTopicIndustry(Long topicIndustry) {
        this.topicIndustry = topicIndustry;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov == null ? null : prov.trim();
    }

    public Long getIsDev() {
        return isDev;
    }

    public void setIsDev(Long isDev) {
        this.isDev = isDev;
    }

    public Long getChairmanCumCeo() {
        return chairmanCumCeo;
    }

    public void setChairmanCumCeo(Long chairmanCumCeo) {
        this.chairmanCumCeo = chairmanCumCeo;
    }

    public Long getIsStateHold() {
        return isStateHold;
    }

    public void setIsStateHold(Long isStateHold) {
        this.isStateHold = isStateHold;
    }

    public Double getTop1Hold() {
        return top1Hold;
    }

    public void setTop1Hold(Double top1Hold) {
        this.top1Hold = top1Hold;
    }

    public Double getTop10Hold() {
        return top10Hold;
    }

    public void setTop10Hold(Double top10Hold) {
        this.top10Hold = top10Hold;
    }

    public Double getShareConc() {
        return shareConc;
    }

    public void setShareConc(Double shareConc) {
        this.shareConc = shareConc;
    }

    public Double getShareBalance() {
        return shareBalance;
    }

    public void setShareBalance(Double shareBalance) {
        this.shareBalance = shareBalance;
    }

    public Double getPersonHold() {
        return personHold;
    }

    public void setPersonHold(Double personHold) {
        this.personHold = personHold;
    }

    public Long getCeoEducation() {
        return ceoEducation;
    }

    public void setCeoEducation(Long ceoEducation) {
        this.ceoEducation = ceoEducation;
    }

    public Long getCeoAge() {
        return ceoAge;
    }

    public void setCeoAge(Long ceoAge) {
        this.ceoAge = ceoAge;
    }

    public Double getPe() {
        return pe;
    }

    public void setPe(Double pe) {
        this.pe = pe;
    }

    public Double getPb() {
        return pb;
    }

    public void setPb(Double pb) {
        this.pb = pb;
    }

    public Double getPs() {
        return ps;
    }

    public void setPs(Double ps) {
        this.ps = ps;
    }

    public Double getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(Double totalStock) {
        this.totalStock = totalStock;
    }

    public Double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(Double marketCap) {
        this.marketCap = marketCap;
    }

    public Double getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(Double netProfit) {
        this.netProfit = netProfit;
    }

    public Double getOptProfitGrowth() {
        return optProfitGrowth;
    }

    public void setOptProfitGrowth(Double optProfitGrowth) {
        this.optProfitGrowth = optProfitGrowth;
    }

    public Double getNetProfitOnAsset() {
        return netProfitOnAsset;
    }

    public void setNetProfitOnAsset(Double netProfitOnAsset) {
        this.netProfitOnAsset = netProfitOnAsset;
    }

    public Double getOptProfit() {
        return optProfit;
    }

    public void setOptProfit(Double optProfit) {
        this.optProfit = optProfit;
    }

    public Double getTotalAsset() {
        return totalAsset;
    }

    public void setTotalAsset(Double totalAsset) {
        this.totalAsset = totalAsset;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getProfitGrowth() {
        return profitGrowth;
    }

    public void setProfitGrowth(Double profitGrowth) {
        this.profitGrowth = profitGrowth;
    }

    public Double getTotalAssetGrowth() {
        return totalAssetGrowth;
    }

    public void setTotalAssetGrowth(Double totalAssetGrowth) {
        this.totalAssetGrowth = totalAssetGrowth;
    }

    public Double getHolderEquityOnLiab() {
        return holderEquityOnLiab;
    }

    public void setHolderEquityOnLiab(Double holderEquityOnLiab) {
        this.holderEquityOnLiab = holderEquityOnLiab;
    }

    public Double getLiabRatio() {
        return liabRatio;
    }

    public void setLiabRatio(Double liabRatio) {
        this.liabRatio = liabRatio;
    }

    public Double getOptIncome() {
        return optIncome;
    }

    public void setOptIncome(Double optIncome) {
        this.optIncome = optIncome;
    }

    public Double getAssetTurnover() {
        return assetTurnover;
    }

    public void setAssetTurnover(Double assetTurnover) {
        this.assetTurnover = assetTurnover;
    }

    public Double getNetProfitOnIncome() {
        return netProfitOnIncome;
    }

    public void setNetProfitOnIncome(Double netProfitOnIncome) {
        this.netProfitOnIncome = netProfitOnIncome;
    }

    public Double getOptProfitOnIncome() {
        return optProfitOnIncome;
    }

    public void setOptProfitOnIncome(Double optProfitOnIncome) {
        this.optProfitOnIncome = optProfitOnIncome;
    }

    public Double getNetProfitMargin() {
        return netProfitMargin;
    }

    public void setNetProfitMargin(Double netProfitMargin) {
        this.netProfitMargin = netProfitMargin;
    }

    public Double getMaFeeOnIncome() {
        return maFeeOnIncome;
    }

    public void setMaFeeOnIncome(Double maFeeOnIncome) {
        this.maFeeOnIncome = maFeeOnIncome;
    }

    public Double getTradeCycle() {
        return tradeCycle;
    }

    public void setTradeCycle(Double tradeCycle) {
        this.tradeCycle = tradeCycle;
    }

    public Double getDaysInInventory() {
        return daysInInventory;
    }

    public void setDaysInInventory(Double daysInInventory) {
        this.daysInInventory = daysInInventory;
    }

    public Double getInterestLiabOnCapital() {
        return interestLiabOnCapital;
    }

    public void setInterestLiabOnCapital(Double interestLiabOnCapital) {
        this.interestLiabOnCapital = interestLiabOnCapital;
    }

    public Double getCurrentAssetRatio() {
        return currentAssetRatio;
    }

    public void setCurrentAssetRatio(Double currentAssetRatio) {
        this.currentAssetRatio = currentAssetRatio;
    }

    public Double getTangibleAssetRatio() {
        return tangibleAssetRatio;
    }

    public void setTangibleAssetRatio(Double tangibleAssetRatio) {
        this.tangibleAssetRatio = tangibleAssetRatio;
    }

    public Double getRoa() {
        return roa;
    }

    public void setRoa(Double roa) {
        this.roa = roa;
    }

    public Double getNonCurrentAssetRatio() {
        return nonCurrentAssetRatio;
    }

    public void setNonCurrentAssetRatio(Double nonCurrentAssetRatio) {
        this.nonCurrentAssetRatio = nonCurrentAssetRatio;
    }

    public Double getTangibleAssetOnLiab() {
        return tangibleAssetOnLiab;
    }

    public void setTangibleAssetOnLiab(Double tangibleAssetOnLiab) {
        this.tangibleAssetOnLiab = tangibleAssetOnLiab;
    }

    public Double getCurrentLiabRatio() {
        return currentLiabRatio;
    }

    public void setCurrentLiabRatio(Double currentLiabRatio) {
        this.currentLiabRatio = currentLiabRatio;
    }

    public Double getGrossProfitMargin() {
        return grossProfitMargin;
    }

    public void setGrossProfitMargin(Double grossProfitMargin) {
        this.grossProfitMargin = grossProfitMargin;
    }

    public Double getOptCostOnIncome() {
        return optCostOnIncome;
    }

    public void setOptCostOnIncome(Double optCostOnIncome) {
        this.optCostOnIncome = optCostOnIncome;
    }

    public Double getRoic() {
        return roic;
    }

    public void setRoic(Double roic) {
        this.roic = roic;
    }

    public Double getOptIncomeGrowth() {
        return optIncomeGrowth;
    }

    public void setOptIncomeGrowth(Double optIncomeGrowth) {
        this.optIncomeGrowth = optIncomeGrowth;
    }

    public Double getRoe() {
        return roe;
    }

    public void setRoe(Double roe) {
        this.roe = roe;
    }
}