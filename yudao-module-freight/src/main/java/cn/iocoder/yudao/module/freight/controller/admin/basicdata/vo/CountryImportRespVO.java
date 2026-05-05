package cn.iocoder.yudao.module.freight.controller.admin.basicdata.vo;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class CountryImportRespVO {

    @Builder.Default
    private List<String> createNames = new ArrayList<>();

    @Builder.Default
    private List<String> updateNames = new ArrayList<>();

    @Builder.Default
    private Map<String, String> failureNames = new LinkedHashMap<>();

}