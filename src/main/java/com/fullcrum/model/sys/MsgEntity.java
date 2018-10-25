package com.fullcrum.model.sys;

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



public class MsgEntity {

		private int msgId;
		
		private String msgType;
		
		public String getMsgType() {
			return msgType;
		}


		public void setMsgType(String msgType) {
			this.msgType = msgType;
		}


		private String senderId;
		
		private String receiverId;
		
		private String msgContent;
		
		
		private Boolean flag;


		public Boolean getFlag() {
			return flag;
		}


		public void setFlag(Boolean flag) {
			this.flag = flag;
		}


		public int getMsgId() {
			return msgId;
		}


		public void setMsgId(int msgId) {
			this.msgId = msgId;
		}


		public String getSenderId() {
			return senderId;
		}


		public void setSenderId(String senderId) {
			this.senderId = senderId;
		}


		public String getReceiverId() {
			return receiverId;
		}


		public void setReceiverId(String receiverId) {
			this.receiverId = receiverId;
		}


		public String getMsgContent() {
			return msgContent;
		}


		public void setMsgContent(String msgContent) {
			this.msgContent = msgContent;
		}


		
}
