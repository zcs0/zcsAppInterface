package cn.com.app.assistcode;

/**
 * Created by user on 2019/3/12.
 * 分页查询工具类
 */
public class Page {
    private Long count;
    private Integer startRow = 0;
    private Integer limit = 10;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
