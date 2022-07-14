import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Main
{
    static Random random;

    public Main() { }

    public static void main(String[] q)
    {
        RandomList rl = new RandomList(0L);

        String[] list = new String[] { "apple", "banana", "cheese", "drink", "eggs", "fries", "greens", "hotdog" };

        System.out.println(Arrays.toString(rl.randomizeList(list)));

//        new Main().playSound("c4.wav");
//
//        new Main().threadSleeper(500L);
//
//        new Main().playSound("c4.wav");
//
//        new Main().threadSleeper(10000L);

        //new Main().c();

        //new Main().chromatic();

        Scanner scan = new Scanner(System.in);

        Main.random = new Random(Main.longHashString(scan.next()));

        Note[] notes = Main.randomizeNotes(new Note[]
        {
            new Note("c"),
            new Note("c#"),
            new Note("d"),
            new Note("d#"),
            new Note("e"),
            new Note("f"),
            new Note("f#"),
            new Note("g"),
            new Note("g#"),
            new Note("a"),
            new Note("a#"),
            new Note("b")
        });

        for (Note n : notes)
        {
            n.setSize(Main.random.nextInt(4) + 1);

            switch (n.getSize())
            {
                case 1:
                    n.setPattern(new int[] { 1 } );

                    break;
                case 2:
                    int[][] ii = new int[][]
                    {
                        { 2 },
                        { 1, 1 }
                    };

                    n.setPattern(ii[Main.random.nextInt(2)]);

                    break;
                case 3:
                    ii = new int[][]
                    {
                        { 3 },
                        { 2, 1 },
                        { 1, 2 },
                        { 1, 1, 1}
                    };

                    n.setPattern(ii[Main.random.nextInt(4)]);

                    break;
                case 4:
                    ii = new int[][]
                    {
                        { 4 },
                        { 3, 1 },
                        { 1, 3 },
                        { 2, 2 },
                        { 2, 1, 1 },
                        { 1, 2, 1 },
                        { 1, 1, 2 },
                        { 1, 1, 1, 1 }
                    };

                    n.setPattern(ii[Main.random.nextInt(8)]);

                    break;
                default:
                    System.err.println("Something went wrong.");
                    System.exit(Integer.MAX_VALUE);
            }

            for (int i : n.getPattern())
            {
                switch (i)
                {
                    case 1:
                        double[][] dd = new double[][]
                        {
                            { 1D },
                            { 0.5D, 0.5D },
                            { 1D / 3, 1D / 3, 1D / 3 },
                            { 2D / 3, 1D / 3 },
                            { 1D / 3, 2D / 3 },
                            { 0.25D, 0.25D, 0.25D, 0.25D },
                            { 0.5D, 0.25D, 0.25D },
                            { 0.25D, 0.5D, 0.25D },
                            { 0.25D, 0.25D, 0.5D },
                            { 0.75D, 0.25D },
                            { 0.25D, 0.75D }
                        };

                        n.addRhythm(dd[Main.random.nextInt(dd.length)]);

                        break;
                    case 2:
                        dd = new double[][]
                        {
                            { 2D },
                            { 0.5D, 1D, 0.5D },
                            { 1.5D, 0.5D },
                            { 0.5D, 1.5D }
                        };

                        n.addRhythm(dd[Main.random.nextInt(dd.length)]);

                        break;
                    case 3:
                        dd = new double[][]
                        {
                            { 3D },
                            { 1.5D, 1.5D },
                            { 0.5D, 1D, 1D, 0.5D },
                            { 1.5D, 1D, 0.5D },
                            { 0.5D, 1D, 1.5D },
                            { 0.75D, 0.75D, 0.75D, 0.75D },
                            { 0.75D, 0.75D, 1.5D }
                        };

                        n.addRhythm(dd[Main.random.nextInt(dd.length)]);

                        break;
                    case 4:
                        dd = new double[][]
                        {
                            { 4D },
                            { 0.5D, 1D, 1D, 1D, 0.5D },
                            { 1.5D, 1D, 1.5D },
                            { 0.5D, 3D, 0.5D }
                        };

                        n.addRhythm(dd[Main.random.nextInt(dd.length)]);

                        break;
                    default:
                        System.err.println("The universe is over.");
                        System.exit(Integer.MAX_VALUE);
                }
            }

            String pitches = "";

            for (int i = 0; i < n.getCount(); i++)
            {
                if (i == 0) { pitches += "4"; }
                else if (pitches.charAt(pitches.length() - 1) == '4')
                {
                    String[] s = new String[] { "3", "4", "5" };

                    pitches += s[Main.random.nextInt(3)];
                }
                else if (pitches.charAt(pitches.length() - 1) == '5')
                {
                    String[] s = new String[] { "4", "5" };

                    pitches += s[Main.random.nextInt(2)];
                }
                else if (pitches.charAt(pitches.length() - 1) == '3')
                {
                    String[] s = new String[] { "3", "4" };

                    pitches += s[Main.random.nextInt(2)];
                }
                else
                {
                    System.err.println("The end is near.");
                    System.exit(Integer.MAX_VALUE);
                }
            }

            n.setPitches(pitches);
        }

        System.out.println("Beats Per Minute");
        double constant = 0D;

        try
        {
            constant = 60000D / scan.nextDouble();
        }
        catch (Exception ignore)
        {
            System.err.println("The time has come.");
            System.exit(Integer.MAX_VALUE);
        }

        for (Note n : notes)
        {
            for (NotePair np : n.getNotePair())
            {
                System.out.println(n.getName() + np.getName() + " " + np.getLength());

                Main.playNewSound(
                    "sounds/" + np.getName() + "/" + n.getName() + np.getName() + ".wav",
                    (long) (np.getLength() * constant)
                );
            }
        }
    }

    private void threadSleeper(long l)
    {
        try { Thread.sleep(l); }
        catch (Exception e) { e.printStackTrace(); }
    }

    private void playSound(String s)
    {
        try
        {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new java.io.File(s));
            Clip c = AudioSystem.getClip();

            c.open(ais);
            //c.loop(Clip.LOOP_CONTINUOUSLY);
            c.start();
        }
        catch (Exception ignore) { System.out.println("no"); }
    }

    private static Note[] randomizeNotes(Note[] list)
    {
        Note[] rand = new Note[list.length];

        for (int i = 0; i < rand.length; i++)
        {
            int index = Main.random.nextInt(list.length);
            Note[] temp = new Note[list.length - 1];

            rand[i] = list[index];

            System.arraycopy(list, 0, temp, 0, index);
            System.arraycopy(list, index + 1, temp, index, temp.length - index);

            list = temp;
        }

        list = rand;

        return list;
    }

    private static long longHashString(String s)
    {
        long l = 1L;

        try
        {
            for (char c : s.toCharArray()) { l *= (short) c; }
        }
        catch (Exception ignore)
        {
            for (char c : s.toCharArray()) { l += (short) c; }
        }

        return l;
    }

    private static void playNewSound(String s, long l)
    {
        try
        {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new java.io.File(s));
            Clip c = AudioSystem.getClip();

            c.open(ais);
            c.start();

            new Main().threadSleeper(l);

            c.stop();
        }
        catch (Exception ignore) { System.out.println("noe"); }
    }

    private static void playNewChord(long l, String... s)
    {
        try
        {
            AudioInputStream[] ais = new AudioInputStream[s.length];
            Clip[] c = new Clip[s.length];

            for (int i = 0; i < s.length; i++)
            {
                ais[i] = AudioSystem.getAudioInputStream(new java.io.File(s[i]));
                c[i] = AudioSystem.getClip();

                c[i].open(ais[i]);
                c[i].start();
            }

            new Main().threadSleeper(l);

            for (Clip clip : c) { clip.stop(); }
        }
        catch (Exception ignore) { System.err.println("no"); }
    }

    private void c()
    {
        Main.playNewSound("sounds/4/c4.wav", 500L);
        Main.playNewSound("sounds/4/d4.wav", 500L);
        Main.playNewSound("sounds/4/e4.wav", 500L);
        Main.playNewSound("sounds/4/f4.wav", 500L);
        Main.playNewSound("sounds/4/g4.wav", 500L);
        Main.playNewSound("sounds/4/a4.wav", 500L);
        Main.playNewSound("sounds/4/b4.wav", 500L);
        Main.playNewSound("sounds/5/c5.wav", 500L);
        Main.playNewSound("sounds/4/b4.wav", 500L);
        Main.playNewSound("sounds/4/a4.wav", 500L);
        Main.playNewSound("sounds/4/g4.wav", 500L);
        Main.playNewSound("sounds/4/f4.wav", 500L);
        Main.playNewSound("sounds/4/e4.wav", 500L);
        Main.playNewSound("sounds/4/d4.wav", 500L);
        Main.playNewSound("sounds/4/c4.wav", 500L);
        Main.playNewSound("sounds/4/e4.wav", 500L);
        Main.playNewSound("sounds/4/g4.wav", 500L);
        Main.playNewSound("sounds/5/c5.wav", 500L);
        Main.playNewSound("sounds/4/g4.wav", 500L);
        Main.playNewSound("sounds/4/e4.wav", 500L);
        Main.playNewSound("sounds/4/c4.wav", 500L);
        Main.playNewSound("sounds/4/f4.wav", 500L);
        Main.playNewSound("sounds/4/a4.wav", 500L);
        Main.playNewSound("sounds/5/c5.wav", 500L);
        Main.playNewSound("sounds/4/a4.wav", 500L);
        Main.playNewSound("sounds/4/f4.wav", 500L);
        Main.playNewSound("sounds/4/c4.wav", 500L);
        Main.playNewSound("sounds/4/e4.wav", 500L);
        Main.playNewSound("sounds/4/a4.wav", 500L);
        Main.playNewSound("sounds/5/c5.wav", 500L);
        Main.playNewSound("sounds/4/a4.wav", 500L);
        Main.playNewSound("sounds/4/e4.wav", 500L);
        Main.playNewSound("sounds/4/c4.wav", 500L);
        Main.playNewSound("sounds/4/e4.wav", 500L);
        Main.playNewSound("sounds/4/g4.wav", 500L);
        Main.playNewChord(1000L, "sounds/4/c4.wav", "sounds/4/e4.wav", "sounds/4/g4.wav", "sounds/5/c5.wav");
    }

    private void chromatic()
    {
        Main.playNewSound("sounds/4/c4.wav", 200L);
        Main.playNewSound("sounds/4/c#4.wav", 200L);
        Main.playNewSound("sounds/4/d4.wav", 200L);
        Main.playNewSound("sounds/4/d#4.wav", 200L);
        Main.playNewSound("sounds/4/e4.wav", 200L);
        Main.playNewSound("sounds/4/f4.wav", 200L);
        Main.playNewSound("sounds/4/f#4.wav", 200L);
        Main.playNewSound("sounds/4/g4.wav", 200L);
        Main.playNewSound("sounds/4/g#4.wav", 200L);
        Main.playNewSound("sounds/4/a4.wav", 200L);
        Main.playNewSound("sounds/4/a#e3.wav", 200L);
        Main.playNewSound("sounds/4/b4.wav", 200L);
        Main.playNewSound("sounds/5/c5.wav", 200L);
        Main.playNewSound("sounds/4/b4.wav", 200L);
        Main.playNewSound("sounds/4/a#e3.wav", 200L);
        Main.playNewSound("sounds/4/a4.wav", 200L);
        Main.playNewSound("sounds/4/g#4.wav", 200L);
        Main.playNewSound("sounds/4/g4.wav", 200L);
        Main.playNewSound("sounds/4/f#4.wav", 200L);
        Main.playNewSound("sounds/4/f4.wav", 200L);
        Main.playNewSound("sounds/4/e4.wav", 200L);
        Main.playNewSound("sounds/4/d#4.wav", 200L);
        Main.playNewSound("sounds/4/d4.wav", 200L);
        Main.playNewSound("sounds/4/c#4.wav", 200L);
        Main.playNewSound("sounds/4/c4.wav", 200L);
    }
}
