package com.github.markzhl.admin.vo;

import java.util.List;

import com.github.markzhl.admin.entity.sys.BaseUser;

/**
 *  2017/6/18.
 */
public class GroupUsers {
    List<BaseUser> members ;
    List<BaseUser> leaders;

    public GroupUsers() {
    }

    public GroupUsers(List<BaseUser> members, List<BaseUser> leaders) {
        this.members = members;
        this.leaders = leaders;
    }

    public List<BaseUser> getMembers() {
        return members;
    }

    public void setMembers(List<BaseUser> members) {
        this.members = members;
    }

    public List<BaseUser> getLeaders() {
        return leaders;
    }

    public void setLeaders(List<BaseUser> leaders) {
        this.leaders = leaders;
    }
}
