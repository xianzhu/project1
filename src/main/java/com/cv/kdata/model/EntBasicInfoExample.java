package com.cv.kdata.model;

import java.util.ArrayList;
import java.util.List;

public class EntBasicInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EntBasicInfoExample() {
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

        public Criteria andEntIdIsNull() {
            addCriterion("ent_id is null");
            return (Criteria) this;
        }

        public Criteria andEntIdIsNotNull() {
            addCriterion("ent_id is not null");
            return (Criteria) this;
        }

        public Criteria andEntIdEqualTo(String value) {
            addCriterion("ent_id =", value, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdNotEqualTo(String value) {
            addCriterion("ent_id <>", value, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdGreaterThan(String value) {
            addCriterion("ent_id >", value, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdGreaterThanOrEqualTo(String value) {
            addCriterion("ent_id >=", value, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdLessThan(String value) {
            addCriterion("ent_id <", value, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdLessThanOrEqualTo(String value) {
            addCriterion("ent_id <=", value, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdLike(String value) {
            addCriterion("ent_id like", value, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdNotLike(String value) {
            addCriterion("ent_id not like", value, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdIn(List<String> values) {
            addCriterion("ent_id in", values, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdNotIn(List<String> values) {
            addCriterion("ent_id not in", values, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdBetween(String value1, String value2) {
            addCriterion("ent_id between", value1, value2, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdNotBetween(String value1, String value2) {
            addCriterion("ent_id not between", value1, value2, "entId");
            return (Criteria) this;
        }

        public Criteria andEntNameIsNull() {
            addCriterion("ent_name is null");
            return (Criteria) this;
        }

        public Criteria andEntNameIsNotNull() {
            addCriterion("ent_name is not null");
            return (Criteria) this;
        }

        public Criteria andEntNameEqualTo(String value) {
            addCriterion("ent_name =", value, "entName");
            return (Criteria) this;
        }

        public Criteria andEntNameNotEqualTo(String value) {
            addCriterion("ent_name <>", value, "entName");
            return (Criteria) this;
        }

        public Criteria andEntNameGreaterThan(String value) {
            addCriterion("ent_name >", value, "entName");
            return (Criteria) this;
        }

        public Criteria andEntNameGreaterThanOrEqualTo(String value) {
            addCriterion("ent_name >=", value, "entName");
            return (Criteria) this;
        }

        public Criteria andEntNameLessThan(String value) {
            addCriterion("ent_name <", value, "entName");
            return (Criteria) this;
        }

        public Criteria andEntNameLessThanOrEqualTo(String value) {
            addCriterion("ent_name <=", value, "entName");
            return (Criteria) this;
        }

        public Criteria andEntNameLike(String value) {
            addCriterion("ent_name like", value, "entName");
            return (Criteria) this;
        }

        public Criteria andEntNameNotLike(String value) {
            addCriterion("ent_name not like", value, "entName");
            return (Criteria) this;
        }

        public Criteria andEntNameIn(List<String> values) {
            addCriterion("ent_name in", values, "entName");
            return (Criteria) this;
        }

        public Criteria andEntNameNotIn(List<String> values) {
            addCriterion("ent_name not in", values, "entName");
            return (Criteria) this;
        }

        public Criteria andEntNameBetween(String value1, String value2) {
            addCriterion("ent_name between", value1, value2, "entName");
            return (Criteria) this;
        }

        public Criteria andEntNameNotBetween(String value1, String value2) {
            addCriterion("ent_name not between", value1, value2, "entName");
            return (Criteria) this;
        }

        public Criteria andEconKindIsNull() {
            addCriterion("econ_kind is null");
            return (Criteria) this;
        }

        public Criteria andEconKindIsNotNull() {
            addCriterion("econ_kind is not null");
            return (Criteria) this;
        }

        public Criteria andEconKindEqualTo(String value) {
            addCriterion("econ_kind =", value, "econKind");
            return (Criteria) this;
        }

        public Criteria andEconKindNotEqualTo(String value) {
            addCriterion("econ_kind <>", value, "econKind");
            return (Criteria) this;
        }

        public Criteria andEconKindGreaterThan(String value) {
            addCriterion("econ_kind >", value, "econKind");
            return (Criteria) this;
        }

        public Criteria andEconKindGreaterThanOrEqualTo(String value) {
            addCriterion("econ_kind >=", value, "econKind");
            return (Criteria) this;
        }

        public Criteria andEconKindLessThan(String value) {
            addCriterion("econ_kind <", value, "econKind");
            return (Criteria) this;
        }

        public Criteria andEconKindLessThanOrEqualTo(String value) {
            addCriterion("econ_kind <=", value, "econKind");
            return (Criteria) this;
        }

        public Criteria andEconKindLike(String value) {
            addCriterion("econ_kind like", value, "econKind");
            return (Criteria) this;
        }

        public Criteria andEconKindNotLike(String value) {
            addCriterion("econ_kind not like", value, "econKind");
            return (Criteria) this;
        }

        public Criteria andEconKindIn(List<String> values) {
            addCriterion("econ_kind in", values, "econKind");
            return (Criteria) this;
        }

        public Criteria andEconKindNotIn(List<String> values) {
            addCriterion("econ_kind not in", values, "econKind");
            return (Criteria) this;
        }

        public Criteria andEconKindBetween(String value1, String value2) {
            addCriterion("econ_kind between", value1, value2, "econKind");
            return (Criteria) this;
        }

        public Criteria andEconKindNotBetween(String value1, String value2) {
            addCriterion("econ_kind not between", value1, value2, "econKind");
            return (Criteria) this;
        }

        public Criteria andRegCapitalIsNull() {
            addCriterion("reg_capital is null");
            return (Criteria) this;
        }

        public Criteria andRegCapitalIsNotNull() {
            addCriterion("reg_capital is not null");
            return (Criteria) this;
        }

        public Criteria andRegCapitalEqualTo(String value) {
            addCriterion("reg_capital =", value, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalNotEqualTo(String value) {
            addCriterion("reg_capital <>", value, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalGreaterThan(String value) {
            addCriterion("reg_capital >", value, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalGreaterThanOrEqualTo(String value) {
            addCriterion("reg_capital >=", value, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalLessThan(String value) {
            addCriterion("reg_capital <", value, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalLessThanOrEqualTo(String value) {
            addCriterion("reg_capital <=", value, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalLike(String value) {
            addCriterion("reg_capital like", value, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalNotLike(String value) {
            addCriterion("reg_capital not like", value, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalIn(List<String> values) {
            addCriterion("reg_capital in", values, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalNotIn(List<String> values) {
            addCriterion("reg_capital not in", values, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalBetween(String value1, String value2) {
            addCriterion("reg_capital between", value1, value2, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalNotBetween(String value1, String value2) {
            addCriterion("reg_capital not between", value1, value2, "regCapital");
            return (Criteria) this;
        }

        public Criteria andUnitIsNull() {
            addCriterion("unit is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("unit is not null");
            return (Criteria) this;
        }

        public Criteria andUnitEqualTo(String value) {
            addCriterion("unit =", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotEqualTo(String value) {
            addCriterion("unit <>", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThan(String value) {
            addCriterion("unit >", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThanOrEqualTo(String value) {
            addCriterion("unit >=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThan(String value) {
            addCriterion("unit <", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThanOrEqualTo(String value) {
            addCriterion("unit <=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLike(String value) {
            addCriterion("unit like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotLike(String value) {
            addCriterion("unit not like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitIn(List<String> values) {
            addCriterion("unit in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotIn(List<String> values) {
            addCriterion("unit not in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitBetween(String value1, String value2) {
            addCriterion("unit between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotBetween(String value1, String value2) {
            addCriterion("unit not between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andOptTimeStartIsNull() {
            addCriterion("opt_time_start is null");
            return (Criteria) this;
        }

        public Criteria andOptTimeStartIsNotNull() {
            addCriterion("opt_time_start is not null");
            return (Criteria) this;
        }

        public Criteria andOptTimeStartEqualTo(String value) {
            addCriterion("opt_time_start =", value, "optTimeStart");
            return (Criteria) this;
        }

        public Criteria andOptTimeStartNotEqualTo(String value) {
            addCriterion("opt_time_start <>", value, "optTimeStart");
            return (Criteria) this;
        }

        public Criteria andOptTimeStartGreaterThan(String value) {
            addCriterion("opt_time_start >", value, "optTimeStart");
            return (Criteria) this;
        }

        public Criteria andOptTimeStartGreaterThanOrEqualTo(String value) {
            addCriterion("opt_time_start >=", value, "optTimeStart");
            return (Criteria) this;
        }

        public Criteria andOptTimeStartLessThan(String value) {
            addCriterion("opt_time_start <", value, "optTimeStart");
            return (Criteria) this;
        }

        public Criteria andOptTimeStartLessThanOrEqualTo(String value) {
            addCriterion("opt_time_start <=", value, "optTimeStart");
            return (Criteria) this;
        }

        public Criteria andOptTimeStartLike(String value) {
            addCriterion("opt_time_start like", value, "optTimeStart");
            return (Criteria) this;
        }

        public Criteria andOptTimeStartNotLike(String value) {
            addCriterion("opt_time_start not like", value, "optTimeStart");
            return (Criteria) this;
        }

        public Criteria andOptTimeStartIn(List<String> values) {
            addCriterion("opt_time_start in", values, "optTimeStart");
            return (Criteria) this;
        }

        public Criteria andOptTimeStartNotIn(List<String> values) {
            addCriterion("opt_time_start not in", values, "optTimeStart");
            return (Criteria) this;
        }

        public Criteria andOptTimeStartBetween(String value1, String value2) {
            addCriterion("opt_time_start between", value1, value2, "optTimeStart");
            return (Criteria) this;
        }

        public Criteria andOptTimeStartNotBetween(String value1, String value2) {
            addCriterion("opt_time_start not between", value1, value2, "optTimeStart");
            return (Criteria) this;
        }

        public Criteria andOptTimeEndIsNull() {
            addCriterion("opt_time_end is null");
            return (Criteria) this;
        }

        public Criteria andOptTimeEndIsNotNull() {
            addCriterion("opt_time_end is not null");
            return (Criteria) this;
        }

        public Criteria andOptTimeEndEqualTo(String value) {
            addCriterion("opt_time_end =", value, "optTimeEnd");
            return (Criteria) this;
        }

        public Criteria andOptTimeEndNotEqualTo(String value) {
            addCriterion("opt_time_end <>", value, "optTimeEnd");
            return (Criteria) this;
        }

        public Criteria andOptTimeEndGreaterThan(String value) {
            addCriterion("opt_time_end >", value, "optTimeEnd");
            return (Criteria) this;
        }

        public Criteria andOptTimeEndGreaterThanOrEqualTo(String value) {
            addCriterion("opt_time_end >=", value, "optTimeEnd");
            return (Criteria) this;
        }

        public Criteria andOptTimeEndLessThan(String value) {
            addCriterion("opt_time_end <", value, "optTimeEnd");
            return (Criteria) this;
        }

        public Criteria andOptTimeEndLessThanOrEqualTo(String value) {
            addCriterion("opt_time_end <=", value, "optTimeEnd");
            return (Criteria) this;
        }

        public Criteria andOptTimeEndLike(String value) {
            addCriterion("opt_time_end like", value, "optTimeEnd");
            return (Criteria) this;
        }

        public Criteria andOptTimeEndNotLike(String value) {
            addCriterion("opt_time_end not like", value, "optTimeEnd");
            return (Criteria) this;
        }

        public Criteria andOptTimeEndIn(List<String> values) {
            addCriterion("opt_time_end in", values, "optTimeEnd");
            return (Criteria) this;
        }

        public Criteria andOptTimeEndNotIn(List<String> values) {
            addCriterion("opt_time_end not in", values, "optTimeEnd");
            return (Criteria) this;
        }

        public Criteria andOptTimeEndBetween(String value1, String value2) {
            addCriterion("opt_time_end between", value1, value2, "optTimeEnd");
            return (Criteria) this;
        }

        public Criteria andOptTimeEndNotBetween(String value1, String value2) {
            addCriterion("opt_time_end not between", value1, value2, "optTimeEnd");
            return (Criteria) this;
        }

        public Criteria andRegAuthIsNull() {
            addCriterion("reg_auth is null");
            return (Criteria) this;
        }

        public Criteria andRegAuthIsNotNull() {
            addCriterion("reg_auth is not null");
            return (Criteria) this;
        }

        public Criteria andRegAuthEqualTo(String value) {
            addCriterion("reg_auth =", value, "regAuth");
            return (Criteria) this;
        }

        public Criteria andRegAuthNotEqualTo(String value) {
            addCriterion("reg_auth <>", value, "regAuth");
            return (Criteria) this;
        }

        public Criteria andRegAuthGreaterThan(String value) {
            addCriterion("reg_auth >", value, "regAuth");
            return (Criteria) this;
        }

        public Criteria andRegAuthGreaterThanOrEqualTo(String value) {
            addCriterion("reg_auth >=", value, "regAuth");
            return (Criteria) this;
        }

        public Criteria andRegAuthLessThan(String value) {
            addCriterion("reg_auth <", value, "regAuth");
            return (Criteria) this;
        }

        public Criteria andRegAuthLessThanOrEqualTo(String value) {
            addCriterion("reg_auth <=", value, "regAuth");
            return (Criteria) this;
        }

        public Criteria andRegAuthLike(String value) {
            addCriterion("reg_auth like", value, "regAuth");
            return (Criteria) this;
        }

        public Criteria andRegAuthNotLike(String value) {
            addCriterion("reg_auth not like", value, "regAuth");
            return (Criteria) this;
        }

        public Criteria andRegAuthIn(List<String> values) {
            addCriterion("reg_auth in", values, "regAuth");
            return (Criteria) this;
        }

        public Criteria andRegAuthNotIn(List<String> values) {
            addCriterion("reg_auth not in", values, "regAuth");
            return (Criteria) this;
        }

        public Criteria andRegAuthBetween(String value1, String value2) {
            addCriterion("reg_auth between", value1, value2, "regAuth");
            return (Criteria) this;
        }

        public Criteria andRegAuthNotBetween(String value1, String value2) {
            addCriterion("reg_auth not between", value1, value2, "regAuth");
            return (Criteria) this;
        }

        public Criteria andCheckDateIsNull() {
            addCriterion("check_date is null");
            return (Criteria) this;
        }

        public Criteria andCheckDateIsNotNull() {
            addCriterion("check_date is not null");
            return (Criteria) this;
        }

        public Criteria andCheckDateEqualTo(String value) {
            addCriterion("check_date =", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateNotEqualTo(String value) {
            addCriterion("check_date <>", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateGreaterThan(String value) {
            addCriterion("check_date >", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateGreaterThanOrEqualTo(String value) {
            addCriterion("check_date >=", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateLessThan(String value) {
            addCriterion("check_date <", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateLessThanOrEqualTo(String value) {
            addCriterion("check_date <=", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateLike(String value) {
            addCriterion("check_date like", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateNotLike(String value) {
            addCriterion("check_date not like", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateIn(List<String> values) {
            addCriterion("check_date in", values, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateNotIn(List<String> values) {
            addCriterion("check_date not in", values, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateBetween(String value1, String value2) {
            addCriterion("check_date between", value1, value2, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateNotBetween(String value1, String value2) {
            addCriterion("check_date not between", value1, value2, "checkDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNull() {
            addCriterion("start_date is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("start_date is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(String value) {
            addCriterion("start_date =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(String value) {
            addCriterion("start_date <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(String value) {
            addCriterion("start_date >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(String value) {
            addCriterion("start_date >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(String value) {
            addCriterion("start_date <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(String value) {
            addCriterion("start_date <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLike(String value) {
            addCriterion("start_date like", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotLike(String value) {
            addCriterion("start_date not like", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<String> values) {
            addCriterion("start_date in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<String> values) {
            addCriterion("start_date not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(String value1, String value2) {
            addCriterion("start_date between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(String value1, String value2) {
            addCriterion("start_date not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("end_date is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("end_date is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(String value) {
            addCriterion("end_date =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(String value) {
            addCriterion("end_date <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(String value) {
            addCriterion("end_date >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("end_date >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(String value) {
            addCriterion("end_date <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(String value) {
            addCriterion("end_date <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLike(String value) {
            addCriterion("end_date like", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotLike(String value) {
            addCriterion("end_date not like", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<String> values) {
            addCriterion("end_date in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<String> values) {
            addCriterion("end_date not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(String value1, String value2) {
            addCriterion("end_date between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(String value1, String value2) {
            addCriterion("end_date not between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andBizStatIsNull() {
            addCriterion("biz_stat is null");
            return (Criteria) this;
        }

        public Criteria andBizStatIsNotNull() {
            addCriterion("biz_stat is not null");
            return (Criteria) this;
        }

        public Criteria andBizStatEqualTo(String value) {
            addCriterion("biz_stat =", value, "bizStat");
            return (Criteria) this;
        }

        public Criteria andBizStatNotEqualTo(String value) {
            addCriterion("biz_stat <>", value, "bizStat");
            return (Criteria) this;
        }

        public Criteria andBizStatGreaterThan(String value) {
            addCriterion("biz_stat >", value, "bizStat");
            return (Criteria) this;
        }

        public Criteria andBizStatGreaterThanOrEqualTo(String value) {
            addCriterion("biz_stat >=", value, "bizStat");
            return (Criteria) this;
        }

        public Criteria andBizStatLessThan(String value) {
            addCriterion("biz_stat <", value, "bizStat");
            return (Criteria) this;
        }

        public Criteria andBizStatLessThanOrEqualTo(String value) {
            addCriterion("biz_stat <=", value, "bizStat");
            return (Criteria) this;
        }

        public Criteria andBizStatLike(String value) {
            addCriterion("biz_stat like", value, "bizStat");
            return (Criteria) this;
        }

        public Criteria andBizStatNotLike(String value) {
            addCriterion("biz_stat not like", value, "bizStat");
            return (Criteria) this;
        }

        public Criteria andBizStatIn(List<String> values) {
            addCriterion("biz_stat in", values, "bizStat");
            return (Criteria) this;
        }

        public Criteria andBizStatNotIn(List<String> values) {
            addCriterion("biz_stat not in", values, "bizStat");
            return (Criteria) this;
        }

        public Criteria andBizStatBetween(String value1, String value2) {
            addCriterion("biz_stat between", value1, value2, "bizStat");
            return (Criteria) this;
        }

        public Criteria andBizStatNotBetween(String value1, String value2) {
            addCriterion("biz_stat not between", value1, value2, "bizStat");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIsNull() {
            addCriterion("org_code is null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIsNotNull() {
            addCriterion("org_code is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeEqualTo(String value) {
            addCriterion("org_code =", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotEqualTo(String value) {
            addCriterion("org_code <>", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThan(String value) {
            addCriterion("org_code >", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("org_code >=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThan(String value) {
            addCriterion("org_code <", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThanOrEqualTo(String value) {
            addCriterion("org_code <=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLike(String value) {
            addCriterion("org_code like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotLike(String value) {
            addCriterion("org_code not like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIn(List<String> values) {
            addCriterion("org_code in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotIn(List<String> values) {
            addCriterion("org_code not in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeBetween(String value1, String value2) {
            addCriterion("org_code between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotBetween(String value1, String value2) {
            addCriterion("org_code not between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeIsNull() {
            addCriterion("credit_code is null");
            return (Criteria) this;
        }

        public Criteria andCreditCodeIsNotNull() {
            addCriterion("credit_code is not null");
            return (Criteria) this;
        }

        public Criteria andCreditCodeEqualTo(String value) {
            addCriterion("credit_code =", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeNotEqualTo(String value) {
            addCriterion("credit_code <>", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeGreaterThan(String value) {
            addCriterion("credit_code >", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeGreaterThanOrEqualTo(String value) {
            addCriterion("credit_code >=", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeLessThan(String value) {
            addCriterion("credit_code <", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeLessThanOrEqualTo(String value) {
            addCriterion("credit_code <=", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeLike(String value) {
            addCriterion("credit_code like", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeNotLike(String value) {
            addCriterion("credit_code not like", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeIn(List<String> values) {
            addCriterion("credit_code in", values, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeNotIn(List<String> values) {
            addCriterion("credit_code not in", values, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeBetween(String value1, String value2) {
            addCriterion("credit_code between", value1, value2, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeNotBetween(String value1, String value2) {
            addCriterion("credit_code not between", value1, value2, "creditCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeIsNull() {
            addCriterion("reg_code is null");
            return (Criteria) this;
        }

        public Criteria andRegCodeIsNotNull() {
            addCriterion("reg_code is not null");
            return (Criteria) this;
        }

        public Criteria andRegCodeEqualTo(String value) {
            addCriterion("reg_code =", value, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeNotEqualTo(String value) {
            addCriterion("reg_code <>", value, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeGreaterThan(String value) {
            addCriterion("reg_code >", value, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeGreaterThanOrEqualTo(String value) {
            addCriterion("reg_code >=", value, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeLessThan(String value) {
            addCriterion("reg_code <", value, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeLessThanOrEqualTo(String value) {
            addCriterion("reg_code <=", value, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeLike(String value) {
            addCriterion("reg_code like", value, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeNotLike(String value) {
            addCriterion("reg_code not like", value, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeIn(List<String> values) {
            addCriterion("reg_code in", values, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeNotIn(List<String> values) {
            addCriterion("reg_code not in", values, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeBetween(String value1, String value2) {
            addCriterion("reg_code between", value1, value2, "regCode");
            return (Criteria) this;
        }

        public Criteria andRegCodeNotBetween(String value1, String value2) {
            addCriterion("reg_code not between", value1, value2, "regCode");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andTelIsNull() {
            addCriterion("tel is null");
            return (Criteria) this;
        }

        public Criteria andTelIsNotNull() {
            addCriterion("tel is not null");
            return (Criteria) this;
        }

        public Criteria andTelEqualTo(String value) {
            addCriterion("tel =", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotEqualTo(String value) {
            addCriterion("tel <>", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThan(String value) {
            addCriterion("tel >", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThanOrEqualTo(String value) {
            addCriterion("tel >=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThan(String value) {
            addCriterion("tel <", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThanOrEqualTo(String value) {
            addCriterion("tel <=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLike(String value) {
            addCriterion("tel like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotLike(String value) {
            addCriterion("tel not like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelIn(List<String> values) {
            addCriterion("tel in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotIn(List<String> values) {
            addCriterion("tel not in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelBetween(String value1, String value2) {
            addCriterion("tel between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotBetween(String value1, String value2) {
            addCriterion("tel not between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andPDomainIsNull() {
            addCriterion("p_domain is null");
            return (Criteria) this;
        }

        public Criteria andPDomainIsNotNull() {
            addCriterion("p_domain is not null");
            return (Criteria) this;
        }

        public Criteria andPDomainEqualTo(String value) {
            addCriterion("p_domain =", value, "pDomain");
            return (Criteria) this;
        }

        public Criteria andPDomainNotEqualTo(String value) {
            addCriterion("p_domain <>", value, "pDomain");
            return (Criteria) this;
        }

        public Criteria andPDomainGreaterThan(String value) {
            addCriterion("p_domain >", value, "pDomain");
            return (Criteria) this;
        }

        public Criteria andPDomainGreaterThanOrEqualTo(String value) {
            addCriterion("p_domain >=", value, "pDomain");
            return (Criteria) this;
        }

        public Criteria andPDomainLessThan(String value) {
            addCriterion("p_domain <", value, "pDomain");
            return (Criteria) this;
        }

        public Criteria andPDomainLessThanOrEqualTo(String value) {
            addCriterion("p_domain <=", value, "pDomain");
            return (Criteria) this;
        }

        public Criteria andPDomainLike(String value) {
            addCriterion("p_domain like", value, "pDomain");
            return (Criteria) this;
        }

        public Criteria andPDomainNotLike(String value) {
            addCriterion("p_domain not like", value, "pDomain");
            return (Criteria) this;
        }

        public Criteria andPDomainIn(List<String> values) {
            addCriterion("p_domain in", values, "pDomain");
            return (Criteria) this;
        }

        public Criteria andPDomainNotIn(List<String> values) {
            addCriterion("p_domain not in", values, "pDomain");
            return (Criteria) this;
        }

        public Criteria andPDomainBetween(String value1, String value2) {
            addCriterion("p_domain between", value1, value2, "pDomain");
            return (Criteria) this;
        }

        public Criteria andPDomainNotBetween(String value1, String value2) {
            addCriterion("p_domain not between", value1, value2, "pDomain");
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