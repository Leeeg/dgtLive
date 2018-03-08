package com.dgtis.live.model;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Lee on 2018/3/7.
 */

public class Personal {

    /**
     * opInfo : 登陆成功！
     * opState : SUCCESS
     * opValue : [{"id":123456,"loginId":"13621761774","loginPwd":"123456","userName":"方晨","userEmail":"fangchen@dgtis.com","userPhone":"13621761774","company":"43K90101","department":"ca162e94711611e7b45f484d7e9932ef","deptName":null,"post":null,"workplace":"上海","employeNo":"60696","createDate":"2017-04-25 16:05:25","updateDate":"2018-03-07 15:11:35","companyId":"69964c94aef84d3ea9d496a01188cbd8","superAdmin":false,"sex":null,"headImage":"/fileServer/head_image/201712181609521513584592068.png","remainLeave":"10","openId":null,"isDelete":null,"vacation":null,"admin":true}]
     */

    private String opInfo;
    private String opState;
    private List<OpValueBean> opValue;

    public static Personal objectFromData(String str) {

        return new Gson().fromJson(str, Personal.class);
    }

    public String getOpInfo() {
        return opInfo;
    }

    public void setOpInfo(String opInfo) {
        this.opInfo = opInfo;
    }

    public String getOpState() {
        return opState;
    }

    public void setOpState(String opState) {
        this.opState = opState;
    }

    public List<OpValueBean> getOpValue() {
        return opValue;
    }

    public void setOpValue(List<OpValueBean> opValue) {
        this.opValue = opValue;
    }

    public static class OpValueBean {
        /**
         * id : 123456
         * loginId : 13621761774
         * loginPwd : 123456
         * userName : 方晨
         * userEmail : fangchen@dgtis.com
         * userPhone : 13621761774
         * company : 43K90101
         * department : ca162e94711611e7b45f484d7e9932ef
         * deptName : null
         * post : null
         * workplace : 上海
         * employeNo : 60696
         * createDate : 2017-04-25 16:05:25
         * updateDate : 2018-03-07 15:11:35
         * companyId : 69964c94aef84d3ea9d496a01188cbd8
         * superAdmin : false
         * sex : null
         * headImage : /fileServer/head_image/201712181609521513584592068.png
         * remainLeave : 10
         * openId : null
         * isDelete : null
         * vacation : null
         * admin : true
         */

        private int id;
        private String loginId;
        private String loginPwd;
        private String userName;
        private String userEmail;
        private String userPhone;
        private String company;
        private String department;
        private Object deptName;
        private Object post;
        private String workplace;
        private String employeNo;
        private String createDate;
        private String updateDate;
        private String companyId;
        private boolean superAdmin;
        private Object sex;
        private String headImage;
        private String remainLeave;
        private Object openId;
        private Object isDelete;
        private Object vacation;
        private boolean admin;

        public static OpValueBean objectFromData(String str) {

            return new Gson().fromJson(str, OpValueBean.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLoginId() {
            return loginId;
        }

        public void setLoginId(String loginId) {
            this.loginId = loginId;
        }

        public String getLoginPwd() {
            return loginPwd;
        }

        public void setLoginPwd(String loginPwd) {
            this.loginPwd = loginPwd;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public Object getDeptName() {
            return deptName;
        }

        public void setDeptName(Object deptName) {
            this.deptName = deptName;
        }

        public Object getPost() {
            return post;
        }

        public void setPost(Object post) {
            this.post = post;
        }

        public String getWorkplace() {
            return workplace;
        }

        public void setWorkplace(String workplace) {
            this.workplace = workplace;
        }

        public String getEmployeNo() {
            return employeNo;
        }

        public void setEmployeNo(String employeNo) {
            this.employeNo = employeNo;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public boolean isSuperAdmin() {
            return superAdmin;
        }

        public void setSuperAdmin(boolean superAdmin) {
            this.superAdmin = superAdmin;
        }

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
            this.sex = sex;
        }

        public String getHeadImage() {
            return headImage;
        }

        public void setHeadImage(String headImage) {
            this.headImage = headImage;
        }

        public String getRemainLeave() {
            return remainLeave;
        }

        public void setRemainLeave(String remainLeave) {
            this.remainLeave = remainLeave;
        }

        public Object getOpenId() {
            return openId;
        }

        public void setOpenId(Object openId) {
            this.openId = openId;
        }

        public Object getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(Object isDelete) {
            this.isDelete = isDelete;
        }

        public Object getVacation() {
            return vacation;
        }

        public void setVacation(Object vacation) {
            this.vacation = vacation;
        }

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }
    }
}
