package com.huatai.web.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LabelOrgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LabelOrgExample() {
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

        public Criteria andLastGradeIsNull() {
            addCriterion("LAST_GRADE is null");
            return (Criteria) this;
        }

        public Criteria andLastGradeIsNotNull() {
            addCriterion("LAST_GRADE is not null");
            return (Criteria) this;
        }

        public Criteria andLastGradeEqualTo(String value) {
            addCriterion("LAST_GRADE =", value, "lastGrade");
            return (Criteria) this;
        }

        public Criteria andLastGradeNotEqualTo(String value) {
            addCriterion("LAST_GRADE <>", value, "lastGrade");
            return (Criteria) this;
        }

        public Criteria andLastGradeGreaterThan(String value) {
            addCriterion("LAST_GRADE >", value, "lastGrade");
            return (Criteria) this;
        }

        public Criteria andLastGradeGreaterThanOrEqualTo(String value) {
            addCriterion("LAST_GRADE >=", value, "lastGrade");
            return (Criteria) this;
        }

        public Criteria andLastGradeLessThan(String value) {
            addCriterion("LAST_GRADE <", value, "lastGrade");
            return (Criteria) this;
        }

        public Criteria andLastGradeLessThanOrEqualTo(String value) {
            addCriterion("LAST_GRADE <=", value, "lastGrade");
            return (Criteria) this;
        }

        public Criteria andLastGradeLike(String value) {
            addCriterion("LAST_GRADE like", value, "lastGrade");
            return (Criteria) this;
        }

        public Criteria andLastGradeNotLike(String value) {
            addCriterion("LAST_GRADE not like", value, "lastGrade");
            return (Criteria) this;
        }

        public Criteria andLastGradeIn(List<String> values) {
            addCriterion("LAST_GRADE in", values, "lastGrade");
            return (Criteria) this;
        }

        public Criteria andLastGradeNotIn(List<String> values) {
            addCriterion("LAST_GRADE not in", values, "lastGrade");
            return (Criteria) this;
        }

        public Criteria andLastGradeBetween(String value1, String value2) {
            addCriterion("LAST_GRADE between", value1, value2, "lastGrade");
            return (Criteria) this;
        }

        public Criteria andLastGradeNotBetween(String value1, String value2) {
            addCriterion("LAST_GRADE not between", value1, value2, "lastGrade");
            return (Criteria) this;
        }

        public Criteria andGradeIsNull() {
            addCriterion("GRADE is null");
            return (Criteria) this;
        }

        public Criteria andGradeIsNotNull() {
            addCriterion("GRADE is not null");
            return (Criteria) this;
        }

        public Criteria andGradeEqualTo(String value) {
            addCriterion("GRADE =", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotEqualTo(String value) {
            addCriterion("GRADE <>", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThan(String value) {
            addCriterion("GRADE >", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThanOrEqualTo(String value) {
            addCriterion("GRADE >=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThan(String value) {
            addCriterion("GRADE <", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThanOrEqualTo(String value) {
            addCriterion("GRADE <=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLike(String value) {
            addCriterion("GRADE like", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotLike(String value) {
            addCriterion("GRADE not like", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeIn(List<String> values) {
            addCriterion("GRADE in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotIn(List<String> values) {
            addCriterion("GRADE not in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeBetween(String value1, String value2) {
            addCriterion("GRADE between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotBetween(String value1, String value2) {
            addCriterion("GRADE not between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andMonAveGrowthRateIsNull() {
            addCriterion("MON_AVE_GROWTH_RATE is null");
            return (Criteria) this;
        }

        public Criteria andMonAveGrowthRateIsNotNull() {
            addCriterion("MON_AVE_GROWTH_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andMonAveGrowthRateEqualTo(BigDecimal value) {
            addCriterion("MON_AVE_GROWTH_RATE =", value, "monAveGrowthRate");
            return (Criteria) this;
        }

        public Criteria andMonAveGrowthRateNotEqualTo(BigDecimal value) {
            addCriterion("MON_AVE_GROWTH_RATE <>", value, "monAveGrowthRate");
            return (Criteria) this;
        }

        public Criteria andMonAveGrowthRateGreaterThan(BigDecimal value) {
            addCriterion("MON_AVE_GROWTH_RATE >", value, "monAveGrowthRate");
            return (Criteria) this;
        }

        public Criteria andMonAveGrowthRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("MON_AVE_GROWTH_RATE >=", value, "monAveGrowthRate");
            return (Criteria) this;
        }

        public Criteria andMonAveGrowthRateLessThan(BigDecimal value) {
            addCriterion("MON_AVE_GROWTH_RATE <", value, "monAveGrowthRate");
            return (Criteria) this;
        }

        public Criteria andMonAveGrowthRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("MON_AVE_GROWTH_RATE <=", value, "monAveGrowthRate");
            return (Criteria) this;
        }

        public Criteria andMonAveGrowthRateIn(List<BigDecimal> values) {
            addCriterion("MON_AVE_GROWTH_RATE in", values, "monAveGrowthRate");
            return (Criteria) this;
        }

        public Criteria andMonAveGrowthRateNotIn(List<BigDecimal> values) {
            addCriterion("MON_AVE_GROWTH_RATE not in", values, "monAveGrowthRate");
            return (Criteria) this;
        }

        public Criteria andMonAveGrowthRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MON_AVE_GROWTH_RATE between", value1, value2, "monAveGrowthRate");
            return (Criteria) this;
        }

        public Criteria andMonAveGrowthRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MON_AVE_GROWTH_RATE not between", value1, value2, "monAveGrowthRate");
            return (Criteria) this;
        }

        public Criteria andOrgReachRateIsNull() {
            addCriterion("ORG_REACH_RATE is null");
            return (Criteria) this;
        }

        public Criteria andOrgReachRateIsNotNull() {
            addCriterion("ORG_REACH_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andOrgReachRateEqualTo(BigDecimal value) {
            addCriterion("ORG_REACH_RATE =", value, "orgReachRate");
            return (Criteria) this;
        }

        public Criteria andOrgReachRateNotEqualTo(BigDecimal value) {
            addCriterion("ORG_REACH_RATE <>", value, "orgReachRate");
            return (Criteria) this;
        }

        public Criteria andOrgReachRateGreaterThan(BigDecimal value) {
            addCriterion("ORG_REACH_RATE >", value, "orgReachRate");
            return (Criteria) this;
        }

        public Criteria andOrgReachRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ORG_REACH_RATE >=", value, "orgReachRate");
            return (Criteria) this;
        }

        public Criteria andOrgReachRateLessThan(BigDecimal value) {
            addCriterion("ORG_REACH_RATE <", value, "orgReachRate");
            return (Criteria) this;
        }

        public Criteria andOrgReachRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ORG_REACH_RATE <=", value, "orgReachRate");
            return (Criteria) this;
        }

        public Criteria andOrgReachRateIn(List<BigDecimal> values) {
            addCriterion("ORG_REACH_RATE in", values, "orgReachRate");
            return (Criteria) this;
        }

        public Criteria andOrgReachRateNotIn(List<BigDecimal> values) {
            addCriterion("ORG_REACH_RATE not in", values, "orgReachRate");
            return (Criteria) this;
        }

        public Criteria andOrgReachRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ORG_REACH_RATE between", value1, value2, "orgReachRate");
            return (Criteria) this;
        }

        public Criteria andOrgReachRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ORG_REACH_RATE not between", value1, value2, "orgReachRate");
            return (Criteria) this;
        }

        public Criteria andK2IsNull() {
            addCriterion("K2 is null");
            return (Criteria) this;
        }

        public Criteria andK2IsNotNull() {
            addCriterion("K2 is not null");
            return (Criteria) this;
        }

        public Criteria andK2EqualTo(BigDecimal value) {
            addCriterion("K2 =", value, "k2");
            return (Criteria) this;
        }

        public Criteria andK2NotEqualTo(BigDecimal value) {
            addCriterion("K2 <>", value, "k2");
            return (Criteria) this;
        }

        public Criteria andK2GreaterThan(BigDecimal value) {
            addCriterion("K2 >", value, "k2");
            return (Criteria) this;
        }

        public Criteria andK2GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("K2 >=", value, "k2");
            return (Criteria) this;
        }

        public Criteria andK2LessThan(BigDecimal value) {
            addCriterion("K2 <", value, "k2");
            return (Criteria) this;
        }

        public Criteria andK2LessThanOrEqualTo(BigDecimal value) {
            addCriterion("K2 <=", value, "k2");
            return (Criteria) this;
        }

        public Criteria andK2In(List<BigDecimal> values) {
            addCriterion("K2 in", values, "k2");
            return (Criteria) this;
        }

        public Criteria andK2NotIn(List<BigDecimal> values) {
            addCriterion("K2 not in", values, "k2");
            return (Criteria) this;
        }

        public Criteria andK2Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("K2 between", value1, value2, "k2");
            return (Criteria) this;
        }

        public Criteria andK2NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("K2 not between", value1, value2, "k2");
            return (Criteria) this;
        }

        public Criteria andMonAveConversionManpowerIsNull() {
            addCriterion("MON_AVE_CONVERSION_MANPOWER is null");
            return (Criteria) this;
        }

        public Criteria andMonAveConversionManpowerIsNotNull() {
            addCriterion("MON_AVE_CONVERSION_MANPOWER is not null");
            return (Criteria) this;
        }

        public Criteria andMonAveConversionManpowerEqualTo(BigDecimal value) {
            addCriterion("MON_AVE_CONVERSION_MANPOWER =", value, "monAveConversionManpower");
            return (Criteria) this;
        }

        public Criteria andMonAveConversionManpowerNotEqualTo(BigDecimal value) {
            addCriterion("MON_AVE_CONVERSION_MANPOWER <>", value, "monAveConversionManpower");
            return (Criteria) this;
        }

        public Criteria andMonAveConversionManpowerGreaterThan(BigDecimal value) {
            addCriterion("MON_AVE_CONVERSION_MANPOWER >", value, "monAveConversionManpower");
            return (Criteria) this;
        }

        public Criteria andMonAveConversionManpowerGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("MON_AVE_CONVERSION_MANPOWER >=", value, "monAveConversionManpower");
            return (Criteria) this;
        }

        public Criteria andMonAveConversionManpowerLessThan(BigDecimal value) {
            addCriterion("MON_AVE_CONVERSION_MANPOWER <", value, "monAveConversionManpower");
            return (Criteria) this;
        }

        public Criteria andMonAveConversionManpowerLessThanOrEqualTo(BigDecimal value) {
            addCriterion("MON_AVE_CONVERSION_MANPOWER <=", value, "monAveConversionManpower");
            return (Criteria) this;
        }

        public Criteria andMonAveConversionManpowerIn(List<BigDecimal> values) {
            addCriterion("MON_AVE_CONVERSION_MANPOWER in", values, "monAveConversionManpower");
            return (Criteria) this;
        }

        public Criteria andMonAveConversionManpowerNotIn(List<BigDecimal> values) {
            addCriterion("MON_AVE_CONVERSION_MANPOWER not in", values, "monAveConversionManpower");
            return (Criteria) this;
        }

        public Criteria andMonAveConversionManpowerBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MON_AVE_CONVERSION_MANPOWER between", value1, value2, "monAveConversionManpower");
            return (Criteria) this;
        }

        public Criteria andMonAveConversionManpowerNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MON_AVE_CONVERSION_MANPOWER not between", value1, value2, "monAveConversionManpower");
            return (Criteria) this;
        }

        public Criteria andMonAveIsNull() {
            addCriterion("MON_AVE is null");
            return (Criteria) this;
        }

        public Criteria andMonAveIsNotNull() {
            addCriterion("MON_AVE is not null");
            return (Criteria) this;
        }

        public Criteria andMonAveEqualTo(BigDecimal value) {
            addCriterion("MON_AVE =", value, "monAve");
            return (Criteria) this;
        }

        public Criteria andMonAveNotEqualTo(BigDecimal value) {
            addCriterion("MON_AVE <>", value, "monAve");
            return (Criteria) this;
        }

        public Criteria andMonAveGreaterThan(BigDecimal value) {
            addCriterion("MON_AVE >", value, "monAve");
            return (Criteria) this;
        }

        public Criteria andMonAveGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("MON_AVE >=", value, "monAve");
            return (Criteria) this;
        }

        public Criteria andMonAveLessThan(BigDecimal value) {
            addCriterion("MON_AVE <", value, "monAve");
            return (Criteria) this;
        }

        public Criteria andMonAveLessThanOrEqualTo(BigDecimal value) {
            addCriterion("MON_AVE <=", value, "monAve");
            return (Criteria) this;
        }

        public Criteria andMonAveIn(List<BigDecimal> values) {
            addCriterion("MON_AVE in", values, "monAve");
            return (Criteria) this;
        }

        public Criteria andMonAveNotIn(List<BigDecimal> values) {
            addCriterion("MON_AVE not in", values, "monAve");
            return (Criteria) this;
        }

        public Criteria andMonAveBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MON_AVE between", value1, value2, "monAve");
            return (Criteria) this;
        }

        public Criteria andMonAveNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MON_AVE not between", value1, value2, "monAve");
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