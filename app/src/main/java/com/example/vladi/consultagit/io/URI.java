package com.example.vladi.consultagit.io;

public class URI {

    private String queryParam;
    private String sort;
    private int page;
    private String pathParam1;
    private String pathParam2;
    private String pathParam3;

    public String getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(String queryParam) {
        this.queryParam = queryParam;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getPathParam1() {
        return pathParam1;
    }

    public void setPathParam1(String pathParam1) {
        this.pathParam1 = pathParam1;
    }

    public String getPathParam2() {
        return pathParam2;
    }

    public void setPathParam2(String pathParam2) {
        this.pathParam2 = pathParam2;
    }

    public String getPathParam3() {
        return pathParam3;
    }

    public void setPathParam3(String pathParam3) {
        this.pathParam3 = pathParam3;
    }

    public Integer loadAddPage (){
        return this.page++;
    }
}
