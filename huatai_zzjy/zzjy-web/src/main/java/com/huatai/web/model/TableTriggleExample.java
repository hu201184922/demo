package com.huatai.web.model;

import java.util.ArrayList;
import java.util.List;

public class TableTriggleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected java.util.List<Criteria> oredCriteria;

    public TableTriggleExample() {
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

    public java.util.List<Criteria> getOredCriteria() {
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
        protected java.util.List<Criterion> criteria;

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

        public java.util.List<Criterion> getCriteria() {
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

        public Criteria andQrtzGroupIdIsNull() {
            addCriterion("QRTZ_GROUP_ID is null");
            return (Criteria) this;
        }

        public Criteria andQrtzGroupIdIsNotNull() {
            addCriterion("QRTZ_GROUP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andQrtzGroupIdEqualTo(Long value) {
            addCriterion("QRTZ_GROUP_ID =", value, "qrtzGroupId");
            return (Criteria) this;
        }

        public Criteria andQrtzGroupIdNotEqualTo(Long value) {
            addCriterion("QRTZ_GROUP_ID <>", value, "qrtzGroupId");
            return (Criteria) this;
        }

        public Criteria andQrtzGroupIdGreaterThan(Long value) {
            addCriterion("QRTZ_GROUP_ID >", value, "qrtzGroupId");
            return (Criteria) this;
        }

        public Criteria andQrtzGroupIdGreaterThanOrEqualTo(Long value) {
            addCriterion("QRTZ_GROUP_ID >=", value, "qrtzGroupId");
            return (Criteria) this;
        }

        public Criteria andQrtzGroupIdLessThan(Long value) {
            addCriterion("QRTZ_GROUP_ID <", value, "qrtzGroupId");
            return (Criteria) this;
        }

        public Criteria andQrtzGroupIdLessThanOrEqualTo(Long value) {
            addCriterion("QRTZ_GROUP_ID <=", value, "qrtzGroupId");
            return (Criteria) this;
        }

        public Criteria andQrtzGroupIdIn(java.util.List<java.lang.Long> values) {
            addCriterion("QRTZ_GROUP_ID in", values, "qrtzGroupId");
            return (Criteria) this;
        }

        public Criteria andQrtzGroupIdNotIn(java.util.List<java.lang.Long> values) {
            addCriterion("QRTZ_GROUP_ID not in", values, "qrtzGroupId");
            return (Criteria) this;
        }

        public Criteria andQrtzGroupIdBetween(Long value1, Long value2) {
            addCriterion("QRTZ_GROUP_ID between", value1, value2, "qrtzGroupId");
            return (Criteria) this;
        }

        public Criteria andQrtzGroupIdNotBetween(Long value1, Long value2) {
            addCriterion("QRTZ_GROUP_ID not between", value1, value2, "qrtzGroupId");
            return (Criteria) this;
        }

        public Criteria andQrtzCodeIsNull() {
            addCriterion("QRTZ_CODE is null");
            return (Criteria) this;
        }

        public Criteria andQrtzCodeIsNotNull() {
            addCriterion("QRTZ_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andQrtzCodeEqualTo(String value) {
            addCriterion("QRTZ_CODE =", value, "qrtzCode");
            return (Criteria) this;
        }

        public Criteria andQrtzCodeNotEqualTo(String value) {
            addCriterion("QRTZ_CODE <>", value, "qrtzCode");
            return (Criteria) this;
        }

        public Criteria andQrtzCodeGreaterThan(String value) {
            addCriterion("QRTZ_CODE >", value, "qrtzCode");
            return (Criteria) this;
        }

        public Criteria andQrtzCodeGreaterThanOrEqualTo(String value) {
            addCriterion("QRTZ_CODE >=", value, "qrtzCode");
            return (Criteria) this;
        }

        public Criteria andQrtzCodeLessThan(String value) {
            addCriterion("QRTZ_CODE <", value, "qrtzCode");
            return (Criteria) this;
        }

        public Criteria andQrtzCodeLessThanOrEqualTo(String value) {
            addCriterion("QRTZ_CODE <=", value, "qrtzCode");
            return (Criteria) this;
        }

        public Criteria andQrtzCodeLike(String value) {
            addCriterion("QRTZ_CODE like", value, "qrtzCode");
            return (Criteria) this;
        }

        public Criteria andQrtzCodeNotLike(String value) {
            addCriterion("QRTZ_CODE not like", value, "qrtzCode");
            return (Criteria) this;
        }

        public Criteria andQrtzCodeIn(java.util.List<java.lang.String> values) {
            addCriterion("QRTZ_CODE in", values, "qrtzCode");
            return (Criteria) this;
        }

        public Criteria andQrtzCodeNotIn(java.util.List<java.lang.String> values) {
            addCriterion("QRTZ_CODE not in", values, "qrtzCode");
            return (Criteria) this;
        }

        public Criteria andQrtzCodeBetween(String value1, String value2) {
            addCriterion("QRTZ_CODE between", value1, value2, "qrtzCode");
            return (Criteria) this;
        }

        public Criteria andQrtzCodeNotBetween(String value1, String value2) {
            addCriterion("QRTZ_CODE not between", value1, value2, "qrtzCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeIsNull() {
            addCriterion("TABLE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andTableCodeIsNotNull() {
            addCriterion("TABLE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andTableCodeEqualTo(String value) {
            addCriterion("TABLE_CODE =", value, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeNotEqualTo(String value) {
            addCriterion("TABLE_CODE <>", value, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeGreaterThan(String value) {
            addCriterion("TABLE_CODE >", value, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeGreaterThanOrEqualTo(String value) {
            addCriterion("TABLE_CODE >=", value, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeLessThan(String value) {
            addCriterion("TABLE_CODE <", value, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeLessThanOrEqualTo(String value) {
            addCriterion("TABLE_CODE <=", value, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeLike(String value) {
            addCriterion("TABLE_CODE like", value, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeNotLike(String value) {
            addCriterion("TABLE_CODE not like", value, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeIn(java.util.List<java.lang.String> values) {
            addCriterion("TABLE_CODE in", values, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeNotIn(java.util.List<java.lang.String> values) {
            addCriterion("TABLE_CODE not in", values, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeBetween(String value1, String value2) {
            addCriterion("TABLE_CODE between", value1, value2, "tableCode");
            return (Criteria) this;
        }

        public Criteria andTableCodeNotBetween(String value1, String value2) {
            addCriterion("TABLE_CODE not between", value1, value2, "tableCode");
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