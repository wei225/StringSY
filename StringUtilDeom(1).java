
package gushici;


import java.io.FileWriter;
import java.io.IOException;
import java.rmi.MarshalException;
import java.util.Scanner;

public class zifuchuan {

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
    System.out.println("请输入需要查询的字词：");
    String target = scanner.nextLine();

    formatAndQuery(srcStr,target);

  }

 private static void formatAndQuery(String srcStr, String target) {
  // 如果输入的诗词原文为空，提示错误信息结束
  try {
   if (srcStr == null || srcStr.length() == 0) {
    throw new MarshalException("输入诗词原文为空！");
   }
  } catch (Exception e) {
   System.out.println(e.getMessage());
  }
  int c3=0;
  if(target!=null&&!"".equals(target)){
  for(int y=0;y<srcStr.length();y++){
	  int c=target.length();
	  String cwed=srcStr.substring(y);
	  if(c-1+y<srcStr.length()){
	//	  String  ydc=srcStr.substring(y, y+c);
	 if(target.equals(srcStr.substring(y, y+c))){
		 
		  c3++;
	  }
	  }
  }
 
  System.out.println(target+"该字段出现的次数为"+""+c3+"次");
  }
  // 输出诗词原文
  
  
 

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
  System.out.println("===========

 杜伟
====七言律诗格式==========================");
  System.out.print(rsStr);
 
 }
}
