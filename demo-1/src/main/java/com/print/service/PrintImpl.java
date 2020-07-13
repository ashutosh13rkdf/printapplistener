package com.print.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class PrintImpl implements Print {
@Autowired
ApplicationContext ctx;
	@Override
	public String insert(String filePath) {
		JmsTemplate jms = ctx.getBean(JmsTemplate.class);
		jms.convertAndSend("printJob", filePath);
		return "success";
	}

}
