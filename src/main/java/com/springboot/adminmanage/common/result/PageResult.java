package com.springboot.adminmanage.common.result;

import lombok.Data;

import java.io.Serializable;

/**
 * author: ouyang
 * Date:2020/2/7 20:22
 **/
@Data
public class PageResult implements Serializable {

    private Integer page; //穿过来的页数
    private Integer limit; //每页多少
    private Integer offset; //默认

    public void countPage() {
        if (this.page == null || this.limit == null) {
           this.offset=0;
           return;
        }
        this.offset=(this.page-1)*limit;

    }
}
