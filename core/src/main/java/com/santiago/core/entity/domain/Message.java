package com.santiago.core.entity.domain;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Message implements Delayed {

    /** 创建时间 10分钟以后, 测试可以降低该值 */
    private static final int SECOND_NUM = 600;

	/**订单id*/
    private Long id;
    /**订单状态*/
    private String state;
    /**订单创建时间*/
    private Date createTime;


    public Message(Long id, String state, Date createTime) {
        this.id = id;
        this.state = state;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	/**
	 *获取订单过期时间
	 */
    private Date getCancelTime(Date createTime) {
        return Date.from(createTime.toInstant().plusSeconds(SECOND_NUM));
    }

	/**
	 * 判断元素是否过期(小于等于0时过期)
	 *
	 */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(getCancelTime(createTime).getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", createTime=" + createTime +
                '}';
    }

	/**
	 * 根据元素过期时间排序, 哪个元素最早到过期时间, 就排在前面 
	 *
	 */
    @Override
    public int compareTo(Delayed o) {
        Message msg = (Message) o;
        return Long.compare(this.createTime.getTime(), msg.getCreateTime().getTime());
    }
}