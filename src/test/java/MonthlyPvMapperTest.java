import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Test;

public class MonthlyPvMapperTest {
    @Test
    public void map() throws Exception {
        Text value = new Text("20171009,https://www.baidu.com/,369");
        new MapDriver<LongWritable, Text, Text, LongWritable>()
                .withMapper(new MonthlyPvMapper())
                .withInput(new LongWritable(0), value)
                .withOutput(new Text("201710,https://www.baidu.com/"), new LongWritable(369))
                .runTest();
    }

}