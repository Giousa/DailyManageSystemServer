package ${Package}.model;

import lombok.Data;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "${TableName}DO", description = "")
public class ${TableName}DO implements Serializable{

private static final long serialVersionUID = 1L;

<#list columnDataList as columnData>
    /**
     * ${columnData.comment}
     */
    @ApiModelProperty(value = "${columnData.comment}")
    private ${columnData.type} ${columnData.columnHumpName};

</#list>
}