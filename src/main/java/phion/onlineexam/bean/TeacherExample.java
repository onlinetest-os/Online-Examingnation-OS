package phion.onlineexam.bean;

import java.util.ArrayList;
import java.util.List;

public class TeacherExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TeacherExample() {
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

        public Criteria andTeaIdIsNull() {
            addCriterion("tea_id is null");
            return (Criteria) this;
        }

        public Criteria andTeaIdIsNotNull() {
            addCriterion("tea_id is not null");
            return (Criteria) this;
        }

        public Criteria andTeaIdEqualTo(Integer value) {
            addCriterion("tea_id =", value, "teaId");
            return (Criteria) this;
        }

        public Criteria andTeaIdNotEqualTo(Integer value) {
            addCriterion("tea_id <>", value, "teaId");
            return (Criteria) this;
        }

        public Criteria andTeaIdGreaterThan(Integer value) {
            addCriterion("tea_id >", value, "teaId");
            return (Criteria) this;
        }

        public Criteria andTeaIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("tea_id >=", value, "teaId");
            return (Criteria) this;
        }

        public Criteria andTeaIdLessThan(Integer value) {
            addCriterion("tea_id <", value, "teaId");
            return (Criteria) this;
        }

        public Criteria andTeaIdLessThanOrEqualTo(Integer value) {
            addCriterion("tea_id <=", value, "teaId");
            return (Criteria) this;
        }

        public Criteria andTeaIdIn(List<Integer> values) {
            addCriterion("tea_id in", values, "teaId");
            return (Criteria) this;
        }

        public Criteria andTeaIdNotIn(List<Integer> values) {
            addCriterion("tea_id not in", values, "teaId");
            return (Criteria) this;
        }

        public Criteria andTeaIdBetween(Integer value1, Integer value2) {
            addCriterion("tea_id between", value1, value2, "teaId");
            return (Criteria) this;
        }

        public Criteria andTeaIdNotBetween(Integer value1, Integer value2) {
            addCriterion("tea_id not between", value1, value2, "teaId");
            return (Criteria) this;
        }

        public Criteria andTeaNumberIsNull() {
            addCriterion("tea_number is null");
            return (Criteria) this;
        }

        public Criteria andTeaNumberIsNotNull() {
            addCriterion("tea_number is not null");
            return (Criteria) this;
        }

        public Criteria andTeaNumberEqualTo(String value) {
            addCriterion("tea_number =", value, "teaNumber");
            return (Criteria) this;
        }

        public Criteria andTeaNumberNotEqualTo(String value) {
            addCriterion("tea_number <>", value, "teaNumber");
            return (Criteria) this;
        }

        public Criteria andTeaNumberGreaterThan(String value) {
            addCriterion("tea_number >", value, "teaNumber");
            return (Criteria) this;
        }

        public Criteria andTeaNumberGreaterThanOrEqualTo(String value) {
            addCriterion("tea_number >=", value, "teaNumber");
            return (Criteria) this;
        }

        public Criteria andTeaNumberLessThan(String value) {
            addCriterion("tea_number <", value, "teaNumber");
            return (Criteria) this;
        }

        public Criteria andTeaNumberLessThanOrEqualTo(String value) {
            addCriterion("tea_number <=", value, "teaNumber");
            return (Criteria) this;
        }

        public Criteria andTeaNumberLike(String value) {
            addCriterion("tea_number like", value, "teaNumber");
            return (Criteria) this;
        }

        public Criteria andTeaNumberNotLike(String value) {
            addCriterion("tea_number not like", value, "teaNumber");
            return (Criteria) this;
        }

        public Criteria andTeaNumberIn(List<String> values) {
            addCriterion("tea_number in", values, "teaNumber");
            return (Criteria) this;
        }

        public Criteria andTeaNumberNotIn(List<String> values) {
            addCriterion("tea_number not in", values, "teaNumber");
            return (Criteria) this;
        }

        public Criteria andTeaNumberBetween(String value1, String value2) {
            addCriterion("tea_number between", value1, value2, "teaNumber");
            return (Criteria) this;
        }

        public Criteria andTeaNumberNotBetween(String value1, String value2) {
            addCriterion("tea_number not between", value1, value2, "teaNumber");
            return (Criteria) this;
        }

        public Criteria andTeaNameIsNull() {
            addCriterion("tea_name is null");
            return (Criteria) this;
        }

        public Criteria andTeaNameIsNotNull() {
            addCriterion("tea_name is not null");
            return (Criteria) this;
        }

        public Criteria andTeaNameEqualTo(String value) {
            addCriterion("tea_name =", value, "teaName");
            return (Criteria) this;
        }

        public Criteria andTeaNameNotEqualTo(String value) {
            addCriterion("tea_name <>", value, "teaName");
            return (Criteria) this;
        }

        public Criteria andTeaNameGreaterThan(String value) {
            addCriterion("tea_name >", value, "teaName");
            return (Criteria) this;
        }

        public Criteria andTeaNameGreaterThanOrEqualTo(String value) {
            addCriterion("tea_name >=", value, "teaName");
            return (Criteria) this;
        }

        public Criteria andTeaNameLessThan(String value) {
            addCriterion("tea_name <", value, "teaName");
            return (Criteria) this;
        }

        public Criteria andTeaNameLessThanOrEqualTo(String value) {
            addCriterion("tea_name <=", value, "teaName");
            return (Criteria) this;
        }

        public Criteria andTeaNameLike(String value) {
            addCriterion("tea_name like", value, "teaName");
            return (Criteria) this;
        }

        public Criteria andTeaNameNotLike(String value) {
            addCriterion("tea_name not like", value, "teaName");
            return (Criteria) this;
        }

        public Criteria andTeaNameIn(List<String> values) {
            addCriterion("tea_name in", values, "teaName");
            return (Criteria) this;
        }

        public Criteria andTeaNameNotIn(List<String> values) {
            addCriterion("tea_name not in", values, "teaName");
            return (Criteria) this;
        }

        public Criteria andTeaNameBetween(String value1, String value2) {
            addCriterion("tea_name between", value1, value2, "teaName");
            return (Criteria) this;
        }

        public Criteria andTeaNameNotBetween(String value1, String value2) {
            addCriterion("tea_name not between", value1, value2, "teaName");
            return (Criteria) this;
        }

        public Criteria andTeaPasswordIsNull() {
            addCriterion("tea_password is null");
            return (Criteria) this;
        }

        public Criteria andTeaPasswordIsNotNull() {
            addCriterion("tea_password is not null");
            return (Criteria) this;
        }

        public Criteria andTeaPasswordEqualTo(String value) {
            addCriterion("tea_password =", value, "teaPassword");
            return (Criteria) this;
        }

        public Criteria andTeaPasswordNotEqualTo(String value) {
            addCriterion("tea_password <>", value, "teaPassword");
            return (Criteria) this;
        }

        public Criteria andTeaPasswordGreaterThan(String value) {
            addCriterion("tea_password >", value, "teaPassword");
            return (Criteria) this;
        }

        public Criteria andTeaPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("tea_password >=", value, "teaPassword");
            return (Criteria) this;
        }

        public Criteria andTeaPasswordLessThan(String value) {
            addCriterion("tea_password <", value, "teaPassword");
            return (Criteria) this;
        }

        public Criteria andTeaPasswordLessThanOrEqualTo(String value) {
            addCriterion("tea_password <=", value, "teaPassword");
            return (Criteria) this;
        }

        public Criteria andTeaPasswordLike(String value) {
            addCriterion("tea_password like", value, "teaPassword");
            return (Criteria) this;
        }

        public Criteria andTeaPasswordNotLike(String value) {
            addCriterion("tea_password not like", value, "teaPassword");
            return (Criteria) this;
        }

        public Criteria andTeaPasswordIn(List<String> values) {
            addCriterion("tea_password in", values, "teaPassword");
            return (Criteria) this;
        }

        public Criteria andTeaPasswordNotIn(List<String> values) {
            addCriterion("tea_password not in", values, "teaPassword");
            return (Criteria) this;
        }

        public Criteria andTeaPasswordBetween(String value1, String value2) {
            addCriterion("tea_password between", value1, value2, "teaPassword");
            return (Criteria) this;
        }

        public Criteria andTeaPasswordNotBetween(String value1, String value2) {
            addCriterion("tea_password not between", value1, value2, "teaPassword");
            return (Criteria) this;
        }

        public Criteria andIsAdminIsNull() {
            addCriterion("is_admin is null");
            return (Criteria) this;
        }

        public Criteria andIsAdminIsNotNull() {
            addCriterion("is_admin is not null");
            return (Criteria) this;
        }

        public Criteria andIsAdminEqualTo(Integer value) {
            addCriterion("is_admin =", value, "isAdmin");
            return (Criteria) this;
        }

        public Criteria andIsAdminNotEqualTo(Integer value) {
            addCriterion("is_admin <>", value, "isAdmin");
            return (Criteria) this;
        }

        public Criteria andIsAdminGreaterThan(Integer value) {
            addCriterion("is_admin >", value, "isAdmin");
            return (Criteria) this;
        }

        public Criteria andIsAdminGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_admin >=", value, "isAdmin");
            return (Criteria) this;
        }

        public Criteria andIsAdminLessThan(Integer value) {
            addCriterion("is_admin <", value, "isAdmin");
            return (Criteria) this;
        }

        public Criteria andIsAdminLessThanOrEqualTo(Integer value) {
            addCriterion("is_admin <=", value, "isAdmin");
            return (Criteria) this;
        }

        public Criteria andIsAdminIn(List<Integer> values) {
            addCriterion("is_admin in", values, "isAdmin");
            return (Criteria) this;
        }

        public Criteria andIsAdminNotIn(List<Integer> values) {
            addCriterion("is_admin not in", values, "isAdmin");
            return (Criteria) this;
        }

        public Criteria andIsAdminBetween(Integer value1, Integer value2) {
            addCriterion("is_admin between", value1, value2, "isAdmin");
            return (Criteria) this;
        }

        public Criteria andIsAdminNotBetween(Integer value1, Integer value2) {
            addCriterion("is_admin not between", value1, value2, "isAdmin");
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