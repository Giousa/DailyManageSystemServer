package templates

var MybatisDOWithLombok = `package {{.Package}}.model;

import lombok.Data;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "{{.TableName}}DO", description = "")
public class {{.TableName}}DO implements Serializable{

	private static final long serialVersionUID = 1L;

	{{range .ColumnDataArray}}
	/**
     * {{.ColumnComment}}
     */
    @ApiModelProperty(value = "{{.ColumnComment}}")
    private {{.ColumnType}} {{.ColumnName}};
	{{end}}
}

`

var MybatisMapper = `package {{.Package}}.mapper;

import {{.Package}}.model.{{.TableName}}DO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface {{.TableName}}Mapper {

	int insert({{.TableName}}DO param);

	int update({{.TableName}}DO param);

	int delete(@Param("id") Long id);

	{{.TableName}}DO findById(@Param("id") Long id);

	int countPageQuery{{.TableName}}(PageQuery{{.TableName}}Req param);

	List{{.AngleBracket}}{{.TableName}}DO> pageQuery{{.TableName}}(PageQuery{{.TableName}}Req param);

	List{{.AngleBracket}}{{.TableName}}DO> findByBaseReq(BaseReq req);
}

`

var MybatisMapperXml = `{{.AngleBracket}}?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="{{.Package}}.mapper.{{.TableName}}Mapper">
	<resultMap id="ResultMap" type="{{.Package}}.model.{{.TableName}}DO">
		{{- range .ColumnDataArray}}
			<result column="{{.ColumnSeparate}}" property="{{.ColumnName}}" />
		{{- end}}
	</resultMap>

	<sql id="Base_Column_List">
		{{.AllColumnNames}}
	</sql>

	<sql id="Set_Column">
		<set>
			{{- range .ColumnDataArray}}
				<if test="{{.ColumnName}} != null">{{.ColumnSeparate}}=#{{.LeftBrackets}}{{.ColumnName}}{{.RightBrackets}},</if>
			{{- end}}
		</set>
	</sql>

	<sql id="Page_Query">
		<where>
			<if test="ids != null and ids.size() != 0">
				AND (id in
				<foreach item="item" index="index" collection="ids" open="("
						 separator="," close=")">
					#{item}
				</foreach>
				OR id IS NULL)
			</if>
			{{- range .ColumnDataArray}}
			<if test="{{.ColumnName}} != null">
				AND {{.ColumnSeparate}} = #{{.LeftBrackets}}{{.ColumnName}}{{.RightBrackets}}
			</if>
			{{- end}}
		</where>
	</sql>

	<insert id="insert" useGeneratedKeys="true" keyProperty="id" parameterType="{{.Package}}.model.{{.TableName}}DO">
		INSERT INTO {{.TableSeparateName}} <include refid="Set_Column"/>
	</insert>

	<update id="update" parameterType="{{.Package}}.model.{{.TableName}}DO">
		UPDATE {{.TableSeparateName}}
		<include refid="Set_Column"/>
		WHERE id = #{id}
	</update>

	<delete id="delete">
		DELETE FROM {{.TableSeparateName}}
		WHERE id = #{id}
	</delete>

 	<select id="findById" resultMap="ResultMap">
		SELECT
		<include refid="Base_Column_List"/>
		FROM {{.TableSeparateName}}
		WHERE id = #{id}
	</select>

	<select id="countPageQuery{{.TableName}}" resultType="java.lang.Integer">
		SELECT COUNT(1)
		FROM {{.TableSeparateName}}
		<include refid="Page_Query"/>
	</select>

	<select id="pageQuery{{.TableName}}" resultMap="ResultMap">
		SELECT
		<include refid="Base_Column_List"/>
		FROM {{.TableSeparateName}}
		<include refid="Page_Query"/>
		ORDER BY create_time DESC
		LIMIT #{offset},#{size}
	</select>

	<select id="findByBaseReq" resultMap="ResultMap">
        SELECT
        <include refid="Base_Column_List"/>
        FROM {{.TableSeparateName}}
        <include refid="Page_Query"/>
    </select>

</mapper>

`

var MybatisManager = `package {{.Package}}.manager;

import {{.Package}}.mapper.{{.TableName}}Mapper;
import {{.Package}}.model.{{.TableName}}DO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Component
public class {{.TableName}}Manager {

    @Resource
    private {{.TableName}}Mapper {{.TableCamelName}}Mapper;

    public void insert({{.TableName}}DO {{.TableCamelName}}DO) {
        if ({{.TableCamelName}}Mapper.insert({{.TableCamelName}}DO) {{.AngleBracket}} 1) {
            log.warn("insert. {{.TableCamelName}}DO: {}", {{.TableCamelName}}DO);
            throw new ResultException(ResultExceptionEnum.DB_EXCEPTION_INSERT);
        }
    }

    public void update({{.TableName}}DO {{.TableCamelName}}DO) {
        if ({{.TableCamelName}}Mapper.update({{.TableCamelName}}DO) {{.AngleBracket}} 1) {
            log.warn("update. {{.TableCamelName}}DO: {}", {{.TableCamelName}}DO);
            throw new ResultException(ResultExceptionEnum.DB_EXCEPTION_UPDATE);
        }
    }

	public int delete(Long id) {
        return {{.TableCamelName}}Mapper.delete(id);
    }

    public {{.TableName}}DO findById(Long id) {
        return {{.TableCamelName}}Mapper.findById(id);
    }

    public PageBeanVO{{.AngleBracket}}{{.TableName}}DO> pageQuery{{.TableName}}(PageQueryReq req) {
        int totalCount = {{.TableCamelName}}Mapper.countPageQuery{{.TableName}}(req);
        List{{.AngleBracket}}{{.TableName}}DO> list = null;
        if (totalCount > 0) {
            list = {{.TableCamelName}}Mapper.pageQuery{{.TableName}}(req);
        }
        return DataHandlerUtils.handlePage(list, req.getPage(), req.getSize(), totalCount);
    }

	public List{{.AngleBracket}}{{.TableName}}DO> findByBaseReq(BaseReq req) {
        return {{.TableCamelName}}Mapper.findByBaseReq(req);
    }
}


`

var MybatisController = `package {{.Package}}.controller;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("{{.TableCamelName}}Api")
@Api(value = "", tags = "")
public class {{.TableName}}Controller {

    @Resource
    private {{.TableName}}Manager {{.TableCamelName}}Manager;

    /**
     * 添加
     *
     * @param req
     * @return
     */
    @PostMapping("add{{.TableName}}")
    @ApiOperation(value = "添加", notes = "")
    public ResultVO<Void> add{{.TableName}}(@RequestBody {{.TableName}}Req req) {
        return WrapperTemplate.execute(() -> {
            {{.TableCamelName}}Manager.insert({{.TableName}}Convert.convert(req));
            return null;
        }, "add{{.TableName}}", true, req);
    }

    /**
     * 更新
     *
     * @param req
     * @return
     */
    @PostMapping("update{{.TableName}}")
	@ApiOperation(value = "更新", notes = "")
    public ResultVO<Void> update{{.TableName}}(@RequestBody {{.TableName}}Req req) {
        return WrapperTemplate.execute(() -> {
			Assertion.checkParam(req.getId());
            {{.TableName}}DO {{.TableCamelName}}DO = {{.TableCamelName}}Manager.findById(req.getId());
            if (Objects.isNull({{.TableCamelName}}DO)) {
                throw new ResultException(ResultExceptionEnum.ID_IS_ERROR);
            }
            {{.TableCamelName}}Manager.update({{.TableName}}Convert.convert(req));
            return null;
        }, "update{{.TableName}}", true, req);
    }

    /**
     * 删除
     *
     * @param req
     * @return
     */
    @PostMapping("delete{{.TableName}}ById")
	@ApiOperation(value = "删除", notes = "")
    public ResultVO<Void> delete{{.TableName}}ById(@RequestBody IdReq req) {
        return WrapperTemplate.execute(() -> {
            Assertion.checkParam(req.getId());
            {{.TableName}}DO {{.TableCamelName}}DO = {{.TableCamelName}}Manager.findById(req.getId());
            if (Objects.isNull({{.TableCamelName}}DO)) {
                throw new ResultException(ResultExceptionEnum.ID_IS_ERROR);
            }
            {{.TableCamelName}}Manager.delete(req.getId());
            return null;
        }, "delete{{.TableName}}ById", true, req);
    }

	/**
     * 查询详情
     *
     * @param req
     * @return
     */
    @PostMapping("find{{.TableName}}ById")
	@ApiOperation(value = "查询详情", notes = "")
    public ResultVO{{.AngleBracket}}{{.TableName}}DTO> find{{.TableName}}ById(@RequestBody IdReq req) {
        return WrapperTemplate.execute(() -> {
            Assertion.checkParam(req.getId());
            {{.TableName}}DO {{.TableCamelName}}DO = {{.TableCamelName}}Manager.findById(req.getId());
            if (Objects.isNull({{.TableCamelName}}DO)) {
                throw new ResultException(ResultExceptionEnum.ID_IS_ERROR);
            }
            return {{.TableName}}Convert.convert({{.TableCamelName}}DO);
        }, "find{{.TableName}}ById", true, req);
    }

    /**
     * 分页查询
     *
     * @param req
     * @return
     */
    @PostMapping("pageQuery{{.TableName}}")
	@ApiOperation(value = "分页查询", notes = "")
    public ResultVO{{.AngleBracket}}PageBeanVO{{.AngleBracket}}{{.TableName}}DTO>> pageQuery{{.TableName}}(@RequestBody PageQuery{{.TableName}}Req req) {
        return WrapperTemplate.execute(() -> {
            PageBeanVO{{.AngleBracket}}{{.TableName}}DTO> result = new PageBeanVO{{.AngleBracket}}>();
            PageBeanVO{{.AngleBracket}}{{.TableName}}DO> pageBeanVO = {{.TableCamelName}}Manager.pageQuery{{.TableName}}(req);
            result.setTotalCount(pageBeanVO.getTotalCount());
            result.setPageNum(pageBeanVO.getPageNum());
            result.setPageSize(pageBeanVO.getPageSize());
            result.setTotalPage(pageBeanVO.getTotalPage());
            result.setData(CollectionUtils.isEmpty(pageBeanVO.getData()) ? null : pageBeanVO.getData()
                    .parallelStream()
                    .map({{.TableName}}Convert::convert)
                    .collect(Collectors.toList()));
            return result;
        }, "pageQuery{{.TableName}}", true, req);
    }

}


`

var MybatisConvert = `package {{.Package}}.convert;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class {{.TableName}}Convert {

    public static {{.TableName}}DTO convert({{.TableName}}DO req) {
		if(Objects.isNull(req)){
            return null;
        }
        return null;
    }

    public static {{.TableName}}DO convert({{.TableName}}Req req){
		if(Objects.isNull(req)){
            return null;
        }
        return null;
    }
}

`

var MybatisDTO = `package {{.Package}}.web.dto;

import lombok.Data;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "{{.TableName}}DTO", description = "")
public class {{.TableName}}DTO implements Serializable {

    private static final long serialVersionUID = 1L;

	{{range .ColumnDataArray}}
	/**
     * {{.ColumnComment}}
     */
	@ApiModelProperty(value = "{{.ColumnComment}}")
    private {{.ColumnType}} {{.ColumnName}};
	{{end}}
}

`

var MybatisPageQueryRequest = `package {{.Package}}.web.req;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class PageQuery{{.TableName}}Req extends PageQueryReq{

    private static final long serialVersionUID = 1L;

	/**
     * ID列表
     */
	private List{{.AngleBracket}}Long> ids;
	{{range .ColumnDataArray}}
	/**
     * {{.ColumnComment}}
     */
    private {{.ColumnType}} {{.ColumnName}};
	{{end}}
}

`

var MybatisRequest = `package {{.Package}}.web.req;

import lombok.Data;
import java.util.Date;

@Data
public class {{.TableName}}Req extends LoginHeaderReq{

    private static final long serialVersionUID = 1L;

	{{range .ColumnDataArray}}
	/**
     * {{.ColumnComment}}
     */
    private {{.ColumnType}} {{.ColumnName}};
	{{end}}
}

`

var MybatisJson = `
{
{{- range .ColumnDataArray}}
	"{{.ColumnName}}":"",
{{- end}}
}

`
