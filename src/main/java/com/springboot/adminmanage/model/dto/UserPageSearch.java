package com.springboot.adminmanage.model.dto;

import javafx.scene.input.PickResult;
import lombok.Data;

import java.io.Serializable;

/**
 * author: ouyang
 * Date:2020/2/9 16:08
 **/
@Data
public class UserPageSearch implements Serializable {

    private String username;

    private  String telephone;

    private  String nickname;


}
