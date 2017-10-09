import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Test;

import java.util.Arrays;

public class MonthlyPvReducerTest {
    @Test
    public void reduce() throws Exception {
        new ReduceDriver<Text, LongWritable, Text, NullWritable>()
                .withReducer(new MonthlyPvReducer())
                .withInput(new Text("201710,https://www.baidu.com/"),
                        Arrays.asList(new LongWritable(369), new LongWritable(5)))
                .withOutput(new Text("201710,https://www.baidu.com/,374"), NullWritable.get())
                .runTest();
    }



}