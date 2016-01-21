package com.sdeport.common;


import java.util.List;

/**
 * grid返回bean
 */
public class GridResult<T> {

    String isOk = "";
    List<T> rows = null;
    String total = "";
    String page = "";
    String records = "";

    public GridResult(final List<T> rows, final GridRequest gridRequest,
                      final long totalCnt) {
        this.setRows(rows);
        this.setPage(String.valueOf(gridRequest.getPage()));
        this.setIsOk("1");
        this.setTotal(String.valueOf(totalCnt / gridRequest.getRows() + (0 == totalCnt % gridRequest.getRows() ? 0 : 1)));
        this.setRecords(String.valueOf(totalCnt));
    }

    public String getIsOk() {
        return this.isOk;
    }

    public void setIsOk(final String isOk) {
        this.isOk = isOk;
    }

    public List<T> getRows() {
        return this.rows;
    }

    public void setRows(final List<T> rows) {
        this.rows = rows;
    }

    public String getTotal() {
        return this.total;
    }

    public void setTotal(final String total) {
        this.total = total;
    }

    public String getPage() {
        return this.page;
    }

    public void setPage(final String page) {
        this.page = page;
    }

    public String getRecords() {
        return this.records;
    }

    public void setRecords(final String records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "GridResult{" +
               "isOk='" + isOk + '\'' +
               ", rows=" + rows +
               ", total='" + total + '\'' +
               ", page='" + page + '\'' +
               ", records='" + records + '\'' +
               '}';
    }
}
