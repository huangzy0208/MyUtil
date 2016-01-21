package com.custom.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 与具体ORM实现无关的分页参数及查询结果封装.
 * <p/>
 *
 * @param <T> Page中记录的类型.
 * @author liuguo
 */
public class Page<T> implements Serializable {

    //-- 公共变量 --//
    public static final String ASC = "asc";

    public static final String DESC = "desc";

    private static final int NUM2 = 2;

    private static final int NUM3 = 3;

    private static final int NUM4 = 4;

    private static final long serialVersionUID = 2697188762117078424L;

    //-- 分页参数 --//
    //页码
    protected int pageNo = 1;

    //每页记录数  默认为-1(不分页)
    protected int pageSize = 5;

    //排序字段
    protected String orderBy;

    //排序方向
    protected String order;

    //自动统计数据总数开关
    protected boolean autoCount = true;

    //总记录数
    protected long totalCount;

    //返回当前页结果
    protected List<T> currentPageResult = new ArrayList<T>();

    //总页数
    private long totalPages;

    //自定义初始页
    private int startPageNo;

    //自定义末尾页
    private int endPageNo;

    //默认构造函数
    public Page() {
    }

    //分页参数访问函数
    public Page(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获得当前页的页号,序号从1开始,默认为1.
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
     */
    public void setPageNo(final int pageNo) {
        this.pageNo = 1 > pageNo ? 1 : pageNo;
    }

    /**
     * 返回Page对象自身的setPageNo函数,可用于连续设置。
     */
    public Page<T> pageNo(final int thePageNo) {
        setPageNo(thePageNo);
        return this;
    }

    /**
     * 获得每页的记录数量, 默认为-1.
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置每页的记录数量.
     */
    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
     */
    public int getStartIndex() {
        return (pageNo - 1) * pageSize + 1;
    }

    /**
     * 根据pageNo和pageSize计算当前页最后一条记录在总结果集中的位置,序号从pageSize开始.
     */
    public int getLastIndex() {
        return pageNo * pageSize;
    }

    /**
     * 获得排序字段,无默认值. 多个排序字段时用','分隔.
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * 设置排序字段,多个排序字段时用','分隔.
     */
    public void setOrderBy(final String orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * 设置排序方向.
     *
     * @param order 可选值为desc或asc,多个排序字段时用','分隔.
     */
    public void setOrder(final String order) {
        String lowcaseOrder = StringUtils.lowerCase(order);

        //检查order字符串的合法值
        String[] orders = StringUtils.split(lowcaseOrder, ',');
        for (String orderStr : orders) {
            if (!StringUtils.equals(DESC, orderStr) && !StringUtils.equals(ASC, orderStr)) {
                throw new RuntimeException("排序方向" + orderStr + "不是合法值");
            }
        }

        this.order = lowcaseOrder;
    }

    /**
     * 获得order值
     *
     * @return
     */
    public String getOrder() {
        return this.order;
    }

    /**
     * 设置查询对象时是否自动先执行count查询获取总记录数.
     */
    public void setAutoCount(final boolean autoCount) {
        this.autoCount = autoCount;
    }

    //-- 访问查询结果函数 --//

    /**
     * 获得当前页内的记录列表.
     */
    public List<T> getCurrentPageResult() {
        return currentPageResult;
    }

    /**
     * 设置当前页内的记录列表.
     */
    public void setCurrentPageResult(final List<T> currentPageResult) {
        this.currentPageResult = currentPageResult;
    }

    /**
     * 获得总记录数, 默认值为1.
     */
    public long getTotalCount() {
        return totalCount;
    }

    /**
     * 总记录数决定总页数,所以设置总页数的方法也写在这个方法内
     * 设置总记录数,总页数,以及判断当前页是否大于最大页
     */
    public void setTotalCount(final long totalCount) {
        //设置总记录数
        this.totalCount = totalCount;
        //设置总页数
        if (totalCount > 0) {
            long count = totalCount / pageSize;
            if (totalCount % pageSize > 0) {
                totalPages = count + 1;
            } else {
                totalPages = count;
            }
        }
        //判断当前页是否超过最大页,如果大于则当前页等于最大页
        if (totalPages < pageNo) {
            pageNo = (int) totalPages;
        }
    }

    /**
     * 获得总页数
     */

    public long getTotalPages() {
        return totalPages;
    }

    /**
     * 是否还有下一页.
     */
    public boolean isHasNext() {
        return pageNo + 1 <= getTotalPages();
    }

    public boolean isHasNext1() {
        return pageNo + 1 < getTotalPages();
    }

    public boolean isHasNext2() {
        return pageNo + NUM2 < getTotalPages();
    }

    public boolean isHasNext3() {
        return pageNo + NUM3 < getTotalPages();
    }

    public boolean isHasNext4() {
        return pageNo + NUM4 < getTotalPages();
    }

    /**
     * 取得下页的页号, 序号从1开始.
     * 当前页为尾页时仍返回尾页序号.
     */
    public int getNextPage() {
        if (isHasNext()) {
            return pageNo + 1;
        } else {
            return pageNo;
        }
    }

    public int getNextPage2() {
        if (isHasNext()) {
            return pageNo + NUM2;
        } else {
            return pageNo;
        }
    }

    public int getNextPage3() {
        if (isHasNext()) {
            return pageNo + NUM3;
        } else {
            return pageNo;
        }
    }

    /**
     * 是否还有上一页.
     */
    public boolean isHasPre() {
        return pageNo - 1 >= 1;
    }

    public boolean isHasPre1() {
        return pageNo - 1 > 1;
    }

    public boolean isHasPre2() {
        return pageNo - NUM2 > 1;
    }

    public boolean isHasPre3() {
        return pageNo - NUM3 > 1;
    }

    public boolean isHasPre4() {
        return pageNo - NUM4 > 1;
    }

    /**
     * 取得上页的页号, 序号从1开始.
     * 当前页为首页时返回首页序号.
     */
    public int getPrePage() {
        if (isHasPre()) {
            return pageNo - 1;
        } else {
            return pageNo;
        }
    }

    public int getPrePage2() {
        if (isHasPre()) {
            return pageNo - NUM2;
        } else {
            return pageNo;
        }
    }

    public int getPrePage3() {
        if (isHasPre()) {
            return pageNo - NUM3;
        } else {
            return pageNo;
        }
    }

    public int getStartPageNo() {
        return startPageNo;
    }

    public void setStartPageNo(int startPageNo) {
        this.startPageNo = startPageNo;
    }

    public int getEndPageNo() {
        return endPageNo;
    }

    public void setEndPageNo(int endPageNo) {
        this.endPageNo = endPageNo;
    }

    @Override
    public String toString() {
        return "Page{" +
               "pageNo=" + pageNo +
               ", pageSize=" + pageSize +
               ", orderBy='" + orderBy + '\'' +
               ", order='" + order + '\'' +
               ", autoCount=" + autoCount +
               ", totalCount=" + totalCount +
               ", currentPageResult=" + currentPageResult +
               ", totalPages=" + totalPages +
               '}';
    }

}
