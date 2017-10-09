import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MonthlyPvMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        System.out.println("Parse value " + value);
        if (value != null) {
            String[] record = value.toString().split(",");
            if (record.length == 3) {
                String yearAndMonth = record[0].substring(0, 6);
                Text yearMonthUrl = new Text(yearAndMonth + "," + record[1]);
                LongWritable pv = new LongWritable(Long.parseLong(record[2]));
                context.write(yearMonthUrl, pv);
            } else {
                System.out.println("Value " + value + " is invalid");
            }
        }
    }
}
