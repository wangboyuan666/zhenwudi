package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
@Data
@TableName("tb_cart")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
public class Cart extends BasePojo{
	
	private static final long serialVersionUID = 8959277839743347211L;
	@TableId(type = IdType.AUTO)
	private Long id;
	private Long userId;
	private Long itemId;
	private String ItemTitle;
	private String ItemImage;
	private Long ItemPrice;
	private Integer num;
	
}
