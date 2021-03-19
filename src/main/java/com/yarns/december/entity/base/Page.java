package com.yarns.december.entity.base;

import org.apache.ibatis.type.Alias;

/**
 * @author Yarns
 */
@Alias("page")
public class Page {

    /**
     * 每页显示记录数
     */
    private int showCount;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 总记录数
     */
    private int totalResult;
    /**
     * 当前页
     */
    private int currentPage;
    /**
     * 当前记录起始索引
     */
    private int currentResult;
    /**
     * true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性
     */
    private boolean entityOrField;
    /**
     * 最终页面显示的底部翻页导航，详细见：getPageStr();
     */
    private String pageStr;
    /**
     * 数据
     */
    private PageData pd = new PageData();
    /**
     * 是否需要分页
     */
    private boolean requirePageBreak = true;


    public Page() {
        try {
            this.showCount = 10;
        } catch (Exception e) {
            this.showCount = 15;
        }
    }

    private int getTotalPage() {
        if (totalResult % showCount == 0) {
            totalPage = totalResult / showCount;
        } else {
            totalPage = totalResult / showCount + 1;
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public int getCurrentPage() {
        if (currentPage <= 0) {
            currentPage = 1;
        }
        if (currentPage > getTotalPage()) {
            currentPage = getTotalPage();
        }
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageStr(String pageStr) {
        this.pageStr = pageStr;
    }

    public int getShowCount() {
        return showCount;
    }

    public void setShowCount(int showCount) {

        this.showCount = showCount;
    }

    public boolean isRequirePageBreak() {
        return requirePageBreak;
    }

    public void setRequirePageBreak(boolean requirePageBreak) {
        this.requirePageBreak = requirePageBreak;
    }

    public int getCurrentResult() {
        return currentResult;
    }

    public void setCurrentResult(int currentResult) {
        this.currentResult = currentResult;
    }

    public boolean isEntityOrField() {
        return entityOrField;
    }

    public void setEntityOrField(boolean entityOrField) {
        this.entityOrField = entityOrField;
    }

    public PageData getPd() {
        return pd;
    }

    public void setPd(PageData pd) {
        this.pd = pd;
    }

    @Override
    public String toString() {
        return "Page [showCount=" + showCount + ", totalPage=" + totalPage
                + ", totalResult=" + totalResult + ", currentPage="
                + currentPage + ", currentResult=" + currentResult
                + ", entityOrField=" + entityOrField + ", pageStr=" + pageStr
                + ", pd=" + pd + "]";
    }

}
