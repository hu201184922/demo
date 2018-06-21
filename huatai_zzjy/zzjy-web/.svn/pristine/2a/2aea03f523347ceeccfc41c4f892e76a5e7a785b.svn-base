package com.huatai.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BillExample() {
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

        public Criteria andBillIdIsNull() {
            addCriterion("BILL_ID is null");
            return (Criteria) this;
        }

        public Criteria andBillIdIsNotNull() {
            addCriterion("BILL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBillIdEqualTo(Integer value) {
            addCriterion("BILL_ID =", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdNotEqualTo(Integer value) {
            addCriterion("BILL_ID <>", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdGreaterThan(Integer value) {
            addCriterion("BILL_ID >", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("BILL_ID >=", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdLessThan(Integer value) {
            addCriterion("BILL_ID <", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdLessThanOrEqualTo(Integer value) {
            addCriterion("BILL_ID <=", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdIn(List<Integer> values) {
            addCriterion("BILL_ID in", values, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdNotIn(List<Integer> values) {
            addCriterion("BILL_ID not in", values, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdBetween(Integer value1, Integer value2) {
            addCriterion("BILL_ID between", value1, value2, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdNotBetween(Integer value1, Integer value2) {
            addCriterion("BILL_ID not between", value1, value2, "billId");
            return (Criteria) this;
        }

        public Criteria andBillNameIsNull() {
            addCriterion("BILL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBillNameIsNotNull() {
            addCriterion("BILL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBillNameEqualTo(String value) {
            addCriterion("BILL_NAME =", value, "billName");
            return (Criteria) this;
        }

        public Criteria andBillNameNotEqualTo(String value) {
            addCriterion("BILL_NAME <>", value, "billName");
            return (Criteria) this;
        }

        public Criteria andBillNameGreaterThan(String value) {
            addCriterion("BILL_NAME >", value, "billName");
            return (Criteria) this;
        }

        public Criteria andBillNameGreaterThanOrEqualTo(String value) {
            addCriterion("BILL_NAME >=", value, "billName");
            return (Criteria) this;
        }

        public Criteria andBillNameLessThan(String value) {
            addCriterion("BILL_NAME <", value, "billName");
            return (Criteria) this;
        }

        public Criteria andBillNameLessThanOrEqualTo(String value) {
            addCriterion("BILL_NAME <=", value, "billName");
            return (Criteria) this;
        }

        public Criteria andBillNameLike(String value) {
            addCriterion("BILL_NAME like", value, "billName");
            return (Criteria) this;
        }

        public Criteria andBillNameNotLike(String value) {
            addCriterion("BILL_NAME not like", value, "billName");
            return (Criteria) this;
        }

        public Criteria andBillNameIn(List<String> values) {
            addCriterion("BILL_NAME in", values, "billName");
            return (Criteria) this;
        }

        public Criteria andBillNameNotIn(List<String> values) {
            addCriterion("BILL_NAME not in", values, "billName");
            return (Criteria) this;
        }

        public Criteria andBillNameBetween(String value1, String value2) {
            addCriterion("BILL_NAME between", value1, value2, "billName");
            return (Criteria) this;
        }

        public Criteria andBillNameNotBetween(String value1, String value2) {
            addCriterion("BILL_NAME not between", value1, value2, "billName");
            return (Criteria) this;
        }

        public Criteria andDeptCodeIsNull() {
            addCriterion("DEPT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andDeptCodeIsNotNull() {
            addCriterion("DEPT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andDeptCodeEqualTo(String value) {
            addCriterion("DEPT_CODE =", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeNotEqualTo(String value) {
            addCriterion("DEPT_CODE <>", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeGreaterThan(String value) {
            addCriterion("DEPT_CODE >", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeGreaterThanOrEqualTo(String value) {
            addCriterion("DEPT_CODE >=", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeLessThan(String value) {
            addCriterion("DEPT_CODE <", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeLessThanOrEqualTo(String value) {
            addCriterion("DEPT_CODE <=", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeLike(String value) {
            addCriterion("DEPT_CODE like", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeNotLike(String value) {
            addCriterion("DEPT_CODE not like", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeIn(List<String> values) {
            addCriterion("DEPT_CODE in", values, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeNotIn(List<String> values) {
            addCriterion("DEPT_CODE not in", values, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeBetween(String value1, String value2) {
            addCriterion("DEPT_CODE between", value1, value2, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeNotBetween(String value1, String value2) {
            addCriterion("DEPT_CODE not between", value1, value2, "deptCode");
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

        public Criteria andBillTypeIsNull() {
            addCriterion("BILL_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andBillTypeIsNotNull() {
            addCriterion("BILL_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andBillTypeEqualTo(String value) {
            addCriterion("BILL_TYPE =", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeNotEqualTo(String value) {
            addCriterion("BILL_TYPE <>", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeGreaterThan(String value) {
            addCriterion("BILL_TYPE >", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeGreaterThanOrEqualTo(String value) {
            addCriterion("BILL_TYPE >=", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeLessThan(String value) {
            addCriterion("BILL_TYPE <", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeLessThanOrEqualTo(String value) {
            addCriterion("BILL_TYPE <=", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeLike(String value) {
            addCriterion("BILL_TYPE like", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeNotLike(String value) {
            addCriterion("BILL_TYPE not like", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeIn(List<String> values) {
            addCriterion("BILL_TYPE in", values, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeNotIn(List<String> values) {
            addCriterion("BILL_TYPE not in", values, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeBetween(String value1, String value2) {
            addCriterion("BILL_TYPE between", value1, value2, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeNotBetween(String value1, String value2) {
            addCriterion("BILL_TYPE not between", value1, value2, "billType");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
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