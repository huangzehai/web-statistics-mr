import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MonthlyPvDriver extends Configured implements Tool {
    private static final String DATE_FORMAT = "yyyyMM";

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new MonthlyPvDriver(), args);
        System.exit(exitCode);
    }

    public int run(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.printf("Usage: %s [generic options] <input> <output>\n",
                    getClass().getSimpleName());
            ToolRunner.printGenericCommandUsage(System.err);
            return -1;
        }

        Job job = Job.getInstance();
        job.setJarByClass(getClass());
        job.setMapperClass(MonthlyPvMapper.class);
        job.setReducerClass(MonthlyPvReducer.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);


        // Defines additional sequence-file based output 'sequence' for the job
        Calendar calendar = Calendar.getInstance();
        //Set start date.
        calendar.set(2017, 0, 1, 0, 0, 0);
        Date today = new Date();
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        while (calendar.getTime().before(today)) {
            MultipleOutputs.addNamedOutput(job, df.format(calendar.getTime()),
                    TextOutputFormat.class,
                    Text.class, NullWritable.class);
            calendar.add(Calendar.MONTH, 1);
        }
        return job.waitForCompletion(true) ? 0 : 1;
    }
}
