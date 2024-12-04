package br.com.lmello.day1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1HistorianHysteria {
    public List<String> readFile(String fileName) {
        ClassLoader classLoader = Day1HistorianHysteria.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("File not found: " + fileName);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        List<String> lines = new ArrayList<>();

        reader.lines().forEach(lines::add);

        return lines;
    }

    public static void main(String[] args) {
        Day1HistorianHysteria dh = new Day1HistorianHysteria();
        String filePath = "day1/input.txt";

        List<String> lines = dh.readFile(filePath);
        PriorityQueue<Integer> left = new PriorityQueue<>();
        PriorityQueue<Integer> right = new PriorityQueue<>();

        Pattern pattern = Pattern.compile("(\\d+)\\s+(\\d+)");

        lines.forEach(line -> {
            Matcher matcher = pattern.matcher(line);

            if (matcher.matches()) {
                left.offer(Integer.parseInt(matcher.group(1)));
                right.offer(Integer.parseInt(matcher.group(2)));
            }
        });

        int res = 0;
        for (int i = 0; i < lines.size(); i++) {
            res += Math.abs(left.poll() - right.poll());
        }

        System.out.println(res);
    }
}
