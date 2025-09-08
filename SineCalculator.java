import java.util.InputMismatchException;
import java.util.Scanner;

public class SineCalculator
{
    public static final double PI = 3.14159265358979;
    public double x_value;
    public double x_in_interval;
    public double sin_x;

    public boolean fitsCondition(double number, double condition)
    {
        return number > condition || number < -condition;
    }

    public double calculateEpsilon(int k)
    {
        if (k < 0) throw new IllegalArgumentException("k must be greater than or equal to zero!");
        double epsilon = 1;
        for (int i = 0; i < k; i++)
        {
            epsilon /= 10;
        }
        return epsilon;
    }

    public void fitToInterval()
    {
        this.x_in_interval = this.x_value % (2 * PI);
        if (this.x_in_interval > PI)
        {
            this.x_in_interval -= 2 * PI;
        }
    }

    public void calculateSine(double epsilon)
    {
        double taylor_increment = this.x_in_interval;
        this.sin_x = 0;
        int factorial = 1;
        int n = 1;
        while (fitsCondition(taylor_increment, epsilon))
        {
            this.sin_x += taylor_increment;
            taylor_increment /= ((2 * n) * (2 * n + 1));
            taylor_increment *= -this.x_in_interval * this.x_in_interval;
            n++;
        }
    }

    public static void main(String[] args)
    {
        try
        {
            Scanner in = new Scanner(System.in);
            System.out.print("Enter k: ");
            int k_value = in.nextInt();
            System.out.print("Enter x: ");
            double x_value = in.nextDouble();
            in.close();

            SineCalculator calculator = new SineCalculator();
            calculator.x_value = x_value;
            double epsilon = calculator.calculateEpsilon(k_value);
            calculator.fitToInterval();
            calculator.calculateSine(epsilon);

            System.out.printf("Taylor sine approximation: %f%n", calculator.sin_x);
            System.out.printf("Standard library function: %f%n", Math.sin(x_value));
        }
        catch (IllegalArgumentException e)
        {
            System.out.printf("Invalid input: %s%n", e.getMessage());
        }
        catch (InputMismatchException e)
        {
            System.out.printf("Invalid input: %s%n", e.getMessage());
        }
        catch (Exception e)
        {
            System.out.printf("An error occurred: %s%n", e.getMessage());
        }
    }
}
