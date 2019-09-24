package com.example.springclouddemouser.user.entity;

import lombok.Data;

/**
 * @Description 用户返回积分
 * @Author hnuya
 * @Date 2019/9/24
 **/
@Data
public class UserBonusPoints {

    private Integer id;

    private Long userUuid;

    private Integer points;
}
