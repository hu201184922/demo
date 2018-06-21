package com.huatai.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserSetWarnExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserSetWarnExample() {
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

        public Criteria andBsIdIsNull() {
            addCriterion("BS_ID is null");
            return (Criteria) this;
        }

        public Criteria andBsIdIsNotNull() {
            addCriterion("BS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBsIdEqualTo(Integer value) {
            addCriterion("BS_ID =", value, "bsId");
            return (Criteria) this;
        }

        public Criteria andBsIdNotEqualTo(Integer value) {
            addCriterion("BS_ID <>", value, "bsId");
            return (Criteria) this;
        }

        public Criteria andBsIdGreaterThan(Integer value) {
            addCriterion("BS_ID >", value, "bsId");
            return (Criteria) this;
        }

        public Criteria andBsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("BS_ID >=", value, "bsId");
            return (Criteria) this;
        }

        public Criteria andBsIdLessThan(Integer value) {
            addCriterion("BS_ID <", value, "bsId");
            return (Criteria) this;
        }

        public Criteria andBsIdLessThanOrEqualTo(Integer value) {
            addCriterion("BS_ID <=", value, "bsId");
            return (Criteria) this;
        }

        public Criteria andBsIdIn(List<Integer> values) {
            addCriterion("BS_ID in", values, "bsId");
            return (Criteria) this;
        }

        public Criteria andBsIdNotIn(List<Integer> values) {
            addCriterion("BS_ID not in", values, "bsId");
            return (Criteria) this;
        }

        public Criteria andBsIdBetween(Integer value1, Integer value2) {
            addCriterion("BS_ID between", value1, value2, "bsId");
            return (Criteria) this;
        }

        public Criteria andBsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("BS_ID not between", value1, value2, "bsId");
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

        public Criteria andWarnSettingTypeIsNull() {
            addCriterion("WARN_SETTING_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andWarnSettingTypeIsNotNull() {
            addCriterion("WARN_SETTING_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andWarnSettingTypeEqualTo(String value) {
            addCriterion("WARN_SETTING_TYPE =", value, "warnSettingType");
            return (Criteria) this;
        }

        public Criteria andWarnSettingTypeNotEqualTo(String value) {
            addCriterion("WARN_SETTING_TYPE <>", value, "warnSettingType");
            return (Criteria) this;
        }

        public Criteria andWarnSettingTypeGreaterThan(String value) {
            addCriterion("WARN_SETTING_TYPE >", value, "warnSettingType");
            return (Criteria) this;
        }

        public Criteria andWarnSettingTypeGreaterThanOrEqualTo(String value) {
            addCriterion("WARN_SETTING_TYPE >=", value, "warnSettingType");
            return (Criteria) this;
        }

        public Criteria andWarnSettingTypeLessThan(String value) {
            addCriterion("WARN_SETTING_TYPE <", value, "warnSettingType");
            return (Criteria) this;
        }

        public Criteria andWarnSettingTypeLessThanOrEqualTo(String value) {
            addCriterion("WARN_SETTING_TYPE <=", value, "warnSettingType");
            return (Criteria) this;
        }

        public Criteria andWarnSettingTypeLike(String value) {
            addCriterion("WARN_SETTING_TYPE like", value, "warnSettingType");
            return (Criteria) this;
        }

        public Criteria andWarnSettingTypeNotLike(String value) {
            addCriterion("WARN_SETTING_TYPE not like", value, "warnSettingType");
            return (Criteria) this;
        }

        public Criteria andWarnSettingTypeIn(List<String> values) {
            addCriterion("WARN_SETTING_TYPE in", values, "warnSettingType");
            return (Criteria) this;
        }

        public Criteria andWarnSettingTypeNotIn(List<String> values) {
            addCriterion("WARN_SETTING_TYPE not in", values, "warnSettingType");
            return (Criteria) this;
        }

        public Criteria andWarnSettingTypeBetween(String value1, String value2) {
            addCriterion("WARN_SETTING_TYPE between", value1, value2, "warnSettingType");
            return (Criteria) this;
        }

        public Criteria andWarnSettingTypeNotBetween(String value1, String value2) {
            addCriterion("WARN_SETTING_TYPE not between", value1, value2, "warnSettingType");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("USERNAME is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("USERNAME is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("USERNAME =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("USERNAME <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("USERNAME >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("USERNAME >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("USERNAME <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("USERNAME <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("USERNAME like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("USERNAME not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("USERNAME in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("USERNAME not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("USERNAME between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("USERNAME not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andRoleCodeIsNull() {
            addCriterion("ROLE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andRoleCodeIsNotNull() {
            addCriterion("ROLE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andRoleCodeEqualTo(String value) {
            addCriterion("ROLE_CODE =", value, "roleCode");
            return (Criteria) this;
        }

        public Criteria andRoleCodeNotEqualTo(String value) {
            addCriterion("ROLE_CODE <>", value, "roleCode");
            return (Criteria) this;
        }

        public Criteria andRoleCodeGreaterThan(String value) {
            addCriterion("ROLE_CODE >", value, "roleCode");
            return (Criteria) this;
        }

        public Criteria andRoleCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ROLE_CODE >=", value, "roleCode");
            return (Criteria) this;
        }

        public Criteria andRoleCodeLessThan(String value) {
            addCriterion("ROLE_CODE <", value, "roleCode");
            return (Criteria) this;
        }

        public Criteria andRoleCodeLessThanOrEqualTo(String value) {
            addCriterion("ROLE_CODE <=", value, "roleCode");
            return (Criteria) this;
        }

        public Criteria andRoleCodeLike(String value) {
            addCriterion("ROLE_CODE like", value, "roleCode");
            return (Criteria) this;
        }

        public Criteria andRoleCodeNotLike(String value) {
            addCriterion("ROLE_CODE not like", value, "roleCode");
            return (Criteria) this;
        }

        public Criteria andRoleCodeIn(List<String> values) {
            addCriterion("ROLE_CODE in", values, "roleCode");
            return (Criteria) this;
        }

        public Criteria andRoleCodeNotIn(List<String> values) {
            addCriterion("ROLE_CODE not in", values, "roleCode");
            return (Criteria) this;
        }

        public Criteria andRoleCodeBetween(String value1, String value2) {
            addCriterion("ROLE_CODE between", value1, value2, "roleCode");
            return (Criteria) this;
        }

        public Criteria andRoleCodeNotBetween(String value1, String value2) {
            addCriterion("ROLE_CODE not between", value1, value2, "roleCode");
            return (Criteria) this;
        }

        public Criteria andWarnCodeIsNull() {
            addCriterion("WARN_CODE is null");
            return (Criteria) this;
        }

        public Criteria andWarnCodeIsNotNull() {
            addCriterion("WARN_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andWarnCodeEqualTo(String value) {
            addCriterion("WARN_CODE =", value, "warnCode");
            return (Criteria) this;
        }

        public Criteria andWarnCodeNotEqualTo(String value) {
            addCriterion("WARN_CODE <>", value, "warnCode");
            return (Criteria) this;
        }

        public Criteria andWarnCodeGreaterThan(String value) {
            addCriterion("WARN_CODE >", value, "warnCode");
            return (Criteria) this;
        }

        public Criteria andWarnCodeGreaterThanOrEqualTo(String value) {
            addCriterion("WARN_CODE >=", value, "warnCode");
            return (Criteria) this;
        }

        public Criteria andWarnCodeLessThan(String value) {
            addCriterion("WARN_CODE <", value, "warnCode");
            return (Criteria) this;
        }

        public Criteria andWarnCodeLessThanOrEqualTo(String value) {
            addCriterion("WARN_CODE <=", value, "warnCode");
            return (Criteria) this;
        }

        public Criteria andWarnCodeLike(String value) {
            addCriterion("WARN_CODE like", value, "warnCode");
            return (Criteria) this;
        }

        public Criteria andWarnCodeNotLike(String value) {
            addCriterion("WARN_CODE not like", value, "warnCode");
            return (Criteria) this;
        }

        public Criteria andWarnCodeIn(List<String> values) {
            addCriterion("WARN_CODE in", values, "warnCode");
            return (Criteria) this;
        }

        public Criteria andWarnCodeNotIn(List<String> values) {
            addCriterion("WARN_CODE not in", values, "warnCode");
            return (Criteria) this;
        }

        public Criteria andWarnCodeBetween(String value1, String value2) {
            addCriterion("WARN_CODE between", value1, value2, "warnCode");
            return (Criteria) this;
        }

        public Criteria andWarnCodeNotBetween(String value1, String value2) {
            addCriterion("WARN_CODE not between", value1, value2, "warnCode");
            return (Criteria) this;
        }

        public Criteria andMinValIsNull() {
            addCriterion("MIN_VAL is null");
            return (Criteria) this;
        }

        public Criteria andMinValIsNotNull() {
            addCriterion("MIN_VAL is not null");
            return (Criteria) this;
        }

        public Criteria andMinValEqualTo(Float value) {
            addCriterion("MIN_VAL =", value, "minVal");
            return (Criteria) this;
        }

        public Criteria andMinValNotEqualTo(Float value) {
            addCriterion("MIN_VAL <>", value, "minVal");
            return (Criteria) this;
        }

        public Criteria andMinValGreaterThan(Float value) {
            addCriterion("MIN_VAL >", value, "minVal");
            return (Criteria) this;
        }

        public Criteria andMinValGreaterThanOrEqualTo(Float value) {
            addCriterion("MIN_VAL >=", value, "minVal");
            return (Criteria) this;
        }

        public Criteria andMinValLessThan(Float value) {
            addCriterion("MIN_VAL <", value, "minVal");
            return (Criteria) this;
        }

        public Criteria andMinValLessThanOrEqualTo(Float value) {
            addCriterion("MIN_VAL <=", value, "minVal");
            return (Criteria) this;
        }

        public Criteria andMinValIn(List<Float> values) {
            addCriterion("MIN_VAL in", values, "minVal");
            return (Criteria) this;
        }

        public Criteria andMinValNotIn(List<Float> values) {
            addCriterion("MIN_VAL not in", values, "minVal");
            return (Criteria) this;
        }

        public Criteria andMinValBetween(Float value1, Float value2) {
            addCriterion("MIN_VAL between", value1, value2, "minVal");
            return (Criteria) this;
        }

        public Criteria andMinValNotBetween(Float value1, Float value2) {
            addCriterion("MIN_VAL not between", value1, value2, "minVal");
            return (Criteria) this;
        }

        public Criteria andMaxValIsNull() {
            addCriterion("MAX_VAL is null");
            return (Criteria) this;
        }

        public Criteria andMaxValIsNotNull() {
            addCriterion("MAX_VAL is not null");
            return (Criteria) this;
        }

        public Criteria andMaxValEqualTo(Float value) {
            addCriterion("MAX_VAL =", value, "maxVal");
            return (Criteria) this;
        }

        public Criteria andMaxValNotEqualTo(Float value) {
            addCriterion("MAX_VAL <>", value, "maxVal");
            return (Criteria) this;
        }

        public Criteria andMaxValGreaterThan(Float value) {
            addCriterion("MAX_VAL >", value, "maxVal");
            return (Criteria) this;
        }

        public Criteria andMaxValGreaterThanOrEqualTo(Float value) {
            addCriterion("MAX_VAL >=", value, "maxVal");
            return (Criteria) this;
        }

        public Criteria andMaxValLessThan(Float value) {
            addCriterion("MAX_VAL <", value, "maxVal");
            return (Criteria) this;
        }

        public Criteria andMaxValLessThanOrEqualTo(Float value) {
            addCriterion("MAX_VAL <=", value, "maxVal");
            return (Criteria) this;
        }

        public Criteria andMaxValIn(List<Float> values) {
            addCriterion("MAX_VAL in", values, "maxVal");
            return (Criteria) this;
        }

        public Criteria andMaxValNotIn(List<Float> values) {
            addCriterion("MAX_VAL not in", values, "maxVal");
            return (Criteria) this;
        }

        public Criteria andMaxValBetween(Float value1, Float value2) {
            addCriterion("MAX_VAL between", value1, value2, "maxVal");
            return (Criteria) this;
        }

        public Criteria andMaxValNotBetween(Float value1, Float value2) {
            addCriterion("MAX_VAL not between", value1, value2, "maxVal");
            return (Criteria) this;
        }

        public Criteria andAlertTypeIsNull() {
            addCriterion("ALERT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andAlertTypeIsNotNull() {
            addCriterion("ALERT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andAlertTypeEqualTo(String value) {
            addCriterion("ALERT_TYPE =", value, "alertType");
            return (Criteria) this;
        }

        public Criteria andAlertTypeNotEqualTo(String value) {
            addCriterion("ALERT_TYPE <>", value, "alertType");
            return (Criteria) this;
        }

        public Criteria andAlertTypeGreaterThan(String value) {
            addCriterion("ALERT_TYPE >", value, "alertType");
            return (Criteria) this;
        }

        public Criteria andAlertTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ALERT_TYPE >=", value, "alertType");
            return (Criteria) this;
        }

        public Criteria andAlertTypeLessThan(String value) {
            addCriterion("ALERT_TYPE <", value, "alertType");
            return (Criteria) this;
        }

        public Criteria andAlertTypeLessThanOrEqualTo(String value) {
            addCriterion("ALERT_TYPE <=", value, "alertType");
            return (Criteria) this;
        }

        public Criteria andAlertTypeLike(String value) {
            addCriterion("ALERT_TYPE like", value, "alertType");
            return (Criteria) this;
        }

        public Criteria andAlertTypeNotLike(String value) {
            addCriterion("ALERT_TYPE not like", value, "alertType");
            return (Criteria) this;
        }

        public Criteria andAlertTypeIn(List<String> values) {
            addCriterion("ALERT_TYPE in", values, "alertType");
            return (Criteria) this;
        }

        public Criteria andAlertTypeNotIn(List<String> values) {
            addCriterion("ALERT_TYPE not in", values, "alertType");
            return (Criteria) this;
        }

        public Criteria andAlertTypeBetween(String value1, String value2) {
            addCriterion("ALERT_TYPE between", value1, value2, "alertType");
            return (Criteria) this;
        }

        public Criteria andAlertTypeNotBetween(String value1, String value2) {
            addCriterion("ALERT_TYPE not between", value1, value2, "alertType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIsNull() {
            addCriterion("ORG_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIsNotNull() {
            addCriterion("ORG_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andOrgTypeEqualTo(String value) {
            addCriterion("ORG_TYPE =", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNotEqualTo(String value) {
            addCriterion("ORG_TYPE <>", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeGreaterThan(String value) {
            addCriterion("ORG_TYPE >", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_TYPE >=", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeLessThan(String value) {
            addCriterion("ORG_TYPE <", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeLessThanOrEqualTo(String value) {
            addCriterion("ORG_TYPE <=", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeLike(String value) {
            addCriterion("ORG_TYPE like", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNotLike(String value) {
            addCriterion("ORG_TYPE not like", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIn(List<String> values) {
            addCriterion("ORG_TYPE in", values, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNotIn(List<String> values) {
            addCriterion("ORG_TYPE not in", values, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeBetween(String value1, String value2) {
            addCriterion("ORG_TYPE between", value1, value2, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNotBetween(String value1, String value2) {
            addCriterion("ORG_TYPE not between", value1, value2, "orgType");
            return (Criteria) this;
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

        public Criteria andWarnStatusIsNull() {
            addCriterion("WARN_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andWarnStatusIsNotNull() {
            addCriterion("WARN_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andWarnStatusEqualTo(String value) {
            addCriterion("WARN_STATUS =", value, "warnStatus");
            return (Criteria) this;
        }

        public Criteria andWarnStatusNotEqualTo(String value) {
            addCriterion("WARN_STATUS <>", value, "warnStatus");
            return (Criteria) this;
        }

        public Criteria andWarnStatusGreaterThan(String value) {
            addCriterion("WARN_STATUS >", value, "warnStatus");
            return (Criteria) this;
        }

        public Criteria andWarnStatusGreaterThanOrEqualTo(String value) {
            addCriterion("WARN_STATUS >=", value, "warnStatus");
            return (Criteria) this;
        }

        public Criteria andWarnStatusLessThan(String value) {
            addCriterion("WARN_STATUS <", value, "warnStatus");
            return (Criteria) this;
        }

        public Criteria andWarnStatusLessThanOrEqualTo(String value) {
            addCriterion("WARN_STATUS <=", value, "warnStatus");
            return (Criteria) this;
        }

        public Criteria andWarnStatusLike(String value) {
            addCriterion("WARN_STATUS like", value, "warnStatus");
            return (Criteria) this;
        }

        public Criteria andWarnStatusNotLike(String value) {
            addCriterion("WARN_STATUS not like", value, "warnStatus");
            return (Criteria) this;
        }

        public Criteria andWarnStatusIn(List<String> values) {
            addCriterion("WARN_STATUS in", values, "warnStatus");
            return (Criteria) this;
        }

        public Criteria andWarnStatusNotIn(List<String> values) {
            addCriterion("WARN_STATUS not in", values, "warnStatus");
            return (Criteria) this;
        }

        public Criteria andWarnStatusBetween(String value1, String value2) {
            addCriterion("WARN_STATUS between", value1, value2, "warnStatus");
            return (Criteria) this;
        }

        public Criteria andWarnStatusNotBetween(String value1, String value2) {
            addCriterion("WARN_STATUS not between", value1, value2, "warnStatus");
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