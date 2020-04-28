package com.poplar.bean;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StringUtilDeom {

  //输出文件保存的路径
  public static final String filepath = "D:\\format.txt";
  // 一个逗号的字符串
  private static final String COMMA = "，";
  // 一个句号的字符串，句号后字符串换行
  private static final String PERIOD = "。\n";
  // 每句诗的长度
  private static final int SENTENCE_LENGTH = 7;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    // 执行时输入的第一个参数作为原字符串
    System.out.println("请输入诗词原文：");
    String srcStr = scanner.nextLine();

    // 第二个参数作为统计的目标字段
    System.out.println("请输入需要查询的字段，不需要查询请直接回车：");
    String target = scanner.nextLine();

    formatAndQuery(srcStr,target);

  }

	private static void formatAndQuery(String srcStr, String target) {
		// 如果输入的诗词原文为空，提示错误信息结束
		try {
			if (srcStr == null || srcStr.length() == 0) {
				throw new MyException("输入诗词原文为空！");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// 输出诗词原文
		System.out.println("====================诗词原文======================");
		System.out.println(srcStr);
		System.out.println("=====================诗词原文结束===================");

		// 用于接受结果字符串
		StringBuilder rsStr = new StringBuilder();

		// 分割原诗词，每特定句长添加一个标点,最多循环原文长度除以每句的长度
		for (int i = 0; i < (srcStr.length() / SENTENCE_LENGTH); i++) {
			// 结果字符串拼接从原文截取的字符串
			rsStr = rsStr.append(srcStr, SENTENCE_LENGTH * i, SENTENCE_LENGTH * (i + 1));
			// 因为从0开始，所以第0、2、4这类偶数的添加逗号
			if (i % 2 == 0) {
				rsStr.append(COMMA);
			} else { // 1、3、5奇数添加句号和换行符
				rsStr.append(PERIOD);
			}
		}

		// 如果不能整除余下的原文单独成为一行
		if (srcStr.length() % SENTENCE_LENGTH != 0) {
			rsStr.append(srcStr.substring(SENTENCE_LENGTH * (srcStr.length() / SENTENCE_LENGTH)))
					.append(PERIOD);
		}

		// 输出结果
		System.out.println("=================格式化后诗词==========================");
		System.out.print(rsStr);
		System.out.println("================格式化后诗词结束========================");

		System.out.println("=======================================================");

		System.out.println("================开始写入文件========================");
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(filepath, false);
			fileWriter.write(String.valueOf(rsStr));
		} catch (IOException e) {
			System.out.println("================写入文件失败========================");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("================写入文件失败========================");
				e.printStackTrace();
			}
		}
		System.out.println("================写入文件成功========================");
		// 如果目标字段为空提示错误信息
		if (target == null || target.length() == 0) {
			System.out.println("统计目标字段为空！");
			return;
		} else { // 否则展示目标字段
			System.out.println("统计目标字段为是：" + target);
		}

		// 统计次数
		int count = 0;
		// 坐标
		int index = 0;
		while ((index = srcStr.indexOf(target, index)) != -1) {// 如果目标字段target在原文中存在
			index = index + target.length();
			count++;// 找到一次统计一次
		}

		// 输出统计结果
		System.out.print("目标字段为\"" + target + "\",共出现" + count + "次！");
	}
}

class MyException extends RuntimeException {

  public MyException(String message) {
    super(message);
  }
}

