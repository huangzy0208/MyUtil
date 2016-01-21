package com.sdeport.common;


/**
 * grid请求bean
 */
public class GridRequest {

    int rows = 1;
    int page;
    int start;
    int end;

    public int getRows() {
        return this.rows;
    }

    public void setRows(final int rows) {
        this.rows = rows;
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(final int page) {
        this.page = page;
    }

    public int getEnd() {
        return (page * rows-1)>0?(page * rows):1;
    }
    public int getStart() {
        return (page - 1) * rows+1;
    }

    @Override
    public String toString() {
        return "GridRequest{" +
               "rows=" + rows +
               ", page=" + page +
               ", start=" + getStart() +
               ", end=" + getEnd() +
               '}';
    }



}
