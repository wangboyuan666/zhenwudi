package com.jt.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class EasyUITree implements Serializable{
	/**
	 * 用于封装商品分类信息树对象
	 */
	private static final long serialVersionUID = -3374824151482249728L;
	private Long id; //节点的id值
	private String text; // 节点的名称
	private String state ; // 状态 默认为closed
	/**
	 * 如果包含子节点 ,后期维护
	 */
}

