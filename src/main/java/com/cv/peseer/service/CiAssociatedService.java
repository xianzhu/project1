package com.cv.peseer.service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cv.peseer.cont.RDDWebConst;
import com.cv.peseer.dao.CiChnFilmBoxMapper;
import com.cv.peseer.dao.CiCorePayShareMapper;
import com.cv.peseer.dao.CiCptocScaMapper;
import com.cv.peseer.dao.CiDtourIncomeMapper;
import com.cv.peseer.dao.CiOlVideoIncmMapper;
import com.cv.peseer.datasource.DBContextHolder;
import com.cv.peseer.model.CiChnFilmBox;
import com.cv.peseer.model.CiCorePayShare;
import com.cv.peseer.model.CiCptocSca;
import com.cv.peseer.model.CiDtourIncome;
import com.cv.peseer.model.CiOlVideoIncm;
import com.cv.peseer.model.ci.CiAbvLimCrs;
import com.cv.peseer.model.ci.CiAddRmbLoan;
import com.cv.peseer.model.ci.CiAgriculturalOutput;
import com.cv.peseer.model.ci.CiAirCleanerSales;
import com.cv.peseer.model.ci.CiAutomatSalInv;
import com.cv.peseer.model.ci.CiBcAvgAmo;
import com.cv.peseer.model.ci.CiBitPrice;
import com.cv.peseer.model.ci.CiCbEcSca;
import com.cv.peseer.model.ci.CiCcIndex;
import com.cv.peseer.model.ci.CiCcNum;
import com.cv.peseer.model.ci.CiCeVecSales;
import com.cv.peseer.model.ci.CiChnFilmStat;
import com.cv.peseer.model.ci.CiChnNetizenNet;
import com.cv.peseer.model.ci.CiChsArea;
import com.cv.peseer.model.ci.CiCoScPropt;
import com.cv.peseer.model.ci.CiCourierBizSrvy;
import com.cv.peseer.model.ci.CiCsOrgFundSca;
import com.cv.peseer.model.ci.CiDmpDeli;
import com.cv.peseer.model.ci.CiEightRRRValue;
import com.cv.peseer.model.ci.CiEngelCoefficient;
import com.cv.peseer.model.ci.CiEntDayStat;
import com.cv.peseer.model.ci.CiEntMonStat;
import com.cv.peseer.model.ci.CiFreVolGrowth;
import com.cv.peseer.model.ci.CiGdp;
import com.cv.peseer.model.ci.CiGeneCoefficient;
import com.cv.peseer.model.ci.CiHotAppUse;
import com.cv.peseer.model.ci.CiHsConsuLoan;
import com.cv.peseer.model.ci.CiHunderdCareerSd;
import com.cv.peseer.model.ci.CiIndHoursLoan;
import com.cv.peseer.model.ci.CiJpnRetail;
import com.cv.peseer.model.ci.CiLcGdp;
import com.cv.peseer.model.ci.CiLusConsuSca;
import com.cv.peseer.model.ci.CiManufPmi;
import com.cv.peseer.model.ci.CiMbShopSca;
import com.cv.peseer.model.ci.CiMiaData;
import com.cv.peseer.model.ci.CiMigrantWorkerInc;
import com.cv.peseer.model.ci.CiMigrantWorkerSca;
import com.cv.peseer.model.ci.CiMoneySup;
import com.cv.peseer.model.ci.CiNetScy;
import com.cv.peseer.model.ci.CiNewEmpPopulation;
import com.cv.peseer.model.ci.CiNhreConsmpSpeed;
import com.cv.peseer.model.ci.CiNonManufPmi;
import com.cv.peseer.model.ci.CiNsFundSca;
import com.cv.peseer.model.ci.CiOlGameIncm;
import com.cv.peseer.model.ci.CiOlShopPr;
import com.cv.peseer.model.ci.CiOlgIncomComp;
import com.cv.peseer.model.ci.CiOlgIncomSac;
import com.cv.peseer.model.ci.CiOlgUserSca;
import com.cv.peseer.model.ci.CiOlgVideoIncm;
import com.cv.peseer.model.ci.CiOpsStatInstResearch;
import com.cv.peseer.model.ci.CiOpsStatToeMa;
import com.cv.peseer.model.ci.CiOrScPropt;
import com.cv.peseer.model.ci.CiPayRank;
import com.cv.peseer.model.ci.CiPaymComp;
import com.cv.peseer.model.ci.CiPaymSca;
import com.cv.peseer.model.ci.CiPhoneOlShop;
import com.cv.peseer.model.ci.CiPltDistribution;
import com.cv.peseer.model.ci.CiPltGrowth;
import com.cv.peseer.model.ci.CiPopulationSca;
import com.cv.peseer.model.ci.CiPtopNlSca;
import com.cv.peseer.model.ci.CiRcaPay;
import com.cv.peseer.model.ci.CiRegUnempRate;
import com.cv.peseer.model.ci.CiRhfsArea;
import com.cv.peseer.model.ci.CiRifaAmount;
import com.cv.peseer.model.ci.CiSfSca;
import com.cv.peseer.model.ci.CiShtRentSca;
import com.cv.peseer.model.ci.CiSocialInsurance;
import com.cv.peseer.model.ci.CiSpSales;
import com.cv.peseer.model.ci.CiTifaAmout;
import com.cv.peseer.model.ci.CiTotSocialLog;
import com.cv.peseer.model.ci.CiTpFundDisc;
import com.cv.peseer.model.ci.CiTslcAcctGdp;
import com.cv.peseer.model.ci.CiUrPcce;
import com.cv.peseer.model.ci.CiUrResdIncome;
import com.cv.peseer.model.ci.CiUrcSca;
import com.cv.peseer.model.ci.CiUsConsuGrowth;
import com.cv.peseer.model.ci.CiUsCr;
import com.cv.peseer.model.ci.CiUsNetRetail;
import com.cv.peseer.model.ci.CiUsNewEmployee;
import com.cv.peseer.model.ci.CiUsNpRetail;
import com.cv.peseer.model.ci.CiUsPsce;
import com.cv.peseer.model.ci.CiUsedCarSales;
import com.cv.peseer.model.ci.CiWorldOsShare;
import com.cv.peseer.model.ci.CiWtClnSales;
import com.cv.peseer.response.CiAssociatedResponse;
import com.cv.peseer.response.CiResponse;
import com.cv.peseer.util.BeanConverter;
import com.cv.peseer.util.MysqlHelper;
import com.cv.peseer.util.StringUtil;
@Service
public class CiAssociatedService {
	@Autowired
	CiChnFilmBoxMapper ciChnFilmBoxMapper;
	@Autowired
	CiCorePayShareMapper ciCorePayShareMapper;
	@Autowired
	CiCptocScaMapper ciCptocScaMapper;
	@Autowired
	CiDtourIncomeMapper ciDtourIncomeMapper;
	@Autowired
	CiOlVideoIncmMapper ciOlVideoIncmMapper;
	private static Logger logger = LoggerFactory.getLogger(CiAssociatedService.class);
	public void searchCiCptocSca(CiResponse ciResponse) {

		if(RDDWebConst.FAILURE.equals(ciResponse.getStatus())){
			logger.info("the search already failed!");
			return;
		}

		DBContextHolder.setDbType(DBContextHolder.PESEER_RPT);
		List<CiCptocSca> list = ciCptocScaMapper.selectAll();
		ciResponse.setCprocSca(list);
		ciResponse.setStatus(RDDWebConst.SUCCESS);
		ciResponse.setMessage("返回互联网金融-网络众筹-众筹市场规模");
	}

	// 服务消费—全国电影票房收入
	public void searchCiChnFilmBox(CiResponse ciResponse) {
		if(RDDWebConst.FAILURE.equals(ciResponse.getStatus())){
			logger.info("the search already failed!");
			return;
		}
		DBContextHolder.setDbType(DBContextHolder.PESEER_RPT);
		List<CiChnFilmBox> list = ciChnFilmBoxMapper.selectAll();
		ciResponse.setChnFilmBox(list);
		ciResponse.setStatus(RDDWebConst.SUCCESS);
		ciResponse.setMessage("返回服务消费—全国电影票房收入");
	}

	// 互联网金融-第三方支付-核心支付企业市场份额
	public void searchCiCorePayShare(CiResponse ciResponse) {
		if(RDDWebConst.FAILURE.equals(ciResponse.getStatus())){
			logger.info("the search already failed!");
			return;
		}

		DBContextHolder.setDbType(DBContextHolder.PESEER_RPT);
		List<CiCorePayShare> list = ciCorePayShareMapper.selectAll();
		ciResponse.setCorePayShare(list);
		ciResponse.setStatus(RDDWebConst.SUCCESS);
		ciResponse.setMessage("返回互联网金融-第三方支付-核心支付企业市场份额");
	}

	// 服务消费-国内旅游收入
	public void searchCiDtourIncome(CiResponse ciResponse) {
		if(RDDWebConst.FAILURE.equals(ciResponse.getStatus())){
			logger.info("the search already failed!");
			return;
		}

		DBContextHolder.setDbType(DBContextHolder.PESEER_RPT);
		List<CiDtourIncome> list = ciDtourIncomeMapper.selectAll();
		ciResponse.setDtourIncome(list);
		ciResponse.setStatus(RDDWebConst.SUCCESS);
		ciResponse.setMessage("返回服务消费-国内旅游收入");
	}

	// 服务消费—在线视频收入
	public void searchCiOlVideoIncm(CiResponse ciResponse) {
		if(RDDWebConst.FAILURE.equals(ciResponse.getStatus())){
			logger.info("the search already failed!");
			return;
		}

		DBContextHolder.setDbType(DBContextHolder.PESEER_RPT);
		List<CiOlVideoIncm> list = ciOlVideoIncmMapper.selectAll();
		ciResponse.setVideoIncom(list);
		ciResponse.setStatus(RDDWebConst.SUCCESS);
		ciResponse.setMessage("返回服务消费—在线视频收入");
	}

	private static Map<String, String> map = new HashMap<>();

	static{
		map.put("10011", "ci_abv_lim_crs");
		map.put("10012", "ci_add_rmb_loan");
		map.put("10013", "ci_agricultural_output");
		map.put("10014", "ci_air_cleaner_sales");
		map.put("10015", "ci_automat_sal_inv");
		map.put("10016", "ci_bc_avg_amo");
		map.put("10017", "ci_bit_price");
		map.put("10018", "ci_cb_ec_sca");
		map.put("10019", "ci_cc_index");
		map.put("10020", "ci_cc_num");
		map.put("10021", "ci_ce_vec_sales");
		map.put("10022", "ci_chn_film_box");
		map.put("10023", "ci_chn_film_stat");
		map.put("10024", "ci_chn_netizen_net");
		map.put("10025", "ci_chs_area");
		map.put("10026", "ci_co_sc_propt");
		map.put("10027", "ci_core_pay_share");
		map.put("10028", "ci_courier_biz_srvy");
		map.put("10029", "ci_cptoc_sca");
		map.put("10030", "ci_cs_org_fund_sca");
		map.put("10031", "ci_dmp_deli");
		map.put("10032", "ci_dtour_income");
		map.put("10033", "ci_eight_rrr_value");
		map.put("10034", "ci_engel_coefficient");
		map.put("10035", "ci_ent_day_stat");
		map.put("10036", "ci_ent_mon_stat");
		map.put("10037", "ci_fre_vol_growth");
		map.put("10038", "ci_gdp");
		map.put("10039", "ci_gene_coefficient");
		map.put("10040", "ci_hot_app_use");
		map.put("10041", "ci_hs_consu_loan");
		map.put("10042", "ci_hunderd_career_sd");
		map.put("10043", "ci_ind_hous_loan");
		map.put("10044", "ci_jpn_retail");
		map.put("10045", "ci_lc_gdp");
		map.put("10046", "ci_lux_consu_sca");
		map.put("10047", "ci_manuf_pmi");
		map.put("10048", "ci_mb_shop_sca");
		map.put("10049", "ci_mia_data");
		map.put("10050", "ci_migrant_worker_inc");
		map.put("10051", "ci_migrant_worker_sca");
		map.put("10052", "ci_money_sup");
		map.put("10053", "ci_net_scy");
		map.put("10054", "ci_new_emp_population");
		map.put("10055", "ci_nhre_consmp_speed");
		map.put("10056", "ci_non_manuf_pmi");
		map.put("10057", "ci_ns_fund_sca");
		map.put("10058", "ci_ol_game_incm");
		map.put("10059", "ci_ol_shop_pr");
		map.put("10060", "ci_ol_video_incm");
		map.put("10061", "ci_olg_incom_comp");
		map.put("10062", "ci_olg_incom_sac");
		map.put("10063", "ci_olg_user_sca");
		map.put("10064", "ci_ops_stat_inst_research");
		map.put("10065", "ci_ops_stat_toe_ma");
		map.put("10066", "ci_or_sc_propt");
		map.put("10067", "ci_pay_m_comp");
		map.put("10068", "ci_pay_m_sca");
		map.put("10069", "ci_pay_rank");
		map.put("10070", "ci_phone_ol_shop");
		map.put("10071", "ci_plt_distribution");
		map.put("10072", "ci_plt_growth");
		map.put("10073", "ci_population_sca");
		map.put("10074", "ci_ptop_nl_sca");
		map.put("10075", "ci_rca_pay");
		map.put("10076", "ci_reg_unemp_rate");
		map.put("10077", "ci_rhfs_area");
		map.put("10078", "ci_rifa_amount");
		map.put("10079", "ci_sf_sca");
		map.put("10080", "ci_sht_rent_sca");
		map.put("10081", "ci_social_insurance");
		map.put("10082", "ci_sp_sales");
		map.put("10083", "ci_tifa_amount");
		map.put("10084", "ci_tot_social_log");
		map.put("10085", "ci_tp_fund_disc");
		map.put("10086", "ci_tslc_acct_gdp");
		map.put("10087", "ci_ur_pcce");
		map.put("10088", "ci_ur_resd_income");
		map.put("10089", "ci_urc_sca");
		map.put("10090", "ci_us_consu_growth");
		map.put("10091", "ci_us_cr");
		map.put("10092", "ci_us_net_retail");
		map.put("10093", "ci_us_new_employee");
		map.put("10094", "ci_us_np_retail");
		map.put("10095", "ci_us_psce");
		map.put("10096", "ci_used_car_sales");
		map.put("10097", "ci_world_os_share");
		map.put("10098", "ci_wt_cln_sales");
	}

	@SuppressWarnings("unchecked")
	public <T> void getDataList(String table, Class<T> TT, CiAssociatedResponse response) {

		List<T> list = new ArrayList<>();
		String sql = String.format("select * from %s ", table);
		ResultSet rs = null;
		try {
			rs = MysqlHelper.getInstance(RDDWebConst.PESEER_RPT_CHOICE).getResultSet(sql);
			while (rs.next()) {
				T objectItem = (T) BeanConverter.convert(rs, TT);

				if (objectItem != null) {
					if (list == null) {
						list = new ArrayList<>();
					}
					list.add(objectItem);
				}
			}

			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("get propertities success!");
			response.setObject((List<Object>) list);

		} catch (Exception ex) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("get data error!");
			logger.error("", ex);
		} finally {
			MysqlHelper.getInstance(RDDWebConst.PESEER_RPT_CHOICE).close(rs);
		}
	}

	public void getPropMap(String table, CiAssociatedResponse response) {
		String sql = String.format(
				"select  column_name, column_comment from information_schema.columns where table_schema ='peseer_rpt_choice' and table_name = '%s'",
				table);
		ResultSet rs = null;
		Map<String, String> map = new HashMap<>();
		try {
			rs = MysqlHelper.getInstance(RDDWebConst.PESEER_RPT_CHOICE).getResultSet(sql);
			while (rs.next()) {
				String colum = rs.getString("column_name");
				String comment = rs.getString("column_comment");
				colum = StringUtil.lineToHump(colum);
				map.put(colum, comment);
			}
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("get propertities success!");
			response.setPropertities(map);

		} catch (Exception ex) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("get propertities error!");
			logger.error("", ex);
		} finally {
			MysqlHelper.getInstance(RDDWebConst.PESEER_RPT_CHOICE).close(rs);
		}
	}

	public void getCiAssociatedInfo(String type, CiAssociatedResponse response) {
		String table = map.get(type);
		if(StringUtil.isNullOrEmpty(table)){
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Cannot find table!");
			return;
		}
		getPropMap(table, response);

		if (!RDDWebConst.FAILURE.equals(response.getStatus())) {
			switch (table) {
			case "ci_abv_lim_crs":
				getDataList(table, CiAbvLimCrs.class, response);
				break;
			case "ci_add_rmb_loan":
				getDataList(table, CiAddRmbLoan.class, response);
				break;
			case "ci_agricultural_output":
				getDataList(table, CiAgriculturalOutput.class, response);
				break;
			case "ci_air_cleaner_sales":
				getDataList(table, CiAirCleanerSales.class, response);
				break;
			case "ci_automat_sal_inv":
				getDataList(table, CiAutomatSalInv.class, response);
				break;
			case "ci_bc_avg_amo":
				getDataList(table, CiBcAvgAmo.class, response);
				break;
			case "ci_bit_price":
				getDataList(table, CiBitPrice.class, response);
				break;
			case "ci_cb_ec_sca":
				getDataList(table, CiCbEcSca.class, response);
				break;
			case "ci_cc_index":
				getDataList(table, CiCcIndex.class, response);
				break;
			case "ci_cc_num":
				getDataList(table, CiCcNum.class, response);
				break;
			case "ci_ce_vec_sales":
				getDataList(table, CiCeVecSales.class, response);
				break;
			case "ci_chn_film_box":
				getDataList(table, CiChnFilmBox.class, response);
				break;
			case "ci_chn_film_stat":
				getDataList(table, CiChnFilmStat.class, response);
				break;
			case "ci_chn_netizen_net":
				getDataList(table, CiChnNetizenNet.class, response);
				break;
			case "ci_chs_area":
				getDataList(table, CiChsArea.class, response);
				break;
			case "ci_co_sc_propt":
				getDataList(table, CiCoScPropt.class, response);
				break;
			case "ci_core_pay_share":
				getDataList(table, CiCorePayShare.class, response);
				break;
			case "ci_courier_biz_srvy":
				getDataList(table, CiCourierBizSrvy.class, response);
				break;
			case "ci_cptoc_sca":
				getDataList(table, CiCptocSca.class, response);
				break;
			case "ci_cs_org_fund_sca":
				getDataList(table, CiCsOrgFundSca.class, response);
				break;
			case "ci_dmp_deli":
				getDataList(table, CiDmpDeli.class, response);
				break;
			case "ci_dtour_income":
				getDataList(table, CiDtourIncome.class, response);
				break;
			case "ci_eight_rrr_value":
				getDataList(table, CiEightRRRValue.class, response);
				break;
			case "ci_engel_coefficient":
				getDataList(table, CiEngelCoefficient.class, response);
				break;
			case "ci_ent_day_stat":
				getDataList(table, CiEntDayStat.class, response);
				break;
			case "ci_ent_mon_stat":
				getDataList(table, CiEntMonStat.class, response);
				break;
			case "ci_fre_vol_growth":
				getDataList(table, CiFreVolGrowth.class, response);
				break;
			case "ci_gdp":
				getDataList(table, CiGdp.class, response);
				break;
			case "ci_gene_coefficient":
				getDataList(table, CiGeneCoefficient.class, response);
				break;
			case "ci_hot_app_use":
				getDataList(table, CiHotAppUse.class, response);
				break;
			case "ci_hs_consu_loan":
				getDataList(table, CiHsConsuLoan.class, response);
				break;
			case "ci_hunderd_career_sd":
				getDataList(table, CiHunderdCareerSd.class, response);
				break;
			case "ci_ind_hous_loan":
				getDataList(table, CiIndHoursLoan.class, response);
				break;
			case "ci_jpn_retail":
				getDataList(table, CiJpnRetail.class, response);
				break;
			case "ci_lc_gdp":
				getDataList(table, CiLcGdp.class, response);
				break;
			case "ci_lux_consu_sca":
				getDataList(table, CiLusConsuSca.class, response);
				break;
			case "ci_manuf_pmi":
				getDataList(table, CiManufPmi.class, response);
				break;
			case "ci_mb_shop_sca":
				getDataList(table, CiMbShopSca.class, response);
				break;
			case "ci_mia_data":
				getDataList(table, CiMiaData.class, response);
				break;
			case "ci_migrant_worker_inc":
				getDataList(table, CiMigrantWorkerInc.class, response);
				break;
			case "ci_migrant_worker_sca":
				getDataList(table, CiMigrantWorkerSca.class, response);
				break;
			case "ci_money_sup":
				getDataList(table, CiMoneySup.class, response);
				break;
			case "ci_net_scy":
				getDataList(table, CiNetScy.class, response);
				break;
			case "ci_new_emp_population":
				getDataList(table, CiNewEmpPopulation.class, response);
				break;
			case "ci_nhre_consmp_speed":
				getDataList(table, CiNhreConsmpSpeed.class, response);
				break;
			case "ci_non_manuf_pmi":
				getDataList(table, CiNonManufPmi.class, response);
				break;
			case "ci_ns_fund_sca":
				getDataList(table, CiNsFundSca.class, response);
				break;
			case "ci_ol_game_incm":
				getDataList(table, CiOlGameIncm.class, response);
				break;
			case "ci_ol_shop_pr":
				getDataList(table, CiOlShopPr.class, response);
				break;
			case "ci_ol_video_incm":
				getDataList(table, CiOlgVideoIncm.class, response);
				break;
			case "ci_olg_incom_comp":
				getDataList(table, CiOlgIncomComp.class, response);
				break;
			case "ci_olg_incom_sac":
				getDataList(table, CiOlgIncomSac.class, response);
				break;
			case "ci_olg_user_sca":
				getDataList(table, CiOlgUserSca.class, response);
				break;
			case "ci_ops_stat_inst_research":
				getDataList(table, CiOpsStatInstResearch.class, response);
				break;
			case "ci_ops_stat_toe_ma":
				getDataList(table, CiOpsStatToeMa.class, response);
				break;
			case "ci_or_sc_propt":
				getDataList(table, CiOrScPropt.class, response);
				break;
			case "ci_pay_m_comp":
				getDataList(table, CiPaymComp.class, response);
				break;
			case "ci_pay_m_sca":
				getDataList(table, CiPaymSca.class, response);
				break;
			case "ci_pay_rank":
				getDataList(table, CiPayRank.class, response);
				break;
			case "ci_phone_ol_shop":
				getDataList(table, CiPhoneOlShop.class, response);
				break;
			case "ci_plt_distribution":
				getDataList(table, CiPltDistribution.class, response);
				break;
			case "ci_plt_growth":
				getDataList(table, CiPltGrowth.class, response);
				break;
			case "ci_population_sca":
				getDataList(table, CiPopulationSca.class, response);
				break;
			case "ci_ptop_nl_sca":
				getDataList(table, CiPtopNlSca.class, response);
				break;
			case "ci_rca_pay":
				getDataList(table, CiRcaPay.class, response);
				break;
			case "ci_reg_unemp_rate":
				getDataList(table, CiRegUnempRate.class, response);
				break;
			case "ci_rhfs_area":
				getDataList(table, CiRhfsArea.class, response);
				break;
			case "ci_rifa_amount":
				getDataList(table, CiRifaAmount.class, response);
				break;
			case "ci_sf_sca":
				getDataList(table, CiSfSca.class, response);
				break;
			case "ci_sht_rent_sca":
				getDataList(table, CiShtRentSca.class, response);
				break;
			case "ci_social_insurance":
				getDataList(table, CiSocialInsurance.class, response);
				break;
			case "ci_sp_sales":
				getDataList(table, CiSpSales.class, response);
				break;
			case "ci_tifa_amount":
				getDataList(table, CiTifaAmout.class, response);
				break;
			case "ci_tot_social_log":
				getDataList(table, CiTotSocialLog.class, response);
				break;
			case "ci_tp_fund_disc":
				getDataList(table, CiTpFundDisc.class, response);
				break;
			case "ci_tslc_acct_gdp":
				getDataList(table, CiTslcAcctGdp.class, response);
				break;
			case "ci_ur_pcce":
				getDataList(table, CiUrPcce.class, response);
				break;
			case "ci_ur_resd_income":
				getDataList(table, CiUrResdIncome.class, response);
				break;
			case "ci_urc_sca":
				getDataList(table, CiUrcSca.class, response);
				break;
			case "ci_us_consu_growth":
				getDataList(table, CiUsConsuGrowth.class, response);
				break;
			case "ci_us_cr":
				getDataList(table, CiUsCr.class, response);
				break;
			case "ci_us_net_retail":
				getDataList(table, CiUsNetRetail.class, response);
				break;
			case "ci_us_new_employee":
				getDataList(table, CiUsNewEmployee.class, response);
				break;
			case "ci_us_np_retail":
				getDataList(table, CiUsNpRetail.class, response);
				break;
			case "ci_us_psce":
				getDataList(table, CiUsPsce.class, response);
				break;
			case "ci_used_car_sales":
				getDataList(table, CiUsedCarSales.class, response);
				break;
			case "ci_world_os_share":
				getDataList(table, CiWorldOsShare.class, response);
				break;
			case "ci_wt_cln_sales":
				getDataList(table, CiWtClnSales.class, response);
				break;
			default:
				response.setStatus(RDDWebConst.FAILURE);
				response.setMessage("Please provide a valid table!");
				break;
			}
		}
	}
}
