package com.yixin.js.common.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by apple on 16/6/6.
 */
@MappedSuperclass
public class BaseEntity implements Serializable {


    private static final long serialVersionUID = 8403496616558199001L;

     @Id
     @Column
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @Column(name = "create_by")
     private String createBy;

     @Column(name = "create_date")
     private Date createTime;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
