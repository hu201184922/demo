package com.huatai.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JyfxTargetExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JyfxTargetExample() {
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

        public Criteria andJtIdIsNull() {
            addCriterion("JT_ID is null");
            return (Criteria) this;
        }

        public Criteria andJtIdIsNotNull() {
            addCriterion("JT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andJtIdEqualTo(Long value) {
            addCriterion("JT_ID =", value, "jtId");
            return (Criteria) this;
        }

        public Criteria andJtIdNotEqualTo(Long value) {
            addCriterion("JT_ID <>", value, "jtId");
            return (Criteria) this;
        }

        public Criteria andJtIdGreaterThan(Long value) {
            addCriterion("JT_ID >", value, "jtId");
            return (Criteria) this;
        }

        public Criteria andJtIdGreaterThanOrEqualTo(Long value) {
            addCriterion("JT_ID >=", value, "jtId");
            return (Criteria) this;
        }

        public Criteria andJtIdLessThan(Long value) {
            addCriterion("JT_ID <", value, "jtId");
            return (Criteria) this;
        }

        public Criteria andJtIdLessThanOrEqualTo(Long value) {
            addCriterion("JT_ID <=", value, "jtId");
            return (Criteria) this;
        }

        public Criteria andJtIdIn(List<Long> values) {
            addCriterion("JT_ID in", values, "jtId");
            return (Criteria) this;
        }

        public Criteria andJtIdNotIn(List<Long> values) {
            addCriterion("JT_ID not in", values, "jtId");
            return (Criteria) this;
        }

        public Criteria andJtIdBetween(Long value1, Long value2) {
            addCriterion("JT_ID between", value1, value2, "jtId");
            return (Criteria) this;
        }

        public Criteria andJtIdNotBetween(Long value1, Long value2) {
            addCriterion("JT_ID not between", value1, value2, "jtId");
            return (Criteria) this;
        }

        public Criteria andTargetCodeIsNull() {
            addCriterion("TARGET_CODE is null");
            return (Criteria) this;
        }

        public Criteria andTargetCodeIsNotNull() {
            addCriterion("TARGET_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andTargetCodeEqualTo(String value) {
            addCriterion("TARGET_CODE =", value, "targetCode");
            return (Criteria) this;
        }

        public Criteria andTargetCodeNotEqualTo(String value) {
            addCriterion("TARGET_CODE <>", value, "targetCode");
            return (Criteria) this;
        }

        public Criteria andTargetCodeGreaterThan(String value) {
            addCriterion("TARGET_CODE >", value, "targetCode");
            return (Criteria) this;
        }

        public Criteria andTargetCodeGreaterThanOrEqualTo(String value) {
            addCriterion("TARGET_CODE >=", value, "targetCode");
            return (Criteria) this;
        }

        public Criteria andTargetCodeLessThan(String value) {
            addCriterion("TARGET_CODE <", value, "targetCode");
            return (Criteria) this;
        }

        public Criteria andTargetCodeLessThanOrEqualTo(String value) {
            addCriterion("TARGET_CODE <=", value, "targetCode");
            return (Criteria) this;
        }

        public Criteria andTargetCodeLike(String value) {
            addCriterion("TARGET_CODE like", value, "targetCode");
            return (Criteria) this;
        }

        public Criteria andTargetCodeNotLike(String value) {
            addCriterion("TARGET_CODE not like", value, "targetCode");
            return (Criteria) this;
        }

        public Criteria andTargetCodeIn(List<String> values) {
            addCriterion("TARGET_CODE in", values, "targetCode");
            return (Criteria) this;
        }

        public Criteria andTargetCodeNotIn(List<String> values) {
            addCriterion("TARGET_CODE not in", values, "targetCode");
            return (Criteria) this;
        }

        public Criteria andTargetCodeBetween(String value1, String value2) {
            addCriterion("TARGET_CODE between", value1, value2, "targetCode");
            return (Criteria) this;
        }

        public Criteria andTargetCodeNotBetween(String value1, String value2) {
            addCriterion("TARGET_CODE not between", value1, value2, "targetCode");
            return (Criteria) this;
        }

        public Criteria andAnTargetCodeIsNull() {
            addCriterion("AN_TARGET_CODE is null");
            return (Criteria) this;
        }

        public Criteria andAnTargetCodeIsNotNull() {
            addCriterion("AN_TARGET_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andAnTargetCodeEqualTo(String value) {
            addCriterion("AN_TARGET_CODE =", value, "anTargetCode");
            return (Criteria) this;
        }

        public Criteria andAnTargetCodeNotEqualTo(String value) {
            addCriterion("AN_TARGET_CODE <>", value, "anTargetCode");
            return (Criteria) this;
        }

        public Criteria andAnTargetCodeGreaterThan(String value) {
            addCriterion("AN_TARGET_CODE >", value, "anTargetCode");
            return (Criteria) this;
        }

        public Criteria andAnTargetCodeGreaterThanOrEqualTo(String value) {
            addCriterion("AN_TARGET_CODE >=", value, "anTargetCode");
            return (Criteria) this;
        }

        public Criteria andAnTargetCodeLessThan(String value) {
            addCriterion("AN_TARGET_CODE <", value, "anTargetCode");
            return (Criteria) this;
        }

        public Criteria andAnTargetCodeLessThanOrEqualTo(String value) {
            addCriterion("AN_TARGET_CODE <=", value, "anTargetCode");
            return (Criteria) this;
        }

        public Criteria andAnTargetCodeLike(String value) {
            addCriterion("AN_TARGET_CODE like", value, "anTargetCode");
            return (Criteria) this;
        }

        public Criteria andAnTargetCodeNotLike(String value) {
            addCriterion("AN_TARGET_CODE not like", value, "anTargetCode");
            return (Criteria) this;
        }

        public Criteria andAnTargetCodeIn(List<String> values) {
            addCriterion("AN_TARGET_CODE in", values, "anTargetCode");
            return (Criteria) this;
        }

        public Criteria andAnTargetCodeNotIn(List<String> values) {
            addCriterion("AN_TARGET_CODE not in", values, "anTargetCode");
            return (Criteria) this;
        }

        public Criteria andAnTargetCodeBetween(String value1, String value2) {
            addCriterion("AN_TARGET_CODE between", value1, value2, "anTargetCode");
            return (Criteria) this;
        }

        public Criteria andAnTargetCodeNotBetween(String value1, String value2) {
            addCriterion("AN_TARGET_CODE not between", value1, value2, "anTargetCode");
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