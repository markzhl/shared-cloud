package com.github.markzhl.admin.vo;

import java.util.ArrayList;
import java.util.List;

import com.github.markzhl.common.util.TreeUtil;
import com.github.markzhl.common.vo.TreeNode;

/**
 *  2017/6/12.
 */
public class MenuTree extends TreeNode {
    String icon;
    String title;
    String href;
    boolean spread = false;

    public MenuTree() {
    }

    public MenuTree(int id, String name, int parentId) {
        this.id = id;
        this.parentId = parentId;
        this.title = name;
    }
    public MenuTree(int id, String name, MenuTree parent) {
        this.id = id;
        this.parentId = parent.getId();
        this.title = name;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }
}
