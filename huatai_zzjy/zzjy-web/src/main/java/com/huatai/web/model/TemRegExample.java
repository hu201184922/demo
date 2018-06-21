package com.huatai.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TemRegExample {
	 protected String orderByClause;

	    protected boolean distinct;

	    protected List<Criteria> oredCriteria;

	    public TemRegExample() {
	        oredCriteria = new ArrayList<Criteria>();
	    }

	    public void setOrderByClause(String orderByClause) {
	        this.orderByClause = orderByClause;
	    }

	    public String getOrderByClause() {
	        return orderByClause;
	    }

	    public void setDistinct(boolean distinct) {
	        this.distinct = distinct;
	    }

	    public boolean isDistinct() {
	        return distinct;
	    }

	    public List<Criteria> getOredCriteria() {
	        return oredCriteria;
	    }

	    public void or(Criteria criteria) {
	        oredCriteria.add(criteria);
	    }

	    public Criteria or() {
	        Criteria criteria = createCriteriaInternal();
	        oredCriteria.add(criteria);
	        return criteria;
	    }

	    public Criteria createCriteria() {
	        Criteria criteria = createCriteriaInternal();
	        if (oredCriteria.size() == 0) {
	            oredCriteria.add(criteria);
	        }
	        return criteria;
	    }

	    protected Criteria createCriteriaInternal() {
	        Criteria criteria = new Criteria();
	        return criteria;
	    }

	    public void clear() {
	        oredCriteria.clear();
	        orderByClause = null;
	        distinct = false;
	    }

	    protected abstract static class GeneratedCriteria {
	        protected List<Criterion> criteria;

	        protected GeneratedCriteria() {
	            super();
	            criteria = new ArrayList<Criterion>();
	        }

	        public boolean isValid() {
	            return criteria.size() > 0;
	        }

	        public List<Criterion> getAllCriteria() {
	            return criteria;
	        }

	        public List<Criterion> getCriteria() {
	            return criteria;
	        }

	        protected void addCriterion(String condition) {
	            if (condition == null) {
	                throw new RuntimeException("Value for condition cannot be null");
	            }
	            criteria.add(new Criterion(condition));
	        }

	        protected void addCriterion(String condition, Object value, String property) {
	            if (value == null) {
	                throw new RuntimeException("Value for " + property + " cannot be null");
	            }
	            criteria.add(new Criterion(condition, value));
	        }

	        protected void addCriterion(String condition, Object value1, Object value2, String property) {
	            if (value1 == null || value2 == null) {
	                throw new RuntimeException("Between values for " + property + " cannot be null");
	            }
	            criteria.add(new Criterion(condition, value1, value2));
	        }

	        public Criteria andRegIdIsNull() {
	            addCriterion("REG_ID is null");
	            return (Criteria) this;
	        }

	        public Criteria andRegIdIsNotNull() {
	            addCriterion("REG_ID is not null");
	            return (Criteria) this;
	        }

	        public Criteria andRegIdEqualTo(Integer value) {
	            addCriterion("REG_ID =", value, "regId");
	            return (Criteria) this;
	        }

	        public Criteria andRegIdNotEqualTo(Integer value) {
	            addCriterion("REG_ID <>", value, "regId");
	            return (Criteria) this;
	        }

	        public Criteria andRegIdGreaterThan(Integer value) {
	            addCriterion("REG_ID >", value, "regId");
	            return (Criteria) this;
	        }

	        public Criteria andRegIdGreaterThanOrEqualTo(Integer value) {
	            addCriterion("REG_ID >=", value, "regId");
	            return (Criteria) this;
	        }

	        public Criteria andRegIdLessThan(Integer value) {
	            addCriterion("REG_ID <", value, "regId");
	            return (Criteria) this;
	        }

	        public Criteria andRegIdLessThanOrEqualTo(Integer value) {
	            addCriterion("REG_ID <=", value, "regId");
	            return (Criteria) this;
	        }

	        public Criteria andRegIdIn(List<Integer> values) {
	            addCriterion("REG_ID in", values, "regId");
	            return (Criteria) this;
	        }

	        public Criteria andRegIdNotIn(List<Integer> values) {
	            addCriterion("REG_ID not in", values, "regId");
	            return (Criteria) this;
	        }

	        public Criteria andRegIdBetween(Integer value1, Integer value2) {
	            addCriterion("REG_ID between", value1, value2, "regId");
	            return (Criteria) this;
	        }

	        public Criteria andRegIdNotBetween(Integer value1, Integer value2) {
	            addCriterion("REG_ID not between", value1, value2, "regId");
	            return (Criteria) this;
	        }

	        public Criteria andTempIdIsNull() {
	            addCriterion("TEMP_ID is null");
	            return (Criteria) this;
	        }

	        public Criteria andTempIdIsNotNull() {
	            addCriterion("TEMP_ID is not null");
	            return (Criteria) this;
	        }

	        public Criteria andTempIdEqualTo(Integer value) {
	            addCriterion("TEMP_ID =", value, "tempId");
	            return (Criteria) this;
	        }

	        public Criteria andTempIdNotEqualTo(Integer value) {
	            addCriterion("TEMP_ID <>", value, "tempId");
	            return (Criteria) this;
	        }

	        public Criteria andTempIdGreaterThan(Integer value) {
	            addCriterion("TEMP_ID >", value, "tempId");
	            return (Criteria) this;
	        }

	        public Criteria andTempIdGreaterThanOrEqualTo(Integer value) {
	            addCriterion("TEMP_ID >=", value, "tempId");
	            return (Criteria) this;
	        }

	        public Criteria andTempIdLessThan(Integer value) {
	            addCriterion("TEMP_ID <", value, "tempId");
	            return (Criteria) this;
	        }

	        public Criteria andTempIdLessThanOrEqualTo(Integer value) {
	            addCriterion("TEMP_ID <=", value, "tempId");
	            return (Criteria) this;
	        }

	        public Criteria andTempIdIn(List<Integer> values) {
	            addCriterion("TEMP_ID in", values, "tempId");
	            return (Criteria) this;
	        }

	        public Criteria andTempIdNotIn(List<Integer> values) {
	            addCriterion("TEMP_ID not in", values, "tempId");
	            return (Criteria) this;
	        }

	        public Criteria andTempIdBetween(Integer value1, Integer value2) {
	            addCriterion("TEMP_ID between", value1, value2, "tempId");
	            return (Criteria) this;
	        }

	        public Criteria andTempIdNotBetween(Integer value1, Integer value2) {
	            addCriterion("TEMP_ID not between", value1, value2, "tempId");
	            return (Criteria) this;
	        }

	        public Criteria andRegCodeIsNull() {
	            addCriterion("REG_CODE is null");
	            return (Criteria) this;
	        }

	        public Criteria andRegCodeIsNotNull() {
	            addCriterion("REG_CODE is not null");
	            return (Criteria) this;
	        }

	        public Criteria andRegCodeEqualTo(String value) {
	            addCriterion("REG_CODE =", value, "regCode");
	            return (Criteria) this;
	        }

	        public Criteria andRegCodeNotEqualTo(String value) {
	            addCriterion("REG_CODE <>", value, "regCode");
	            return (Criteria) this;
	        }

	        public Criteria andRegCodeGreaterThan(String value) {
	            addCriterion("REG_CODE >", value, "regCode");
	            return (Criteria) this;
	        }

	        public Criteria andRegCodeGreaterThanOrEqualTo(String value) {
	            addCriterion("REG_CODE >=", value, "regCode");
	            return (Criteria) this;
	        }

	        public Criteria andRegCodeLessThan(String value) {
	            addCriterion("REG_CODE <", value, "regCode");
	            return (Criteria) this;
	        }

	        public Criteria andRegCodeLessThanOrEqualTo(String value) {
	            addCriterion("REG_CODE <=", value, "regCode");
	            return (Criteria) this;
	        }

	        public Criteria andRegCodeLike(String value) {
	            addCriterion("REG_CODE like", value, "regCode");
	            return (Criteria) this;
	        }

	        public Criteria andRegCodeNotLike(String value) {
	            addCriterion("REG_CODE not like", value, "regCode");
	            return (Criteria) this;
	        }

	        public Criteria andRegCodeIn(List<String> values) {
	            addCriterion("REG_CODE in", values, "regCode");
	            return (Criteria) this;
	        }

	        public Criteria andRegCodeNotIn(List<String> values) {
	            addCriterion("REG_CODE not in", values, "regCode");
	            return (Criteria) this;
	        }

	        public Criteria andRegCodeBetween(String value1, String value2) {
	            addCriterion("REG_CODE between", value1, value2, "regCode");
	            return (Criteria) this;
	        }

	        public Criteria andRegCodeNotBetween(String value1, String value2) {
	            addCriterion("REG_CODE not between", value1, value2, "regCode");
	            return (Criteria) this;
	        }

	        public Criteria andRegNameIsNull() {
	            addCriterion("REG_NAME is null");
	            return (Criteria) this;
	        }

	        public Criteria andRegNameIsNotNull() {
	            addCriterion("REG_NAME is not null");
	            return (Criteria) this;
	        }

	        public Criteria andRegNameEqualTo(String value) {
	            addCriterion("REG_NAME =", value, "regName");
	            return (Criteria) this;
	        }

	        public Criteria andRegNameNotEqualTo(String value) {
	            addCriterion("REG_NAME <>", value, "regName");
	            return (Criteria) this;
	        }

	        public Criteria andRegNameGreaterThan(String value) {
	            addCriterion("REG_NAME >", value, "regName");
	            return (Criteria) this;
	        }

	        public Criteria andRegNameGreaterThanOrEqualTo(String value) {
	            addCriterion("REG_NAME >=", value, "regName");
	            return (Criteria) this;
	        }

	        public Criteria andRegNameLessThan(String value) {
	            addCriterion("REG_NAME <", value, "regName");
	            return (Criteria) this;
	        }

	        public Criteria andRegNameLessThanOrEqualTo(String value) {
	            addCriterion("REG_NAME <=", value, "regName");
	            return (Criteria) this;
	        }

	        public Criteria andRegNameLike(String value) {
	            addCriterion("REG_NAME like", value, "regName");
	            return (Criteria) this;
	        }

	        public Criteria andRegNameNotLike(String value) {
	            addCriterion("REG_NAME not like", value, "regName");
	            return (Criteria) this;
	        }

	        public Criteria andRegNameIn(List<String> values) {
	            addCriterion("REG_NAME in", values, "regName");
	            return (Criteria) this;
	        }

	        public Criteria andRegNameNotIn(List<String> values) {
	            addCriterion("REG_NAME not in", values, "regName");
	            return (Criteria) this;
	        }

	        public Criteria andRegNameBetween(String value1, String value2) {
	            addCriterion("REG_NAME between", value1, value2, "regName");
	            return (Criteria) this;
	        }

	        public Criteria andRegNameNotBetween(String value1, String value2) {
	            addCriterion("REG_NAME not between", value1, value2, "regName");
	            return (Criteria) this;
	        }

	        public Criteria andRemarkIsNull() {
	            addCriterion("REMARK is null");
	            return (Criteria) this;
	        }

	        public Criteria andRemarkIsNotNull() {
	            addCriterion("REMARK is not null");
	            return (Criteria) this;
	        }

	        public Criteria andRemarkEqualTo(String value) {
	            addCriterion("REMARK =", value, "remark");
	            return (Criteria) this;
	        }

	        public Criteria andRemarkNotEqualTo(String value) {
	            addCriterion("REMARK <>", value, "remark");
	            return (Criteria) this;
	        }

	        public Criteria andRemarkGreaterThan(String value) {
	            addCriterion("REMARK >", value, "remark");
	            return (Criteria) this;
	        }

	        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
	            addCriterion("REMARK >=", value, "remark");
	            return (Criteria) this;
	        }

	        public Criteria andRemarkLessThan(String value) {
	            addCriterion("REMARK <", value, "remark");
	            return (Criteria) this;
	        }

	        public Criteria andRemarkLessThanOrEqualTo(String value) {
	            addCriterion("REMARK <=", value, "remark");
	            return (Criteria) this;
	        }

	        public Criteria andRemarkLike(String value) {
	            addCriterion("REMARK like", value, "remark");
	            return (Criteria) this;
	        }

	        public Criteria andRemarkNotLike(String value) {
	            addCriterion("REMARK not like", value, "remark");
	            return (Criteria) this;
	        }

	        public Criteria andRemarkIn(List<String> values) {
	            addCriterion("REMARK in", values, "remark");
	            return (Criteria) this;
	        }

	        public Criteria andRemarkNotIn(List<String> values) {
	            addCriterion("REMARK not in", values, "remark");
	            return (Criteria) this;
	        }

	        public Criteria andRemarkBetween(String value1, String value2) {
	            addCriterion("REMARK between", value1, value2, "remark");
	            return (Criteria) this;
	        }

	        public Criteria andRemarkNotBetween(String value1, String value2) {
	            addCriterion("REMARK not between", value1, value2, "remark");
	            return (Criteria) this;
	        }

	        public Criteria andOpTypeIsNull() {
	            addCriterion("OP_TYPE is null");
	            return (Criteria) this;
	        }

	        public Criteria andOpTypeIsNotNull() {
	            addCriterion("OP_TYPE is not null");
	            return (Criteria) this;
	        }

	        public Criteria andOpTypeEqualTo(String value) {
	            addCriterion("OP_TYPE =", value, "opType");
	            return (Criteria) this;
	        }

	        public Criteria andOpTypeNotEqualTo(String value) {
	            addCriterion("OP_TYPE <>", value, "opType");
	            return (Criteria) this;
	        }

	        public Criteria andOpTypeGreaterThan(String value) {
	            addCriterion("OP_TYPE >", value, "opType");
	            return (Criteria) this;
	        }

	        public Criteria andOpTypeGreaterThanOrEqualTo(String value) {
	            addCriterion("OP_TYPE >=", value, "opType");
	            return (Criteria) this;
	        }

	        public Criteria andOpTypeLessThan(String value) {
	            addCriterion("OP_TYPE <", value, "opType");
	            return (Criteria) this;
	        }

	        public Criteria andOpTypeLessThanOrEqualTo(String value) {
	            addCriterion("OP_TYPE <=", value, "opType");
	            return (Criteria) this;
	        }

	        public Criteria andOpTypeLike(String value) {
	            addCriterion("OP_TYPE like", value, "opType");
	            return (Criteria) this;
	        }

	        public Criteria andOpTypeNotLike(String value) {
	            addCriterion("OP_TYPE not like", value, "opType");
	            return (Criteria) this;
	        }

	        public Criteria andOpTypeIn(List<String> values) {
	            addCriterion("OP_TYPE in", values, "opType");
	            return (Criteria) this;
	        }

	        public Criteria andOpTypeNotIn(List<String> values) {
	            addCriterion("OP_TYPE not in", values, "opType");
	            return (Criteria) this;
	        }

	        public Criteria andOpTypeBetween(String value1, String value2) {
	            addCriterion("OP_TYPE between", value1, value2, "opType");
	            return (Criteria) this;
	        }

	        public Criteria andOpTypeNotBetween(String value1, String value2) {
	            addCriterion("OP_TYPE not between", value1, value2, "opType");
	            return (Criteria) this;
	        }

	        public Criteria andCreateTimeIsNull() {
	            addCriterion("CREATE_TIME is null");
	            return (Criteria) this;
	        }

	        public Criteria andCreateTimeIsNotNull() {
	            addCriterion("CREATE_TIME is not null");
	            return (Criteria) this;
	        }

	        public Criteria andCreateTimeEqualTo(Date value) {
	            addCriterion("CREATE_TIME =", value, "createTime");
	            return (Criteria) this;
	        }

	        public Criteria andCreateTimeNotEqualTo(Date value) {
	            addCriterion("CREATE_TIME <>", value, "createTime");
	            return (Criteria) this;
	        }

	        public Criteria andCreateTimeGreaterThan(Date value) {
	            addCriterion("CREATE_TIME >", value, "createTime");
	            return (Criteria) this;
	        }

	        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
	            addCriterion("CREATE_TIME >=", value, "createTime");
	            return (Criteria) this;
	        }

	        public Criteria andCreateTimeLessThan(Date value) {
	            addCriterion("CREATE_TIME <", value, "createTime");
	            return (Criteria) this;
	        }

	        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
	            addCriterion("CREATE_TIME <=", value, "createTime");
	            return (Criteria) this;
	        }

	        public Criteria andCreateTimeIn(List<Date> values) {
	            addCriterion("CREATE_TIME in", values, "createTime");
	            return (Criteria) this;
	        }

	        public Criteria andCreateTimeNotIn(List<Date> values) {
	            addCriterion("CREATE_TIME not in", values, "createTime");
	            return (Criteria) this;
	        }

	        public Criteria andCreateTimeBetween(Date value1, Date value2) {
	            addCriterion("CREATE_TIME between", value1, value2, "createTime");
	            return (Criteria) this;
	        }

	        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
	            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
	            return (Criteria) this;
	        }

	        public Criteria andModifyTimeIsNull() {
	            addCriterion("MODIFY_TIME is null");
	            return (Criteria) this;
	        }

	        public Criteria andModifyTimeIsNotNull() {
	            addCriterion("MODIFY_TIME is not null");
	            return (Criteria) this;
	        }

	        public Criteria andModifyTimeEqualTo(Date value) {
	            addCriterion("MODIFY_TIME =", value, "modifyTime");
	            return (Criteria) this;
	        }

	        public Criteria andModifyTimeNotEqualTo(Date value) {
	            addCriterion("MODIFY_TIME <>", value, "modifyTime");
	            return (Criteria) this;
	        }

	        public Criteria andModifyTimeGreaterThan(Date value) {
	            addCriterion("MODIFY_TIME >", value, "modifyTime");
	            return (Criteria) this;
	        }

	        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
	            addCriterion("MODIFY_TIME >=", value, "modifyTime");
	            return (Criteria) this;
	        }

	        public Criteria andModifyTimeLessThan(Date value) {
	            addCriterion("MODIFY_TIME <", value, "modifyTime");
	            return (Criteria) this;
	        }

	        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
	            addCriterion("MODIFY_TIME <=", value, "modifyTime");
	            return (Criteria) this;
	        }

	        public Criteria andModifyTimeIn(List<Date> values) {
	            addCriterion("MODIFY_TIME in", values, "modifyTime");
	            return (Criteria) this;
	        }

	        public Criteria andModifyTimeNotIn(List<Date> values) {
	            addCriterion("MODIFY_TIME not in", values, "modifyTime");
	            return (Criteria) this;
	        }

	        public Criteria andModifyTimeBetween(Date value1, Date value2) {
	            addCriterion("MODIFY_TIME between", value1, value2, "modifyTime");
	            return (Criteria) this;
	        }

	        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
	            addCriterion("MODIFY_TIME not between", value1, value2, "modifyTime");
	            return (Criteria) this;
	        }

	        public Criteria andCreatorIdIsNull() {
	            addCriterion("CREATOR_ID is null");
	            return (Criteria) this;
	        }

	        public Criteria andCreatorIdIsNotNull() {
	            addCriterion("CREATOR_ID is not null");
	            return (Criteria) this;
	        }

	        public Criteria andCreatorIdEqualTo(String value) {
	            addCriterion("CREATOR_ID =", value, "creatorId");
	            return (Criteria) this;
	        }

	        public Criteria andCreatorIdNotEqualTo(String value) {
	            addCriterion("CREATOR_ID <>", value, "creatorId");
	            return (Criteria) this;
	        }

	        public Criteria andCreatorIdGreaterThan(String value) {
	            addCriterion("CREATOR_ID >", value, "creatorId");
	            return (Criteria) this;
	        }

	        public Criteria andCreatorIdGreaterThanOrEqualTo(String value) {
	            addCriterion("CREATOR_ID >=", value, "creatorId");
	            return (Criteria) this;
	        }

	        public Criteria andCreatorIdLessThan(String value) {
	            addCriterion("CREATOR_ID <", value, "creatorId");
	            return (Criteria) this;
	        }

	        public Criteria andCreatorIdLessThanOrEqualTo(String value) {
	            addCriterion("CREATOR_ID <=", value, "creatorId");
	            return (Criteria) this;
	        }

	        public Criteria andCreatorIdLike(String value) {
	            addCriterion("CREATOR_ID like", value, "creatorId");
	            return (Criteria) this;
	        }

	        public Criteria andCreatorIdNotLike(String value) {
	            addCriterion("CREATOR_ID not like", value, "creatorId");
	            return (Criteria) this;
	        }

	        public Criteria andCreatorIdIn(List<String> values) {
	            addCriterion("CREATOR_ID in", values, "creatorId");
	            return (Criteria) this;
	        }

	        public Criteria andCreatorIdNotIn(List<String> values) {
	            addCriterion("CREATOR_ID not in", values, "creatorId");
	            return (Criteria) this;
	        }

	        public Criteria andCreatorIdBetween(String value1, String value2) {
	            addCriterion("CREATOR_ID between", value1, value2, "creatorId");
	            return (Criteria) this;
	        }

	        public Criteria andCreatorIdNotBetween(String value1, String value2) {
	            addCriterion("CREATOR_ID not between", value1, value2, "creatorId");
	            return (Criteria) this;
	        }

	        public Criteria andModifierIdIsNull() {
	            addCriterion("MODIFIER_ID is null");
	            return (Criteria) this;
	        }

	        public Criteria andModifierIdIsNotNull() {
	            addCriterion("MODIFIER_ID is not null");
	            return (Criteria) this;
	        }

	        public Criteria andModifierIdEqualTo(String value) {
	            addCriterion("MODIFIER_ID =", value, "modifierId");
	            return (Criteria) this;
	        }

	        public Criteria andModifierIdNotEqualTo(String value) {
	            addCriterion("MODIFIER_ID <>", value, "modifierId");
	            return (Criteria) this;
	        }

	        public Criteria andModifierIdGreaterThan(String value) {
	            addCriterion("MODIFIER_ID >", value, "modifierId");
	            return (Criteria) this;
	        }

	        public Criteria andModifierIdGreaterThanOrEqualTo(String value) {
	            addCriterion("MODIFIER_ID >=", value, "modifierId");
	            return (Criteria) this;
	        }

	        public Criteria andModifierIdLessThan(String value) {
	            addCriterion("MODIFIER_ID <", value, "modifierId");
	            return (Criteria) this;
	        }

	        public Criteria andModifierIdLessThanOrEqualTo(String value) {
	            addCriterion("MODIFIER_ID <=", value, "modifierId");
	            return (Criteria) this;
	        }

	        public Criteria andModifierIdLike(String value) {
	            addCriterion("MODIFIER_ID like", value, "modifierId");
	            return (Criteria) this;
	        }

	        public Criteria andModifierIdNotLike(String value) {
	            addCriterion("MODIFIER_ID not like", value, "modifierId");
	            return (Criteria) this;
	        }

	        public Criteria andModifierIdIn(List<String> values) {
	            addCriterion("MODIFIER_ID in", values, "modifierId");
	            return (Criteria) this;
	        }

	        public Criteria andModifierIdNotIn(List<String> values) {
	            addCriterion("MODIFIER_ID not in", values, "modifierId");
	            return (Criteria) this;
	        }

	        public Criteria andModifierIdBetween(String value1, String value2) {
	            addCriterion("MODIFIER_ID between", value1, value2, "modifierId");
	            return (Criteria) this;
	        }

	        public Criteria andModifierIdNotBetween(String value1, String value2) {
	            addCriterion("MODIFIER_ID not between", value1, value2, "modifierId");
	            return (Criteria) this;
	        }
	    }

	    public static class Criteria extends GeneratedCriteria {

	        protected Criteria() {
	            super();
	        }
	    }

	    public static class Criterion {
	        private String condition;

	        private Object value;

	        private Object secondValue;

	        private boolean noValue;

	        private boolean singleValue;

	        private boolean betweenValue;

	        private boolean listValue;

	        private String typeHandler;

	        public String getCondition() {
	            return condition;
	        }

	        public Object getValue() {
	            return value;
	        }

	        public Object getSecondValue() {
	            return secondValue;
	        }

	        public boolean isNoValue() {
	            return noValue;
	        }

	        public boolean isSingleValue() {
	            return singleValue;
	        }

	        public boolean isBetweenValue() {
	            return betweenValue;
	        }

	        public boolean isListValue() {
	            return listValue;
	        }

	        public String getTypeHandler() {
	            return typeHandler;
	        }

	        protected Criterion(String condition) {
	            super();
	            this.condition = condition;
	            this.typeHandler = null;
	            this.noValue = true;
	        }

	        protected Criterion(String condition, Object value, String typeHandler) {
	            super();
	            this.condition = condition;
	            this.value = value;
	            this.typeHandler = typeHandler;
	            if (value instanceof List<?>) {
	                this.listValue = true;
	            } else {
	                this.singleValue = true;
	            }
	        }

	        protected Criterion(String condition, Object value) {
	            this(condition, value, null);
	        }

	        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
	            super();
	            this.condition = condition;
	            this.value = value;
	            this.secondValue = secondValue;
	            this.typeHandler = typeHandler;
	            this.betweenValue = true;
	        }

	        protected Criterion(String condition, Object value, Object secondValue) {
	            this(condition, value, secondValue, null);
	        }
	    }
	}