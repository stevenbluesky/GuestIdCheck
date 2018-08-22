package cn.com.isurpass.zufang.guestidcheck.po;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author liwenxiang
 * Date:2018/8/22
 * Time:14:51
 */
@javax.persistence.Entity
@Table(name="district" )
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "generator", strategy = "increment")
    private Long id;

    /**用户id*/
    @Column(name = "personid")
    private Long personId;

    /**省份*/
    @Column(name = "provincecode")
    private String provinceCode;

    /**城市*/
    @Column(name = "citycode")
    private String cityCode;

    /**区县*/
    @Column(name = "areascode")
    private String areasCode;

    /**省份名称*/
    @Column(name = "provincename")
    private String provinceName;

    /**城市名称*/
    @Column(name = "cityname")
    private String cityName;

    /**区县名称*/
    @Column(name = "areasname")
    private String areasName;

    /**图片*/
    @Column(name = "districtimg")
    private String districtImg;

    /**小区名称*/
    @Column(name = "districtname")
    private String districtName;

    /**地址*/
    private String address;

    /**房间数量*/
    @Column(name = "roomcount")
    private Integer roomCount;

    /**管理员数量*/
    @Column(name = "managercount")
    private Integer managerCount;

    /**电价 以分为单位*/
    private Integer price;

    private Integer waterprice;
    private Integer hotwaterprice;

    /**均摊费用 以分为单位*/
    @Column(name = "shareamount")
    private Integer shareAmount;

    /**备注*/
    private String remark;

    /**插入时间*/
    @Column(name = "inputdate")
    private Date inputDate;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getShareAmount() {
        return shareAmount;
    }

    public void setShareAmount(Integer shareAmount) {
        this.shareAmount = shareAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAreasCode() {
        return areasCode;
    }

    public void setAreasCode(String areasCode) {
        this.areasCode = areasCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreasName() {
        return areasName;
    }

    public void setAreasName(String areasName) {
        this.areasName = areasName;
    }

    public String getDistrictImg() {
        return districtImg;
    }

    public void setDistrictImg(String districtImg) {
        this.districtImg = districtImg;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }

    public Integer getManagerCount() {
        return managerCount;
    }

    public void setManagerCount(Integer managerCount) {
        this.managerCount = managerCount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public Integer getWaterprice() {
        return waterprice;
    }

    public void setWaterprice(Integer waterprice) {
        this.waterprice = waterprice;
    }

    public Integer getHotwaterprice() {
        return hotwaterprice;
    }

    public void setHotwaterprice(Integer hotwaterprice) {
        this.hotwaterprice = hotwaterprice;
    }


}

