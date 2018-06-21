package com.huatai.web.thrift.service;

import java.util.List;

import com.huatai.web.bean.FieldInterBean;
import com.huatai.web.model.BillFiltDim;
import com.huatai.web.model.BillTarget;
import com.huatai.web.model.GroDimDetail;
import com.huatai.web.model.Inter;
import com.huatai.web.model.InterField;
import com.huatai.web.model.QueryDim;
import com.huatai.web.model.SqlAlias;
import com.huatai.web.model.TarRegSql;

public interface CommonService {

	List<SqlAlias> findSqlAliasByTrsId(Integer trsId);

	InterField findInterFieldByInterAndDim(Integer interId, String dimCode);

	InterField findInterFieldByTabAndTar(String target, String dimCode);

	Inter findInterByTableName(String tableNmae, String dimCode);

	BillFiltDim findBillFiltByBillAndTarAndDim(Integer billId, String target, String dimCode);

	QueryDim findQueryDimByCode(String code);

	List<BillTarget> findBillTargetByBill(Integer billId);

	TarRegSql findTarRegSql(String target, Integer regId, String groupType, String groupDetail, String dateType);

	Object getTarInitSql(String fun, String targetCode, String groupType, String groupDetail, String dateType);

	Boolean findTarGroDimByGroAndTar(String dimCode, String target);

	List<GroDimDetail> findGroDetailListBySubWithDate(String subCode);

	List<GroDimDetail> findGroDetailListBySubWithDateAndPlate(Integer plateId);

	boolean findTQDByChannelCode(String code);

	List<FieldInterBean> findTIByTarChanCode(String targetCode, String ChannelCode, String interName);

}
