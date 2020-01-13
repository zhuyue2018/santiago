package com.santiago.commons.dto.req;

import java.util.List;

/**
 * @program: sac-fa
 * @description:
 * @author: zhuyue
 * @create: 2019-11-21 10:42
 **/
public class Page {
    private Integer pageNum;
    private Integer pageSize;
    private List<Sort> sort;

    public Page() {
    }

    public Page(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<Sort> getSort() {
        return sort;
    }

    public void setSort(List<Sort> sort) {
        this.sort = sort;
    }

    public static Page handlePage(Page page) {
        if (null == page) {
            page = Page.getDefaultPage();
        }
        if (null == page.getPageNum()) {
            page.setPageNum(1);
        }
        if (null == page.getPageSize()) {
            page.setPageSize(10);
        }
        return page;
    }



    public static Page getDefaultPage() {
        return new Page(1, 10);
    }
}
