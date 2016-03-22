package com.weixin.dao;

import java.util.List;

import com.weixin.model.WeixinWorkGoingOutModel;

public interface IWeixinWorkGoingOutDao {
    
    public long addWorkGoingOut(WeixinWorkGoingOutModel wgom);
    
    public List<WeixinWorkGoingOutModel> getWorkGoingOutIdById(int id);
}
