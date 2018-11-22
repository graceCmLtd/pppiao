create table ppp_tx_payment
(
	id int auto_increment
		primary key,
	payment_way varchar(10) not null comment '三方支付：yop:易宝',
	external_id varchar(45) null comment '三方支付的订单id（如易宝的uniqueOrderNo）',
	transac_id int null,
	bill_number varchar(45) null comment '冗余字段，billNumber',
	seller_id varchar(45) null comment '冗余字段，即transaction中的seller',
	buyer_id varchar(45) null comment '冗余字段，即transaction中的buyer',
	amount decimal(12,2) null comment '冗余字段，即transaction中的amount',
	request_date timestamp default CURRENT_TIMESTAMP not null comment '请求时间',
	expire int null comment '订单有效期，单位秒',
	memo varchar(50) null comment '自定义对账备注',
	fee_type int null comment '收费方式：（将来如需定制收费方案在添加收费方案表）
1: amount中抽1%作为平台收益（profit），另外每笔10元支付给第三方平台（fee）',
	profit decimal(12,2) null comment '公司收益部分，卖方实得=amount-profit-fee',
	fee decimal(12,2) null comment '第三方支付平台手续费，卖方实得=amount-profit-fee',
	status int(1) null comment '订单状态:暂时参考易宝状态
													PROCESSING 处理中(非终态)
													SUCCESS 订单成功(终态)
													CLOSED订单关闭(终态)
													TIME_OUT 订单超时(终态)
													REJECT订单拒绝(终态)
													REPEALED 订单撤销(分账订单退款后查询)
													REVOKED 订单取消(网银订单)
													REVERSAL 冲正',
	extra varchar(300) null comment '额外信息，json字符串形式',
	error_msg varchar(300) null comment '调用中的错误提示,三方调用出错时存储返回码等',
	update_date timestamp default CURRENT_TIMESTAMP not null
)
;

create index ppp_tx_payment_bill_id
	on ppp_tx_payment (bill_number)
;

create index ppp_tx_payment_buyer_id
	on ppp_tx_payment (buyer_id)
;

create index ppp_tx_payment_date
	on ppp_tx_payment (request_date)
;

create index ppp_tx_payment_seller_id
	on ppp_tx_payment (seller_id)
;

create index ppp_tx_payment_status
	on ppp_tx_payment (status)
;

create index ppp_tx_payment_tx_id
	on ppp_tx_payment (transac_id)
;

