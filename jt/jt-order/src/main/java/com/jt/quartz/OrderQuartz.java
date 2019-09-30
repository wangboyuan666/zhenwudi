package com.jt.quartz;

import java.util.Calendar;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.pojo.Order;
import com.jt.mapper.OrderMapper;

//准备订单定时任务
@Component
public class OrderQuartz extends QuartzJobBean{

	@Autowired
	private OrderMapper orderMapper;

	/**当用户订单提交30分钟后,如果还没有支付.则交易关闭
	 * 现在时间 - 订单创建时间 > 30分钟  则超时
	 * new date - 30 分钟 > 订单创建时间
	 * 将超时的订单的状态码有1改为6
	 */
	@Override
	@Transactional
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		//设定30分钟超时
//		Calendar calendar = Calendar.getInstance();
//		calendar.add(Calendar.MINUTE, -30);
//		Date date = calendar.getTime();
//		Order order = new Order();
//		order.setStatus(6);
//		order.setUpdated(new Date());
//		UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
//		updateWrapper.eq("status", "1").lt("created",date);
//		orderMapper.update(order, updateWrapper);
//		System.out.println("定时任务执行");
		//获取当前的时间的日历对象
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, -30);
		Date timeOut = calendar.getTime();
		Order order = new Order();
		order.setStatus(6).setUpdated(new Date());
		UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("status", 1).lt("created",timeOut );
		orderMapper.update(order, updateWrapper);
		System.out.println("定时任务执行");
	}
}
