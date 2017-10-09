import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class MonthlyPvReducer extends Reducer<Text, LongWritable, Text, NullWritable> {
    public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        long total = 0;
        for (LongWritable value : values) {
            total += value.get();
        }
        StringBuilder builder = new StringBuilder();
        builder.append(key);
        builder.append(",");
        builder.append(total);
        System.out.println("Key: " + key + " value: " + total);
        context.write(new Text(builder.toString()), NullWritable.get());
    }
}
