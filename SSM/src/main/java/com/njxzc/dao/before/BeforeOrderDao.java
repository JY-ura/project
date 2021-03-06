package com.njxzc.dao.before;

import com.njxzc.po.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public interface BeforeOrderDao {
    public int addOrderBase(Order order);
    public int addOrderDetail(Map<String, Object> map);
    public List<Map<String, Object>> selectGoodsShop(Integer uid);
    public int updateStore(Map<String, Object> map);
    public int clear(Integer uid);
    public int pay(Integer ordersn);
}
