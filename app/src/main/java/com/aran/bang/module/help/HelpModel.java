package com.aran.bang.module.help;

import com.aran.bang.module.UserModel;

import cn.bmob.v3.BmobObject;

/**
 * Created by Yat3s on 6/17/16.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class HelpModel extends BmobObject {
    public String title;

    public String description;

    public String image;

    public UserModel host;

    public void setHost(UserModel host) {
        this.host = host;
    }
}
