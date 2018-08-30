package com.fullcrum.model.sys;

import java.sql.Date;

/*
 * 
 * author:gavin.hou
 * 
 * 
 * 
 * 
 * msgId  int   消息id
SenderId    int  发送者
ReceiverId   int  接收者
MsgContent  varchar（200） 消息内容
sendTime    date  发送时间
 * */



public class MsgDao {

		private int msgId;
		
		private int senderId;
		
		private int receiverId;
		
		private String msgContent;
		
		
		private Date sendDate;


		public int getMsgId() {
			return msgId;
		}


		public void setMsgId(int msgId) {
			this.msgId = msgId;
		}


		public int getSenderId() {
			return senderId;
		}


		public void setSenderId(int senderId) {
			this.senderId = senderId;
		}


		public int getReceiverId() {
			return receiverId;
		}


		public void setReceiverId(int receiverId) {
			this.receiverId = receiverId;
		}


		public String getMsgContent() {
			return msgContent;
		}


		public void setMsgContent(String msgContent) {
			this.msgContent = msgContent;
		}


		public Date getSendDate() {
			return sendDate;
		}


		public void setSendDate(Date sendDate) {
			this.sendDate = sendDate;
		}
		
		
}
