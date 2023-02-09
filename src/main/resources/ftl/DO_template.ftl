package ${packageName}.model;

import lombok.Data;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.Date;

@Data
public class ${tableUpperCamelName}DO implements Serializable {

    private static final long serialVersionUID = 1L;

<#list columnDataList as columnData>
    /**
     * ${columnData.comment}
     */
    private ${columnData.columnType} ${columnData.columnCamelName};

</#list>
}