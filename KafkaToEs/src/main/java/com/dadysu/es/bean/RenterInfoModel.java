package com.dadysu.es.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * renter_info  注意要加 @Data 或者get set方法
 * @author
 */

@Data
@Document(indexName = "renter_info", shards = 1 , replicas = 1)
public class RenterInfoModel implements Serializable {

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
     * 审核状态：0-未提交 1-已提交未审核 2-审核通过 3-审核不通过
     */
    private Integer auditState;

    /**
     * 审核申请提交时间
     */
    private Date auditApplyTime;

    /**
     * 审核时间
     */
    private Date auditTime;

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

    /**
     * 锁定状态 0：未锁定  1：已锁定
     */
    private Integer lockState;

    /**
     * 删除标志 0-未删除；1：已删除
     */
    private Integer delState;

    /**
     * 最后登录城市（城市编码字典）
     */
    private String lastLoginCity;

    /**
     * 最近上线时间
     */
    private Date lastLogonTime;

    /**
     * 最近上线经度
     */
    private Double lastLogonLng;

    /**
     * 最近上线纬度
     */
    private Double lastLogonLat;

    /**
     * 注册城市（城市编码字典）
     */
    private String registerCity;

    /**
     * 注册城市名称
     */
    @Field(type = FieldType.Keyword)
    private String cityName;

    /**
     * 通过司机拉新邀请人的分享链接注册的用户，此处记录司机ID
     */
    private Integer driverInviterId;

    /**
     * 通过邀请人的分享链接注册的用户，此处记录邀请人ID
     */
    private Integer inviterId;

    /**
     * 余额
     */
    private Integer accBalance;

    /**
     * 家住址位置经度
     */
    private Double homeLng;

    /**
     * 家住址位置纬度
     */
    private Double homeLat;

    /**
     * 家住址位置信息
     */
    private String homeLocation;

    /**
     * 公司位置经度
     */
    private Double companyLng;

    /**
     * 公司位置纬度
     */
    private Double companyLat;

    /**
     * 公司位置信息
     */
    private String companyLocation;

    /**
     * 用户评分
     */
    private Double renterScore;

    /**
     * 用户星级。5:表示5颗星 4：表示4颗星 3：表示3颗星。。。
     */
    private Double star;

    /**
     * 当前已被评价的订单数：包括司机主动评分的订单数和系统到期自动评分的订单数
     */
    private Integer appraiseOrderNum;

    /**
     * 未完成订单id，接单时写入；取消订单、完成订单时清除
     */
    private String unfinished;

    /**
     * 用户当天有责取消订单的次数
     */
    private Integer cancelCount;

    /**
     * 加入黑名单时间
     */
    private Date joinBlackTime;

    /**
     * 退出黑名单时间
     */
    private Date exitBlackTime;

    /**
     * 用户注册来源，通过h5页面注册会有该字段，用来区分推广厂商，用来结酬金。存储格式为：0001、0002.....0010
     */
    private String renterSource;

    /**
     * 审核标识，1.人工审核 2.自动审核
     */
    private Integer auditFlag;

    /**
     * 分时租赁-驾驶证照片URL
     */
    private String driveLicImg;

    /**
     * 分时租赁-驾驶证副本照片URL
     */
    private String driveLicImgCopy;

    /**
     * 分时租赁-初次领证日期
     */
    private Date firstIssueDate;

    /**
     * 分时租赁-押金金额
     */
    private Integer foregiftAmount;

    /**
     * 分时租赁-是否已通知
     */
    private Integer isNotice;

    /**
     * 分时租赁-驾驶证号
     */
    private String driveLicenceNum;

    /**
     * 分时租赁-档案编号
     */
    private String fileNumber;

    /**
     * 分时租赁-准驾车型
     */
    private String driveCarType;

    private String field1;

    private String field2;

    private String field3;

    private String field4;

    private String field5;

    /**
     * 是否拉新用户1：是，0：否
     */
    private Byte isInvite;

    /**
     * 预约单未完成订单id，接单时写入；取消订单、完成订单时清除
     */
    private String unfinishedDelay;

    /**
     * 风险等级，0：未拉取，1：正常，2：低，3：中，4：高
     */
    private Integer renterRisk;

    /**
     * 风险分值
     */
    private Integer renterRiskScore;

    /**
     * 风险值标记类型(1，系统自动标记；2，手动设置)
     */
    private Integer riskOptType;

    /**
     * 风险值更新时间
     */
    private Date riskUpdateTime;

    /**
     * 认证状态，1：实名认证 2：驾驶证认证
     */
    private Integer authState;

    /**
     * 行业id
     */
    private String industryId;

    /**
     * 行业名称
     */
    private String industryName;

    /**
     * 职业
     */
    private String occupationName;

    /**
     * 公司
     */
    private String renterCompany;

    private Integer isAdSource;

    private static final long serialVersionUID = 1L;
}
