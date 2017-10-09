import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import java.io.IOException;

public class MonthlyPvReducer extends Reducer<Text, LongWritable, Text, NullWritable> {

    private MultipleOutputs mos;

    public void setup(Context context) {
        mos = new MultipleOutputs(context);
    }

    public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        String[] record = key.toString().split(",");
        if (record.length == 2) {
            long total = 0;
            for (LongWritable value : values) {
                total += value.get();
            }
            StringBuilder builder = new StringBuilder();
            builder.append(key);
            builder.append(",");
            builder.append(total);
            System.out.println("Key: " + key + " value: " + total);
            mos.write(record[0], new Text(builder.toString()), NullWritable.get());
        }
    }

    public void cleanup(Context context) throws IOException, InterruptedException {
        mos.close();
    }
}
