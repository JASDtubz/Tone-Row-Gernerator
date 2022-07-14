import java.util.Random;

public class RandomList
{
    private final Random random;

    public RandomList() { this(new Random().nextLong()); }

    public RandomList(long seed) { this.random = new Random(seed); }

    public String[] randomizeList(String[] list)
    {
        String[] rand = new String[list.length];

        for (int i = 0; i < rand.length; i++)
        {
            int index = this.random.nextInt(list.length);
            String[] temp = new String[list.length - 1];

            rand[i] = list[index];

            System.arraycopy(list, 0, temp, 0, index);
            System.arraycopy(list, index + 1, temp, index, temp.length - index);

            list = temp;
        }

        list = rand;

        return list;
    }
}
