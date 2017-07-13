package socket;


import java.util.Scanner;

/**
 * Created by zhugongyi on 2017/7/12.
 */
public class TestConsole {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);// 创建输入流扫描器
        for (;;) {
            System.out.println("请输入你的身份证号：");// 提示用户输入
            String line = scanner.nextLine();// 获取用户输入的一行文本
            // 打印对输入文本的描述
            System.out.println("原来你身份证号是" + line);
        }

    }
}
