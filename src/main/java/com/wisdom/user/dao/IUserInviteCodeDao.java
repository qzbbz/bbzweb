package com.wisdom.user.dao;

import com.wisdom.common.model.UserInviteCode;

/**
 * Created by kcchai on 2015/4/30.
 */
public interface IUserInviteCodeDao {

    public UserInviteCode getUserInviteCodeByInviteCode(String inviteCode);

    public boolean addUserInviteCode(String userId, String inviteCode);

}
