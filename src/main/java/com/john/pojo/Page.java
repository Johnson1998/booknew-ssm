package com.john.pojo;

import java.util.List;

/**
 * @author John
 * @create 2021-10-2813:38
 */
public class Page<Book> {
    public static final Integer PAGE_SIZE = 4;
    private Integer pageNo;
    private Integer pageTotal;
    private Integer pageSize;
    private Integer pageTotalCount;
    private List<Book> items;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static Integer getPageSize() {
        return PAGE_SIZE;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return this.pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<Book> getItems() {
        return this.items;
    }

    public void setItems(List<Book> items) {
        this.items = items;
    }

    public Integer getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return this.pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Page(Integer pageNo, Integer pageTotal, Integer pageSize, Integer pageTotalCount, List<Book> items) {
        this.pageSize = PAGE_SIZE;
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        this.pageSize = pageSize;
        this.pageTotalCount = pageTotalCount;
        this.items = items;
    }

    public Page() {
        this.pageSize = PAGE_SIZE;
    }

    public String toString() {
        return "Page{pageNo=" + this.pageNo + ", pageTotal=" + this.pageTotal + ", pageSize=" + this.pageSize + ", pageTotalCount=" + this.pageTotalCount + ", items=" + this.items + "}";
    }
}
