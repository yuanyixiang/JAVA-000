基于电商交易场景（用户、商品、订单）

用户

```mysql
DROP TABLE IF EXISITS t_user
CREATE TABLE t_user
(
  `user_id`	int NOT NULL AUTO_INCREMENT COMMENT '用户id主键',
  `user_name` varchar(30)	NOT NULL COMMENT '名称',
  `user_address` varchar(50) NOT NULL COMMENT '地址',
  `user_phoneNum` varchar(11) NOT NULL COMMENT '手机号码',
  `user_email` varchar(30) NULL COMMENT '邮箱',
  `user_createTime` timestamp NOT NULL COMMENT '创建时间',
  `user_updateTime` timestamp NOT NULL COMMENT '更新时间',
  PRIMARY KEY(user_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
```

商品

```mysql
DROP TABLE IF EXISITS t_goods
CREATE TABLE t_goods
(
  `goods_id` int NOT NULL AUTO_INCREMENT COMMENT '商品id主键',
  `goods_type` varchar(30) NOT NULL COMMENT '商品类型',
  `goods_name` varchar(30) NOT NULL COMMENT '商品名称',
  `goods_price` double NOT NULL COMMENT '商品价格',
  `goods_stock` int NOT NULL DEFAULT '0' COMMENT '库存',
  `goods_createTime` timestamp NOT NULL COMMENT '创建时间',
  `goods_updateTime` timestamp NOT NULL COMMENT '更新时间',
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品';
```

订单

```mysql
DROP TABLE IF EXISITS t_order
CREATE TABLE t_order
(
  `order_id` int NOT NULL AUTO_INCREMENT COMMENT '订单id主键',
  `user_id`	int NOT NULL COMMENT '用户id',
  `goods_id` int NOT NULL COMMENT '商品类型',
  `order_price` double DEFAULT NULL COMMENT '订单金额',
  `order_discount` double DEFAULT NULL COMMENT '优惠金额',
  `order_actualPrice` double NOT NULL COMMENT '实付金额',
  `order_status` double NOT NULL DEFAULT '0' COMMENT '订单状态根据实际情况而定',
  `order_createTime` timestamp NOT NULL COMMENT '创建时间',
  `order_updateTime` timestamp NOT NULL COMMENT '更新时间',
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单';
```

