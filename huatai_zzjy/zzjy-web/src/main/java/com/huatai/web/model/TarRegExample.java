package com.huatai.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TarRegExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TarRegExample() {
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

        public Criteria andTrIdIsNull() {
            addCriterion("TR_ID is null");
            return (Criteria) this;
        }

        public Criteria andTrIdIsNotNull() {
            addCriterion("TR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTrIdEqualTo(Integer value) {
            addCriterion("TR_ID =", value, "trId");
            return (Criteria) this;
        }

        public Criteria andTrIdNotEqualTo(Integer value) {
            addCriterion("TR_ID <>", value, "trId");
            return (Criteria) this;
        }

        public Criteria andTrIdGreaterThan(Integer value) {
            addCriterion("TR_ID >", value, "trId");
            return (Criteria) this;
        }

        public Criteria andTrIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("TR_ID >=", value, "trId");
            return (Criteria) this;
        }

        public Criteria andTrIdLessThan(Integer value) {
            addCriterion("TR_ID <", value, "trId");
            return (Criteria) this;
        }

        public Criteria andTrIdLessThanOrEqualTo(Integer value) {
            addCriterion("TR_ID <=", value, "trId");
            return (Criteria) this;
        }

        public Criteria andTrIdIn(List<Integer> values) {
            addCriterion("TR_ID in", values, "trId");
            return (Criteria) this;
        }

        public Criteria andTrIdNotIn(List<Integer> values) {
            addCriterion("TR_ID not in", values, "trId");
            return (Criteria) this;
        }

        public Criteria andTrIdBetween(Integer value1, Integer value2) {
            addCriterion("TR_ID between", value1, value2, "trId");
            return (Criteria) this;
        }

        public Criteria andTrIdNotBetween(Integer value1, Integer value2) {
            addCriterion("TR_ID not between", value1, value2, "trId");
            return (Criteria) this;
        }

        public Criteria andRegIdIsNull() {
            addCriterion("REG_ID is null");
            return (Criteria) this;
        }

        public Criteria andRegIdIsNotNull() {
            addCriterion("REG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRegIdEqualTo(Integer value) {
            addCriterion("REG_ID =", value, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdNotEqualTo(Integer value) {
            addCriterion("REG_ID <>", value, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdGreaterThan(Integer value) {
            addCriterion("REG_ID >", value, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("REG_ID >=", value, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdLessThan(Integer value) {
            addCriterion("REG_ID <", value, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdLessThanOrEqualTo(Integer value) {
            addCriterion("REG_ID <=", value, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdIn(List<Integer> values) {
            addCriterion("REG_ID in", values, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdNotIn(List<Integer> values) {
            addCriterion("REG_ID not in", values, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdBetween(Integer value1, Integer value2) {
            addCriterion("REG_ID between", value1, value2, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdNotBetween(Integer value1, Integer value2) {
            addCriterion("REG_ID not between", value1, value2, "regId");
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

        public Criteria andSubCodeIsNull() {
            addCriterion("SUB_CODE is null");
            return (Criteria) this;
        }

        public Criteria andSubCodeIsNotNull() {
            addCriterion("SUB_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andSubCodeEqualTo(String value) {
            addCriterion("SUB_CODE =", value, "subCode");
            return (Criteria) this;
        }

        public Criteria andSubCodeNotEqualTo(String value) {
            addCriterion("SUB_CODE <>", value, "subCode");
            return (Criteria) this;
        }

        public Criteria andSubCodeGreaterThan(String value) {
            addCriterion("SUB_CODE >", value, "subCode");
            return (Criteria) this;
        }

        public Criteria andSubCodeGreaterThanOrEqualTo(String value) {
            addCriterion("SUB_CODE >=", value, "subCode");
            return (Criteria) this;
        }

        public Criteria andSubCodeLessThan(String value) {
            addCriterion("SUB_CODE <", value, "subCode");
            return (Criteria) this;
        }

        public Criteria andSubCodeLessThanOrEqualTo(String value) {
            addCriterion("SUB_CODE <=", value, "subCode");
            return (Criteria) this;
        }

        public Criteria andSubCodeLike(String value) {
            addCriterion("SUB_CODE like", value, "subCode");
            return (Criteria) this;
        }

        public Criteria andSubCodeNotLike(String value) {
            addCriterion("SUB_CODE not like", value, "subCode");
            return (Criteria) this;
        }

        public Criteria andSubCodeIn(List<String> values) {
            addCriterion("SUB_CODE in", values, "subCode");
            return (Criteria) this;
        }

        public Criteria andSubCodeNotIn(List<String> values) {
            addCriterion("SUB_CODE not in", values, "subCode");
            return (Criteria) this;
        }

        public Criteria andSubCodeBetween(String value1, String value2) {
            addCriterion("SUB_CODE between", value1, value2, "subCode");
            return (Criteria) this;
        }

        public Criteria andSubCodeNotBetween(String value1, String value2) {
            addCriterion("SUB_CODE not between", value1, value2, "subCode");
            return (Criteria) this;
        }

        public Criteria andDimTypeIsNull() {
            addCriterion("DIM_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andDimTypeIsNotNull() {
            addCriterion("DIM_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andDimTypeEqualTo(String value) {
            addCriterion("DIM_TYPE =", value, "dimType");
            return (Criteria) this;
        }

        public Criteria andDimTypeNotEqualTo(String value) {
            addCriterion("DIM_TYPE <>", value, "dimType");
            return (Criteria) this;
        }

        public Criteria andDimTypeGreaterThan(String value) {
            addCriterion("DIM_TYPE >", value, "dimType");
            return (Criteria) this;
        }

        public Criteria andDimTypeGreaterThanOrEqualTo(String value) {
            addCriterion("DIM_TYPE >=", value, "dimType");
            return (Criteria) this;
        }

        public Criteria andDimTypeLessThan(String value) {
            addCriterion("DIM_TYPE <", value, "dimType");
            return (Criteria) this;
        }

        public Criteria andDimTypeLessThanOrEqualTo(String value) {
            addCriterion("DIM_TYPE <=", value, "dimType");
            return (Criteria) this;
        }

        public Criteria andDimTypeLike(String value) {
            addCriterion("DIM_TYPE like", value, "dimType");
            return (Criteria) this;
        }

        public Criteria andDimTypeNotLike(String value) {
            addCriterion("DIM_TYPE not like", value, "dimType");
            return (Criteria) this;
        }

        public Criteria andDimTypeIn(List<String> values) {
            addCriterion("DIM_TYPE in", values, "dimType");
            return (Criteria) this;
        }

        public Criteria andDimTypeNotIn(List<String> values) {
            addCriterion("DIM_TYPE not in", values, "dimType");
            return (Criteria) this;
        }

        public Criteria andDimTypeBetween(String value1, String value2) {
            addCriterion("DIM_TYPE between", value1, value2, "dimType");
            return (Criteria) this;
        }

        public Criteria andDimTypeNotBetween(String value1, String value2) {
            addCriterion("DIM_TYPE not between", value1, value2, "dimType");
            return (Criteria) this;
        }

        public Criteria andGraphTypeIsNull() {
            addCriterion("GRAPH_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andGraphTypeIsNotNull() {
            addCriterion("GRAPH_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andGraphTypeEqualTo(String value) {
            addCriterion("GRAPH_TYPE =", value, "graphType");
            return (Criteria) this;
        }

        public Criteria andGraphTypeNotEqualTo(String value) {
            addCriterion("GRAPH_TYPE <>", value, "graphType");
            return (Criteria) this;
        }

        public Criteria andGraphTypeGreaterThan(String value) {
            addCriterion("GRAPH_TYPE >", value, "graphType");
            return (Criteria) this;
        }

        public Criteria andGraphTypeGreaterThanOrEqualTo(String value) {
            addCriterion("GRAPH_TYPE >=", value, "graphType");
            return (Criteria) this;
        }

        public Criteria andGraphTypeLessThan(String value) {
            addCriterion("GRAPH_TYPE <", value, "graphType");
            return (Criteria) this;
        }

        public Criteria andGraphTypeLessThanOrEqualTo(String value) {
            addCriterion("GRAPH_TYPE <=", value, "graphType");
            return (Criteria) this;
        }

        public Criteria andGraphTypeLike(String value) {
            addCriterion("GRAPH_TYPE like", value, "graphType");
            return (Criteria) this;
        }

        public Criteria andGraphTypeNotLike(String value) {
            addCriterion("GRAPH_TYPE not like", value, "graphType");
            return (Criteria) this;
        }

        public Criteria andGraphTypeIn(List<String> values) {
            addCriterion("GRAPH_TYPE in", values, "graphType");
            return (Criteria) this;
        }

        public Criteria andGraphTypeNotIn(List<String> values) {
            addCriterion("GRAPH_TYPE not in", values, "graphType");
            return (Criteria) this;
        }

        public Criteria andGraphTypeBetween(String value1, String value2) {
            addCriterion("GRAPH_TYPE between", value1, value2, "graphType");
            return (Criteria) this;
        }

        public Criteria andGraphTypeNotBetween(String value1, String value2) {
            addCriterion("GRAPH_TYPE not between", value1, value2, "graphType");
            return (Criteria) this;
        }

        public Criteria andGraphTitleIsNull() {
            addCriterion("GRAPH_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andGraphTitleIsNotNull() {
            addCriterion("GRAPH_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andGraphTitleEqualTo(String value) {
            addCriterion("GRAPH_TITLE =", value, "graphTitle");
            return (Criteria) this;
        }

        public Criteria andGraphTitleNotEqualTo(String value) {
            addCriterion("GRAPH_TITLE <>", value, "graphTitle");
            return (Criteria) this;
        }

        public Criteria andGraphTitleGreaterThan(String value) {
            addCriterion("GRAPH_TITLE >", value, "graphTitle");
            return (Criteria) this;
        }

        public Criteria andGraphTitleGreaterThanOrEqualTo(String value) {
            addCriterion("GRAPH_TITLE >=", value, "graphTitle");
            return (Criteria) this;
        }

        public Criteria andGraphTitleLessThan(String value) {
            addCriterion("GRAPH_TITLE <", value, "graphTitle");
            return (Criteria) this;
        }

        public Criteria andGraphTitleLessThanOrEqualTo(String value) {
            addCriterion("GRAPH_TITLE <=", value, "graphTitle");
            return (Criteria) this;
        }

        public Criteria andGraphTitleLike(String value) {
            addCriterion("GRAPH_TITLE like", value, "graphTitle");
            return (Criteria) this;
        }

        public Criteria andGraphTitleNotLike(String value) {
            addCriterion("GRAPH_TITLE not like", value, "graphTitle");
            return (Criteria) this;
        }

        public Criteria andGraphTitleIn(List<String> values) {
            addCriterion("GRAPH_TITLE in", values, "graphTitle");
            return (Criteria) this;
        }

        public Criteria andGraphTitleNotIn(List<String> values) {
            addCriterion("GRAPH_TITLE not in", values, "graphTitle");
            return (Criteria) this;
        }

        public Criteria andGraphTitleBetween(String value1, String value2) {
            addCriterion("GRAPH_TITLE between", value1, value2, "graphTitle");
            return (Criteria) this;
        }

        public Criteria andGraphTitleNotBetween(String value1, String value2) {
            addCriterion("GRAPH_TITLE not between", value1, value2, "graphTitle");
            return (Criteria) this;
        }

        public Criteria andXNameIsNull() {
            addCriterion("X_NAME is null");
            return (Criteria) this;
        }

        public Criteria andXNameIsNotNull() {
            addCriterion("X_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andXNameEqualTo(String value) {
            addCriterion("X_NAME =", value, "xName");
            return (Criteria) this;
        }

        public Criteria andXNameNotEqualTo(String value) {
            addCriterion("X_NAME <>", value, "xName");
            return (Criteria) this;
        }

        public Criteria andXNameGreaterThan(String value) {
            addCriterion("X_NAME >", value, "xName");
            return (Criteria) this;
        }

        public Criteria andXNameGreaterThanOrEqualTo(String value) {
            addCriterion("X_NAME >=", value, "xName");
            return (Criteria) this;
        }

        public Criteria andXNameLessThan(String value) {
            addCriterion("X_NAME <", value, "xName");
            return (Criteria) this;
        }

        public Criteria andXNameLessThanOrEqualTo(String value) {
            addCriterion("X_NAME <=", value, "xName");
            return (Criteria) this;
        }

        public Criteria andXNameLike(String value) {
            addCriterion("X_NAME like", value, "xName");
            return (Criteria) this;
        }

        public Criteria andXNameNotLike(String value) {
            addCriterion("X_NAME not like", value, "xName");
            return (Criteria) this;
        }

        public Criteria andXNameIn(List<String> values) {
            addCriterion("X_NAME in", values, "xName");
            return (Criteria) this;
        }

        public Criteria andXNameNotIn(List<String> values) {
            addCriterion("X_NAME not in", values, "xName");
            return (Criteria) this;
        }

        public Criteria andXNameBetween(String value1, String value2) {
            addCriterion("X_NAME between", value1, value2, "xName");
            return (Criteria) this;
        }

        public Criteria andXNameNotBetween(String value1, String value2) {
            addCriterion("X_NAME not between", value1, value2, "xName");
            return (Criteria) this;
        }

        public Criteria andYNameIsNull() {
            addCriterion("Y_NAME is null");
            return (Criteria) this;
        }

        public Criteria andYNameIsNotNull() {
            addCriterion("Y_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andYNameEqualTo(String value) {
            addCriterion("Y_NAME =", value, "yName");
            return (Criteria) this;
        }

        public Criteria andYNameNotEqualTo(String value) {
            addCriterion("Y_NAME <>", value, "yName");
            return (Criteria) this;
        }

        public Criteria andYNameGreaterThan(String value) {
            addCriterion("Y_NAME >", value, "yName");
            return (Criteria) this;
        }

        public Criteria andYNameGreaterThanOrEqualTo(String value) {
            addCriterion("Y_NAME >=", value, "yName");
            return (Criteria) this;
        }

        public Criteria andYNameLessThan(String value) {
            addCriterion("Y_NAME <", value, "yName");
            return (Criteria) this;
        }

        public Criteria andYNameLessThanOrEqualTo(String value) {
            addCriterion("Y_NAME <=", value, "yName");
            return (Criteria) this;
        }

        public Criteria andYNameLike(String value) {
            addCriterion("Y_NAME like", value, "yName");
            return (Criteria) this;
        }

        public Criteria andYNameNotLike(String value) {
            addCriterion("Y_NAME not like", value, "yName");
            return (Criteria) this;
        }

        public Criteria andYNameIn(List<String> values) {
            addCriterion("Y_NAME in", values, "yName");
            return (Criteria) this;
        }

        public Criteria andYNameNotIn(List<String> values) {
            addCriterion("Y_NAME not in", values, "yName");
            return (Criteria) this;
        }

        public Criteria andYNameBetween(String value1, String value2) {
            addCriterion("Y_NAME between", value1, value2, "yName");
            return (Criteria) this;
        }

        public Criteria andYNameNotBetween(String value1, String value2) {
            addCriterion("Y_NAME not between", value1, value2, "yName");
            return (Criteria) this;
        }

        public Criteria andUnitIsNull() {
            addCriterion("UNIT is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andUnitEqualTo(String value) {
            addCriterion("UNIT =", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotEqualTo(String value) {
            addCriterion("UNIT <>", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThan(String value) {
            addCriterion("UNIT >", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThanOrEqualTo(String value) {
            addCriterion("UNIT >=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThan(String value) {
            addCriterion("UNIT <", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThanOrEqualTo(String value) {
            addCriterion("UNIT <=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLike(String value) {
            addCriterion("UNIT like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotLike(String value) {
            addCriterion("UNIT not like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitIn(List<String> values) {
            addCriterion("UNIT in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotIn(List<String> values) {
            addCriterion("UNIT not in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitBetween(String value1, String value2) {
            addCriterion("UNIT between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotBetween(String value1, String value2) {
            addCriterion("UNIT not between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andRoleOrgTypeIsNull() {
            addCriterion("ROLE_ORG_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andRoleOrgTypeIsNotNull() {
            addCriterion("ROLE_ORG_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andRoleOrgTypeEqualTo(String value) {
            addCriterion("ROLE_ORG_TYPE =", value, "roleOrgType");
            return (Criteria) this;
        }

        public Criteria andRoleOrgTypeNotEqualTo(String value) {
            addCriterion("ROLE_ORG_TYPE <>", value, "roleOrgType");
            return (Criteria) this;
        }

        public Criteria andRoleOrgTypeGreaterThan(String value) {
            addCriterion("ROLE_ORG_TYPE >", value, "roleOrgType");
            return (Criteria) this;
        }

        public Criteria andRoleOrgTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ROLE_ORG_TYPE >=", value, "roleOrgType");
            return (Criteria) this;
        }

        public Criteria andRoleOrgTypeLessThan(String value) {
            addCriterion("ROLE_ORG_TYPE <", value, "roleOrgType");
            return (Criteria) this;
        }

        public Criteria andRoleOrgTypeLessThanOrEqualTo(String value) {
            addCriterion("ROLE_ORG_TYPE <=", value, "roleOrgType");
            return (Criteria) this;
        }

        public Criteria andRoleOrgTypeLike(String value) {
            addCriterion("ROLE_ORG_TYPE like", value, "roleOrgType");
            return (Criteria) this;
        }

        public Criteria andRoleOrgTypeNotLike(String value) {
            addCriterion("ROLE_ORG_TYPE not like", value, "roleOrgType");
            return (Criteria) this;
        }

        public Criteria andRoleOrgTypeIn(List<String> values) {
            addCriterion("ROLE_ORG_TYPE in", values, "roleOrgType");
            return (Criteria) this;
        }

        public Criteria andRoleOrgTypeNotIn(List<String> values) {
            addCriterion("ROLE_ORG_TYPE not in", values, "roleOrgType");
            return (Criteria) this;
        }

        public Criteria andRoleOrgTypeBetween(String value1, String value2) {
            addCriterion("ROLE_ORG_TYPE between", value1, value2, "roleOrgType");
            return (Criteria) this;
        }

        public Criteria andRoleOrgTypeNotBetween(String value1, String value2) {
            addCriterion("ROLE_ORG_TYPE not between", value1, value2, "roleOrgType");
            return (Criteria) this;
        }

        public Criteria andIsRankIsNull() {
            addCriterion("IS_RANK is null");
            return (Criteria) this;
        }

        public Criteria andIsRankIsNotNull() {
            addCriterion("IS_RANK is not null");
            return (Criteria) this;
        }

        public Criteria andIsRankEqualTo(String value) {
            addCriterion("IS_RANK =", value, "isRank");
            return (Criteria) this;
        }

        public Criteria andIsRankNotEqualTo(String value) {
            addCriterion("IS_RANK <>", value, "isRank");
            return (Criteria) this;
        }

        public Criteria andIsRankGreaterThan(String value) {
            addCriterion("IS_RANK >", value, "isRank");
            return (Criteria) this;
        }

        public Criteria andIsRankGreaterThanOrEqualTo(String value) {
            addCriterion("IS_RANK >=", value, "isRank");
            return (Criteria) this;
        }

        public Criteria andIsRankLessThan(String value) {
            addCriterion("IS_RANK <", value, "isRank");
            return (Criteria) this;
        }

        public Criteria andIsRankLessThanOrEqualTo(String value) {
            addCriterion("IS_RANK <=", value, "isRank");
            return (Criteria) this;
        }

        public Criteria andIsRankLike(String value) {
            addCriterion("IS_RANK like", value, "isRank");
            return (Criteria) this;
        }

        public Criteria andIsRankNotLike(String value) {
            addCriterion("IS_RANK not like", value, "isRank");
            return (Criteria) this;
        }

        public Criteria andIsRankIn(List<String> values) {
            addCriterion("IS_RANK in", values, "isRank");
            return (Criteria) this;
        }

        public Criteria andIsRankNotIn(List<String> values) {
            addCriterion("IS_RANK not in", values, "isRank");
            return (Criteria) this;
        }

        public Criteria andIsRankBetween(String value1, String value2) {
            addCriterion("IS_RANK between", value1, value2, "isRank");
            return (Criteria) this;
        }

        public Criteria andIsRankNotBetween(String value1, String value2) {
            addCriterion("IS_RANK not between", value1, value2, "isRank");
            return (Criteria) this;
        }

        public Criteria andRankOrgTypeIsNull() {
            addCriterion("RANK_ORG_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andRankOrgTypeIsNotNull() {
            addCriterion("RANK_ORG_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andRankOrgTypeEqualTo(String value) {
            addCriterion("RANK_ORG_TYPE =", value, "rankOrgType");
            return (Criteria) this;
        }

        public Criteria andRankOrgTypeNotEqualTo(String value) {
            addCriterion("RANK_ORG_TYPE <>", value, "rankOrgType");
            return (Criteria) this;
        }

        public Criteria andRankOrgTypeGreaterThan(String value) {
            addCriterion("RANK_ORG_TYPE >", value, "rankOrgType");
            return (Criteria) this;
        }

        public Criteria andRankOrgTypeGreaterThanOrEqualTo(String value) {
            addCriterion("RANK_ORG_TYPE >=", value, "rankOrgType");
            return (Criteria) this;
        }

        public Criteria andRankOrgTypeLessThan(String value) {
            addCriterion("RANK_ORG_TYPE <", value, "rankOrgType");
            return (Criteria) this;
        }

        public Criteria andRankOrgTypeLessThanOrEqualTo(String value) {
            addCriterion("RANK_ORG_TYPE <=", value, "rankOrgType");
            return (Criteria) this;
        }

        public Criteria andRankOrgTypeLike(String value) {
            addCriterion("RANK_ORG_TYPE like", value, "rankOrgType");
            return (Criteria) this;
        }

        public Criteria andRankOrgTypeNotLike(String value) {
            addCriterion("RANK_ORG_TYPE not like", value, "rankOrgType");
            return (Criteria) this;
        }

        public Criteria andRankOrgTypeIn(List<String> values) {
            addCriterion("RANK_ORG_TYPE in", values, "rankOrgType");
            return (Criteria) this;
        }

        public Criteria andRankOrgTypeNotIn(List<String> values) {
            addCriterion("RANK_ORG_TYPE not in", values, "rankOrgType");
            return (Criteria) this;
        }

        public Criteria andRankOrgTypeBetween(String value1, String value2) {
            addCriterion("RANK_ORG_TYPE between", value1, value2, "rankOrgType");
            return (Criteria) this;
        }

        public Criteria andRankOrgTypeNotBetween(String value1, String value2) {
            addCriterion("RANK_ORG_TYPE not between", value1, value2, "rankOrgType");
            return (Criteria) this;
        }

        public Criteria andIsDisplayIsNull() {
            addCriterion("IS_DISPLAY is null");
            return (Criteria) this;
        }

        public Criteria andIsDisplayIsNotNull() {
            addCriterion("IS_DISPLAY is not null");
            return (Criteria) this;
        }

        public Criteria andIsDisplayEqualTo(String value) {
            addCriterion("IS_DISPLAY =", value, "isDisplay");
            return (Criteria) this;
        }

        public Criteria andIsDisplayNotEqualTo(String value) {
            addCriterion("IS_DISPLAY <>", value, "isDisplay");
            return (Criteria) this;
        }

        public Criteria andIsDisplayGreaterThan(String value) {
            addCriterion("IS_DISPLAY >", value, "isDisplay");
            return (Criteria) this;
        }

        public Criteria andIsDisplayGreaterThanOrEqualTo(String value) {
            addCriterion("IS_DISPLAY >=", value, "isDisplay");
            return (Criteria) this;
        }

        public Criteria andIsDisplayLessThan(String value) {
            addCriterion("IS_DISPLAY <", value, "isDisplay");
            return (Criteria) this;
        }

        public Criteria andIsDisplayLessThanOrEqualTo(String value) {
            addCriterion("IS_DISPLAY <=", value, "isDisplay");
            return (Criteria) this;
        }

        public Criteria andIsDisplayLike(String value) {
            addCriterion("IS_DISPLAY like", value, "isDisplay");
            return (Criteria) this;
        }

        public Criteria andIsDisplayNotLike(String value) {
            addCriterion("IS_DISPLAY not like", value, "isDisplay");
            return (Criteria) this;
        }

        public Criteria andIsDisplayIn(List<String> values) {
            addCriterion("IS_DISPLAY in", values, "isDisplay");
            return (Criteria) this;
        }

        public Criteria andIsDisplayNotIn(List<String> values) {
            addCriterion("IS_DISPLAY not in", values, "isDisplay");
            return (Criteria) this;
        }

        public Criteria andIsDisplayBetween(String value1, String value2) {
            addCriterion("IS_DISPLAY between", value1, value2, "isDisplay");
            return (Criteria) this;
        }

        public Criteria andIsDisplayNotBetween(String value1, String value2) {
            addCriterion("IS_DISPLAY not between", value1, value2, "isDisplay");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("SORT is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("SORT is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("SORT =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("SORT <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("SORT >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("SORT >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("SORT <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("SORT <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("SORT in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("SORT not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("SORT between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("SORT not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andPwdIsNull() {
            addCriterion("PWD is null");
            return (Criteria) this;
        }

        public Criteria andPwdIsNotNull() {
            addCriterion("PWD is not null");
            return (Criteria) this;
        }

        public Criteria andPwdEqualTo(String value) {
            addCriterion("PWD =", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotEqualTo(String value) {
            addCriterion("PWD <>", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdGreaterThan(String value) {
            addCriterion("PWD >", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdGreaterThanOrEqualTo(String value) {
            addCriterion("PWD >=", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLessThan(String value) {
            addCriterion("PWD <", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLessThanOrEqualTo(String value) {
            addCriterion("PWD <=", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLike(String value) {
            addCriterion("PWD like", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotLike(String value) {
            addCriterion("PWD not like", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdIn(List<String> values) {
            addCriterion("PWD in", values, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotIn(List<String> values) {
            addCriterion("PWD not in", values, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdBetween(String value1, String value2) {
            addCriterion("PWD between", value1, value2, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotBetween(String value1, String value2) {
            addCriterion("PWD not between", value1, value2, "pwd");
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