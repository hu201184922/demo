package com.huatai.web.sql;

/**
 * @作者 MaxBill
 * @时间 2017年8月21日 上午10:45:45
 */
public class SqlConstants {

	// GDZB:01, FXGL:02, BKFW:03

	// 达时报默认
	public static final String DSB_01 = "0001";

	// 达时报排序
	public static final String DSB_02 = "0002";

	// 达时报特殊
	public static final String DSB_03 = "0003";

	// (固定指标默认sql处理器：适用于当月指标统计区域、当月指标统计列表区域)
	public static final String GDZB_01 = "0101";

	// (固定指标核心sql处理器：适用于核心区域)
	public static final String GDZB_02 = "0102";

	// (固定指标排行sql处理器：适用于排行区域)
	public static final String GDZB_03 = "0103";

	/*------------------------------- 分析管理按用户机构处理 -------------------------------*/

	// 分析管理默认sql处理器：适用于常用指标区域、一级指标区域、二级指标区域、特殊指标区域,区间统计区域
	public static final String FXGL_01 = "0201";

	// 分析管理排名sql处理器：适用于排名区域
	public static final String FXGL_02 = "0202";

	// 分析管理列表sql处理器：适用于列表区域
	public static final String FXGL_03 = "0203";

	// 分析管理列表sql处理器：机构对比区域
	public static final String FXGL_04 = "0204";

	// (分析管理列表sql处理器：适用于列表区域特殊情况（登录人机构是中支机构）)
	public static final String FXGL_05 = "0205";

	// (分析管理列表sql处理器：适用于列表区域特殊情况（登录人机构是四级机构）)
	public static final String FXGL_06 = "0206";

	/*------------------------------- 分析管理按搜索机构处理 -------------------------------*/

	// 分析管理默认sql处理器：适用于常用指标区域、一级指标区域、二级指标区域、特殊指标区域,区间统计区域
	public static final String FXGL_ORG0101 = "020101";

	// 分析管理排名sql处理器：适用于排名区域
	public static final String FXGL_ORG0201 = "020201";

	public static final String FXGL_ORG0202 = "020202";

	public static final String FXGL_ORG0203 = "020203";

	// 分析管理列表sql处理器：适用于列表区域
	public static final String FXGL_ORG0301 = "020301";

	// 分析管理列表sql处理器：机构对比区域
	public static final String FXGL_ORG0302 = "020302";

	// 分析管理列表sql处理器：适用于列表区域特殊情况（登录人是中支机构）
	public static final String FXGL_ORG0303 = "020303";

	// 分析管理列表sql处理器：适用于保费部
	public static final String FXGL_ORG0401 = "020401";

	public static final String FXGL_ORG0402 = "020402";

	public static final String FXGL_ORG0403 = "020403";

	// 板块总区域
	public static final String BKFW_01 = "0301";

	public static final String BKFW_02 = "0302";

	public static final String BKFW_03 = "0303";

	public static final String BKFW_04 = "0304";

	// 板块下钻区域
	public static final String BKFW_DOWN01 = "0305";

	public static final String BKFW_DOWN02 = "0306";

	public static final String BKFW_DOWN03 = "0307";

	public static final String BKFW_DOWN04 = "0308";

	// (板块分析管理默认sql处理器：适用于常用指标区域、一级指标区域、二级指标区域、特殊指标区域)
	public static final String BKFX_00101 = "0300101";

	// 按部门显示
	public static final String BKFX_00102 = "0300102";

	// (板块分析管理排名sql处理器：适用于排名区域)
	public static final String BKFX_00201 = "0300201";

	// 按部门显示
	public static final String BKFX_00202 = "0300202";

	// (板块分析管理列表sql处理器：适用于列表区域)
	public static final String BKFX_00301 = "0300301";

	// (板块分析管理列表sql处理器：适用于列表区域特殊情况(中支))
	public static final String BKFX_00401 = "0300401";

	public static final String BKFX_00501 = "0300501";

	// 使用于板块分析机构对比
	public static final String BKFX_00601 = "0300601";

	public static final String BKFX_00701 = "0300701";

	public static final String BKFX_00801 = "0300801";

	/*------------------------------- 分析管理按搜索机构处理 -------------------------------*/

	// 分析管理默认sql处理器：适用于常用指标区域、一级指标区域、二级指标区域、特殊指标区域,区间统计区域
	public static final String BKFX_ORG0101 = "030101";

	// 分析管理排名sql处理器：适用于排名区域
	public static final String BKFX_ORG0201 = "030201";

	public static final String BKFX_ORG0202 = "030202";

	public static final String BKFX_ORG0203 = "020203";

	// 分析管理列表sql处理器：适用于列表区域
	public static final String BKFX_ORG0301 = "030301";

	// 分析管理列表sql处理器：机构对比区域
	public static final String BKFX_ORG0302 = "030302";

	// 分析管理列表sql处理器：适用于列表区域特殊情况（登录人是中支机构）
	public static final String BKFX_ORG0303 = "030303";

	public static final String BKFX_ORG0401 = "030401";

	public static final String BKFX_ORG0402 = "030402";

	public static final String BKFX_ORG0403 = "030403";

}
