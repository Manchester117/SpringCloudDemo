package com.user.product.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tb_user")                // 实体和表进行关联
public class User {
    @Id                                 // 确定主键
    @KeySql(useGeneratedKeys = true)    // 返回主键
    private Long id;
    private String username;
    private String password;
    private String name;
    private Integer age;
    private Integer sex;
    private Date birthday;
    private String note;
    private Date created;
    private Date updated;
}
