package src.adventofcode.day5;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day5 {

    private static final List<Almanac> almanacList = new ArrayList<>();
    private static final Map<Integer, List<Range>> almanacMap = new HashMap<>();
    private static int index = 0;

    public static void main(String[] args) throws IOException {
        final List<String> lines = Files.readAllLines(Path.of("src/adventofcode/day5/input"), StandardCharsets.UTF_8);

        // Delete empty lines
        lines.removeIf(String::isEmpty);

        // Process first seed line
        String seedsStr = lines.get(0).substring(7);
        List<Long> seedList = Arrays.stream(seedsStr.split(" ")).map(Long::valueOf).toList();

        List<Range> ranges = new ArrayList<>();

        for (int i = 2; i < lines.size(); i++) {
            final String line = lines.get(i);

            if (!Character.isDigit(line.charAt(0))) {
                almanacMap.put(index, ranges);
                index++;
                ranges = new ArrayList<>();
            } else {
                String[] numbers = line.split(" ");
                ranges.add(new Range(Long.parseLong(numbers[0]), Long.parseLong(numbers[1]), Long.parseLong(numbers[2])));
            }
        }
        almanacMap.put(index++, ranges);

        System.out.println("seedList: " + seedList);
        System.out.println("almanacMap: " + almanacMap);

        for (Long seed : seedList) {
            long soil = castRange(seed, almanacMap.get(0));
            long fertilizer = castRange(soil, almanacMap.get(1));
            long water = castRange(fertilizer, almanacMap.get(2));
            long light = castRange(water, almanacMap.get(3));
            long temperature = castRange(light, almanacMap.get(4));
            long humidity = castRange(temperature, almanacMap.get(5));
            long location = castRange(humidity, almanacMap.get(6));

            almanacList.add(new Almanac(seed, soil, fertilizer, water, light, temperature, humidity, location));
        }

        System.out.println("almanacList: " + almanacList);

        Almanac result = almanacList.stream().min(Comparator.comparingLong(Almanac::location))
                .orElseThrow(() -> new RuntimeException("List is empty"));
        System.out.println("Min location = " + result.location());
    }

    public static long castRange(final long source, final List<Range> ranges) {
        for (final Range range : ranges) {
            if (range.source() <= source && range.source() + range.length() > source) {
                final long diff = source - range.source();
                return range.destination() + diff;
            }
        }
        return source;
    }

    record Almanac(long seed, long soil, long fertilizer, long water, long light, long temperature, long humidity,
                   long location) {
    }

    public record Range(long destination, long source, long length) {
    }
}
