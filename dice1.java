import java.text.*;
import java.util.*;

public class dice1 { // ver1.02.01 4/24

   public static void main(String[] args) {

      boolean bl = true;
      long allCnt;
      long n1 = 0, n2 = 0, n3 = 0, n4 = 0, n5 = 0, n6 = 0;
      Random random = new Random();
      DecimalFormat df;

      while (bl) {
         System.out.print("주사위를 던지고 싶은 회수를 입력하세요. >");

         Scanner sc = new Scanner(System.in);
         String strAllCnt = sc.next();
         allCnt = Long.parseLong(strAllCnt);
         df = new DecimalFormat("#,###");

         System.out.println(df.format(allCnt) + "회를 지정했습니다.");
         if (allCnt >= 100000000) {
            System.out.println("1억회 이상을 던지려고 합니다. 너무 많은 횟수를 지정하면 시간이 지나치게 오래 걸릴 수 있습니다.");
         }

         System.out.println("지정한 횟수만큼 주사위를 던지려면 'y'를 누르세요. 중단하고 싶으면 'Ctrl+C'를 누르면 중단됩니다.");

         String strTmp = sc.next(); // <<<<
         long startTime, endTime;

         if (strTmp.equals("y") || strTmp.equals("Y")) { // <<<<
            startTime = System.currentTimeMillis();
            for (long i = 1; i <= allCnt; i++) {
               int n = random.nextInt(6) + 1; //<<<

               switch (n) {
                  case 1:
                     n1++;
                     break;
                  case 2:
                     n2++;
                     break;
                  case 3:
                     n3++;
                     break;
                  case 4:
                     n4++;
                     break;
                  case 5:
                     n5++;
                     break;
                  case 6:
                     n6++;
                     break;
               }
            }

            endTime = System.currentTimeMillis();
            System.out.println("\n주사위 던진 횟수는 " + df.format(allCnt) + "입니다.\n");
            System.out.println("1 횟수: " + n1 + "\t  1 확률: " + n1 / (double) allCnt);
            System.out.println("2 횟수: " + n2 + "\t  2 확률: " + n2 / (double) allCnt);
            System.out.println("3 횟수: " + n3 + "\t  3 확률: " + n3 / (double) allCnt);
            System.out.println("4 횟수: " + n4 + "\t  4 확률: " + n4 / (double) allCnt);
            System.out.println("5 횟수: " + n5 + "\t  5 확률: " + n5 / (double) allCnt);
            System.out.println("6 횟수: " + n6 + "\t  6 확률: " + n6 / (double) allCnt);

            System.out.println("\n주사위 한면이 나올 확률은 " + 1.0 / 6 + "입니다.");
            // if((endTime-startTime)
            System.out.println("소요시간은 " + (endTime - startTime) / 1000.0 + "초입니다.");
            sc.close();
            bl = false;
         } else {
            System.out.println("프로그램을 종료합니다.");
            System.exit(0);
         }
      }
   }

}