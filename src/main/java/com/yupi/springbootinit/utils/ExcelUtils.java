package com.yupi.springbootinit.utils;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class ExcelUtils {
    /**
     * excel 转 csv
     *
     * @param multipartFile
     * @return
     */
   public static String  excelToCsv(MultipartFile multipartFile) {
       List<Map<Integer,String>> list = null;
       //读取数据
       try {
           list = EasyExcel.read(multipartFile.getInputStream())
                   .excelType(ExcelTypeEnum.XLSX)
                   .sheet()
                   .headRowNumber(0)  //0代表第一行即表头
                   .doReadSync();
       } catch (IOException e) {
           log.error("表格读取错误",e);
       }
       if(CollUtil.isEmpty(list))){
            return "";
       }
       StringBuilder builder = new StringBuilder();
       //将数据转换成csv格式
       Map<Integer,String> headMap = list.get(0); //读取表头数据
       List<String> headList = headMap.values().stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
       builder.append(StringUtils.join(headList,",")).append("\n");
       for(int i = 1;i<list.size();i++) {
           Map<Integer,String> dataMap = list.get(i);
           List<String> dataList = dataMap.values().stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
           builder.append(StringUtils.join(dataList,",")).append("\n");
       }
       return builder.toString();
   }

}
