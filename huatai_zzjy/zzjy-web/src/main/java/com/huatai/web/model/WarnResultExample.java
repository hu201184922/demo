package com.huatai.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WarnResultExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WarnResultExample() {
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

        public Criteria andWrIdIsNull() {
            addCriterion("WR_ID is null");
            return (Criteria) this;
        }

        public Criteria andWrIdIsNotNull() {
            addCriterion("WR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andWrIdEqualTo(Integer value) {
            addCriterion("WR_ID =", value, "wrId");
            return (Criteria) this;
        }

        public Criteria andWrIdNotEqualTo(Integer value) {
            addCriterion("WR_ID <>", value, "wrId");
            return (Criteria) this;
        }

        public Criteria andWrIdGreaterThan(Integer value) {
            addCriterion("WR_ID >", value, "wrId");
            return (Criteria) this;
        }

        public Criteria andWrIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("WR_ID >=", value, "wrId");
            return (Criteria) this;
        }

        public Criteria andWrIdLessThan(Integer value) {
            addCriterion("WR_ID <", value, "wrId");
            return (Criteria) this;
        }

        public Criteria andWrIdLessThanOrEqualTo(Integer value) {
            addCriterion("WR_ID <=", value, "wrId");
            return (Criteria) this;
        }

        public Criteria andWrIdIn(List<Integer> values) {
            addCriterion("WR_ID in", values, "wrId");
            return (Criteria) this;
        }

        public Criteria andWrIdNotIn(List<Integer> values) {
            addCriterion("WR_ID not in", values, "wrId");
            return (Criteria) this;
        }

        public Criteria andWrIdBetween(Integer value1, Integer value2) {
            addCriterion("WR_ID between", value1, value2, "wrId");
            return (Criteria) this;
        }

        public Criteria andWrIdNotBetween(Integer value1, Integer value2) {
            addCriterion("WR_ID not between", value1, value2, "wrId");
            return (Criteria) this;
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

        public Criteria andWarnValIsNull() {
            addCriterion("WARN_VAL is null");
            return (Criteria) this;
        }

        public Criteria andWarnValIsNotNull() {
            addCriterion("WARN_VAL is not null");
            return (Criteria) this;
        }

        public Criteria andWarnValEqualTo(Float value) {
            addCriterion("WARN_VAL =", value, "warnVal");
            return (Criteria) this;
        }

        public Criteria andWarnValNotEqualTo(Float value) {
            addCriterion("WARN_VAL <>", value, "warnVal");
            return (Criteria) this;
        }

        public Criteria andWarnValGreaterThan(Float value) {
            addCriterion("WARN_VAL >", value, "warnVal");
            return (Criteria) this;
        }

        public Criteria andWarnValGreaterThanOrEqualTo(Float value) {
            addCriterion("WARN_VAL >=", value, "warnVal");
            return (Criteria) this;
        }

        public Criteria andWarnValLessThan(Float value) {
            addCriterion("WARN_VAL <", value, "warnVal");
            return (Criteria) this;
        }

        public Criteria andWarnValLessThanOrEqualTo(Float value) {
            addCriterion("WARN_VAL <=", value, "warnVal");
            return (Criteria) this;
        }

        public Criteria andWarnValIn(List<Float> values) {
            addCriterion("WARN_VAL in", values, "warnVal");
            return (Criteria) this;
        }

        public Criteria andWarnValNotIn(List<Float> values) {
            addCriterion("WARN_VAL not in", values, "warnVal");
            return (Criteria) this;
        }

        public Criteria andWarnValBetween(Float value1, Float value2) {
            addCriterion("WARN_VAL between", value1, value2, "warnVal");
            return (Criteria) this;
        }

        public Criteria andWarnValNotBetween(Float value1, Float value2) {
            addCriterion("WARN_VAL not between", value1, value2, "warnVal");
            return (Criteria) this;
        }

        public Criteria andFirstWarnTimeIsNull() {
            addCriterion("FIRST_WARN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andFirstWarnTimeIsNotNull() {
            addCriterion("FIRST_WARN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andFirstWarnTimeEqualTo(Date value) {
            addCriterion("FIRST_WARN_TIME =", value, "firstWarnTime");
            return (Criteria) this;
        }

        public Criteria andFirstWarnTimeNotEqualTo(Date value) {
            addCriterion("FIRST_WARN_TIME <>", value, "firstWarnTime");
            return (Criteria) this;
        }

        public Criteria andFirstWarnTimeGreaterThan(Date value) {
            addCriterion("FIRST_WARN_TIME >", value, "firstWarnTime");
            return (Criteria) this;
        }

        public Criteria andFirstWarnTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("FIRST_WARN_TIME >=", value, "firstWarnTime");
            return (Criteria) this;
        }

        public Criteria andFirstWarnTimeLessThan(Date value) {
            addCriterion("FIRST_WARN_TIME <", value, "firstWarnTime");
            return (Criteria) this;
        }

        public Criteria andFirstWarnTimeLessThanOrEqualTo(Date value) {
            addCriterion("FIRST_WARN_TIME <=", value, "firstWarnTime");
            return (Criteria) this;
        }

        public Criteria andFirstWarnTimeIn(List<Date> values) {
            addCriterion("FIRST_WARN_TIME in", values, "firstWarnTime");
            return (Criteria) this;
        }

        public Criteria andFirstWarnTimeNotIn(List<Date> values) {
            addCriterion("FIRST_WARN_TIME not in", values, "firstWarnTime");
            return (Criteria) this;
        }

        public Criteria andFirstWarnTimeBetween(Date value1, Date value2) {
            addCriterion("FIRST_WARN_TIME between", value1, value2, "firstWarnTime");
            return (Criteria) this;
        }

        public Criteria andFirstWarnTimeNotBetween(Date value1, Date value2) {
            addCriterion("FIRST_WARN_TIME not between", value1, value2, "firstWarnTime");
            return (Criteria) this;
        }

        public Criteria andWarnTimeIsNull() {
            addCriterion("WARN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andWarnTimeIsNotNull() {
            addCriterion("WARN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andWarnTimeEqualTo(Date value) {
            addCriterion("WARN_TIME =", value, "warnTime");
            return (Criteria) this;
        }

        public Criteria andWarnTimeNotEqualTo(Date value) {
            addCriterion("WARN_TIME <>", value, "warnTime");
            return (Criteria) this;
        }

        public Criteria andWarnTimeGreaterThan(Date value) {
            addCriterion("WARN_TIME >", value, "warnTime");
            return (Criteria) this;
        }

        public Criteria andWarnTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("WARN_TIME >=", value, "warnTime");
            return (Criteria) this;
        }

        public Criteria andWarnTimeLessThan(Date value) {
            addCriterion("WARN_TIME <", value, "warnTime");
            return (Criteria) this;
        }

        public Criteria andWarnTimeLessThanOrEqualTo(Date value) {
            addCriterion("WARN_TIME <=", value, "warnTime");
            return (Criteria) this;
        }

        public Criteria andWarnTimeIn(List<Date> values) {
            addCriterion("WARN_TIME in", values, "warnTime");
            return (Criteria) this;
        }

        public Criteria andWarnTimeNotIn(List<Date> values) {
            addCriterion("WARN_TIME not in", values, "warnTime");
            return (Criteria) this;
        }

        public Criteria andWarnTimeBetween(Date value1, Date value2) {
            addCriterion("WARN_TIME between", value1, value2, "warnTime");
            return (Criteria) this;
        }

        public Criteria andWarnTimeNotBetween(Date value1, Date value2) {
            addCriterion("WARN_TIME not between", value1, value2, "warnTime");
            return (Criteria) this;
        }

        public Criteria andIsReadIsNull() {
            addCriterion("IS_READ is null");
            return (Criteria) this;
        }

        public Criteria andIsReadIsNotNull() {
            addCriterion("IS_READ is not null");
            return (Criteria) this;
        }

        public Criteria andIsReadEqualTo(String value) {
            addCriterion("IS_READ =", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadNotEqualTo(String value) {
            addCriterion("IS_READ <>", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadGreaterThan(String value) {
            addCriterion("IS_READ >", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadGreaterThanOrEqualTo(String value) {
            addCriterion("IS_READ >=", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadLessThan(String value) {
            addCriterion("IS_READ <", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadLessThanOrEqualTo(String value) {
            addCriterion("IS_READ <=", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadLike(String value) {
            addCriterion("IS_READ like", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadNotLike(String value) {
            addCriterion("IS_READ not like", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadIn(List<String> values) {
            addCriterion("IS_READ in", values, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadNotIn(List<String> values) {
            addCriterion("IS_READ not in", values, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadBetween(String value1, String value2) {
            addCriterion("IS_READ between", value1, value2, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadNotBetween(String value1, String value2) {
            addCriterion("IS_READ not between", value1, value2, "isRead");
            return (Criteria) this;
        }

        public Criteria andReadTimeIsNull() {
            addCriterion("READ_TIME is null");
            return (Criteria) this;
        }

        public Criteria andReadTimeIsNotNull() {
            addCriterion("READ_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andReadTimeEqualTo(Date value) {
            addCriterion("READ_TIME =", value, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeNotEqualTo(Date value) {
            addCriterion("READ_TIME <>", value, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeGreaterThan(Date value) {
            addCriterion("READ_TIME >", value, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("READ_TIME >=", value, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeLessThan(Date value) {
            addCriterion("READ_TIME <", value, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeLessThanOrEqualTo(Date value) {
            addCriterion("READ_TIME <=", value, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeIn(List<Date> values) {
            addCriterion("READ_TIME in", values, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeNotIn(List<Date> values) {
            addCriterion("READ_TIME not in", values, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeBetween(Date value1, Date value2) {
            addCriterion("READ_TIME between", value1, value2, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeNotBetween(Date value1, Date value2) {
            addCriterion("READ_TIME not between", value1, value2, "readTime");
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