package com.github.markzhl.common.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author mark
 */
public class TreeNode {
    protected Long id;
    protected Long parentId;

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    List<TreeNode> children = new ArrayList<TreeNode>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void add(TreeNode node){
        children.add(node);
    }
}
