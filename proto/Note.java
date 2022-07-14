public class Note
{
    String name;
    int size = 0;
    int[] pattern;
    double[][] rhythm;
    String pitches;
    NotePair[] np;

    int index = 0;
    int count = 0;

    public Note(String s) { this.name = s; }

    public void setSize(int i) { this.size = i; }

    public int getSize() { return size; }

    public void setPattern(int[] i)
    {
        this.pattern = i;
        this.rhythm = new double[this.pattern.length][];
    }

    public int[] getPattern() { return this.pattern; }

    public void addRhythm(double[] d)
    {
        this.rhythm[this.index] = d;
        this.index++;
        this.count += d.length;
    }

    public int getCount() { return this.count; }

    public void setPitches(String s)
    {
        this.pitches = s;

        this.np = new NotePair[s.length()];

        int ind = 0;

        for (double[] dd : this.rhythm)
        {
            for (double d : dd)
            {
                this.np[ind] = new NotePair(s.substring(ind, ind + 1), d);
                ind++;
            }
        }
    }

    public NotePair[] getNotePair() { return this.np; }

    public String getName() { return this.name; }
}

class NotePair
{
    String s;
    double d;

    NotePair(String s, double d)
    {
        this.s = s;
        this.d = d;
    }

    public String getName() { return this.s; }

    public double getLength() { return this.d; }
}
