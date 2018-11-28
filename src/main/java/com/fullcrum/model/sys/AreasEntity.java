package com.fullcrum.model.sys;

/**
 * 省市实体类
 */
public class AreasEntity {
    private Integer id;//当前省份或市区的id
    private String name;//当前省份或市区名称
    private Integer pid;//上级省份或市区id

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
