package com.aran.bang.module;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by Yat3s on 6/17/16.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class UserModel extends BmobUser {
    public String avatar;

    public String nickname;

    public BmobRelation helps;

}
