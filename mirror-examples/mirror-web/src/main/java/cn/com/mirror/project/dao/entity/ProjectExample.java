package cn.com.mirror.project.dao.entity;

import java.util.ArrayList;
import java.util.List;

public class ProjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProjectExample() {
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

        public Criteria andPrjIdIsNull() {
            addCriterion("prj_id is null");
            return (Criteria) this;
        }

        public Criteria andPrjIdIsNotNull() {
            addCriterion("prj_id is not null");
            return (Criteria) this;
        }

        public Criteria andPrjIdEqualTo(String value) {
            addCriterion("prj_id =", value, "prjId");
            return (Criteria) this;
        }

        public Criteria andPrjIdNotEqualTo(String value) {
            addCriterion("prj_id <>", value, "prjId");
            return (Criteria) this;
        }

        public Criteria andPrjIdGreaterThan(String value) {
            addCriterion("prj_id >", value, "prjId");
            return (Criteria) this;
        }

        public Criteria andPrjIdGreaterThanOrEqualTo(String value) {
            addCriterion("prj_id >=", value, "prjId");
            return (Criteria) this;
        }

        public Criteria andPrjIdLessThan(String value) {
            addCriterion("prj_id <", value, "prjId");
            return (Criteria) this;
        }

        public Criteria andPrjIdLessThanOrEqualTo(String value) {
            addCriterion("prj_id <=", value, "prjId");
            return (Criteria) this;
        }

        public Criteria andPrjIdLike(String value) {
            addCriterion("prj_id like", value, "prjId");
            return (Criteria) this;
        }

        public Criteria andPrjIdNotLike(String value) {
            addCriterion("prj_id not like", value, "prjId");
            return (Criteria) this;
        }

        public Criteria andPrjIdIn(List<String> values) {
            addCriterion("prj_id in", values, "prjId");
            return (Criteria) this;
        }

        public Criteria andPrjIdNotIn(List<String> values) {
            addCriterion("prj_id not in", values, "prjId");
            return (Criteria) this;
        }

        public Criteria andPrjIdBetween(String value1, String value2) {
            addCriterion("prj_id between", value1, value2, "prjId");
            return (Criteria) this;
        }

        public Criteria andPrjIdNotBetween(String value1, String value2) {
            addCriterion("prj_id not between", value1, value2, "prjId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andAccessCodeIsNull() {
            addCriterion("access_code is null");
            return (Criteria) this;
        }

        public Criteria andAccessCodeIsNotNull() {
            addCriterion("access_code is not null");
            return (Criteria) this;
        }

        public Criteria andAccessCodeEqualTo(String value) {
            addCriterion("access_code =", value, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeNotEqualTo(String value) {
            addCriterion("access_code <>", value, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeGreaterThan(String value) {
            addCriterion("access_code >", value, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeGreaterThanOrEqualTo(String value) {
            addCriterion("access_code >=", value, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeLessThan(String value) {
            addCriterion("access_code <", value, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeLessThanOrEqualTo(String value) {
            addCriterion("access_code <=", value, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeLike(String value) {
            addCriterion("access_code like", value, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeNotLike(String value) {
            addCriterion("access_code not like", value, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeIn(List<String> values) {
            addCriterion("access_code in", values, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeNotIn(List<String> values) {
            addCriterion("access_code not in", values, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeBetween(String value1, String value2) {
            addCriterion("access_code between", value1, value2, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeNotBetween(String value1, String value2) {
            addCriterion("access_code not between", value1, value2, "accessCode");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andAchvIdIsNull() {
            addCriterion("achv_id is null");
            return (Criteria) this;
        }

        public Criteria andAchvIdIsNotNull() {
            addCriterion("achv_id is not null");
            return (Criteria) this;
        }

        public Criteria andAchvIdEqualTo(String value) {
            addCriterion("achv_id =", value, "achvId");
            return (Criteria) this;
        }

        public Criteria andAchvIdNotEqualTo(String value) {
            addCriterion("achv_id <>", value, "achvId");
            return (Criteria) this;
        }

        public Criteria andAchvIdGreaterThan(String value) {
            addCriterion("achv_id >", value, "achvId");
            return (Criteria) this;
        }

        public Criteria andAchvIdGreaterThanOrEqualTo(String value) {
            addCriterion("achv_id >=", value, "achvId");
            return (Criteria) this;
        }

        public Criteria andAchvIdLessThan(String value) {
            addCriterion("achv_id <", value, "achvId");
            return (Criteria) this;
        }

        public Criteria andAchvIdLessThanOrEqualTo(String value) {
            addCriterion("achv_id <=", value, "achvId");
            return (Criteria) this;
        }

        public Criteria andAchvIdLike(String value) {
            addCriterion("achv_id like", value, "achvId");
            return (Criteria) this;
        }

        public Criteria andAchvIdNotLike(String value) {
            addCriterion("achv_id not like", value, "achvId");
            return (Criteria) this;
        }

        public Criteria andAchvIdIn(List<String> values) {
            addCriterion("achv_id in", values, "achvId");
            return (Criteria) this;
        }

        public Criteria andAchvIdNotIn(List<String> values) {
            addCriterion("achv_id not in", values, "achvId");
            return (Criteria) this;
        }

        public Criteria andAchvIdBetween(String value1, String value2) {
            addCriterion("achv_id between", value1, value2, "achvId");
            return (Criteria) this;
        }

        public Criteria andAchvIdNotBetween(String value1, String value2) {
            addCriterion("achv_id not between", value1, value2, "achvId");
            return (Criteria) this;
        }

        public Criteria andGraphIdIsNull() {
            addCriterion("graph_id is null");
            return (Criteria) this;
        }

        public Criteria andGraphIdIsNotNull() {
            addCriterion("graph_id is not null");
            return (Criteria) this;
        }

        public Criteria andGraphIdEqualTo(String value) {
            addCriterion("graph_id =", value, "graphId");
            return (Criteria) this;
        }

        public Criteria andGraphIdNotEqualTo(String value) {
            addCriterion("graph_id <>", value, "graphId");
            return (Criteria) this;
        }

        public Criteria andGraphIdGreaterThan(String value) {
            addCriterion("graph_id >", value, "graphId");
            return (Criteria) this;
        }

        public Criteria andGraphIdGreaterThanOrEqualTo(String value) {
            addCriterion("graph_id >=", value, "graphId");
            return (Criteria) this;
        }

        public Criteria andGraphIdLessThan(String value) {
            addCriterion("graph_id <", value, "graphId");
            return (Criteria) this;
        }

        public Criteria andGraphIdLessThanOrEqualTo(String value) {
            addCriterion("graph_id <=", value, "graphId");
            return (Criteria) this;
        }

        public Criteria andGraphIdLike(String value) {
            addCriterion("graph_id like", value, "graphId");
            return (Criteria) this;
        }

        public Criteria andGraphIdNotLike(String value) {
            addCriterion("graph_id not like", value, "graphId");
            return (Criteria) this;
        }

        public Criteria andGraphIdIn(List<String> values) {
            addCriterion("graph_id in", values, "graphId");
            return (Criteria) this;
        }

        public Criteria andGraphIdNotIn(List<String> values) {
            addCriterion("graph_id not in", values, "graphId");
            return (Criteria) this;
        }

        public Criteria andGraphIdBetween(String value1, String value2) {
            addCriterion("graph_id between", value1, value2, "graphId");
            return (Criteria) this;
        }

        public Criteria andGraphIdNotBetween(String value1, String value2) {
            addCriterion("graph_id not between", value1, value2, "graphId");
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