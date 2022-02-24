package duplicate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solutions {

    Map<Integer, Integer> solution1(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer i : arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        return map.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue));
    }

    List<Map.Entry<Integer, Long>> solution2(int[] nums) {
        return Arrays.stream(nums).parallel()
                .boxed()
                .collect(Collectors.groupingBy(e -> e,
                        Collectors.counting()))
                .entrySet().stream().parallel()
                .filter(e -> e.getValue() > 1)
                .collect(Collectors.toList());
    }

    String solution3(int[] nums) {
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.sort(nums);
        int count = 0;
        int tmp = nums[0];
        if (tmp == nums[nums.length - 1]) {
            return tmp + "=" + nums.length;
        }
        for (int i = 0; i < nums.length; i++) {
            if (tmp == nums[i]) {
                count++;
            }
            if (tmp != nums[i] && count > 1) {
                stringBuilder.append(tmp)
                        .append("=")
                        .append(count)
                        .append(System.lineSeparator());
                count = 1;
            }
            if (i == nums.length - 1 && count > 1) {
                stringBuilder.append(tmp)
                        .append("=")
                        .append(count)
                        .append(System.lineSeparator());
                count = 1;
            }
            tmp = nums[i];
        }
        return stringBuilder.toString();
    }

    Map<Integer, Integer> solution4(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer name : nums) {
            Integer count = map.get(name);
            if (count == null) {
                map.put(name, 1);
            } else {
                map.put(name, ++count);
            }
        }
        return map.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue));
    }
}
