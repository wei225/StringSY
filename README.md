# StringSY
# JAVA实验

# 一.实验目的  

掌握字符串String及其方法的使用  

掌握异常处理结构  

# 二.业务要求  

内容：利用已学的字符串处理知识编程完成《长恨歌》古诗的整理对齐工作，写出功能函数，并运行。达到如下功能：  


1.每7个汉字加入一个标点符号，奇数时加“，”，偶数时加“。”  

2.允许提供输入参数，统计古诗中某个字或词出现的次数  

3.考虑操作中可能出现的异常，在程序中设计异常处理程序  


输入：汉皇重色思倾国御宇多年求不得杨家有女初长成养在深闺人未识天生丽质难自弃一朝选在君王侧回眸一笑百媚生六宫粉黛无颜色春寒赐浴华清池温泉水滑洗凝脂侍儿扶起娇无力始是新承恩泽时云鬓花颜金步摇芙蓉帐暖度春宵春宵苦短日高起从此君王不早朝承欢侍宴无闲暇春从春游夜专夜后宫佳丽三千人三千宠爱在一身金屋妆成娇侍夜玉楼宴罢醉和春姊妹弟兄皆列士可怜光采生门户遂令天下父母心不重生男重生女骊宫高处入青云仙乐风飘处处闻缓歌慢舞凝丝竹尽日君王看不足渔阳鼙鼓动地来惊破霓裳羽衣曲九重城阙烟尘生千乘万骑西南行<未完，待续>  

输出：  

汉皇重色思倾国，御宇多年求不得。  

杨家有女初长成，养在深闺人未识。  

天生丽质难自弃，一朝选在君王侧。  

回眸一笑百媚生，六宫粉黛无颜色。  

春寒赐浴华清池，温泉水滑洗凝脂。  

侍儿扶起娇无力，始是新承恩泽时。  

云鬓花颜金步摇，芙蓉帐暖度春宵。  

春宵苦短日高起，从此君王不早朝。  

…………
*****
#  三.实验核心方法
 ## 1. 
  ```java
  //输出文件保存的路径
  public static final String filepath = "D:\\format.txt";
    // 一个逗号的字符串
  private static final String COMMA = "，";
    // 一个句号的字符串，句号后字符串换行
  private static final String PERIOD = "。\n";
    // 每句诗的长度
private static final int SENTENCE_LENGTH = 7;
```

##  2.
```java
private static void formatAndQuery(String srcStr, String target) {
		// 如果输入的诗词原文为空，提示错误信息结束
		try {
			if (srcStr == null || srcStr.length() == 0) {
				throw new MyException("输入诗词原文为空！");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
```

##  3.
```java
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
```

##  4.
```java
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
```

##  5.
```java
class MyException extends RuntimeException {

  public MyException(String message) {
    super(message);
  }
```

# 四.实验结果
![1](https://github.com/saodehen/StringSY/blob/master/1.jpg)
# 五.实验心得
  通过本次实验，让我更深刻的理解了字符串的性质，在上机的操作过场中，发现了自己平时疏忽的细节，在以后的学习过程中会更加注意。  
  并且Java学习应该是个漫长的过程，不可心浮气躁，只有保持一个良好的心态加上不断累积的知识才能将Java学好。
