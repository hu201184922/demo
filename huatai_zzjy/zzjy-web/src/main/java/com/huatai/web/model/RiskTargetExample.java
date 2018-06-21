package com.huatai.web.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RiskTargetExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RiskTargetExample() {
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

        public Criteria andReceStadPremIsNull() {
            addCriterion("RECE_STAD_PREM is null");
            return (Criteria) this;
        }

        public Criteria andReceStadPremIsNotNull() {
            addCriterion("RECE_STAD_PREM is not null");
            return (Criteria) this;
        }

        public Criteria andReceStadPremEqualTo(BigDecimal value) {
            addCriterion("RECE_STAD_PREM =", value, "receStadPrem");
            return (Criteria) this;
        }

        public Criteria andReceStadPremNotEqualTo(BigDecimal value) {
            addCriterion("RECE_STAD_PREM <>", value, "receStadPrem");
            return (Criteria) this;
        }

        public Criteria andReceStadPremGreaterThan(BigDecimal value) {
            addCriterion("RECE_STAD_PREM >", value, "receStadPrem");
            return (Criteria) this;
        }

        public Criteria andReceStadPremGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("RECE_STAD_PREM >=", value, "receStadPrem");
            return (Criteria) this;
        }

        public Criteria andReceStadPremLessThan(BigDecimal value) {
            addCriterion("RECE_STAD_PREM <", value, "receStadPrem");
            return (Criteria) this;
        }

        public Criteria andReceStadPremLessThanOrEqualTo(BigDecimal value) {
            addCriterion("RECE_STAD_PREM <=", value, "receStadPrem");
            return (Criteria) this;
        }

        public Criteria andReceStadPremIn(List<BigDecimal> values) {
            addCriterion("RECE_STAD_PREM in", values, "receStadPrem");
            return (Criteria) this;
        }

        public Criteria andReceStadPremNotIn(List<BigDecimal> values) {
            addCriterion("RECE_STAD_PREM not in", values, "receStadPrem");
            return (Criteria) this;
        }

        public Criteria andReceStadPremBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("RECE_STAD_PREM between", value1, value2, "receStadPrem");
            return (Criteria) this;
        }

        public Criteria andReceStadPremNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("RECE_STAD_PREM not between", value1, value2, "receStadPrem");
            return (Criteria) this;
        }

        public Criteria andValuePremIsNull() {
            addCriterion("VALUE_PREM is null");
            return (Criteria) this;
        }

        public Criteria andValuePremIsNotNull() {
            addCriterion("VALUE_PREM is not null");
            return (Criteria) this;
        }

        public Criteria andValuePremEqualTo(BigDecimal value) {
            addCriterion("VALUE_PREM =", value, "valuePrem");
            return (Criteria) this;
        }

        public Criteria andValuePremNotEqualTo(BigDecimal value) {
            addCriterion("VALUE_PREM <>", value, "valuePrem");
            return (Criteria) this;
        }

        public Criteria andValuePremGreaterThan(BigDecimal value) {
            addCriterion("VALUE_PREM >", value, "valuePrem");
            return (Criteria) this;
        }

        public Criteria andValuePremGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("VALUE_PREM >=", value, "valuePrem");
            return (Criteria) this;
        }

        public Criteria andValuePremLessThan(BigDecimal value) {
            addCriterion("VALUE_PREM <", value, "valuePrem");
            return (Criteria) this;
        }

        public Criteria andValuePremLessThanOrEqualTo(BigDecimal value) {
            addCriterion("VALUE_PREM <=", value, "valuePrem");
            return (Criteria) this;
        }

        public Criteria andValuePremIn(List<BigDecimal> values) {
            addCriterion("VALUE_PREM in", values, "valuePrem");
            return (Criteria) this;
        }

        public Criteria andValuePremNotIn(List<BigDecimal> values) {
            addCriterion("VALUE_PREM not in", values, "valuePrem");
            return (Criteria) this;
        }

        public Criteria andValuePremBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("VALUE_PREM between", value1, value2, "valuePrem");
            return (Criteria) this;
        }

        public Criteria andValuePremNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("VALUE_PREM not between", value1, value2, "valuePrem");
            return (Criteria) this;
        }

        public Criteria andRecePreGoldBeeNumIsNull() {
            addCriterion("RECE_PRE_GOLD_BEE_NUM is null");
            return (Criteria) this;
        }

        public Criteria andRecePreGoldBeeNumIsNotNull() {
            addCriterion("RECE_PRE_GOLD_BEE_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andRecePreGoldBeeNumEqualTo(BigDecimal value) {
            addCriterion("RECE_PRE_GOLD_BEE_NUM =", value, "recePreGoldBeeNum");
            return (Criteria) this;
        }

        public Criteria andRecePreGoldBeeNumNotEqualTo(BigDecimal value) {
            addCriterion("RECE_PRE_GOLD_BEE_NUM <>", value, "recePreGoldBeeNum");
            return (Criteria) this;
        }

        public Criteria andRecePreGoldBeeNumGreaterThan(BigDecimal value) {
            addCriterion("RECE_PRE_GOLD_BEE_NUM >", value, "recePreGoldBeeNum");
            return (Criteria) this;
        }

        public Criteria andRecePreGoldBeeNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("RECE_PRE_GOLD_BEE_NUM >=", value, "recePreGoldBeeNum");
            return (Criteria) this;
        }

        public Criteria andRecePreGoldBeeNumLessThan(BigDecimal value) {
            addCriterion("RECE_PRE_GOLD_BEE_NUM <", value, "recePreGoldBeeNum");
            return (Criteria) this;
        }

        public Criteria andRecePreGoldBeeNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("RECE_PRE_GOLD_BEE_NUM <=", value, "recePreGoldBeeNum");
            return (Criteria) this;
        }

        public Criteria andRecePreGoldBeeNumIn(List<BigDecimal> values) {
            addCriterion("RECE_PRE_GOLD_BEE_NUM in", values, "recePreGoldBeeNum");
            return (Criteria) this;
        }

        public Criteria andRecePreGoldBeeNumNotIn(List<BigDecimal> values) {
            addCriterion("RECE_PRE_GOLD_BEE_NUM not in", values, "recePreGoldBeeNum");
            return (Criteria) this;
        }

        public Criteria andRecePreGoldBeeNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("RECE_PRE_GOLD_BEE_NUM between", value1, value2, "recePreGoldBeeNum");
            return (Criteria) this;
        }

        public Criteria andRecePreGoldBeeNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("RECE_PRE_GOLD_BEE_NUM not between", value1, value2, "recePreGoldBeeNum");
            return (Criteria) this;
        }

        public Criteria andPreGoldBeeNumIsNull() {
            addCriterion("PRE_GOLD_BEE_NUM is null");
            return (Criteria) this;
        }

        public Criteria andPreGoldBeeNumIsNotNull() {
            addCriterion("PRE_GOLD_BEE_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andPreGoldBeeNumEqualTo(BigDecimal value) {
            addCriterion("PRE_GOLD_BEE_NUM =", value, "preGoldBeeNum");
            return (Criteria) this;
        }

        public Criteria andPreGoldBeeNumNotEqualTo(BigDecimal value) {
            addCriterion("PRE_GOLD_BEE_NUM <>", value, "preGoldBeeNum");
            return (Criteria) this;
        }

        public Criteria andPreGoldBeeNumGreaterThan(BigDecimal value) {
            addCriterion("PRE_GOLD_BEE_NUM >", value, "preGoldBeeNum");
            return (Criteria) this;
        }

        public Criteria andPreGoldBeeNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PRE_GOLD_BEE_NUM >=", value, "preGoldBeeNum");
            return (Criteria) this;
        }

        public Criteria andPreGoldBeeNumLessThan(BigDecimal value) {
            addCriterion("PRE_GOLD_BEE_NUM <", value, "preGoldBeeNum");
            return (Criteria) this;
        }

        public Criteria andPreGoldBeeNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PRE_GOLD_BEE_NUM <=", value, "preGoldBeeNum");
            return (Criteria) this;
        }

        public Criteria andPreGoldBeeNumIn(List<BigDecimal> values) {
            addCriterion("PRE_GOLD_BEE_NUM in", values, "preGoldBeeNum");
            return (Criteria) this;
        }

        public Criteria andPreGoldBeeNumNotIn(List<BigDecimal> values) {
            addCriterion("PRE_GOLD_BEE_NUM not in", values, "preGoldBeeNum");
            return (Criteria) this;
        }

        public Criteria andPreGoldBeeNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PRE_GOLD_BEE_NUM between", value1, value2, "preGoldBeeNum");
            return (Criteria) this;
        }

        public Criteria andPreGoldBeeNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PRE_GOLD_BEE_NUM not between", value1, value2, "preGoldBeeNum");
            return (Criteria) this;
        }

        public Criteria andEffNumIsNull() {
            addCriterion("EFF_NUM is null");
            return (Criteria) this;
        }

        public Criteria andEffNumIsNotNull() {
            addCriterion("EFF_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andEffNumEqualTo(BigDecimal value) {
            addCriterion("EFF_NUM =", value, "effNum");
            return (Criteria) this;
        }

        public Criteria andEffNumNotEqualTo(BigDecimal value) {
            addCriterion("EFF_NUM <>", value, "effNum");
            return (Criteria) this;
        }

        public Criteria andEffNumGreaterThan(BigDecimal value) {
            addCriterion("EFF_NUM >", value, "effNum");
            return (Criteria) this;
        }

        public Criteria andEffNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("EFF_NUM >=", value, "effNum");
            return (Criteria) this;
        }

        public Criteria andEffNumLessThan(BigDecimal value) {
            addCriterion("EFF_NUM <", value, "effNum");
            return (Criteria) this;
        }

        public Criteria andEffNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("EFF_NUM <=", value, "effNum");
            return (Criteria) this;
        }

        public Criteria andEffNumIn(List<BigDecimal> values) {
            addCriterion("EFF_NUM in", values, "effNum");
            return (Criteria) this;
        }

        public Criteria andEffNumNotIn(List<BigDecimal> values) {
            addCriterion("EFF_NUM not in", values, "effNum");
            return (Criteria) this;
        }

        public Criteria andEffNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("EFF_NUM between", value1, value2, "effNum");
            return (Criteria) this;
        }

        public Criteria andEffNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("EFF_NUM not between", value1, value2, "effNum");
            return (Criteria) this;
        }

        public Criteria andReceEffNumIsNull() {
            addCriterion("RECE_EFF_NUM is null");
            return (Criteria) this;
        }

        public Criteria andReceEffNumIsNotNull() {
            addCriterion("RECE_EFF_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andReceEffNumEqualTo(BigDecimal value) {
            addCriterion("RECE_EFF_NUM =", value, "receEffNum");
            return (Criteria) this;
        }

        public Criteria andReceEffNumNotEqualTo(BigDecimal value) {
            addCriterion("RECE_EFF_NUM <>", value, "receEffNum");
            return (Criteria) this;
        }

        public Criteria andReceEffNumGreaterThan(BigDecimal value) {
            addCriterion("RECE_EFF_NUM >", value, "receEffNum");
            return (Criteria) this;
        }

        public Criteria andReceEffNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("RECE_EFF_NUM >=", value, "receEffNum");
            return (Criteria) this;
        }

        public Criteria andReceEffNumLessThan(BigDecimal value) {
            addCriterion("RECE_EFF_NUM <", value, "receEffNum");
            return (Criteria) this;
        }

        public Criteria andReceEffNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("RECE_EFF_NUM <=", value, "receEffNum");
            return (Criteria) this;
        }

        public Criteria andReceEffNumIn(List<BigDecimal> values) {
            addCriterion("RECE_EFF_NUM in", values, "receEffNum");
            return (Criteria) this;
        }

        public Criteria andReceEffNumNotIn(List<BigDecimal> values) {
            addCriterion("RECE_EFF_NUM not in", values, "receEffNum");
            return (Criteria) this;
        }

        public Criteria andReceEffNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("RECE_EFF_NUM between", value1, value2, "receEffNum");
            return (Criteria) this;
        }

        public Criteria andReceEffNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("RECE_EFF_NUM not between", value1, value2, "receEffNum");
            return (Criteria) this;
        }

        public Criteria andNetGrowthNumIsNull() {
            addCriterion("NET_GROWTH_NUM is null");
            return (Criteria) this;
        }

        public Criteria andNetGrowthNumIsNotNull() {
            addCriterion("NET_GROWTH_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andNetGrowthNumEqualTo(BigDecimal value) {
            addCriterion("NET_GROWTH_NUM =", value, "netGrowthNum");
            return (Criteria) this;
        }

        public Criteria andNetGrowthNumNotEqualTo(BigDecimal value) {
            addCriterion("NET_GROWTH_NUM <>", value, "netGrowthNum");
            return (Criteria) this;
        }

        public Criteria andNetGrowthNumGreaterThan(BigDecimal value) {
            addCriterion("NET_GROWTH_NUM >", value, "netGrowthNum");
            return (Criteria) this;
        }

        public Criteria andNetGrowthNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("NET_GROWTH_NUM >=", value, "netGrowthNum");
            return (Criteria) this;
        }

        public Criteria andNetGrowthNumLessThan(BigDecimal value) {
            addCriterion("NET_GROWTH_NUM <", value, "netGrowthNum");
            return (Criteria) this;
        }

        public Criteria andNetGrowthNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("NET_GROWTH_NUM <=", value, "netGrowthNum");
            return (Criteria) this;
        }

        public Criteria andNetGrowthNumIn(List<BigDecimal> values) {
            addCriterion("NET_GROWTH_NUM in", values, "netGrowthNum");
            return (Criteria) this;
        }

        public Criteria andNetGrowthNumNotIn(List<BigDecimal> values) {
            addCriterion("NET_GROWTH_NUM not in", values, "netGrowthNum");
            return (Criteria) this;
        }

        public Criteria andNetGrowthNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("NET_GROWTH_NUM between", value1, value2, "netGrowthNum");
            return (Criteria) this;
        }

        public Criteria andNetGrowthNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("NET_GROWTH_NUM not between", value1, value2, "netGrowthNum");
            return (Criteria) this;
        }

        public Criteria andGradetypecodeIsNull() {
            addCriterion("GRADETYPECODE is null");
            return (Criteria) this;
        }

        public Criteria andGradetypecodeIsNotNull() {
            addCriterion("GRADETYPECODE is not null");
            return (Criteria) this;
        }

        public Criteria andGradetypecodeEqualTo(String value) {
            addCriterion("GRADETYPECODE =", value, "gradetypecode");
            return (Criteria) this;
        }

        public Criteria andGradetypecodeNotEqualTo(String value) {
            addCriterion("GRADETYPECODE <>", value, "gradetypecode");
            return (Criteria) this;
        }

        public Criteria andGradetypecodeGreaterThan(String value) {
            addCriterion("GRADETYPECODE >", value, "gradetypecode");
            return (Criteria) this;
        }

        public Criteria andGradetypecodeGreaterThanOrEqualTo(String value) {
            addCriterion("GRADETYPECODE >=", value, "gradetypecode");
            return (Criteria) this;
        }

        public Criteria andGradetypecodeLessThan(String value) {
            addCriterion("GRADETYPECODE <", value, "gradetypecode");
            return (Criteria) this;
        }

        public Criteria andGradetypecodeLessThanOrEqualTo(String value) {
            addCriterion("GRADETYPECODE <=", value, "gradetypecode");
            return (Criteria) this;
        }

        public Criteria andGradetypecodeLike(String value) {
            addCriterion("GRADETYPECODE like", value, "gradetypecode");
            return (Criteria) this;
        }

        public Criteria andGradetypecodeNotLike(String value) {
            addCriterion("GRADETYPECODE not like", value, "gradetypecode");
            return (Criteria) this;
        }

        public Criteria andGradetypecodeIn(List<String> values) {
            addCriterion("GRADETYPECODE in", values, "gradetypecode");
            return (Criteria) this;
        }

        public Criteria andGradetypecodeNotIn(List<String> values) {
            addCriterion("GRADETYPECODE not in", values, "gradetypecode");
            return (Criteria) this;
        }

        public Criteria andGradetypecodeBetween(String value1, String value2) {
            addCriterion("GRADETYPECODE between", value1, value2, "gradetypecode");
            return (Criteria) this;
        }

        public Criteria andGradetypecodeNotBetween(String value1, String value2) {
            addCriterion("GRADETYPECODE not between", value1, value2, "gradetypecode");
            return (Criteria) this;
        }

        public Criteria andMorningAttendanceIsNull() {
            addCriterion("MORNING_ATTENDANCE is null");
            return (Criteria) this;
        }

        public Criteria andMorningAttendanceIsNotNull() {
            addCriterion("MORNING_ATTENDANCE is not null");
            return (Criteria) this;
        }

        public Criteria andMorningAttendanceEqualTo(BigDecimal value) {
            addCriterion("MORNING_ATTENDANCE =", value, "morningAttendance");
            return (Criteria) this;
        }

        public Criteria andMorningAttendanceNotEqualTo(BigDecimal value) {
            addCriterion("MORNING_ATTENDANCE <>", value, "morningAttendance");
            return (Criteria) this;
        }

        public Criteria andMorningAttendanceGreaterThan(BigDecimal value) {
            addCriterion("MORNING_ATTENDANCE >", value, "morningAttendance");
            return (Criteria) this;
        }

        public Criteria andMorningAttendanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("MORNING_ATTENDANCE >=", value, "morningAttendance");
            return (Criteria) this;
        }

        public Criteria andMorningAttendanceLessThan(BigDecimal value) {
            addCriterion("MORNING_ATTENDANCE <", value, "morningAttendance");
            return (Criteria) this;
        }

        public Criteria andMorningAttendanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("MORNING_ATTENDANCE <=", value, "morningAttendance");
            return (Criteria) this;
        }

        public Criteria andMorningAttendanceIn(List<BigDecimal> values) {
            addCriterion("MORNING_ATTENDANCE in", values, "morningAttendance");
            return (Criteria) this;
        }

        public Criteria andMorningAttendanceNotIn(List<BigDecimal> values) {
            addCriterion("MORNING_ATTENDANCE not in", values, "morningAttendance");
            return (Criteria) this;
        }

        public Criteria andMorningAttendanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MORNING_ATTENDANCE between", value1, value2, "morningAttendance");
            return (Criteria) this;
        }

        public Criteria andMorningAttendanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MORNING_ATTENDANCE not between", value1, value2, "morningAttendance");
            return (Criteria) this;
        }

        public Criteria andAddManpowIsNull() {
            addCriterion("ADD_MANPOW is null");
            return (Criteria) this;
        }

        public Criteria andAddManpowIsNotNull() {
            addCriterion("ADD_MANPOW is not null");
            return (Criteria) this;
        }

        public Criteria andAddManpowEqualTo(BigDecimal value) {
            addCriterion("ADD_MANPOW =", value, "addManpow");
            return (Criteria) this;
        }

        public Criteria andAddManpowNotEqualTo(BigDecimal value) {
            addCriterion("ADD_MANPOW <>", value, "addManpow");
            return (Criteria) this;
        }

        public Criteria andAddManpowGreaterThan(BigDecimal value) {
            addCriterion("ADD_MANPOW >", value, "addManpow");
            return (Criteria) this;
        }

        public Criteria andAddManpowGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ADD_MANPOW >=", value, "addManpow");
            return (Criteria) this;
        }

        public Criteria andAddManpowLessThan(BigDecimal value) {
            addCriterion("ADD_MANPOW <", value, "addManpow");
            return (Criteria) this;
        }

        public Criteria andAddManpowLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ADD_MANPOW <=", value, "addManpow");
            return (Criteria) this;
        }

        public Criteria andAddManpowIn(List<BigDecimal> values) {
            addCriterion("ADD_MANPOW in", values, "addManpow");
            return (Criteria) this;
        }

        public Criteria andAddManpowNotIn(List<BigDecimal> values) {
            addCriterion("ADD_MANPOW not in", values, "addManpow");
            return (Criteria) this;
        }

        public Criteria andAddManpowBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ADD_MANPOW between", value1, value2, "addManpow");
            return (Criteria) this;
        }

        public Criteria andAddManpowNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ADD_MANPOW not between", value1, value2, "addManpow");
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