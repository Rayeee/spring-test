package stream;

import com.google.common.collect.Lists;
import rpc.UserDto;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by zhugongyi on 2017/8/1.
 */
public class Java8Test {

    public static void main(String[] args) {
//        String a = "";
//        Optional.ofNullable(null).ifPresent(System.out::println);
//        System.out.println(Optional.ofNullable(a).map(String::length).orElse(-1));
//        System.out.println(IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).reduce((i, j) -> i + j).getAsInt());
//        System.out.println(IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).reduce(0, (i, j) -> i + j));
//        System.out.println(Stream.of("1", "2", "3").collect(Collectors.joining(",")));
//        System.out.println(Stream.of(1, 2, 3, 4, 5).map(e -> "'" + e + "' ").reduce(String::concat).get());
//        System.out.println(Stream.of(1, 2, 3, 4).reduce(Integer::sum).get());

//        IntStream.rangeClosed(1, 100).limit(30).skip(20).forEach(System.out::println);
//        Stream.of("a", "a", "b", "c", "d", "e", "e").distinct().forEach(System.out::println);
//        System.out.println(Stream.of(1, 2, 3, 4, 5).anyMatch(e -> e > 3));
        List<UserDto> list = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            UserDto dto = new UserDto(i, "", i, new Date());
            list.add(dto);
        }
        for (int i = 5; i < 15; i++) {
            UserDto dto = new UserDto(i, "", i, new Date());
            list.add(dto);
        }
        list.stream().forEach(System.out::println);
        Map<Integer, List<UserDto>> collect = list.stream().collect(Collectors.groupingBy(UserDto::getId));
    }

}
