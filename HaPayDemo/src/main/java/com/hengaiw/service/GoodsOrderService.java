package com.hengaiw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hengaiw.model.dao.mapper.GoodsOrderMapper;
import com.hengaiw.model.dao.model.GoodsOrder;
import com.hengaiw.model.dao.model.GoodsOrderExample;
import com.hengaiw.util.Constant;


/**
 
 */
@Component
public class GoodsOrderService {

    @Autowired
    private GoodsOrderMapper goodsOrderMapper;

   /*
    `Status` tinyint(6) NOT NULL DEFAULT '0' COMMENT '订单状态,订单生成(0),支付成功(1),处理完成(2),处理失败(-1)',
    */

    public int addGoodsOrder(GoodsOrder goodsOrder) {
        return goodsOrderMapper.insertSelective(goodsOrder);
    }

    public GoodsOrder getGoodsOrder(String goodsOrderId) {
        return goodsOrderMapper.selectByPrimaryKey(goodsOrderId);
    }

    public int updateStatus4Success(String goodsOrderId) {
        GoodsOrder goodsOrder = new GoodsOrder();
        goodsOrder.setStatus(Constant.GOODS_ORDER_STATUS_SUCCESS);
        GoodsOrderExample example = new GoodsOrderExample();
        GoodsOrderExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsOrderIdEqualTo(goodsOrderId);
        criteria.andStatusEqualTo(Constant.GOODS_ORDER_STATUS_INIT);
        return goodsOrderMapper.updateByExampleSelective(goodsOrder, example);
    }

    public int updateStatus4Complete(String goodsOrderId) {
        GoodsOrder goodsOrder = new GoodsOrder();
        goodsOrder.setStatus(Constant.GOODS_ORDER_STATUS_COMPLETE);
        GoodsOrderExample example = new GoodsOrderExample();
        GoodsOrderExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsOrderIdEqualTo(goodsOrderId);
        criteria.andStatusEqualTo(Constant.GOODS_ORDER_STATUS_SUCCESS);
        return goodsOrderMapper.updateByExampleSelective(goodsOrder, example);
    }

    public int updateStatus4Fail(String goodsOrderId) {
        GoodsOrder goodsOrder = new GoodsOrder();
        goodsOrder.setStatus(Constant.GOODS_ORDER_STATUS_FAIL);
        GoodsOrderExample example = new GoodsOrderExample();
        GoodsOrderExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsOrderIdEqualTo(goodsOrderId);
        //criteria.andStatusEqualTo(Constant.GOODS_ORDER_STATUS_SUCCESS);
        return goodsOrderMapper.updateByExampleSelective(goodsOrder, example);
    }

    public int update(GoodsOrder goodsOrder) {
        return goodsOrderMapper.updateByPrimaryKeySelective(goodsOrder);
    }

}
