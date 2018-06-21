package com.huatai.web.model;

import java.util.ArrayList;
import java.util.List;

public class OrganizationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrganizationExample() {
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

        public Criteria andManagecomcodeIsNull() {
            addCriterion("MANAGECOMCODE is null");
            return (Criteria) this;
        }

        public Criteria andManagecomcodeIsNotNull() {
            addCriterion("MANAGECOMCODE is not null");
            return (Criteria) this;
        }

        public Criteria andManagecomcodeEqualTo(String value) {
            addCriterion("MANAGECOMCODE =", value, "managecomcode");
            return (Criteria) this;
        }

        public Criteria andManagecomcodeNotEqualTo(String value) {
            addCriterion("MANAGECOMCODE <>", value, "managecomcode");
            return (Criteria) this;
        }

        public Criteria andManagecomcodeGreaterThan(String value) {
            addCriterion("MANAGECOMCODE >", value, "managecomcode");
            return (Criteria) this;
        }

        public Criteria andManagecomcodeGreaterThanOrEqualTo(String value) {
            addCriterion("MANAGECOMCODE >=", value, "managecomcode");
            return (Criteria) this;
        }

        public Criteria andManagecomcodeLessThan(String value) {
            addCriterion("MANAGECOMCODE <", value, "managecomcode");
            return (Criteria) this;
        }

        public Criteria andManagecomcodeLessThanOrEqualTo(String value) {
            addCriterion("MANAGECOMCODE <=", value, "managecomcode");
            return (Criteria) this;
        }

        public Criteria andManagecomcodeLike(String value) {
            addCriterion("MANAGECOMCODE like", value, "managecomcode");
            return (Criteria) this;
        }

        public Criteria andManagecomcodeNotLike(String value) {
            addCriterion("MANAGECOMCODE not like", value, "managecomcode");
            return (Criteria) this;
        }

        public Criteria andManagecomcodeIn(List<String> values) {
            addCriterion("MANAGECOMCODE in", values, "managecomcode");
            return (Criteria) this;
        }

        public Criteria andManagecomcodeNotIn(List<String> values) {
            addCriterion("MANAGECOMCODE not in", values, "managecomcode");
            return (Criteria) this;
        }

        public Criteria andManagecomcodeBetween(String value1, String value2) {
            addCriterion("MANAGECOMCODE between", value1, value2, "managecomcode");
            return (Criteria) this;
        }

        public Criteria andManagecomcodeNotBetween(String value1, String value2) {
            addCriterion("MANAGECOMCODE not between", value1, value2, "managecomcode");
            return (Criteria) this;
        }

        public Criteria andManagecomnameIsNull() {
            addCriterion("MANAGECOMNAME is null");
            return (Criteria) this;
        }

        public Criteria andManagecomnameIsNotNull() {
            addCriterion("MANAGECOMNAME is not null");
            return (Criteria) this;
        }

        public Criteria andManagecomnameEqualTo(String value) {
            addCriterion("MANAGECOMNAME =", value, "managecomname");
            return (Criteria) this;
        }

        public Criteria andManagecomnameNotEqualTo(String value) {
            addCriterion("MANAGECOMNAME <>", value, "managecomname");
            return (Criteria) this;
        }

        public Criteria andManagecomnameGreaterThan(String value) {
            addCriterion("MANAGECOMNAME >", value, "managecomname");
            return (Criteria) this;
        }

        public Criteria andManagecomnameGreaterThanOrEqualTo(String value) {
            addCriterion("MANAGECOMNAME >=", value, "managecomname");
            return (Criteria) this;
        }

        public Criteria andManagecomnameLessThan(String value) {
            addCriterion("MANAGECOMNAME <", value, "managecomname");
            return (Criteria) this;
        }

        public Criteria andManagecomnameLessThanOrEqualTo(String value) {
            addCriterion("MANAGECOMNAME <=", value, "managecomname");
            return (Criteria) this;
        }

        public Criteria andManagecomnameLike(String value) {
            addCriterion("MANAGECOMNAME like", value, "managecomname");
            return (Criteria) this;
        }

        public Criteria andManagecomnameNotLike(String value) {
            addCriterion("MANAGECOMNAME not like", value, "managecomname");
            return (Criteria) this;
        }

        public Criteria andManagecomnameIn(List<String> values) {
            addCriterion("MANAGECOMNAME in", values, "managecomname");
            return (Criteria) this;
        }

        public Criteria andManagecomnameNotIn(List<String> values) {
            addCriterion("MANAGECOMNAME not in", values, "managecomname");
            return (Criteria) this;
        }

        public Criteria andManagecomnameBetween(String value1, String value2) {
            addCriterion("MANAGECOMNAME between", value1, value2, "managecomname");
            return (Criteria) this;
        }

        public Criteria andManagecomnameNotBetween(String value1, String value2) {
            addCriterion("MANAGECOMNAME not between", value1, value2, "managecomname");
            return (Criteria) this;
        }

        public Criteria andHeadcomcodeIsNull() {
            addCriterion("HEADCOMCODE is null");
            return (Criteria) this;
        }

        public Criteria andHeadcomcodeIsNotNull() {
            addCriterion("HEADCOMCODE is not null");
            return (Criteria) this;
        }

        public Criteria andHeadcomcodeEqualTo(String value) {
            addCriterion("HEADCOMCODE =", value, "headcomcode");
            return (Criteria) this;
        }

        public Criteria andHeadcomcodeNotEqualTo(String value) {
            addCriterion("HEADCOMCODE <>", value, "headcomcode");
            return (Criteria) this;
        }

        public Criteria andHeadcomcodeGreaterThan(String value) {
            addCriterion("HEADCOMCODE >", value, "headcomcode");
            return (Criteria) this;
        }

        public Criteria andHeadcomcodeGreaterThanOrEqualTo(String value) {
            addCriterion("HEADCOMCODE >=", value, "headcomcode");
            return (Criteria) this;
        }

        public Criteria andHeadcomcodeLessThan(String value) {
            addCriterion("HEADCOMCODE <", value, "headcomcode");
            return (Criteria) this;
        }

        public Criteria andHeadcomcodeLessThanOrEqualTo(String value) {
            addCriterion("HEADCOMCODE <=", value, "headcomcode");
            return (Criteria) this;
        }

        public Criteria andHeadcomcodeLike(String value) {
            addCriterion("HEADCOMCODE like", value, "headcomcode");
            return (Criteria) this;
        }

        public Criteria andHeadcomcodeNotLike(String value) {
            addCriterion("HEADCOMCODE not like", value, "headcomcode");
            return (Criteria) this;
        }

        public Criteria andHeadcomcodeIn(List<String> values) {
            addCriterion("HEADCOMCODE in", values, "headcomcode");
            return (Criteria) this;
        }

        public Criteria andHeadcomcodeNotIn(List<String> values) {
            addCriterion("HEADCOMCODE not in", values, "headcomcode");
            return (Criteria) this;
        }

        public Criteria andHeadcomcodeBetween(String value1, String value2) {
            addCriterion("HEADCOMCODE between", value1, value2, "headcomcode");
            return (Criteria) this;
        }

        public Criteria andHeadcomcodeNotBetween(String value1, String value2) {
            addCriterion("HEADCOMCODE not between", value1, value2, "headcomcode");
            return (Criteria) this;
        }

        public Criteria andHeadcomnameIsNull() {
            addCriterion("HEADCOMNAME is null");
            return (Criteria) this;
        }

        public Criteria andHeadcomnameIsNotNull() {
            addCriterion("HEADCOMNAME is not null");
            return (Criteria) this;
        }

        public Criteria andHeadcomnameEqualTo(String value) {
            addCriterion("HEADCOMNAME =", value, "headcomname");
            return (Criteria) this;
        }

        public Criteria andHeadcomnameNotEqualTo(String value) {
            addCriterion("HEADCOMNAME <>", value, "headcomname");
            return (Criteria) this;
        }

        public Criteria andHeadcomnameGreaterThan(String value) {
            addCriterion("HEADCOMNAME >", value, "headcomname");
            return (Criteria) this;
        }

        public Criteria andHeadcomnameGreaterThanOrEqualTo(String value) {
            addCriterion("HEADCOMNAME >=", value, "headcomname");
            return (Criteria) this;
        }

        public Criteria andHeadcomnameLessThan(String value) {
            addCriterion("HEADCOMNAME <", value, "headcomname");
            return (Criteria) this;
        }

        public Criteria andHeadcomnameLessThanOrEqualTo(String value) {
            addCriterion("HEADCOMNAME <=", value, "headcomname");
            return (Criteria) this;
        }

        public Criteria andHeadcomnameLike(String value) {
            addCriterion("HEADCOMNAME like", value, "headcomname");
            return (Criteria) this;
        }

        public Criteria andHeadcomnameNotLike(String value) {
            addCriterion("HEADCOMNAME not like", value, "headcomname");
            return (Criteria) this;
        }

        public Criteria andHeadcomnameIn(List<String> values) {
            addCriterion("HEADCOMNAME in", values, "headcomname");
            return (Criteria) this;
        }

        public Criteria andHeadcomnameNotIn(List<String> values) {
            addCriterion("HEADCOMNAME not in", values, "headcomname");
            return (Criteria) this;
        }

        public Criteria andHeadcomnameBetween(String value1, String value2) {
            addCriterion("HEADCOMNAME between", value1, value2, "headcomname");
            return (Criteria) this;
        }

        public Criteria andHeadcomnameNotBetween(String value1, String value2) {
            addCriterion("HEADCOMNAME not between", value1, value2, "headcomname");
            return (Criteria) this;
        }

        public Criteria andProvcomcodeIsNull() {
            addCriterion("PROVCOMCODE is null");
            return (Criteria) this;
        }

        public Criteria andProvcomcodeIsNotNull() {
            addCriterion("PROVCOMCODE is not null");
            return (Criteria) this;
        }

        public Criteria andProvcomcodeEqualTo(String value) {
            addCriterion("PROVCOMCODE =", value, "provcomcode");
            return (Criteria) this;
        }

        public Criteria andProvcomcodeNotEqualTo(String value) {
            addCriterion("PROVCOMCODE <>", value, "provcomcode");
            return (Criteria) this;
        }

        public Criteria andProvcomcodeGreaterThan(String value) {
            addCriterion("PROVCOMCODE >", value, "provcomcode");
            return (Criteria) this;
        }

        public Criteria andProvcomcodeGreaterThanOrEqualTo(String value) {
            addCriterion("PROVCOMCODE >=", value, "provcomcode");
            return (Criteria) this;
        }

        public Criteria andProvcomcodeLessThan(String value) {
            addCriterion("PROVCOMCODE <", value, "provcomcode");
            return (Criteria) this;
        }

        public Criteria andProvcomcodeLessThanOrEqualTo(String value) {
            addCriterion("PROVCOMCODE <=", value, "provcomcode");
            return (Criteria) this;
        }

        public Criteria andProvcomcodeLike(String value) {
            addCriterion("PROVCOMCODE like", value, "provcomcode");
            return (Criteria) this;
        }

        public Criteria andProvcomcodeNotLike(String value) {
            addCriterion("PROVCOMCODE not like", value, "provcomcode");
            return (Criteria) this;
        }

        public Criteria andProvcomcodeIn(List<String> values) {
            addCriterion("PROVCOMCODE in", values, "provcomcode");
            return (Criteria) this;
        }

        public Criteria andProvcomcodeNotIn(List<String> values) {
            addCriterion("PROVCOMCODE not in", values, "provcomcode");
            return (Criteria) this;
        }

        public Criteria andProvcomcodeBetween(String value1, String value2) {
            addCriterion("PROVCOMCODE between", value1, value2, "provcomcode");
            return (Criteria) this;
        }

        public Criteria andProvcomcodeNotBetween(String value1, String value2) {
            addCriterion("PROVCOMCODE not between", value1, value2, "provcomcode");
            return (Criteria) this;
        }

        public Criteria andProvcomnameIsNull() {
            addCriterion("PROVCOMNAME is null");
            return (Criteria) this;
        }

        public Criteria andProvcomnameIsNotNull() {
            addCriterion("PROVCOMNAME is not null");
            return (Criteria) this;
        }

        public Criteria andProvcomnameEqualTo(String value) {
            addCriterion("PROVCOMNAME =", value, "provcomname");
            return (Criteria) this;
        }

        public Criteria andProvcomnameNotEqualTo(String value) {
            addCriterion("PROVCOMNAME <>", value, "provcomname");
            return (Criteria) this;
        }

        public Criteria andProvcomnameGreaterThan(String value) {
            addCriterion("PROVCOMNAME >", value, "provcomname");
            return (Criteria) this;
        }

        public Criteria andProvcomnameGreaterThanOrEqualTo(String value) {
            addCriterion("PROVCOMNAME >=", value, "provcomname");
            return (Criteria) this;
        }

        public Criteria andProvcomnameLessThan(String value) {
            addCriterion("PROVCOMNAME <", value, "provcomname");
            return (Criteria) this;
        }

        public Criteria andProvcomnameLessThanOrEqualTo(String value) {
            addCriterion("PROVCOMNAME <=", value, "provcomname");
            return (Criteria) this;
        }

        public Criteria andProvcomnameLike(String value) {
            addCriterion("PROVCOMNAME like", value, "provcomname");
            return (Criteria) this;
        }

        public Criteria andProvcomnameNotLike(String value) {
            addCriterion("PROVCOMNAME not like", value, "provcomname");
            return (Criteria) this;
        }

        public Criteria andProvcomnameIn(List<String> values) {
            addCriterion("PROVCOMNAME in", values, "provcomname");
            return (Criteria) this;
        }

        public Criteria andProvcomnameNotIn(List<String> values) {
            addCriterion("PROVCOMNAME not in", values, "provcomname");
            return (Criteria) this;
        }

        public Criteria andProvcomnameBetween(String value1, String value2) {
            addCriterion("PROVCOMNAME between", value1, value2, "provcomname");
            return (Criteria) this;
        }

        public Criteria andProvcomnameNotBetween(String value1, String value2) {
            addCriterion("PROVCOMNAME not between", value1, value2, "provcomname");
            return (Criteria) this;
        }

        public Criteria andCountcomcodeIsNull() {
            addCriterion("COUNTCOMCODE is null");
            return (Criteria) this;
        }

        public Criteria andCountcomcodeIsNotNull() {
            addCriterion("COUNTCOMCODE is not null");
            return (Criteria) this;
        }

        public Criteria andCountcomcodeEqualTo(String value) {
            addCriterion("COUNTCOMCODE =", value, "countcomcode");
            return (Criteria) this;
        }

        public Criteria andCountcomcodeNotEqualTo(String value) {
            addCriterion("COUNTCOMCODE <>", value, "countcomcode");
            return (Criteria) this;
        }

        public Criteria andCountcomcodeGreaterThan(String value) {
            addCriterion("COUNTCOMCODE >", value, "countcomcode");
            return (Criteria) this;
        }

        public Criteria andCountcomcodeGreaterThanOrEqualTo(String value) {
            addCriterion("COUNTCOMCODE >=", value, "countcomcode");
            return (Criteria) this;
        }

        public Criteria andCountcomcodeLessThan(String value) {
            addCriterion("COUNTCOMCODE <", value, "countcomcode");
            return (Criteria) this;
        }

        public Criteria andCountcomcodeLessThanOrEqualTo(String value) {
            addCriterion("COUNTCOMCODE <=", value, "countcomcode");
            return (Criteria) this;
        }

        public Criteria andCountcomcodeLike(String value) {
            addCriterion("COUNTCOMCODE like", value, "countcomcode");
            return (Criteria) this;
        }

        public Criteria andCountcomcodeNotLike(String value) {
            addCriterion("COUNTCOMCODE not like", value, "countcomcode");
            return (Criteria) this;
        }

        public Criteria andCountcomcodeIn(List<String> values) {
            addCriterion("COUNTCOMCODE in", values, "countcomcode");
            return (Criteria) this;
        }

        public Criteria andCountcomcodeNotIn(List<String> values) {
            addCriterion("COUNTCOMCODE not in", values, "countcomcode");
            return (Criteria) this;
        }

        public Criteria andCountcomcodeBetween(String value1, String value2) {
            addCriterion("COUNTCOMCODE between", value1, value2, "countcomcode");
            return (Criteria) this;
        }

        public Criteria andCountcomcodeNotBetween(String value1, String value2) {
            addCriterion("COUNTCOMCODE not between", value1, value2, "countcomcode");
            return (Criteria) this;
        }

        public Criteria andCountcomnameIsNull() {
            addCriterion("COUNTCOMNAME is null");
            return (Criteria) this;
        }

        public Criteria andCountcomnameIsNotNull() {
            addCriterion("COUNTCOMNAME is not null");
            return (Criteria) this;
        }

        public Criteria andCountcomnameEqualTo(String value) {
            addCriterion("COUNTCOMNAME =", value, "countcomname");
            return (Criteria) this;
        }

        public Criteria andCountcomnameNotEqualTo(String value) {
            addCriterion("COUNTCOMNAME <>", value, "countcomname");
            return (Criteria) this;
        }

        public Criteria andCountcomnameGreaterThan(String value) {
            addCriterion("COUNTCOMNAME >", value, "countcomname");
            return (Criteria) this;
        }

        public Criteria andCountcomnameGreaterThanOrEqualTo(String value) {
            addCriterion("COUNTCOMNAME >=", value, "countcomname");
            return (Criteria) this;
        }

        public Criteria andCountcomnameLessThan(String value) {
            addCriterion("COUNTCOMNAME <", value, "countcomname");
            return (Criteria) this;
        }

        public Criteria andCountcomnameLessThanOrEqualTo(String value) {
            addCriterion("COUNTCOMNAME <=", value, "countcomname");
            return (Criteria) this;
        }

        public Criteria andCountcomnameLike(String value) {
            addCriterion("COUNTCOMNAME like", value, "countcomname");
            return (Criteria) this;
        }

        public Criteria andCountcomnameNotLike(String value) {
            addCriterion("COUNTCOMNAME not like", value, "countcomname");
            return (Criteria) this;
        }

        public Criteria andCountcomnameIn(List<String> values) {
            addCriterion("COUNTCOMNAME in", values, "countcomname");
            return (Criteria) this;
        }

        public Criteria andCountcomnameNotIn(List<String> values) {
            addCriterion("COUNTCOMNAME not in", values, "countcomname");
            return (Criteria) this;
        }

        public Criteria andCountcomnameBetween(String value1, String value2) {
            addCriterion("COUNTCOMNAME between", value1, value2, "countcomname");
            return (Criteria) this;
        }

        public Criteria andCountcomnameNotBetween(String value1, String value2) {
            addCriterion("COUNTCOMNAME not between", value1, value2, "countcomname");
            return (Criteria) this;
        }

        public Criteria andAgentgroupidIsNull() {
            addCriterion("AGENTGROUPID is null");
            return (Criteria) this;
        }

        public Criteria andAgentgroupidIsNotNull() {
            addCriterion("AGENTGROUPID is not null");
            return (Criteria) this;
        }

        public Criteria andAgentgroupidEqualTo(Integer value) {
            addCriterion("AGENTGROUPID =", value, "agentgroupid");
            return (Criteria) this;
        }

        public Criteria andAgentgroupidNotEqualTo(Integer value) {
            addCriterion("AGENTGROUPID <>", value, "agentgroupid");
            return (Criteria) this;
        }

        public Criteria andAgentgroupidGreaterThan(Integer value) {
            addCriterion("AGENTGROUPID >", value, "agentgroupid");
            return (Criteria) this;
        }

        public Criteria andAgentgroupidGreaterThanOrEqualTo(Integer value) {
            addCriterion("AGENTGROUPID >=", value, "agentgroupid");
            return (Criteria) this;
        }

        public Criteria andAgentgroupidLessThan(Integer value) {
            addCriterion("AGENTGROUPID <", value, "agentgroupid");
            return (Criteria) this;
        }

        public Criteria andAgentgroupidLessThanOrEqualTo(Integer value) {
            addCriterion("AGENTGROUPID <=", value, "agentgroupid");
            return (Criteria) this;
        }

        public Criteria andAgentgroupidIn(List<Integer> values) {
            addCriterion("AGENTGROUPID in", values, "agentgroupid");
            return (Criteria) this;
        }

        public Criteria andAgentgroupidNotIn(List<Integer> values) {
            addCriterion("AGENTGROUPID not in", values, "agentgroupid");
            return (Criteria) this;
        }

        public Criteria andAgentgroupidBetween(Integer value1, Integer value2) {
            addCriterion("AGENTGROUPID between", value1, value2, "agentgroupid");
            return (Criteria) this;
        }

        public Criteria andAgentgroupidNotBetween(Integer value1, Integer value2) {
            addCriterion("AGENTGROUPID not between", value1, value2, "agentgroupid");
            return (Criteria) this;
        }

        public Criteria andAgentgroupcodeIsNull() {
            addCriterion("AGENTGROUPCODE is null");
            return (Criteria) this;
        }

        public Criteria andAgentgroupcodeIsNotNull() {
            addCriterion("AGENTGROUPCODE is not null");
            return (Criteria) this;
        }

        public Criteria andAgentgroupcodeEqualTo(String value) {
            addCriterion("AGENTGROUPCODE =", value, "agentgroupcode");
            return (Criteria) this;
        }

        public Criteria andAgentgroupcodeNotEqualTo(String value) {
            addCriterion("AGENTGROUPCODE <>", value, "agentgroupcode");
            return (Criteria) this;
        }

        public Criteria andAgentgroupcodeGreaterThan(String value) {
            addCriterion("AGENTGROUPCODE >", value, "agentgroupcode");
            return (Criteria) this;
        }

        public Criteria andAgentgroupcodeGreaterThanOrEqualTo(String value) {
            addCriterion("AGENTGROUPCODE >=", value, "agentgroupcode");
            return (Criteria) this;
        }

        public Criteria andAgentgroupcodeLessThan(String value) {
            addCriterion("AGENTGROUPCODE <", value, "agentgroupcode");
            return (Criteria) this;
        }

        public Criteria andAgentgroupcodeLessThanOrEqualTo(String value) {
            addCriterion("AGENTGROUPCODE <=", value, "agentgroupcode");
            return (Criteria) this;
        }

        public Criteria andAgentgroupcodeLike(String value) {
            addCriterion("AGENTGROUPCODE like", value, "agentgroupcode");
            return (Criteria) this;
        }

        public Criteria andAgentgroupcodeNotLike(String value) {
            addCriterion("AGENTGROUPCODE not like", value, "agentgroupcode");
            return (Criteria) this;
        }

        public Criteria andAgentgroupcodeIn(List<String> values) {
            addCriterion("AGENTGROUPCODE in", values, "agentgroupcode");
            return (Criteria) this;
        }

        public Criteria andAgentgroupcodeNotIn(List<String> values) {
            addCriterion("AGENTGROUPCODE not in", values, "agentgroupcode");
            return (Criteria) this;
        }

        public Criteria andAgentgroupcodeBetween(String value1, String value2) {
            addCriterion("AGENTGROUPCODE between", value1, value2, "agentgroupcode");
            return (Criteria) this;
        }

        public Criteria andAgentgroupcodeNotBetween(String value1, String value2) {
            addCriterion("AGENTGROUPCODE not between", value1, value2, "agentgroupcode");
            return (Criteria) this;
        }

        public Criteria andAgentgroupnameIsNull() {
            addCriterion("AGENTGROUPNAME is null");
            return (Criteria) this;
        }

        public Criteria andAgentgroupnameIsNotNull() {
            addCriterion("AGENTGROUPNAME is not null");
            return (Criteria) this;
        }

        public Criteria andAgentgroupnameEqualTo(String value) {
            addCriterion("AGENTGROUPNAME =", value, "agentgroupname");
            return (Criteria) this;
        }

        public Criteria andAgentgroupnameNotEqualTo(String value) {
            addCriterion("AGENTGROUPNAME <>", value, "agentgroupname");
            return (Criteria) this;
        }

        public Criteria andAgentgroupnameGreaterThan(String value) {
            addCriterion("AGENTGROUPNAME >", value, "agentgroupname");
            return (Criteria) this;
        }

        public Criteria andAgentgroupnameGreaterThanOrEqualTo(String value) {
            addCriterion("AGENTGROUPNAME >=", value, "agentgroupname");
            return (Criteria) this;
        }

        public Criteria andAgentgroupnameLessThan(String value) {
            addCriterion("AGENTGROUPNAME <", value, "agentgroupname");
            return (Criteria) this;
        }

        public Criteria andAgentgroupnameLessThanOrEqualTo(String value) {
            addCriterion("AGENTGROUPNAME <=", value, "agentgroupname");
            return (Criteria) this;
        }

        public Criteria andAgentgroupnameLike(String value) {
            addCriterion("AGENTGROUPNAME like", value, "agentgroupname");
            return (Criteria) this;
        }

        public Criteria andAgentgroupnameNotLike(String value) {
            addCriterion("AGENTGROUPNAME not like", value, "agentgroupname");
            return (Criteria) this;
        }

        public Criteria andAgentgroupnameIn(List<String> values) {
            addCriterion("AGENTGROUPNAME in", values, "agentgroupname");
            return (Criteria) this;
        }

        public Criteria andAgentgroupnameNotIn(List<String> values) {
            addCriterion("AGENTGROUPNAME not in", values, "agentgroupname");
            return (Criteria) this;
        }

        public Criteria andAgentgroupnameBetween(String value1, String value2) {
            addCriterion("AGENTGROUPNAME between", value1, value2, "agentgroupname");
            return (Criteria) this;
        }

        public Criteria andAgentgroupnameNotBetween(String value1, String value2) {
            addCriterion("AGENTGROUPNAME not between", value1, value2, "agentgroupname");
            return (Criteria) this;
        }

        public Criteria andTeamcomcodeIsNull() {
            addCriterion("TEAMCOMCODE is null");
            return (Criteria) this;
        }

        public Criteria andTeamcomcodeIsNotNull() {
            addCriterion("TEAMCOMCODE is not null");
            return (Criteria) this;
        }

        public Criteria andTeamcomcodeEqualTo(String value) {
            addCriterion("TEAMCOMCODE =", value, "teamcomcode");
            return (Criteria) this;
        }

        public Criteria andTeamcomcodeNotEqualTo(String value) {
            addCriterion("TEAMCOMCODE <>", value, "teamcomcode");
            return (Criteria) this;
        }

        public Criteria andTeamcomcodeGreaterThan(String value) {
            addCriterion("TEAMCOMCODE >", value, "teamcomcode");
            return (Criteria) this;
        }

        public Criteria andTeamcomcodeGreaterThanOrEqualTo(String value) {
            addCriterion("TEAMCOMCODE >=", value, "teamcomcode");
            return (Criteria) this;
        }

        public Criteria andTeamcomcodeLessThan(String value) {
            addCriterion("TEAMCOMCODE <", value, "teamcomcode");
            return (Criteria) this;
        }

        public Criteria andTeamcomcodeLessThanOrEqualTo(String value) {
            addCriterion("TEAMCOMCODE <=", value, "teamcomcode");
            return (Criteria) this;
        }

        public Criteria andTeamcomcodeLike(String value) {
            addCriterion("TEAMCOMCODE like", value, "teamcomcode");
            return (Criteria) this;
        }

        public Criteria andTeamcomcodeNotLike(String value) {
            addCriterion("TEAMCOMCODE not like", value, "teamcomcode");
            return (Criteria) this;
        }

        public Criteria andTeamcomcodeIn(List<String> values) {
            addCriterion("TEAMCOMCODE in", values, "teamcomcode");
            return (Criteria) this;
        }

        public Criteria andTeamcomcodeNotIn(List<String> values) {
            addCriterion("TEAMCOMCODE not in", values, "teamcomcode");
            return (Criteria) this;
        }

        public Criteria andTeamcomcodeBetween(String value1, String value2) {
            addCriterion("TEAMCOMCODE between", value1, value2, "teamcomcode");
            return (Criteria) this;
        }

        public Criteria andTeamcomcodeNotBetween(String value1, String value2) {
            addCriterion("TEAMCOMCODE not between", value1, value2, "teamcomcode");
            return (Criteria) this;
        }

        public Criteria andHeadgroupcodeIsNull() {
            addCriterion("HEADGROUPCODE is null");
            return (Criteria) this;
        }

        public Criteria andHeadgroupcodeIsNotNull() {
            addCriterion("HEADGROUPCODE is not null");
            return (Criteria) this;
        }

        public Criteria andHeadgroupcodeEqualTo(String value) {
            addCriterion("HEADGROUPCODE =", value, "headgroupcode");
            return (Criteria) this;
        }

        public Criteria andHeadgroupcodeNotEqualTo(String value) {
            addCriterion("HEADGROUPCODE <>", value, "headgroupcode");
            return (Criteria) this;
        }

        public Criteria andHeadgroupcodeGreaterThan(String value) {
            addCriterion("HEADGROUPCODE >", value, "headgroupcode");
            return (Criteria) this;
        }

        public Criteria andHeadgroupcodeGreaterThanOrEqualTo(String value) {
            addCriterion("HEADGROUPCODE >=", value, "headgroupcode");
            return (Criteria) this;
        }

        public Criteria andHeadgroupcodeLessThan(String value) {
            addCriterion("HEADGROUPCODE <", value, "headgroupcode");
            return (Criteria) this;
        }

        public Criteria andHeadgroupcodeLessThanOrEqualTo(String value) {
            addCriterion("HEADGROUPCODE <=", value, "headgroupcode");
            return (Criteria) this;
        }

        public Criteria andHeadgroupcodeLike(String value) {
            addCriterion("HEADGROUPCODE like", value, "headgroupcode");
            return (Criteria) this;
        }

        public Criteria andHeadgroupcodeNotLike(String value) {
            addCriterion("HEADGROUPCODE not like", value, "headgroupcode");
            return (Criteria) this;
        }

        public Criteria andHeadgroupcodeIn(List<String> values) {
            addCriterion("HEADGROUPCODE in", values, "headgroupcode");
            return (Criteria) this;
        }

        public Criteria andHeadgroupcodeNotIn(List<String> values) {
            addCriterion("HEADGROUPCODE not in", values, "headgroupcode");
            return (Criteria) this;
        }

        public Criteria andHeadgroupcodeBetween(String value1, String value2) {
            addCriterion("HEADGROUPCODE between", value1, value2, "headgroupcode");
            return (Criteria) this;
        }

        public Criteria andHeadgroupcodeNotBetween(String value1, String value2) {
            addCriterion("HEADGROUPCODE not between", value1, value2, "headgroupcode");
            return (Criteria) this;
        }

        public Criteria andTeamcomnameIsNull() {
            addCriterion("TEAMCOMNAME is null");
            return (Criteria) this;
        }

        public Criteria andTeamcomnameIsNotNull() {
            addCriterion("TEAMCOMNAME is not null");
            return (Criteria) this;
        }

        public Criteria andTeamcomnameEqualTo(String value) {
            addCriterion("TEAMCOMNAME =", value, "teamcomname");
            return (Criteria) this;
        }

        public Criteria andTeamcomnameNotEqualTo(String value) {
            addCriterion("TEAMCOMNAME <>", value, "teamcomname");
            return (Criteria) this;
        }

        public Criteria andTeamcomnameGreaterThan(String value) {
            addCriterion("TEAMCOMNAME >", value, "teamcomname");
            return (Criteria) this;
        }

        public Criteria andTeamcomnameGreaterThanOrEqualTo(String value) {
            addCriterion("TEAMCOMNAME >=", value, "teamcomname");
            return (Criteria) this;
        }

        public Criteria andTeamcomnameLessThan(String value) {
            addCriterion("TEAMCOMNAME <", value, "teamcomname");
            return (Criteria) this;
        }

        public Criteria andTeamcomnameLessThanOrEqualTo(String value) {
            addCriterion("TEAMCOMNAME <=", value, "teamcomname");
            return (Criteria) this;
        }

        public Criteria andTeamcomnameLike(String value) {
            addCriterion("TEAMCOMNAME like", value, "teamcomname");
            return (Criteria) this;
        }

        public Criteria andTeamcomnameNotLike(String value) {
            addCriterion("TEAMCOMNAME not like", value, "teamcomname");
            return (Criteria) this;
        }

        public Criteria andTeamcomnameIn(List<String> values) {
            addCriterion("TEAMCOMNAME in", values, "teamcomname");
            return (Criteria) this;
        }

        public Criteria andTeamcomnameNotIn(List<String> values) {
            addCriterion("TEAMCOMNAME not in", values, "teamcomname");
            return (Criteria) this;
        }

        public Criteria andTeamcomnameBetween(String value1, String value2) {
            addCriterion("TEAMCOMNAME between", value1, value2, "teamcomname");
            return (Criteria) this;
        }

        public Criteria andTeamcomnameNotBetween(String value1, String value2) {
            addCriterion("TEAMCOMNAME not between", value1, value2, "teamcomname");
            return (Criteria) this;
        }

        public Criteria andHeadgroupnameIsNull() {
            addCriterion("HEADGROUPNAME is null");
            return (Criteria) this;
        }

        public Criteria andHeadgroupnameIsNotNull() {
            addCriterion("HEADGROUPNAME is not null");
            return (Criteria) this;
        }

        public Criteria andHeadgroupnameEqualTo(String value) {
            addCriterion("HEADGROUPNAME =", value, "headgroupname");
            return (Criteria) this;
        }

        public Criteria andHeadgroupnameNotEqualTo(String value) {
            addCriterion("HEADGROUPNAME <>", value, "headgroupname");
            return (Criteria) this;
        }

        public Criteria andHeadgroupnameGreaterThan(String value) {
            addCriterion("HEADGROUPNAME >", value, "headgroupname");
            return (Criteria) this;
        }

        public Criteria andHeadgroupnameGreaterThanOrEqualTo(String value) {
            addCriterion("HEADGROUPNAME >=", value, "headgroupname");
            return (Criteria) this;
        }

        public Criteria andHeadgroupnameLessThan(String value) {
            addCriterion("HEADGROUPNAME <", value, "headgroupname");
            return (Criteria) this;
        }

        public Criteria andHeadgroupnameLessThanOrEqualTo(String value) {
            addCriterion("HEADGROUPNAME <=", value, "headgroupname");
            return (Criteria) this;
        }

        public Criteria andHeadgroupnameLike(String value) {
            addCriterion("HEADGROUPNAME like", value, "headgroupname");
            return (Criteria) this;
        }

        public Criteria andHeadgroupnameNotLike(String value) {
            addCriterion("HEADGROUPNAME not like", value, "headgroupname");
            return (Criteria) this;
        }

        public Criteria andHeadgroupnameIn(List<String> values) {
            addCriterion("HEADGROUPNAME in", values, "headgroupname");
            return (Criteria) this;
        }

        public Criteria andHeadgroupnameNotIn(List<String> values) {
            addCriterion("HEADGROUPNAME not in", values, "headgroupname");
            return (Criteria) this;
        }

        public Criteria andHeadgroupnameBetween(String value1, String value2) {
            addCriterion("HEADGROUPNAME between", value1, value2, "headgroupname");
            return (Criteria) this;
        }

        public Criteria andHeadgroupnameNotBetween(String value1, String value2) {
            addCriterion("HEADGROUPNAME not between", value1, value2, "headgroupname");
            return (Criteria) this;
        }

        public Criteria andProvgroupcodeIsNull() {
            addCriterion("PROVGROUPCODE is null");
            return (Criteria) this;
        }

        public Criteria andProvgroupcodeIsNotNull() {
            addCriterion("PROVGROUPCODE is not null");
            return (Criteria) this;
        }

        public Criteria andProvgroupcodeEqualTo(String value) {
            addCriterion("PROVGROUPCODE =", value, "provgroupcode");
            return (Criteria) this;
        }

        public Criteria andProvgroupcodeNotEqualTo(String value) {
            addCriterion("PROVGROUPCODE <>", value, "provgroupcode");
            return (Criteria) this;
        }

        public Criteria andProvgroupcodeGreaterThan(String value) {
            addCriterion("PROVGROUPCODE >", value, "provgroupcode");
            return (Criteria) this;
        }

        public Criteria andProvgroupcodeGreaterThanOrEqualTo(String value) {
            addCriterion("PROVGROUPCODE >=", value, "provgroupcode");
            return (Criteria) this;
        }

        public Criteria andProvgroupcodeLessThan(String value) {
            addCriterion("PROVGROUPCODE <", value, "provgroupcode");
            return (Criteria) this;
        }

        public Criteria andProvgroupcodeLessThanOrEqualTo(String value) {
            addCriterion("PROVGROUPCODE <=", value, "provgroupcode");
            return (Criteria) this;
        }

        public Criteria andProvgroupcodeLike(String value) {
            addCriterion("PROVGROUPCODE like", value, "provgroupcode");
            return (Criteria) this;
        }

        public Criteria andProvgroupcodeNotLike(String value) {
            addCriterion("PROVGROUPCODE not like", value, "provgroupcode");
            return (Criteria) this;
        }

        public Criteria andProvgroupcodeIn(List<String> values) {
            addCriterion("PROVGROUPCODE in", values, "provgroupcode");
            return (Criteria) this;
        }

        public Criteria andProvgroupcodeNotIn(List<String> values) {
            addCriterion("PROVGROUPCODE not in", values, "provgroupcode");
            return (Criteria) this;
        }

        public Criteria andProvgroupcodeBetween(String value1, String value2) {
            addCriterion("PROVGROUPCODE between", value1, value2, "provgroupcode");
            return (Criteria) this;
        }

        public Criteria andProvgroupcodeNotBetween(String value1, String value2) {
            addCriterion("PROVGROUPCODE not between", value1, value2, "provgroupcode");
            return (Criteria) this;
        }

        public Criteria andProvgroupnameIsNull() {
            addCriterion("PROVGROUPNAME is null");
            return (Criteria) this;
        }

        public Criteria andProvgroupnameIsNotNull() {
            addCriterion("PROVGROUPNAME is not null");
            return (Criteria) this;
        }

        public Criteria andProvgroupnameEqualTo(String value) {
            addCriterion("PROVGROUPNAME =", value, "provgroupname");
            return (Criteria) this;
        }

        public Criteria andProvgroupnameNotEqualTo(String value) {
            addCriterion("PROVGROUPNAME <>", value, "provgroupname");
            return (Criteria) this;
        }

        public Criteria andProvgroupnameGreaterThan(String value) {
            addCriterion("PROVGROUPNAME >", value, "provgroupname");
            return (Criteria) this;
        }

        public Criteria andProvgroupnameGreaterThanOrEqualTo(String value) {
            addCriterion("PROVGROUPNAME >=", value, "provgroupname");
            return (Criteria) this;
        }

        public Criteria andProvgroupnameLessThan(String value) {
            addCriterion("PROVGROUPNAME <", value, "provgroupname");
            return (Criteria) this;
        }

        public Criteria andProvgroupnameLessThanOrEqualTo(String value) {
            addCriterion("PROVGROUPNAME <=", value, "provgroupname");
            return (Criteria) this;
        }

        public Criteria andProvgroupnameLike(String value) {
            addCriterion("PROVGROUPNAME like", value, "provgroupname");
            return (Criteria) this;
        }

        public Criteria andProvgroupnameNotLike(String value) {
            addCriterion("PROVGROUPNAME not like", value, "provgroupname");
            return (Criteria) this;
        }

        public Criteria andProvgroupnameIn(List<String> values) {
            addCriterion("PROVGROUPNAME in", values, "provgroupname");
            return (Criteria) this;
        }

        public Criteria andProvgroupnameNotIn(List<String> values) {
            addCriterion("PROVGROUPNAME not in", values, "provgroupname");
            return (Criteria) this;
        }

        public Criteria andProvgroupnameBetween(String value1, String value2) {
            addCriterion("PROVGROUPNAME between", value1, value2, "provgroupname");
            return (Criteria) this;
        }

        public Criteria andProvgroupnameNotBetween(String value1, String value2) {
            addCriterion("PROVGROUPNAME not between", value1, value2, "provgroupname");
            return (Criteria) this;
        }

        public Criteria andCountgroupcodeIsNull() {
            addCriterion("COUNTGROUPCODE is null");
            return (Criteria) this;
        }

        public Criteria andCountgroupcodeIsNotNull() {
            addCriterion("COUNTGROUPCODE is not null");
            return (Criteria) this;
        }

        public Criteria andCountgroupcodeEqualTo(String value) {
            addCriterion("COUNTGROUPCODE =", value, "countgroupcode");
            return (Criteria) this;
        }

        public Criteria andCountgroupcodeNotEqualTo(String value) {
            addCriterion("COUNTGROUPCODE <>", value, "countgroupcode");
            return (Criteria) this;
        }

        public Criteria andCountgroupcodeGreaterThan(String value) {
            addCriterion("COUNTGROUPCODE >", value, "countgroupcode");
            return (Criteria) this;
        }

        public Criteria andCountgroupcodeGreaterThanOrEqualTo(String value) {
            addCriterion("COUNTGROUPCODE >=", value, "countgroupcode");
            return (Criteria) this;
        }

        public Criteria andCountgroupcodeLessThan(String value) {
            addCriterion("COUNTGROUPCODE <", value, "countgroupcode");
            return (Criteria) this;
        }

        public Criteria andCountgroupcodeLessThanOrEqualTo(String value) {
            addCriterion("COUNTGROUPCODE <=", value, "countgroupcode");
            return (Criteria) this;
        }

        public Criteria andCountgroupcodeLike(String value) {
            addCriterion("COUNTGROUPCODE like", value, "countgroupcode");
            return (Criteria) this;
        }

        public Criteria andCountgroupcodeNotLike(String value) {
            addCriterion("COUNTGROUPCODE not like", value, "countgroupcode");
            return (Criteria) this;
        }

        public Criteria andCountgroupcodeIn(List<String> values) {
            addCriterion("COUNTGROUPCODE in", values, "countgroupcode");
            return (Criteria) this;
        }

        public Criteria andCountgroupcodeNotIn(List<String> values) {
            addCriterion("COUNTGROUPCODE not in", values, "countgroupcode");
            return (Criteria) this;
        }

        public Criteria andCountgroupcodeBetween(String value1, String value2) {
            addCriterion("COUNTGROUPCODE between", value1, value2, "countgroupcode");
            return (Criteria) this;
        }

        public Criteria andCountgroupcodeNotBetween(String value1, String value2) {
            addCriterion("COUNTGROUPCODE not between", value1, value2, "countgroupcode");
            return (Criteria) this;
        }

        public Criteria andCountgroupnameIsNull() {
            addCriterion("COUNTGROUPNAME is null");
            return (Criteria) this;
        }

        public Criteria andCountgroupnameIsNotNull() {
            addCriterion("COUNTGROUPNAME is not null");
            return (Criteria) this;
        }

        public Criteria andCountgroupnameEqualTo(String value) {
            addCriterion("COUNTGROUPNAME =", value, "countgroupname");
            return (Criteria) this;
        }

        public Criteria andCountgroupnameNotEqualTo(String value) {
            addCriterion("COUNTGROUPNAME <>", value, "countgroupname");
            return (Criteria) this;
        }

        public Criteria andCountgroupnameGreaterThan(String value) {
            addCriterion("COUNTGROUPNAME >", value, "countgroupname");
            return (Criteria) this;
        }

        public Criteria andCountgroupnameGreaterThanOrEqualTo(String value) {
            addCriterion("COUNTGROUPNAME >=", value, "countgroupname");
            return (Criteria) this;
        }

        public Criteria andCountgroupnameLessThan(String value) {
            addCriterion("COUNTGROUPNAME <", value, "countgroupname");
            return (Criteria) this;
        }

        public Criteria andCountgroupnameLessThanOrEqualTo(String value) {
            addCriterion("COUNTGROUPNAME <=", value, "countgroupname");
            return (Criteria) this;
        }

        public Criteria andCountgroupnameLike(String value) {
            addCriterion("COUNTGROUPNAME like", value, "countgroupname");
            return (Criteria) this;
        }

        public Criteria andCountgroupnameNotLike(String value) {
            addCriterion("COUNTGROUPNAME not like", value, "countgroupname");
            return (Criteria) this;
        }

        public Criteria andCountgroupnameIn(List<String> values) {
            addCriterion("COUNTGROUPNAME in", values, "countgroupname");
            return (Criteria) this;
        }

        public Criteria andCountgroupnameNotIn(List<String> values) {
            addCriterion("COUNTGROUPNAME not in", values, "countgroupname");
            return (Criteria) this;
        }

        public Criteria andCountgroupnameBetween(String value1, String value2) {
            addCriterion("COUNTGROUPNAME between", value1, value2, "countgroupname");
            return (Criteria) this;
        }

        public Criteria andCountgroupnameNotBetween(String value1, String value2) {
            addCriterion("COUNTGROUPNAME not between", value1, value2, "countgroupname");
            return (Criteria) this;
        }

        public Criteria andCostCenterIsNull() {
            addCriterion("COST_CENTER is null");
            return (Criteria) this;
        }

        public Criteria andCostCenterIsNotNull() {
            addCriterion("COST_CENTER is not null");
            return (Criteria) this;
        }

        public Criteria andCostCenterEqualTo(String value) {
            addCriterion("COST_CENTER =", value, "costCenter");
            return (Criteria) this;
        }

        public Criteria andCostCenterNotEqualTo(String value) {
            addCriterion("COST_CENTER <>", value, "costCenter");
            return (Criteria) this;
        }

        public Criteria andCostCenterGreaterThan(String value) {
            addCriterion("COST_CENTER >", value, "costCenter");
            return (Criteria) this;
        }

        public Criteria andCostCenterGreaterThanOrEqualTo(String value) {
            addCriterion("COST_CENTER >=", value, "costCenter");
            return (Criteria) this;
        }

        public Criteria andCostCenterLessThan(String value) {
            addCriterion("COST_CENTER <", value, "costCenter");
            return (Criteria) this;
        }

        public Criteria andCostCenterLessThanOrEqualTo(String value) {
            addCriterion("COST_CENTER <=", value, "costCenter");
            return (Criteria) this;
        }

        public Criteria andCostCenterLike(String value) {
            addCriterion("COST_CENTER like", value, "costCenter");
            return (Criteria) this;
        }

        public Criteria andCostCenterNotLike(String value) {
            addCriterion("COST_CENTER not like", value, "costCenter");
            return (Criteria) this;
        }

        public Criteria andCostCenterIn(List<String> values) {
            addCriterion("COST_CENTER in", values, "costCenter");
            return (Criteria) this;
        }

        public Criteria andCostCenterNotIn(List<String> values) {
            addCriterion("COST_CENTER not in", values, "costCenter");
            return (Criteria) this;
        }

        public Criteria andCostCenterBetween(String value1, String value2) {
            addCriterion("COST_CENTER between", value1, value2, "costCenter");
            return (Criteria) this;
        }

        public Criteria andCostCenterNotBetween(String value1, String value2) {
            addCriterion("COST_CENTER not between", value1, value2, "costCenter");
            return (Criteria) this;
        }

        public Criteria andCostCenterstdcodeIsNull() {
            addCriterion("COST_CENTERSTDCODE is null");
            return (Criteria) this;
        }

        public Criteria andCostCenterstdcodeIsNotNull() {
            addCriterion("COST_CENTERSTDCODE is not null");
            return (Criteria) this;
        }

        public Criteria andCostCenterstdcodeEqualTo(String value) {
            addCriterion("COST_CENTERSTDCODE =", value, "costCenterstdcode");
            return (Criteria) this;
        }

        public Criteria andCostCenterstdcodeNotEqualTo(String value) {
            addCriterion("COST_CENTERSTDCODE <>", value, "costCenterstdcode");
            return (Criteria) this;
        }

        public Criteria andCostCenterstdcodeGreaterThan(String value) {
            addCriterion("COST_CENTERSTDCODE >", value, "costCenterstdcode");
            return (Criteria) this;
        }

        public Criteria andCostCenterstdcodeGreaterThanOrEqualTo(String value) {
            addCriterion("COST_CENTERSTDCODE >=", value, "costCenterstdcode");
            return (Criteria) this;
        }

        public Criteria andCostCenterstdcodeLessThan(String value) {
            addCriterion("COST_CENTERSTDCODE <", value, "costCenterstdcode");
            return (Criteria) this;
        }

        public Criteria andCostCenterstdcodeLessThanOrEqualTo(String value) {
            addCriterion("COST_CENTERSTDCODE <=", value, "costCenterstdcode");
            return (Criteria) this;
        }

        public Criteria andCostCenterstdcodeLike(String value) {
            addCriterion("COST_CENTERSTDCODE like", value, "costCenterstdcode");
            return (Criteria) this;
        }

        public Criteria andCostCenterstdcodeNotLike(String value) {
            addCriterion("COST_CENTERSTDCODE not like", value, "costCenterstdcode");
            return (Criteria) this;
        }

        public Criteria andCostCenterstdcodeIn(List<String> values) {
            addCriterion("COST_CENTERSTDCODE in", values, "costCenterstdcode");
            return (Criteria) this;
        }

        public Criteria andCostCenterstdcodeNotIn(List<String> values) {
            addCriterion("COST_CENTERSTDCODE not in", values, "costCenterstdcode");
            return (Criteria) this;
        }

        public Criteria andCostCenterstdcodeBetween(String value1, String value2) {
            addCriterion("COST_CENTERSTDCODE between", value1, value2, "costCenterstdcode");
            return (Criteria) this;
        }

        public Criteria andCostCenterstdcodeNotBetween(String value1, String value2) {
            addCriterion("COST_CENTERSTDCODE not between", value1, value2, "costCenterstdcode");
            return (Criteria) this;
        }

        public Criteria andCostCenterdevnameIsNull() {
            addCriterion("COST_CENTERDEVNAME is null");
            return (Criteria) this;
        }

        public Criteria andCostCenterdevnameIsNotNull() {
            addCriterion("COST_CENTERDEVNAME is not null");
            return (Criteria) this;
        }

        public Criteria andCostCenterdevnameEqualTo(String value) {
            addCriterion("COST_CENTERDEVNAME =", value, "costCenterdevname");
            return (Criteria) this;
        }

        public Criteria andCostCenterdevnameNotEqualTo(String value) {
            addCriterion("COST_CENTERDEVNAME <>", value, "costCenterdevname");
            return (Criteria) this;
        }

        public Criteria andCostCenterdevnameGreaterThan(String value) {
            addCriterion("COST_CENTERDEVNAME >", value, "costCenterdevname");
            return (Criteria) this;
        }

        public Criteria andCostCenterdevnameGreaterThanOrEqualTo(String value) {
            addCriterion("COST_CENTERDEVNAME >=", value, "costCenterdevname");
            return (Criteria) this;
        }

        public Criteria andCostCenterdevnameLessThan(String value) {
            addCriterion("COST_CENTERDEVNAME <", value, "costCenterdevname");
            return (Criteria) this;
        }

        public Criteria andCostCenterdevnameLessThanOrEqualTo(String value) {
            addCriterion("COST_CENTERDEVNAME <=", value, "costCenterdevname");
            return (Criteria) this;
        }

        public Criteria andCostCenterdevnameLike(String value) {
            addCriterion("COST_CENTERDEVNAME like", value, "costCenterdevname");
            return (Criteria) this;
        }

        public Criteria andCostCenterdevnameNotLike(String value) {
            addCriterion("COST_CENTERDEVNAME not like", value, "costCenterdevname");
            return (Criteria) this;
        }

        public Criteria andCostCenterdevnameIn(List<String> values) {
            addCriterion("COST_CENTERDEVNAME in", values, "costCenterdevname");
            return (Criteria) this;
        }

        public Criteria andCostCenterdevnameNotIn(List<String> values) {
            addCriterion("COST_CENTERDEVNAME not in", values, "costCenterdevname");
            return (Criteria) this;
        }

        public Criteria andCostCenterdevnameBetween(String value1, String value2) {
            addCriterion("COST_CENTERDEVNAME between", value1, value2, "costCenterdevname");
            return (Criteria) this;
        }

        public Criteria andCostCenterdevnameNotBetween(String value1, String value2) {
            addCriterion("COST_CENTERDEVNAME not between", value1, value2, "costCenterdevname");
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