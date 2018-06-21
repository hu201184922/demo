package com.huatai.web.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PremTargetExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PremTargetExample() {
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

        public Criteria andOrgCodeIsNull() {
            addCriterion("ORG_CODE is null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIsNotNull() {
            addCriterion("ORG_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeEqualTo(String value) {
            addCriterion("ORG_CODE =", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotEqualTo(String value) {
            addCriterion("ORG_CODE <>", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThan(String value) {
            addCriterion("ORG_CODE >", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_CODE >=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThan(String value) {
            addCriterion("ORG_CODE <", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThanOrEqualTo(String value) {
            addCriterion("ORG_CODE <=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLike(String value) {
            addCriterion("ORG_CODE like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotLike(String value) {
            addCriterion("ORG_CODE not like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIn(List<String> values) {
            addCriterion("ORG_CODE in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotIn(List<String> values) {
            addCriterion("ORG_CODE not in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeBetween(String value1, String value2) {
            addCriterion("ORG_CODE between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotBetween(String value1, String value2) {
            addCriterion("ORG_CODE not between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNull() {
            addCriterion("ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNotNull() {
            addCriterion("ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgNameEqualTo(String value) {
            addCriterion("ORG_NAME =", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotEqualTo(String value) {
            addCriterion("ORG_NAME <>", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThan(String value) {
            addCriterion("ORG_NAME >", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_NAME >=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThan(String value) {
            addCriterion("ORG_NAME <", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThanOrEqualTo(String value) {
            addCriterion("ORG_NAME <=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLike(String value) {
            addCriterion("ORG_NAME like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotLike(String value) {
            addCriterion("ORG_NAME not like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameIn(List<String> values) {
            addCriterion("ORG_NAME in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotIn(List<String> values) {
            addCriterion("ORG_NAME not in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameBetween(String value1, String value2) {
            addCriterion("ORG_NAME between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotBetween(String value1, String value2) {
            addCriterion("ORG_NAME not between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andDateCodeIsNull() {
            addCriterion("DATE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andDateCodeIsNotNull() {
            addCriterion("DATE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andDateCodeEqualTo(String value) {
            addCriterion("DATE_CODE =", value, "dateCode");
            return (Criteria) this;
        }

        public Criteria andDateCodeNotEqualTo(String value) {
            addCriterion("DATE_CODE <>", value, "dateCode");
            return (Criteria) this;
        }

        public Criteria andDateCodeGreaterThan(String value) {
            addCriterion("DATE_CODE >", value, "dateCode");
            return (Criteria) this;
        }

        public Criteria andDateCodeGreaterThanOrEqualTo(String value) {
            addCriterion("DATE_CODE >=", value, "dateCode");
            return (Criteria) this;
        }

        public Criteria andDateCodeLessThan(String value) {
            addCriterion("DATE_CODE <", value, "dateCode");
            return (Criteria) this;
        }

        public Criteria andDateCodeLessThanOrEqualTo(String value) {
            addCriterion("DATE_CODE <=", value, "dateCode");
            return (Criteria) this;
        }

        public Criteria andDateCodeLike(String value) {
            addCriterion("DATE_CODE like", value, "dateCode");
            return (Criteria) this;
        }

        public Criteria andDateCodeNotLike(String value) {
            addCriterion("DATE_CODE not like", value, "dateCode");
            return (Criteria) this;
        }

        public Criteria andDateCodeIn(List<String> values) {
            addCriterion("DATE_CODE in", values, "dateCode");
            return (Criteria) this;
        }

        public Criteria andDateCodeNotIn(List<String> values) {
            addCriterion("DATE_CODE not in", values, "dateCode");
            return (Criteria) this;
        }

        public Criteria andDateCodeBetween(String value1, String value2) {
            addCriterion("DATE_CODE between", value1, value2, "dateCode");
            return (Criteria) this;
        }

        public Criteria andDateCodeNotBetween(String value1, String value2) {
            addCriterion("DATE_CODE not between", value1, value2, "dateCode");
            return (Criteria) this;
        }

        public Criteria andRecePremIsNull() {
            addCriterion("RECE_PREM is null");
            return (Criteria) this;
        }

        public Criteria andRecePremIsNotNull() {
            addCriterion("RECE_PREM is not null");
            return (Criteria) this;
        }

        public Criteria andRecePremEqualTo(BigDecimal value) {
            addCriterion("RECE_PREM =", value, "recePrem");
            return (Criteria) this;
        }

        public Criteria andRecePremNotEqualTo(BigDecimal value) {
            addCriterion("RECE_PREM <>", value, "recePrem");
            return (Criteria) this;
        }

        public Criteria andRecePremGreaterThan(BigDecimal value) {
            addCriterion("RECE_PREM >", value, "recePrem");
            return (Criteria) this;
        }

        public Criteria andRecePremGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("RECE_PREM >=", value, "recePrem");
            return (Criteria) this;
        }

        public Criteria andRecePremLessThan(BigDecimal value) {
            addCriterion("RECE_PREM <", value, "recePrem");
            return (Criteria) this;
        }

        public Criteria andRecePremLessThanOrEqualTo(BigDecimal value) {
            addCriterion("RECE_PREM <=", value, "recePrem");
            return (Criteria) this;
        }

        public Criteria andRecePremIn(List<BigDecimal> values) {
            addCriterion("RECE_PREM in", values, "recePrem");
            return (Criteria) this;
        }

        public Criteria andRecePremNotIn(List<BigDecimal> values) {
            addCriterion("RECE_PREM not in", values, "recePrem");
            return (Criteria) this;
        }

        public Criteria andRecePremBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("RECE_PREM between", value1, value2, "recePrem");
            return (Criteria) this;
        }

        public Criteria andRecePremNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("RECE_PREM not between", value1, value2, "recePrem");
            return (Criteria) this;
        }

        public Criteria andStadPremIsNull() {
            addCriterion("STAD_PREM is null");
            return (Criteria) this;
        }

        public Criteria andStadPremIsNotNull() {
            addCriterion("STAD_PREM is not null");
            return (Criteria) this;
        }

        public Criteria andStadPremEqualTo(BigDecimal value) {
            addCriterion("STAD_PREM =", value, "stadPrem");
            return (Criteria) this;
        }

        public Criteria andStadPremNotEqualTo(BigDecimal value) {
            addCriterion("STAD_PREM <>", value, "stadPrem");
            return (Criteria) this;
        }

        public Criteria andStadPremGreaterThan(BigDecimal value) {
            addCriterion("STAD_PREM >", value, "stadPrem");
            return (Criteria) this;
        }

        public Criteria andStadPremGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("STAD_PREM >=", value, "stadPrem");
            return (Criteria) this;
        }

        public Criteria andStadPremLessThan(BigDecimal value) {
            addCriterion("STAD_PREM <", value, "stadPrem");
            return (Criteria) this;
        }

        public Criteria andStadPremLessThanOrEqualTo(BigDecimal value) {
            addCriterion("STAD_PREM <=", value, "stadPrem");
            return (Criteria) this;
        }

        public Criteria andStadPremIn(List<BigDecimal> values) {
            addCriterion("STAD_PREM in", values, "stadPrem");
            return (Criteria) this;
        }

        public Criteria andStadPremNotIn(List<BigDecimal> values) {
            addCriterion("STAD_PREM not in", values, "stadPrem");
            return (Criteria) this;
        }

        public Criteria andStadPremBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("STAD_PREM between", value1, value2, "stadPrem");
            return (Criteria) this;
        }

        public Criteria andStadPremNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("STAD_PREM not between", value1, value2, "stadPrem");
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