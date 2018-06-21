package com.huatai.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UserBehInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserBehInfoExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andBehavioridIsNull() {
            addCriterion("BEHAVIORID is null");
            return (Criteria) this;
        }

        public Criteria andBehavioridIsNotNull() {
            addCriterion("BEHAVIORID is not null");
            return (Criteria) this;
        }

        public Criteria andBehavioridEqualTo(String value) {
            addCriterion("BEHAVIORID =", value, "behaviorid");
            return (Criteria) this;
        }

        public Criteria andBehavioridNotEqualTo(String value) {
            addCriterion("BEHAVIORID <>", value, "behaviorid");
            return (Criteria) this;
        }

        public Criteria andBehavioridGreaterThan(String value) {
            addCriterion("BEHAVIORID >", value, "behaviorid");
            return (Criteria) this;
        }

        public Criteria andBehavioridGreaterThanOrEqualTo(String value) {
            addCriterion("BEHAVIORID >=", value, "behaviorid");
            return (Criteria) this;
        }

        public Criteria andBehavioridLessThan(String value) {
            addCriterion("BEHAVIORID <", value, "behaviorid");
            return (Criteria) this;
        }

        public Criteria andBehavioridLessThanOrEqualTo(String value) {
            addCriterion("BEHAVIORID <=", value, "behaviorid");
            return (Criteria) this;
        }

        public Criteria andBehavioridLike(String value) {
            addCriterion("BEHAVIORID like", value, "behaviorid");
            return (Criteria) this;
        }

        public Criteria andBehavioridNotLike(String value) {
            addCriterion("BEHAVIORID not like", value, "behaviorid");
            return (Criteria) this;
        }

        public Criteria andBehavioridIn(List<String> values) {
            addCriterion("BEHAVIORID in", values, "behaviorid");
            return (Criteria) this;
        }

        public Criteria andBehavioridNotIn(List<String> values) {
            addCriterion("BEHAVIORID not in", values, "behaviorid");
            return (Criteria) this;
        }

        public Criteria andBehavioridBetween(String value1, String value2) {
            addCriterion("BEHAVIORID between", value1, value2, "behaviorid");
            return (Criteria) this;
        }

        public Criteria andBehavioridNotBetween(String value1, String value2) {
            addCriterion("BEHAVIORID not between", value1, value2, "behaviorid");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("USER_ID =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("USER_ID <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("USER_ID >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_ID >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("USER_ID <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("USER_ID <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("USER_ID like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("USER_ID not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("USER_ID in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("USER_ID not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("USER_NAME =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("USER_NAME <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("USER_NAME >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("USER_NAME >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("USER_NAME <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("USER_NAME <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("USER_NAME like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("USER_NAME not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("USER_NAME in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("USER_NAME not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("USER_NAME between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("USER_NAME not between", value1, value2, "userName");
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

        public Criteria andLoginTimeNowIsNull() {
            addCriterion("LOGIN_TIME_NOW is null");
            return (Criteria) this;
        }

        public Criteria andLoginTimeNowIsNotNull() {
            addCriterion("LOGIN_TIME_NOW is not null");
            return (Criteria) this;
        }

        public Criteria andLoginTimeNowEqualTo(Date value) {
            addCriterionForJDBCDate("LOGIN_TIME_NOW =", value, "loginTimeNow");
            return (Criteria) this;
        }

        public Criteria andLoginTimeNowNotEqualTo(Date value) {
            addCriterionForJDBCDate("LOGIN_TIME_NOW <>", value, "loginTimeNow");
            return (Criteria) this;
        }

        public Criteria andLoginTimeNowGreaterThan(Date value) {
            addCriterionForJDBCDate("LOGIN_TIME_NOW >", value, "loginTimeNow");
            return (Criteria) this;
        }

        public Criteria andLoginTimeNowGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("LOGIN_TIME_NOW >=", value, "loginTimeNow");
            return (Criteria) this;
        }

        public Criteria andLoginTimeNowLessThan(Date value) {
            addCriterionForJDBCDate("LOGIN_TIME_NOW <", value, "loginTimeNow");
            return (Criteria) this;
        }

        public Criteria andLoginTimeNowLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("LOGIN_TIME_NOW <=", value, "loginTimeNow");
            return (Criteria) this;
        }

        public Criteria andLoginTimeNowIn(List<Date> values) {
            addCriterionForJDBCDate("LOGIN_TIME_NOW in", values, "loginTimeNow");
            return (Criteria) this;
        }

        public Criteria andLoginTimeNowNotIn(List<Date> values) {
            addCriterionForJDBCDate("LOGIN_TIME_NOW not in", values, "loginTimeNow");
            return (Criteria) this;
        }

        public Criteria andLoginTimeNowBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("LOGIN_TIME_NOW between", value1, value2, "loginTimeNow");
            return (Criteria) this;
        }

        public Criteria andLoginTimeNowNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("LOGIN_TIME_NOW not between", value1, value2, "loginTimeNow");
            return (Criteria) this;
        }

        public Criteria andLoginTimeFirstIsNull() {
            addCriterion("LOGIN_TIME_FIRST is null");
            return (Criteria) this;
        }

        public Criteria andLoginTimeFirstIsNotNull() {
            addCriterion("LOGIN_TIME_FIRST is not null");
            return (Criteria) this;
        }

        public Criteria andLoginTimeFirstEqualTo(Date value) {
            addCriterionForJDBCDate("LOGIN_TIME_FIRST =", value, "loginTimeFirst");
            return (Criteria) this;
        }

        public Criteria andLoginTimeFirstNotEqualTo(Date value) {
            addCriterionForJDBCDate("LOGIN_TIME_FIRST <>", value, "loginTimeFirst");
            return (Criteria) this;
        }

        public Criteria andLoginTimeFirstGreaterThan(Date value) {
            addCriterionForJDBCDate("LOGIN_TIME_FIRST >", value, "loginTimeFirst");
            return (Criteria) this;
        }

        public Criteria andLoginTimeFirstGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("LOGIN_TIME_FIRST >=", value, "loginTimeFirst");
            return (Criteria) this;
        }

        public Criteria andLoginTimeFirstLessThan(Date value) {
            addCriterionForJDBCDate("LOGIN_TIME_FIRST <", value, "loginTimeFirst");
            return (Criteria) this;
        }

        public Criteria andLoginTimeFirstLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("LOGIN_TIME_FIRST <=", value, "loginTimeFirst");
            return (Criteria) this;
        }

        public Criteria andLoginTimeFirstIn(List<Date> values) {
            addCriterionForJDBCDate("LOGIN_TIME_FIRST in", values, "loginTimeFirst");
            return (Criteria) this;
        }

        public Criteria andLoginTimeFirstNotIn(List<Date> values) {
            addCriterionForJDBCDate("LOGIN_TIME_FIRST not in", values, "loginTimeFirst");
            return (Criteria) this;
        }

        public Criteria andLoginTimeFirstBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("LOGIN_TIME_FIRST between", value1, value2, "loginTimeFirst");
            return (Criteria) this;
        }

        public Criteria andLoginTimeFirstNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("LOGIN_TIME_FIRST not between", value1, value2, "loginTimeFirst");
            return (Criteria) this;
        }

        public Criteria andLoginTimeLastIsNull() {
            addCriterion("LOGIN_TIME_LAST is null");
            return (Criteria) this;
        }

        public Criteria andLoginTimeLastIsNotNull() {
            addCriterion("LOGIN_TIME_LAST is not null");
            return (Criteria) this;
        }

        public Criteria andLoginTimeLastEqualTo(Date value) {
            addCriterionForJDBCDate("LOGIN_TIME_LAST =", value, "loginTimeLast");
            return (Criteria) this;
        }

        public Criteria andLoginTimeLastNotEqualTo(Date value) {
            addCriterionForJDBCDate("LOGIN_TIME_LAST <>", value, "loginTimeLast");
            return (Criteria) this;
        }

        public Criteria andLoginTimeLastGreaterThan(Date value) {
            addCriterionForJDBCDate("LOGIN_TIME_LAST >", value, "loginTimeLast");
            return (Criteria) this;
        }

        public Criteria andLoginTimeLastGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("LOGIN_TIME_LAST >=", value, "loginTimeLast");
            return (Criteria) this;
        }

        public Criteria andLoginTimeLastLessThan(Date value) {
            addCriterionForJDBCDate("LOGIN_TIME_LAST <", value, "loginTimeLast");
            return (Criteria) this;
        }

        public Criteria andLoginTimeLastLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("LOGIN_TIME_LAST <=", value, "loginTimeLast");
            return (Criteria) this;
        }

        public Criteria andLoginTimeLastIn(List<Date> values) {
            addCriterionForJDBCDate("LOGIN_TIME_LAST in", values, "loginTimeLast");
            return (Criteria) this;
        }

        public Criteria andLoginTimeLastNotIn(List<Date> values) {
            addCriterionForJDBCDate("LOGIN_TIME_LAST not in", values, "loginTimeLast");
            return (Criteria) this;
        }

        public Criteria andLoginTimeLastBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("LOGIN_TIME_LAST between", value1, value2, "loginTimeLast");
            return (Criteria) this;
        }

        public Criteria andLoginTimeLastNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("LOGIN_TIME_LAST not between", value1, value2, "loginTimeLast");
            return (Criteria) this;
        }

        public Criteria andIsOnlineIsNull() {
            addCriterion("IS_ONLINE is null");
            return (Criteria) this;
        }

        public Criteria andIsOnlineIsNotNull() {
            addCriterion("IS_ONLINE is not null");
            return (Criteria) this;
        }

        public Criteria andIsOnlineEqualTo(String value) {
            addCriterion("IS_ONLINE =", value, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineNotEqualTo(String value) {
            addCriterion("IS_ONLINE <>", value, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineGreaterThan(String value) {
            addCriterion("IS_ONLINE >", value, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineGreaterThanOrEqualTo(String value) {
            addCriterion("IS_ONLINE >=", value, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineLessThan(String value) {
            addCriterion("IS_ONLINE <", value, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineLessThanOrEqualTo(String value) {
            addCriterion("IS_ONLINE <=", value, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineLike(String value) {
            addCriterion("IS_ONLINE like", value, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineNotLike(String value) {
            addCriterion("IS_ONLINE not like", value, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineIn(List<String> values) {
            addCriterion("IS_ONLINE in", values, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineNotIn(List<String> values) {
            addCriterion("IS_ONLINE not in", values, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineBetween(String value1, String value2) {
            addCriterion("IS_ONLINE between", value1, value2, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineNotBetween(String value1, String value2) {
            addCriterion("IS_ONLINE not between", value1, value2, "isOnline");
            return (Criteria) this;
        }

        public Criteria andRegeditDateIsNull() {
            addCriterion("REGEDIT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andRegeditDateIsNotNull() {
            addCriterion("REGEDIT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andRegeditDateEqualTo(Date value) {
            addCriterionForJDBCDate("REGEDIT_DATE =", value, "regeditDate");
            return (Criteria) this;
        }

        public Criteria andRegeditDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("REGEDIT_DATE <>", value, "regeditDate");
            return (Criteria) this;
        }

        public Criteria andRegeditDateGreaterThan(Date value) {
            addCriterionForJDBCDate("REGEDIT_DATE >", value, "regeditDate");
            return (Criteria) this;
        }

        public Criteria andRegeditDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("REGEDIT_DATE >=", value, "regeditDate");
            return (Criteria) this;
        }

        public Criteria andRegeditDateLessThan(Date value) {
            addCriterionForJDBCDate("REGEDIT_DATE <", value, "regeditDate");
            return (Criteria) this;
        }

        public Criteria andRegeditDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("REGEDIT_DATE <=", value, "regeditDate");
            return (Criteria) this;
        }

        public Criteria andRegeditDateIn(List<Date> values) {
            addCriterionForJDBCDate("REGEDIT_DATE in", values, "regeditDate");
            return (Criteria) this;
        }

        public Criteria andRegeditDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("REGEDIT_DATE not in", values, "regeditDate");
            return (Criteria) this;
        }

        public Criteria andRegeditDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("REGEDIT_DATE between", value1, value2, "regeditDate");
            return (Criteria) this;
        }

        public Criteria andRegeditDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("REGEDIT_DATE not between", value1, value2, "regeditDate");
            return (Criteria) this;
        }

        public Criteria andIsRecentIsNull() {
            addCriterion("IS_RECENT is null");
            return (Criteria) this;
        }

        public Criteria andIsRecentIsNotNull() {
            addCriterion("IS_RECENT is not null");
            return (Criteria) this;
        }

        public Criteria andIsRecentEqualTo(String value) {
            addCriterion("IS_RECENT =", value, "isRecent");
            return (Criteria) this;
        }

        public Criteria andIsRecentNotEqualTo(String value) {
            addCriterion("IS_RECENT <>", value, "isRecent");
            return (Criteria) this;
        }

        public Criteria andIsRecentGreaterThan(String value) {
            addCriterion("IS_RECENT >", value, "isRecent");
            return (Criteria) this;
        }

        public Criteria andIsRecentGreaterThanOrEqualTo(String value) {
            addCriterion("IS_RECENT >=", value, "isRecent");
            return (Criteria) this;
        }

        public Criteria andIsRecentLessThan(String value) {
            addCriterion("IS_RECENT <", value, "isRecent");
            return (Criteria) this;
        }

        public Criteria andIsRecentLessThanOrEqualTo(String value) {
            addCriterion("IS_RECENT <=", value, "isRecent");
            return (Criteria) this;
        }

        public Criteria andIsRecentLike(String value) {
            addCriterion("IS_RECENT like", value, "isRecent");
            return (Criteria) this;
        }

        public Criteria andIsRecentNotLike(String value) {
            addCriterion("IS_RECENT not like", value, "isRecent");
            return (Criteria) this;
        }

        public Criteria andIsRecentIn(List<String> values) {
            addCriterion("IS_RECENT in", values, "isRecent");
            return (Criteria) this;
        }

        public Criteria andIsRecentNotIn(List<String> values) {
            addCriterion("IS_RECENT not in", values, "isRecent");
            return (Criteria) this;
        }

        public Criteria andIsRecentBetween(String value1, String value2) {
            addCriterion("IS_RECENT between", value1, value2, "isRecent");
            return (Criteria) this;
        }

        public Criteria andIsRecentNotBetween(String value1, String value2) {
            addCriterion("IS_RECENT not between", value1, value2, "isRecent");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(String value) {
            addCriterion("LEVEL =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(String value) {
            addCriterion("LEVEL <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(String value) {
            addCriterion("LEVEL >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(String value) {
            addCriterion("LEVEL >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(String value) {
            addCriterion("LEVEL <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(String value) {
            addCriterion("LEVEL <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLike(String value) {
            addCriterion("LEVEL like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotLike(String value) {
            addCriterion("LEVEL not like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<String> values) {
            addCriterion("LEVEL in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<String> values) {
            addCriterion("LEVEL not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(String value1, String value2) {
            addCriterion("LEVEL between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(String value1, String value2) {
            addCriterion("LEVEL not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andOutTimeIsNull() {
            addCriterion("OUT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andOutTimeIsNotNull() {
            addCriterion("OUT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andOutTimeEqualTo(Date value) {
            addCriterionForJDBCDate("OUT_TIME =", value, "outTime");
            return (Criteria) this;
        }

        public Criteria andOutTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("OUT_TIME <>", value, "outTime");
            return (Criteria) this;
        }

        public Criteria andOutTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("OUT_TIME >", value, "outTime");
            return (Criteria) this;
        }

        public Criteria andOutTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("OUT_TIME >=", value, "outTime");
            return (Criteria) this;
        }

        public Criteria andOutTimeLessThan(Date value) {
            addCriterionForJDBCDate("OUT_TIME <", value, "outTime");
            return (Criteria) this;
        }

        public Criteria andOutTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("OUT_TIME <=", value, "outTime");
            return (Criteria) this;
        }

        public Criteria andOutTimeIn(List<Date> values) {
            addCriterionForJDBCDate("OUT_TIME in", values, "outTime");
            return (Criteria) this;
        }

        public Criteria andOutTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("OUT_TIME not in", values, "outTime");
            return (Criteria) this;
        }

        public Criteria andOutTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("OUT_TIME between", value1, value2, "outTime");
            return (Criteria) this;
        }

        public Criteria andOutTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("OUT_TIME not between", value1, value2, "outTime");
            return (Criteria) this;
        }

        public Criteria andTerminalTypeIsNull() {
            addCriterion("TERMINAL_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTerminalTypeIsNotNull() {
            addCriterion("TERMINAL_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTerminalTypeEqualTo(String value) {
            addCriterion("TERMINAL_TYPE =", value, "terminalType");
            return (Criteria) this;
        }

        public Criteria andTerminalTypeNotEqualTo(String value) {
            addCriterion("TERMINAL_TYPE <>", value, "terminalType");
            return (Criteria) this;
        }

        public Criteria andTerminalTypeGreaterThan(String value) {
            addCriterion("TERMINAL_TYPE >", value, "terminalType");
            return (Criteria) this;
        }

        public Criteria andTerminalTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TERMINAL_TYPE >=", value, "terminalType");
            return (Criteria) this;
        }

        public Criteria andTerminalTypeLessThan(String value) {
            addCriterion("TERMINAL_TYPE <", value, "terminalType");
            return (Criteria) this;
        }

        public Criteria andTerminalTypeLessThanOrEqualTo(String value) {
            addCriterion("TERMINAL_TYPE <=", value, "terminalType");
            return (Criteria) this;
        }

        public Criteria andTerminalTypeLike(String value) {
            addCriterion("TERMINAL_TYPE like", value, "terminalType");
            return (Criteria) this;
        }

        public Criteria andTerminalTypeNotLike(String value) {
            addCriterion("TERMINAL_TYPE not like", value, "terminalType");
            return (Criteria) this;
        }

        public Criteria andTerminalTypeIn(List<String> values) {
            addCriterion("TERMINAL_TYPE in", values, "terminalType");
            return (Criteria) this;
        }

        public Criteria andTerminalTypeNotIn(List<String> values) {
            addCriterion("TERMINAL_TYPE not in", values, "terminalType");
            return (Criteria) this;
        }

        public Criteria andTerminalTypeBetween(String value1, String value2) {
            addCriterion("TERMINAL_TYPE between", value1, value2, "terminalType");
            return (Criteria) this;
        }

        public Criteria andTerminalTypeNotBetween(String value1, String value2) {
            addCriterion("TERMINAL_TYPE not between", value1, value2, "terminalType");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("VERSION is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("VERSION is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(String value) {
            addCriterion("VERSION =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(String value) {
            addCriterion("VERSION <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(String value) {
            addCriterion("VERSION >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(String value) {
            addCriterion("VERSION >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(String value) {
            addCriterion("VERSION <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(String value) {
            addCriterion("VERSION <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLike(String value) {
            addCriterion("VERSION like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotLike(String value) {
            addCriterion("VERSION not like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<String> values) {
            addCriterion("VERSION in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<String> values) {
            addCriterion("VERSION not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(String value1, String value2) {
            addCriterion("VERSION between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(String value1, String value2) {
            addCriterion("VERSION not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andIsExcIsNull() {
            addCriterion("IS_EXC is null");
            return (Criteria) this;
        }

        public Criteria andIsExcIsNotNull() {
            addCriterion("IS_EXC is not null");
            return (Criteria) this;
        }

        public Criteria andIsExcEqualTo(String value) {
            addCriterion("IS_EXC =", value, "isExc");
            return (Criteria) this;
        }

        public Criteria andIsExcNotEqualTo(String value) {
            addCriterion("IS_EXC <>", value, "isExc");
            return (Criteria) this;
        }

        public Criteria andIsExcGreaterThan(String value) {
            addCriterion("IS_EXC >", value, "isExc");
            return (Criteria) this;
        }

        public Criteria andIsExcGreaterThanOrEqualTo(String value) {
            addCriterion("IS_EXC >=", value, "isExc");
            return (Criteria) this;
        }

        public Criteria andIsExcLessThan(String value) {
            addCriterion("IS_EXC <", value, "isExc");
            return (Criteria) this;
        }

        public Criteria andIsExcLessThanOrEqualTo(String value) {
            addCriterion("IS_EXC <=", value, "isExc");
            return (Criteria) this;
        }

        public Criteria andIsExcLike(String value) {
            addCriterion("IS_EXC like", value, "isExc");
            return (Criteria) this;
        }

        public Criteria andIsExcNotLike(String value) {
            addCriterion("IS_EXC not like", value, "isExc");
            return (Criteria) this;
        }

        public Criteria andIsExcIn(List<String> values) {
            addCriterion("IS_EXC in", values, "isExc");
            return (Criteria) this;
        }

        public Criteria andIsExcNotIn(List<String> values) {
            addCriterion("IS_EXC not in", values, "isExc");
            return (Criteria) this;
        }

        public Criteria andIsExcBetween(String value1, String value2) {
            addCriterion("IS_EXC between", value1, value2, "isExc");
            return (Criteria) this;
        }

        public Criteria andIsExcNotBetween(String value1, String value2) {
            addCriterion("IS_EXC not between", value1, value2, "isExc");
            return (Criteria) this;
        }

        public Criteria andErrorInfoIsNull() {
            addCriterion("ERROR_INFO is null");
            return (Criteria) this;
        }

        public Criteria andErrorInfoIsNotNull() {
            addCriterion("ERROR_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andErrorInfoEqualTo(String value) {
            addCriterion("ERROR_INFO =", value, "errorInfo");
            return (Criteria) this;
        }

        public Criteria andErrorInfoNotEqualTo(String value) {
            addCriterion("ERROR_INFO <>", value, "errorInfo");
            return (Criteria) this;
        }

        public Criteria andErrorInfoGreaterThan(String value) {
            addCriterion("ERROR_INFO >", value, "errorInfo");
            return (Criteria) this;
        }

        public Criteria andErrorInfoGreaterThanOrEqualTo(String value) {
            addCriterion("ERROR_INFO >=", value, "errorInfo");
            return (Criteria) this;
        }

        public Criteria andErrorInfoLessThan(String value) {
            addCriterion("ERROR_INFO <", value, "errorInfo");
            return (Criteria) this;
        }

        public Criteria andErrorInfoLessThanOrEqualTo(String value) {
            addCriterion("ERROR_INFO <=", value, "errorInfo");
            return (Criteria) this;
        }

        public Criteria andErrorInfoLike(String value) {
            addCriterion("ERROR_INFO like", value, "errorInfo");
            return (Criteria) this;
        }

        public Criteria andErrorInfoNotLike(String value) {
            addCriterion("ERROR_INFO not like", value, "errorInfo");
            return (Criteria) this;
        }

        public Criteria andErrorInfoIn(List<String> values) {
            addCriterion("ERROR_INFO in", values, "errorInfo");
            return (Criteria) this;
        }

        public Criteria andErrorInfoNotIn(List<String> values) {
            addCriterion("ERROR_INFO not in", values, "errorInfo");
            return (Criteria) this;
        }

        public Criteria andErrorInfoBetween(String value1, String value2) {
            addCriterion("ERROR_INFO between", value1, value2, "errorInfo");
            return (Criteria) this;
        }

        public Criteria andErrorInfoNotBetween(String value1, String value2) {
            addCriterion("ERROR_INFO not between", value1, value2, "errorInfo");
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