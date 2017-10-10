//package log;
//
//import util.RandomDate;
//
//import java.io.IOException;
//import java.nio.charset.Charset;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardOpenOption;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Random;
//
//public class LogGenerator {
//    private static final int SIZE = 5000000;
//    private static final String DATE_FORMAT = "yyyyMMdd";
//    private static final List<String> URLS = Arrays.asList("https://www.baidu.com/"
//            , "https://www.qq.com/"
//            , "https://www.taobao.com/"
//            , "https://www.tmall.com/"
//            , "https://www.youdao.com/"
//            , "https://www.netease.com/"
//            , "https://www.cnbeta.com/"
//            , "https://www.leiphone.com/"
//            , "https://www.google.com/"
//            , "https://www.apple.com/"
//            , "https://www.facebook.com/"
//            , "https://www.instagram.com/"
//            , "https://www.youtube.com/"
//            , "https://www.500px.com/");
//    private static final String FILE_NAME = "pv.cvs";
//
//
//    public static void main(String[] args) throws IOException {
//        List<String> records = new ArrayList<>();
//
//        StringBuilder builder = new StringBuilder();
//        RandomDate randomDate = new RandomDate(LocalDate.of(2017, 1, 1), LocalDate.of(2017, 10, 10));
//
//        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
//        Random pvRandom = new Random();
//        int urlSize = URLS.size();
//        for (int i = 0; i < SIZE; i++) {
//            builder.append(df.format(randomDate.nextDate()));
//            builder.append(",");
//            builder.append(URLS.get(pvRandom.nextInt(urlSize)));
//            builder.append(",");
//            builder.append(pvRandom.nextInt(10000));
//            records.add(builder.toString());
//            //Clear builder
//            builder.setLength(0);
//        }
//        Path out = Paths.get(FILE_NAME);
//        Files.write(out, records, Charset.defaultCharset(), StandardOpenOption.TRUNCATE_EXISTING);
//    }
//}
