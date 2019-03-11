package com.jiangyu.ggzl.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Asyou
 * @description 单位表
 * @author dingjsh
 * @date 2018-6-8 下午3:59:56
 * @version 1.0.0
 */
public class Asyou{

    private String name;

    private String address;

    private String post;

    private String contact;

    private String workphone;

    private String mail;

    private String notes;

    private String type;
    
    private String status;

    private String selcoding;


    private Asyou parentidjoin;

    private Set children = new HashSet();

    private Set asyuseroumnjoin = new HashSet();

    private Set asyuseroumnjoin2 = new HashSet();

    public Asyou() {
    }

    /**
     * Asyou
     * @param code
     * @param coding
     * @param parentid
     * @param name
     * @param type
     * @author zsn
     * @date 2018年7月16日 下午6:58:59
     */
    public Asyou(String code, String coding, String parentid, String name, String type) {
        super();
        this.name = name;
        this.type = type;
    }

    public Asyou getParentidjoin() {
        return parentidjoin;
    }

    public void setParentidjoin(Asyou parentidjoin) {
        this.parentidjoin = parentidjoin;
    }



    public Set getAsyuseroumnjoin() {
        return asyuseroumnjoin;
    }

    public void setAsyuseroumnjoin(Set asyuseroumnjoin) {
        this.asyuseroumnjoin = asyuseroumnjoin;
    }

    public Set getChildren() {
        return children;
    }

    public void setChildren(Set children) {
        this.children = children;
    }

    public String getSelcoding() {
        return selcoding;
    }

    public void setSelcoding(String selcoding) {
        this.selcoding = selcoding;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getWorkphone() {
        return workphone;
    }

    public void setWorkphone(String workphone) {
        this.workphone = workphone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set getAsyuseroumnjoin2() {
        return asyuseroumnjoin2;
    }

    public void setAsyuseroumnjoin2(Set asyuseroumnjoin2) {
        this.asyuseroumnjoin2 = asyuseroumnjoin2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
