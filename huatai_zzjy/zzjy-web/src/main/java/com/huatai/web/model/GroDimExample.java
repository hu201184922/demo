package com.huatai.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GroDimExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GroDimExample() {
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

        public Criteria andGdIdIsNull() {
            addCriterion("GD_ID is null");
            return (Criteria) this;
        }

        public Criteria andGdIdIsNotNull() {
            addCriterion("GD_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGdIdEqualTo(Integer value) {
            addCriterion("GD_ID =", value, "gdId");
            return (Criteria) this;
        }

        public Criteria andGdIdNotEqualTo(Integer value) {
            addCriterion("GD_ID <>", value, "gdId");
            return (Criteria) this;
        }

        public Criteria andGdIdGreaterThan(Integer value) {
            addCriterion("GD_ID >", value, "gdId");
            return (Criteria) this;
        }

        public Criteria andGdIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("GD_ID >=", value, "gdId");
            return (Criteria) this;
        }

        public Criteria andGdIdLessThan(Integer value) {
            addCriterion("GD_ID <", value, "gdId");
            return (Criteria) this;
        }

        public Criteria andGdIdLessThanOrEqualTo(Integer value) {
            addCriterion("GD_ID <=", value, "gdId");
            return (Criteria) this;
        }

        public Criteria andGdIdIn(List<Integer> values) {
            addCriterion("GD_ID in", values, "gdId");
            return (Criteria) this;
        }

        public Criteria andGdIdNotIn(List<Integer> values) {
            addCriterion("GD_ID not in", values, "gdId");
            return (Criteria) this;
        }

        public Criteria andGdIdBetween(Integer value1, Integer value2) {
            addCriterion("GD_ID between", value1, value2, "gdId");
            return (Criteria) this;
        }

        public Criteria andGdIdNotBetween(Integer value1, Integer value2) {
            addCriterion("GD_ID not between", value1, value2, "gdId");
            return (Criteria) this;
        }

        public Criteria andGroDimTypeCodeIsNull() {
            addCriterion("GRO_DIM_TYPE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andGroDimTypeCodeIsNotNull() {
            addCriterion("GRO_DIM_TYPE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andGroDimTypeCodeEqualTo(String value) {
            addCriterion("GRO_DIM_TYPE_CODE =", value, "groDimTypeCode");
            return (Criteria) this;
        }

        public Criteria andGroDimTypeCodeNotEqualTo(String value) {
            addCriterion("GRO_DIM_TYPE_CODE <>", value, "groDimTypeCode");
            return (Criteria) this;
        }

        public Criteria andGroDimTypeCodeGreaterThan(String value) {
            addCriterion("GRO_DIM_TYPE_CODE >", value, "groDimTypeCode");
            return (Criteria) this;
        }

        public Criteria andGroDimTypeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("GRO_DIM_TYPE_CODE >=", value, "groDimTypeCode");
            return (Criteria) this;
        }

        public Criteria andGroDimTypeCodeLessThan(String value) {
            addCriterion("GRO_DIM_TYPE_CODE <", value, "groDimTypeCode");
            return (Criteria) this;
        }

        public Criteria andGroDimTypeCodeLessThanOrEqualTo(String value) {
            addCriterion("GRO_DIM_TYPE_CODE <=", value, "groDimTypeCode");
            return (Criteria) this;
        }

        public Criteria andGroDimTypeCodeLike(String value) {
            addCriterion("GRO_DIM_TYPE_CODE like", value, "groDimTypeCode");
            return (Criteria) this;
        }

        public Criteria andGroDimTypeCodeNotLike(String value) {
            addCriterion("GRO_DIM_TYPE_CODE not like", value, "groDimTypeCode");
            return (Criteria) this;
        }

        public Criteria andGroDimTypeCodeIn(List<String> values) {
            addCriterion("GRO_DIM_TYPE_CODE in", values, "groDimTypeCode");
            return (Criteria) this;
        }

        public Criteria andGroDimTypeCodeNotIn(List<String> values) {
            addCriterion("GRO_DIM_TYPE_CODE not in", values, "groDimTypeCode");
            return (Criteria) this;
        }

        public Criteria andGroDimTypeCodeBetween(String value1, String value2) {
            addCriterion("GRO_DIM_TYPE_CODE between", value1, value2, "groDimTypeCode");
            return (Criteria) this;
        }

        public Criteria andGroDimTypeCodeNotBetween(String value1, String value2) {
            addCriterion("GRO_DIM_TYPE_CODE not between", value1, value2, "groDimTypeCode");
            return (Criteria) this;
        }

        public Criteria andGroDimTypeNameIsNull() {
            addCriterion("\"GRO_DIM_TYPE_NAME\" is null");
            return (Criteria) this;
        }

        public Criteria andGroDimTypeNameIsNotNull() {
            addCriterion("\"GRO_DIM_TYPE_NAME\" is not null");
            return (Criteria) this;
        }

        public Criteria andGroDimTypeNameEqualTo(String value) {
            addCriterion("\"GRO_DIM_TYPE_NAME\" =", value, "groDimTypeName");
            return (Criteria) this;
        }

        public Criteria andGroDimTypeNameNotEqualTo(String value) {
            addCriterion("\"GRO_DIM_TYPE_NAME\" <>", value, "groDimTypeName");
            return (Criteria) this;
        }

        public Criteria andGroDimTypeNameGreaterThan(String value) {
            addCriterion("\"GRO_DIM_TYPE_NAME\" >", value, "groDimTypeName");
            return (Criteria) this;
        }

        public Criteria andGroDimTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("\"GRO_DIM_TYPE_NAME\" >=", value, "groDimTypeName");
            return (Criteria) this;
        }

        public Criteria andGroDimTypeNameLessThan(String value) {
            addCriterion("\"GRO_DIM_TYPE_NAME\" <", value, "groDimTypeName");
            return (Criteria) this;
        }

        public Criteria andGroDimTypeNameLessThanOrEqualTo(String value) {
            addCriterion("\"GRO_DIM_TYPE_NAME\" <=", value, "groDimTypeName");
            return (Criteria) this;
        }

        public Criteria andGroDimTypeNameLike(String value) {
            addCriterion("\"GRO_DIM_TYPE_NAME\" like", value, "groDimTypeName");
            return (Criteria) this;
        }

        public Criteria andGroDimTypeNameNotLike(String value) {
            addCriterion("\"GRO_DIM_TYPE_NAME\" not like", value, "groDimTypeName");
            return (Criteria) this;
        }

        public Criteria andGroDimTypeNameIn(List<String> values) {
            addCriterion("\"GRO_DIM_TYPE_NAME\" in", values, "groDimTypeName");
            return (Criteria) this;
        }

        public Criteria andGroDimTypeNameNotIn(List<String> values) {
            addCriterion("\"GRO_DIM_TYPE_NAME\" not in", values, "groDimTypeName");
            return (Criteria) this;
        }

        public Criteria andGroDimTypeNameBetween(String value1, String value2) {
            addCriterion("\"GRO_DIM_TYPE_NAME\" between", value1, value2, "groDimTypeName");
            return (Criteria) this;
        }

        public Criteria andGroDimTypeNameNotBetween(String value1, String value2) {
            addCriterion("\"GRO_DIM_TYPE_NAME\" not between", value1, value2, "groDimTypeName");
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