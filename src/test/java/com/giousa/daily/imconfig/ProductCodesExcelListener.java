package com.giousa.daily.imconfig;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class ProductCodesExcelListener extends AnalysisEventListener<ProductCodesDTO> {

    private Map<String, List<String>> scMap = Maps.newHashMap();

    private List<String> scList = Lists.newArrayList();
    @Override
    public void invoke(ProductCodesDTO productCodesDTO, AnalysisContext analysisContext) {
        System.out.println("invoke 读取数据：" + productCodesDTO);
        scList.add(productCodesDTO.getServiceCode());
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        List<String> collect = scList.stream().distinct().collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect));
    }
}
