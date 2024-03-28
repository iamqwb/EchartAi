package com.yupi.springbootinit.model.dto.chart;

import lombok.Data;

import java.io.Serializable;


/**
 * 创建请求
 *
 * 
 * 
 */
@Data
public class GenChartByAiRequest implements Serializable {
    /**
     * 分析目标
     */
    private String goal;

    /**
     * 名称
     */
    private String name;

    /**
     * 图表类型
     */
    private String chartType;

    private static final long serialVersionUID = 1L;
}