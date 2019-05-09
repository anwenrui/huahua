package com.an.friend.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tb_friend")
@Data
@IdClass(Friend.class)
public class Friend implements Serializable {

    private static final long serialVersionUID = -9218332446981878235L;

    @Id
    private String userid;
    @Id
    private String friendid;

    private String islike;

}