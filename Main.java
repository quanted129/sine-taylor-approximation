import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static boolean fitsCondition(double number, double condition)
    {
        return number > condition || number < -condition;
    }
    public static void main(String[] args)
    {
        try
        {
            final double PI = 3.14159265358979;
            Scanner in = new Scanner(System.in);
            System.out.print("Enter k: ");
            int k = in.nextInt();
            if (k < 0) throw new IllegalArgumentException("k must be greater than or equal to zero!");
            double epsilon = 1;
            for (int i = 0; i < k; i++) epsilon /= 10;
            System.out.print("Enter x: ");
            double x = in.nextDouble();
            in.close();
            double new_x = x % (2 * PI);
            if (new_x > PI) new_x -= 2 * PI;
            double taylor_increment = new_x;
            double sin_x = 0;
            int factorial = 1;
            for (int n = 1; fitsCondition(taylor_increment, epsilon); n++) {
                sin_x += taylor_increment;
                taylor_increment /= ((2 * n) * (2 * n + 1));
                taylor_increment *= -new_x * new_x;
            }
            System.out.printf("Taylor sine approximation: %f%n", sin_x);
            System.out.printf("Standard library function: %f%n", Math.sin(x));
        }
        catch (IllegalArgumentException e)
        {
            System.out.printf("Invalid input: %s%n", e.getMessage());
        }
        catch (InputMismatchException e)
        {
            System.out.printf("Invalid input: %s!%n", e.getMessage());
        }
        catch (Exception e)
        {
            System.out.printf("An error occurred: %s!%n", e.getMessage());
        }

    }
}