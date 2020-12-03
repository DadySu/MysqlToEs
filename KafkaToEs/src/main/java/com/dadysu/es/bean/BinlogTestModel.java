package com.dadysu.es.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * binlog_test  注意要加 @Data 或者get set方法
 *
 * @author
 */

@Data
@Document(indexName = "binlog_test", shards = 1, replicas = 1)
public class BinlogTestModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Field(index = false, store = true, type = FieldType.Keyword)
    private Integer id;
    @Field(type = FieldType.Integer, store = true)
    private Integer renterType;
    /**
     * 手机号
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", store = true)
    private String mobile;
    /**
     * 密码  如果不使用 @Field es会自动给字段创建索引
     */
    private String pwd;
    /**
     * 昵称
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", store = true)
    private String nickName;
    /**
     * 姓名
     */
    private String fullName;
    /**
     * 性别1：男  2：女
     */
    private Integer gender;
    /**
     * 出生日期
     */
    private Date birthdate;
    /**
     * 大头像
     */
    private String headBig;
    /**
     * 小头像
     */
    private String headSmall;
    /**
     * 身份证号
     */
    @Field(type = FieldType.Keyword, store = true)
    private String idCard;
    /**
     * 身份证照片（正面）URL
     */
    private String idCardImgHead;
    /**
     * 身份证照片（反面）URL
     */
    private String idCardImgBack;
    /**
     * 是否加入了黑名单 0-否 1-是
     */
    private Integer blackState;
    /**
     * 备注
     */
    private String remark;
    /**
     * 审核状态
     */
    private Integer auditState;

    /**
     * 审核意见
     */
    private String auditOpinion;
    /**
     * 创建时间
     */
    @Field(type = FieldType.Date, store = true)
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
