package phion.onlineexam.bean;

import java.util.ArrayList;
import java.util.List;

public class ExamInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamInfoExample() {
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

        public Criteria andInIdIsNull() {
            addCriterion("in_id is null");
            return (Criteria) this;
        }

        public Criteria andInIdIsNotNull() {
            addCriterion("in_id is not null");
            return (Criteria) this;
        }

        public Criteria andInIdEqualTo(Integer value) {
            addCriterion("in_id =", value, "inId");
            return (Criteria) this;
        }

        public Criteria andInIdNotEqualTo(Integer value) {
            addCriterion("in_id <>", value, "inId");
            return (Criteria) this;
        }

        public Criteria andInIdGreaterThan(Integer value) {
            addCriterion("in_id >", value, "inId");
            return (Criteria) this;
        }

        public Criteria andInIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("in_id >=", value, "inId");
            return (Criteria) this;
        }

        public Criteria andInIdLessThan(Integer value) {
            addCriterion("in_id <", value, "inId");
            return (Criteria) this;
        }

        public Criteria andInIdLessThanOrEqualTo(Integer value) {
            addCriterion("in_id <=", value, "inId");
            return (Criteria) this;
        }

        public Criteria andInIdIn(List<Integer> values) {
            addCriterion("in_id in", values, "inId");
            return (Criteria) this;
        }

        public Criteria andInIdNotIn(List<Integer> values) {
            addCriterion("in_id not in", values, "inId");
            return (Criteria) this;
        }

        public Criteria andInIdBetween(Integer value1, Integer value2) {
            addCriterion("in_id between", value1, value2, "inId");
            return (Criteria) this;
        }

        public Criteria andInIdNotBetween(Integer value1, Integer value2) {
            addCriterion("in_id not between", value1, value2, "inId");
            return (Criteria) this;
        }

        public Criteria andEIdIsNull() {
            addCriterion("e_id is null");
            return (Criteria) this;
        }

        public Criteria andEIdIsNotNull() {
            addCriterion("e_id is not null");
            return (Criteria) this;
        }

        public Criteria andEIdEqualTo(Integer value) {
            addCriterion("e_id =", value, "eId");
            return (Criteria) this;
        }

        public Criteria andEIdNotEqualTo(Integer value) {
            addCriterion("e_id <>", value, "eId");
            return (Criteria) this;
        }

        public Criteria andEIdGreaterThan(Integer value) {
            addCriterion("e_id >", value, "eId");
            return (Criteria) this;
        }

        public Criteria andEIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("e_id >=", value, "eId");
            return (Criteria) this;
        }

        public Criteria andEIdLessThan(Integer value) {
            addCriterion("e_id <", value, "eId");
            return (Criteria) this;
        }

        public Criteria andEIdLessThanOrEqualTo(Integer value) {
            addCriterion("e_id <=", value, "eId");
            return (Criteria) this;
        }

        public Criteria andEIdIn(List<Integer> values) {
            addCriterion("e_id in", values, "eId");
            return (Criteria) this;
        }

        public Criteria andEIdNotIn(List<Integer> values) {
            addCriterion("e_id not in", values, "eId");
            return (Criteria) this;
        }

        public Criteria andEIdBetween(Integer value1, Integer value2) {
            addCriterion("e_id between", value1, value2, "eId");
            return (Criteria) this;
        }

        public Criteria andEIdNotBetween(Integer value1, Integer value2) {
            addCriterion("e_id not between", value1, value2, "eId");
            return (Criteria) this;
        }

        public Criteria andAllNumberIsNull() {
            addCriterion("all_number is null");
            return (Criteria) this;
        }

        public Criteria andAllNumberIsNotNull() {
            addCriterion("all_number is not null");
            return (Criteria) this;
        }

        public Criteria andAllNumberEqualTo(Integer value) {
            addCriterion("all_number =", value, "allNumber");
            return (Criteria) this;
        }

        public Criteria andAllNumberNotEqualTo(Integer value) {
            addCriterion("all_number <>", value, "allNumber");
            return (Criteria) this;
        }

        public Criteria andAllNumberGreaterThan(Integer value) {
            addCriterion("all_number >", value, "allNumber");
            return (Criteria) this;
        }

        public Criteria andAllNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("all_number >=", value, "allNumber");
            return (Criteria) this;
        }

        public Criteria andAllNumberLessThan(Integer value) {
            addCriterion("all_number <", value, "allNumber");
            return (Criteria) this;
        }

        public Criteria andAllNumberLessThanOrEqualTo(Integer value) {
            addCriterion("all_number <=", value, "allNumber");
            return (Criteria) this;
        }

        public Criteria andAllNumberIn(List<Integer> values) {
            addCriterion("all_number in", values, "allNumber");
            return (Criteria) this;
        }

        public Criteria andAllNumberNotIn(List<Integer> values) {
            addCriterion("all_number not in", values, "allNumber");
            return (Criteria) this;
        }

        public Criteria andAllNumberBetween(Integer value1, Integer value2) {
            addCriterion("all_number between", value1, value2, "allNumber");
            return (Criteria) this;
        }

        public Criteria andAllNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("all_number not between", value1, value2, "allNumber");
            return (Criteria) this;
        }

        public Criteria andIsDownloadIsNull() {
            addCriterion("is_download is null");
            return (Criteria) this;
        }

        public Criteria andIsDownloadIsNotNull() {
            addCriterion("is_download is not null");
            return (Criteria) this;
        }

        public Criteria andIsDownloadEqualTo(Integer value) {
            addCriterion("is_download =", value, "isDownload");
            return (Criteria) this;
        }

        public Criteria andIsDownloadNotEqualTo(Integer value) {
            addCriterion("is_download <>", value, "isDownload");
            return (Criteria) this;
        }

        public Criteria andIsDownloadGreaterThan(Integer value) {
            addCriterion("is_download >", value, "isDownload");
            return (Criteria) this;
        }

        public Criteria andIsDownloadGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_download >=", value, "isDownload");
            return (Criteria) this;
        }

        public Criteria andIsDownloadLessThan(Integer value) {
            addCriterion("is_download <", value, "isDownload");
            return (Criteria) this;
        }

        public Criteria andIsDownloadLessThanOrEqualTo(Integer value) {
            addCriterion("is_download <=", value, "isDownload");
            return (Criteria) this;
        }

        public Criteria andIsDownloadIn(List<Integer> values) {
            addCriterion("is_download in", values, "isDownload");
            return (Criteria) this;
        }

        public Criteria andIsDownloadNotIn(List<Integer> values) {
            addCriterion("is_download not in", values, "isDownload");
            return (Criteria) this;
        }

        public Criteria andIsDownloadBetween(Integer value1, Integer value2) {
            addCriterion("is_download between", value1, value2, "isDownload");
            return (Criteria) this;
        }

        public Criteria andIsDownloadNotBetween(Integer value1, Integer value2) {
            addCriterion("is_download not between", value1, value2, "isDownload");
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