package com.huatai.web.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TrainTargetExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TrainTargetExample() {
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

        public Criteria andGuardTranReateIsNull() {
            addCriterion("GUARD_TRAN_REATE is null");
            return (Criteria) this;
        }

        public Criteria andGuardTranReateIsNotNull() {
            addCriterion("GUARD_TRAN_REATE is not null");
            return (Criteria) this;
        }

        public Criteria andGuardTranReateEqualTo(BigDecimal value) {
            addCriterion("GUARD_TRAN_REATE =", value, "guardTranReate");
            return (Criteria) this;
        }

        public Criteria andGuardTranReateNotEqualTo(BigDecimal value) {
            addCriterion("GUARD_TRAN_REATE <>", value, "guardTranReate");
            return (Criteria) this;
        }

        public Criteria andGuardTranReateGreaterThan(BigDecimal value) {
            addCriterion("GUARD_TRAN_REATE >", value, "guardTranReate");
            return (Criteria) this;
        }

        public Criteria andGuardTranReateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("GUARD_TRAN_REATE >=", value, "guardTranReate");
            return (Criteria) this;
        }

        public Criteria andGuardTranReateLessThan(BigDecimal value) {
            addCriterion("GUARD_TRAN_REATE <", value, "guardTranReate");
            return (Criteria) this;
        }

        public Criteria andGuardTranReateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("GUARD_TRAN_REATE <=", value, "guardTranReate");
            return (Criteria) this;
        }

        public Criteria andGuardTranReateIn(List<BigDecimal> values) {
            addCriterion("GUARD_TRAN_REATE in", values, "guardTranReate");
            return (Criteria) this;
        }

        public Criteria andGuardTranReateNotIn(List<BigDecimal> values) {
            addCriterion("GUARD_TRAN_REATE not in", values, "guardTranReate");
            return (Criteria) this;
        }

        public Criteria andGuardTranReateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("GUARD_TRAN_REATE between", value1, value2, "guardTranReate");
            return (Criteria) this;
        }

        public Criteria andGuardTranReateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("GUARD_TRAN_REATE not between", value1, value2, "guardTranReate");
            return (Criteria) this;
        }

        public Criteria andHireNewRateIsNull() {
            addCriterion("HIRE_NEW_RATE is null");
            return (Criteria) this;
        }

        public Criteria andHireNewRateIsNotNull() {
            addCriterion("HIRE_NEW_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andHireNewRateEqualTo(BigDecimal value) {
            addCriterion("HIRE_NEW_RATE =", value, "hireNewRate");
            return (Criteria) this;
        }

        public Criteria andHireNewRateNotEqualTo(BigDecimal value) {
            addCriterion("HIRE_NEW_RATE <>", value, "hireNewRate");
            return (Criteria) this;
        }

        public Criteria andHireNewRateGreaterThan(BigDecimal value) {
            addCriterion("HIRE_NEW_RATE >", value, "hireNewRate");
            return (Criteria) this;
        }

        public Criteria andHireNewRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("HIRE_NEW_RATE >=", value, "hireNewRate");
            return (Criteria) this;
        }

        public Criteria andHireNewRateLessThan(BigDecimal value) {
            addCriterion("HIRE_NEW_RATE <", value, "hireNewRate");
            return (Criteria) this;
        }

        public Criteria andHireNewRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("HIRE_NEW_RATE <=", value, "hireNewRate");
            return (Criteria) this;
        }

        public Criteria andHireNewRateIn(List<BigDecimal> values) {
            addCriterion("HIRE_NEW_RATE in", values, "hireNewRate");
            return (Criteria) this;
        }

        public Criteria andHireNewRateNotIn(List<BigDecimal> values) {
            addCriterion("HIRE_NEW_RATE not in", values, "hireNewRate");
            return (Criteria) this;
        }

        public Criteria andHireNewRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("HIRE_NEW_RATE between", value1, value2, "hireNewRate");
            return (Criteria) this;
        }

        public Criteria andHireNewRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("HIRE_NEW_RATE not between", value1, value2, "hireNewRate");
            return (Criteria) this;
        }

        public Criteria andHireMembNewRateIsNull() {
            addCriterion("HIRE_MEMB_NEW_RATE is null");
            return (Criteria) this;
        }

        public Criteria andHireMembNewRateIsNotNull() {
            addCriterion("HIRE_MEMB_NEW_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andHireMembNewRateEqualTo(BigDecimal value) {
            addCriterion("HIRE_MEMB_NEW_RATE =", value, "hireMembNewRate");
            return (Criteria) this;
        }

        public Criteria andHireMembNewRateNotEqualTo(BigDecimal value) {
            addCriterion("HIRE_MEMB_NEW_RATE <>", value, "hireMembNewRate");
            return (Criteria) this;
        }

        public Criteria andHireMembNewRateGreaterThan(BigDecimal value) {
            addCriterion("HIRE_MEMB_NEW_RATE >", value, "hireMembNewRate");
            return (Criteria) this;
        }

        public Criteria andHireMembNewRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("HIRE_MEMB_NEW_RATE >=", value, "hireMembNewRate");
            return (Criteria) this;
        }

        public Criteria andHireMembNewRateLessThan(BigDecimal value) {
            addCriterion("HIRE_MEMB_NEW_RATE <", value, "hireMembNewRate");
            return (Criteria) this;
        }

        public Criteria andHireMembNewRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("HIRE_MEMB_NEW_RATE <=", value, "hireMembNewRate");
            return (Criteria) this;
        }

        public Criteria andHireMembNewRateIn(List<BigDecimal> values) {
            addCriterion("HIRE_MEMB_NEW_RATE in", values, "hireMembNewRate");
            return (Criteria) this;
        }

        public Criteria andHireMembNewRateNotIn(List<BigDecimal> values) {
            addCriterion("HIRE_MEMB_NEW_RATE not in", values, "hireMembNewRate");
            return (Criteria) this;
        }

        public Criteria andHireMembNewRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("HIRE_MEMB_NEW_RATE between", value1, value2, "hireMembNewRate");
            return (Criteria) this;
        }

        public Criteria andHireMembNewRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("HIRE_MEMB_NEW_RATE not between", value1, value2, "hireMembNewRate");
            return (Criteria) this;
        }

        public Criteria andHireNewNumIsNull() {
            addCriterion("HIRE_NEW_NUM is null");
            return (Criteria) this;
        }

        public Criteria andHireNewNumIsNotNull() {
            addCriterion("HIRE_NEW_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andHireNewNumEqualTo(BigDecimal value) {
            addCriterion("HIRE_NEW_NUM =", value, "hireNewNum");
            return (Criteria) this;
        }

        public Criteria andHireNewNumNotEqualTo(BigDecimal value) {
            addCriterion("HIRE_NEW_NUM <>", value, "hireNewNum");
            return (Criteria) this;
        }

        public Criteria andHireNewNumGreaterThan(BigDecimal value) {
            addCriterion("HIRE_NEW_NUM >", value, "hireNewNum");
            return (Criteria) this;
        }

        public Criteria andHireNewNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("HIRE_NEW_NUM >=", value, "hireNewNum");
            return (Criteria) this;
        }

        public Criteria andHireNewNumLessThan(BigDecimal value) {
            addCriterion("HIRE_NEW_NUM <", value, "hireNewNum");
            return (Criteria) this;
        }

        public Criteria andHireNewNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("HIRE_NEW_NUM <=", value, "hireNewNum");
            return (Criteria) this;
        }

        public Criteria andHireNewNumIn(List<BigDecimal> values) {
            addCriterion("HIRE_NEW_NUM in", values, "hireNewNum");
            return (Criteria) this;
        }

        public Criteria andHireNewNumNotIn(List<BigDecimal> values) {
            addCriterion("HIRE_NEW_NUM not in", values, "hireNewNum");
            return (Criteria) this;
        }

        public Criteria andHireNewNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("HIRE_NEW_NUM between", value1, value2, "hireNewNum");
            return (Criteria) this;
        }

        public Criteria andHireNewNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("HIRE_NEW_NUM not between", value1, value2, "hireNewNum");
            return (Criteria) this;
        }

        public Criteria andMembNewNumIsNull() {
            addCriterion("MEMB_NEW_NUM is null");
            return (Criteria) this;
        }

        public Criteria andMembNewNumIsNotNull() {
            addCriterion("MEMB_NEW_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andMembNewNumEqualTo(BigDecimal value) {
            addCriterion("MEMB_NEW_NUM =", value, "membNewNum");
            return (Criteria) this;
        }

        public Criteria andMembNewNumNotEqualTo(BigDecimal value) {
            addCriterion("MEMB_NEW_NUM <>", value, "membNewNum");
            return (Criteria) this;
        }

        public Criteria andMembNewNumGreaterThan(BigDecimal value) {
            addCriterion("MEMB_NEW_NUM >", value, "membNewNum");
            return (Criteria) this;
        }

        public Criteria andMembNewNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("MEMB_NEW_NUM >=", value, "membNewNum");
            return (Criteria) this;
        }

        public Criteria andMembNewNumLessThan(BigDecimal value) {
            addCriterion("MEMB_NEW_NUM <", value, "membNewNum");
            return (Criteria) this;
        }

        public Criteria andMembNewNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("MEMB_NEW_NUM <=", value, "membNewNum");
            return (Criteria) this;
        }

        public Criteria andMembNewNumIn(List<BigDecimal> values) {
            addCriterion("MEMB_NEW_NUM in", values, "membNewNum");
            return (Criteria) this;
        }

        public Criteria andMembNewNumNotIn(List<BigDecimal> values) {
            addCriterion("MEMB_NEW_NUM not in", values, "membNewNum");
            return (Criteria) this;
        }

        public Criteria andMembNewNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MEMB_NEW_NUM between", value1, value2, "membNewNum");
            return (Criteria) this;
        }

        public Criteria andMembNewNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MEMB_NEW_NUM not between", value1, value2, "membNewNum");
            return (Criteria) this;
        }

        public Criteria andHireNewStadPremIsNull() {
            addCriterion("HIRE_NEW_STAD_PREM is null");
            return (Criteria) this;
        }

        public Criteria andHireNewStadPremIsNotNull() {
            addCriterion("HIRE_NEW_STAD_PREM is not null");
            return (Criteria) this;
        }

        public Criteria andHireNewStadPremEqualTo(BigDecimal value) {
            addCriterion("HIRE_NEW_STAD_PREM =", value, "hireNewStadPrem");
            return (Criteria) this;
        }

        public Criteria andHireNewStadPremNotEqualTo(BigDecimal value) {
            addCriterion("HIRE_NEW_STAD_PREM <>", value, "hireNewStadPrem");
            return (Criteria) this;
        }

        public Criteria andHireNewStadPremGreaterThan(BigDecimal value) {
            addCriterion("HIRE_NEW_STAD_PREM >", value, "hireNewStadPrem");
            return (Criteria) this;
        }

        public Criteria andHireNewStadPremGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("HIRE_NEW_STAD_PREM >=", value, "hireNewStadPrem");
            return (Criteria) this;
        }

        public Criteria andHireNewStadPremLessThan(BigDecimal value) {
            addCriterion("HIRE_NEW_STAD_PREM <", value, "hireNewStadPrem");
            return (Criteria) this;
        }

        public Criteria andHireNewStadPremLessThanOrEqualTo(BigDecimal value) {
            addCriterion("HIRE_NEW_STAD_PREM <=", value, "hireNewStadPrem");
            return (Criteria) this;
        }

        public Criteria andHireNewStadPremIn(List<BigDecimal> values) {
            addCriterion("HIRE_NEW_STAD_PREM in", values, "hireNewStadPrem");
            return (Criteria) this;
        }

        public Criteria andHireNewStadPremNotIn(List<BigDecimal> values) {
            addCriterion("HIRE_NEW_STAD_PREM not in", values, "hireNewStadPrem");
            return (Criteria) this;
        }

        public Criteria andHireNewStadPremBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("HIRE_NEW_STAD_PREM between", value1, value2, "hireNewStadPrem");
            return (Criteria) this;
        }

        public Criteria andHireNewStadPremNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("HIRE_NEW_STAD_PREM not between", value1, value2, "hireNewStadPrem");
            return (Criteria) this;
        }

        public Criteria andHireMembNewNumIsNull() {
            addCriterion("HIRE_MEMB_NEW_NUM is null");
            return (Criteria) this;
        }

        public Criteria andHireMembNewNumIsNotNull() {
            addCriterion("HIRE_MEMB_NEW_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andHireMembNewNumEqualTo(BigDecimal value) {
            addCriterion("HIRE_MEMB_NEW_NUM =", value, "hireMembNewNum");
            return (Criteria) this;
        }

        public Criteria andHireMembNewNumNotEqualTo(BigDecimal value) {
            addCriterion("HIRE_MEMB_NEW_NUM <>", value, "hireMembNewNum");
            return (Criteria) this;
        }

        public Criteria andHireMembNewNumGreaterThan(BigDecimal value) {
            addCriterion("HIRE_MEMB_NEW_NUM >", value, "hireMembNewNum");
            return (Criteria) this;
        }

        public Criteria andHireMembNewNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("HIRE_MEMB_NEW_NUM >=", value, "hireMembNewNum");
            return (Criteria) this;
        }

        public Criteria andHireMembNewNumLessThan(BigDecimal value) {
            addCriterion("HIRE_MEMB_NEW_NUM <", value, "hireMembNewNum");
            return (Criteria) this;
        }

        public Criteria andHireMembNewNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("HIRE_MEMB_NEW_NUM <=", value, "hireMembNewNum");
            return (Criteria) this;
        }

        public Criteria andHireMembNewNumIn(List<BigDecimal> values) {
            addCriterion("HIRE_MEMB_NEW_NUM in", values, "hireMembNewNum");
            return (Criteria) this;
        }

        public Criteria andHireMembNewNumNotIn(List<BigDecimal> values) {
            addCriterion("HIRE_MEMB_NEW_NUM not in", values, "hireMembNewNum");
            return (Criteria) this;
        }

        public Criteria andHireMembNewNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("HIRE_MEMB_NEW_NUM between", value1, value2, "hireMembNewNum");
            return (Criteria) this;
        }

        public Criteria andHireMembNewNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("HIRE_MEMB_NEW_NUM not between", value1, value2, "hireMembNewNum");
            return (Criteria) this;
        }

        public Criteria andHireMembNewmanNumIsNull() {
            addCriterion("HIRE_MEMB_NEWMAN_NUM is null");
            return (Criteria) this;
        }

        public Criteria andHireMembNewmanNumIsNotNull() {
            addCriterion("HIRE_MEMB_NEWMAN_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andHireMembNewmanNumEqualTo(BigDecimal value) {
            addCriterion("HIRE_MEMB_NEWMAN_NUM =", value, "hireMembNewmanNum");
            return (Criteria) this;
        }

        public Criteria andHireMembNewmanNumNotEqualTo(BigDecimal value) {
            addCriterion("HIRE_MEMB_NEWMAN_NUM <>", value, "hireMembNewmanNum");
            return (Criteria) this;
        }

        public Criteria andHireMembNewmanNumGreaterThan(BigDecimal value) {
            addCriterion("HIRE_MEMB_NEWMAN_NUM >", value, "hireMembNewmanNum");
            return (Criteria) this;
        }

        public Criteria andHireMembNewmanNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("HIRE_MEMB_NEWMAN_NUM >=", value, "hireMembNewmanNum");
            return (Criteria) this;
        }

        public Criteria andHireMembNewmanNumLessThan(BigDecimal value) {
            addCriterion("HIRE_MEMB_NEWMAN_NUM <", value, "hireMembNewmanNum");
            return (Criteria) this;
        }

        public Criteria andHireMembNewmanNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("HIRE_MEMB_NEWMAN_NUM <=", value, "hireMembNewmanNum");
            return (Criteria) this;
        }

        public Criteria andHireMembNewmanNumIn(List<BigDecimal> values) {
            addCriterion("HIRE_MEMB_NEWMAN_NUM in", values, "hireMembNewmanNum");
            return (Criteria) this;
        }

        public Criteria andHireMembNewmanNumNotIn(List<BigDecimal> values) {
            addCriterion("HIRE_MEMB_NEWMAN_NUM not in", values, "hireMembNewmanNum");
            return (Criteria) this;
        }

        public Criteria andHireMembNewmanNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("HIRE_MEMB_NEWMAN_NUM between", value1, value2, "hireMembNewmanNum");
            return (Criteria) this;
        }

        public Criteria andHireMembNewmanNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("HIRE_MEMB_NEWMAN_NUM not between", value1, value2, "hireMembNewmanNum");
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