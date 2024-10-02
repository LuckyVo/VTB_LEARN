package vtb.learn.dz2;

import vtb.learn.dz2.model.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class dz2 {

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 1, 2, 3, 4));
        List<Person> list2 = new ArrayList<>(List.of(
                new Person("Абу", 19, "ИНЖЕНЕР"),
                new Person("УБА", 20, "ИНЖЕНЕР"),
                new Person("БУБА", 21, "БАНДЮГА"),
                new Person("ГУБА", 22, "СПЕЦИАЛИСТ"),
                new Person("ДУРА", 23, "ПОДСТРЕКАТЕЛЬ"),
                new Person("АВДЕЦ", 24, "ИНЖЕНЕР"),
                new Person("ПЕСНЯ", 25, "ПИРАТ"),
                new Person("ПОСЛЕ", 26, "ОТСТРЕКАТЕЛЬ"),
                new Person("АВДЕЦА", 27, "ЗАСТРЕКАТЕЛЬ"),
                new Person("ГУЛЯЛАМАШАПОШОССЕ", 28, "ИНЖЕНЕР")
        ));
        List<String> list3 = new ArrayList<>(List.of("ИНЖЕНЕР", "БАНДЮГА", "СПЕЦИАЛИСТ", "ПОДСТРЕКАТЕЛЬ", "ПИРАТ", "ОТСТРЕКАТЕЛЬ", "ЗАСТРЕКАТЕЛЬ"));
        String st1 = "инженер бандюга специалист подстрекатель пират отстрекатель застрекатель инженер";
        String[] st2 = new String[]{
                "инженер бандюга специалист подстрекатель пират отстрекатель застрекатель инженер",
                "инженер бандюга специалист подстрекатель пират отстрекатель застрекатель инженер",
                "инженер бандюга специалист подстрекатель пират отстрекатель застрекатель инженер",
                "инженер бандюга специалист подстрекатель пират отстрекатель застрекатель инженер",
                "инженер бандюга специалист подстрекатель пират отстрекатель застрекатель инженер"
        };

        //удаление из листа всех дубликатов
        var res1 = list1.stream()
                .distinct()
                .toList();
//        System.out.println(res1);

        //Найдите в списке целых чисел 3-е наибольшее число
        var res2 = list1.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .sorted()
                .findFirst();
//        System.out.println(res2);

        //Найдите в списке целых чисел 3-е наибольшее «уникальное» число
        var res3 = list1.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .sorted()
                .findFirst();
//        System.out.println(res3);

        //необходимо получить список имен 3 самых старших сотрудников с должностью «Инженер», в порядке убывания возраста
        var res4 = list2.stream()
                .filter(person -> person.getJobTitle().equals("ИНЖЕНЕР"))
                .sorted(Comparator.comparingInt(Person::getAge))
                .limit(3)
                .toList();
//        System.out.println(res4);

        //посчитайте средний возраст сотрудников с должностью «Инженер»
        var res5 = list2.stream()
                .filter(person -> person.getJobTitle().equals("ИНЖЕНЕР"))
                .collect(Collectors.averagingInt(Person::getAge));
//        System.out.println(res5);

        //Найдите в списке слов самое длинное
        var res6 = list3.stream()
                .max(Comparator.comparingInt(String::length));
//        System.out.println(res6);

        //Постройте хеш-мапы, в которой будут хранится пары: слово - сколько раз оно встречается во входной строке
        var res7 = Arrays.stream(st1.split(" "))
                .collect(Collectors.groupingBy(String::strip, Collectors.counting()));
//        System.out.println(res7);

        //Отпечатайте в консоль строки из списка в порядке увеличения длины слова, если слова имеют одинаковую длины,
        // то должен быть сохранен алфавитный порядок
        Arrays.stream(st1.split(" "))
                .sorted(Comparator.comparingInt(String::length)
                        .thenComparing(Function.identity()))
                .forEach(System.out::println);

        //Имеется массив строк, в каждой из которых лежит набор из 5 строк,
        // разделенных пробелом, найдите среди всех слов самое длинное, если таких слов несколько, получите любое из них
        var res9 = Arrays.stream(st2)
                .map(st -> st.split(" "))
                .flatMap(Stream::of)
                .max(Comparator.comparingInt(String::length))
                .orElseThrow();
//        System.out.println(res9);

    }
}
