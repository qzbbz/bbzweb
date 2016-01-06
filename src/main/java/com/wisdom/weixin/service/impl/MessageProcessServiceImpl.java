package com.wisdom.weixin.service.impl;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.Date;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.wisdom.weixin.service.IMessageProcessService;
import com.wisdom.weixin.utils.InputMessage;
import com.wisdom.weixin.utils.OutputMessage;
import com.wisdom.weixin.utils.TextOutputMessage;
import com.wisdom.weixin.utils.WeixinMsgType;

@Service("weixinMessageProcessService")
public class MessageProcessServiceImpl implements IMessageProcessService {

	private static final Logger logger = LoggerFactory
			.getLogger(MessageProcessServiceImpl.class);
	
	@Override
	public String processWeixinMessage(HttpServletRequest request) {
		String result = "";
		logger.debug("begin to process weixin message.");
		try {
			ServletInputStream in;
			in = request.getInputStream();
			XStream xs = new XStream(new DomDriver());
			xs.alias("xml", InputMessage.class);
			StringBuilder xmlMsg = new StringBuilder();
			byte[] b = new byte[4096];
			for (int n; (n = in.read(b)) != -1;) {
				xmlMsg.append(new String(b, 0, n, "UTF-8"));
			}
			logger.info(xmlMsg.toString());
			InputMessage inputMsg = (InputMessage) xs
					.fromXML(xmlMsg.toString());
			String msgType = inputMsg.getMsgType();
			logger.debug("Message Type : {}", msgType);
			logger.debug("Event : {}", inputMsg.getEvent());
			logger.debug("WeixinMsgType.Event.toString() : {}",
					WeixinMsgType.Event.toString().trim());
			if (msgType.equals(WeixinMsgType.Text.toString())) {
				logger.debug("开发者微信号：" + inputMsg.getToUserName());
				logger.debug("发送方帐号：" + inputMsg.getFromUserName());
				logger.debug("消息创建时间：" + inputMsg.getCreateTime());
				logger.debug("消息内容：" + inputMsg.getContent());
				logger.debug("消息Id：" + inputMsg.getMsgId());
			} else if (msgType.equals(WeixinMsgType.Event.toString())) {
				logger.info("inputMsg.getEvent() : {}", inputMsg
						.getEvent().trim());
				if (("subscribe").equals(inputMsg.getEvent().trim())) {
					XStream xstream = new XStream(new XppDriver() {
						@Override
						public HierarchicalStreamWriter createWriter(Writer out) {
							return new PrettyPrintWriter(out) {
								@Override
								protected void writeText(QuickWriter writer,
										String text) {
									if (!text.startsWith("<![CDATA[")) {
										text = "<![CDATA[" + text + "]]>";
									}
									writer.write(text);
								}
							};
						}
					});
					TextOutputMessage outputMsg = new TextOutputMessage();
					outputMsg.setContent("您好，欢迎关注帮帮账！");
					try {
						setOutputMsgInfo(outputMsg, inputMsg);
					} catch (Exception e) {
						logger.debug(e.toString());
					}
					xstream.alias("xml", outputMsg.getClass());
					result = new String(xstream.toXML(outputMsg).getBytes());
					logger.debug("xml result : {}", result);
				}
			}
		} catch (IOException e) {
			logger.info(e.toString());
		} catch (Exception e) {
			logger.info(e.toString());
		}
		logger.debug("finish in processing weixin message.");
		return result;
	}
	
	private static void setOutputMsgInfo(OutputMessage oms, InputMessage msg)
			throws Exception {
		Class<?> outMsg = oms.getClass().getSuperclass();
		Field CreateTime = outMsg.getDeclaredField("CreateTime");
		Field ToUserName = outMsg.getDeclaredField("ToUserName");
		Field FromUserName = outMsg.getDeclaredField("FromUserName");

		ToUserName.setAccessible(true);
		CreateTime.setAccessible(true);
		FromUserName.setAccessible(true);

		CreateTime.set(oms, new Date().getTime());
		ToUserName.set(oms, msg.getFromUserName());
		FromUserName.set(oms, msg.getToUserName());
	}

}
