package com.lyz.security.common.remote.page;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/31 16:48
 */
@Getter
@Setter
public class RemotePage<T> implements Serializable {
    private static final long serialVersionUID = 7496308334162518721L;

    public RemotePage(List<T> list, long total, long pages, long pageNum, long pageSize, boolean hasNextPage) {
        this.list = list;
        this.total = total;
        this.pages = pages;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.hasNextPage = hasNextPage;
    }

    /**
     * 结果集
     */
    private List<T> list;

    /**
     * 总记录数
     */
    private long total;

    /**
     * 总页数
     */
    private long pages;

    /**
     * 当前页
     */
    private long pageNum;

    /**
     * 每页的数量
     */
    private long pageSize;

    /**
     * 是否有下一页
     */
    private boolean hasNextPage = false;
}
