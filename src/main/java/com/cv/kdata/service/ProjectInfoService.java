package com.cv.kdata.service;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cv.kdata.cache.LoginInfoCache;
import com.cv.kdata.cont.RDDWebConst;
import com.cv.kdata.dao.PMStartupInfoMapper;
import com.cv.kdata.dao.UserInfoMapper;
import com.cv.kdata.datasource.DBContextHolder;
import com.cv.kdata.model.PMStartupInfo;
import com.cv.kdata.model.UserInfoWithBLOBs;
import com.cv.kdata.response.ProjectResponse;
import com.cv.kdata.util.StringUtil;

@Service
public class ProjectInfoService {
	// 获取基本信息
	@Autowired
	PMStartupInfoMapper startupInfoMapper;
	@Autowired
	UserInfoMapper usrInfoMapper;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectInfoService.class);

	// 获取最新收录项目信息
	public void getProjectInfo(HttpServletRequest req, ProjectResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		List<String> domain = null;
		String token = (String) req.getSession().getAttribute(RDDWebConst.TOKEN);

		if (!StringUtil.isNullOrEmpty(token)) {
			DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
			UserInfoWithBLOBs userInfo = null;
			String uid = LoginInfoCache.getInstance().getUid(token);
			if (!StringUtil.isNullOrEmpty(uid)) {
				userInfo = usrInfoMapper.selectByPrimaryKey(uid);
			}

			if (null != userInfo) {
				String customInfo = userInfo.getDomainTips();
				if (!StringUtil.isNullOrEmpty(customInfo)) {

					String[] custom = customInfo.split(",");
					domain = Arrays.asList(custom);
				}
			}
		}

		DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
		List<PMStartupInfo> projectList = startupInfoMapper.getProjectInfo(domain);
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("Get project list success!");
		response.setProjectList(projectList);
	}

	public void search(HttpServletRequest req, ProjectResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}

		String key = req.getParameter("key");
		String domain = req.getParameter("domain");

		int from = StringUtil.parseInt(req.getParameter("from"), 0);
		int count = StringUtil.parseInt(req.getParameter("count"), 20);

		try {
			DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
			List<PMStartupInfo> projectList = startupInfoMapper.searchProjectInfo(domain, key, from, count);

			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get project list success!");
			response.setProjectList(projectList);

		} catch (Exception e) {
			LOGGER.error("error: " + e.getMessage());
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Get project list failed!");
		}
	}

}
