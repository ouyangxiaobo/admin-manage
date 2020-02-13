package com.springboot.adminmanage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysPermission implements Serializable {

    private static final long serialVersionUID = -6525908145032868837L;

    private Integer id;

    private Integer parentid;

    private String name;

    private String css;

    private String href;

    private Integer type;

    private String permission;

    private Integer sort;

    private List<SysPermission> child;





    @Override
    public String toString() {
        return "SysPermission{" +
                "id=" + id +
                ", parentid=" + parentid +
                ", name='" + name + '\'' +
                ", css='" + css + '\'' +
                ", href='" + href + '\'' +
                ", type=" + type +
                ", permission='" + permission + '\'' +
                ", sort=" + sort +
                '}';
    }
}