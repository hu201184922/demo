package com.huatai.web.sql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fairyland.jdp.framework.util.SpringUtil;
import com.huatai.web.mapper.TarGroDimMapper;
import com.huatai.web.model.TarGroDim;
import com.huatai.web.model.TarReg;

/**
 * @功能 {SQL拼装及数据过滤通用工具类}
 * @作者 MaxBill
 * @时间 2017年8月21日 上午10:45:37
 */
@Component
public class CommonUtil {

	public String getOrgType(String type) {
		String orgType = "";
		switch (type) {
		case "8":
			orgType = "1";
			break;
		case "7":
			orgType = "2";
			break;
		case "6":
			orgType = "3";
			break;
		case "5":
			orgType = "4";
			break;
		case "4":
			orgType = "5";
			break;
		case "3":
			orgType = "6";
			break;
		case "2":
			orgType = "7";
			break;
		case "1":
			orgType = "8";
			break;
		}
		return orgType;
	}

	/**
	 * @功能 {获取用户机构代码的对应系统代码}
	 * @作者 MaxBill
	 * @时间 2017年8月18日 下午4:49:16
	 */
	public String getRoleOrgCode(String roleOrg) {
		String userRoleOrgCode = "";
		switch (roleOrg.length()) {
		case 1:
			userRoleOrgCode = "8";
			break;
		case 3:
			userRoleOrgCode = "7";
			break;
		case 5:
			userRoleOrgCode = "6";
			break;
		case 10:
			userRoleOrgCode = "5";
			break;
		default:
			userRoleOrgCode = "5";
			break;
		}
		return userRoleOrgCode;
	}

	/**
	 * @功能 {是否是保费部和营销服务部}
	 * @作者 MaxBill
	 * @时间 2017年9月6日 下午5:45:10
	 */
	public boolean isGeYeAndPeiXunDept(String deptCode) {
		boolean deptFlag = false;
		switch (deptCode) {
		case "130107":
			deptFlag = true;
			break;
		case "101402":
			deptFlag = true;
			break;
		}
		return deptFlag;
	}

	/**
	 * @功能 {判断该指标是否在最低权限机构中}
	 * @作者 MaxBill
	 * @时间 2017年8月18日 下午4:49:16
	 */
	public boolean hasRoleOrg(int minRoleOrg, int userRoleOrg) {
		int userRoleOrgLevel = 0;
		switch (userRoleOrg) {
		case 1:
			userRoleOrgLevel = 8;
			break;
		case 3:
			userRoleOrgLevel = 7;
			break;
		case 5:
			userRoleOrgLevel = 6;
			break;
		case 10:
			userRoleOrgLevel = 5;
			break;
		default:
			userRoleOrgLevel = 5;
			break;
		}
		if (userRoleOrgLevel >= minRoleOrg) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @功能 {判断该指标是否在最低权限机构中}(机构对比)
	 * @作者 MaxBill
	 * @时间 2017年8月18日 下午4:49:16
	 */
	public boolean hasRoleOrgCompare(int minRoleOrg, int userRoleOrg) {
		int userRoleOrgLevel = 0;
		switch (userRoleOrg) {
		case 1:
			userRoleOrgLevel = 8;
			break;
		case 3:
			userRoleOrgLevel = 7;
			break;
		case 5:
			userRoleOrgLevel = 6;
			break;
		case 10:
			userRoleOrgLevel = 5;
			break;
		default:
			userRoleOrgLevel = 5;
			break;
		}
		if (userRoleOrgLevel > minRoleOrg) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @功能 {判断该指标是否可以下转}
	 * @作者 MaxBill
	 * @时间 2017年8月28日 下午2:03:16
	 */
	public boolean isDrilldown(int minRoleOrg, int userRoleOrg) {
		int userRoleOrgLevel = 0;
		switch (userRoleOrg) {
		case 1:
			userRoleOrgLevel = 8;
			break;
		case 3:
			userRoleOrgLevel = 7;
			break;
		case 5:
			userRoleOrgLevel = 6;
			break;
		case 10:
			userRoleOrgLevel = 5;
			break;
		default:
			userRoleOrgLevel = 5;
			break;
		}
		if (userRoleOrgLevel > minRoleOrg) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @功能 {判断该指标是否可以下转}
	 * @作者 MaxBill
	 * @时间 2017年8月28日 下午2:03:16
	 */
	public boolean isGdzbDrilldown(int minRoleOrg, int userRoleOrg) {
		Boolean isGdzbDrilldown;
		int userRoleOrgLevel = 0;
		switch (userRoleOrg) {
		case 1:
			userRoleOrgLevel = 8;
			break;
		case 3:
			userRoleOrgLevel = 7;
			break;
		case 5:
			userRoleOrgLevel = 6;
			break;
		case 10:
			userRoleOrgLevel = 5;
			break;
		default:
			userRoleOrgLevel = 5;
			break;
		}
		if (userRoleOrgLevel > minRoleOrg) {
			isGdzbDrilldown = true;
		} else {
			isGdzbDrilldown = false;
		}
		if (userRoleOrg == 11) {
			isGdzbDrilldown = false;
		}
		return isGdzbDrilldown;
	}

	/**
	 * @功能 {获取指标单位}
	 * @作者 MaxBill
	 * @时间 2017年8月23日 下午2:43:13
	 */
	public String getTargetUnit(String type) {
		String unit = "";
		if (null == type || type.equals("") || type.equals(" ") || type.equals("无")) {
			unit = "";
		} else {
			switch (type) {
			case "01":
				unit = "万元";
				break;
			case "02":
				unit = "%";
				break;
			case "03":
				unit = "千人";
				break;
			case "04":
				unit = "元";
				break;
			case "05":
				unit = "个";
				break;
			case "06":
				unit = "人";
				break;
			case "07":
				unit = "百人";
				break;
			case "08":
				unit = "件";
				break;
			case "09":
				unit = "百件";
				break;
			}
		}
		return unit;
	}

	/**
	 * @功能 {按最低机构过滤处理指标列表}
	 * @作者 MaxBill
	 * @时间 2017年8月24日 上午9:32:46
	 */
	public List<TarReg> dealTarRegList(List<TarReg> tarRegList, String roleOrg) {
		List<TarReg> resultList = new ArrayList<TarReg>();
		int userOrgRole;
		if (null == roleOrg || roleOrg.equals("")) {
			userOrgRole = 0;
		} else {
			userOrgRole = roleOrg.length();
		}
		if (null != tarRegList && !tarRegList.isEmpty()) {
			tarRegList.stream().forEach(tarReg -> {
				String roleOrgType = tarReg.getRoleOrgType();
				if (hasRoleOrg(Integer.parseInt(roleOrgType), userOrgRole)) {
					resultList.add(tarReg);
				}
			});
			return resultList;
		} else {
			return null;
		}
	}

	public boolean isHasGroByTarAndGro(String targetCode, String groupbyDate) {
		TarGroDimMapper tarGroDimMapper = SpringUtil.getBean(TarGroDimMapper.class);
		List<TarGroDim> tgds = tarGroDimMapper.isHasGroByTarAndGro(targetCode, groupbyDate);
		return tgds.size() > 0 ? true : false;
	}

}
