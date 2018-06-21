package com.huatai.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QueryDimExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public QueryDimExample() {
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

        public Criteria andQdIdIsNull() {
            addCriterion("QD_ID is null");
            return (Criteria) this;
        }

        public Criteria andQdIdIsNotNull() {
            addCriterion("QD_ID is not null");
            return (Criteria) this;
        }

        public Criteria andQdIdEqualTo(Integer value) {
            addCriterion("QD_ID =", value, "qdId");
            return (Criteria) this;
        }

        public Criteria andQdIdNotEqualTo(Integer value) {
            addCriterion("QD_ID <>", value, "qdId");
            return (Criteria) this;
        }

        public Criteria andQdIdGreaterThan(Integer value) {
            addCriterion("QD_ID >", value, "qdId");
            return (Criteria) this;
        }

        public Criteria andQdIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("QD_ID >=", value, "qdId");
            return (Criteria) this;
        }

        public Criteria andQdIdLessThan(Integer value) {
            addCriterion("QD_ID <", value, "qdId");
            return (Criteria) this;
        }

        public Criteria andQdIdLessThanOrEqualTo(Integer value) {
            addCriterion("QD_ID <=", value, "qdId");
            return (Criteria) this;
        }

        public Criteria andQdIdIn(List<Integer> values) {
            addCriterion("QD_ID in", values, "qdId");
            return (Criteria) this;
        }

        public Criteria andQdIdNotIn(List<Integer> values) {
            addCriterion("QD_ID not in", values, "qdId");
            return (Criteria) this;
        }

        public Criteria andQdIdBetween(Integer value1, Integer value2) {
            addCriterion("QD_ID between", value1, value2, "qdId");
            return (Criteria) this;
        }

        public Criteria andQdIdNotBetween(Integer value1, Integer value2) {
            addCriterion("QD_ID not between", value1, value2, "qdId");
            return (Criteria) this;
        }

        public Criteria andQueryDimCodeIsNull() {
            addCriterion("QUERY_DIM_CODE is null");
            return (Criteria) this;
        }

        public Criteria andQueryDimCodeIsNotNull() {
            addCriterion("QUERY_DIM_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andQueryDimCodeEqualTo(String value) {
            addCriterion("QUERY_DIM_CODE =", value, "queryDimCode");
            return (Criteria) this;
        }

        public Criteria andQueryDimCodeNotEqualTo(String value) {
            addCriterion("QUERY_DIM_CODE <>", value, "queryDimCode");
            return (Criteria) this;
        }

        public Criteria andQueryDimCodeGreaterThan(String value) {
            addCriterion("QUERY_DIM_CODE >", value, "queryDimCode");
            return (Criteria) this;
        }

        public Criteria andQueryDimCodeGreaterThanOrEqualTo(String value) {
            addCriterion("QUERY_DIM_CODE >=", value, "queryDimCode");
            return (Criteria) this;
        }

        public Criteria andQueryDimCodeLessThan(String value) {
            addCriterion("QUERY_DIM_CODE <", value, "queryDimCode");
            return (Criteria) this;
        }

        public Criteria andQueryDimCodeLessThanOrEqualTo(String value) {
            addCriterion("QUERY_DIM_CODE <=", value, "queryDimCode");
            return (Criteria) this;
        }

        public Criteria andQueryDimCodeLike(String value) {
            addCriterion("QUERY_DIM_CODE like", value, "queryDimCode");
            return (Criteria) this;
        }

        public Criteria andQueryDimCodeNotLike(String value) {
            addCriterion("QUERY_DIM_CODE not like", value, "queryDimCode");
            return (Criteria) this;
        }

        public Criteria andQueryDimCodeIn(List<String> values) {
            addCriterion("QUERY_DIM_CODE in", values, "queryDimCode");
            return (Criteria) this;
        }

        public Criteria andQueryDimCodeNotIn(List<String> values) {
            addCriterion("QUERY_DIM_CODE not in", values, "queryDimCode");
            return (Criteria) this;
        }

        public Criteria andQueryDimCodeBetween(String value1, String value2) {
            addCriterion("QUERY_DIM_CODE between", value1, value2, "queryDimCode");
            return (Criteria) this;
        }

        public Criteria andQueryDimCodeNotBetween(String value1, String value2) {
            addCriterion("QUERY_DIM_CODE not between", value1, value2, "queryDimCode");
            return (Criteria) this;
        }

        public Criteria andQueryDimNameIsNull() {
            addCriterion("QUERY_DIM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andQueryDimNameIsNotNull() {
            addCriterion("QUERY_DIM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andQueryDimNameEqualTo(String value) {
            addCriterion("QUERY_DIM_NAME =", value, "queryDimName");
            return (Criteria) this;
        }

        public Criteria andQueryDimNameNotEqualTo(String value) {
            addCriterion("QUERY_DIM_NAME <>", value, "queryDimName");
            return (Criteria) this;
        }

        public Criteria andQueryDimNameGreaterThan(String value) {
            addCriterion("QUERY_DIM_NAME >", value, "queryDimName");
            return (Criteria) this;
        }

        public Criteria andQueryDimNameGreaterThanOrEqualTo(String value) {
            addCriterion("QUERY_DIM_NAME >=", value, "queryDimName");
            return (Criteria) this;
        }

        public Criteria andQueryDimNameLessThan(String value) {
            addCriterion("QUERY_DIM_NAME <", value, "queryDimName");
            return (Criteria) this;
        }

        public Criteria andQueryDimNameLessThanOrEqualTo(String value) {
            addCriterion("QUERY_DIM_NAME <=", value, "queryDimName");
            return (Criteria) this;
        }

        public Criteria andQueryDimNameLike(String value) {
            addCriterion("QUERY_DIM_NAME like", value, "queryDimName");
            return (Criteria) this;
        }

        public Criteria andQueryDimNameNotLike(String value) {
            addCriterion("QUERY_DIM_NAME not like", value, "queryDimName");
            return (Criteria) this;
        }

        public Criteria andQueryDimNameIn(List<String> values) {
            addCriterion("QUERY_DIM_NAME in", values, "queryDimName");
            return (Criteria) this;
        }

        public Criteria andQueryDimNameNotIn(List<String> values) {
            addCriterion("QUERY_DIM_NAME not in", values, "queryDimName");
            return (Criteria) this;
        }

        public Criteria andQueryDimNameBetween(String value1, String value2) {
            addCriterion("QUERY_DIM_NAME between", value1, value2, "queryDimName");
            return (Criteria) this;
        }

        public Criteria andQueryDimNameNotBetween(String value1, String value2) {
            addCriterion("QUERY_DIM_NAME not between", value1, value2, "queryDimName");
            return (Criteria) this;
        }

        public Criteria andIsAuthIsNull() {
            addCriterion("IS_AUTH is null");
            return (Criteria) this;
        }

        public Criteria andIsAuthIsNotNull() {
            addCriterion("IS_AUTH is not null");
            return (Criteria) this;
        }

        public Criteria andIsAuthEqualTo(String value) {
            addCriterion("IS_AUTH =", value, "isAuth");
            return (Criteria) this;
        }

        public Criteria andIsAuthNotEqualTo(String value) {
            addCriterion("IS_AUTH <>", value, "isAuth");
            return (Criteria) this;
        }

        public Criteria andIsAuthGreaterThan(String value) {
            addCriterion("IS_AUTH >", value, "isAuth");
            return (Criteria) this;
        }

        public Criteria andIsAuthGreaterThanOrEqualTo(String value) {
            addCriterion("IS_AUTH >=", value, "isAuth");
            return (Criteria) this;
        }

        public Criteria andIsAuthLessThan(String value) {
            addCriterion("IS_AUTH <", value, "isAuth");
            return (Criteria) this;
        }

        public Criteria andIsAuthLessThanOrEqualTo(String value) {
            addCriterion("IS_AUTH <=", value, "isAuth");
            return (Criteria) this;
        }

        public Criteria andIsAuthLike(String value) {
            addCriterion("IS_AUTH like", value, "isAuth");
            return (Criteria) this;
        }

        public Criteria andIsAuthNotLike(String value) {
            addCriterion("IS_AUTH not like", value, "isAuth");
            return (Criteria) this;
        }

        public Criteria andIsAuthIn(List<String> values) {
            addCriterion("IS_AUTH in", values, "isAuth");
            return (Criteria) this;
        }

        public Criteria andIsAuthNotIn(List<String> values) {
            addCriterion("IS_AUTH not in", values, "isAuth");
            return (Criteria) this;
        }

        public Criteria andIsAuthBetween(String value1, String value2) {
            addCriterion("IS_AUTH between", value1, value2, "isAuth");
            return (Criteria) this;
        }

        public Criteria andIsAuthNotBetween(String value1, String value2) {
            addCriterion("IS_AUTH not between", value1, value2, "isAuth");
            return (Criteria) this;
        }

        public Criteria andQueryDimTypeIsNull() {
            addCriterion("QUERY_DIM_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andQueryDimTypeIsNotNull() {
            addCriterion("QUERY_DIM_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andQueryDimTypeEqualTo(String value) {
            addCriterion("QUERY_DIM_TYPE =", value, "queryDimType");
            return (Criteria) this;
        }

        public Criteria andQueryDimTypeNotEqualTo(String value) {
            addCriterion("QUERY_DIM_TYPE <>", value, "queryDimType");
            return (Criteria) this;
        }

        public Criteria andQueryDimTypeGreaterThan(String value) {
            addCriterion("QUERY_DIM_TYPE >", value, "queryDimType");
            return (Criteria) this;
        }

        public Criteria andQueryDimTypeGreaterThanOrEqualTo(String value) {
            addCriterion("QUERY_DIM_TYPE >=", value, "queryDimType");
            return (Criteria) this;
        }

        public Criteria andQueryDimTypeLessThan(String value) {
            addCriterion("QUERY_DIM_TYPE <", value, "queryDimType");
            return (Criteria) this;
        }

        public Criteria andQueryDimTypeLessThanOrEqualTo(String value) {
            addCriterion("QUERY_DIM_TYPE <=", value, "queryDimType");
            return (Criteria) this;
        }

        public Criteria andQueryDimTypeLike(String value) {
            addCriterion("QUERY_DIM_TYPE like", value, "queryDimType");
            return (Criteria) this;
        }

        public Criteria andQueryDimTypeNotLike(String value) {
            addCriterion("QUERY_DIM_TYPE not like", value, "queryDimType");
            return (Criteria) this;
        }

        public Criteria andQueryDimTypeIn(List<String> values) {
            addCriterion("QUERY_DIM_TYPE in", values, "queryDimType");
            return (Criteria) this;
        }

        public Criteria andQueryDimTypeNotIn(List<String> values) {
            addCriterion("QUERY_DIM_TYPE not in", values, "queryDimType");
            return (Criteria) this;
        }

        public Criteria andQueryDimTypeBetween(String value1, String value2) {
            addCriterion("QUERY_DIM_TYPE between", value1, value2, "queryDimType");
            return (Criteria) this;
        }

        public Criteria andQueryDimTypeNotBetween(String value1, String value2) {
            addCriterion("QUERY_DIM_TYPE not between", value1, value2, "queryDimType");
            return (Criteria) this;
        }

        public Criteria andQueryDimShowTypeIsNull() {
            addCriterion("QUERY_DIM_SHOW_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andQueryDimShowTypeIsNotNull() {
            addCriterion("QUERY_DIM_SHOW_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andQueryDimShowTypeEqualTo(String value) {
            addCriterion("QUERY_DIM_SHOW_TYPE =", value, "queryDimShowType");
            return (Criteria) this;
        }

        public Criteria andQueryDimShowTypeNotEqualTo(String value) {
            addCriterion("QUERY_DIM_SHOW_TYPE <>", value, "queryDimShowType");
            return (Criteria) this;
        }

        public Criteria andQueryDimShowTypeGreaterThan(String value) {
            addCriterion("QUERY_DIM_SHOW_TYPE >", value, "queryDimShowType");
            return (Criteria) this;
        }

        public Criteria andQueryDimShowTypeGreaterThanOrEqualTo(String value) {
            addCriterion("QUERY_DIM_SHOW_TYPE >=", value, "queryDimShowType");
            return (Criteria) this;
        }

        public Criteria andQueryDimShowTypeLessThan(String value) {
            addCriterion("QUERY_DIM_SHOW_TYPE <", value, "queryDimShowType");
            return (Criteria) this;
        }

        public Criteria andQueryDimShowTypeLessThanOrEqualTo(String value) {
            addCriterion("QUERY_DIM_SHOW_TYPE <=", value, "queryDimShowType");
            return (Criteria) this;
        }

        public Criteria andQueryDimShowTypeLike(String value) {
            addCriterion("QUERY_DIM_SHOW_TYPE like", value, "queryDimShowType");
            return (Criteria) this;
        }

        public Criteria andQueryDimShowTypeNotLike(String value) {
            addCriterion("QUERY_DIM_SHOW_TYPE not like", value, "queryDimShowType");
            return (Criteria) this;
        }

        public Criteria andQueryDimShowTypeIn(List<String> values) {
            addCriterion("QUERY_DIM_SHOW_TYPE in", values, "queryDimShowType");
            return (Criteria) this;
        }

        public Criteria andQueryDimShowTypeNotIn(List<String> values) {
            addCriterion("QUERY_DIM_SHOW_TYPE not in", values, "queryDimShowType");
            return (Criteria) this;
        }

        public Criteria andQueryDimShowTypeBetween(String value1, String value2) {
            addCriterion("QUERY_DIM_SHOW_TYPE between", value1, value2, "queryDimShowType");
            return (Criteria) this;
        }

        public Criteria andQueryDimShowTypeNotBetween(String value1, String value2) {
            addCriterion("QUERY_DIM_SHOW_TYPE not between", value1, value2, "queryDimShowType");
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